package org.example.explicacion;/*
===========================================================
ACTIVIDAD 2 - FACTORY METHOD
SISTEMA DE NOTIFICACIONES
===========================================================

OBJETIVO:

Ampliar el sistema de notificaciones para trabajar con:

- Email
- SMS
- WhatsApp

Se utiliza el patrón:

FACTORY METHOD


-----------------------------------------------------------
¿QUÉ ES FACTORY METHOD?
-----------------------------------------------------------

Es un patrón creacional.

La idea es no crear directamente todos los objetos desde
el Main.

En lugar de hacer solamente:

new NotificacionEmail();

new NotificacionSMS();

new NotificacionWhatsApp();


se crean clases responsables de fabricar cada objeto:

CreadorEmail
CreadorSMS
CreadorWhatsApp


Todos heredan de:

CreadorNotificacion


===========================================================
ESTRUCTURA
===========================================================


Notificacion
    |
    |---- NotificacionEmail
    |
    |---- NotificacionSMS
    |
    |---- NotificacionWhatsApp


CreadorNotificacion
    |
    |---- CreadorEmail
    |
    |---- CreadorSMS
    |
    |---- CreadorWhatsApp


===========================================================
IMPORTANTE
===========================================================

En el proyecto real cada clase pública va en su
propio archivo .java.

Este código está unido solamente para estudiar
la estructura completa.

===========================================================
*/


// =========================================================
// 1. INTERFAZ
// =========================================================

/*

La interfaz define qué debe saber hacer cualquier
notificación.

Todas deben implementar:

enviar()

*/

interface Notificacion {

    void enviar(
            String destinatario,
            String mensaje
    );

}


// =========================================================
// 2. NOTIFICACIÓN POR EMAIL
// =========================================================

/*

Esta clase implementa la interfaz Notificacion.

En el proyecto real, acá se reutiliza el código
de Jakarta Mail realizado en la Actividad 1.

*/

class NotificacionEmail
        implements Notificacion {


    @Override
    public void enviar(
            String destinatario,
            String mensaje
    ) {


        System.out.println(
                "Enviando correo a: "
                        + destinatario
        );


        System.out.println(
                "Mensaje: "
                        + mensaje
        );


        /*
        En el proyecto real:

        acá se utiliza Jakarta Mail
        para enviar el correo verdadero.
        */

    }
}


// =========================================================
// 3. NOTIFICACIÓN POR SMS
// =========================================================

/*

El SMS es simulado.

No llega realmente al celular.

Se muestra solamente en consola.
*/

class NotificacionSMS
        implements Notificacion {


    @Override
    public void enviar(
            String destinatario,
            String mensaje
    ) {


        System.out.println(
                "SMS enviado a: "
                        + destinatario
        );


        System.out.println(
                "Mensaje: "
                        + mensaje
        );

    }
}


// =========================================================
// 4. NOTIFICACIÓN POR WHATSAPP
// =========================================================

/*

WhatsApp también es simulado.

Se agregó como tercera forma de notificación.

Esto permite demostrar que Factory Method
puede extenderse sin modificar Email ni SMS.
*/

class NotificacionWhatsApp
        implements Notificacion {


    @Override
    public void enviar(
            String destinatario,
            String mensaje
    ) {


        System.out.println(
                "WhatsApp enviado a: "
                        + destinatario
        );


        System.out.println(
                "Mensaje: "
                        + mensaje
        );

    }
}


// =========================================================
// 5. CREADOR ABSTRACTO
// =========================================================

/*

Esta es una de las partes más importantes
del patrón Factory Method.

El método:

crearNotificacion()

no sabe exactamente qué tipo concreto
de notificación será creada.

Las subclases toman esa decisión.
*/

abstract class CreadorNotificacion {


    // FACTORY METHOD

    public abstract Notificacion
    crearNotificacion();


    /*
    Este método utiliza el objeto creado
    por el Factory Method.
    */

    public void enviarNotificacion(

            String destinatario,
            String mensaje

    ) {


        Notificacion notificacion =
                crearNotificacion();


        notificacion.enviar(
                destinatario,
                mensaje
        );

    }

}


// =========================================================
// 6. CREADOR EMAIL
// =========================================================

/*

Este creador decide crear:

NotificacionEmail
*/

class CreadorEmail
        extends CreadorNotificacion {


    @Override
    public Notificacion
    crearNotificacion() {


        return new NotificacionEmail();

    }
}


// =========================================================
// 7. CREADOR SMS
// =========================================================

/*

Este creador decide crear:

NotificacionSMS
*/

class CreadorSMS
        extends CreadorNotificacion {


    @Override
    public Notificacion
    crearNotificacion() {


        return new NotificacionSMS();

    }
}


// =========================================================
// 8. CREADOR WHATSAPP
// =========================================================

/*

Este creador decide crear:

NotificacionWhatsApp

Fue agregado sin modificar:

NotificacionEmail
NotificacionSMS
CreadorEmail
CreadorSMS

Esa es una de las ventajas del patrón.
*/

class CreadorWhatsApp
        extends CreadorNotificacion {


    @Override
    public Notificacion
    crearNotificacion() {


        return new NotificacionWhatsApp();

    }
}


// =========================================================
// 9. MAIN
// =========================================================

public class FactoryMethodNotificacionDos {


    public static void main(String[] args) {


        // =================================================
        // EMAIL
        // =================================================

        CreadorNotificacion creadorEmail =
                new CreadorEmail();


        creadorEmail.enviarNotificacion(

                "correo@ejemplo.com",

                "Prueba de notificación por correo"

        );


        System.out.println(
                "----------------------"
        );


        // =================================================
        // SMS
        // =================================================

        CreadorNotificacion creadorSMS =
                new CreadorSMS();


        creadorSMS.enviarNotificacion(

                "099000000",

                "Prueba de notificación por SMS"

        );


        System.out.println(
                "----------------------"
        );


        // =================================================
        // WHATSAPP
        // =================================================

        CreadorNotificacion creadorWhatsApp =
                new CreadorWhatsApp();


        creadorWhatsApp.enviarNotificacion(

                "099000000",

                "Prueba de notificación por WhatsApp"

        );

    }
}


/*
===========================================================
QUÉ HAY QUE RECORDAR PARA EL PARCIAL
===========================================================


1. INTERFAZ

interface Notificacion {

    void enviar(
        String destinatario,
        String mensaje
    );

}


-----------------------------------------------------------
2. FACTORY METHOD
-----------------------------------------------------------

public abstract Notificacion
crearNotificacion();


-----------------------------------------------------------
3. FACTORY EMAIL
-----------------------------------------------------------

return new NotificacionEmail();


-----------------------------------------------------------
4. FACTORY SMS
-----------------------------------------------------------

return new NotificacionSMS();


-----------------------------------------------------------
5. FACTORY WHATSAPP
-----------------------------------------------------------

return new NotificacionWhatsApp();


===========================================================
IDEA COMPLETA
===========================================================


              Notificacion
                   |
        -----------------------
        |          |          |
      Email       SMS      WhatsApp



         CreadorNotificacion
                   |
        -----------------------
        |          |          |
    Creador     Creador    Creador
     Email        SMS      WhatsApp


===========================================================
VENTAJA
===========================================================

Se pudo agregar WhatsApp creando:

NotificacionWhatsApp

y:

CreadorWhatsApp


sin modificar las implementaciones anteriores
de Email y SMS.


Eso permite que el sistema sea:

- más extensible
- menos acoplado
- más fácil de mantener


===========================================================
*/