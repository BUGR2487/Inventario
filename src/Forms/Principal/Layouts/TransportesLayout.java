package Forms.Principal.Layouts;

import Forms.Principal.Panels.PanelTransportes;

import javax.swing.*;

public class TransportesLayout extends GroupLayout implements  Layouts{

    private GroupLayout.SequentialGroup Horizontal;
    private GroupLayout.SequentialGroup Vertical;

    public TransportesLayout(PanelTransportes host) {
        super(host);

        this.Horizontal = this.createSequentialGroup();
        this.Vertical   = this.createSequentialGroup();

        this.Horizontal.addGroup(this.createSequentialGroup()
                    .addGap(padding_x_mid)
                    .addGroup(this.createParallelGroup()
                                .addGap(120)
                                .addComponent(host.getCHOFER_LB())
                                .addComponent(host.getCHOFER_TXT())
                                .addGap(50)
                                .addComponent(host.getEMPRESA_LB())
                                .addComponent(host.getEMPRESA_TXT())
                                .addGap(50)
                                .addGroup(this.createSequentialGroup()
                                        .addGroup(this.createParallelGroup()
                                                .addComponent(host.getPLACAS_LB())
                                                .addComponent(host.getPLACAS_TXT())
                                        )
                                        .addGap(30)
                                        .addGroup(this.createParallelGroup()
                                                .addComponent(host.getTRACTO_CAMION_LB())
                                                .addComponent(host.getTRACTO_CAMION_TXT())
                                        )
                                )
                                .addGap(50)
                                .addComponent(host.getREGISTRAR_TRANSPORTE())
                    )
                .addGap(padding_x_mid)
        );

        this.Vertical.addGroup(this.createParallelGroup()

                .addGroup(this.createSequentialGroup()
                        .addGap(120)
                        .addComponent(host.getCHOFER_LB())
                        .addComponent(host.getCHOFER_TXT(),size_y_min, size_y_mid, size_y_max)
                        .addGap(50)
                        .addComponent(host.getEMPRESA_LB())
                        .addComponent(host.getEMPRESA_TXT(),size_y_min, size_y_mid, size_y_max)
                        .addGap(50)
                        .addGroup(this.createParallelGroup()
                                .addGroup(this.createSequentialGroup()
                                        .addComponent(host.getPLACAS_LB())
                                        .addComponent(host.getPLACAS_TXT(),size_y_min, size_y_mid, size_y_max)
                                )
                                .addGap(30)
                                .addGroup(this.createSequentialGroup()
                                        .addComponent(host.getTRACTO_CAMION_LB())
                                        .addComponent(host.getTRACTO_CAMION_TXT(),size_y_min, size_y_mid, size_y_max)
                                )
                        )
                        .addGap(50)
                        .addComponent(host.getREGISTRAR_TRANSPORTE(),size_y_min, size_y_mid, size_y_max)
                )

        );

        this.setVerticalGroup(this.Vertical);
        this.setHorizontalGroup(this.Horizontal);
    }
}
