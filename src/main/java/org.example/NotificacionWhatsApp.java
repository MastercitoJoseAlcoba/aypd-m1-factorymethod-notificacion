package org.example;

public class NotificacionWhatsApp implements Notificacion {

    @Override
    public void enviar(String destinatario, String mensaje) {

        System.out.println("WhatsApp enviado a: " + destinatario);
        System.out.println("Mensaje: " + mensaje);
    }
}