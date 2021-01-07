package Forms.Components;

import Forms.Layouts;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.ArrayList;

public class Table extends JTable {

    //colores de la tabla
    private Color[] rowColors = {
            Color.decode("#EAEAEA"), // silver claro
            Color.decode("#C0C0C0"), // silver oscuro
    };

    private TableColumnModel modeloDeColumna;
    private JScrollPane scrollDeTabla;
    private WordWrapCellRenderer tableCellRenderer;
    private DefaultTableModel defaultTableModel;
    private DefaultTableCellRenderer defaultTableCellRenderer;
    
    private boolean useScroll = true; 

    private int size = 0;
    private int howManyCol = 0;
    private int[] columnWidths = null;
    private String[] headers;
    // -- CONSTRUCTOR:

    public Table(boolean useScrollbar, String[] headers, boolean sort, int multiplo){
        // -- primero capturo variables:
        this.useScroll = useScrollbar;
        this.setBackground(Color.DARK_GRAY);
        this.setRowHeight(40);
        this.setAutoCreateRowSorter(sort);

        this.headers = headers;
        this.howManyCol = headers.length;
        this.setSize(Layouts.size_x_max_tabla * multiplo, Layouts.size_y_max_tabla);
        this.size = this.getWidth();
        //System.out.println( "El tamaño de la tabla: " + this.size );
        // -- despues comiezo a fabricar la tabla:
        this.tableCellRenderer = new WordWrapCellRenderer();
        this.defaultTableModel = new DefaultTableModel();
        this.defaultTableCellRenderer = new DefaultTableCellRenderer();
        this.defaultTableCellRenderer.setHorizontalAlignment( JLabel.CENTER );
        this.defaultTableCellRenderer.setVerticalAlignment( JLabel.CENTER );

        this.columnModel = this.getColumnModel();

        if(useScrollbar){
            this.scrollDeTabla     = new JScrollPane(this,
                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
            );

            this.scrollDeTabla.setBounds(50, 570, 1160, 300);
        }

        this.setDefaultRenderer(Object.class, defaultTableCellRenderer);
        
        this.setModel(defaultTableModel);
        this.setPreferredScrollableViewportSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setFillsViewportHeight(true);
        this.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );

        defaultTableModel.setColumnIdentifiers(headers);



        int[] widths = new int[headers.length];

        for (int i = 0; i < widths.length; i++)
        {
            widths[ i ] = headers[ i ].length();
        }

        this.setColumnWidths( widths );
        /*for(int i = 0; i < headers.length; i++) {

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

        }*/



    }


    // -- METODOS DEL COMPONENTE:

    public boolean isEmpty(){
        return this.getRowCount() == 0;
    }

    public void setColumnWidths(int[] widths){
        int nrCols = this.howManyCol;
        if(nrCols==0||widths==null){
            return;
        }

        this.columnWidths = widths.clone();
        int totalWidth = this.size;

        int totalWidthRequested = 0;
        int nrRequestedWidths = columnWidths.length;
        int defaultWidth = ( int ) Math.floor( ( double ) totalWidth / ( double ) nrCols);

        for(int col=0;col<nrCols;col++){
            int width = 0
                    ;
            if(columnWidths.length>col){
                width = columnWidths[col];
            }

            totalWidthRequested += width;
        }

        //Note: for the not defined columns: use the defaultWidth
        if(nrRequestedWidths<nrCols){
            System.out.println("Setting column widths: nr of columns do not match column widths requested");
            totalWidthRequested += ( ( nrCols - nrRequestedWidths ) * defaultWidth );
        }
        //calculate the scale for the column width
        double factor = ( double ) totalWidth / ( double ) totalWidthRequested;

        for(int col = 0; col < nrCols; col++){
            int width = defaultWidth;
            if( columnWidths.length > col ){
                //scale the requested width to the current table width
                width = ( int ) Math.floor( factor * ( double ) columnWidths[ col ] );
            }

            if(col == (this.howManyCol - 1))
            {
                this.getColumnModel().getColumn(col).setMaxWidth(0);
                this.getColumnModel().getColumn(col).setPreferredWidth(0);
                this.getColumnModel().getColumn(col).setWidth(0);
            }
            else {
                this.getColumnModel().getColumn(col).setMaxWidth(width);
                this.getColumnModel().getColumn(col).setPreferredWidth(width);
                this.getColumnModel().getColumn(col).setWidth(width);
            }

        }
        this.removeColumn(this.getColumnModel().getColumn(this.howManyCol - 1));

    }

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
            String valor = (String) this.getValueAt(i, ( this.howManyCol - 2 ));
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

    public WordWrapCellRenderer getTableCellRenderer() {
        return tableCellRenderer;
    }

    public void setTableCellRenderer(WordWrapCellRenderer tableCellRenderer) {
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

    public ArrayList<ArrayList<String>> getRows(){

        ArrayList<ArrayList<String>> lists = new ArrayList<ArrayList<String>>();

        for (int fila = 0; fila < this.getRowCount(); fila++)
        {
            ArrayList<String> list = new ArrayList<String>();

            for (int columna = 0; columna < this.getColumnCount(); columna++)
            {
                String value = String.valueOf( this.getValueAt(fila, columna) );
                list.add( value );
                System.out.println(value);
            }
            System.out.println();

            lists.add(list);
        }


        return lists;
    }



    // -- METODOS OVERRIDE:

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component comp = super.prepareRenderer(renderer, row, column);


        if(!comp.getBackground().equals(getSelectionBackground())) {
            int indexColor = Integer.valueOf((String) this.getModel().getValueAt(row, (this.headers.length - 1) ));

           // System.out.println( indexColor );

            comp.setBackground(rowColors[indexColor]);
        }

        return comp;
    }



    static class WordWrapCellRenderer extends JTextArea implements TableCellRenderer {
        WordWrapCellRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value.toString());
            setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);
            if (table.getRowHeight(row) != getPreferredSize().height) {
                table.setRowHeight(row, getPreferredSize().height);
            }
            return this;
        }
    }

}
