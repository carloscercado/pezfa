create table if not exists insumo
(
  id serial primary key,
    codigo varchar(30) not null unique,
    nombre varchar(20) not null,
    cantidad float default 0,
    medida varchar(20),
    minimo float default 10,
    tipo varchar(20) not null
    
);


create table if not exists indicador
(
  id serial PRIMARY KEY,
  nombre character varying(20) NOT NULL,
  bueno double precision NOT NULL,
  malo double precision NOT NULL,
  mensaje_bueno text NOT NULL,
  mensaje_malo text NOT NULL,
  mensaje_aceptable text NOT NULL,
  descripcion text NOT NULL
);



create table if not exists proveedor
(
    id serial primary key,
    rif varchar(20) not null unique,
    nombre text not null,
    direccion text not null,
    telefono varchar(15),
    correo text
);

create table if not exists camion
(
    id serial primary key,
    marca varchar(15) not null,
    modelo varchar(15) not null,
    placa varchar(15) not null
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
    precio numeric(10,2) not null,
    descripcion text not null
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
    cantidad float default 0,
    maximo float default 100,
    minimo float default 10,
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
    camion int not null references camion(id),
    chofer int not null references empleado(id),
    estado varchar(20),
    gasto numeric(10,2) default 0,
    kilo_total float default 0
);

create table if not exists venta
(
    id serial primary key,
    factura varchar(20) not null unique,
    fecha date default now()::date,
    cliente int not null references cliente (id),
    usuario int not null references usuario(id),
    ingreso numeric(10,2) default 0
);

create table if not exists cava
(
    id serial primary key,
    nombre varchar(10) not null,
    capacidad float,
    capacidad_disponible float,
    almacen int not null references almacen (id)    
);

create table if not exists compra_especie
(
    id serial primary key,  
    compra int not null references compra(id),
    especie int not null references especie(id),
    cantidad float not null,
    costo numeric(10,2) default 0,
    ubicados float default 0
);

create table if not exists ubicacion
(
    id serial primary key,
    detalle int not null references compra_especie (id),
    cava int not null references cava (id),
    peso float not null,
    codigo varchar(36) not null unique,
    estado boolean default true    
);

create table if not exists venta_especie
(
    id serial primary key,
    cantidad float not null,
    ubicacion int not null references ubicacion(id),
    venta int not null references venta (id)
);

create table if not exists detalle_produccion
(
    id serial primary key,
    produccion int not null references produccion(id),
    ubicacion int not null references ubicacion (id),
    cantidad float not null
);

create table if not exists unidad
(
    id serial primary key,
    produccion int not null references produccion(id),
    producto int not null references producto (id),
    vencimiento date not null,
    lote varchar(20) not null,
    estado boolean default true    
);

create table if not exists venta_unidad
(
    id serial primary key,
    unidad int not null references unidad (id),
    venta int not null references venta (id)
);

CREATE TRIGGER actualizar_inversion_produccion
  AFTER INSERT
  ON detalle_produccion
  FOR EACH ROW
  EXECUTE PROCEDURE actualizar_inversion_produccion();

CREATE TRIGGER actualizar_cantidad_especie_compra
  AFTER INSERT
  ON ubicacion
  FOR EACH ROW
  EXECUTE PROCEDURE actualizar_cantidad_especie_compra();

CREATE TRIGGER actualizar_cantidad_producto_venta
  AFTER INSERT
  ON venta_unidad
  FOR EACH ROW
  EXECUTE PROCEDURE actualizar_cantidad_producto_venta();

  CREATE TRIGGER actualizar_cantidad_especie_venta
  AFTER INSERT
  ON venta_especie
  FOR EACH ROW
  EXECUTE PROCEDURE actualizar_cantidad_especie_venta();

CREATE TRIGGER actualizar_gasto_compra
  AFTER INSERT
  ON compra_especie
  FOR EACH ROW
  EXECUTE PROCEDURE actualizar_gasto_compra();

CREATE TRIGGER actualizar_ubicados
  AFTER INSERT
  ON ubicacion
  FOR EACH ROW
  EXECUTE PROCEDURE actualizar_ubicados();