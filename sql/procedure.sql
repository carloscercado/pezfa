-- Primer procedimiento almacenado

CREATE OR REPLACE FUNCTION actualizar_cantidad_especie_compra()
RETURNS trigger AS
$BODY$
declare 
objeto record;

begin

for objeto in (select especie, unidad.precio,compra from unidad join 
compra_especie on compra_especie.id=unidad.detalle join 
especie on especie.id=compra_especie.especie where unidad.id=new.id) loop
update especie set cantidad=cantidad+1 where id=objeto.especie;
update compra set gasto = gasto+objeto.precio where id = objeto.compra;
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

for objeto in (select especie, especie.precio valor 
from unidad join compra_especie on compra_especie.id=unidad.detalle join especie on especie.id=especie
where unidad.id=new.unidad) loop
update especie set cantidad=cantidad-1 where id=objeto.especie;
update venta set ingreso = ingreso+objeto.valor where id = new.venta;
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

for objeto in (select produccion, precio from detalle_produccion join unidad on unidad.id=unidad
 where detalle_produccion.id=new.id) loop
update produccion set inversion=inversion+objeto.precio where id=objeto.produccion;
end loop;
return null;
end;
$BODY$
LANGUAGE plpgsql;