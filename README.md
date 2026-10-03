# Registro de Avistamientos de Fauna Silvestre

API para registrar y consultar avistamientos de animales en reservas naturales.

## Tecnologías

Java 21, Spring Boot 4, Spring Data JPA, PostgreSQL.

## Configuración

1. Crear la base de datos:
   ```sql
   CREATE DATABASE bd_fauna_silvestre;
   ```
2. Crear la tabla (conectado a `bd_fauna_silvestre`):
   ```bash
   psql -U postgres -d bd_fauna_silvestre -f db/avistamientos.sql
   ```
3. Crear `src/main/resources/application-dev.properties` (no se sube al repo):
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/bd_fauna_silvestre
   spring.datasource.username=postgres
   spring.datasource.password=TU_PASSWORD
   spring.jpa.hibernate.ddl-auto=update
   ```

## Ejecutar

```bash
./mvnw spring-boot:run
```

## Endpoints

| Método | Ruta | Descripción |
|---|---|---|
| POST | `/avistamientos` | Registra un avistamiento |

Ejemplo de cuerpo:

```json
{
  "especie": "Puma concolor",
  "ubicacionGeografica": "Sierra Tarahumara, Chihuahua",
  "fechaAvistamiento": "2026-10-01",
  "observaciones": "Ejemplar adulto"
}
```

## Ramas

- `master`: versión entregada.
- `develop`: integración. Los PR van aquí.
- `feature/<iniciales>/<historia>`: una rama por historia, creada desde `develop`.
