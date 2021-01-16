package pl.javaFx.textAreas;

import javafx.scene.control.TextArea;

public class ResultTA extends TextArea {

    public ResultTA(double layoutX, double layoutY,double maxHeight ,double maxWidth) {
        setLayoutX(layoutX);
        setLayoutY(layoutY);
        setMaxHeight(maxHeight);
        setMaxWidth(maxWidth);
        isWrapText();
    }
}
