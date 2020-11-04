package Forms.Principal.Salidas;

import Forms.Components.Fecha_y_hora;
import Forms.Components.Tabs;
import Forms.Principal.Salidas.Layouts.PanelSalidasLayout;
import Forms.Principal.Salidas.Panels.ConsultarSalidasPanel;
import Forms.Principal.Salidas.Panels.InsertarSalidasPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class PanelSalidas extends JPanel {

    // -- VARIABLES:
    private InsertarSalidasPanel registro;

    private ConsultarSalidasPanel consultar;

    private final Tabs TABBEDPANE = new Tabs(true);

    // -- CONSTRUCTOR:

    public PanelSalidas(JLabel lb , Fecha_y_hora controller){
        this.registro = new InsertarSalidasPanel(lb, controller);
        this.consultar = new ConsultarSalidasPanel();

        prepareMenu();

        this.setLayout( new PanelSalidasLayout(this));
    }

    // -- METODOS DE LA CLASE:

    public Component getview()
    {
        return this;
    }

    public Tabs getTABBEDPANE() {
        return TABBEDPANE;
    }

    public void prepareMenu(){

        Tabs.Tab tab1 =
                new Tabs.Tab("Insertar salida.",
                        null,
                        this.registro.getview(),
                        "Haz click aqui para registrar una salida al almacen.");

        Tabs.Tab tab2 =
                new Tabs.Tab("Consultar salidas.",
                        null,
                        this.consultar.getview(),
                        "Haz click aqui para consultar las salidas dentro de un rango de fechas.");

        this.getTABBEDPANE().addTab( tab1 );
        this.getTABBEDPANE().addTab( tab2 );
        this.getTABBEDPANE().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
                int selectedIndex = tabbedPane.getSelectedIndex();
                switch (selectedIndex)
                {
                    case 0:
                    {
                        resetConsultarSalidas();
                    }
                    break;
                    case 1:
                    {
                        resetRegistros();
                    }
                    break;
                }
                consultar.hideDatePickers();
            }
        });

    }


    public void resetRegistros()
    {
        this.getRegistro().vaciarTextos();
        this.getRegistro().cargaComboBox();
    }

    public void resetConsultarSalidas(){
        this.getConsultar().vaciarTextos();
    }

    public ConsultarSalidasPanel getConsultarEntrada(){
        return this.consultar;
    }

    public InsertarSalidasPanel getRegistro() {
        return registro;
    }

    public void setRegistro(InsertarSalidasPanel registro) {
        this.registro = registro;
    }

    public ConsultarSalidasPanel getConsultar() {
        return consultar;
    }

    public void setConsultar(ConsultarSalidasPanel consultar) {
        this.consultar = consultar;
    }
}
