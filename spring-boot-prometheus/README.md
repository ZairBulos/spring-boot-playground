# Spring Boot Prometheus

Prometheus es un sistema de monitoreo y alertas de código abierto. Está diseñado para recopilar, almacenar y consultar métricas de diversas fuentes, permitiendo a los usuarios obtener información sobre el rendimiento y el comportamiento de sus aplicaciones y sistemas.

<p align="center">
    <img 
        src="https://formadoresit.es/wp-content/uploads/2024/01/prometheus.jpg"
        alt="Prometheus"
    />
</p>

Tiene una arquitectura distribuida compuesta por varios componentes que trabajan juntos para recolectar y almacenar métricas. Los principales componentes de Prometheus son:

1. **Prometheus server**: Es el componente central de la arquitectura de Prometheus. Se encarga de recopilar métricas de diversas fuentes, almacenarlas en una base de datos de series temporales y proporcionar una API HTTP para consultar las métricas.
2. **Exporters**: Los exportadores son responsables de recopilar y exponer métricas desde diferentes fuentes, como aplicaciones, servicios y sistemas.
3. **Pushgateway**: Es un servicio independiente que permite a los usuarios enviar métricas desde trabajos por lotes y otros procesos de corta duración a Prometheus. Esto es útil para recopilar métricas de trabajos que no tienen endpoints HTTP de larga duración.
4. **Alertmanager**: s el componente encargado de manejar alertas generadas por Prometheus. Recibe alertas del servidor de Prometheus, las agrega y elimina duplicados, y envía notificaciones a diversos receptores.

## Conceptos Clave

### Metrics

Las métricas son la base del monitoreo con Prometheus. Se utilizan para medir el rendimiento de un sistema o aplicación. Prometheus recopila métricas de diferentes fuentes y las almacena en una base de datos de series temporales. Las métricas se representan como pares clave-valor, donde la clave representa el nombre de la métrica y el valor representa el valor de la métrica.

### Exporters

Los exportadores son responsables de recopilar y exponer métricas a Prometheus. Existen varios exportadores disponibles para diferentes tipos de sistemas y aplicaciones. Para Spring Boot, podemos usar la biblioteca Micrometer, que proporciona un exportador para Prometheus.

### Targets

Los objetivos son los sistemas o aplicaciones que Prometheus monitorea. Prometheus recopila métricas de estos objetivos en intervalos regulares.

### Query Language

PromQL es el lenguaje de consulta utilizado por Prometheus para consultar y agregar métricas. Proporciona un conjunto rico de funciones para realizar consultas complejas sobre las métricas recopiladas.
