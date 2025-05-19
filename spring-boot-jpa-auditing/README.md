# JPA Auditing

Auditar es una práctica fundamental en el desarrollo de software, especialmente en aplicaciones basadas en datos. Implica registrar cambios en la información, monitorear acciones de los usuarios y mantener un historial detallado de estas actividades.

<p align="center">
    <img
        src="https://codevup.com/assets/images/2023-10-13-spring-jpa-auditing/thumbnail.jpg"
        alt="JPA Auditing Spring Boot"
        height="400px"
    />
</p>

## ¿Por qué usar JPA Auditing?

La auditoría de base de datos permite rastrear y registrar eventos, como la creación o modificación de registros, y quién los realizó. Esto es esencial para:

- **Integridad de Datos**: Asegura que los datos se mantengan consistentes y sin alteraciones maliciosas. 
- **Seguridad**: Ayuda a identificar y reaccionar ante brechas o comportamientos sospechosos. 
- **Cumplimiento**: Muchas industrias requieren auditoría para cumplir con normativas legales. 
- **Resolución de Problemas**: Provee un rastro histórico útil para depuración y análisis de errores.

## ¿ Cómo implementar JPA Auditing con Spring Data JPA ?

Spring Data JPA facilita la auditoría con anotaciones específicas, eliminando la necesidad de registrar manualmente estos campos en cada operación.

### Anotaciones de Auditoría

- `@CreatedBy`: Usuario que creó el registro. 
- `@CreatedDate`: Fecha y hora de creación. 
- `@LastModifiedBy`: Usuario que actualizó el registro por última vez. 
- `@LastModifiedDate`: Fecha y hora de la última modificación.