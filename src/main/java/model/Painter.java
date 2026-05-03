package model;

import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

/**
 * Dibuja el arreglo como barras verticales en un Canvas.
 * Colorea según el estado de cada celda en el paso actual.
 */
public class Painter {
    //javi
    // ── Paleta UCR ────────────────────────────────────────────────────────────
    private static final Color COL_DEFAULT   = Color.web("#1F3868");   // azul UCR
    private static final Color COL_ACTIVE    = Color.web("#E74C3C");   // rojo – comparando ahora
    private static final Color COL_RANGE_LO  = Color.web("#4A90D9");   // azul claro – dentro del rango
    private static final Color COL_FOUND     = Color.web("#1A8C7B");   // verde – encontrado
    private static final Color COL_VISITED   = Color.web("#8896A5");   // gris – ya visitado
    private static final Color COL_OUT       = Color.web("#D0D6E0");   // muy claro – fuera del rango
    private static final Color COL_TEXT      = Color.WHITE;
    private static final Color COL_IDX       = Color.web("#8896A5");
    private static final Color COL_BG        = Color.web("#F4F6FA");
    private static final Color COL_POINTER   = Color.web("#E8A020");   // ámbar UCR – puntero

    /** Estado de pintura para una celda. */
    public enum CellState {
        DEFAULT, ACTIVE, IN_RANGE, FOUND, VISITED, OUT_OF_RANGE
    }


    public static void paintEmpty(Canvas canvas, String message) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double W = canvas.getWidth(), H = canvas.getHeight();
        gc.setFill(COL_BG);
        gc.fillRect(0, 0, W, H);
        gc.setFill(Color.web("#8896A5"));
        gc.setFont(Font.font("Calibri", FontWeight.NORMAL, 14));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.fillText(message, W / 2, H / 2);
    }

    /**
     * Dibuja una representación gráfica de una LinkedList en el Canvas.
     * @param canvas El Canvas donde dibujar.
     * @param list La LinkedList a dibujar.
     * @throws ListException Si hay un error al acceder a los elementos de la lista.
     */
    public static void paintLinkedList(Canvas canvas, LinkedList<Integer> list) throws ListException {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double W = canvas.getWidth();
        double H = canvas.getHeight();

        // Limpiar canvas
        gc.setFill(COL_BG);
        gc.fillRect(0, 0, W, H);

        if (list.isEmpty()) {
            paintEmpty(canvas, "Lista Vacía");
            return;
        }

        final double NODE_WIDTH = 60;
        final double NODE_HEIGHT = 30;
        final double NODE_SPACING_X = 80; // Espacio entre el inicio de un nodo y el inicio del siguiente
        final double START_X = 20;
        final double START_Y = H / 2 - NODE_HEIGHT / 2; // Centrar verticalmente

        gc.setFont(Font.font("Calibri", FontWeight.NORMAL, 12));
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER); // Para centrado vertical del texto

        int listSize = list.size();
        for (int i = 1; i <= listSize; i++) {
            double nodeX = START_X + (i - 1) * NODE_SPACING_X;
            double nodeY = START_Y;

            // Dibujar rectángulo del nodo
            gc.setFill(COL_DEFAULT);
            gc.fillRect(nodeX, nodeY, NODE_WIDTH, NODE_HEIGHT);
            gc.setStroke(COL_TEXT);
            gc.strokeRect(nodeX, nodeY, NODE_WIDTH, NODE_HEIGHT);

            // Dibujar datos del nodo
            gc.setFill(COL_TEXT);
            gc.fillText(String.valueOf(list.get(i)), nodeX + NODE_WIDTH / 2, nodeY + NODE_HEIGHT / 2);

            // Dibujar flecha al siguiente nodo si no es el último
            if (i < listSize) {
                double arrowStartX = nodeX + NODE_WIDTH;
                double arrowStartY = nodeY + NODE_HEIGHT / 2;
                double arrowEndX = nodeX + NODE_SPACING_X;
                double arrowEndY = nodeY + NODE_HEIGHT / 2;

                gc.setStroke(COL_POINTER); // Color ámbar UCR para el puntero
                gc.setLineWidth(2);
                gc.strokeLine(arrowStartX, arrowStartY, arrowEndX, arrowEndY);

                // Dibujar punta de flecha (triángulo simple)
                double arrowHeadSize = 5;
                gc.strokeLine(arrowEndX, arrowEndY, arrowEndX - arrowHeadSize, arrowEndY - arrowHeadSize);
                gc.strokeLine(arrowEndX, arrowEndY, arrowEndX - arrowHeadSize, arrowEndY + arrowHeadSize);
            }
        }
    }
}
