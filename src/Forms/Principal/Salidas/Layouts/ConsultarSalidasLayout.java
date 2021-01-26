package Forms.Principal.Salidas.Layouts;


import Forms.Layouts;
import Forms.Principal.Entradas.PanelEntradas;
import Forms.Principal.Entradas.Panels.ConsultarEntradas;
import Forms.Principal.Salidas.Panels.ConsultarSalidasPanel;

import javax.swing.*;

/**
 * Clase que hereda de la clase {@link GroupLayout} e implementa la interface {@link Layouts}
 * para el acomodo de componentes y sus dimensiones de su respectivo panel.
 */
public class ConsultarSalidasLayout extends GroupLayout implements Layouts {

    //Variable que se encarga de acomodar y dimencionar los componentes en el eje X
    private SequentialGroup Horizontal;
    //Variable que se encarga de acomodar y dimencionar los componentes en el eje Y
    private SequentialGroup Vertical;

    /**
     *
     * Contructor que recibe como parametro una instancia de la clase {@link ConsultarSalidasPanel}
     * para ordenar sus componentes con respecto al eje X y Y.
     *
     * @param host {@link ConsultarSalidasPanel}
     */
    public ConsultarSalidasLayout(ConsultarSalidasPanel host) {
        super(host);
        this.Horizontal = this.createSequentialGroup();
        this.Vertical   = this.createSequentialGroup();

        Horizontal.addGroup(this.createParallelGroup()
                .addGroup(this.createSequentialGroup()
                        .addGap(padding_x2)
                        .addGroup(this.createParallelGroup()
                                .addComponent(host.getDATEPICKER_FROM_LB())
                                .addComponent(host.getDATEPICKER_FROM(),size_x_min,size_x_mid,size_x_mid)
                        )
                        .addGap(10)
                        .addGroup(this.createParallelGroup()
                                .addComponent(host.getDATEPICKER_TO_LB())
                                .addComponent(host.getDATEPICKER_TO(),size_x_min,size_x_mid,size_x_mid)
                        )
                        .addGap(10)
                        .addGroup(this.createParallelGroup()
                                .addComponent(host.getBUSCAR_BTN(),size_x_min,size_x_mid,size_x_mid)
                        )
                        .addGap(padding_x2)
                )
                .addGroup(this.createSequentialGroup()
                        .addGap(padding_x2)
                        .addGroup(this.createParallelGroup()
                                .addComponent(host.getTABLA_BUSQUEDA_SALIDAS().getView(),size_x_min_tabla,size_x_mid_tabla,size_x_max_tabla)
                        )
                        .addGap(padding_x2)
                )
        );

        Vertical.addGroup(this.createSequentialGroup()
                .addGroup(this.createParallelGroup()
                        .addGap(padding_x2)
                        .addGroup(this.createSequentialGroup()
                                .addComponent(host.getDATEPICKER_FROM_LB())
                                .addComponent(host.getDATEPICKER_FROM())
                        )
                        .addGroup(this.createSequentialGroup()
                                .addComponent(host.getDATEPICKER_TO_LB())
                                .addComponent(host.getDATEPICKER_TO())
                        )
                        .addGroup(this.createSequentialGroup()
                                .addGap( 10 )
                                .addComponent(host.getBUSCAR_BTN(),size_y_min, size_y_mid, size_y_max)
                        )
                        .addGap(padding_x2)
                )
                .addGap( 30 )
                .addGroup(this.createParallelGroup()
                        .addGap(padding_x2)
                        .addGroup(this.createSequentialGroup()
                                .addComponent(host.getTABLA_BUSQUEDA_SALIDAS().getView(),size_y_min_tabla,size_y_mid_tabla,size_y_max_tabla)
                        )
                        .addGap(padding_x2)
                )
        );

        this.setHorizontalGroup( Horizontal );
        this.setVerticalGroup( Vertical );
    }
}
