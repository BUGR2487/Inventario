package Forms.Components;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class Table extends JTable {

    //colores de la tabla
    private Color[] rowColors = {
            Color.decode("#EAEAEA"), // silver claro
            Color.decode("#C0C0C0"), // silver oscuro
    };

    private TableColumnModel modeloDeColumna;
    private JScrollPane scrollDeTabla;
    private TextAreaRenderer tableCellRenderer;
    private DefaultTableModel defaultTableModel;
    private DefaultTableCellRenderer defaultTableCellRenderer;
    
    private boolean useScroll = true; 

    private int size = 0;
    // -- CONSTRUCTOR:

    public Table(boolean useScrollbar, String[] headers, boolean sort){
        // -- primero capturo variables:
        this.useScroll = useScrollbar;
        this.setBackground(Color.DARK_GRAY);
        this.setRowHeight(40);
        this.setAutoCreateRowSorter(sort);

        this.size = headers.length;

        // -- despues comiezo a fabricar la tabla:
        this.tableCellRenderer = new TextAreaRenderer();
        this.defaultTableModel = new DefaultTableModel();
        this.defaultTableCellRenderer = new DefaultTableCellRenderer();
        this.columnModel = this.getColumnModel();

        if(useScrollbar){
            this.scrollDeTabla     = new JScrollPane(this,
                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
            );

            this.scrollDeTabla.setBounds(50, 570, 1160, 300);
        }
        
        this.setDefaultRenderer(Object.class, this.tableCellRenderer);
        
        this.setModel(defaultTableModel);
        this.setPreferredScrollableViewportSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setFillsViewportHeight(true);
        this.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );

        defaultTableModel.setColumnIdentifiers(headers);


        
        this.defaultTableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for(int i = 0; i < headers.length; i++) {

            this.getColumnModel().getColumn(i).setCellRenderer(defaultTableCellRenderer);

            if(i == 0) {
                this.columnModel.getColumn(i).setMaxWidth(headers[i].length() * 30);
                this.columnModel.getColumn(i).setMinWidth(headers[i].length() * 30);
                this.columnModel.getColumn(i).setPreferredWidth(headers[i].length() * 30);
                this.columnModel.getColumn(i).setResizable( false );
            }
            else if(i < (headers.length - 1) ) {
                this.columnModel.getColumn(i).setMaxWidth(headers[i].length() * 30);
                this.columnModel.getColumn(i).setMinWidth(headers[i].length() * 30);
                this.columnModel.getColumn(i).setPreferredWidth(headers[i].length() * 30);
                this.columnModel.getColumn(i).setResizable( false );
            }
            else if( i == (headers.length - 1) )
            {
                this.columnModel.getColumn(i).setMaxWidth(0);
                this.columnModel.getColumn(i).setMinWidth(0);
                this.columnModel.getColumn(i).setPreferredWidth(0);
            }

        }



    }


    // -- METODOS DEL COMPONENTE:

    public Component getView(){
        return (this.isUseScroll())? this.scrollDeTabla : this;
    }

    public int sumatoriaCantidadUnidad()
    {
        int fila = this.getRowCount();
        int i;
        int sumatoria = 0;
        StringBuilder valores= new StringBuilder();

        for (i = 0; i < fila; i++)
        {
            String valor = (String) this.getValueAt(i, 5);
            sumatoria += Integer.parseInt(valor);
            valores.append(valor);

            if (i < (fila - 1))
            {
                valores.append(", ");
            }
        }

        System.out.println("Valores de la columna Cantidad/Unidad: " + valores);
        System.out.println("La sumatoria de la columna Cantidad/Unidad es: " + sumatoria);
        return sumatoria;
    }

    public void vaciarTabla()
    {
        this.defaultTableModel.setRowCount(0);
    }

    public void eliminarFila()
    {
        int[] rowsSelected = this.getSelectedRows();

        if(rowsSelected.length > 0)
        {

            for(int i=0;i<rowsSelected.length;i++){
                this.defaultTableModel.removeRow(rowsSelected[i]-i);
            }

            int rows = this.defaultTableModel.getRowCount();
            int veces = 0;
            int valor = 0;

            for (int i = 0, row = 1 ;i < rows; i++, row++)
            {
                this.defaultTableModel.setValueAt( row, i, 0);
                this.defaultTableModel.setValueAt( String.valueOf( valor ), i, 6);
                if(veces == 1) {
                    veces = 0;
                    valor = (valor == 1)? 0 : (valor + 1);
                    continue;
                }
                veces++;
            }

        }
        else
        {
            JOptionPane.showMessageDialog(this, "Fila no seleccionada", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void addRow(String[] row)
    {
        this.defaultTableModel.addRow(row);
    }

    // -- GET'S Y SET'S:

    public Color[] getRowColors() {
        return rowColors;
    }

    public void setRowColors(Color[] rowColors) {
        this.rowColors = rowColors;
    }

    public TableColumnModel getModeloDeColumna() {
        return modeloDeColumna;
    }

    public void setModeloDeColumna(TableColumnModel modeloDeColumna) {
        this.modeloDeColumna = modeloDeColumna;
    }

    public JScrollPane getScrollDeTabla() {
        return scrollDeTabla;
    }

    public void setScrollDeTabla(JScrollPane scrollDeTabla) {
        this.scrollDeTabla = scrollDeTabla;
    }

    public TextAreaRenderer getTableCellRenderer() {
        return tableCellRenderer;
    }

    public void setTableCellRenderer(TextAreaRenderer tableCellRenderer) {
        this.tableCellRenderer = tableCellRenderer;
    }

    public DefaultTableModel getDefaultTableModel() {
        return defaultTableModel;
    }

    public void setDefaultTableModel(DefaultTableModel defaultTableModel) {
        this.defaultTableModel = defaultTableModel;
    }

    public DefaultTableCellRenderer getDefaultTableCellRenderer() {
        return defaultTableCellRenderer;
    }

    public void setDefaultTableCellRenderer(DefaultTableCellRenderer defaultTableCellRenderer) {
        this.defaultTableCellRenderer = defaultTableCellRenderer;
    }

    public boolean isUseScroll() {
        return useScroll;
    }

    public void setUseScroll(boolean useScroll) {
        this.useScroll = useScroll;
    }


    // -- METODOS OVERRIDE:

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component comp = super.prepareRenderer(renderer, row, column);

        if(!comp.getBackground().equals(getSelectionBackground())) {
            int indexColor = Integer.valueOf((String) this.getModel().getValueAt(row, (this.size - 1) ));



            comp.setBackground(rowColors[indexColor]);
        }

        return comp;
    }



    public static class TextAreaRenderer extends JTextArea
            implements TableCellRenderer {

        public TextAreaRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
        }

        public Component getTableCellRendererComponent(JTable jTable,
                                                       Object obj, boolean isSelected, boolean hasFocus, int row,
                                                       int column) {
            setText((String)obj);
            return this;
        }
    }

}
