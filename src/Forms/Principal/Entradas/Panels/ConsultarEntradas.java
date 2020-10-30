package Forms.Principal.Entradas.Panels;

import Forms.Components.DatePicker;
import Forms.Components.Table;
import Forms.Panel;
import Forms.Principal.Entradas.Layouts.ConsultarEntradasLayout;

import javax.swing.*;
import java.awt.*;

public class ConsultarEntradas extends JPanel implements Panel {

    
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

    }
}
