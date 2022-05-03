SELECT c.id, m.nombre_mascota, v.nombre_veterinario, s.nombre_servicio, c.total_pago, c.fecha
 FROM cita AS c
INNER JOIN mascota AS m ON m.id = c.codigo_mascota
INNER JOIN veterinario AS v ON v.id = c.id_veterinario
INNER JOIN servicio AS s ON s.id = c.codigo_servicio
WHERE c.id = :id;