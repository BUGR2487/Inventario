package Forms.Principal.Entradas.Layouts;

import Forms.Principal.Entradas.PanelEntradas;
import Forms.Layouts;

import javax.swing.*;

public class PanelEntradasLayout extends GroupLayout implements Layouts {

    private SequentialGroup Horizontal;
    private SequentialGroup Vertical;

    public PanelEntradasLayout(PanelEntradas host) {
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
