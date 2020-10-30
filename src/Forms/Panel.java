package Forms;

import java.awt.*;

public interface Panel {

    // -- Colores de fondo:
    public static Color color1 = Color.decode("#8C8C8C");
    public static Color color2 = Color.decode("#CBCBCB");

    // -- Fuentes
    public static final Font FUENTE_GENERAL_LB            = new Font("Arial", Font.PLAIN, 24);
    public static final Font FUENTE_GENERAL_TXT           = new Font("Arial", Font.PLAIN, 19);

    public boolean camposVacios();
    public void vaciarTextos();

    public static final GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
    public static final Rectangle bounds = env.getMaximumWindowBounds();

    public static final int MAX_X = bounds.width;
    public static final int MAX_Y = bounds.height;





}
