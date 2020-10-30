package Forms.Principal.Salidas.Layouts;

import Forms.Layouts;
import Forms.Principal.Salidas.PanelSalidas;

import javax.swing.*;

public class PanelSalidasLayout extends GroupLayout implements Layouts {

    private GroupLayout.SequentialGroup Horizontal;
    private GroupLayout.SequentialGroup Vertical;

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
