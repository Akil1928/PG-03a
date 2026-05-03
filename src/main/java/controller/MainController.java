package controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.LinkedList;
import model.ListException;
import model.Painter;
import model.Probabilistic;

import java.math.BigInteger;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    // =========================================================================
    // FXML Elements - General
    // =========================================================================
    @FXML
    private TabPane mainTabPane;

    // =========================================================================
    // FXML Elements - Pestaña Miller-Rabin
    // =========================================================================
    @FXML
    private Button btnGenerar;
    @FXML
    private TableColumn colEsPrimo;
    @FXML
    private Button btnMillerRabin;
    @FXML
    private ListView listRegistroOperaciones;
    @FXML
    private Spinner<BigInteger> spinnerRounds;
    @FXML
    private Button btnLimpiarTodo; // Considerar renombrar si es específico de Miller-Rabin
    @FXML
    private Label lblCanvasHint; // Considerar renombrar si es específico de Miller-Rabin
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

    // =========================================================================
    // FXML Elements - Pestaña Random Search
    // =========================================================================
    @FXML
    private ListView listRegistroOperacionesRS;
    @FXML
    private Button btnLimpiarRS;
    @FXML
    private Spinner spinnerMaxAttemptsRS;
    @FXML
    private Label lblCanvasHintRandomSearch;
    @FXML
    private TableView tableRandomSearch;
    @FXML
    private Canvas canvasRandomSearch;
    @FXML
    private TableColumn colMaximoIntentos;
    @FXML
    private TableColumn colIndice;
    @FXML
    private Spinner spinnerValueRS;
    @FXML
    private Button btnRandomSearch;
    @FXML
    private Button btnGenerarAleatorioRS;
    @FXML
    private TableColumn colIntentosRegistrados;
    @FXML
    private TableColumn colValor;
    @FXML
    private Label lblResultadoBusquedaRS;
    @FXML
    private TextArea txtInfoRS;
    @FXML
    private Slider sliderArraySizeRS;
    @FXML
    private Label lblArrayRS;
    @FXML
    private Label lblNoEncontradoRS;

    // =========================================================================
    // FXML Elements - Pestaña Doubly Linked List (DLL)
    // =========================================================================
    @FXML
    private Button btnUltimoDLL;
    @FXML
    private Button btnAgregarDLL;
    @FXML
    private Button btnEliminarInicioDLL;
    @FXML
    private Button btnAnteriorDLL;
    @FXML
    private Button btnSiguienteDLL;
    @FXML
    private TextField txtIdDLL;
    @FXML
    private ComboBox cmbJobPositionDLL;
    @FXML
    private Canvas canvasDoublyLinkedList;
    @FXML
    private TableColumn colNombreDLL;
    @FXML
    private TableView tableDoublyLinkedList;
    @FXML
    private TableColumn colFechaIngresoDLL;
    @FXML
    private Button btnLimpiarTodoDLL;
    @FXML
    private Button btnPrimeroDLL;
    @FXML
    private TableColumn colIdDLL;
    @FXML
    private TextArea txtRepresentacionDLL;
    @FXML
    private Button btnBuscarDLL;
    @FXML
    private Button btnEliminarFinalDLL;
    @FXML
    private TextField txtNameDLL;
    @FXML
    private TableColumn colPuestoDLL;
    @FXML
    private DatePicker dpHireDateDLL;
    @FXML
    private TextArea txtInfoDLL;
    @FXML
    private TextArea txtRegistroOperacionesDLL;

    // =========================================================================
    // FXML Elements - Pestaña Linked List (LL)
    // =========================================================================
    @FXML
    private TextField txtValueLinkedList;
    @FXML
    private TableColumn<Integer, Integer> colElemento; // Columna para el elemento
    @FXML
    private TableColumn<Integer, Integer> colPosicion; // Columna para la posición actual
    // @FXML private TableColumn<LinkedListOperationEntry, String> colSeInserto; // Eliminado: No relevante para vista en vivo
    @FXML
    private Button btnLimpiarLinkedList; // Esto es para limpiar la LinkedList
    @FXML
    private Button btnBuscar; // Esto es para buscar en LinkedList
    @FXML
    private ListView listRegistroOperacionesLL;
    @FXML
    private Button btnAgregarFinal; // Esto es para agregar al final en LinkedList
    @FXML
    private Button btnEliminarDLL; // Este FXML ID parece duplicado, se usa para eliminar en LL
    @FXML
    private TableView<Integer> tableLinkedList; // Especificar tipo para TableView
    @FXML
    private Canvas canvasLinkedList;
    @FXML
    private Button btnEliminar; // Este FXML ID parece duplicado, se usa para eliminar en LL
    @FXML
    private Button btnAgregarInicio; // Esto es para agregar al inicio en LinkedList
    @FXML
    private TextArea txtInfoLL;
    @FXML
    private TextArea txtRepresentacionLL;
    @FXML
    private Label lblResultadoLL;

    // =========================================================================
    // Atributos Internos - Miller-Rabin
    // =========================================================================
    BigInteger min = new BigInteger("0");
    BigInteger max = new BigInteger("1000000000000000000000000000");
    BigInteger initial = new BigInteger("10000000000000000000000");
    BigInteger step = new BigInteger("1");

    // =========================================================================
    // Atributos Internos - LinkedList
    // =========================================================================
    private LinkedList<Integer> linkedList;
    private ObservableList<String> linkedListOperationsLog;
    private ObservableList<Integer> linkedListTableData; // Ahora ObservableList<Integer>

    // =========================================================================
    // Inicialización del Controlador
    // =========================================================================
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupMillerRabin();
        try {
            setupLinkedList();
        } catch (ListException e) {
            showAlert("Error de Inicialización", "No se pudo inicializar la LinkedList: " + e.getMessage());
        }
        // setupRandomSearch(); // Pendiente de implementación
        // setupDoublyLinkedList(); // Pendiente de implementación
    }

    // =========================================================================
    // Métodos de Configuración
    // =========================================================================

    /**
     * Configura los componentes de la pestaña de Miller-Rabin.
     */
    private void setupMillerRabin() {
        // 1. Configuración de la fábrica del Spinner
        spinnerRounds.setValueFactory(new BigIntegerSpinnerValueFactory(min, max, initial, step));
        spinnerRounds.setEditable(true);

        // 2. Funcionalidad del botón Generar número aleatorio
        btnGenerar.setOnAction(actionEvent -> {
            BigInteger numeroAleatorio = generarBigIntegerAleatorio(min, max);
            spinnerRounds.getValueFactory().setValue(numeroAleatorio);
            lblBigInteger.setText(numeroAleatorio.toString());
        });

        // 3. Listener del Spinner para actualizar el Label
        spinnerRounds.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                lblBigInteger.setText(newValue.toString());
            }
        });

        // 4. Botón Miller Rabin para ejecutar la prueba
        btnMillerRabin.setOnAction(actionEvent -> {
            runMillerRabin();
            // reset(1); // Opcional si se desea limpiar el canvas al ejecutar
        });

        // 5. Botón para limpiar el campo del número grande
        btnLimpiarCampo.setOnAction(actionEvent -> lblBigInteger.setText(""));
    }

    /**
     * Configura los componentes de la pestaña de LinkedList.
     */
    private void setupLinkedList() throws ListException {
        linkedList = new LinkedList<>();
        linkedListOperationsLog = FXCollections.observableArrayList();
        listRegistroOperacionesLL.setItems(linkedListOperationsLog);

        // Inicializar linkedListTableData y enlazar a tableLinkedList
        linkedListTableData = FXCollections.observableArrayList();
        tableLinkedList.setItems(linkedListTableData);

        // Configurar las columnas de la tabla para la vista en vivo de la LinkedList
        colElemento.setCellValueFactory(param -> new SimpleIntegerProperty(param.getValue()).asObject());
        colPosicion.setCellValueFactory(param -> new SimpleIntegerProperty(tableLinkedList.getItems().indexOf(param.getValue()) + 1).asObject());
        // colSeInserto ya no es relevante para esta vista y se asume que se ha eliminado del FXML

        // Asignar manejadores de eventos a los botones
        btnAgregarInicio.setOnAction(event -> {
            try {
                handleAddFirstLL();
            } catch (ListException e) {
                showAlert("Error en la lista", e.getMessage());
            }
        });
        btnAgregarFinal.setOnAction(event -> {
            try {
                handleAddLastLL();
            } catch (ListException e) {
                showAlert("Error en la lista", e.getMessage());
            }
        });
        btnEliminar.setOnAction(event -> {
            try {
                handleRemoveLL();
            } catch (ListException e) {
                showAlert("Error en la lista", e.getMessage());
            }
        });
        btnBuscar.setOnAction(event -> {
            try {
                handleSearchLL();
            } catch (ListException e) {
                showAlert("Error en la lista", e.getMessage());
            }
        });
        btnLimpiarLinkedList.setOnAction(event -> {
            try {
                handleClearLL();
            } catch (ListException e) {
                showAlert("Error en la lista", e.getMessage());
            }
        });

        refreshLinkedListView(); // Actualizar la vista inicial de la lista
    }

    // =========================================================================
    // Métodos de Manejo de Eventos - Miller-Rabin
    // =========================================================================

    /**
     * Genera un número BigInteger aleatorio dentro de un rango especificado.
     * @param minimo El valor mínimo (inclusive).
     * @param maximo El valor máximo (inclusive).
     * @return Un BigInteger aleatorio.
     */
    private BigInteger generarBigIntegerAleatorio(BigInteger minimo, BigInteger maximo) {
        Random rnd = new Random();
        BigInteger res;
        do {
            res = new BigInteger(maximo.bitLength(), rnd);
        } while (res.compareTo(minimo) < 0 || res.compareTo(maximo) > 0);
        return res;
    }

    /**
     * Ejecuta la prueba de primalidad de Miller-Rabin y actualiza el registro de operaciones.
     */
    private void runMillerRabin() {
        Probabilistic probabilistic = new Probabilistic();
        String result = probabilistic.millerRabin(lblBigInteger.getText());
        ObservableList<String> items = FXCollections.observableArrayList();
        if (result.contains("is probably prime")) {
            items.add(result + " ✔");
        } else {
            items.add(result + " ❌");
        }
        listRegistroOperaciones.setItems(items);
    }

    // =========================================================================
    // Métodos de Manejo de Eventos - LinkedList
    // =========================================================================

    /**
     * Maneja la acción de agregar un elemento al inicio de la LinkedList.
     */
    private void handleAddFirstLL() throws ListException {
        try {
            Integer value = Integer.parseInt(txtValueLinkedList.getText());
            linkedList.addFirst(value);
            linkedListOperationsLog.add("Agregado " + value + " al inicio. Estado: " + linkedList.toString());
            lblResultadoLL.setText("Último elemento insertado: " + value + " (Inicio)");
            refreshLinkedListView();
        } catch (NumberFormatException e) {
            showAlert("Error de entrada", "Por favor, ingrese un número válido.");
        }
    }

    /**
     * Maneja la acción de agregar un elemento al final de la LinkedList.
     */
    private void handleAddLastLL() throws ListException {
        try {
            Integer value = Integer.parseInt(txtValueLinkedList.getText());
            linkedList.add(value);
            linkedListOperationsLog.add("Agregado " + value + " al final. Estado: " + linkedList.toString());
            lblResultadoLL.setText("Último elemento insertado: " + value + " (Final)");
            refreshLinkedListView();
        } catch (NumberFormatException e) {
            showAlert("Error de entrada", "Por favor, ingrese un número válido.");
        }
    }

    /**
     * Maneja la acción de eliminar un elemento de la LinkedList.
     */
    private void handleRemoveLL() throws ListException {
        try {
            Integer value = Integer.parseInt(txtValueLinkedList.getText());
            if (linkedList.contains(value)) {
                linkedList.remove(value);
                linkedListOperationsLog.add("Eliminado " + value + " de la lista. Estado: " + linkedList.toString());
            } else {
                linkedListOperationsLog.add("Se intentó eliminar " + value + ", pero no se encontró. Estado: " + linkedList.toString());
            }
            refreshLinkedListView();
        } catch (NumberFormatException e) {
            showAlert("Error de entrada", "Por favor, ingrese un número válido.");
        } catch (ListException e) {
            showAlert("Error en la lista", e.getMessage());
        }
    }

    /**
     * Maneja la acción de buscar un elemento en la LinkedList.
     */
    private void handleSearchLL() throws ListException {
        try {
            Integer value = Integer.parseInt(txtValueLinkedList.getText());
            if (linkedList.contains(value)) {
                int index = linkedList.indexOf(value);
                lblResultadoLL.setText("Valor " + value + " encontrado en la posición " + index + ".");
                linkedListOperationsLog.add("Buscado " + value + ": Encontrado en la posición " + index + ". Estado: " + linkedList.toString());
            } else {
                lblResultadoLL.setText("Valor " + value + " no encontrado.");
                linkedListOperationsLog.add("Buscado " + value + ": No encontrado. Estado: " + linkedList.toString());
            }
            refreshLinkedListView();
        } catch (NumberFormatException e) {
            showAlert("Error de entrada", "Por favor, ingrese un número válido.");
            lblResultadoLL.setText("Error: Entrada inválida.");
        } catch (ListException e) {
            showAlert("Error en la lista", e.getMessage());
            lblResultadoLL.setText("Error: " + e.getMessage());
        }
    }

    /**
     * Maneja la acción de limpiar la LinkedList.
     */
    private void handleClearLL() throws ListException {
        linkedList.clear();
        linkedListOperationsLog.add("Lista limpiada. Estado: " + linkedList.toString());
        refreshLinkedListView();
    }

    // =========================================================================
    // Métodos Auxiliares
    // =========================================================================

    /**
     * Actualiza la representación visual de la LinkedList en la interfaz de usuario.
     */
    private void refreshLinkedListView() throws ListException {
        // No es necesario un try-catch aquí para linkedList.size() o isEmpty()
        // ya que size() ahora devuelve 0 y isEmpty() es seguro.
        txtRepresentacionLL.setText(linkedList.toString());
        txtInfoLL.setText("Tamaño: " + linkedList.size() + "\nVacía: " + linkedList.isEmpty());
        // lblResultadoLL.setText(""); // Esta línea se eliminó para permitir que lblResultadoLL persista mensajes de inserción

        // Actualizar tableLinkedList (vista en vivo)
        linkedListTableData.clear();
        if (!linkedList.isEmpty()) { // isEmpty() es seguro
            for (int i = 1; i <= linkedList.size(); i++) { // size() es seguro
                try {
                    linkedListTableData.add(linkedList.get(i)); // get(i) puede lanzar ListException
                } catch (ListException e) {
                    System.err.println("Error al obtener elemento para tableLinkedList: " + e.getMessage());
                    // Manejar el error, por ejemplo, mostrando una alerta o registrándolo
                }
            }
        }

        // Actualizar canvasLinkedList
        try {
            if (linkedList.isEmpty()) { // isEmpty() es seguro
                Painter.paintEmpty(canvasLinkedList, "Lista Vacía");
            } else {
                Painter.paintLinkedList(canvasLinkedList, linkedList); // paintLinkedList puede lanzar ListException
            }
        } catch (ListException e) {
            System.err.println("Error al pintar la LinkedList: " + e.getMessage());
            Painter.paintEmpty(canvasLinkedList, "Error al cargar la representación gráfica");
        }
    }

    /**
     * Muestra una alerta de error al usuario.
     * @param title El título de la alerta.
     * @param message El mensaje de la alerta.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Reinicia los componentes de una pestaña específica.
     * @param index El índice de la pestaña a reiniciar (1 para Miller-Rabin).
     */
    private void reset(int index) {
        switch (index) {
            case 1: // Pestaña Miller-Rabin
                Painter.paintEmpty(canvasMillerRabin, "Presione Miller Rabin para comenzar");
                listRegistroOperaciones.getItems().clear();
                break;
            case 2: // Pestaña Random Search
                // Lógica de reinicio para Random Search
                break;
            case 3: // Pestaña Doubly Linked List
                // Lógica de reinicio para Doubly Linked List
                break;
            case 4: // Pestaña Linked List
                // Lógica de reinicio para Linked List
                break;
        }
    }

    // =========================================================================
    // Clases Internas
    // =========================================================================

    /**
     * Fábrica de valores para un Spinner que maneja objetos BigInteger.
     */
    public static class BigIntegerSpinnerValueFactory extends SpinnerValueFactory<BigInteger> {
        private final BigInteger step;

        public BigIntegerSpinnerValueFactory(BigInteger min, BigInteger max, BigInteger initialValue, BigInteger step) {
            this.step = step;
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
