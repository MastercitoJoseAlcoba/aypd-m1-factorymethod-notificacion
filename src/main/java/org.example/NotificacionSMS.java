package org.example;

public class NotificacionSMS implements Notificacion {
    @Override
    public void enviar(String destinatario, String mensaje) {

        System.out.println("SMS enviado a: " + destinatario);
        System.out.println("Mensaje: " + mensaje);
    }
}

