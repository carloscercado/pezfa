﻿insert into indicador
values (1, 'CAPACIDAD', 80,50,'DISPONIBILIDAD DE LOS ALMACENES SON EXITOSOS','DISPONIBILIDAD DE ALMACENES MUY BAJAS, REALIZAR MAS COMPRAS','MEJORAR LA DISPONIBILIDAD DE LOS ALMACENES','MEDIR LA CAPACIDAD DE CAVA'),
	(2, 'SATISFACCIÓN', 90,75, 'CLIENTES SATISFECHOS EXITOSAMENTE','MEJORAR LA CALIDAD DE LAS ESPECIES PARA LOS CLIENTES','BUSCAR OTRO MÉTODO DE SATISFACCIÓN PARA LOS CLIENTES','MEDIR LA SATISFACCIÓN DEL CLIENTE'),
	(3, 'VENTAS EN BOLIVARES', 90,75, 'LAS VENTAS FUERÓN EXITOSAS','VENTAS DEMASIADO BAJAS BUSCAR OTRO MÉTODO DE VENTA','DEBEN MEJORAR EL RENDIMIENTO DE LAS VENTAS','MEDIR LAS VENTAS EN BOLIVARES'),
	(4, 'VENTAS EN KILOS', 90,75, 'LAS VENTAS FUERÓN EXITOSAS','VENTAS DEMASIADO BAJAS BUSCAR OTRO MÉTODO DE VENTA','DEBEN MEJORAR EL RENDIMIENTO DE LAS VENTAS','MEDIR LAS VENTAS EN KILOS'),
	(5, 'FORMACIÓN EMPLEADOS', 70,40, 'EDUCACIÓN DE LOS EMPLEADOS EXITOSO','DEBEN MEJORAR LA EDUCACIÓN DE LOS EMPLEADOS','LA EDUCACIÓN DE LOS EMPLEADOS ES REGULAR ','MEDIR LA FORMACIÓN DE LOS EMPLEADOS');

insert into almacen 
	values (1, 'ALMACEN A', 'AV. LAS PALOMAS', '(293)-1234561'),
		(2, 'ALMACEN B', 'AV. GRAN MARISCAL', '(293)-4653782'),
		(3, 'ALMACEN C', 'AV. EL PEÑON', '(293)-8796450'),
		(4, 'ALMACEN D', 'AV. PERIMETRAL', '(293)-1637531'),
		(5, 'ALMACEN E', 'AV. CANCAMURE', '(293)-4734373');


       
insert into empleado
	VALUES (1, '15764832', 'MARIA', 'JOSE', 'PEREZ', 'ALVARADO', now(), 'F', 'ADMINISTRACION', 3),
		(2, '24657978', 'PEDRO', 'JOSE', 'GARCIA', 'ALVARADO', now(), 'M', 'RRHH', 3),
		(3, '19375232', 'ROSA', 'JOSE', 'MARQUEZ', 'ALVARADO', now(), 'F', 'PRODUCCION', 2),
		(4, '16425538', 'PABLO', 'JOSE', 'LOPEZ', 'ALVARADO', now(), 'M', 'OPERACION', 3),
		(5, '21685725', 'PATRICIA', 'JOSE', 'ROMERO', 'ALVARADO', now(), 'F', 'ADMINISTRACION', 3),
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

INSERT INTO USUARIO
	values (1, 'admin', 'admin', 'ADMINISTRADOR', 1),
		(2, 'usuario', '1234', 'USUARIO', 2),
		(3, 'rosa', '12345', 'ADMINISTRADOR', 3),
		(4, 'pablo', '123456', 'USUARIO', 4),
		(5, 'usua', '1234567', 'ADMINISTRADOR', 5);

INSERT INTO CAMION 
	VALUES (1, 'SUBARU', 'MITSUBUSHI', 'RIP203'),
	(2, '350', 'FORD', 'TR1SI2');

INSERT INTO COMPRA
    VALUES (1, 'AA001', '2017-01-05', 1, 1, 1, 3, 'COMPRADO'),
    (2, 'AA002', '2017-02-05', 2, 1, 2, 6, 'COMPRADO'),
    (3, 'AA003', '2017-03-05', 3, 1, 1, 5, 'COMPRADO'),
    (4, 'AA004', '2017-04-05', 4, 1, 2, 6, 'COMPRADO'),
    (5, 'AA005', '2017-05-05', 5, 1, 1, 5, 'COMPRADO'),
    (6, 'AA006', '2017-06-05', 1, 1, 2, 6, 'COMPRADO'),
    (7, 'AA007', '2017-07-05', 2, 1, 1, 5, 'COMPRADO'),
    (8, 'AA008', '2017-08-05', 3, 1, 2, 3, 'COMPRADO'),
    (9, 'AA009', '2017-09-05', 4, 1, 1, 5, 'COMPRADO'),
    (10, 'AA010', '2017-10-05', 5, 1, 2, 3, 'COMPRADO'),
    (11, 'AA011', '2017-11-05', 1, 1, 1, 5, 'COMPRADO'),
    (12, 'AA012', '2017-10-05', 2, 1, 2, 6, 'COMPRADO');

INSERT INTO COMPRA_ESPECIE
	VALUES (1, 1, 1, 1000, 500, 0),
		(2, 1, 2, 1000, 500, 0),
		(3, 2, 3, 1000, 500, 0),
		(4, 2, 4, 1000, 500, 0), 
		(5, 3, 5, 1000, 500, 0),
		(6, 3, 1, 1000, 500, 0),
		(7, 4, 2, 1000, 500, 0),
		(8, 4, 3, 1000, 2000, 0),
		(9, 5, 4, 1000, 500, 0),
		(10, 5, 5, 1000, 500, 0), 
		(11, 6, 1, 1000, 500, 0),
		(12, 7, 2, 1000, 500, 0),
		(13, 8, 3, 1000, 2000, 0),
		(14, 9, 4, 1000, 500, 0),
		(15, 10, 5, 1000, 500, 0),
		(16, 11, 1, 1000, 500, 0),
		(17, 12, 2, 1000, 500, 0);

INSERT INTO UBICACION (ID, DETALLE, CAVA, PESO, CODIGO, ESTADO)
	VALUES(1, 1, 1, 1000, 'A', TRUE),
		(2, 2, 2, 1000, 'B', TRUE),
		(3, 3, 3, 1000, 'C', TRUE),
		(4, 4, 4, 1000, 'D', TRUE), 
		(5, 5, 5, 1000, 'E', TRUE),
		(6, 6, 1, 1000, 'F', TRUE),
		(7, 7, 2, 1000, 'G', TRUE),
		(8, 8, 3, 1000, 'H', TRUE),
		(9, 9, 4, 1000, 'I', TRUE),
		(10, 10, 5, 1000, 'J', TRUE), 
		(11, 11, 1, 1000, 'K', TRUE),
		(12, 12, 2, 1000, 'L', TRUE),
		(13, 13, 3, 1000, 'M', TRUE),
		(14, 14, 4, 1000, 'N', TRUE),
		(15, 15, 5, 1000, 'Ñ', TRUE),
		(16, 16, 1, 1000, 'O', TRUE),
		(17, 17, 2, 1000, 'P', TRUE);

INSERT INTO VENTA(ID, FACTURA, FECHA, CLIENTE, USUARIO, INGRESO, KILO_TOTAL, DEVUELTA)
	VALUES (1, 'AA-001', '2017-01-05', 1, 1, 50000, 200, FALSE),
		(2, 'AA-002', '2017-02-05', 2, 1, 70000, 200, FALSE),
		(3, 'AA-003', '2017-03-05', 3, 1, 55000, 200, FALSE),
		(4, 'AA-004', '2017-04-05', 4, 1, 60000, 200, FALSE),
		(5, 'AA-005', '2017-05-05', 5, 1, 100000, 200, FALSE),
		(6, 'AA-006', '2017-06-05', 1, 1, 110000, 200, FALSE),
		(7, 'AA-007', '2017-07-05', 2, 1, 100000, 200, FALSE),
		(8, 'AA-008', '2017-08-05', 3, 1, 80000, 200, FALSE),
		(9, 'AA-009', '2017-09-05', 4, 1, 115000, 200, FALSE),
		(10, 'AA-010', '2017-01-05', 5, 1, 120000, 200, FALSE),
		(11, 'AA-011', '2017-02-05', 1, 1, 125000, 200, FALSE),
		(12, 'AA-012', '2017-03-05', 2, 1, 130000, 200, FALSE),
		(13, 'AA-013', '2017-04-05', 3, 1, 50000, 200, FALSE),
		(14, 'AA-014', '2017-05-05', 4, 1, 70000, 200, FALSE),
		(15, 'AA-015', '2017-06-05', 5, 1, 55000, 200, FALSE),
		(16, 'AA-016', '2017-07-05', 1, 1, 60000, 200, FALSE),
		(17, 'AA-017', '2017-08-05', 2, 1, 100000, 200, FALSE),
		(18, 'AA-018', '2017-09-05', 3, 1, 110000, 200, FALSE),
		(19, 'AA-019', '2017-10-05', 4, 1, 100000, 200, FALSE),
		(20, 'AA-020', '2017-10-05', 5, 1, 80000, 200, FALSE),
		(21, 'AA-021', '2017-11-05', 1, 1, 115000, 200, FALSE),
		(22, 'AA-022', '2017-11-05', 2, 1, 120000, 200, FALSE),
		(23, 'AA-023', '2017-02-05', 3, 1, 125000, 200, FALSE),
		(24, 'AA-024', '2017-03-05', 4, 1, 130000, 200, FALSE);

INSERT INTO VENTA_ESPECIE(ID, CANTIDAD, UBICACION, VENTA)
	VALUES (1, 200, 1, 1),
		(2, 200, 2, 2),
		(3, 200, 3, 3),
		(4, 200, 4, 4),
		(5, 200, 5, 5),
		(6, 200, 1, 6),
		(7, 200, 2, 7),
		(8, 200, 3, 8),
		(9, 200, 4, 9),
		(10, 200, 5, 10),
		(11, 200, 1, 11),
		(12, 200, 2, 12),
		(13, 200, 3, 13),
		(14, 200, 4, 14),
		(15, 200, 5, 15),
		(16, 200, 1, 16),
		(17, 200, 2, 17),
		(18, 200, 3, 18),
		(19, 200, 4, 19),
		(20, 200, 5, 20),
		(21, 200, 1, 21),
		(22, 200, 2, 22),
		(23, 200, 3, 23),
		(24, 200, 4, 24);
