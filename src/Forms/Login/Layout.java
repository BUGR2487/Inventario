package Forms.Login;

import Forms.Layouts;
import Forms.Principal.Transporte.PanelTransportes;

import javax.swing.*;

/**
 * Clase que hereda de la clase {@link GroupLayout} e implementa la interface {@link Layouts}
 * para el acomodo de componentes y sus dimensiones de su respectivo panel.
 */
public class Layout extends GroupLayout {


    //Variable que se encarga de acomodar y dimencionar los componentes en el eje X
    private SequentialGroup horizontal;
    //Variable que se encarga de acomodar y dimencionar los componentes en el eje Y
    private SequentialGroup vertical;

    /**
     *
     * Contructor que recibe como parametro una instancia de la clase {@link PanelLogin}
     * para ordenar sus componentes con respecto al eje X y Y.
     *
     * @param host {@link PanelLogin}
     */
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
