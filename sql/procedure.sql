/*
Procedimiento que automatiza el incremento de una determinada especie
para cuando esta, se ingresa kilos de una especie al sistema
*/
CREATE OR REPLACE FUNCTION actualizar_cantidad_especie_compra()
RETURNS trigger AS
$BODY$
declare 
objeto record;
begin
for objeto in (select especie, peso from ubicacion join 
compra_especie on compra_especie.id=ubicacion.detalle where ubicacion.id=new.id) loop
update especie set cantidad=cantidad+objeto.peso where id=objeto.especie;
end loop;
return null;
end;
$BODY$
LANGUAGE plpgsql;
/*
Procedimiento que automaiza la actualizacion de la especie cuando se venden, tambien actualiza el peso restante, 
y el ingreso de la venta
*/

CREATE OR REPLACE FUNCTION actualizar_cantidad_especie_venta()
RETURNS trigger AS
$BODY$
declare 
objeto record;
begin

for objeto in (select especie, venta_especie.cantidad as cantidad, especie.precio valor, ubicacion.cava as cava, venta_especie.cantidad as cantidad, ubicacion.id as ubicacion from venta_especie join ubicacion on ubicacion=ubicacion.id join compra_especie on compra_especie.id=ubicacion.detalle join especie on especie.id=especie
where ubicacion.id=new.ubicacion) loop
update especie set cantidad=cantidad-objeto.cantidad where id=objeto.especie;
update cava set capacidad_disponible = (capacidad_disponible + new.cantidad) where id = new.cava;
update venta set ingreso = ingreso+(objeto.valor*objeto.cantidad) where id = new.venta;
end loop;
return null;
end;
$BODY$
LANGUAGE plpgsql;

-- Tercer procedimiento almacenado

CREATE OR REPLACE FUNCTION actualizar_cantidad_producto_venta()
RETURNS trigger AS
$BODY$
declare 
objeto record;
begin

for objeto in (select producto, precio, unidad.id as unidad from unidad join producto on producto.id=producto where unidad.id=new.unidad) loop
update producto set cantidad=cantidad-1 where id=objeto.unidad;
update venta set ingreso = ingreso + objeto.precio where id = new.venta;
update unidad set estado = false where id = objeto.unidad;
end loop;
return null;
end;
$BODY$
LANGUAGE plpgsql;

-- Cuarto procedimiento almacenado

CREATE OR REPLACE FUNCTION actualizar_inversion_produccion()
RETURNS trigger AS
$BODY$
declare 
objeto record;
begin

for objeto in (select produccion, detalle_produccion.cantidad as cantidad, especie.precio as precio from detalle_produccion join ubicacion on ubicacion.id=ubicacion
join compra_especie on compra_especie.id = ubicacion.detalle join especie on especie = compra_especie.especie
 where detalle_produccion.id=new.id) loop
update produccion set inversion=inversion+(objeto.precio*objeto.cantidad) where id = objeto.produccion;
end loop;
return null;
end;
$BODY$
LANGUAGE plpgsql;

/*
procedimiento encargado de determinar automaticamente el gasto de una determinada compra,
tomando el costo de cada articulo comprado, multiplicandolo por la cantidad de ubicacione y luego 
sumando todo, el total es el gasto total de compra
*/

CREATE OR REPLACE FUNCTION actualizar_gasto_compra()
  RETURNS trigger AS
$BODY$
begin
update compra set gasto=gasto+(new.costo*new.cantidad), kilo_total=(kilo_total+new.cantidad) where id=new.compra;
update compra set kilo_total=(kilo_total+new.cantidad) where id=new.compra;
return null;
end;
$BODY$
  LANGUAGE plpgsql;

 /*
 Procedimiento encargado de actualizar automaticamente la cantidad de especies ubicadas
 de una determinada compra. Cada vez que se ubica una ubicacion, se suma uno al atributo 'ubicados' 
 de la entidad 'compra_especie'
 */

CREATE OR REPLACE FUNCTION actualizar_ubicados()
  RETURNS trigger AS
$BODY$
declare
objeto record;
objeto2 record;
valor int;
begin
update compra_especie set ubicados=ubicados+new.peso where id=new.detalle;
update cava set capacidad_disponible = (capacidad_disponible - new.peso) where id = new.cava;
for objeto in (select compra from compra_especie where id = new.detalle limit 1) loop
	valor = 0;
	for objeto2 in (select * from compra_especie where compra = objeto.compra and (cantidad - ubicados) > 1 ) loop
		valor =	 1;
	end loop;
	if valor = 0 then
		update compra set estado = 'Procesado' where id = objeto.compra;
	end if;
end loop;
return null;
end;
$BODY$
  LANGUAGE plpgsql;
  