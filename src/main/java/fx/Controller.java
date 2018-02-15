package main.java.fx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import main.java.model.ApiConnection;
import main.java.model.ThreadPeticiones;

public class Controller {

    public static final String COMENZAR = "Empezar";
    public static final String EMPEZANDO = "Cancelar";

    public static final int waitTime = 5000;

    private ApiConnection apiConnection;



    private StringProperty repetitions = new SimpleStringProperty();
    private volatile StringProperty restantes = new SimpleStringProperty();


    private ThreadPeticiones threadPeticiones;

    @FXML
    private TextField userNameLabel;
    @FXML
    private TextArea messageTextArea;
    @FXML
    private TextField repetitionsLabel;
    @FXML
    private Label aproximationLabel;
    @FXML
    private Button comenzarButton;
    @FXML
    private Label restantesLabel;


    @FXML
    private void handleComenzar() {
        if (comenzarButton.getText().equals(COMENZAR)) {
            if (camposCorrectos()) {

                this.threadPeticiones = new ThreadPeticiones(userNameLabel.getText(),
                       Long.parseLong(repetitionsLabel.getText()), apiConnection, messageTextArea.getText(), restantes );
                threadPeticiones.start();
                comenzarButton.setText(EMPEZANDO);
            }
        } else{
            threadPeticiones.interrupt();
            restantes.setValue("");
            comenzarButton.setText(COMENZAR);

        }


    }

    private boolean camposCorrectos() {
        try {
            if (userNameLabel.getText().equals("")){
                return false;
            }
            if (messageTextArea.getText().equals("")){
                return false;
            }
            Integer.parseInt(repetitionsLabel.getText());
            return true;
        } catch (Exception e) {
            return false;
        }

}


    @FXML
    private void initialize() {

        repetitions.addListener((observable, oldValue, newValue) -> {
            try {
                aproximationLabel.textProperty()
                        .setValue(getAproximationMinutes(newValue));
            } catch (Exception e) {
                aproximationLabel.textProperty().setValue("0");
            }
        });
        repetitionsLabel.textProperty().bindBidirectional(repetitions);
        repetitions.setValue("100");

        restantesLabel.textProperty().bindBidirectional(restantes);
        restantes.setValue("");



    }

    private String getAproximationMinutes(String repetitions) {
        return Long.toString(waitTime * Long.parseLong(repetitions)/1000/60);
    }


    public ApiConnection getApiConnection() {
        return apiConnection;
    }

    public void setApiConnection(ApiConnection apiConnection) {
        this.apiConnection = apiConnection;
    }


}
