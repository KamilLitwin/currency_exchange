package pl.javaFx.buttons;

import javafx.scene.control.Button;

public class Change extends Button {
    public Change(double layoutX, double layoutY) {
        setLayoutX(layoutX);
        setLayoutY(layoutY);
        setText("Wymień");
    }
}
