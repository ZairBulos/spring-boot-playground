# Hexagonal Architecture (Ports and Adapters) with Spring Boot

La arquitectura hexagonal es un estilo de diseño de software que tiene como objetivo desacoplar el núcleo de una aplicación de sus dependencias externas, como bases de datos, interfaces de usuario, servicios externos, etc. Se centra en separar la lógica de negocio (núcleo) de las interacciones con el mundo exterior mediante el uso de puertos y adaptadores.

## Puertos y Adaptadores

Este estilo arquitectónico se basa en la idea de definir puertos como puntos de entrada y salida del sistema, e implementarlos mediante adaptadores, que actúan como traductores entre la lógica interna y los sistemas externos.

### Puertos

**Interfaces** que definen cómo el núcleo de la aplicación (casos de uso y dominio) se comunica con el exterior

- **Puertos de entrada**: Exponen funcionalidades que pueden ser activadas desde el exterior (por ejemplo, desde un controlador HTTP o una cola de mensajes).
- **Puertos de salida**: Definen las operaciones que el núcleo necesita que sean implementadas externamente (por ejemplo, para acceder a una base de datos o a un servicio externo).

### Adaptadores

**Implementaciones concretas** de esos puertos. Su función es conectar el mundo exterior con el núcleo de la aplicación, sin que este último tenga conocimiento de los detalles técnicos.

- Controladores REST (entrada).
- Repositorios JPA, clientes HTTP, colas (salida).

## La regla de la dependencia

Una de las reglas más importantes de esta arquitectura es la dirección de las dependencias: Siempre deben apuntar hacia el núcleo de la aplicación.

Esto significa que:

- El código del dominio y los casos de uso no debe depender de frameworks, controladores, bases de datos ni librerías externas. 
- En cambio, los adaptadores (REST, persistencia, etc.) dependen del núcleo, implementando sus interfaces.

Este principio garantiza que la lógica de negocio permanezca estable y protegida, mientras que los detalles técnicos pueden cambiar sin afectar el corazón de la aplicación.