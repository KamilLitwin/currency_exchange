package pl.javaFx;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pl.api.CurrentService;
import pl.exception.CustomException;



public class Menu extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private String baseCurrency;
    private String exchangeCurrency;

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public void setExchangeCurrency(String exchangeCurrency) {
        this.exchangeCurrency = exchangeCurrency;
    }

    @Override
    public void start(Stage primaryStage) {

        Button oneToOne = new Button("Sprawdź kurs wymiany waluty na inną");
        oneToOne.shapeProperty();
        oneToOne.setLayoutX(50);
        oneToOne.setLayoutY(50);
        oneToOne.wrapTextProperty();
        oneToOne.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CurrentService currentService = new CurrentService();

                String [] options = {"PLN","EUR","USD"};
                ComboBox<String> baseCB = new ComboBox<String>();
                baseCB.getItems().addAll(options);
                baseCB.setLayoutX(100);
                baseCB.setLayoutY(250);
                baseCB.setMaxWidth(100);
                baseCB.getSelectionModel().selectedItemProperty().addListener(new ChangeListenerBase(Menu.this));// odwołujemy się do obiektu nadrzędnego,
                // przekazujemy instancje menu w którym jesteśmy

                ComboBox<String> exchangeCB = new ComboBox<String>();
                exchangeCB.getItems().addAll(options);
                exchangeCB.setLayoutX(200);
                exchangeCB.setLayoutY(250);
                exchangeCB.setMaxWidth(100);
                exchangeCB.getSelectionModel().selectedItemProperty().addListener(
                        new ChangeListener<String>(){
                            public void changed(ObservableValue<? extends String> ov,
                                                String old_val, String new_val) {
                                exchangeCurrency = new_val;
                            }
                        });

                TextArea result = new TextArea();
                result.setLayoutX(450);
                result.setLayoutY(250);
                result.setMaxHeight(10);
                result.setMaxWidth(300);
                result.isWrapText();

                Button change = new Button("Wymień");
                change.setLayoutX(300);
                change.setLayoutY(250);
                change.setOnAction(actionEventoTo -> {
                    try {
                        String result2 = currentService.currencyExchangeCountries(baseCurrency, exchangeCurrency);
                        result.setText(result2);
                    } catch (CustomException e) {
                        e.printStackTrace();
                    }
                });

                Group root = new Group();
                root.getChildren().add(exchangeCB);
                root.getChildren().add(result);
                root.getChildren().add(change);
                root.getChildren().add(baseCB);
                Scene scene2 = new Scene(root,800,600, Color.DARKGREEN);
                Stage oneToOneStage = new Stage();
                oneToOneStage.setScene(scene2);
                oneToOneStage.setTitle("Currency Exchange");
                oneToOneStage.show();
                primaryStage.close();
            }
        });


        Group root = new Group();
        root.getChildren().add(oneToOne);

        Scene scene = new Scene(root,800,600, Color.DARKGREEN);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Currency Exchange");
        primaryStage.show();

    }
}
