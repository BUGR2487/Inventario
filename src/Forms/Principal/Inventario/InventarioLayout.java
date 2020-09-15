package Forms.Principal.Inventario;

import Forms.Principal.Layouts;

import javax.swing.*;

public class InventarioLayout extends  GroupLayout implements Layouts {

    private GroupLayout.SequentialGroup Horizontal;
    private GroupLayout.SequentialGroup Vertical;

    public InventarioLayout(PanelInventario host) {
        super(host);
        
        this.Horizontal = this.createSequentialGroup();
        this.Vertical = this.createSequentialGroup();

        this.Horizontal.addGroup(this.createParallelGroup()
                .addGap(padding_y/2)
                .addGroup(this.createSequentialGroup()
                        .addGap(this.padding_x + 50)
                        .addGroup(this.createParallelGroup()
                                .addComponent(host.getCODIGO_BARRAS_LB())
                                .addComponent(host.getCODIGO_BARRAS_TXT(),size_x_min,size_x_mid,size_x_max)
                        )
                        .addGap(this.padding_x)
                )
                .addGroup(this.createSequentialGroup()
                        .addGap(this.padding_x + 50)
                        .addGroup(this.createParallelGroup()
                                .addComponent(host.getDISENO_LB())
                                .addComponent(host.getDISENO_TXT(),size_x_min,size_x_mid,size_x_max)
                        )
                        .addGap(this.padding_x)
                )
                .addGroup(this.createSequentialGroup()
                        .addGap(this.padding_x + 50)
                        .addGroup(this.createParallelGroup()
                                .addComponent(host.getCODIGO_INTERNO_LB())
                                .addComponent(host.getCODIGO_INTERNO_TXT(),size_x_min,size_x_mid,size_x_max)
                        )
                        .addGap(this.padding_x)
                )
                .addGroup(this.createSequentialGroup()
                        .addGap(this.padding_x + 50)
                        .addGroup(this.createParallelGroup()
                                .addComponent(host.getCLIENTE_LB())
                                .addComponent(host.getCLIENTE_TXT(),size_x_min,size_x_mid,size_x_max)
                        )
                        .addGap(this.padding_x)
                )
                .addGroup(this.createSequentialGroup()
                        .addGap(this.padding_x + 50)
                        .addGroup(this.createParallelGroup()
                                .addComponent(host.getCANTIDAD_PALLET_LB())
                                .addComponent(host.getCANTIDAD_PALLET_TXT(),size_x_min,size_x_mid,size_x_max)
                        )
                        .addGap(this.padding_x)
                )
                .addGroup(this.createSequentialGroup()
                        .addGap(this.padding_x + 50)
                        .addGroup(this.createParallelGroup()
                                .addComponent(host.getCANTIDAD_POR_PALLET_LB())
                                .addComponent(host.getCANTIDAD_POR_PALLET_TXT(),size_x_min,size_x_mid,size_x_max)
                        )
                        .addGap(this.padding_x)
                )
                .addGroup(this.createSequentialGroup()
                        .addGap(this.padding_x + 50)
                        .addGroup(this.createParallelGroup()
                                .addComponent(host.getPRODUCTO_LB())
                                .addComponent(host.getPRODUCTO_TXT(),size_x_min,size_x_mid,size_x_max)
                        )
                        .addGap(this.padding_x)
                )
                .addGroup(this.createSequentialGroup()
                        .addGap(this.padding_x + 50)
                        .addGroup(this.createParallelGroup()
                                .addGap(20)
                                .addComponent(host.getREGISTRAR_ENTRADA(),size_x_min,size_x_mid,size_x_max)
                        )
                        .addGap(this.padding_x)
                )
        );

        this.Vertical.addGroup(this.createSequentialGroup()
                .addGap(padding_y/2)
                .addGroup(this.createParallelGroup()

                        .addGroup(this.createSequentialGroup()
                                .addComponent(host.getCODIGO_BARRAS_LB())
                                .addComponent(host.getCODIGO_BARRAS_TXT(),size_y_min,size_y_mid,size_y_max)
                        )

                )
                .addGroup(this.createParallelGroup()

                        .addGroup(this.createSequentialGroup()
                                .addComponent(host.getDISENO_LB())
                                .addComponent(host.getDISENO_TXT(),size_y_min,size_y_mid,size_y_max)
                        )

                )
                .addGroup(this.createParallelGroup()

                        .addGroup(this.createSequentialGroup()
                                .addComponent(host.getCODIGO_INTERNO_LB())
                                .addComponent(host.getCODIGO_INTERNO_TXT(),size_y_min,size_y_mid,size_y_max)
                        )

                )
                .addGroup(this.createParallelGroup()

                        .addGroup(this.createSequentialGroup()
                                .addComponent(host.getCLIENTE_LB())
                                .addComponent(host.getCLIENTE_TXT(),size_y_min,size_y_mid,size_y_max)
                        )

                )
                .addGroup(this.createParallelGroup()

                        .addGroup(this.createSequentialGroup()
                                .addComponent(host.getCANTIDAD_PALLET_LB())
                                .addComponent(host.getCANTIDAD_PALLET_TXT(),size_y_min,size_y_mid,size_y_max)
                        )

                )
                .addGroup(this.createParallelGroup()

                        .addGroup(this.createSequentialGroup()
                                .addComponent(host.getCANTIDAD_POR_PALLET_LB())
                                .addComponent(host.getCANTIDAD_POR_PALLET_TXT(),size_y_min,size_y_mid,size_y_max)
                        )

                )
                .addGroup(this.createParallelGroup()

                        .addGroup(this.createSequentialGroup()
                                .addComponent(host.getPRODUCTO_LB())
                                .addComponent(host.getPRODUCTO_TXT(),size_y_min,size_y_mid,size_y_max)
                        )

                )
                .addGroup(this.createParallelGroup()

                        .addGroup(this.createSequentialGroup()
                                .addGap(20)
                                .addComponent(host.getREGISTRAR_ENTRADA(),size_y_min,size_y_mid,size_y_max)
                        )

                )
        );

        this.setHorizontalGroup(this.Horizontal);
        this.setVerticalGroup(this.Vertical);


    }
}
