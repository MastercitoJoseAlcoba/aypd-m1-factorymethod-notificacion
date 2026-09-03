package org.example;

public class Main {

    public static void main(String[] args) {

        CreadorNotificacion creadorEmail = new CreadorEmail();

        creadorEmail.enviarNotificacion(
                "jalcobapereda@gmail.com",
                "Prueba de notificación por correo"
        );

        System.out.println("----------------------");

        CreadorNotificacion creadorSMS = new CreadorSMS();

        creadorSMS.enviarNotificacion(
                "098674403",
                "Prueba de notificación por SMS"
        );
    }
}