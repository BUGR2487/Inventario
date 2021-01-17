package Forms.Principal.Usuario;

import Forms.Layouts;

import javax.swing.*;

public class UsuariosLayout extends GroupLayout implements Layouts {


    private GroupLayout.SequentialGroup Horizontal;
    private GroupLayout.SequentialGroup Vertical;


    public UsuariosLayout(PanelUsuarios host) {
        super(host);

        this.Horizontal = this.createSequentialGroup();
        this.Vertical   = this.createSequentialGroup();

        this.Horizontal.addGroup(this.createParallelGroup()
                        .addGap(30)
                        .addGroup(this.createSequentialGroup()
                                .addGap(padding_x2)
                                .addGroup(this.createParallelGroup()
                                        .addComponent(host.getNOMBRES_LB())
                                        .addComponent(host.getNOMBRES_TXT(),size_x_min, size_x_mid, size_x_max)
                                )
                                .addGap(20)
                                .addGroup(this.createParallelGroup()
                                        .addComponent(host.getAPELLIDO_P_LB())
                                        .addComponent(host.getAPELLIDO_P_TXT(),size_x_min, size_x_mid, size_x_max)
                                )
                                .addGap(20)
                                .addGroup(this.createParallelGroup()
                                        .addComponent(host.getAPELLIDO_M_LB())
                                        .addComponent(host.getAPELLIDO_M_TXT(),size_x_min, size_x_mid, size_x_max)
                                )
                                .addGap(padding_x2)
                        )
                        .addGap(20)
                        .addGroup(this.createSequentialGroup()
                                .addGap(padding_x2)
                                .addGroup(this.createParallelGroup()
                                        .addComponent(host.getCORREO_LB())
                                        .addComponent(host.getCORREO_TXT())
                                )
                                .addGap(20)
                                .addGroup(this.createParallelGroup()
                                        .addComponent(host.getPUESTO_LB())
                                        .addComponent(host.getPUESTO_TXT())
                                )
                                .addGap(padding_x2)
                        )
                        .addGap(20)
                        .addGroup(this.createSequentialGroup()
                                .addGap(padding_x2)
                                .addGroup(this.createParallelGroup()
                                        .addComponent(host.getPASS_LB())
                                        .addComponent(host.getPASS_TXT())
                                )
                                .addGap(20)
                                .addGroup(this.createParallelGroup()
                                        .addComponent(host.getCONFIRMAR_PASS_LB())
                                        .addComponent(host.getCONFIRMAR_PASS_TXT())
                                )
                                .addGap(padding_x2)
                        )
                        .addGap(20)
                        .addGroup(this.createSequentialGroup()
                                .addGap(padding_x)
                                .addComponent(host.getREGISTRAR_USUARIO(),size_x_min, size_x_mid, size_x_max)
                                .addGap(padding_x)
                        )
                        .addGap(30)

        );

        this.Vertical.addGroup(this.createSequentialGroup()
                .addGap(220)
                .addGroup(this.createParallelGroup()
                        .addGroup(this.createSequentialGroup()
                                .addComponent(host.getNOMBRES_LB())
                                .addComponent(host.getNOMBRES_TXT(),size_y_min, size_y_mid, size_y_max)
                        )
                        .addGap(20)
                        .addGroup(this.createSequentialGroup()
                                .addComponent(host.getAPELLIDO_P_LB())
                                .addComponent(host.getAPELLIDO_P_TXT(),size_y_min, size_y_mid, size_y_max)
                        )
                        .addGap(20)
                        .addGroup(this.createSequentialGroup()
                                .addComponent(host.getAPELLIDO_M_LB())
                                .addComponent(host.getAPELLIDO_M_TXT(),size_y_min, size_y_mid, size_y_max)
                        )

                )
                .addGap(30)
                .addGroup(this.createParallelGroup()

                        .addGroup(this.createSequentialGroup()
                                .addComponent(host.getCORREO_LB())
                                .addComponent(host.getCORREO_TXT(),size_y_min, size_y_mid, size_y_max)
                        )
                        .addGap(20)
                        .addGroup(this.createSequentialGroup()
                                .addComponent(host.getPUESTO_LB())
                                .addComponent(host.getPUESTO_TXT(),size_y_min, size_y_mid, size_y_max)
                        )

                )
                .addGap(30)
                .addGroup(this.createParallelGroup()

                        .addGroup(this.createSequentialGroup()
                                .addComponent(host.getPASS_LB())
                                .addComponent(host.getPASS_TXT(),size_y_min, size_y_mid, size_y_max)
                        )
                        .addGap(20)
                        .addGroup(this.createSequentialGroup()
                                .addComponent(host.getCONFIRMAR_PASS_LB())
                                .addComponent(host.getCONFIRMAR_PASS_TXT(),size_y_min, size_y_mid, size_y_max)
                        )

                )
                .addGap(30)
                .addGroup(this.createParallelGroup()

                        .addComponent(host.getREGISTRAR_USUARIO(),size_y_min, size_y_mid, size_y_max)

                )


        );

        this.setVerticalGroup(this.Vertical);
        this.setHorizontalGroup(this.Horizontal);


    }
}
