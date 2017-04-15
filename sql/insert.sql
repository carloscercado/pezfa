insert into proveedor 
	values (1,'23923165','ALBEJA C.A', 'PTO LA CRUZ, CASA N1', '(293)-7564738',  'albeja@albeja.com'),
	 (2,'23923166','ANOCA C.A', 'PTO LA CRUZ, CASA N1', '(293)-7564738',  'albeja@albeja.com'),
	 (3,'23923167','UJULUM C.A', 'PTO LA CRUZ, CASA N1', '(293)-7564738',  'albeja@albeja.com'),
	 (4,'23923168','CHIPICHIPI C.A', 'PTO LA CRUZ, CASA N1', '(293)-7564738',  'albeja@albeja.com'),
	 (5,'23923169','CARACOLITO C.A', 'PTO LA CRUZ, CASA N1', '(293)-7564738',  'albeja@albeja.com');

insert into cliente 
	values (1,'23923165','PEDRO', 'PTO LA CRUZ, CASA N1', '(293)-7564738',  'albeja@albeja.com'),
	 (2,'23923166','ANOTONIA', 'PTO LA CRUZ, CASA N1', '(293)-7564738',  'albeja@albeja.com'),
	 (3,'23923167','CARLOS ', 'PTO LA CRUZ, CASA N1', '(293)-7564738',  'albeja@albeja.com'),
	 (4,'23923168','PETONIA', 'GUIRIA, CASA N1', '(293)-7564738',  'albeja@albeja.com'),
	 (5,'23923169','ARANZA', 'CUMANA, CASA N1', '(293)-7564738',  'albeja@albeja.com');

insert into empleado 
	values (1,'23923163','PEDRO','ALBERTO', 'LOPEZ', 'ALCANTARA',now(), 'M', 'ALMACENISTA'),
	 (2,'23923162','RICARDO','ALBERTO', 'LOPEZ', 'ALCANTARA',now(), 'M', 'ALMACENISTA'),
	 (3,'23923161','MARIA','ALBERTO', 'LOPEZ', 'ALCANTARA',now(), 'M', 'ALMACENISTA'),
	 (4,'23923165','PETONIA','ALBERTO', 'LOPEZ', 'ALCANTARA',now(), 'M', 'ALMACENISTA'),
	 (5,'23923169','CHICHA','ALBERTO', 'LOPEZ', 'ALCANTARA',now(), 'M', 'ALMACENISTA');

insert into almacen 
	values (1, 'PEÑON', 'AV SANTA ROSA, CUMANA', '(293)-7564738'),
	 (2, 'HUMBOLT 04', 'AV SANTA ROSA, CUMANA', '(293)-7564738'),
	 (3, 'CENTRAL, PEZFA', 'AV SANTA ROSA, CUMANA', '(293)-7564738');

insert into producto
	values (1, '000001', 'LATA CHIPICHIPI', 0, 100,10, 'DEL MAR', 0),	
	 (2, '000002', 'LATA ATUM', 0, 100,10, 'DEL MAR', 0),
	 (3, '000003', 'LATA PEPITONAS', 0, 100,10, 'DEL MAR', 0),
	 (4, '000005', 'LATA JUREL', 0, 100,10, 'DEL MAR', 0),
	 (5, '000006', 'LATA TIBURON', 0, 100,10, 'DEL MAR', 0);

insert into produccion
	values (1, 'PRODUCCION DEL MES', now(), 0),
	(2, 'PRODUCCION DE ATUM', now(), 0);

insert into especie
	values (1, '000001', 'CHIPICHIPI', 0, 100,10, 'DEL MAR', 0),
	 (2, '000002', 'ATUM', 0, 100,10, 'DEL MAR', 0),
	 (3, '000003', 'PEPITONAS', 0, 100,10, 'DEL MAR', 0),
	 (4, '000005', 'JUREL', 0, 100,10, 'DEL MAR', 0),
	 (5, '000006', 'TIBURON', 0, 100,10, 'DEL MAR', 0);

insert into usuario
	values (1, 'admin', 'admin', 'ADMINISTRADOR', 1);

insert into compra
	values (1, '00001', now(), 1, 1,false, 0),
	 (2, '00002', now(),  2, 1,false, 0),
	 (3, '00003', now(),  3, 1, false,0);

insert into venta 
	values (1, '00001', now(), now(), 1, 1, 0),
	 (2, '00002', now(), now(), 2, 1, 0),
	 (3, '00003', now(), now(), 3, 1, 0);

insert into cava
	values (1, 'CAVA 1', 1),
	 (2, 'CAVA 2', 3),
	 (3, 'CAVA 3', 1),
	 (4, 'CAVA 5', 2),
	 (5, 'CAVA 6', 2);

insert into compra_especie
	values (1, 1,1,1000),
	 (2, 1,1,1000),
	 (3, 1,1,1000),
	 (4, 1,1,1000),
	 (5, 1,1,1000);

insert into unidad
	values (1, 1, 1, 10, '00001', true),
	 (2, 2, 1, 10, '00002', true),
	 (3, 3, 1, 10,  '00003', true),
	 (4, 4, 1, 10,  '00004', true),
	 (5, 5, 1, 10,  '00005', true);

insert into venta_unidad
	values (1, 1,1),
	 (2, 4,1),
	 (3, 2,1),
	 (4, 3,1),
	 (5, 5,1);

insert into detalle_produccion
	values (1, 1,1),
	 (2, 1,2),
	 (3, 1,3),
	 (4, 1,4),
	 (5, 1,5);

insert into terminado
	values (1, 1, 1, now(), 'L-000012'),
	(2, 2, 1, now(), 'L-000012'),
	(3, 2, 1, now(), 'L-000012'),
	(4, 2, 1, now(), 'L-000012');

insert into venta_terminado
	values (1,1,1),
	(2,2,1),
	(3,3,1),
	(4,4,1),
	(5,4,1);
