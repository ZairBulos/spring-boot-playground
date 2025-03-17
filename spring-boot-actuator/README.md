# Spring Boot Actuator

Spring Boot Actuator es una herramienta bastante útil de Spring Boot que te da acceso a información importante sobre el 
estado y el rendimiento de tu aplicación. Esto lo convierte en un recurso indispensable para monitorear y gestionar aplicaciones en tiempo real.

<p align="center">
    <img 
        src="https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.arquitecturajava.com%2Fwp-content%2Fuploads%2Factuator.png&f=1&nofb=1&ipt=2a951109b0f01512d87943e89b00f1fb163c57eb2bb29145e6b7058db4ac916c&ipo=images"
        alt="Spring Boot Actuator"
    />
</p>

## Endpoints

Proporciona endpoints listos para monitorear y gestionar aplicaciones en tiempo real. Algunos de los más comunes son:

- **/info**: Ofrece información general sobre la aplicación, como propiedades personalizadas o metadatos.
- **/health**: Proporciona un resumen del estado de salud de la aplicación, indicando si está en condiciones óptimas.
- **/metrics**: Devuelve métricas relacionadas con el rendimiento, como tasas de solicitudes, uso de memoria o estadísticas específicas de la aplicación.
- **/beans**: Enumera todos los beans registrados en el BeanFactory junto con su información detallada.
- **/loggers**: Permite consultar y ajustar los niveles de registro (logging) en tiempo real.
- **/env**: Muestra las propiedades actuales del entorno y sus configuraciones.