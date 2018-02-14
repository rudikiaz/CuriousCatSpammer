package main.java.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.model.ApiConnection;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CuriousCatSpamBot.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("CuriousCatSpamBot");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();

        Controller controller = loader.getController();
        controller.setApiConnection(new ApiConnection());



    }


    public static void main(String[] args) {
        launch(args);
    }
}
