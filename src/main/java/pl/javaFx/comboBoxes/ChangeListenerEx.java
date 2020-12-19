package pl.javaFx.comboBoxes;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import pl.javaFx.Menu;

public class ChangeListenerEx implements ChangeListener<String> {
    private Menu menu;

    @Override
    public void changed(ObservableValue observableValue, String o, String t1) {
        menu.setExchangeCurrency(t1);
    }

    public ChangeListenerEx(Menu menu) {
        this.menu=menu;
    }
}
