package org.example.lab5;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class RestaurantController {

    @FXML
    private VBox dishListVBox;

    private final List<Dish> dishes = List.of(
            new Dish("Бургер", 500),
            new Dish("Пицца", 900),
            new Dish("Суши", 400)
    );

    private final List<SelectedDish> selectedDishes = new ArrayList<>();

    @FXML
    public void initialize() {
        for (Dish dish : dishes) {
            addDishToMenu(dish);
        }
    }

    private void addDishToMenu(Dish dish) {
        HBox dishRow = new HBox(10);
        CheckBox checkBox = new CheckBox();
        Label nameLabel = new Label(dish.getName());
        Label priceLabel = new Label(dish.getPrice() + " ₽");

        // Поле для ввода количества порций
        TextField portionField = new TextField();
        portionField.setDisable(true); // отключен по умолчанию
        portionField.setText("1"); // по умолчанию 1

        // Отключение возможности ввода букв
        portionField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                portionField.setText(oldValue);
            }
        });

        checkBox.setOnAction(event -> {
            boolean selected = checkBox.isSelected();
            portionField.setDisable(!selected); // активировать/деактивировать поле при выборе
            if (selected) {
                selectedDishes.add(new SelectedDish(dish, Integer.parseInt(portionField.getText())));
            } else {
                selectedDishes.removeIf(selectedDish -> selectedDish.getDish().equals(dish));
            }
        });

        portionField.textProperty().addListener((obs, oldValue, newValue) -> {
            if (checkBox.isSelected() && !newValue.isEmpty()) {
                selectedDishes.stream()
                        .filter(selectedDish -> selectedDish.getDish().equals(dish))
                        .findFirst()
                        .ifPresent(selectedDish -> selectedDish.setPortionCount(Integer.parseInt(newValue)));
            }
        });

        dishRow.getChildren().addAll(checkBox, nameLabel, priceLabel, portionField);
        dishListVBox.getChildren().add(dishRow);
    }

    @FXML
    private void processOrder() {
        if (selectedDishes.isEmpty()) {
            showWarningDialog("Пожалуйста, выберите блюдо.");
            return;
        }

        showOrderSummary();
    }

    private void showOrderSummary() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Итоговый чек");

        VBox vbox = new VBox(10);
        vbox.setStyle("-fx-padding: 10;");

        Label title = new Label("Ваш заказ:");
        title.setStyle("-fx-font-size: 18px;");

        vbox.getChildren().add(title);

        double total = 0;
        for (SelectedDish selectedDish : selectedDishes) {
            Dish dish = selectedDish.getDish();
            int portionCount = selectedDish.getPortionCount();
            double pricePerPortion = dish.getPrice();
            double totalPriceForDish = pricePerPortion * portionCount;

            Label orderItem = new Label(dish.getName() + " - " + pricePerPortion + " ₽ x " + portionCount + " = " + totalPriceForDish + " ₽");
            vbox.getChildren().add(orderItem);

            total += totalPriceForDish;
        }

        Label totalLabel = new Label("Итого: " + total + " ₽");
        totalLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        vbox.getChildren().add(totalLabel);

        Scene scene = new Scene(vbox, 300, 200);
        stage.setScene(scene);
        stage.show();
    }

    // Метод для показа предупреждения
    private void showWarningDialog(String message) {
        Stage warningStage = new Stage();
        warningStage.initModality(Modality.APPLICATION_MODAL);
        warningStage.setTitle("Предупреждение");

        VBox vbox = new VBox(10);
        vbox.setStyle("-fx-padding: 20;");

        Label warningLabel = new Label(message);
        warningLabel.setStyle("-fx-font-size: 14px;");

        Button okButton = new Button("OK");
        okButton.setOnAction(e -> warningStage.close());

        vbox.getChildren().addAll(warningLabel, okButton);

        Scene scene = new Scene(vbox, 300, 100);
        warningStage.setScene(scene);
        warningStage.show();
    }

    // Класс для хранения выбранных блюд и количества порций
    private static class SelectedDish {
        private final Dish dish;
        private int portionCount;

        public SelectedDish(Dish dish, int portionCount) {
            this.dish = dish;
            this.portionCount = portionCount;
        }

        public Dish getDish() {
            return dish;
        }

        public int getPortionCount() {
            return portionCount;
        }

        public void setPortionCount(int portionCount) {
            this.portionCount = portionCount;
        }
    }
}

