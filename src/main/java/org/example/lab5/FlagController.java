package org.example.lab5;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;

public class FlagController {

    @FXML
    private ComboBox<String> colorComboBox1;
    @FXML
    private ComboBox<String> colorComboBox2;
    @FXML
    private ComboBox<String> colorComboBox3;

    private final String[] colors = {"Белый", "Красный", "Зеленый", "Голубой", "Желтый"};

    @FXML
    public void initialize() {
        initializeComboBox(colorComboBox1);
        initializeComboBox(colorComboBox2);
        initializeComboBox(colorComboBox3);
    }

    private void initializeComboBox(ComboBox<String> comboBox) {
        comboBox.setItems(FXCollections.observableArrayList(colors));
        comboBox.setValue("Белый"); // Установить белый цвет по умолчанию
    }

    @FXML
    private void showSelectedColors() {
        // Получаем выбранные цвета
        String color1 = colorComboBox1.getValue();
        String color2 = colorComboBox2.getValue();
        String color3 = colorComboBox3.getValue();

        // Формируем строку с выбранными цветами
        String selectedColors = color1 + ", " + color2 + ", " + color3;

        // Выводим модальное окно с сообщением
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Выбранные цвета");
        alert.setHeaderText(null);
        alert.setContentText(selectedColors);

        alert.showAndWait();
    }
}
