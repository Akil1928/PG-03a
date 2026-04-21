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
        // 1. Configuración de la fábrica (clase interna detallada abajo)
        spinnerRounds.setValueFactory(new BigIntegerSpinnerValueFactory(min, max, initial, step));
        spinnerRounds.setEditable(true);

        // 2. Funcionalidad del botón Generar
        btnGenerar.setOnAction(actionEvent -> {
            BigInteger numeroAleatorio = generarBigIntegerAleatorio(min, max);
            spinnerRounds.getValueFactory().setValue(numeroAleatorio);
            // Actualizamos el label de una vez para que se vea el cambio
            lblBigInteger.setText(numeroAleatorio.toString());
        });

        // 3. Listener del Spinner
        spinnerRounds.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                lblBigInteger.setText(newValue.toString());
            }
        });

        // 4. Botón Miller Rabin (Agrupando lógica para evitar que se pisen)
        btnMillerRabin.setOnAction(actionEvent -> {
            runMillerRabin();
            // reset(1); // Opcional si quieres limpiar canvas al ejecutar
        });

        btnLimpiarCampo.setOnAction(actionEvent -> lblBigInteger.setText(""));
    }

    private BigInteger generarBigIntegerAleatorio(BigInteger minimo, BigInteger maximo) {
        Random rnd = new Random();
        BigInteger res;
        do {
            //generamos un número con la cantidad de bits necesaria para el máximo
            res = new BigInteger(maximo.bitLength(), rnd);
        } while (res.compareTo(minimo) < 0 || res.compareTo(maximo) > 0);

        return res;
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
    //agrega esto al final de tu MainController o como clase interna
    public static class BigIntegerSpinnerValueFactory extends SpinnerValueFactory<BigInteger> {
        private final BigInteger step;

        public BigIntegerSpinnerValueFactory(BigInteger min, BigInteger max, BigInteger initialValue, BigInteger step) {
            this.step = step;
            //definimos los límites
            this.setValue(initialValue);

            this.valueProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal.compareTo(min) < 0) setValue(min);
                if (newVal.compareTo(max) > 0) setValue(max);
            });
        }

        @Override
        public void decrement(int steps) {
            BigInteger newValue = getValue().subtract(step.multiply(BigInteger.valueOf(steps)));
            setValue(newValue);
        }

        @Override
        public void increment(int steps) {
            BigInteger newValue = getValue().add(step.multiply(BigInteger.valueOf(steps)));
            setValue(newValue);
        }
    }
}
