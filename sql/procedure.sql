/*
Procedimiento que automatiza el incremento de una determinada especie
para cuando esta, se ingresa por unidad al sistema
*/
CREATE OR REPLACE FUNCTION actualizar_cantidad_especie_compra()
RETURNS trigger AS
$BODY$
declare 
objeto record;
begin
for objeto in (select especie from unidad join 
compra_especie on compra_especie.id=unidad.detalle where unidad.id=new.id) loop
update especie set cantidad=cantidad+1 where id=objeto.especie;
end loop;
return null;
end;
$BODY$
LANGUAGE plpgsql;

-- Segundo procedimiento almacenado

CREATE OR REPLACE FUNCTION actualizar_cantidad_especie_venta()
RETURNS trigger AS
$BODY$
declare 
objeto record;
begin

for objeto in (select especie, especie.precio valor, unidad.id as unidad
from unidad join compra_especie on compra_especie.id=unidad.detalle join especie on especie.id=especie
where unidad.id=new.unidad) loop
update especie set cantidad=cantidad-1 where id=objeto.especie;
update venta set ingreso = ingreso+objeto.valor where id = new.venta;
update unidad set estado = false where id = objeto.unidad;
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

for objeto in (select producto, precio, terminado.id as terminado from terminado join producto on producto.id=producto where terminado.id=new.terminado) loop
update producto set cantidad=cantidad-1 where id=objeto.terminado;
update venta set ingreso = ingreso + objeto.precio where id = new.venta;
update terminado set estado = false where id = objeto.terminado;
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

for objeto in (select produccion, costo from detalle_produccion join unidad on unidad.id=unidad
join compra_especie on compra_especie.id = unidad.detalle
 where detalle_produccion.id=new.id) loop
update produccion set inversion=inversion+objeto.costo where id=objeto.produccion;
end loop;
return null;
end;
$BODY$
LANGUAGE plpgsql;

/*
procedimiento encargado de determinar automaticamente el gasto de una determinada compra,
tomando el costo de cada articulo comprado, multiplicandolo por la cantidad de unidade y luego 
sumando todo, el total es el gasto total de compra
*/

CREATE OR REPLACE FUNCTION actualizar_gasto_compra()
  RETURNS trigger AS
$BODY$
begin
update compra set gasto=gasto+(new.costo*new.cantidad) where id=new.compra;
return null;
end;
$BODY$
  LANGUAGE plpgsql;

 /*
 Procedimiento encargado de actualizar automaticamente la cantidad de especies ubicadas
 de una determinada compra. Cada vez que se ubica una unidad, se suma uno al atributo 'ubicados' 
 de la entidad 'compra_especie'
 */

CREATE OR REPLACE FUNCTION actualizar_ubicados()
  RETURNS trigger AS
$BODY$
begin
update compra_especie set ubicados=ubicados+1 where id=new.detalle;
update compra set estado='Procesado' where id=new.detalle;
return null;
end;
$BODY$
  LANGUAGE plpgsql;
  