create database NODO_GUAYAQUIL
drop database NODO_GUAYAQUIL
use NODO_GUAYAQUIL

--Crear la tabla Taller_Guayaquil
drop table Taller_Guayaquil
CREATE TABLE Taller_Guayaquil (
    id_Taller VARCHAR(10),--campo de fragmentación
    id_Director VARCHAR(10),
    Nombre_Taller VARCHAR(50),
    Localidad_Taller VARCHAR(50) , 
	PRIMARY KEY (id_Taller)
);

-- Agregar clave foránea en Taller_Guayaquil referenciando Empleado_Guayaquil para id_Director
ALTER TABLE Taller_Guayaquil
ADD CONSTRAINT fk_taller_director_guayaquil
FOREIGN KEY (id_Director,id_Taller)
REFERENCES Empleado_Guayaquil(id_Empleado,id_Taller);
--

-- Insertar datos en la tabla Taller_Guayaquil seleccionando de NODO_GUAYAQUIL...TALLER
INSERT INTO Taller_Guayaquil
SELECT *
FROM BD_CENTRALIZADA...TALLER
WHERE  id_taller = 2

DELETE FROM Taller_Guayaquil;


-- Verificar los datos insertados
SELECT * FROM Taller_Guayaquil;
drop table Empleado_Guayaquil
-------------------------------------------------------------------------------------------------
-- Crear la tabla Empleado_Guayaquil
CREATE TABLE Empleado_Guayaquil (
    id_Empleado VARCHAR(10) ,
    id_Taller VARCHAR(10),--campo de Fragmentacion
    Nombre_empleado VARCHAR(50),
    Cedula_Empleado VARCHAR(10),
    Fecha_comienzo VARCHAR(10),
    Salario  VARCHAR(10) -- Hacer varchar
	PRIMARY KEY  (id_Empleado,id_Taller)
);

select * from Telefono_Guayaquil

Insert into Empleado_Guayaquil values (3,2,'Erik','1753535353',GETDATE(),200.2)
-- Agregar clave foránea en Empleado_Guayaquil referenciando Taller_Guayaquil
ALTER TABLE Empleado_Guayaquil
ADD CONSTRAINT fk_empleado_taller_guayaquil
FOREIGN KEY (Id_Taller)
REFERENCES Taller_Guayaquil(id_Taller);

--SP_HELP Empleado_Guayaquil
--DELETE FROM Empleado_Guayaquil;
drop table Empleado_Guayaquil

-- Insertar datos en la tabla Empleado_Guayaquil uniendo las tablas NODO_GUAYAQUIL...EMPLEADO y Taller_Guayaquil
INSERT INTO Empleado_Guayaquil (id_Empleado, id_Taller, Nombre_empleado, Cedula_Empleado, Fecha_comienzo, Salario)
SELECT
    E.id_Empleado,
    T.id_Taller,
    E.nombre_empleado,
    E.cedula_empleado,
    E.fecha_comienzo,
    E.salario
FROM
    BD_CENTRALIZADA...Empleado AS E
JOIN
    Taller_Guayaquil AS T ON E.id_Taller = T.id_Taller;

SELECT * FROM Empleado_Guayaquil;

------------------------------------------------------------------------------------------------------------
-- Crear la tabla Telefono_Guayaquil
CREATE TABLE Telefono_Guayaquil (
    id_Empleado VARCHAR(10),
    numero_Telefonico VARCHAR(20),
    id_Taller VARCHAR(10)--campo de Fragmentacion
    PRIMARY KEY (id_Empleado, numero_Telefonico ,id_Taller)
);

ALTER TABLE Telefono_Guayaquil
DROP CONSTRAINT fk_telefono_empleado_guayaquil;

ALTER TABLE Telefono_Guayaquil
ADD CONSTRAINT fk_telefono_empleado_guayaquil
FOREIGN KEY (id_Empleado, id_Taller)
REFERENCES Empleado_Guayaquil(id_Empleado, id_Taller)
ON DELETE CASCADE;

--
sp_help Telefono_Guayaquil
-- Insertar datos en la tabla Telefono_Guayaquil uniendo las tablas Empleado_Guayaquil y NODO_GUAYAQUIL...TELEFONO
INSERT INTO Telefono_Guayaquil (id_Empleado, numero_Telefonico,id_Taller)
SELECT
    EG.id_Empleado,
    T.numero_Telefonico,
	EG.id_Taller
FROM
    Empleado_Guayaquil AS EG
JOIN
    BD_CENTRALIZADA...TELEFONO AS T ON EG.id_Empleado = T.id_empleado;

-- Verificar los datos insertados
SELECT * FROM Telefono_Guayaquil;
DELETE FROM Telefono_Guayaquil;

----------------------------------------------------------------------------------------

-- Crear la tabla Cliente_Todo_Guayaquil
CREATE TABLE Cliente_Todo_Guayaquil (
    cedula_cliente VARCHAR(20),
    id_taller VARCHAR(10) ,--campo de fragmentacion
    nombre_cliente VARCHAR(255),---
    apellido_cliente VARCHAR(255),
    ciudad_cliente VARCHAR(255)
	PRIMARY KEY (nombre_cliente, apellido_cliente, id_taller)
);

Insert into Cliente_Todo_Guayaquil values ('1752333367',2,'Erik','Gaibor','New York')

drop table  Cliente_Todo_Guayaquil

-- Agregar clave foránea en Cliente_Todo_Guayaquil referenciando Taller_Guayaquil
ALTER TABLE Cliente_Todo_Guayaquil
ADD CONSTRAINT fk_cliente_taller_guayaquil
FOREIGN KEY (id_taller)
REFERENCES Taller_Guayaquil(id_Taller);
--

-- Insertar datos en la tabla Cliente_Todo_Guayaquil uniendo las tablas NODO_GUAYAQUIL...CLIENTE y Taller_Guayaquil
INSERT INTO Cliente_Todo_Guayaquil (cedula_cliente, id_taller, nombre_cliente, apellido_cliente, ciudad_cliente)
SELECT
    C.cedula_cliente,
    T.id_taller,
    C.nombre_cliente,
    C.apellido_cliente,
    C.ciudad_cliente
FROM
    BD_CENTRALIZADA...CLIENTE AS C
JOIN
    Taller_Guayaquil AS T ON C.id_taller = T.id_Taller;


-- Verificar los datos insertados
SELECT * FROM Cliente_Todo_Guayaquil;
DELETE FROM Cliente_Todo_Guayaquil;
SELECT * FROM Vehiculo_Matricula;
-----------------------------------------------------------------------------------------------------------------------

-- Crear la tabla Vehiculo_Todo_Guayaquil
CREATE TABLE Vehiculo_Todo_Guayaquil (
    num_matricula VARCHAR(20),
    id_taller VARCHAR(10),--campo de fragmentacion
	nombre_cliente VARCHAR(255),
    apellido_cliente VARCHAR(255),
    fecha_compra VARCHAR(10),
    marca VARCHAR(255),
    modelo VARCHAR(255),
    anio VARCHAR(10)
	PRIMARY KEY (num_matricula, id_taller)
);
drop table Vehiculo_Todo_Guayaquil
-- Agregar clave foránea en Vehiculo_Todo_Guayaquil referenciando Taller_Guayaquil
ALTER TABLE Vehiculo_Todo_Guayaquil
ADD CONSTRAINT fk_vehiculo_taller_guayaquil
FOREIGN KEY (nombre_cliente,apellido_cliente, id_taller)
REFERENCES Cliente_Todo_Guayaquil(nombre_cliente,apellido_cliente, id_taller)
--

ALTER TABLE Vehiculo_Todo_Guayaquil
ADD CONSTRAINT fk_vehiculo_matricula
FOREIGN KEY (num_matricula)
REFERENCES Vehiculo_Matricula(num_matricula)


-- Insertar datos en la tabla Vehiculo_Todo_Guayaquil uniendo las tablas NODO_GUAYAQUIL...VEHICULO y Taller_Guayaquil
INSERT INTO Vehiculo_Todo_Guayaquil (num_matricula, id_taller, nombre_cliente, apellido_cliente, fecha_compra,marca, modelo, anio )
SELECT
    V.num_matricula,
    TG.id_taller,
	V.nombre_cliente, 
	V.apellido_cliente,
    V.fecha_compra,
    V.marca,
    V.modelo,
    V.anio
FROM
    BD_CENTRALIZADA...VEHICULO AS V
JOIN
    Taller_Guayaquil AS TG ON V.id_taller = TG.id_Taller;

-- Verificar los datos insertados
SELECT * FROM  Vehiculo_Todo_Guayaquil;
DELETE FROM Vehiculo_Todo_Guayaquil;
------------------------------------------------------------------------------------------------------------
---------CONTINUARA:
-- Crear la tabla Articulo_Guayaquil
CREATE TABLE Articulo_Guayaquil (
    id_articulo VARCHAR(10), -- INT IDENTITY(1, 1)
    id_taller VARCHAR(10), --campo fragmentación
    nombre_articulo VARCHAR(255),
    tipo_articulo VARCHAR(255),
    descripcion_articulo TEXT,
    cantidad_articulo INT
	PRIMARY KEY (id_articulo, id_taller)
);

Insert into Articulo_Guayaquil values (3 ,2,'Aceite Premium','Lubricante','Aceite premium para carros',10)

-- Agregar clave foránea en Articulo_Guayaquil referenciando Taller_Guayaquil
ALTER TABLE Articulo_Guayaquil
ADD CONSTRAINT fk_articulo_taller_guayaquil
FOREIGN KEY (id_taller)
REFERENCES Taller_Guayaquil(id_Taller);
select * from Articulo_Guayaquil

-- Insertar datos en la tabla Articulo_Guayaquil uniendo las tablas NODO_GUAYAQUIL...ARTICULO y Taller_Guayaquil
INSERT INTO Articulo_Guayaquil (id_articulo, id_taller, nombre_articulo, tipo_articulo, descripcion_articulo, cantidad_articulo)
SELECT
    A.id_articulo,
    TG.id_taller,
    A.nombre_articulo,
    A.tipo_articulo,
    A.descripcion_articulo,
    A.cantidad_articulo
FROM
    BD_CENTRALIZADA...ARTICULO AS A
JOIN
    Taller_Guayaquil AS TG ON A.id_taller = TG.id_Taller;

-- Verificar los datos insertados
SELECT * FROM Articulo_Guayaquil;

-- Crear la tabla Reparación_Guayaquil
drop table Reparacion_Guayaquil
CREATE TABLE Reparacion_Guayaquil (
    num_matricula VARCHAR(20),
    id_reparacion VARCHAR(10),
    id_articulo VARCHAR(10),
    fecha_reparacion VARCHAR(10),
    tipo_reparacion VARCHAR(255),
    observacion TEXT,
    precio VARCHAR(10),
	id_taller VARCHAR(10) --campo fragmentación
	PRIMARY KEY (id_reparacion, id_taller,num_matricula)
);

drop table Reparacion_Guayaquil
-- Agregar clave foránea en Reparacion_Guayaquil referenciando Vehiculo_Todo_Guayaquil
ALTER TABLE Reparacion_Guayaquil
ADD CONSTRAINT fk_reparacion_vehiculo_guayaquil
FOREIGN KEY (num_matricula,id_taller)
REFERENCES Vehiculo_Todo_Guayaquil(num_matricula,id_taller);
--FOREIGN ARTICULO
ALTER TABLE Reparacion_Guayaquil
ADD CONSTRAINT fk_articulo_vehiculo_guayaquil
FOREIGN KEY (id_articulo,id_taller)
REFERENCES Articulo_Guayaquil(id_articulo,id_taller);

drop table Reparacion_Guayaquil

-- Insertar datos en la tabla Reparación_Guayaquil uniendo las tablas NODO_GUAYAQUIL...REPARACION y Vehiculo_Todo_Guayaquil
INSERT INTO Reparacion_Guayaquil (num_matricula, id_reparacion, id_articulo, fecha_reparacion, tipo_reparacion, observacion, precio,id_taller)
SELECT
    VTG.num_matricula,
    R.id_reparacion,
    R.id_articulo,
    R.fecha_reparacion,
    R.tipo_reparacion,
    R.observacion,
    R.precio,
	VTG.id_taller
FROM
    BD_CENTRALIZADA...REPARACION AS R
JOIN
    Vehiculo_Todo_Guayaquil AS VTG ON R.num_matricula = VTG.num_matricula;

-- Verificar los datos insertados
SELECT * FROM Reparacion_Guayaquil;
-------------------------------------------------------------------------------------------
-- Creacion de vistaS 

alter table Cliente_Todo_Guayaquil
add constraint ck_taller
check (id_taller= '2')

sp_help Cliente_Todo_Guayaquil
drop constraint ck_ciudad

Create view Vista_Clientes
AS
select cedula_cliente , id_taller, nombre_cliente , apellido_cliente , ciudad_cliente 
FROM [DESKTOP-U6SHESU].[NODO_GUAYAQUIL].[dbo].[Cliente_Todo_Guayaquil]
UNION ALL
select cedula_cliente , id_taller, nombre_cliente , apellido_cliente , ciudad_cliente 
FROM [LAPTOP-DAOUI9J1].[NODO_QUITO1].[dbo].[Cliente_Todo_Quito]



select *from Vista_Clientes
sp_configure 'remote login timeout', 30;
GO
RECONFIGURE WITH OVERRIDE;
GO
--Insertar datos en tabla Kevin
set xact_abort on
begin distributed tran
insert into Vista_Clientes values ('222222', '1','kEVIN' , 'Naranjo', 'QUITO ')
commit tran



---- VUSta par avehiculo 


alter table Vehiculo_Todo_Guayaquil
add constraint ck_Vehiculo
check (id_taller='2')



CREATE VIEW Vista_Vehiculos AS
SELECT num_matricula, id_taller, nombre_cliente, apellido_cliente, fecha_compra, marca, modelo, anio
FROM [LAPTOP-DAOUI9J1].[NODO_QUITO1].[dbo].[Vehiculo_Todo_Quito]
UNION ALL 
select num_matricula, id_taller, nombre_cliente, apellido_cliente, fecha_compra, marca, modelo, anio
FROM [DESKTOP-U6SHESU].[NODO_GUAYAQUIL].[dbo].[Vehiculo_Todo_Guayaquil]

select * from Vehiculo_Matricula
insert into Vista_Vehiculos values ('PDH640', '2' ,'Dilan ','Naranjo', '17-02-09','peugeot', 'kia', '2020')
select * from Vista_Vehiculos
---- Vista empleado 


alter table Empleado_Guayaquil
add constraint ck_tallerE
check (id_taller='2')



ALTER TABLE Empleado_Quito
DROP CONSTRAINT ck_tallersa;


CREATE VIEW Vista_Empleados AS
SELECT id_Empleado, id_Taller, Nombre_empleado, Cedula_Empleado, Fecha_comienzo, Salario
FROM [LAPTOP-DAOUI9J1].[NODO_QUITO1].[dbo].[Empleado_Quito]
UNION ALL 
select  id_Empleado, id_Taller, Nombre_empleado, Cedula_Empleado, Fecha_comienzo, Salario
FROM [DESKTOP-U6SHESU].[NODO_GUAYAQUIL].[dbo].[Empleado_Guayaquil]

select * from Vehiculo_Matricula