package controller;

import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;


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
    private Spinner spinnerRounds;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
