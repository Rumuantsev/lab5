package org.example.lab5;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;

public class CheckboxerController {
    @FXML
    private ImageView image1;
    @FXML
    private ImageView image2;
    @FXML
    private ImageView image3;
    @FXML
    private CheckBox checkbox1;
    @FXML
    private CheckBox checkbox2;
    @FXML
    private CheckBox checkbox3;

    public void handleCheckboxAction(ActionEvent event) {
        // Определяем, какой чекбокс был нажат
        CheckBox selectedCheckbox = (CheckBox) event.getSource();

        // Связываем чекбокс с соответствующим изображением
        if (selectedCheckbox.equals(checkbox1)) {
            image1.setVisible(selectedCheckbox.isSelected());
        } else if (selectedCheckbox.equals(checkbox2)) {
            image2.setVisible(selectedCheckbox.isSelected());
        } else if (selectedCheckbox.equals(checkbox3)) {
            image3.setVisible(selectedCheckbox.isSelected());
        }
    }
}
