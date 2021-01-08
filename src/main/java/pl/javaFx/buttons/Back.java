package pl.javaFx.buttons;

import javafx.scene.control.Button;
import javafx.scene.text.Font;


public class Back extends Button {
    public Back () {
        setLayoutX(50);
        setLayoutY(50);
        setText("Powrót");
        setFont(new Font("Arial", 12));
        setMinSize(60, 40);
    }
}
