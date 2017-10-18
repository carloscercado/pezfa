﻿insert into indicador
values (1, 'CAPACIDAD', 80,50,'MENSAJE BUENO','MENSAJE MALO','MENSAJE ACEPTABLE','MEDIR LA CAPACIDAD DE CAVA'),
	(2, 'SATISFACCION', 90,75, 'MENSAJE BUENO','MENSAJE MALO','MENSAJE ACEPTABLE','MEDIR LA SATISFACCION DEL CLIENTE');
		

insert into almacen 
	values (1, 'ALMACEN A', 'AV. LAS PALOMAS', '(293)-1234561'),
		(2, 'ALMACEN B', 'AV. GRAN MARISCAL', '(293)-4653782'),
		(3, 'ALMACEN C', 'AV. EL PEÑON', '(293)-8796450'),
		(4, 'ALMACEN D', 'AV. PERIMETRAL', '(293)-1637531'),
		(5, 'ALMACEN E', 'AV. CANCAMURE', '(293)-4734373');


       
insert into empleado
	VALUES (1, '15764832', 'MARIA', 'JOSE', 'PEREZ', 'ALVARADO', now(), 'F', 'ADMINISTRACION', 4),
		(2, '24657978', 'PEDRO', 'JOSE', 'GARCIA', 'ALVARADO', now(), 'M', 'RRHH', 3),
		(3, '19375232', 'ROSA', 'JOSE', 'MARQUEZ', 'ALVARADO', now(), 'F', 'PRODUCCION', 2),
		(4, '16425538', 'PABLO', 'JOSE', 'LOPEZ', 'ALVARADO', now(), 'M', 'OPERACION', 4),
		(5, '21685725', 'PATRICIA', 'JOSE', 'ROMERO', 'ALVARADO', now(), 'F', 'ADMINISTRACION', 4),
		(6, '22785825', 'ARTURO', 'MERLIN', 'CORAZON', 'DE LEON', now(), 'M', 'CHOFER', 1),
		(7, '24645225', 'CHARLES', 'WINSTON', 'CHURCHILL', 'LEON', now(), 'M', 'CHOFER', 1);
		

insert into ESPECIE (id, codigo, tipo, nombre) 
	values (1, '0001', 'MOLUSCO', 'PULPO'),
		(2, '0002', 'PEZ', 'TIBURON'),
		(3, '0003', 'CRUSTACEO', 'JAIBA'),
		(4, '0004', 'MOLUSCO', 'CARACOL'),
		(5, '0005', 'PEZ', 'ATUN');

INSERT INTO CLIENTE 
	VALUES (1, '12345678', 'J', 'ROBERTO', 'AV. GRAN MARISCAL', '(293)-1234561', 'ROBERTO@GMAIL.COM'),
		(2, '24446543', 'V', 'GLADYS', 'AV. UNIVERSIDAD', '(293)-1234561', 'GLADYS@GMAIL.COM'),
		(3, '9527242', 'E', 'JOSE', 'AV. CANCAMURE', '(293)-1234561', 'JOSE@GMAIL.COM'),
		(4, '3153518', 'J', 'ANDREA', 'AV. PERIMETRAL', '(293)-1234561', 'ANDREA@GMAIL.COM'),
		(5, '19353264', 'V', 'ORLANDO', 'AV. ROTARIA', '(293)-1234561', 'ORLANDO@GMAIL.COM');

INSERT INTO PROVEEDOR
	VALUES (1, '25267638', 'LA CARACOLA C.A.', 'GRAN MARISCAL', '(293)-1234561', 'LACARACOLA@GMAIL.COM'),
		(2, '52634359', 'TIVEN C.A.', 'UNIVERSIDAD', '(293)-1234561', 'TIVEN@GMAIL.COM'),
		(3, '63753642', 'ALTAMAR C.A.', 'CANCAMURE', '(293)-1234561', 'ALTAMAR@GMAIL.COM'),
		(4, '62628479', 'BRISAMAR C.A.', 'PERIMETRAL', '(293)-1234561', 'BRISAMAR@GMAIL.COM'),
		(5, '54253178', 'OCEANIA C.A.', 'AV. ROTARIA', '(293)-1234561', 'OCEANIA@GMAIL.COM');

INSERT INTO PRODUCTO (ID, CODIGO, NOMBRE, CATEGORIA, DESCRIPCION, PRECIO)
	VALUES (1, 00001, 'LATA DE ATUN', 'ENLATADO', 'PESCADO EN ACEITE', 1000),
		(2, 00002, 'ENVASADO CAMARONES', 'ENVASE', 'CARMARONES PROCESADOS EN ENVASES', 1000),
		(3, 00003, 'LATA DE PEPITONAS', 'ENLATADO', 'PEPITONAS EN ACEITE', 1000),
		(4, 00004, 'LATA DE SARDINAS', 'ENLATADO', 'SARDINAS EN ACEITE', 1000);

INSERT INTO CAVA 
	VALUES (1, 'CAVA 1', 10000,10000,1),
		(2, 'CAVA 2',10000,10000, 2),
		(3, 'CAVA 3',10000,10000, 3),
		(4, 'CAVA 4',10000,10000, 4),
		(5, 'CAVA 5', 10000,10000,5);

INSERT INTO usuario
	values (1, 'admin', 'admin', 'ADMINISTRADOR', 1),
		(2, 'usuario', '1234', 'USUARIO', 2),
		(3, 'rosa', '12345', 'ADMINISTRADOR', 3),
		(4, 'pablo', '123456', 'USUARIO', 4),
		(5, 'usua', '1234567', 'ADMINISTRADOR', 5);

INSERT INTO CAMION 
	VALUES (1, 'SUBARU', 'MITSUBUSHI', 'RIP203'),
	(2, '350', 'FORD', 'TR1SI2');

INSERT INTO COMPRA 
	VALUES (1, '0001', NOW (), 1, 1, 1, 3, 'COMPRADO'),
	(2, '0002', NOW (), 2, 1, 2, 6, 'COMPRADO'),
	(3, '0003', NOW (), 3, 1, 1, 5, 'COMPRADO'),
	(4, '0004', NOW (), 4, 1, 2, 6, 'COMPRADO'),
	(5, '0005', NOW (), 5, 1, 1, 5, 'COMPRADO');	

INSERT INTO COMPRA_ESPECIE
	VALUES (1, 1, 1, 1000, 500, 0),
		(2, 1, 2, 1000, 500, 0),
		(3, 1, 3, 1000, 500, 0),
		(4, 2, 4, 1000, 500, 0), 
		(5, 2, 5, 1000, 500, 0),
		(6, 2, 1, 1000, 500, 0),
		(7, 3, 2, 1000, 500, 0),
		(8, 3, 3, 1000, 2000, 0),
		(9, 3, 4, 1000, 500, 1000),
		(10, 4, 5, 1000, 500, 1000), 
		(11, 4, 1, 1000, 500, 1000),
		(12, 4, 2, 1000, 500, 1000),
		(13, 5, 3, 1000, 2000, 1000),
		(14, 5, 4, 1000, 500, 1000),
		(15, 5, 5, 1000, 500, 1000);