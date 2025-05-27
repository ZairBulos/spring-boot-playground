# Spring Boot Database Migrations with Flyway

La mayoría de las aplicaciones de software utilizan bases de datos SQL debido a su confiabilidad, consistencia y madurez para manejar datos estructurados. Con el tiempo, el esquema de la base de datos evoluciona a medida que cambian los requisitos comerciales, ya sea para agregar nuevas características o actualizar las existentes.

Los frameworks de mapeo objeto-relacional (ORM) como JPA/Hibernate proporcionan una forma sencilla de generar un esquema de base de datos basado en entidades JPA, lo cual puede ser conveniente durante el desarrollo.

Sin embargo, actualizar automáticamente el esquema de la base de datos basándose solo en los cambios de las entidades JPA puede ser riesgoso y propenso a errores, especialmente en entornos de producción. En su lugar, se recomienda utilizar una herramienta de migración de bases de datos como [Flyway](https://www.red-gate.com/products/flyway/).

<p align="center">
    <img src="https://i.ibb.co/k28cfVdJ/flyway.png" alt="Flyway Spring Boot" />
</p>

## ¿Por qué usar una herramienta de migración de bases de datos?

Aunque los frameworks ORM permiten generar y actualizar el esquema de la base de datos en función de los modelos de entidad JPA, confiar únicamente en estos cambios automáticos es riesgoso, especialmente en producción.

Algunos de los problemas asociados con el uso de `ddl-auto=true` en JPA/Hibernate incluyen:
- Aunque los ORM intentan asignar propiedades de objetos a tipos de columnas apropiados, no siempre lo hacen correctamente.
- Los cambios en las entidades JPA pueden no generar los cambios esperados en las estructuras de la base de datos. Por ejemplo, si renombras una propiedad de una entidad, en lugar de cambiar el nombre de la columna existente, JPA/Hibernate puede crear una nueva columna y dejar la anterior intacta.
- El mecanismo de actualización de esquemas de JPA/Hibernate no considera optimizaciones específicas del proveedor de la base de datos, como tipos de columnas personalizados, particiones de tablas o tipos de índices. Esto puede afectar el rendimiento y limitar las posibilidades de ajuste.

## Introducción a Flyway

Flyway es una herramienta de migración de bases de datos de código abierto que simplifica el proceso de gestión y versionado
de cambios en el esquema de la base de datos.

Con Flyway, los scripts de migración se almacenan junto con el código de la aplicación, siguiendo un enfoque consistente
y versionado que permite a los equipos gestionar los cambios en la base de datos como parte de su flujo de desarrollo. 
Flyway es compatible con una amplia variedad de bases de datos, incluidas MySQL, PostgreSQL, Oracle, SQL Server y muchas más.

Flyway utiliza un sistema de versionado simple para administrar los scripts de migración. Cada script recibe un número de versión único (por ejemplo, `V1__init.sql`, `V2__create_articles_table.sql`), que Flyway usa para rastrear qué scripts han sido aplicados y cuáles están pendientes.

La convención de nombres para las migraciones versionadas es: `{Prefijo}{Versión}{Separador}{Descripción}{Sufijo}`

Por defecto:
- `Prefijo` = `V`
- `Separador` = `__`
- `Sufijo` = `.sql`

Ejemplos de nombres de migraciones en Flyway:
- `V1__Init_Setup.sql`
- `V2__Add_status_col.sql`
- `V3.1__create_url_index.sql`
- `V3.2__add_updated_by_column_to_bookmarks_table.sql`
- `V4__Add_tags_table.sql`

Una vez que se crean los scripts de migración, puedes aplicarlos a una base de datos usando la API de Java de Flyway o los complementos de Maven o Gradle.

Después de aplicarlas, Flyway registra las migraciones aplicadas en una tabla llamada `flyway_schema_history`.
