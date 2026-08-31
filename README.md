# Actividad: sistema de notificaciones con Factory Method

## Introducción

En la actividad anterior se implementó una aplicación Java capaz de enviar un correo electrónico real mediante Gmail. Actualmente, la aplicación se encuentra vinculada directamente con un único medio de comunicación: el correo electrónico.

En esta actividad se ampliará el sistema para que permita enviar notificaciones mediante dos canales:

- **correo electrónico real**, reutilizando la implementación anterior;
- **mensaje SMS simulado**, mostrando el resultado del envío en la consola.

Para organizar la creación de los distintos tipos de notificación se aplicará el patrón de diseño **Factory Method**.

Factory Method es un patrón creacional que define un método para crear objetos, pero permite que las subclases determinen qué objeto concreto debe generarse. De esta manera, el código principal puede trabajar con un tipo general de notificación sin depender directamente de las clases que implementan el envío por correo o SMS.

En este caso:

- el creador de correo deberá generar una notificación por correo electrónico;
- el creador de SMS deberá generar una notificación SMS.

---

## Objetivo

Modificar el proyecto anterior para construir un sistema que permita seleccionar el medio por el cual se enviará una notificación, aplicando Factory Method para crear el canal correspondiente.

---

## Situación planteada

Una institución necesita comunicar avisos a sus estudiantes. Algunos mensajes se enviarán por correo electrónico y otros mediante SMS.

El sistema deberá solicitar al usuario el canal que desea utilizar:

```text
1. Correo electrónico
2. SMS
```

Si se selecciona correo electrónico, la aplicación deberá utilizar el envío real desarrollado en la actividad anterior.

Si se selecciona SMS, la aplicación deberá simular el envío mostrando el teléfono destinatario y el contenido del mensaje en la consola.

---

## Estructura sugerida

El proyecto podrá organizarse utilizando las siguientes clases:

| Clase o interfaz | Responsabilidad |
|---|---|
| `Notificacion` | Definir la operación general para enviar una notificación. |
| `NotificacionCorreo` | Implementar el envío real mediante Gmail. |
| `NotificacionSMS` | Simular el envío de un SMS mediante la consola. |
| `CreadorNotificacion` | Declarar el Factory Method para crear notificaciones. |
| `CreadorCorreo` | Crear una notificación por correo. |
| `CreadorSMS` | Crear una notificación SMS. |
| `Main` | Solicitar la opción al usuario y utilizar el creador correspondiente. |

La estructura esperada será similar a la siguiente:

```text
src/main/java/org/example
├── Notificacion.java
├── NotificacionCorreo.java
├── NotificacionSMS.java
├── CreadorNotificacion.java
├── CreadorCorreo.java
├── CreadorSMS.java
└── Main.java
```

> Si el proyecto utiliza un paquete diferente de `org.example`, las clases deberán crearse dentro del paquete correspondiente.

---

## Código base

La interfaz general puede comenzar de la siguiente manera:

```java
public interface Notificacion {

    void enviar(
            String destinatario,
            String asunto,
            String contenido
    ) throws Exception;
}
```

La clase creadora deberá declarar el Factory Method encargado de crear la notificación:

```java
public abstract class CreadorNotificacion {

    protected abstract Notificacion crearNotificacion();

    public void notificar(
            String destinatario,
            String asunto,
            String contenido
    ) throws Exception {

        Notificacion notificacion = crearNotificacion();
        notificacion.enviar(destinatario, asunto, contenido);
    }
}
```

A partir de esta base deberán implementarse las clases concretas. No es necesario modificar la configuración de Gmail realizada en la actividad anterior.

---

## Instrucciones

### 1. Implementar la interfaz `Notificacion`

La interfaz deberá establecer una operación común para todos los medios de notificación.

Tanto el correo como el SMS deberán poder utilizarse mediante esta interfaz.

### 2. Implementar `NotificacionCorreo`

La clase deberá:

- implementar `Notificacion`;
- reutilizar el código de envío real desarrollado en la actividad anterior;
- obtener el correo remitente y la contraseña de aplicación desde las variables de entorno;
- informar en la consola si el correo fue enviado correctamente.

Las variables de entorno deberán continuar llamándose:

```text
CORREO_REMITENTE
CLAVE_APLICACION
```

No se deben escribir credenciales directamente en el código.

### 3. Implementar `NotificacionSMS`

La clase deberá:

- implementar `Notificacion`;
- simular el envío mediante la consola;
- mostrar el teléfono destinatario;
- mostrar el contenido del mensaje.

No es necesario conectarse con un servicio externo de SMS. El asunto puede recibirse como parámetro, aunque no es obligatorio mostrarlo en la simulación.

### 4. Implementar `CreadorNotificacion`

La clase deberá:

- ser abstracta;
- declarar el método `crearNotificacion()`;
- proporcionar un procedimiento general para crear y enviar una notificación.

El método `crearNotificacion()` representará el **Factory Method** de la solución.

### 5. Implementar `CreadorCorreo`

La clase deberá:

- extender `CreadorNotificacion`;
- implementar el Factory Method;
- crear el tipo de notificación correspondiente al correo electrónico.

### 6. Implementar `CreadorSMS`

La clase deberá:

- extender `CreadorNotificacion`;
- implementar el Factory Method;
- crear el tipo de notificación correspondiente al SMS.

### 7. Implementar la clase `Main`

La clase principal deberá:

- mostrar las opciones de correo y SMS;
- solicitar una opción al usuario;
- solicitar los datos necesarios para el envío;
- seleccionar el creador correspondiente;
- realizar el envío mediante un objeto de tipo `CreadorNotificacion`;
- controlar el ingreso de una opción incorrecta.

La clase `Main` no deberá crear directamente objetos de tipo `NotificacionCorreo` o `NotificacionSMS`.

---

## Funcionamiento esperado

### Envío mediante correo electrónico

```text
Seleccione el medio de notificación:
1. Correo electrónico
2. SMS

Opción: 1
Destinatario: prueba@yopmail.com
Asunto: Confirmación
Mensaje: Tu inscripción fue confirmada.

Correo enviado correctamente.
```

El mensaje deberá llegar realmente a la dirección indicada. Para realizar pruebas puede utilizarse una cuenta propia o un correo temporal de [YOPmail](https://yopmail.com/).

> Los correos temporales deben utilizarse únicamente con mensajes de prueba que no contengan datos personales, contraseñas ni información sensible.

### Envío mediante SMS

```text
Seleccione el medio de notificación:
1. Correo electrónico
2. SMS

Opción: 2
Teléfono: 099123456
Mensaje: Tu inscripción fue confirmada.

SMS enviado
Destinatario: 099123456
Mensaje: Tu inscripción fue confirmada.
```

En este caso, el SMS solamente se mostrará en la consola.

---

## Condiciones de implementación

- Se debe mantener la dependencia de Jakarta Mail utilizada en la actividad anterior.
- Las credenciales de Gmail deben permanecer fuera del código.
- El correo electrónico debe enviarse realmente.
- El SMS debe simularse mediante la consola.
- `NotificacionCorreo` y `NotificacionSMS` deben implementar la misma interfaz.
- `CreadorCorreo` y `CreadorSMS` deben extender la misma clase creadora.
- La clase `Main` no debe instanciar directamente los productos concretos.
- Cada creador concreto debe decidir qué implementación de `Notificacion` crear.
- El programa debe controlar una opción incorrecta del menú.

---

## Lista de comprobación

- [ ] Existe una interfaz común para las notificaciones.
- [ ] La notificación por correo implementa la interfaz.
- [ ] La notificación SMS implementa la interfaz.
- [ ] El correo electrónico continúa enviándose correctamente.
- [ ] El SMS se muestra correctamente en la consola.
- [ ] Existe una clase creadora abstracta.
- [ ] El Factory Method está declarado en la clase creadora.
- [ ] Existe un creador concreto para correo.
- [ ] Existe un creador concreto para SMS.
- [ ] `Main` trabaja con un objeto de tipo `CreadorNotificacion`.
- [ ] `Main` no crea directamente una notificación por correo o SMS.
- [ ] Las credenciales permanecen fuera del código.
- [ ] El proyecto compila y permite probar ambas opciones.

---

## Desafío adicional opcional

Incorporar una tercera forma de notificación, por ejemplo WhatsApp o una notificación emergente, creando las clases necesarias sin modificar las implementaciones existentes de correo y SMS.

---

*Material elaborado por Área de Programación - LTI*