package Forms.Principal.Entradas.Layouts;

import Forms.Principal.Entradas.PanelEntradas;
import Forms.Layouts;

import javax.swing.*;

/**
 * Clase que hereda de la clase {@link GroupLayout} e implementa la interface {@link Layouts}
 * para el acomodo de componentes y sus dimensiones de su respectivo panel.
 */
public class PanelEntradasLayout extends GroupLayout implements Layouts {

    //Variable que se encarga de acomodar y dimencionar los componentes en el eje X
    private SequentialGroup Horizontal;
    //Variable que se encarga de acomodar y dimencionar los componentes en el eje Y
    private SequentialGroup Vertical;

    /**
     *
     * Contructor que recibe como parametro una instancia de la clase {@link PanelEntradas}
     * para ordenar sus componentes con respecto al eje X y Y.
     *
     * @param host {@link PanelEntradas}
     */
    public PanelEntradasLayout(PanelEntradas host) {
        super(host);

        this.Horizontal = this.createSequentialGroup();
        this.Vertical   = this.createSequentialGroup();

        this.Horizontal.addGroup( this.createSequentialGroup()
                .addComponent( host.getTABBEDPANE() )
        );

        this.Vertical.addGroup( this.createParallelGroup()
                .addComponent( host.getTABBEDPANE() )
        );

        this.setVerticalGroup( this.Vertical );
        this.setHorizontalGroup( this.Horizontal );

    }
}
