-- Crear la base de datos
CREATE DATABASE inventario_db
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

CREATE TABLE inventario (
    sku VARCHAR(50) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    cantidad INT NOT NULL CHECK (cantidad >= 0),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE empleados (
    id_empleado SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    puesto VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE polizas (
    id_poliza SERIAL PRIMARY KEY,
    empleado_genero INT NOT NULL,
    sku VARCHAR(50) NOT NULL,
    cantidad INT NOT NULL CHECK (Cantidad > 0),
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (empleado_genero) REFERENCES empleados(id_empleado) ON DELETE CASCADE,
    FOREIGN KEY (sku) REFERENCES inventario(sku) ON DELETE CASCADE
);

CREATE INDEX idx_empleado_genero ON polizas(empleado_genero);
CREATE INDEX idx_sku ON polizas(sku);
CREATE INDEX idx_inventario_created_at ON inventario(created_at);
CREATE INDEX idx_empleado_created_at ON empleados(created_at);
CREATE INDEX idx_polizas_created_at ON polizas(created_at);



INSERT INTO empleados (nombre, apellido, puesto) VALUES
('Juan', 'Pérez', 'Almacén'),
('María', 'López', 'Vendedora'),
('Carlos', 'Gómez', 'Supervisor'),
('Ana', 'Martínez', 'Gerente'),
('Pedro', 'Rodríguez', 'Cajero'),
('Laura', 'Fernández', 'Asistente'),
('Javier', 'Hernández', 'Operador'),
('Sofía', 'Gutiérrez', 'Recepcionista'),
('Andrés', 'Díaz', 'Bodeguero'),
('Daniela', 'Castro', 'Supervisora');


INSERT INTO inventario (sku, nombre, cantidad) VALUES
('SKU001', 'Laptop HP', 50),
('SKU002', 'Monitor Samsung', 30),
('SKU003', 'Teclado Mecánico', 100),
('SKU004', 'Mouse Inalámbrico', 75),
('SKU005', 'Impresora Epson', 20),
('SKU006', 'Memoria USB 32GB', 200),
('SKU007', 'Silla Ergonómica', 15),
('SKU008', 'Escritorio de Oficina', 10),
('SKU009', 'Audífonos Bluetooth', 40),
('SKU010', 'Router WiFi', 25);


INSERT INTO polizas (empleado_genero, sku, cantidad) VALUES
(1, 'SKU001', 5),
(2, 'SKU002', 3),
(3, 'SKU003', 10),
(4, 'SKU004', 7),
(5, 'SKU005', 2),
(6, 'SKU006', 15),
(7, 'SKU007', 1),
(8, 'SKU008', 2),
(9, 'SKU009', 8),
(10, 'SKU010', 4);

