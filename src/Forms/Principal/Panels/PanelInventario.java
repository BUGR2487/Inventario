package Forms.Principal.Panels;

import Forms.Principal.Layouts.InventarioLayout;
import Tools.Config;
import Tools.DataBase.Inventario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.sql.SQLException;

public class PanelInventario extends JPanel implements ActionListener, KeyListener, Panel{


    //etiquetas:

    private final JLabel CODIGO_BARRAS_LB      = new JLabel("C\u00f3digo de barras");
    private final JLabel DISENO_LB             = new JLabel("Dise\u00f1o");
    private final JLabel CODIGO_INTERNO_LB     = new JLabel("C\u00f3digo interno");

    private final JLabel CLIENTE_LB            = new JLabel("Cliente");
    private final JLabel CANTIDAD_PALLET_LB    = new JLabel("Cantidad por pallet");
    private final JLabel PRODUCTO_LB           = new JLabel("Producto");

    //campos de textos:

    private final JTextField CODIGO_BARRAS_TXT      = new JTextField();
    private final JTextField DISENO_TXT             = new JTextField();
    private final JTextField CODIGO_INTERNO_TXT     = new JTextField();

    private final JTextField CLIENTE_TXT            = new JTextField();
    private final JTextField CANTIDAD_PALLET_TXT    = new JTextField();
    private final JTextField PRODUCTO_TXT           = new JTextField();

    // botones:

    private final JButton REGISTRAR_ENTRADA = new JButton("Registrar en el inventario.");

    // -- scroll de la vista:
    private final JScrollPane PANE = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

    private final Inventario INVENTARIO = new Inventario();


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
        this.CANTIDAD_PALLET_LB.setFont(this.FUENTE_GENERAL_LB);
        this.PRODUCTO_LB.setFont(this.FUENTE_GENERAL_LB);


        this.CODIGO_BARRAS_TXT.setFont(this.FUENTE_GENERAL_TXT);
        this.DISENO_TXT.setFont(this.FUENTE_GENERAL_TXT);
        this.CODIGO_INTERNO_TXT.setFont(this.FUENTE_GENERAL_TXT);
        this.CLIENTE_TXT.setFont(this.FUENTE_GENERAL_TXT);
        this.CANTIDAD_PALLET_TXT.setFont(this.FUENTE_GENERAL_TXT);
        this.PRODUCTO_TXT.setFont(this.FUENTE_GENERAL_TXT);

        this.CODIGO_BARRAS_TXT.setHorizontalAlignment(JTextField.CENTER);
        this.DISENO_TXT.setHorizontalAlignment(JTextField.CENTER);
        this.CODIGO_INTERNO_TXT.setHorizontalAlignment(JTextField.CENTER);
        this.CLIENTE_TXT.setHorizontalAlignment(JTextField.CENTER);
        this.CANTIDAD_PALLET_TXT.setHorizontalAlignment(JTextField.CENTER);
        this.PRODUCTO_TXT.setHorizontalAlignment(JTextField.CENTER);

        this.REGISTRAR_ENTRADA.setFont(this.FUENTE_GENERAL_TXT);

        this.CODIGO_BARRAS_TXT.addKeyListener(this);
        this.DISENO_TXT.addKeyListener(this);
        this.CODIGO_INTERNO_TXT.addKeyListener(this);
        this.CLIENTE_TXT.addKeyListener(this);
        this.CANTIDAD_PALLET_TXT.addKeyListener(this);
        this.PRODUCTO_TXT.addKeyListener(this);

        this.REGISTRAR_ENTRADA.addActionListener(this);
    }

    public boolean camposVacios(){
        return  this.CODIGO_BARRAS_TXT   .getText().isEmpty() ||
                this.DISENO_TXT          .getText().isEmpty() ||
                this.CODIGO_INTERNO_TXT  .getText().isEmpty() ||

                this.CLIENTE_TXT         .getText().isEmpty() ||
                this.CANTIDAD_PALLET_TXT .getText().isEmpty() ||
                this.PRODUCTO_TXT        .getText().isEmpty();
    }

    public void vaciarTextos()
    {
            this.CODIGO_BARRAS_TXT   .setText( "" );
            this.DISENO_TXT          .setText( "" );
            this.CODIGO_INTERNO_TXT  .setText( "" );

            this.CLIENTE_TXT         .setText( "" );
            this.CANTIDAD_PALLET_TXT .setText( "" );
            this.PRODUCTO_TXT        .setText( "" );
    }

    // -- GET'S Y SET'S

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

    public JLabel getCANTIDAD_PALLET_LB() {
        return CANTIDAD_PALLET_LB;
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

    public JTextField getCANTIDAD_PALLET_TXT() {
        return CANTIDAD_PALLET_TXT;
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
                this.INVENTARIO.setCodigoBarras(this.getCODIGO_BARRAS_TXT().getText());
                this.INVENTARIO.setDiseno(Integer.parseInt(this.getDISENO_TXT().getText()));
                this.INVENTARIO.setCodigoInterno(this.getCODIGO_INTERNO_TXT().getText());
                this.INVENTARIO.setCliente(this.getCLIENTE_TXT().getText());
                this.INVENTARIO.setProducto(this.getPRODUCTO_TXT().getText());
                this.INVENTARIO.setCantidadPorPallet(Integer.parseInt(this.getCANTIDAD_PALLET_TXT().getText()));

                this.INVENTARIO.insertarInventario();

                vaciarTextos();

                JOptionPane.showMessageDialog(this,
                        "Producto registrado",
                        "Registro de Productos",
                        JOptionPane.INFORMATION_MESSAGE);

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
                this.getCANTIDAD_PALLET_TXT().isFocusOwner())
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
