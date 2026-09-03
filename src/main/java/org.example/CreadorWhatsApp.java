package org.example;

public class CreadorWhatsApp extends CreadorNotificacion {

    @Override
    public Notificacion crearNotificacion() {

        return new NotificacionWhatsApp();
    }
}