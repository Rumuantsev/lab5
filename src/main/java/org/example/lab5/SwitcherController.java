package org.example.lab5;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SwitcherController {

    @FXML
    public Button btnToCalculator;
    @FXML
    private TextField textField1;
    @FXML
    private TextField textField2;
    @FXML
    private Button switchButton;

    private boolean isTextInFirstField = true;

    @FXML
    public void switchText() {
        if (isTextInFirstField) {
            // Перемещаем текст из первого поля во второе
            textField2.setText(textField1.getText());
            textField1.clear();
            switchButton.setText("←");
        } else {
            // Перемещаем текст из второго поля в первое
            textField1.setText(textField2.getText());
            textField2.clear();
            switchButton.setText("→");
        }
        isTextInFirstField = !isTextInFirstField;
    }

    @FXML
    private void loadCalculator() throws Exception {
        MainWindow.loadCalculator();  // Переход на второе окно
    }
}
