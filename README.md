# literalura_project

Este proyecto, **literalura_project**, es una aplicación Java desarrollada con Spring Boot que permite buscar y gestionar información de libros y autores desde una API externa y almacenarla en una base de datos relacional. La aplicación incluye funcionalidades como búsqueda por título, autor, género, y más.

## Características

- **Búsqueda de Libros:** Permite buscar libros por título, autor, género, idioma y otros criterios.
- **Gestión de Autores:** Permite agregar, actualizar y eliminar información de autores.
- **Persistencia de Datos:** Utiliza JPA/Hibernate para mapear y almacenar información en una base de datos relacional.
- **Interfaz de Consola:** Implementa una interfaz de línea de comandos (CLI) para interactuar con la aplicación.

## Estructura del Proyecto

El proyecto está estructurado de la siguiente manera:

- **`src/main/java`:** Contiene el código fuente Java de la aplicación.
  - `com.example.literalura_project`: Paquete principal de la aplicación.
    - `CommandLineRunner.java`: Clase principal que inicializa y ejecuta la aplicación.
    - `model`: Contiene las entidades (`Book.java`, `Author.java`) y clases relacionadas.
    - `repository`: Contiene las interfaces de repositorio para acceso a datos.
    - `service`: Contiene la lógica de negocio y servicios de la aplicación.
    - `util`: Contiene clases de utilidad para la aplicación.
- **`src/main/resources`:** Contiene archivos de configuración y recursos.
  - `application.properties`: Configuración de la base de datos y otras propiedades de la aplicación.

## Uso

1. **Configuración:**
   - Asegúrate de tener configurada una base de datos relacional compatible (MySQL, PostgreSQL, etc.).
   - Configura los parámetros de conexión en `application.properties`.

2. **Ejecución:**
   - Compila y ejecuta la clase `CommandLineRunner.java`.
   - Sigue las instrucciones en la consola para interactuar con la aplicación.

## Contribución

Las contribuciones son bienvenidas. Si deseas contribuir al proyecto, sigue estos pasos:

1. Haz un *fork* del repositorio.
2. Crea una rama (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza tus cambios y confirma (`git commit -am 'Agrega nueva funcionalidad'`).
4. Sube la rama (`git push origin feature/nueva-funcionalidad`).
5. Abre un *pull request*.

## Contacto



