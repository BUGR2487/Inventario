package Forms.Principal.Salidas;

import Forms.Components.Fecha_y_hora;
import Forms.Components.Tabs;
import Forms.Panel;
import Forms.Principal.Salidas.Layouts.PanelSalidasLayout;
import Forms.Principal.Salidas.Panels.ConsultarSalidasPanel;
import Forms.Principal.Salidas.Panels.InsertarSalidasPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;

//PARA IR RAPIDO DE SECCION A SECCION USA CTRL + F O CMD + F(EN MAC) Y BUSCA LAS SIGUIENTES PALABRAS:
// -- VARIABLES Y CONSTANTES
// -- CONSTRUCTOR:
// -- METODOS DE LA CLASE:
// -- GET'S Y SET'S

/**
 * Panel de entradas que contiene un tab panel que alberga 2 vistas mas
 *
 * 1 - insertar salida
 * 2 - buscar salida por rango de fechas
 */
public class PanelSalidas extends JPanel {

    // -- VARIABLES Y CONSTANTES

    //vistas(paneles)
    private InsertarSalidasPanel registro;
    private ConsultarSalidasPanel consultar;

    //tab Panel
    private final Tabs TABBEDPANE = new Tabs(true);

    // -- CONSTRUCTOR:

    /**
     *
     * Constructor que genera el panel de menú de entradas, recibe como parametro la etiqueta que
     * se usara para mostrar fecha y hora y el controlador que actualizara el label.
     *
     * @param lb {@link JLabel}
     * @param controller {@link Fecha_y_hora}
     */
    public PanelSalidas(JLabel lb , Fecha_y_hora controller){
        this.registro = new InsertarSalidasPanel(lb, controller);
        this.consultar = new ConsultarSalidasPanel();

        prepareMenu();

        this.setLayout( new PanelSalidasLayout(this));
    }

    // -- METODOS DE LA CLASE:
    /**
     *
     * Retorna el Panel con el Tab panel implementado en el
     *
     * @return {@link Tabs}
     */
    public Tabs getTABBEDPANE() {
        return TABBEDPANE;
    }
    /**
     * Función que prepara los tabs y componentes en este panel dejando listo en cuestion
     * de formato cada componente
     */
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
    /**
     *
     * Función que resetea la vista de insertar salidas dejando sus campos en sus valores por default.
     *
     */
    public void resetRegistros(){
        this.getRegistro().vaciarTextos();
        this.getRegistro().cargaComboBox();
        this.getRegistro().getTABLA_SALIDAS().vaciarTabla();
    }
    /**
     *
     * Función que resetea la vista de consultar salidas dejando sus campos en sus valores por default.
     *
     */
    public void resetConsultarSalidas(){
        this.getConsultar().vaciarTextos();
        this.getConsultar().getTABLA_BUSQUEDA_SALIDAS().vaciarTabla();
    }


    // -- GET'S Y SET'S
    /**
     *
     * Retorna el JPanel en algunos paneles esta funcion puede devolver el
     * JPanel con el Scroll implementado de ser requerido, en este caso no hubo necesidad de ello.
     *
     * @return {@link Component}
     */
    public Component getview(){
        return this;
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
