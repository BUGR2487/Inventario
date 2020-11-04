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
import java.util.ArrayList;

public class ConsultarEntradas extends JPanel implements Panel, ActionListener {

    
    private final DatePicker DATEPICKER_TO      = DatePicker.buildDatePicker( true );
    private final DatePicker DATEPICKER_FROM    = DatePicker.buildDatePicker( false );

    private final JLabel DATEPICKER_TO_LB       = new JLabel( "Desde: " );
    private final JLabel DATEPICKER_FROM_LB     = new JLabel( "Hasta: " );

    private final JButton BUSCAR_BTN            = new JButton( "Buscar entradas." );

    private final Table TABLA_BUSQUEDA_ENTRADAS = new Table(true,
                                                            new String[]{
                                                                    "#",
                                                                    "No. Orden",
                                                                    "No. Pedido",
                                                                    "Fecha de entrega",
                                                                    "Hora de entrada",
                                                                    "C\u00f3digo de barras",
                                                                    "Dise\u00f1o",
                                                                    "C\u00f3digo interno",
                                                                    "Cliente",
                                                                    "Producto",
                                                                    "Cantidad de pallet's",
                                                                    "Cantidad por pallet's",
                                                                    "Total de unidades",
                                                                    "Condici\u00f3n",
                                                                    "Observaciones",
                                                                    "Nombre del chofer",
                                                                    "Empresa",
                                                                    "Placas",
                                                                    "Tractocami\u00f3n",
                                                                    "Color"
                                                                    }, true);

    public ConsultarEntradas() {

        this.setLayout( new ConsultarEntradasLayout( this ));
        this.getBUSCAR_BTN().addActionListener( this );

    }

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

    public void hideDatePickers(){
        this.DATEPICKER_FROM.hidePopup();
        this.DATEPICKER_TO.hidePopup();
    }

    public void dropInTable(ArrayList<Entradas> data){

        this.TABLA_BUSQUEDA_ENTRADAS.vaciarTabla();

        int num = 0;
        int veces = 0, valor = 0;

        for(Entradas entrada : data)
        {
            String[] row = new String[ 20 ];

            /* "#" */row[ 0 ] = String.valueOf( num );
            /* "No. Orden" */row[ 1 ] = String.valueOf( entrada.getNumOrden() );
            /* "No. Pedido" */row[ 2 ] = String.valueOf( entrada.getNumPedido() );
            /* "Fecha de entrega" */row[ 3 ] = entrada.getFechaEntrada().toString();
            /* "Hora de entrada" */row[ 4 ] = entrada.getHoraEntrada().toString();
            /* "C\u00f3digo de barras" */row[ 5 ] = String.valueOf( entrada.getCodigoBarras() );
            /* "Dise\u00f1o" */row[ 6 ] = String.valueOf( entrada.getDiseno() );
            /* "C\u00f3digo interno" */row[ 7 ] = String.valueOf( entrada.getCodigoInterno() );
            /* "Cliente" */row[ 8 ] = String.valueOf( entrada.getCliente() );
            /* "Producto" */row[ 9 ] = String.valueOf( entrada.getCantidadPallet() );
            /* "Cantidad de pallet's" */row[ 10 ] = String.valueOf( entrada.getCantidadPallet() );
            /* "Cantidad por pallet's" */row[ 11 ] = String.valueOf( entrada.getCantidadPorPallet() );
            /* "Total de unidades" */row[ 12 ] = String.valueOf( entrada.getTotalUnidades() );
            /* "Condici\u00f3n" */row[ 13 ] = String.valueOf( entrada.getCondicion() );
            /* "Observaciones" */row[ 14 ] = String.valueOf( entrada.getObservaciones() );
            /* "Nombre del chofer" */row[ 15 ] = String.valueOf( entrada.getTransporte().getChofer() );
            /* "Empresa" */row[ 16 ] = String.valueOf( entrada.getTransporte().getEmpresa() );
            /* "Placas" */row[ 17 ] = String.valueOf( entrada.getTransporte().getPlacas() );
            /* "Tractocami\u00f3n" */row[ 18 ] = String.valueOf( entrada.getTransporte().getTractoCamion() );
            /* color */row[ 19 ] = String.valueOf( valor );

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
    public Component getview()
    {
        return this;
    }

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
