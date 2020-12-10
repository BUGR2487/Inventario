package Forms.Principal.Salidas.Layouts;

import Forms.Layouts;
import Forms.Principal.Salidas.Panels.InsertarSalidasPanel;

import javax.swing.*;

public class InsertarSalidasLayout extends GroupLayout implements Layouts {

    private SequentialGroup Horizontal;
    private SequentialGroup Vertical;

    public InsertarSalidasLayout(InsertarSalidasPanel host) {
        super(host);
        this.Horizontal = this.createSequentialGroup();
        this.Vertical = this.createSequentialGroup();

        this.Horizontal.addGroup(this.createParallelGroup()

                .addGroup(this.createSequentialGroup()
                        .addGap(padding_x - 400)
                        .addGroup(this.createParallelGroup()
                                .addComponent(host.getN_PEDIDO_LB())
                                .addComponent(host.getN_PEDIDO_CMB(),size_x_min,size_x_mid,size_x_max)
                        )
                        .addGroup(this.createParallelGroup()
                                .addComponent(host.getSELLOS_LB())
                                .addComponent(host.getSELLOS_TXT(),size_x_min,size_x_mid,size_x_max)
                        )
                        .addGroup(this.createParallelGroup()
                                .addComponent(host.getCANT_PALLET_LB())
                                .addComponent(host.getCANT_PALLET_TXT(),size_x_min,size_x_mid,size_x_max)
                        )
                        .addGap( 100 )
                        .addGroup(this.createParallelGroup()
                                .addComponent(host.getFECHA_SALIDA_LB(),size_x_min,size_x_mid,size_x_max)
                        )
                        .addGap(padding_x - 400)
                )

                .addGroup(this.createSequentialGroup()
                        .addGap(padding_x - 400)
                        .addGroup(this.createParallelGroup()
                                .addComponent(host.getCANT_POR_PALETT_LB())
                                .addComponent(host.getCANT_POR_PALETT_TXT(),size_x_min,size_x_mid,size_x_max)
                        )
                        .addGroup(this.createParallelGroup()
                                .addComponent(host.getTOTAL_UNIDADES_LB())
                                .addComponent(host.getTOTAL_UNIDADES_TXT(),size_x_min,size_x_mid,size_x_max)
                        )
                        .addGroup(this.createParallelGroup()
                                .addComponent(host.getFECHA_ENTREGA_LB())
                                .addComponent(host.getFECHA_ENTREGA(),size_x_min,size_x_mid,size_x_max)
                        )
                        .addGroup(this.createParallelGroup()
                                .addComponent(host.getCHOFER_LB())
                                .addComponent(host.getCHOFER_TXT(),size_x_min,size_x_mid,size_x_max)
                        )
                        .addGap(padding_x - 400)
                )

                .addGroup(this.createSequentialGroup()
                        .addGap(padding_x - 400)

                        .addGroup(this.createParallelGroup()
                                .addComponent(host.getEMPRESA_LB())
                                .addComponent(host.getEMPRESA_TXT(),size_x_min,size_x_mid,size_x_max)
                        )
                        .addGroup(this.createParallelGroup()
                                .addComponent(host.getPLACAS_LB())
                                .addComponent(host.getPLACAS_TXT(),size_x_min,size_x_mid,size_x_max)
                        )
                        .addGroup(this.createParallelGroup()
                                .addComponent(host.getTRACTO_LB())
                                .addComponent(host.getTRACTO_TXT(),size_x_min,size_x_mid,size_x_max)
                        )
                        .addGap(padding_x - 400)
                )

                .addGroup(this.createSequentialGroup()
                        .addGap(padding_x - 400)
                        .addGroup(this.createParallelGroup()
                                .addGroup(this.createSequentialGroup()
                                    .addComponent(host.getREGISTRAR_SALIDA_BTN(),size_x_min,size_x_mid,size_x_max)
                                    .addComponent(host.getAGREGAR_A_LA_TABLA_BTN(),size_x_min,size_x_mid,size_x_max)
                                    .addComponent(host.getLIMPIAR_LA_TABLA_BTN(),size_x_min,size_x_mid,size_x_max)
                                    .addComponent(host.getELIMINAR_DE_LA_TABLA_BTN(),size_x_min,size_x_mid,size_x_max)
                                )
                                .addComponent(host.getTABLA_SALIDAS().getView(),size_x_min_tabla,size_x_mid_tabla,size_x_max_tabla)
                        )
                        .addGap(padding_x - 400)
                )



        );

        this.Vertical.addGroup(this.createSequentialGroup()

                .addGroup(this.createParallelGroup()
                        .addGroup(this.createSequentialGroup()
                                .addGap(padding_y - 180)
                                .addComponent(host.getN_PEDIDO_LB())
                                .addComponent(host.getN_PEDIDO_CMB(),size_y_min,size_y_mid,size_y_max)
                                .addGap(padding_y - 180)
                        )
                        .addGroup(this.createSequentialGroup()
                                .addGap(padding_y - 180)
                                .addComponent(host.getSELLOS_LB())
                                .addComponent(host.getSELLOS_TXT(),size_y_min,size_y_mid,size_y_max)
                                .addGap(padding_y - 180)
                        )
                        .addGroup(this.createSequentialGroup()
                                .addGap(padding_y - 180)
                                .addComponent(host.getCANT_PALLET_LB())
                                .addComponent(host.getCANT_PALLET_TXT(),size_y_min,size_y_mid,size_y_max)
                                .addGap(padding_y - 180)
                        )
                        .addGroup(this.createSequentialGroup()
                                .addGap(padding_y - 180)
                                .addComponent(host.getFECHA_SALIDA_LB())
                                .addGap(padding_y - 180)
                        )

                )

                .addGroup(this.createParallelGroup()
                        .addGroup(this.createSequentialGroup()
                                .addGap(padding_y - 180)
                                .addComponent(host.getCANT_POR_PALETT_LB())
                                .addComponent(host.getCANT_POR_PALETT_TXT(),size_y_min,size_y_mid,size_y_max)
                                .addGap(padding_y - 180)
                        )
                        .addGroup(this.createSequentialGroup()
                                .addGap(padding_y - 180)
                                .addComponent(host.getTOTAL_UNIDADES_LB())
                                .addComponent(host.getTOTAL_UNIDADES_TXT(),size_y_min,size_y_mid,size_y_max)
                                .addGap(padding_y - 180)
                        )
                        .addGroup(this.createSequentialGroup()
                                .addGap(padding_y - 180)
                                .addComponent(host.getFECHA_ENTREGA_LB())
                                .addComponent(host.getFECHA_ENTREGA(),size_y_min,size_y_mid,size_y_max)
                                .addGap(padding_y - 180)
                        )
                        .addGroup(this.createSequentialGroup()
                                .addGap(padding_y - 180)
                                .addComponent(host.getCHOFER_LB())
                                .addComponent(host.getCHOFER_TXT(),size_y_min,size_y_mid,size_y_max)
                                .addGap(padding_y - 180)
                        )
                )


                .addGroup(this.createParallelGroup()

                        .addGroup(this.createSequentialGroup()
                                .addGap(padding_y - 180)
                                .addComponent(host.getEMPRESA_LB())
                                .addComponent(host.getEMPRESA_TXT(),size_y_min,size_y_mid,size_y_max)
                                .addGap(padding_y - 180)
                        )
                        .addGroup(this.createSequentialGroup()
                                .addGap(padding_y - 180)
                                .addComponent(host.getPLACAS_LB())
                                .addComponent(host.getPLACAS_TXT(),size_y_min,size_y_mid,size_y_max)
                                .addGap(padding_y - 180)
                        )
                        .addGroup(this.createSequentialGroup()
                                .addGap(padding_y - 180)
                                .addComponent(host.getTRACTO_LB())
                                .addComponent(host.getTRACTO_TXT(),size_y_min,size_y_mid,size_y_max)
                                .addGap(padding_y - 180)
                        )
                )

                .addGroup(this.createParallelGroup()
                        .addGroup(this.createSequentialGroup()
                                .addGap(padding_y - 180)
                                .addGroup(this.createParallelGroup()
                                        .addComponent(host.getREGISTRAR_SALIDA_BTN(),size_y_min,size_y_mid,size_y_max)
                                        .addComponent(host.getAGREGAR_A_LA_TABLA_BTN(),size_y_min,size_y_mid,size_y_max)
                                        .addComponent(host.getLIMPIAR_LA_TABLA_BTN(),size_y_min,size_y_mid,size_y_max)
                                        .addComponent(host.getELIMINAR_DE_LA_TABLA_BTN(),size_y_min,size_y_mid,size_y_max)
                                )
                                .addGap(padding_y - 180)
                                .addComponent(host.getTABLA_SALIDAS().getView(),size_y_min_tabla,size_y_mid_tabla,size_y_max_tabla)
                                .addGap(padding_y - 180)
                        )
                )

        );

        this.setVerticalGroup(this.Vertical);
        this.setHorizontalGroup(this.Horizontal);
    }
}
