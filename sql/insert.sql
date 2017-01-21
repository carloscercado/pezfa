insert into proveedor 
	values (1,'23923165','ALBEJA C.A', 'PTO LA CRUZ, CASA N1', '(293)-7564738',  'albeja@albeja.com'),
	values (2,'23923166','ANOCA C.A', 'PTO LA CRUZ, CASA N1', '(293)-7564738',  'albeja@albeja.com'),
	values (3,'23923167','UJULUM C.A', 'PTO LA CRUZ, CASA N1', '(293)-7564738',  'albeja@albeja.com'),
	values (4,'23923168','CHIPICHIPI C.A', 'PTO LA CRUZ, CASA N1', '(293)-7564738',  'albeja@albeja.com'),
	values (5,'23923169','CARACOLITO C.A', 'PTO LA CRUZ, CASA N1', '(293)-7564738',  'albeja@albeja.com');

insert into cliente 
	values (1,'23923165','PEDRO', 'PTO LA CRUZ, CASA N1', '(293)-7564738',  'albeja@albeja.com'),
	values (2,'23923166','ANOTONIA', 'PTO LA CRUZ, CASA N1', '(293)-7564738',  'albeja@albeja.com'),
	values (3,'23923167','CARLOS ', 'PTO LA CRUZ, CASA N1', '(293)-7564738',  'albeja@albeja.com'),
	values (4,'23923168','PETONIA', 'GUIRIA, CASA N1', '(293)-7564738',  'albeja@albeja.com'),
	values (5,'23923169','ARANZA', 'CUMANA, CASA N1', '(293)-7564738',  'albeja@albeja.com');

insert into empleado 
	values (1,'23923165','PEDRO','ALBERTO', 'LOPEZ', 'ALCANTARA',now(), 'M', 'ALMACENISTA'),
	values (2,'23923162','RICARDO','ALBERTO', 'LOPEZ', 'ALCANTARA',now(), 'M', 'ALMACENISTA'),
	values (3,'23923161','MARIA','ALBERTO', 'LOPEZ', 'ALCANTARA',now(), 'M', 'ALMACENISTA'),
	values (4,'23923165','PETONIA','ALBERTO', 'LOPEZ', 'ALCANTARA',now(), 'M', 'ALMACENISTA'),
	values (5,'23923169','CHICHA','ALBERTO', 'LOPEZ', 'ALCANTARA',now(), 'M', 'ALMACENISTA');

insert into almacen 
	values (1, 'PEÑON', 'AV SANTA ROSA, CUMANA', '(293)-7564738'),
	values (2, 'HUMBOLT 04', 'AV SANTA ROSA, CUMANA', '(293)-7564738'),
	values (3, 'CENTRAL, PEZFA', 'AV SANTA ROSA, CUMANA', '(293)-7564738');

insert into producto
	values (1, '000001', 'LATA CHIPICHIPI', 0, 100,10, 'DEL MAR', 0),	
	values (2, '000002', 'LATA ATUM', 0, 100,10, 'DEL MAR', 0),
	values (3, '000003', 'LATA PEPITONAS', 0, 100,10, 'DEL MAR', 0),
	values (4, '000005', 'LATA JUREL', 0, 100,10, 'DEL MAR', 0),
	values (5, '000006', 'LATA TIBURON', 0, 100,10, 'DEL MAR', 0);

insert into produccion
	values(1, 'PRODUCCION DEL MES', now(), 0),
	values(2, 'PRODUCCION DE ATUM', now(), 0);

insert into especie
	values (1, '000001', 'CHIPICHIPI', 0, 100,10, 'DEL MAR', 0),
	values (2, '000002', 'ATUM', 0, 100,10, 'DEL MAR', 0),
	values (3, '000003', 'PEPITONAS', 0, 100,10, 'DEL MAR', 0),
	values (4, '000005', 'JUREL', 0, 100,10, 'DEL MAR', 0),
	values (5, '000006', 'TIBURON', 0, 100,10, 'DEL MAR', 0);

insert into usuario
	values (1, 'admin', 'admin', 'ADMINISTRADOR', 1);

insert into compra
	values (1, '00001', now(), now(), 1, 1, 0),
	values (2, '00002', now(), now(), 2, 1, 0),
	values (3, '00003', now(), now(), 3, 1, 0);

insert into venta 
	values (1, '00001', now(), now(), 1, 1, 0),
	values (2, '00002', now(), now(), 2, 1, 0),
	values (3, '00003', now(), now(), 3, 1, 0);

insert into cava
	values (1, 'CAVA 1', 1),
	values (2, 'CAVA 2', 3),
	values (3, 'CAVA 3', 1),
	Values (4, 'CAVA 5', 2),
	values (5, 'CAVA 6', 2);

insert into compra_especie
	values (1, 1,1,1000),
	values (2, 1,1,1000),
	values (3, 1,1,1000),
	values (4, 1,1,1000),
	values (5, 1,1,1000);

insert into unidad
	values (1, 1, 1, 10, 900, '00001', true),
	values (2, 2, 1, 10, 900, '00002', true),
	values (3, 3, 1, 10, 900, '00003', true),
	values (4, 4, 1, 10, 900, '00004', true),
	values (5, 5, 1, 10, 900, '00005', true);

insert into venta_unidad
	values (1, 1,1),
	values (2, 4,1),
	values (3, 2,1),
	values (4, 3,1),
	values (5, 5,1);

insert into detalle_produccion
	values (1, 1,1),
	values (2, 1,2),
	values (3, 1,3),
	values (4, 1,4),
	values (5, 1,5);

insert into terminado
	values(1, 1, 1, now(), 'L-000012'),
	values(2, 2, 1, now(), 'L-000012'),
	values(3, 3, 1, now(), 'L-000012'),
	values(4, 4, 1, now(), 'L-000012');

insert into venta_terminado
	values(1,1,1),
	values(2,2,1),
	values(3,3,1),
	values(4,4,1),
	values(5,4,1);
