package pl.javaFx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ChangeListenerBase implements ChangeListener<String> {

    private Menu menu;

    @Override
    public void changed(ObservableValue observableValue, String o, String t1) {
        menu.setBaseCurrency(t1);
    }

    public ChangeListenerBase(Menu menu) {
        this.menu=menu;
    }
}
