-- Ejecutar conectado a la base bd_fauna_silvestre:
-- psql -U postgres -d bd_fauna_silvestre -f db/avistamientos.sql
CREATE TABLE IF NOT EXISTS avistamientos (
    id BIGSERIAL PRIMARY KEY,
    especie VARCHAR(100) NOT NULL,
    ubicacion_geografica VARCHAR(255) NOT NULL,
    fecha_avistamiento DATE NOT NULL DEFAULT CURRENT_DATE,
    observaciones TEXT,
    fecha_registro TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
);
