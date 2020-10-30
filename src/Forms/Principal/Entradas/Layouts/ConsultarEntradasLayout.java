package Forms.Principal.Entradas.Layouts;

import Forms.Layouts;
import Forms.Principal.Entradas.Panels.ConsultarEntradas;

import javax.swing.*;

public class ConsultarEntradasLayout extends GroupLayout implements Layouts {

    private SequentialGroup Horizontal;
    private SequentialGroup Vertical;

    public ConsultarEntradasLayout(ConsultarEntradas host) {
        super(host);

        this.Horizontal = this.createSequentialGroup();
        this.Vertical   = this.createSequentialGroup();

        Horizontal.addGroup(this.createParallelGroup()
                .addGroup(this.createSequentialGroup()
                        .addGap(pading_x2)
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
                        .addGap(pading_x2)
                )
                .addGroup(this.createSequentialGroup()
                        .addGap(pading_x2)
                        .addGroup(this.createParallelGroup()
                                .addComponent(host.getTABLA_BUSQUEDA_ENTRADAS().getView(),size_x_min_tabla,size_x_mid_tabla,size_x_max_tabla)
                        )
                        .addGap(pading_x2)
                )
        );

        Vertical.addGroup(this.createSequentialGroup()
                .addGroup(this.createParallelGroup()
                        .addGap(pading_x2)
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
                        .addGap(pading_x2)
                )
                .addGap( 30 )
                .addGroup(this.createParallelGroup()
                        .addGap(pading_x2)
                        .addGroup(this.createSequentialGroup()
                                .addComponent(host.getTABLA_BUSQUEDA_ENTRADAS().getView(),size_y_min_tabla,size_y_mid_tabla,size_y_max_tabla)
                        )
                        .addGap(pading_x2)
                )
        );

        this.setHorizontalGroup( Horizontal );
        this.setVerticalGroup( Vertical );

    }
}
