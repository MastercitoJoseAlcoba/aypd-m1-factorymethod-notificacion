package org.example;

public class CreadorSMS extends CreadorNotificacion {

    @Override
    public Notificacion crearNotificacion() {

        return new NotificacionSMS();
    }
}