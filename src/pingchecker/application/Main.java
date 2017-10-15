package pingchecker.application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Main extends Application {
    final static String NA = "104.160.131.3";
    final static String EUW = "104.160.141.3";
    final static String EUNE = "104.160.142.3";
    final static String OCE = "104.160.156.1";
    final static String LAN = "104.160.136.3";

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("pingcheckergui.fxml"));

        Scene scene = new Scene(root, 475, 300);

        primaryStage.setTitle("Ping Checker");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });


    }
}
