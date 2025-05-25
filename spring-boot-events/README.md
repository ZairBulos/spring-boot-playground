# Spring Boot Events

Los eventos nos permiten hacer que nuestra aplicación sea más flexible y desacoplada que si usaramos invocaciones a métodos directamente.

## Introducción

Spring Boot proporciona herramientas para aplicar el **patrón Observer** de manera automática, sin necesidad de configuraciones complejas, gracias a la integración de `ApplicationEventPublisher` y `@EventListener`.

## 1. Definiendo Eventos

Los eventos deben extender `ApplicationEvent`, conteniendo los datos necesarios para los oyentes.

```java
public class SpringEvent extends ApplicationEvent {
    private final String message;

    public SpringEvent(Object source, String message) {
        super(source);
        this.message = message;
    }
}
```

## 2. Publicando Eventos

Para publicar eventos, usamos `ApplicationEventPublisher`. Spring inyecta automáticamente esta dependencia en la clase encargada de la publicación.

```java
@Component
public class SpringEventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    public void publish(String message) {
        eventPublisher.publishEvent(new SpringEvent(this, message));
    }
}
```

## 3. Escuchando Eventos

Un evento puede tener **varios listeners**, cada uno manejando diferentes aspectos de la aplicación. Spring registra automáticamente estos oyentes en el arranque.

```java
@Component
public class SpringListener {

    @EventListener
    public void handleSpringEvent(SpringEvent event) {
        System.out.println("Received event: " + event.getMessage());
    }
}
```

## 4. Eventos en Transacciones

Spring permite que los listeners consideren **contextos de transacción**, asegurando que los eventos ocurran en el momento correcto.

```java
@Component
public class TransactionalListener {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleTransactionEvent(SpringEvent event) {
        System.out.println("Event processed after commit: " + event.getMessage());
    }
}
```

### Fases de ejecución en transacciones

| **Phase**           | **Descripción**                                                                    |
|---------------------|------------------------------------------------------------------------------------|
| `AFTER_COMMIT`      | (Por defecto) Se ejecuta **después** de que la transacción finaliza correctamente. |
| `AFTER_COMPLETION`  | Se ejecuta **al finalizar** la transacción, sin importar si tuvo éxito o falló.    |
| `AFTER_ROLLBACK`    | Se ejecuta **solo si la transacción se revierte** con rollback.                    |
| `BEFORE_COMMIT`     | Se ejecuta **antes** de que la transacción haga commit.                            |


## 5. Eventos Asíncronos

Por defecto, los eventos en Spring Boot son **síncronos**, lo que significa que el proceso que dispara el evento **espera** a que el evento se maneje antes de continuar. Para eventos **asíncronos**, usa `@Async`.

```java
@Component
public class AsyncEventListener {

    @Async
    @EventListener
    public void handleAsyncEvent(SpringEvent event) {
        System.out.println("Processing event asynchronously: " + event.getMessage());
    }
}
```

### Habilitando `@Async`

Para que los eventos realmente se ejecuten en **otro hilo**, es necesario habilitar `@EnableAsync`.

```java
@EnableAsync
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```