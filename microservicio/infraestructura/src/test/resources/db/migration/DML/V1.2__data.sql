insert into usuario(id, nombre,clave,fecha_creacion) values(1,'test','1234',now());
insert into veterinario(id, cedula_veterinario, nombre_veterinario) values(6,'1234','Carolina');
insert into mascota(id, codigo_mascota, nombre_mascota, raza, fecha_nacimiento_mascota, peso) values (1,'1234','Titan', 'Criollo', now(), 20);
insert into mascota(id, codigo_mascota, nombre_mascota, raza, fecha_nacimiento_mascota, peso) values (2,'4567','Guardian', 'Criollo', now(), 20);
insert into servicio(id, nombre_servicio, precio_servicio) values(1,'Servicio banio', 50000);
insert into cita(id, codigo_mascota, id_veterinario, codigo_servicio, total_pago, fecha) values(1, 2, 1, 1, 130000, now());