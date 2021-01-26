package Forms.Principal.Salidas.Layouts;

import Forms.Layouts;
import Forms.Principal.Entradas.PanelEntradas;
import Forms.Principal.Entradas.Panels.ConsultarEntradas;
import Forms.Principal.Salidas.PanelSalidas;

import javax.swing.*;

/**
 * Clase que hereda de la clase {@link GroupLayout} e implementa la interface {@link Layouts}
 * para el acomodo de componentes y sus dimensiones de su respectivo panel.
 */
public class PanelSalidasLayout extends GroupLayout implements Layouts {

    //Variable que se encarga de acomodar y dimencionar los componentes en el eje X
    private GroupLayout.SequentialGroup Horizontal;
    //Variable que se encarga de acomodar y dimencionar los componentes en el eje Y
    private GroupLayout.SequentialGroup Vertical;

    /**
     *
     * Contructor que recibe como parametro una instancia de la clase {@link PanelSalidas}
     * para ordenar sus componentes con respecto al eje X y Y.
     *
     * @param host {@link PanelSalidas}
     */
    public PanelSalidasLayout(PanelSalidas host) {
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
