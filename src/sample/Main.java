package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Scanner;

public class Main extends Application {
    static String CONNECTION_USER;
    static String CONNECTION_PASSWORD;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxmls/sample.fxml"));
        primaryStage.setTitle("Расходы на рекламу");
        primaryStage.setScene(new Scene(root, 800, 450));
        primaryStage.show();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.err.print("Enter USER of your connection: ");
        CONNECTION_USER = scanner.nextLine();
        System.err.print("Enter PASSWORD of your connection: ");
        CONNECTION_PASSWORD = scanner.nextLine();
        System.out.println("Loading..");
        launch(args);
    }
}
