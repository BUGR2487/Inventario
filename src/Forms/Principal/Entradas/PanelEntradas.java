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



/**
 * Panel de entradas que contiene un tab panel que alberga 2 vistas mas
 *
 * 1 - insertar entrada
 * 2 - buscar entrada por rango de fechas
 *
 */
public class PanelEntradas extends JPanel {

    //VARIBLES Y CONSTANTES:

    //Panel de la vista insertar entrada:
    private InsertarEntradas registro;

    //panel de la vista buscar entrada:
    private ConsultarEntradas consultar;

    //panel tab contenedor de las pestañas:
    private final Tabs TABBEDPANE = new Tabs(true);

    /**
     *
     * Constructor que genera el panel de menú de entradas, recibe como parametro la etiqueta que
     * se usara para mostrar fecha y hora y el controlador que actualizara el label.
     *
     * @param dateTime {@link JLabel}
     * @param dateController {@link Fecha_y_hora}
     */
    public PanelEntradas(JLabel dateTime, Fecha_y_hora dateController){

        //se traspasa los paramateros a el contructor de InsertarEntradas:
        this.registro = new InsertarEntradas(dateTime, dateController);

        //se inicia el panel de la clase ConsultarEntradas:
        this.consultar = new ConsultarEntradas();

        //Se manda a llamar la configuracion y el formato de la vista:
        prepareMenu();

        //Se establece el layout de la vista principal para el acomodo de todo:
        this.setLayout( new PanelEntradasLayout(this));

    }

    /**
     *
     * Retorna el JPanel en algunos paneles esta funcion puede devolver el
     * JPanel con el Scroll implementado de ser requerido, en este caso no hubo necesidad de ello.
     *
     * @return {@link Component}
     */
    public Component getview()
    {
        return this;
    }

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

        // se inicia cada tab o pestaña:
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

        //se agregan dichos tabs a tab panel:
        this.getTABBEDPANE().addTab( tab1 );
        this.getTABBEDPANE().addTab( tab2 );

        //se crea un listener para ejecutar una accion al cambiar de pestaña en el submenu:
        this.getTABBEDPANE().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
                int selectedIndex = tabbedPane.getSelectedIndex();
                switch (selectedIndex)
                {
                        case 0:
                        {
                            resetConsultarEntradas();
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
     * Función que resetea la vista de insertar entradas dejando sus campos en sus valores por default.
     *
     */
    public void resetRegistros(){
        this.registro.cargaComboBoxs();
        this.registro.vaciarTextos();
        this.registro.getTABLA_DE_ENTRADAS().vaciarTabla();
    }

    /**
     *
     * Función que resetea la vista de consultar entradas dejando sus campos en sus valores por default.
     *
     */
    public void resetConsultarEntradas(){
        this.consultar.vaciarTextos();
        this.consultar.hideDatePickers();
        this.consultar.getTABLA_BUSQUEDA_ENTRADAS().vaciarTabla();
    }

    public ConsultarEntradas getConsultarEntrada(){
        return this.consultar;
    }

}
