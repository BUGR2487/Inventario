package Forms.Principal;

import java.awt.*;

public interface Panel {

    // -- Colores de fondo:
    public Color color1 = Color.decode("#8C8C8C");
    public Color color2 = Color.decode("#CBCBCB");

    // -- Fuentes

    public final Font FUENTE_GENERAL_LB            = new Font("Arial", Font.PLAIN, 24);
    public final Font FUENTE_GENERAL_TXT           = new Font("Arial", Font.PLAIN, 19);

    public boolean camposVacios();
    public void vaciarTextos();

}
