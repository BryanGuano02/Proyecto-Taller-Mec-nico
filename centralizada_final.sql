-- Creación de la tabla TALLER
CREATE TABLE TALLER (
    id_taller SERIAL PRIMARY KEY,
    id_director INT NOT NULL,
    nombre_taller VARCHAR(255) NOT NULL,
    localidad_taller VARCHAR(255) NOT NULL
   
);

----
 ALTER TABLE TALLER ADD FOREIGN KEY(id_director) REFERENCES EMPLEADO(id_empleado) -- Clave foránea al director

------------------------


-- Creación de la tabla EMPLEADO
CREATE TABLE EMPLEADO (
    id_empleado SERIAL PRIMARY KEY,
    id_taller INT NOT NULL,
    nombre_empleado VARCHAR(255) NOT NULL,
    cedula_empleado VARCHAR(255) NOT NULL UNIQUE,
    fecha_comienzo DATE NOT NULL,
    salario DECIMAL(10,2) NOT NULL
);


-- Agregar claves foráneas a EMPLEADO
ALTER TABLE EMPLEADO ADD FOREIGN KEY (id_taller) REFERENCES TALLER(id_taller);


-- Creación de la tabla TELEFONO
CREATE TABLE TELEFONO (
    id_telefono SERIAL PRIMARY KEY, -- Clave primaria
    id_empleado INT NOT NULL, -- Clave foránea al empleado
    numero_telefonico VARCHAR(20) NOT NULL
);


-- Agregar claves foráneas a TELEFONO
ALTER TABLE TELEFONO ADD FOREIGN KEY (id_empleado) REFERENCES EMPLEADO(id_empleado);



-- Resto de las tablas (CLIENTE, VEHICULO, ARTICULO, REPARACION) permanecen sin cambios
CREATE TABLE CLIENTE (
   cedula_cliente VARCHAR(255) PRIMARY KEY,
   id_taller INT NOT NULL,
   nombre_cliente VARCHAR(255) NOT NULL,
   apellido_cliente VARCHAR(255),
   ciudad_cliente VARCHAR(255)
);


-- Agregar claves foráneas a CLIENTE
ALTER TABLE CLIENTE ADD FOREIGN KEY  (id_taller) REFERENCES TALLER(id_taller);



CREATE TABLE VEHICULO (
  num_matricula SERIAL PRIMARY KEY, 
  id_taller INT NOT NULL, 
  cedula_cliente VARCHAR (255) NOT NULL, 
  fecha_compra DATE, 
  marca TEXT, 
  modelo TEXT, 
  ano INT
);


-- Agregar claves foráneas a VEHICULO
ALTER TABLE VEHICULO ADD FOREIGN KEY (id_taller) REFERENCES TALLER(id_taller);
ALTER TABLE VEHICULO ADD FOREIGN KEY (cedula_cliente) REFERENCES CLIENTE(cedula_cliente);


CREATE TABLE ARTICULO (
    id_articulo SERIAL PRIMARY KEY, 
    id_taller INT NOT NULL, 
    nombre_articulo TEXT, 
    tipo_articulo TEXT, 
    descripcion_articulo TEXT, 
    cantidad_articulo INT
);

----
ALTER TABLE ARTICULO ADD FOREIGN KEY (id_taller) REFERENCES TALLER(id_taller);

CREATE TABLE REPARACION (
    id_reparacion SERIAL PRIMARY KEY ,  
	num_matricula INT NOT NULL,  
    id_articulo INT,  
    fecha_reparacion DATE ,  
    tipo_reparacion TEXT ,  
    observacion TEXT , 
    precio DECIMAL (10 ,2 )
);

--
-- Agregar claves foráneas a REPARACION
ALTER TABLE REPARACION ADD FOREIGN KEY (num_matricula) REFERENCES VEHICULO(num_matricula);
ALTER TABLE REPARACION ADD FOREIGN KEY (id_articulo) REFERENCES ARTICULO(id_articulo);


----------------------------------
---DATOS

-- Inserciones para la tabla TALLER
INSERT INTO TALLER (id_director, nombre_taller, localidad_taller) VALUES
(1, 'Taller Quito', 'Quito'),
(2, 'Taller Guayaquil', 'Guayaquil');

-- Inserciones para la tabla EMPLEADO
INSERT INTO EMPLEADO (id_taller, nombre_empleado, cedula_empleado, fecha_comienzo, salario) VALUES
(1, 'Juan Perez', '123456789', '2022-01-01', 3000.00),
(1, 'Maria Rodriguez', '987654321', '2022-02-15', 2800.00),
(2, 'Pedro Gomez', '111222333', '2021-12-10', 3200.00),
(2, 'Ana Martinez', '444555666', '2023-03-20', 3000.00);

-- Inserciones para la tabla TELEFONO
INSERT INTO TELEFONO (id_empleado, numero_telefonico) VALUES
(1, '555-1234'),
(2, '555-5678'),
(3, '555-9876'),
(4, '555-4321');

-- Inserciones para la tabla CLIENTE
INSERT INTO CLIENTE (cedula_cliente, id_taller, nombre_cliente, apellido_cliente, ciudad_cliente) VALUES
('111111111', 1, 'Cliente1', 'Apellido1', 'Quito'),
('222222222', 1, 'Cliente2', 'Apellido2', 'Quito'),
('333333333', 2, 'Cliente3', 'Apellido3', 'Guayaquil'),
('444444444', 2, 'Cliente4', 'Apellido4', 'Guayaquil');

-- Inserciones para la tabla VEHICULO
INSERT INTO VEHICULO (id_taller, cedula_cliente, fecha_compra, marca, modelo, ano) VALUES
(1, '111111111', '2020-05-15', 'Toyota', 'Corolla', 2018),
(1, '222222222', '2019-08-20', 'Honda', 'Civic', 2020),
(2, '333333333', '2021-03-10', 'Ford', 'Focus', 2019),
(2, '444444444', '2022-01-05', 'Chevrolet', 'Malibu', 2022);

-- Inserciones para la tabla ARTICULO
INSERT INTO ARTICULO (id_taller, nombre_articulo, tipo_articulo, descripcion_articulo, cantidad_articulo) VALUES
(1, 'Llanta', 'Repuesto', 'Llanta para automóvil', 10),
(1, 'Aceite de motor', 'Suministro', 'Aceite sintético 5W-30', 20),
(2, 'Filtro de aire', 'Repuesto', 'Filtro de aire para automóvil', 15),
(2, 'Batería', 'Repuesto', 'Batería de 12V', 8);

-- Inserciones para la tabla REPARACION
INSERT INTO REPARACION (num_matricula, id_articulo, fecha_reparacion, tipo_reparacion, observacion, precio) VALUES
(1, 1, '2022-02-01', 'Cambio de llanta', 'Llanta delantera derecha reventada', 50.00),
(2, 2, '2022-03-15', 'Cambio de aceite', 'Cambio de aceite y filtro', 80.00),
(3, 3, '2023-01-10', 'Reparación de filtro de aire', 'Filtro de aire obstruido', 30.00),
(4, 4, '2023-04-05', 'Reemplazo de batería', 'Batería agotada, se requiere reemplazo', 100.00);
