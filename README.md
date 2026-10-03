# Épica 1: Sistema de Registro y Consulta de Avistamientos de Fauna Silvestre

> **Universidad Tecnológica de Chihuahua (UTCH)**  
> **Asignatura:** Desarrollo Web Integral  
> **Proyecto:** HU 1.1 (Registro de Avistamiento) y HU 1.2 (Consulta y Filtros) con flujo Git colaborativo  
> **Entregables:** Repositorio en GitHub, historias de usuario 1.1 y 1.2, `README.md` y capturas del proceso.

---

## 👥 Integrantes y distribución de tareas

| Integrante | Asignación | Rama |
| :--- | :--- | :--- |
| **Ramiro Aragón (ram)** | HU 1.1: Controller, DTOs, model, repository y service del registro (`POST /avistamientos`) | `feature/ram/hu-1.1-registro-avistamiento` |
| **Aram (AA)** | HU 1.2: Consulta y filtros en `AvistamientoService` (`GET /avistamientos`) | `feature/AA/hu-1.2-consulta-avistamiento` |

---

## 📝 Historias de usuario

### HU 1.1: Registro de avistamiento
* **Como:** usuario (investigador o guardabosques)
* **Quiero:** registrar un avistamiento indicando especie, ubicación geográfica, fecha y observaciones
* **Para:** llevar el historial de fauna de la reserva

**Criterios de aceptación:**
1. `POST /avistamientos` exige `especie`, `ubicacionGeografica` y `fechaAvistamiento` (`@NotBlank`, `@NotNull`).
2. Si falta un campo obligatorio, responde `400 Bad Request`.
3. `fechaRegistro` se genera automáticamente al guardar (`@CreationTimestamp`).
4. Responde `201 Created` con el `id` generado.

### HU 1.2: Consulta y filtros
* **Como:** investigador
* **Quiero:** consultar los avistamientos registrados, filtrando por especie, zona o fecha
* **Para:** analizar el historial de la reserva

**Criterios de aceptación:**
1. `GET /avistamientos` sin filtros devuelve todos los registros.
2. Los filtros `especie`, `ubicacion` y `fecha` son opcionales y se pueden combinar.
3. `especie` y `ubicacion` aceptan coincidencias parciales y no distinguen mayúsculas.
4. Los resultados se ordenan por fecha, del más reciente al más antiguo.

---

## 🔀 Flujo de trabajo

Ramas: `master` (entrega), `develop` (integración) y una rama `feature/<iniciales>/<historia>` por historia, creada desde `develop`. Los merges se hicieron en GitHub mediante Pull Request hacia `develop`.

### Paso 1: Base de datos
```sql
CREATE DATABASE bd_fauna_silvestre;
```
La tabla se crea con [`db/avistamientos.sql`](db/avistamientos.sql).

> ![Captura 1: Creación de la base de datos y la tabla](screenshots/01_base_datos.png)

---

### Paso 2: Estructura del proyecto
Proyecto Spring Boot con los paquetes `controller`, `dto`, `model`, `repository` y `service`.

```bash
git init -b master
git add .
git commit -m "Estructura inicial del proyecto"
git remote add origin https://github.com/ramsz9/avistamiento_registro_fauna.git
git push -u origin master
git checkout -b develop
git push -u origin develop
```

> ![Captura 2: Estructura del proyecto](screenshots/02_estructura_proyecto.png)

---

### Paso 3: HU 1.1 - Código del registro
```bash
git checkout -b feature/ram/hu-1.1-registro-avistamiento
```

**Model:** entidad mapeada a la tabla `avistamientos`.
> ![Captura 3: AvistamientoModel](screenshots/03_hu11_model.png)

**Repository:**
> ![Captura 4: AvistamientoRepository](screenshots/04_hu11_repository.png)

**Service:** método `create`.
> ![Captura 5: AvistamientoService](screenshots/05_hu11_service.png)

**DTOs:** entrada con validaciones y respuesta con `message` e `id`.
> ![Captura 6: AvistamientoDto](screenshots/06_hu11_dto.png)
> ![Captura 7: AvistamientoRsDto](screenshots/07_hu11_rsdto.png)

**Controller:** `POST /avistamientos`.
> ![Captura 8: AvistamientoController](screenshots/08_hu11_controller.png)

---

### Paso 4: HU 1.1 - Pull Request y merge a `develop`
```bash
git add .
git commit -m "HU 1.1: registro de avistamiento"
git push -u origin feature/ram/hu-1.1-registro-avistamiento
```

> ![Captura 9: Rama subida a GitHub](screenshots/09_hu11_rama_push.png)
> ![Captura 10: Creación del PR hacia develop](screenshots/10_hu11_crear_pr.png)
> ![Captura 11: PR #1 fusionado](screenshots/11_hu11_pr_merged.png)

---

### Paso 5: HU 1.2 - Código de la consulta
```bash
git checkout develop
git pull
git checkout -b feature/AA/hu-1.2-consulta-avistamiento
```

**DTO de respuesta:**
> ![Captura 12: AvistamientoConsultaDto](screenshots/12_hu12_consulta_dto.jpg)

**Model:** método `toDto()`.
> ![Captura 13: Import en AvistamientoModel](screenshots/13_hu12_model_import.jpg)
> ![Captura 14: Método toDto](screenshots/14_hu12_model_todto.jpg)

**Service:** método `findAll` con filtros (Query by Example).
> ![Captura 15: Imports en AvistamientoService](screenshots/15_hu12_service_imports.jpg)
> ![Captura 16: Método findAll](screenshots/16_hu12_service_findall.jpg)

**Controller:** `GET /avistamientos`.
> ![Captura 17: Imports en AvistamientoController](screenshots/17_hu12_controller_imports.jpg)
> ![Captura 18: Endpoint GET](screenshots/18_hu12_controller_get.jpg)

**README:**
> ![Captura 19: README](screenshots/19_hu12_readme.jpg)

---

### Paso 6: HU 1.2 - Pull Request y merge a `develop`
```bash
git add .
git commit -m "HU 1.2: consulta y filtros de avistamientos"
git push -u origin HEAD
```

> ![Captura 20: Comparación de ramas](screenshots/20_hu12_comparar_pr.png)
> ![Captura 21: Creación del PR](screenshots/21_hu12_crear_pr.png)
> ![Captura 22: PR #2 fusionado](screenshots/22_hu12_pr_merged.png)

---

## 🛠️ Ejecutar la aplicación

1. Clonar el proyecto:
   ```bash
   git clone https://github.com/ramsz9/avistamiento_registro_fauna.git
   cd avistamiento_registro_fauna
   ```
2. Crear la base de datos y la tabla (Paso 1).
3. Crear `src/main/resources/application-dev.properties` (no se sube al repo):
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/bd_fauna_silvestre
   spring.datasource.username=postgres
   spring.datasource.password=TU_PASSWORD
   spring.jpa.hibernate.ddl-auto=update
   ```
4. Iniciar el servidor:
   ```bash
   ./mvnw spring-boot:run
   ```

### Endpoints

| Método | Ruta | Descripción |
|---|---|---|
| POST | `/avistamientos` | Registra un avistamiento |
| GET | `/avistamientos?especie=&ubicacion=&fecha=` | Consulta avistamientos (filtros opcionales, fecha `AAAA-MM-DD`) |

Ejemplo de cuerpo para el `POST`:
```json
{
  "especie": "Puma concolor",
  "ubicacionGeografica": "Sierra Tarahumara, Chihuahua",
  "fechaAvistamiento": "2026-10-01",
  "observaciones": "Ejemplar adulto"
}
```

---

## 📌 Repositorio

https://github.com/ramsz9/avistamiento_registro_fauna
