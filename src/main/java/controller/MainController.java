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

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.cell.PropertyValueFactory;
import model.DoublyLinkedList;
import model.Employee;
import model.ListException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    // TAB DLL - Atributos internos
    private DoublyLinkedList<Employee> dll = new DoublyLinkedList<>();
    private Employee currentEmployee = null; //empleado seleccionado por navegación
    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");


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
    private Button btnUltimoDLL;
    @FXML
    private TextField txtValueLinkedList;
    @FXML
    private Button btnAgregarDLL;
    @FXML
    private Button btnEliminarInicioDLL;
    @FXML
    private Button btnAnteriorDLL;
    @FXML
    private TableColumn colElemento;
    @FXML
    private Button btnSiguienteDLL;
    @FXML
    private TextField txtIdDLL;
    @FXML
    private ComboBox cmbJobPositionDLL;
    @FXML
    private Canvas canvasDoublyLinkedList;
    @FXML
    private TableColumn <Employee, String> colNombreDLL;
    @FXML
    private Button btnLimpiarLinkedList;
    @FXML
    private TableColumn colSeInserto;
    @FXML
    private TableView <Employee> tableDoublyLinkedList;
    @FXML
    private Button btnBuscar;
    @FXML
    private ListView listRegistroOperacionesLL;
    @FXML
    private TableColumn <Employee, String> colFechaIngresoDLL;
    @FXML
    private Button btnLimpiarTodoDLL;
    @FXML
    private Button btnPrimeroDLL;
    @FXML
    private TableColumn <Employee, String> colIdDLL;
    @FXML
    private TextArea txtRepresentacionDLL;
    @FXML
    private Button btnBuscarDLL;
    @FXML
    private Button btnAgregarFinal;
    @FXML
    private Button btnEliminarDLL;
    @FXML
    private TableView tableLinkedList;
    @FXML
    private Button btnEliminarFinalDLL;
    @FXML
    private Canvas canvasLinkedList;
    @FXML
    private Button btnEliminar;
    @FXML
    private TableColumn colPosicion;
    @FXML
    private TextField txtNameDLL;
    @FXML
    private Button btnAgregarInicio;
    @FXML
    private TableColumn <Employee, String> colPuestoDLL;
    @FXML
    private DatePicker dpHireDateDLL;
    @FXML
    private Label lblResultadoBusquedaRS;
    @FXML
    private TextArea txtInfoLL;
    @FXML
    private TextArea txtRepresentacionLL;
    @FXML
    private Label lblResultadoLL;
    @FXML
    private TextArea txtInfoRS;
    @FXML
    private Slider sliderArraySizeRS;
    @FXML
    private TextArea txtInfoDLL;
    @FXML
    private Label lblArrayRS;
    @FXML
    private Label lblNoEncontradoRS;
    @FXML
    private TextArea txtRegistroOperacionesDLL;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupMillerRabin();
        setupDoublyLinkedList();
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

    private void setupDoublyLinkedList() {

        //1.ComboBox de puestos
        cmbJobPositionDLL.getItems().addAll(
                "Choose", "Docente", "Informática/e", "Arquitectura/e",
                "Medicina", "Ingeniería", "Administración", "Recursos Humanos"
        );
        cmbJobPositionDLL.setValue("Choose");

        //2.Columnas de la tabla
        colIdDLL.setCellValueFactory(data ->
                new SimpleStringProperty(((Employee) data.getValue()).getId()));
        colNombreDLL.setCellValueFactory(data ->
                new SimpleStringProperty(((Employee) data.getValue()).getName()));
        colPuestoDLL.setCellValueFactory(data ->
                new SimpleStringProperty(((Employee) data.getValue()).getJobPosition()));
        colFechaIngresoDLL.setCellValueFactory(data ->
                new SimpleStringProperty(
                        ((Employee) data.getValue()).getHireDate() != null
                                ? ((Employee) data.getValue()).getHireDate().format(DATE_FMT)
                                : ""));

        //3.Botón Agregar
        btnAgregarDLL.setOnAction(e -> {
            String id   = txtIdDLL.getText().trim();
            String name = txtNameDLL.getText().trim();
            String job  = (String) cmbJobPositionDLL.getValue();
            LocalDate date = dpHireDateDLL.getValue();

            if (id.isEmpty() || name.isEmpty() || date == null || "Choose".equals(job)) {
                appendLogDLL("⚠ Complete todos los campos antes de agregar.");
                return;
            }
            Employee emp = new Employee(id, name, job, date);
            dll.add(emp);
            appendLogDLL("✔ Agregado: " + emp);
            refreshTableDLL();
            refreshCanvasDLL();
            clearFormDLL();
        });

        //4.Botón Buscar (por ID)
        btnBuscarDLL.setOnAction(e -> {
            String id = txtIdDLL.getText().trim();
            if (id.isEmpty()) {
                appendLogDLL("⚠ Ingrese un ID para buscar.");
                return;
            }
            try {
                Employee found = findById(id);
                if (found != null) {
                    currentEmployee = found;
                    fillFormDLL(found);
                    appendLogDLL("🔍 Encontrado: " + found);
                } else {
                    appendLogDLL("✖ No se encontró empleado con ID: " + id);
                }
            } catch (ListException ex) {
                appendLogDLL("✖ Error: " + ex.getMessage());
            }
        });

        //5.Botón Eliminar (por ID)
        btnEliminarDLL.setOnAction(e -> {
            String id = txtIdDLL.getText().trim();
            if (id.isEmpty()) { appendLogDLL("⚠ Ingrese un ID para eliminar."); return; }
            try {
                Employee found = findById(id);
                if (found != null) {
                    dll.remove(found);
                    appendLogDLL("🗑 Eliminado: " + found);
                    currentEmployee = null;
                    refreshTableDLL();
                    refreshCanvasDLL();
                    clearFormDLL();
                } else {
                    appendLogDLL("✖ No existe empleado con ID: " + id);
                }
            } catch (ListException ex) {
                appendLogDLL("✖ Error: " + ex.getMessage());
            }
        });

        //6.Eliminar Inicio
        btnEliminarInicioDLL.setOnAction(e -> {
            try {
                Employee removed = (Employee) dll.removeFirst();
                appendLogDLL("🗑 Eliminado del inicio: " + removed);
                currentEmployee = null;
                refreshTableDLL();
                refreshCanvasDLL();
                clearFormDLL();
            } catch (ListException ex) {
                appendLogDLL("✖ " + ex.getMessage());
            }
        });

        //7.Eliminar Final
        btnEliminarFinalDLL.setOnAction(e -> {
            try {
                Employee removed = (Employee) dll.removeLast();
                appendLogDLL("🗑 Eliminado del final: " + removed);
                currentEmployee = null;
                refreshTableDLL();
                refreshCanvasDLL();
                clearFormDLL();
            } catch (ListException ex) {
                appendLogDLL("✖ " + ex.getMessage());
            }
        });

        //8.Primero
        btnPrimeroDLL.setOnAction(e -> {
            try {
                currentEmployee = (Employee) dll.getFirst();
                fillFormDLL(currentEmployee);
                appendLogDLL("⏮ Primero: " + currentEmployee);
            } catch (ListException ex) {
                appendLogDLL("✖ " + ex.getMessage());
            }
        });

        //9.Último
        btnUltimoDLL.setOnAction(e -> {
            try {
                currentEmployee = (Employee) dll.getLast();
                fillFormDLL(currentEmployee);
                appendLogDLL("⏭ Último: " + currentEmployee);
            } catch (ListException ex) {
                appendLogDLL("✖ " + ex.getMessage());
            }
        });

        //10.Anterior
        btnAnteriorDLL.setOnAction(e -> {
            if (currentEmployee == null) {
                appendLogDLL("⚠ Primero seleccione un empleado.");
                return;
            }
            try {
                Employee prev = (Employee) dll.getPrev(currentEmployee);
                if (prev != null) {
                    currentEmployee = prev;
                    fillFormDLL(currentEmployee);
                    appendLogDLL("◀ Anterior: " + currentEmployee);
                } else {
                    appendLogDLL("⚠ Ya estás en el primer elemento.");
                }
            } catch (ListException ex) {
                appendLogDLL("✖ " + ex.getMessage());
            }
        });

        //11.Siguiente
        btnSiguienteDLL.setOnAction(e -> {
            if (currentEmployee == null) {
                appendLogDLL("⚠ Primero seleccione un empleado.");
                return;
            }
            try {
                Employee next = (Employee) dll.getNext(currentEmployee);
                if (next != null) {
                    currentEmployee = next;
                    fillFormDLL(currentEmployee);
                    appendLogDLL("▶ Siguiente: " + currentEmployee);
                } else {
                    appendLogDLL("⚠ Ya estás en el último elemento.");
                }
            } catch (ListException ex) {
                appendLogDLL("✖ " + ex.getMessage());
            }
        });

        //12.Limpiar Todo
        btnLimpiarTodoDLL.setOnAction(e -> {
            dll.clear();
            currentEmployee = null;
            tableDoublyLinkedList.getItems().clear();
            txtRepresentacionDLL.setText("NULL ↔ HEAD ↔ NULL");
            txtRegistroOperacionesDLL.clear();
            clearFormDLL();
            refreshCanvasDLL();
            appendLogDLL("↺ Lista limpiada.");
        });

        //esto es solo permite números en el campo ID
        txtIdDLL.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*")) {
                txtIdDLL.setText(newVal.replaceAll("[^\\d]", ""));
            }
        });
    }

//Helpers DLL
    private void refreshTableDLL() {
        ObservableList<Employee> items = FXCollections.observableArrayList();
        try {
            if (!dll.isEmpty()) {
                int size = dll.size();
                for (int i = 1; i <= size; i++) {
                    items.add((Employee) dll.get(i));
                }
            }
        } catch (ListException ex) { /* lista vacía */ }
        tableDoublyLinkedList.setItems(items);

        try {
            txtRepresentacionDLL.setText(dll.isEmpty() ? "NULL ↔ HEAD ↔ NULL" : dll.toString());
        } catch (Exception ignored) {}
    }

    private void refreshCanvasDLL() {
        javafx.scene.canvas.GraphicsContext gc = canvasDoublyLinkedList.getGraphicsContext2D();
        double w = canvasDoublyLinkedList.getWidth();
        double h = canvasDoublyLinkedList.getHeight();
        gc.clearRect(0, 0, w, h);

        if (dll.isEmpty()) {
            gc.setFill(javafx.scene.paint.Color.web("#8896A5"));
            gc.setFont(javafx.scene.text.Font.font("Courier New", 13));
            gc.fillText("NULL ↔ HEAD ↔ NULL", 20, h / 2);
            return;
        }

        try {
            int size = dll.size();
            double nodeW = 90, nodeH = 44, gap = 28;
            double totalW = size * nodeW + (size - 1) * gap;
            //expande el canvas si hace falta
            if (totalW + 40 > w) {
                canvasDoublyLinkedList.setWidth(totalW + 40);
                w = canvasDoublyLinkedList.getWidth();
            }
            double startX = 20, y = (h - nodeH) / 2;

            for (int i = 1; i <= size; i++) {
                Employee emp = (Employee) dll.get(i);
                double x = startX + (i - 1) * (nodeW + gap);

                //caja del nodo
                boolean isCurrent = emp.equals(currentEmployee);
                gc.setFill(isCurrent
                        ? javafx.scene.paint.Color.web("#1A8C7B")
                        : javafx.scene.paint.Color.web("#1F3868"));
                gc.fillRoundRect(x, y, nodeW, nodeH, 8, 8);

                //Texto: ID + nombre
                gc.setFill(javafx.scene.paint.Color.WHITE);
                gc.setFont(javafx.scene.text.Font.font("Courier New", 10));
                String label = emp.getId();
                String name  = emp.getName().length() > 10
                        ? emp.getName().substring(0, 10) + "…"
                        : emp.getName();
                gc.fillText(label, x + 8, y + 16);
                gc.fillText(name,  x + 8, y + 30);

                //Flecha ↔ entre nodos
                if (i < size) {
                    double ax = x + nodeW + 2;
                    double ay = y + nodeH / 2;
                    gc.setStroke(javafx.scene.paint.Color.web("#4A90D9"));
                    gc.setLineWidth(1.5);
                    gc.strokeLine(ax, ay, ax + gap - 4, ay);
                    //Punta derecha →
                    gc.strokeLine(ax + gap - 4, ay, ax + gap - 10, ay - 5);
                    gc.strokeLine(ax + gap - 4, ay, ax + gap - 10, ay + 5);
                    //Punta izquierda ←
                    gc.strokeLine(ax, ay, ax + 6, ay - 5);
                    gc.strokeLine(ax, ay, ax + 6, ay + 5);
                }
            }

            //etiquetas HEAD / TAIL
            gc.setFill(javafx.scene.paint.Color.web("#4A90D9"));
            gc.setFont(javafx.scene.text.Font.font("Courier New", javafx.scene.text.FontWeight.BOLD, 10));
            gc.fillText("HEAD", startX, y - 6);
            double lastX = startX + (size - 1) * (nodeW + gap);
            gc.fillText("TAIL", lastX + nodeW - 30, y - 6);

        } catch (ListException ex) { /* no dibuja */ }
    }

    private void fillFormDLL(Employee emp) {
        txtIdDLL.setText(emp.getId());
        txtNameDLL.setText(emp.getName());
        cmbJobPositionDLL.setValue(emp.getJobPosition());
        dpHireDateDLL.setValue(emp.getHireDate());
    }

    private void clearFormDLL() {
        txtIdDLL.clear();
        txtNameDLL.clear();
        cmbJobPositionDLL.setValue("Choose");
        dpHireDateDLL.setValue(null);
    }

    private void appendLogDLL(String msg) {
        String current = txtRegistroOperacionesDLL.getText();
        String ts = java.time.LocalTime.now().format(
                java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));
        txtRegistroOperacionesDLL.setText(
                (current.isEmpty() ? "" : current + "\n") + "[" + ts + "] " + msg);
    }

    private Employee findById(String id) throws ListException {
        if (dll.isEmpty()) return null;
        int size = dll.size();
        for (int i = 1; i <= size; i++) {
            Employee e = (Employee) dll.get(i);
            if (e.getId().equals(id)) return e;
        }
        return null;
    }
}
