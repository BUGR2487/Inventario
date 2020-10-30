package Forms.Principal.Salidas.Panels;

import Forms.Components.DatePicker;
import Forms.Components.Table;
import Forms.Panel;
import Forms.Principal.Salidas.Layouts.ConsultarSalidasLayout;
import Tools.DataBase.Salidas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ConsultarSalidasPanel extends JPanel implements Panel, ActionListener {

    private final DatePicker DATEPICKER_TO      = DatePicker.buildDatePicker( true );
    private final DatePicker DATEPICKER_FROM    = DatePicker.buildDatePicker( false );

    private final JLabel DATEPICKER_TO_LB       = new JLabel( "Desde: " );
    private final JLabel DATEPICKER_FROM_LB     = new JLabel( "Hasta: " );

    private final JButton BUSCAR_BTN            = new JButton( "Buscar salidas." );

    private final Table TABLA_BUSQUEDA_SALIDAS = new Table(true,
            new String[]{
                    "#",
                    "No. Pedido",
                    "Sellos",

                    "Cantidad por pallet's",
                    "Cantidad de pallet's",
                    "Total de unidades",

                    "Fecha de salida",
                    "Hora de salida",
                    "Nombre del chofer",

                    "Empresa",
                    "Placas",
                    "Tractocami\u00f3n",
                    "Color"

            }, true);


    public ConsultarSalidasPanel() {
        this.setLayout( new ConsultarSalidasLayout( this ));
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

    public Table getTABLA_BUSQUEDA_SALIDAS() {
        return TABLA_BUSQUEDA_SALIDAS;
    }

    public void hideDatePickers(){
        this.DATEPICKER_FROM.hidePopup();
        this.DATEPICKER_TO.hidePopup();
    }

    public Component getview()
    {
        return this;
    }

    public void dropInTable(ArrayList<Salidas> data){

        this.TABLA_BUSQUEDA_SALIDAS.vaciarTabla();

        int num = 0;
        int veces = 0, valor = 0;

        for(Salidas salida : data)
        {
            String[] row = new String[13];

            row[ 0 ] = String.valueOf( num );
            row[ 1 ] = String.valueOf( salida.getNumPedido() );
            row[ 2 ] = String.valueOf( salida.getSellos() );
            row[ 3 ] = String.valueOf( salida.getCantidadPallet() );
            row[ 4 ] = String.valueOf( salida.getCantidadPorPallet() );
            row[ 5 ] = String.valueOf( salida.getTotalUnidades() );
            row[ 6 ] = String.valueOf( salida.getFechaEntrega().toString() );
            row[ 7 ] = String.valueOf( salida.getHoraSalida().toString() );
            row[ 8 ] = String.valueOf( salida.getTransporte().getChofer() );
            row[ 9 ] = String.valueOf( salida.getTransporte().getEmpresa() );
            row[ 10 ] = String.valueOf( salida.getTransporte().getPlacas() );
            row[ 11 ] = String.valueOf( salida.getTransporte().getTractoCamion() );
            row[ 12 ] = String.valueOf( valor );

            this.TABLA_BUSQUEDA_SALIDAS.addRow( row );

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

    @Override
    public boolean camposVacios() {
        return false;
    }

    @Override
    public void vaciarTextos() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getSource() == this.getBUSCAR_BTN() )
        {
            ArrayList<Salidas> data = Salidas.getRangoDeSalidasPorFecha(
                    this.DATEPICKER_FROM.getDateAsSqlDate(),
                    this.DATEPICKER_TO.getDateAsSqlDate()
            );

            if(data == null) {

                JOptionPane.showMessageDialog(this,
                        "No se han encontrado registros dentro de ese rango de fechas, " +
                                "verifique nuevamente e intente de nuevo.",
                        "Registros no encontrados.",
                        JOptionPane.WARNING_MESSAGE);

                return;
            }

            this.dropInTable(data);

        }
    }
}
