package Forms.Principal.Entradas;

import Forms.Components.Fecha_y_hora;
import Forms.Components.Tabs;
import Forms.Principal.Entradas.Layouts.PanelEntradasLayout;
import Forms.Principal.Entradas.Panels.ConsultarEntradas;
import Forms.Principal.Entradas.Panels.InsertarEntradas;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class PanelEntradas extends JPanel {

    private InsertarEntradas registro;

    private ConsultarEntradas consultar;

    private final Tabs TABBEDPANE = new Tabs(true);

    public PanelEntradas(JLabel dateTime, Fecha_y_hora dateController){

        this.registro = new InsertarEntradas(dateTime, dateController);
        this.consultar = new ConsultarEntradas();

        prepareMenu();

        this.setLayout( new PanelEntradasLayout(this));

    }

    public Component getview()
    {
        return this;
    }

    public Tabs getTABBEDPANE() {
        return TABBEDPANE;
    }

    public void prepareMenu(){

        Tabs.Tab tab1 =
                new Tabs.Tab("Insertar entrada.",
                null,
                this.registro.getview(),
                "Haz click aqui para registrar una entrada al almacen.");

        Tabs.Tab tab2 =
                new Tabs.Tab("Consultar entradas.",
                null,
                this.consultar.getview(),
                "Haz click aqui para consultar las entradas dentro de un rango de fechas.");

        this.getTABBEDPANE().addTab( tab1 );
        this.getTABBEDPANE().addTab( tab2 );
        this.getTABBEDPANE().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                consultar.hideDatePickers();
            }
        });

    }

    public ConsultarEntradas getConsultarEntrada(){
        return this.consultar;
    }
}
