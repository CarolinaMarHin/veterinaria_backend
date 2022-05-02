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

CREATE TABLE servicio (
    id int(100) auto_increment,
    nombre_servicio VARCHAR(50),
    precio_servicio int,
    PRIMARY KEY (id)
);

CREATE TABLE cita (
    id int(100) auto_increment,
    codigo_mascota int(100),
    id_veterinario int(100),
    codigo_servicio int(100),
    total_pago int,
    fecha DATE,
    PRIMARY KEY (id),
    CONSTRAINT FK_Id_Codigo_veterinario FOREIGN KEY (id_veterinario) REFERENCES veterinario(id),
    CONSTRAINT FK_Id_Codigo_servicio FOREIGN KEY (codigo_servicio) REFERENCES servicio(id),
    CONSTRAINT FK_Codigo_mascota FOREIGN KEY (codigo_mascota) REFERENCES mascota(id)
);