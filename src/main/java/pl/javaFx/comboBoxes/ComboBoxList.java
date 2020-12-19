package pl.javaFx.comboBoxes;

import javafx.scene.control.ComboBox;

public class ComboBoxList extends ComboBox<String> {

    public ComboBoxList(double layoutX, double layoutY, double maxWidth, String[] options,String prompt) {
        setLayoutX(layoutX);
        setLayoutY(layoutY);
        setMaxWidth(maxWidth);
        getItems().addAll(options);
        setPromptText(prompt);

    }
}
