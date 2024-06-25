create database NODO_QUITO
drop database NODO_QUITO
use NODO_QUITO

--Crear la tabla Taller_Quito
drop table Taller_Quito
CREATE TABLE Taller_Quito (
    id_Taller VARCHAR(10),--campo de fragmentaci�n
    id_Director VARCHAR(10),
    Nombre_Taller VARCHAR(50),
    Localidad_Taller VARCHAR(50) , 
	PRIMARY KEY (id_Taller)
);

-- Agregar clave for�nea en Taller_Quito referenciando Empleado_Quito para id_Director
ALTER TABLE Taller_Quito
ADD CONSTRAINT fk_taller_director_Quito
FOREIGN KEY (id_Director,id_Taller)
REFERENCES Empleado_Quito(id_Empleado,id_Taller);
--

-- Insertar datos en la tabla Taller_Quito seleccionando de NODO_Quito...TALLER
INSERT INTO Taller_Quito
SELECT *
FROM BD_CENTRALIZADA...TALLER
WHERE  id_taller = 2

DELETE FROM Taller_Quito;


-- Verificar los datos insertados
SELECT * FROM Taller_Quito;
drop table Empleado_Quito
-------------------------------------------------------------------------------------------------
-- Crear la tabla Empleado_Quito
CREATE TABLE Empleado_Quito (
    id_Empleado VARCHAR(10) ,
    id_Taller VARCHAR(10),--campo de Fragmentacion
    Nombre_empleado VARCHAR(50),
    Cedula_Empleado VARCHAR(10),
    Fecha_comienzo DATE,
    Salario DECIMAL(10, 2) -- Hacer varchar
	PRIMARY KEY  (id_Empleado,id_Taller)
);



Insert into Empleado_Quito values (3,2,'Erik','1753535353',GETDATE(),200.2)
-- Agregar clave for�nea en Empleado_Quito referenciando Taller_Quito
ALTER TABLE Empleado_Quito
ADD CONSTRAINT fk_empleado_taller_Quito
FOREIGN KEY (Id_Taller)
REFERENCES Taller_Quito(id_Taller);

--SP_HELP Empleado_Quito
--DELETE FROM Empleado_Quito;
drop table Empleado_Quito

-- Insertar datos en la tabla Empleado_Quito uniendo las tablas NODO_Quito...EMPLEADO y Taller_Quito
INSERT INTO Empleado_Quito (id_Empleado, id_Taller, Nombre_empleado, Cedula_Empleado, Fecha_comienzo, Salario)
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
    Taller_Quito AS T ON E.id_Taller = T.id_Taller;

SELECT * FROM Empleado_Quito;

------------------------------------------------------------------------------------------------------------
-- Crear la tabla Telefono_Quito
CREATE TABLE Telefono_Quito (
    id_Empleado VARCHAR(10),
    numero_Telefonico VARCHAR(20),
    id_Taller VARCHAR(10)--campo de Fragmentacion
    PRIMARY KEY (id_Empleado, numero_Telefonico ,id_Taller)
);

-- Agregar clave for�nea en Telefono_Quito referenciando Empleado_Quito
ALTER TABLE Telefono_Quito
ADD CONSTRAINT fk_telefono_empleado_Quito
FOREIGN KEY (id_Empleado,id_Taller)
REFERENCES Empleado_Quito(id_Empleado,id_Taller);
--
sp_help Telefono_Quito
-- Insertar datos en la tabla Telefono_Quito uniendo las tablas Empleado_Quito y NODO_Quito...TELEFONO
INSERT INTO Telefono_Quito (id_Empleado, numero_Telefonico,id_Taller)
SELECT
    EG.id_Empleado,
    T.numero_Telefonico,
	EG.id_Taller
FROM
    Empleado_Quito AS EG
JOIN
    BD_CENTRALIZADA...TELEFONO AS T ON EG.id_Empleado = T.id_empleado;

-- Verificar los datos insertados
SELECT * FROM Telefono_Quito;
DELETE FROM Telefono_Quito;

----------------------------------------------------------------------------------------

-- Crear la tabla Cliente_Todo_Quito
CREATE TABLE Cliente_Todo_Quito (
    cedula_cliente VARCHAR(20),
    id_taller VARCHAR(10) ,--campo de fragmentacion
    nombre_cliente VARCHAR(255),---
    apellido_cliente VARCHAR(255),
    ciudad_cliente VARCHAR(255)
	PRIMARY KEY (nombre_cliente, apellido_cliente, id_taller)
);

Insert into Cliente_Todo_Quito values ('1752333367',2,'Erik','Gaibor','New York')

drop table  Cliente_Todo_Quito

-- Agregar clave for�nea en Cliente_Todo_Quito referenciando Taller_Quito
ALTER TABLE Cliente_Todo_Quito
ADD CONSTRAINT fk_cliente_taller_Quito
FOREIGN KEY (id_taller)
REFERENCES Taller_Quito(id_Taller);
--

-- Insertar datos en la tabla Cliente_Todo_Quito uniendo las tablas NODO_Quito...CLIENTE y Taller_Quito
INSERT INTO Cliente_Todo_Quito (cedula_cliente, id_taller, nombre_cliente, apellido_cliente, ciudad_cliente)
SELECT
    C.cedula_cliente,
    T.id_taller,
    C.nombre_cliente,
    C.apellido_cliente,
    C.ciudad_cliente
FROM
    BD_CENTRALIZADA...CLIENTE AS C
JOIN
    Taller_Quito AS T ON C.id_taller = T.id_Taller;


-- Verificar los datos insertados
SELECT * FROM Cliente_Todo_Quito;
DELETE FROM Cliente_Todo_Quito;
SELECT * FROM Vehiculo_Matricula;
-----------------------------------------------------------------------------------------------------------------------

-- Crear la tabla Vehiculo_Todo_Quito
CREATE TABLE Vehiculo_Todo_Quito (
    num_matricula VARCHAR(20),
    id_taller VARCHAR(10),--campo de fragmentacion
	nombre_cliente VARCHAR(255),
    apellido_cliente VARCHAR(255),
    fecha_compra DATE,
    marca VARCHAR(255),
    modelo VARCHAR(255),
    anio VARCHAR(10)
	PRIMARY KEY (num_matricula, id_taller)
);
drop table Vehiculo_Todo_Quito
-- Agregar clave for�nea en Vehiculo_Todo_Quito referenciando Taller_Quito
ALTER TABLE Vehiculo_Todo_Quito
ADD CONSTRAINT fk_vehiculo_taller_Quito
FOREIGN KEY (nombre_cliente,apellido_cliente, id_taller)
REFERENCES Cliente_Todo_Quito(nombre_cliente,apellido_cliente, id_taller)
--

ALTER TABLE Vehiculo_Todo_Quito
ADD CONSTRAINT fk_vehiculo_matricula
FOREIGN KEY (num_matricula)
REFERENCES Vehiculo_Matricula(num_matricula)

-- Insertar datos en la tabla Vehiculo_Todo_Quito uniendo las tablas NODO_Quito...VEHICULO y Taller_Quito
INSERT INTO Vehiculo_Todo_Quito (num_matricula, id_taller, nombre_cliente, apellido_cliente, fecha_compra,marca, modelo, anio )
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
    Taller_Quito AS TG ON V.id_taller = TG.id_Taller;

-- Verificar los datos insertados
SELECT * FROM  Vehiculo_Todo_Quito;
DELETE FROM Vehiculo_Todo_Quito;
------------------------------------------------------------------------------------------------------------
---------CONTINUARA:
-- Crear la tabla Articulo_Quito
CREATE TABLE Articulo_Quito (
    id_articulo VARCHAR(10), -- INT IDENTITY(1, 1)
    id_taller VARCHAR(10), --campo fragmentaci�n
    nombre_articulo VARCHAR(255),
    tipo_articulo VARCHAR(255),
    descripcion_articulo TEXT,
    cantidad_articulo INT
	PRIMARY KEY (id_articulo, id_taller)
);

Insert into Articulo_Quito values (3 ,2,'Aceite Premium','Lubricante','Aceite premium para carros',10)

-- Agregar clave for�nea en Articulo_Quito referenciando Taller_Quito
ALTER TABLE Articulo_Quito
ADD CONSTRAINT fk_articulo_taller_Quito
FOREIGN KEY (id_taller)
REFERENCES Taller_Quito(id_Taller);


-- Insertar datos en la tabla Articulo_Quito uniendo las tablas NODO_Quito...ARTICULO y Taller_Quito
INSERT INTO Articulo_Quito (id_articulo, id_taller, nombre_articulo, tipo_articulo, descripcion_articulo, cantidad_articulo)
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
    Taller_Quito AS TG ON A.id_taller = TG.id_Taller;

-- Verificar los datos insertados
SELECT * FROM Articulo_Quito;

-- Crear la tabla Reparaci�n_Quito
CREATE TABLE Reparacion_Quito (
    num_matricula VARCHAR(20),
    id_reparacion VARCHAR(10),
    id_articulo VARCHAR(10),
    fecha_reparacion DATE,
    tipo_reparacion VARCHAR(255),
    observacion TEXT,
    precio DECIMAL(10, 2),
	id_taller VARCHAR(10) --campo fragmentaci�n
	PRIMARY KEY (id_reparacion, id_taller,num_matricula)
);
-- Agregar clave for�nea en Reparacion_Quito referenciando Vehiculo_Todo_Quito
ALTER TABLE Reparacion_Quito
ADD CONSTRAINT fk_reparacion_vehiculo_Quito
FOREIGN KEY (num_matricula,id_taller)
REFERENCES Vehiculo_Todo_Quito(num_matricula,id_taller);
--FOREIGN ARTICULO
ALTER TABLE Reparacion_Quito
ADD CONSTRAINT fk_articulo_vehiculo_Quito
FOREIGN KEY (id_articulo,id_taller)
REFERENCES Articulo_Quito(id_articulo,id_taller);

drop table Reparacion_Quito

-- Insertar datos en la tabla Reparaci�n_Quito uniendo las tablas NODO_Quito...REPARACION y Vehiculo_Todo_Quito
INSERT INTO Reparacion_Quito (num_matricula, id_reparacion, id_articulo, fecha_reparacion, tipo_reparacion, observacion, precio,id_taller)
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
    Vehiculo_Todo_Quito AS VTG ON R.num_matricula = VTG.num_matricula;

-- Verificar los datos insertados
SELECT * FROM Reparacion_Quito;
-------------------------------------------------------------------------------------------
