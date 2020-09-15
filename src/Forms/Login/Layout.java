package Forms.Login;

import javax.swing.*;

public class Layout extends GroupLayout {

    //variables para hacer el acomodo de los componentes:
    private SequentialGroup horizontal;
    private SequentialGroup vertical;

    public Layout(PanelLogin host) {
        super(host);

        this.horizontal = this.createSequentialGroup();
        this.vertical = this.createSequentialGroup();

        this.horizontal.addGroup(this.createSequentialGroup()
                .addGap(50)
                .addGroup(this.createParallelGroup()
                                .addGap(30)
                                .addComponent(host.getCONTENEDOR_ICONO_LB(),200,250,400)
                                .addGap(30)
                                .addComponent(host.getUSUARIO_TXT(),229 , 530,550)
                                .addGap(30)
                                .addComponent(host.getPASSWORD_TXT(),229 , 430,450)
                                .addGap(30)
                                .addGroup(this.createSequentialGroup()
                                        .addGap(80)
                                        .addComponent(host.getINICIAR_SESION_BTN(),129 , 235,235)

                                )
                                .addGap(30)
                        )
                .addGap(40)
        );

        this.setHorizontalGroup(this.horizontal);

        this.vertical.addGroup(this.createParallelGroup()

                .addGroup(this.createSequentialGroup()
                        .addGap(30)
                        .addComponent(host.getCONTENEDOR_ICONO_LB(),350,400,400)
                        .addGap(30)
                        .addComponent(host.getUSUARIO_TXT(),49 , 345,375)
                        .addGap(30)
                        .addComponent(host.getPASSWORD_TXT(),49 , 335,375)
                        .addGap(30)
                        .addGroup(this.createParallelGroup()
                                .addGap(450)
                                .addComponent(host.getINICIAR_SESION_BTN(),29 , 35,35)
                                .addGap(250)
                        )
                        .addGap(30)
                )

        );

        this.setVerticalGroup(this.vertical);
    }
}
