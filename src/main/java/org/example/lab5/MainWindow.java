package org.example.lab5;

import com.sun.tools.javac.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class MainWindow extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        loaRestaurant();
    }

    public static void loadSwitcher() throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(MainWindow.class.getResource("SwitcherInterface.fxml")));
        primaryStage.setTitle("Перекидыватель");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void loadCheckboxer() throws Exception {
        FXMLLoader loader = new FXMLLoader(MainWindow.class.getResource("CheckboxerInterface.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Checkboxer");
        primaryStage.show();
    }

    public static void loaRestaurant() throws Exception {
        FXMLLoader loader = new FXMLLoader(MainWindow.class.getResource("RestaurantInterface.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Меню ресторана");
        primaryStage.show();
    }

    public static void loadCalculator() throws Exception {
        FXMLLoader loader = new FXMLLoader(MainWindow.class.getResource("CalculatorInterface.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Calculator");
        ((CalculatorController) loader.getController()).init(primaryStage);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);  // Запуск приложения через стандартный метод JavaFX
    }
}
