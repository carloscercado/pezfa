create table if not exists proveedor
(
    id serial primary key,
    rif varchar(20) not null unique,
    nombre text not null,
    direccion text not null,
    telefono varchar(15),
    correo text
);

create table if not exists cliente
(
    id serial primary key,
    rif varchar(20) not null unique,
    nombre text not null,
    direccion text not null,
    telefono varchar(15),
    correo text
);

create table if not exists empleado
(
    id serial primary key,
    cedula varchar(20) not null unique,
    primer_nombre varchar(20) not null,
    segundo_nombre varchar(20),
    primer_apellido varchar(20) not null,
    segundo_apellido varchar(20),
    nacimiento date not null,
    sexo varchar(1) not null,
    cargo varchar(20) not null
);

create table if not exists almacen
(
    id serial primary key,
    nombre varchar(20) not null,
    direccion text not null,
    telefono varchar(15)
);

create table if not exists producto
(
    id serial primary key,
    codigo varchar(30) not null unique,
    nombre varchar(20) not null,
    cantidad int default 0,
    maximo int default 10,
    minimo int default 100,
    categoria varchar(20) not null,
    precio numeric(10,2) not null
);

create table if not exists produccion
(
    id serial primary key,
    descripcion text not null,
    fecha date default now()::date,
    inversion numeric(10,2) default 0
);

create table if not exists especie
(
    id serial primary key,
    codigo varchar(30) not null unique,
    nombre varchar(20) not null,
    cantidad int default 0,
    maximo int default 100,
    minimo int default 10,
    tipo varchar(20) not null,
    precio numeric(10,2) default 0
);

create table if not exists usuario
(
    id serial primary key,
    usuario varchar(20) not null unique,
    clave text not null,
    rol varchar(20) not null,
    empleado int not null references empleado(id)
);

create table if not exists auditoria 
(
    id serial primary key,
    fecha date default now()::date,
    hora time default now()::time,
    tipo varchar(20) not null,
    descripcion text not null,
    usuario int not null references usuario (id)
);

create table if not exists compra
(
    id serial primary key,
    orden varchar(20) not null unique,
    fecha date default now()::date,
    proveedor int not null references proveedor (id),
    usuario int not null references usuario(id),
    estado varchar(20),
    gasto numeric(10,2) default 0
);

create table if not exists venta
(
    id serial primary key,
    factura varchar(20) not null unique,
    fecha date default now()::date,
    hora time default now()::time,
    cliente int not null references cliente (id),
    usuario int not null references usuario(id),
    ingreso numeric(10,2) default 0
);

create table if not exists cava
(
    id serial primary key,
    nombre varchar(10) not null,
    almacen int not null references almacen (id)
);

create table if not exists compra_especie
(
    id serial primary key,  
    compra int not null references compra(id),
    especie int not null references especie(id),
    cantidad int not null,
    costo numeric(10,2) default 0
);

create table if not exists unidad
(
    id serial primary key,
    detalle int not null references compra_especie (id),
    cava int not null references cava (id),
    peso float not null,
    precio numeric(10,2) default 0,
    codigo varchar(30) not null unique,
    estado boolean default true    
);

create table if not exists venta_unidad
(
    id serial primary key,
    unidad int not null references unidad(id),
    venta int not null references venta (id)
);

create table if not exists detalle_produccion
(
    id serial primary key,
    produccion int not null references produccion(id),
    unidad int not null references unidad (id)
);

create table if not exists terminado
(
    id serial primary key,
    produccion int not null references produccion(id),
    producto int not null references producto (id),
    vencimiento date not null,
    lote varchar(20) not null
);

create table if not exists venta_terminado
(
    id serial primary key,
    terminado int not null references terminado (id),
    venta int not null references venta (id)
);

CREATE TRIGGER actualizar_inversion_produccion
  AFTER INSERT
  ON detalle_produccion
  FOR EACH ROW
  EXECUTE PROCEDURE actualizar_inversion_produccion();



CREATE TRIGGER actualizar_cantidad_especie_compra
  AFTER INSERT
  ON unidad
  FOR EACH ROW
  EXECUTE PROCEDURE actualizar_cantidad_especie_compra();

CREATE TRIGGER actualizar_cantidad_producto_venta
  AFTER INSERT
  ON venta_terminado
  FOR EACH ROW
  EXECUTE PROCEDURE actualizar_cantidad_producto_venta();

  CREATE TRIGGER actualizar_cantidad_especie_venta
  AFTER INSERT
  ON venta_unidad
  FOR EACH ROW
  EXECUTE PROCEDURE actualizar_cantidad_especie_venta();

CREATE TRIGGER actualizar_gasto_compra
  AFTER INSERT
  ON compra_especie
  FOR EACH ROW
  EXECUTE PROCEDURE actualizar_gasto_compra();
