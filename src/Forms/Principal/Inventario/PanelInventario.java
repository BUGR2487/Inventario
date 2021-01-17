package Forms.Principal.Inventario;

import Forms.Components.Table;
import Forms.Panel;
import Tools.Config;
import Tools.DataBase.Inventario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.sql.SQLException;
import java.util.ArrayList;

public class PanelInventario extends JPanel implements ActionListener, KeyListener, Panel {


    //etiquetas:

    private final JLabel CODIGO_BARRAS_LB      = new JLabel("C\u00f3digo de barras");
    private final JLabel DISENO_LB             = new JLabel("Dise\u00f1o");
    private final JLabel CODIGO_INTERNO_LB     = new JLabel("C\u00f3digo interno");

    private final JLabel CLIENTE_LB            = new JLabel("Cliente");
    private final JLabel CB_BUSQUEDA_LB        = new JLabel("Ingrese el c\u00f3digo de barras...");
    private final JLabel PRODUCTO_LB           = new JLabel("Producto");
    private final JLabel TOTAL_DE_STOCK_LB        = new JLabel("Producto");

    //campos de textos:

    private final JTextField CODIGO_BARRAS_TXT      = new JTextField();
    private final JTextField DISENO_TXT             = new JTextField();
    private final JTextField CODIGO_INTERNO_TXT     = new JTextField();

    private final JTextField CLIENTE_TXT            = new JTextField();
    private final JTextField CB_BUSQUEDA_TXT        = new JTextField();
    private final JTextField PRODUCTO_TXT           = new JTextField();

    // botones:

    private final JButton REGISTRAR_ENTRADA = new JButton("Registrar en el inventario.");


    //tablas:

    private final Table TABLA_DE_INVENTARIO = new Table(true, new String[]{
            "#",
            "C\u00f3digo Barras",
            "Diseno",
            "C\u00f3digo Interno",
            "Cliente",
            "Entradas Pallets",
            "Entrada Piezas",
            "Salida Pallets",
            "Salida Piezas",
            "Stock Pallets",
            "Stock Piezas",
            "Producto",
            "color"
    }, false, 1);

    // -- scroll de la vista:
    private final JScrollPane PANE = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);




    // -- CONTRUCTOR:

    public PanelInventario() throws Config.EmptyProperty, SQLException, Config.ReadException {

        this.prepareAll();
        this.setLayout(new InventarioLayout(this));
        this.PANE.getViewport().add(this);

    }


    // -- METODOS DE LA CLASE:
    public Component getview()
    {
        return this.PANE;
    }

    public void prepareAll(){
        this.CODIGO_BARRAS_LB.setFont(this.FUENTE_GENERAL_LB);
        this.DISENO_LB.setFont(this.FUENTE_GENERAL_LB);
        this.CODIGO_INTERNO_LB.setFont(this.FUENTE_GENERAL_LB);

        this.CLIENTE_LB.setFont(this.FUENTE_GENERAL_LB);
        this.CB_BUSQUEDA_LB.setFont(this.FUENTE_GENERAL_LB);
        this.PRODUCTO_LB.setFont(this.FUENTE_GENERAL_LB);
        this.TOTAL_DE_STOCK_LB.setFont(this.FUENTE_GENERAL_LB);


        this.CODIGO_BARRAS_TXT.setFont(this.FUENTE_GENERAL_TXT);
        this.DISENO_TXT.setFont(this.FUENTE_GENERAL_TXT);
        this.CODIGO_INTERNO_TXT.setFont(this.FUENTE_GENERAL_TXT);
        this.CLIENTE_TXT.setFont(this.FUENTE_GENERAL_TXT);
        this.CB_BUSQUEDA_TXT.setFont(this.FUENTE_GENERAL_TXT);
        this.PRODUCTO_TXT.setFont(this.FUENTE_GENERAL_TXT);

        this.CODIGO_BARRAS_TXT.setHorizontalAlignment(JTextField.CENTER);
        this.DISENO_TXT.setHorizontalAlignment(JTextField.CENTER);
        this.CODIGO_INTERNO_TXT.setHorizontalAlignment(JTextField.CENTER);
        this.CLIENTE_TXT.setHorizontalAlignment(JTextField.CENTER);
        this.CB_BUSQUEDA_TXT.setHorizontalAlignment(JTextField.CENTER);
        this.PRODUCTO_TXT.setHorizontalAlignment(JTextField.CENTER);

        this.REGISTRAR_ENTRADA.setFont(this.FUENTE_GENERAL_TXT);


        this.CODIGO_BARRAS_TXT.addKeyListener(this);
        this.DISENO_TXT.addKeyListener(this);
        this.CODIGO_INTERNO_TXT.addKeyListener(this);
        this.CLIENTE_TXT.addKeyListener(this);
        this.PRODUCTO_TXT.addKeyListener(this);

        this.CB_BUSQUEDA_TXT.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String dato = CB_BUSQUEDA_TXT.getText();
                CB_BUSQUEDA_TXT.setText(dato);
                repaint();
                getTABLA_DE_INVENTARIO().filtrarPor(1, dato);
            }
        });

        this.REGISTRAR_ENTRADA.addActionListener(this);

    }

    public boolean camposVacios(){
        return  this.CODIGO_BARRAS_TXT   .getText().isEmpty() ||
                this.DISENO_TXT          .getText().isEmpty() ||
                this.CODIGO_INTERNO_TXT  .getText().isEmpty() ||
                this.CLIENTE_TXT         .getText().isEmpty() ||
                this.PRODUCTO_TXT        .getText().isEmpty();
    }

    public void vaciarTextos()
    {
            this.CODIGO_BARRAS_TXT   .setText( "" );
            this.DISENO_TXT          .setText( "" );
            this.CODIGO_INTERNO_TXT  .setText( "" );

            this.CLIENTE_TXT         .setText( "" );
            this.PRODUCTO_TXT        .setText( "" );
    }

    public void loadTableInventory()
    {
        this.getTABLA_DE_INVENTARIO().vaciarTabla();

        ArrayList<Inventario> productos = Inventario.loadTable();

        if(productos != null)
        {
            /*


          1   IdInventarioPrimary	        int(11)
	      2	  codigoBarrasIndex	            varchar(45)
	      3	  diseno	                    varchar(45)
	      4	  codigoInterno	                varchar(45)
	      5	  cliente	                    varchar(45)
	      6	  entradasPallets	            int(45)
	      7	  entradaPiezas	                int(45)
	      8	  salidaPallets	                int(45)
	      9	  salidaPiezas	                int(45)
	      10  stockPallets	                int(45)
	      11  stockPiezas	                int(45)
	      12  producto	                    varchar(45)


               [ 0 ]    ( int )    #",
               [ 1 ]    ( string )    C\u00f3digo Barras",
               [ 2 ]    ( string )    Diseno",
               [ 3 ]    ( string )    C\u00f3digo Interno",
               [ 4 ]    ( string )    Cliente",
               [ 5 ]    ( int )    Entradas Pallets",
               [ 6 ]    ( int )    Entrada Piezas",
               [ 7 ]    ( int )    Salida Pallets",
               [ 8 ]    ( int )    Salida Piezas",
               [ 9 ]    ( int )    Stock Pallets",
               [ 10 ]   ( int )    Stock Piezas",
               [ 11 ]   ( string )    Producto",
               [ 12 ]   ( int )    color"
            */

            int i = 1;
            int colorIndex = 0;
            for( Inventario producto : productos)
            {
                String[] row = new String[13];

                row[ 0 ] = String.valueOf( i );
                row[ 1 ] = producto.getCodigoBarras();
                row[ 2 ] = producto.getDiseno();
                row[ 3 ] = producto.getCodigoInterno();
                row[ 4 ] = producto.getCliente();

                row[ 5 ] = String.valueOf( producto.getEntradasPallets() );
                row[ 6 ] = String.valueOf( producto.getEntradaPiezas() );
                row[ 7 ] = String.valueOf( producto.getSalidaPallets() );
                row[ 8 ] = String.valueOf( producto.getSalidaPiezas() );
                row[ 9 ] = String.valueOf( producto.getStockPallets() );
                row[ 10 ] = String.valueOf( producto.getStockPiezas() );

                row[ 11 ] = producto.getProducto();
                row[ 12 ] = String.valueOf( colorIndex );

                this.getTABLA_DE_INVENTARIO().addRow( row );

                colorIndex = (colorIndex == 1)? 0: (colorIndex + 1);
                i++;
            }

        }

        this.getTOTAL_DE_STOCK_LB().setText( Inventario.getTotalStock() );

    }

    // -- GET'S Y SET'S


    public Table getTABLA_DE_INVENTARIO() {
        return TABLA_DE_INVENTARIO;
    }

    public JLabel getCB_BUSQUEDA_LB() {
        return CB_BUSQUEDA_LB;
    }

    public JTextField getCB_BUSQUEDA_TXT() {
        return CB_BUSQUEDA_TXT;
    }

    public JLabel getTOTAL_DE_STOCK_LB() {
        return TOTAL_DE_STOCK_LB;
    }

    public JScrollPane getPANE() {
        return PANE;
    }

    public JLabel getCODIGO_BARRAS_LB() {
        return CODIGO_BARRAS_LB;
    }

    public JLabel getDISENO_LB() {
        return DISENO_LB;
    }

    public JLabel getCODIGO_INTERNO_LB() {
        return CODIGO_INTERNO_LB;
    }

    public JLabel getCLIENTE_LB() {
        return CLIENTE_LB;
    }

    public JLabel getPRODUCTO_LB() {
        return PRODUCTO_LB;
    }

    public JTextField getCODIGO_BARRAS_TXT() {
        return CODIGO_BARRAS_TXT;
    }

    public JTextField getDISENO_TXT() {
        return DISENO_TXT;
    }

    public JTextField getCODIGO_INTERNO_TXT() {
        return CODIGO_INTERNO_TXT;
    }

    public JTextField getCLIENTE_TXT() {
        return CLIENTE_TXT;
    }

    public JTextField getPRODUCTO_TXT() {
        return PRODUCTO_TXT;
    }

    public JButton getREGISTRAR_ENTRADA() {
        return REGISTRAR_ENTRADA;
    }

    // -- METODOS OVERRIDE:
    @Override
    protected void paintComponent(Graphics g){
        //obtengo rl graphics 2D del panel o lienzo como lo quieras ver:
        Graphics2D g2 = (Graphics2D) g.create();
        Rectangle clip = g2.getClipBounds();

        // saco las coordenadas maximas de la ventana:
        float x=this.getWidth();
        float y=this.getHeight();

        //el GradientPaint es el objeto que dibuja el degradado
        g2.setPaint(new GradientPaint(
                new Point2D.Float(x/2,0), //punto de donde inicia el primer color
                color2, // color 1
                new Point2D.Float(x,y), //punto donde empieza el segundo color
                color1)); // segundo color

        //dibujo en un rectangulo del tamaño de la ventana mi gradiente:
        g2.fillRect(clip.x, clip.y, clip.width, clip.height);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.getREGISTRAR_ENTRADA())
        {
            if(!this.camposVacios())
            {
               Inventario inventario = new Inventario();
                inventario.setCodigoBarras(this.getCODIGO_BARRAS_TXT().getText());
                inventario.setDiseno(this.getDISENO_TXT().getText());
                inventario.setCodigoInterno(this.getCODIGO_INTERNO_TXT().getText());
                inventario.setCliente(this.getCLIENTE_TXT().getText());
                inventario.setProducto(this.getPRODUCTO_TXT().getText());

                inventario.insertarInventario();

                vaciarTextos();

                JOptionPane.showMessageDialog(this,
                        "Producto registrado",
                        "Registro de Productos",
                        JOptionPane.INFORMATION_MESSAGE);

                loadTableInventory();
            }
            else
            {

                JOptionPane.showMessageDialog(this,
                        "Falta llenar campos",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);

            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(this.getCLIENTE_TXT().isFocusOwner())
        {
            if (!Character.isLetter(e.getKeyChar()) &&
                    !(e.getKeyChar() == KeyEvent.VK_SPACE) &&
                    !(e.getKeyChar() == KeyEvent.VK_BACK_SPACE))
            {
                e.consume();
            }
        }

        if(this.getDISENO_TXT().isFocusOwner() ||
                this.getCODIGO_BARRAS_TXT().isFocusOwner() ||
                this.getCODIGO_BARRAS_TXT().isFocusOwner())
        {
            if (Character.isLetter(e.getKeyChar()) &&
                    !(e.getKeyChar() == KeyEvent.VK_SPACE) &&
                    !(e.getKeyChar() == KeyEvent.VK_BACK_SPACE))
            {
                e.consume();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            this.getREGISTRAR_ENTRADA().doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
