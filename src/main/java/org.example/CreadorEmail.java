package org.example;

public class CreadorEmail extends CreadorNotificacion {

    @Override
    public Notificacion crearNotificacion() {

        return new NotificacionEmail();
    }
}