UPDATE cita
SET
codigo_mascota = :codigoMascota,
id_veterinario = :idVeterinario,
codigo_servicio = :codigoServicio,
total_pago = :totalPago,
fecha = :fecha
WHERE id = :id;