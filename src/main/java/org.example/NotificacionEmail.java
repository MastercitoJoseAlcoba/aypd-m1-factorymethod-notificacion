package org.example;

public class NotificacionEmail implements Notificacion {
    @Override
    public void enviar(String destinatario, String mensaje) {

        System.out.println("Enviando correo a: " + destinatario);
        System.out.println("Mensaje: " + mensaje);

        // Acá después conectamos el envío real con Jakarta Mail
    }
}


