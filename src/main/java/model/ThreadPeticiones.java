package main.java.model;

import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import main.java.fx.Controller;

public class ThreadPeticiones extends Thread {

    private StringProperty restantes;
    String userName;
    long numeroDeVeces;
    ApiConnection apiConnection;
    String mensaje;

    public ThreadPeticiones(String userName, long numeroDeVeces, ApiConnection apiConnection, String mensaje, StringProperty restantes){
        this.numeroDeVeces = numeroDeVeces;
        this.userName = userName;
        this.apiConnection = apiConnection;
        this.mensaje = mensaje;
        this.restantes = restantes;

    }

    public void run(){
        String id = null;
        try {
            Platform.runLater(() -> this.restantes.setValue(Long.toString(numeroDeVeces)));
            id = apiConnection.getId(userName);

        } catch (UnirestException e) {
            e.printStackTrace();
        }
        for( int i = 0; i < numeroDeVeces; i++){
            if (this.isInterrupted())
                break;
            try {
                apiConnection.enviarPregunta(id, mensaje);
                final long actual = i;
                Platform.runLater(() -> this.restantes.setValue(Long.toString(numeroDeVeces - actual)));
                Thread.sleep(Controller.waitTime);
            } catch (UnirestException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                break;
            }
        }


    }

}
