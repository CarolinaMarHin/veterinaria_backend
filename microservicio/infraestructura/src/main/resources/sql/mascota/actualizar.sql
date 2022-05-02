update mascota
set codigo_mascota = :codigoMascota,
 nombre_mascota = :nombreMascota,
 raza = :raza,
 fecha_nacimiento_mascota = :fechaNacimientoMascota,
 peso = :peso
where id = :id