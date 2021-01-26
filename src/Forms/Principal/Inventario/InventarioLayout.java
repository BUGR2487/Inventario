package Forms.Principal.Inventario;

import Forms.Layouts;
import Forms.Principal.Entradas.PanelEntradas;
import Forms.Principal.Entradas.Panels.InsertarEntradas;

import javax.swing.*;

/**
 * Clase que hereda de la clase {@link GroupLayout} e implementa la interface {@link Layouts}
 * para el acomodo de componentes y sus dimensiones de su respectivo panel.
 */
public class InventarioLayout extends  GroupLayout implements Layouts {

    //Variable que se encarga de acomodar y dimencionar los componentes en el eje X
    private GroupLayout.SequentialGroup Horizontal;
    //Variable que se encarga de acomodar y dimencionar los componentes en el eje Y
    private GroupLayout.SequentialGroup Vertical;

    /**
     *
     * Contructor que recibe como parametro una instancia de la clase {@link PanelInventario}
     * para ordenar sus componentes con respecto al eje X y Y.
     *
     * @param host {@link PanelEntradas}
     */
    public InventarioLayout(PanelInventario host) {
        super(host);
        
        this.Horizontal = this.createSequentialGroup();
        this.Vertical = this.createSequentialGroup();

        this.Horizontal.addGroup(this.createParallelGroup()
                .addGap(padding_y/2)
                .addGroup(this.createSequentialGroup()
                    .addGroup(this.createParallelGroup()
                            .addGroup(this.createSequentialGroup()
                                    .addGap(this.padding_x1)
                                    .addGroup(this.createParallelGroup()
                                            .addComponent(host.getCODIGO_BARRAS_LB())
                                            .addComponent(host.getCODIGO_BARRAS_TXT(),size_x_min,size_x_mid,size_x_max)
                                    )
                                    .addGap(this.padding_x1)
                            )
                            .addGroup(this.createSequentialGroup()
                                    .addGap(this.padding_x1)
                                    .addGroup(this.createParallelGroup()
                                            .addComponent(host.getDISENO_LB())
                                            .addComponent(host.getDISENO_TXT(),size_x_min,size_x_mid,size_x_max)
                                    )
                                    .addGap(this.padding_x1)
                            )
                            .addGroup(this.createSequentialGroup()
                                    .addGap(this.padding_x1)
                                    .addGroup(this.createParallelGroup()
                                            .addComponent(host.getCODIGO_INTERNO_LB())
                                            .addComponent(host.getCODIGO_INTERNO_TXT(),size_x_min,size_x_mid,size_x_max)
                                    )
                                    .addGap(this.padding_x1)
                            )
                            .addGroup(this.createSequentialGroup()
                                    .addGap(this.padding_x1)
                                    .addGroup(this.createParallelGroup()
                                            .addComponent(host.getCLIENTE_LB())
                                            .addComponent(host.getCLIENTE_TXT(),size_x_min,size_x_mid,size_x_max)
                                    )
                                    .addGap(this.padding_x1)
                            )
                            .addGroup(this.createSequentialGroup()
                                    .addGap(this.padding_x1)
                                    .addGroup(this.createParallelGroup()
                                            .addComponent(host.getPRODUCTO_LB())
                                            .addComponent(host.getPRODUCTO_TXT(),size_x_min,size_x_mid,size_x_max)
                                    )
                                    .addGap(this.padding_x1)
                            )
                            .addGroup(this.createSequentialGroup()
                                    .addGap(this.padding_x1)
                                    .addGroup(this.createParallelGroup()
                                            .addGap(20)
                                            .addComponent(host.getREGISTRAR_ENTRADA(),size_x_min,size_x_mid,size_x_max)
                                    )
                                    .addGap(this.padding_x1)
                            )
                    )
                        .addGap(10)
                        .addGroup(this.createParallelGroup()
                                .addGroup(this.createSequentialGroup()
                                        .addGroup(this.createParallelGroup()
                                                        .addComponent(host.getCB_BUSQUEDA_LB(),
                                                                size_x_min,size_x_mid,size_x_max)
                                                        .addComponent(host.getCB_BUSQUEDA_TXT(),
                                                                size_x_min,size_x_mid,size_x_max)
                                        )
                                        .addGap(10)
                                        .addComponent(host.getTOTAL_DE_STOCK_LB())
                                )
                                .addGap(20)
                                .addComponent(host.getTABLA_DE_INVENTARIO().getView(),size_x_min_tabla,size_x_mid_tabla,size_x_max_tabla)
                        )
                                .addGap(30)
                )

        );

        this.Vertical.addGroup(this.createSequentialGroup()
                .addGap(padding_y/2)
                .addGroup(this.createParallelGroup()
                    .addGroup(this.createSequentialGroup()
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
                    )
                        .addGap(30)
                        .addGroup(this.createSequentialGroup()
                                .addGroup(this.createParallelGroup()
                                        .addGroup(this.createSequentialGroup()
                                            .addComponent(host.getCB_BUSQUEDA_LB())
                                            .addComponent(host.getCB_BUSQUEDA_TXT(),size_y_min,size_y_mid,size_y_max)
                                        )
                                        .addGap(10)
                                        .addGroup(this.createSequentialGroup()
                                            .addGap(25)
                                            .addComponent(host.getTOTAL_DE_STOCK_LB())
                                        )
                                )
                                .addGap(20)
                                .addComponent(host.getTABLA_DE_INVENTARIO().getView()
                                        ,size_y_min_tabla,size_y_mid_tabla,size_y_max_tabla)
                                .addGap(100)
                        )
                )

        );

        this.setHorizontalGroup(this.Horizontal);
        this.setVerticalGroup(this.Vertical);


    }
}
