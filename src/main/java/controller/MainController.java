package controller;

import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import model.Painter;
import model.Probabilistic;


import java.math.BigInteger;
import java.net.URL;
import java.util.*;

public class MainController implements Initializable {

    @FXML
    private TabPane mainTabPane;
    @FXML
    private Button btnGenerar;
    @FXML
    private TableColumn colEsPrimo;
    @FXML
    private Button btnMillerRabin;
    @FXML
    private ListView listRegistroOperaciones;
    @FXML
    private Spinner <BigInteger> spinnerRounds;
    @FXML
    private Button btnLimpiarTodo;
    @FXML
    private Label lblCanvasHint;
    @FXML
    private TableView tableMillerRabin;
    @FXML
    private Canvas canvasMillerRabin;
    @FXML
    private Button btnLimpiarCampo;
    @FXML
    private TableColumn colBigNumber;
    @FXML
    private Label lblBigInteger;


    //TAB 1 - Atributos Internos
    BigInteger min = new BigInteger("0");
    BigInteger max = new BigInteger("1000000000000000000000000000");
    BigInteger initial = new BigInteger("10000000000000000000000");
    BigInteger step = new BigInteger("1");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupMillerRabin();

    }

    private void setupMillerRabin() {
        //spinnerRounds.setValueFactory(new BigIntegerSpinnerValueFactory(min, max, initial, step));

        spinnerRounds.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                lblBigInteger.setText("Número de rondas: " + newValue.toString());
            }
        });

        btnMillerRabin.setOnAction(actionEvent -> runMillerRabin());
        btnMillerRabin.setOnAction(actionEvent -> reset(1));
        btnLimpiarCampo.setOnAction(actionEvent -> {lblBigInteger.setText("");});

    }

    private void runMillerRabin() {
        Probabilistic probabilistic = new Probabilistic();
        String result = probabilistic.millerRabin(lblBigInteger.getText());
        ObservableList<String> items = FXCollections.observableArrayList();
        if(result.contains("is probably prime")) {
            //tenemos que agregarlo al table view
            items.add(result+" ✔");
        }else{
            items.add(result+" ❌");
        }
        listRegistroOperaciones.setItems(items);
    }


private void reset(int index) {
        switch (index) {
            case 1: //Tab 1
                Painter.paintEmpty(canvasMillerRabin,"Presione Miller Rabin para comenzar");
                listRegistroOperaciones.getItems().clear();
                break;
            case 2:
                break;
        }
}
}
