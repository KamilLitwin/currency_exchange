package pl.javaFx;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pl.api.CurrentService;
import pl.exception.CustomException;
import pl.gson.CurrencyDto;
import pl.javaFx.buttons.Back;
import pl.javaFx.buttons.Change;
import pl.javaFx.buttons.Print;
import pl.javaFx.comboBoxes.ChangeListenerBase;
import pl.javaFx.comboBoxes.ChangeListenerEx;
import pl.javaFx.comboBoxes.ComboBoxList;
import pl.javaFx.textAreas.ResultTA;

public class Menu extends Application {

    private String baseCurrency;
    private String exchangeCurrency;
    CurrentService currentService = new CurrentService();
    String[] options = {"PLN", "EUR", "USD", "GBP"};

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public void setExchangeCurrency(String exchangeCurrency) {
        this.exchangeCurrency = exchangeCurrency;
    }

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {

        Button oneToOne = new Button("Sprawdź kurs wymiany waluty na inną");
        oneToOne.setLayoutX(50);
        oneToOne.setLayoutY(50);
        oneToOne.setMinSize(300, 200);
        oneToOne.setFont(new Font("Arial", 16));
        oneToOne.setOnAction(actionEvent -> {

            ComboBox<String> baseCB = new ComboBoxList(50, 250, 150, options,"Waluta bazowa");
            baseCB.getSelectionModel().selectedItemProperty().addListener(new ChangeListenerBase(Menu.this));// odwołujemy się do obiektu nadrzędnego,
            // przekazujemy instancje menu w którym jesteśmy

            ComboBox<String> exchangeCB = new ComboBoxList(200, 250, 150, options,"Waluta wymiany");
            exchangeCB.getSelectionModel().selectedItemProperty().addListener(new ChangeListenerEx(Menu.this));

            TextArea result = new ResultTA(450, 250, 15, 150);

            Button change = new Change(350, 250);
            change.setOnAction(actionEventoTo -> {
                try {
                    CurrencyDto currencyDto3 = currentService.parseDto3(baseCurrency,exchangeCurrency);
                    result.setText(""+ currencyDto3.getRates());
                } catch (CustomException e) {
                    e.printStackTrace();
                }
            });

            Group root = new Group();
            root.getChildren().add(exchangeCB);
            root.getChildren().add(result);
            root.getChildren().add(change);
            root.getChildren().add(baseCB);
            Scene scene2 = new Scene(root, 800, 600, Color.DARKGREEN);
            Stage oneToOneStage = new Stage();
            oneToOneStage.setScene(scene2);
            oneToOneStage.setTitle("Currency Exchange");
            oneToOneStage.show();
            primaryStage.close();

            Button back = new Back();
            back.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    primaryStage.show();
                    oneToOneStage.close();
                }
            });
            root.getChildren().add(back);
        });

        Button euroToMany = new Button("Wyświetl kursy walut dla euro");
        euroToMany.setLayoutX(400);
        euroToMany.setLayoutY(50);
        euroToMany.setMinSize(300, 200);
        euroToMany.setFont(new Font("Arial", 16));
        euroToMany.setOnAction(actionEvent -> {
            Label label = new Label("Euro");
            label.setFont(new Font("Arial", 100));
            label.setLayoutX(250);
            label.setLayoutY(100);
            TextArea currenciesValue = new ResultTA(200, 200, 600, 400);
            currenciesValue.setWrapText(true);
            try {
                currenciesValue.setText(currentService.returnCurrencies());
            } catch (CustomException e) {
                e.printStackTrace();
            }

            Group root = new Group();
            root.getChildren().add(currenciesValue);
            root.getChildren().add(label);
            Scene scene2 = new Scene(root, 800, 600, Color.DARKGREEN);
            Stage euroToManyStage = new Stage();
            euroToManyStage.setScene(scene2);
            euroToManyStage.setTitle("Kurs Euro");
            euroToManyStage.show();
            primaryStage.close();

            Button back = new Back();
            back.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    primaryStage.show();
                    euroToManyStage.close();
                }
            });
            root.getChildren().add(back);
        });

        Button oneToAll = new Button("Wyświetl wszystkie waluty dla wybranej");
        oneToAll.setLayoutX(50);
        oneToAll.setLayoutY(350);
        oneToAll.setMinSize(300, 200);
        oneToAll.setFont(new Font("Arial", 16));
        oneToAll.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ComboBox<String> base = new ComboBoxList(550, 150, 150, options, "Bazowa waluta");
                base.getSelectionModel().selectedItemProperty().addListener(new ChangeListenerBase(Menu.this));

                TextArea currenciesValue = new ResultTA(100, 200, 800, 800);
                currenciesValue.setWrapText(true);
                currenciesValue.setPrefWidth(600);

                TextField dateFrom = new TextField();
                dateFrom.setPromptText("Pokaż od yyyy-MM-dd");
                dateFrom.setLayoutX(150);
                dateFrom.setLayoutY(150);

                TextField dateTo = new TextField();
                dateTo.setPromptText("Pokaż do yyyy-MM-dd");
                dateTo.setLayoutX(350);
                dateTo.setLayoutY(150);

                Button change = new Change(400, 50);
                change.setOnAction(actionEventoTo -> {
                    try {
                        currenciesValue.setText(currentService.ratesHistorical(baseCurrency
                                ,dateFrom.getText()
                                ,dateTo.getText()));
                    } catch (CustomException e) {
                        e.printStackTrace();
                    }
                });
                Group root = new Group();
                root.getChildren().add(currenciesValue);
                root.getChildren().add(dateFrom);
                root.getChildren().add(dateTo);
                root.getChildren().add(change);
                root.getChildren().add(base);

                Scene scene2 = new Scene(root, 800, 600, Color.DARKGREEN);
                Stage oneToAllStage = new Stage();
                oneToAllStage.setScene(scene2);
                oneToAllStage.setTitle("Kursy wybranej waluty");
                oneToAllStage.show();
                primaryStage.close();

                Button back = new Back();
                back.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        primaryStage.show();
                        oneToAllStage.close();
                    }
                });
                root.getChildren().add(back);
            }
        });

        Button print = new Print();
        print.setLayoutY(350);
        print.setLayoutX(400);

        Group root = new Group();
        root.getChildren().add(oneToOne);
        root.getChildren().add(euroToMany);
        root.getChildren().add(oneToAll);
        root.getChildren().add(print);
        Scene scene = new Scene(root, 800, 600, Color.DARKGREEN);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Currency Exchange");
        primaryStage.show();

    }
}
// pobieranie z api informacji i wyświetlenie na konsoli ( w postaci label) - napisać zapytanie w hibernate
// select CUR_BASE_CURRENCY from currency;