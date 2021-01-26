package Forms.Principal.Entradas.Panels;

import Forms.Components.DatePicker;
import Forms.Components.Table;
import Forms.Panel;
import Forms.Principal.Entradas.Layouts.ConsultarEntradasLayout;
import Tools.DataBase.Entradas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;

//PARA IR RAPIDO DE SECCION A SECCION USA CTRL + F O CMD + F(EN MAC) Y BUSCA LAS SIGUIENTES PALABRAS:
// -- VARIABLES Y CONSTANTES
// -- CONSTRUCTOR:
// -- METODOS DE LA CLASE:
// -- GET'S Y SET'S
// -- METODOS OVERRIDE:

/**
 * Panel ConsultarEntradas, donde el usuario podrá consultar las entradas registradas.
 *<br>
 *<br>
 * Esta clase hereda de {@link JPanel} e implementa de las clases:
 * - {@link ActionListener} <br>
 * - {@link KeyListener} <br>
 * - {@link Panel}: src/Forms/Panel.java
 */
public class ConsultarEntradas extends JPanel implements Panel, ActionListener {

    // -- VARIABLES Y CONSTANTES

    // Calendarios:
    private final DatePicker DATEPICKER_TO      = DatePicker.buildDatePicker( true );
    private final DatePicker DATEPICKER_FROM    = DatePicker.buildDatePicker( false );

    //estiquetas:
    private final JLabel DATEPICKER_TO_LB       = new JLabel( "Desde: " );
    private final JLabel DATEPICKER_FROM_LB     = new JLabel( "Hasta: " );

    // botones:
    private final JButton BUSCAR_BTN            = new JButton( "Buscar entradas." );

    //tablas:
    private final Table TABLA_BUSQUEDA_ENTRADAS = new Table(true,
                                                            new String[]{
                                                                    "#",
                                                                    "No.Orden",
                                                                    "No.Pedido",
                                                                    "Fecha Entrada",
                                                                    "Hora Entrada",
                                                                    "Codigo Barras",
                                                                    "Diseno",
                                                                    "Codigo Interno",
                                                                    "Cliente",
                                                                    "Producto",
                                                                    "Cantidad de Pallet's",
                                                                    "Cantidad por Pallet",
                                                                    "Total Unidades",
                                                                    "Condicion",
                                                                    "Observaciones",
                                                                    "Nombre del chofer",
                                                                    "Empresa",
                                                                    "Placas",
                                                                    "Tractocami\u00f3n",
                                                                    "Color"
                                                                    }, true, 2);

    // -- CONSTRUCTOR:

    /**
     * Contructor que genera el panel de busqueda de entradas por rango de fecha.
     */
    public ConsultarEntradas() {

        this.setLayout( new ConsultarEntradasLayout( this ));
        this.getBUSCAR_BTN().addActionListener( this );

    }

    // -- GET'S Y SET'S
    public DatePicker getDATEPICKER_TO() {
        return DATEPICKER_TO;
    }

    public DatePicker getDATEPICKER_FROM() {
        return DATEPICKER_FROM;
    }

    public JLabel getDATEPICKER_TO_LB() {
        return DATEPICKER_TO_LB;
    }

    public JLabel getDATEPICKER_FROM_LB() {
        return DATEPICKER_FROM_LB;
    }

    public JButton getBUSCAR_BTN() {
        return BUSCAR_BTN;
    }

    public Table getTABLA_BUSQUEDA_ENTRADAS() {
        return TABLA_BUSQUEDA_ENTRADAS;
    }

    public Component getview()
    {
        return this;
    }

    // -- METODOS DE LA CLASE:

    /**
     * Función que oculta los calendarios
     */
    public void hideDatePickers(){
        this.DATEPICKER_FROM.hidePopup();
        this.DATEPICKER_TO.hidePopup();
    }

    /**
     * Funcion que vuelca la informacion de las entradas
     * buscadas en la tabla de la interfaz para su visualización
     * @param data
     */
    public void dropInTable(ArrayList<Entradas> data){

        this.TABLA_BUSQUEDA_ENTRADAS.vaciarTabla();

        int num = 0;
        int veces = 0, valor = 0;

        for(Entradas entrada : data)
        {
            String[] row = new String[ 20 ];

            /* "#", */                      row[ 0 ] = String.valueOf( num );
            /* "No.Orden", */               row[ 1 ] = String.valueOf( entrada.getNumOrden() );
            /* "No.Pedido", */              row[ 2 ] = String.valueOf( entrada.getNumPedido() );

            /* "Fecha Entrada", */          row[ 3 ] = entrada.getFechaEntrada().toString();
            /* "Hora Entrada", */           row[ 4 ] = entrada.getHoraEntrada().toString();
            /* "Codigo Barras", */          row[ 5 ] = String.valueOf( entrada.getCodigoBarras() );

            /* "Diseno", */                 row[ 6 ] = String.valueOf( entrada.getDiseno() );
            /* "Codigo Interno", */         row[ 7 ] = String.valueOf( entrada.getCodigoInterno() );
            /* "Cliente", */                row[ 8 ] = String.valueOf( entrada.getCliente() );

            /* "Producto", */               row[ 9 ] = String.valueOf( entrada.getProducto() );
            /* "Cantidad de Pallet's",*/    row[ 10 ] = String.valueOf( entrada.getCantidadPallet() );
            /* "Cantidad por Pallet", */    row[ 11 ] = String.valueOf( entrada.getCantidadPorPallet() );

            /* "Total Unidades", */         row[ 12 ] = String.valueOf( entrada.getTotalUnidades() );
            /* "Condicion", */              row[ 13 ] = String.valueOf( entrada.getCondicion() );
            /* "Observaciones", */          row[ 14 ] = String.valueOf( entrada.getObservaciones() );

            /* "Nombre del chofer", */      row[ 15 ] = String.valueOf( entrada.getTransporte().getChofer() );
            /* "Empresa", */                row[ 16 ] = String.valueOf( entrada.getTransporte().getEmpresa() );
            /* "Placas", */                 row[ 17 ] = String.valueOf( entrada.getTransporte().getPlacas() );

            /* "Tractocami\u00f3n", */      row[ 18 ] = String.valueOf( entrada.getTransporte().getTractoCamion() );
            /* "Color" */                   row[ 19 ] = String.valueOf( valor );

            this.TABLA_BUSQUEDA_ENTRADAS.addRow( row );

            if(veces == 1) {
                veces = 0;
                num++;
                valor = (valor == 1)? 0 : (valor + 1);
                continue;
            }
            veces++;
            num++;


        }

    }

    // -- METODOS OVERRIDE:
    @Override
    public boolean camposVacios() {
        return false;
    }

    @Override
    public void vaciarTextos() {
        this.getTABLA_BUSQUEDA_ENTRADAS().vaciarTabla();
        this.getDATEPICKER_FROM().resetCalendar();
        this.getDATEPICKER_TO().resetCalendar();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getSource() == this.getBUSCAR_BTN() )
        {
            ArrayList<Entradas> data = Entradas.getEntradasPorRango(
                    this.DATEPICKER_FROM.getDateAsSqlDate(),
                    this.DATEPICKER_TO.getDateAsSqlDate()
            );

            if( data == null )
            {
                JOptionPane.showMessageDialog(this,
                        "No se han encontrado registros dentro de ese rango de fechas, " +
                                "verifique nuevamente e intente de nuevo.",
                        "Registros no encontrados.",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            dropInTable( data );

        }
    }
}
