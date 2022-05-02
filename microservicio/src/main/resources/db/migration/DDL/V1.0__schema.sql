create table usuario (
 id int(11) not null auto_increment,
 nombre varchar(100) not null,
 clave varchar(45) not null,
 fecha_creacion datetime null,
 primary key (id)
);


CREATE TABLE veterinario (
    id int(100) auto_increment,
    cedula_veterinario VARCHAR(20) NOT NULL,
    nombre_veterinario VARCHAR(50),
    PRIMARY KEY (id)
);

CREATE TABLE mascota (
    id int(100) auto_increment,
    nombre_mascota VARCHAR(50),
    codigo_mascota VARCHAR(20),
    raza VARCHAR(50),
    fecha_nacimiento_mascota DATE,
    peso INT,
    PRIMARY KEY (id)
);

CREATE TABLE agenda (
    id int(100) auto_increment,
    codigo_agenda VARCHAR(20) NOT NULL,
    fecha_agenda DATE,
    estado_agenda VARCHAR(20),
    PRIMARY KEY (id)
);


CREATE TABLE articulo (
    id int(100) auto_increment,
    codigo_articulo VARCHAR(20) NOT NULL,
    nombre_articulo VARCHAR(50),
    precio_articulo VARCHAR(20),
    PRIMARY KEY (id)
);

CREATE TABLE factura (
    id int(100) auto_increment,
    codigo_factura VARCHAR(20) NOT NULL,
    total_factura INT,
    fecha_compra DATE,
    codigo_mascota int(100),
    codigo_servicio Int (100),
    PRIMARY KEY (id),
    CONSTRAINT FK_Id_Codigo_mascota FOREIGN KEY (codigo_mascota) REFERENCES mascota(id)
);

CREATE TABLE servicio (
    id int(100) auto_increment,
    nombre_servicio VARCHAR(50),
    precio_servicio int(100),
    codigo_factura int (100),
    codigo_articulo int(100),
    PRIMARY KEY (id),
    CONSTRAINT FK_Id_Codigo_factura FOREIGN KEY (codigo_factura) REFERENCES factura(id),
    CONSTRAINT FK_Id_Codigo_articulo FOREIGN KEY (codigo_articulo) REFERENCES articulo(id)
);

CREATE TABLE cita (
    id int(100) auto_increment,
    codigo_agenda int(100),
    codigo_mascota int(100),
    id_veterinario int(100),
    codigo_servicio int(100),
    PRIMARY KEY (id),
    CONSTRAINT FK_Id_Codigo_agenda FOREIGN KEY (codigo_agenda) REFERENCES agenda(id),
    CONSTRAINT FK_Id_Codigo_veterinario FOREIGN KEY (id_veterinario) REFERENCES veterinario(id),
    CONSTRAINT FK_Id_Codigo_servicio FOREIGN KEY (codigo_servicio) REFERENCES servicio(id),
    CONSTRAINT FK_Codigo_mascota FOREIGN KEY (codigo_mascota) REFERENCES mascota(id)
);