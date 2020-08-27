package Forms.Principal.Panels;

import Forms.Components.Table;
import Forms.Principal.Layouts.SalidasLayout;
import Tools.Config;
import Tools.DataBase.Salidas;
import Tools.PDF;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.sql.SQLException;

public class PanelSalidas extends JPanel implements ActionListener, KeyListener, Panel{

    // -- VARIABLES:
    // -- etiquetas:

    private final JLabel N_PEDIDO_LB            = new JLabel("No. Pedido");
    private final JLabel SELLOS_LB              = new JLabel("Sellos");

    private final JLabel CANT_PALLET_LB         = new JLabel("Cantidad pallet");
    private final JLabel CANT_POR_PALETT_LB     = new JLabel("Cantidad por pallet");

    private final JLabel TOTAL_UNIDADES_LB      = new JLabel("Total de unidades");
    private final JLabel FECHA_ENTREGA_LB       = new JLabel("Fecha de entrega");

    private final JLabel CHOFER_LB              = new JLabel("Chofer");
    private final JLabel EMPRESA_LB             = new JLabel("Empresa");

    private final JLabel PLACAS_LB              = new JLabel("Placas");
    private final JLabel TRACTO_LB              = new JLabel("Tracto camion");

    // -- listas:

    private final JComboBox N_PEDIDO_CMB             = new JComboBox();

    // -- campos de texto:

    private final JTextField SELLOS_TXT              = new JTextField();
    private final JTextField CANT_PALLET_TXT         = new JTextField();
    private final JTextField CANT_POR_PALETT_TXT     = new JTextField();
    private final JTextField TOTAL_UNIDADES_TXT      = new JTextField();
    private final JTextField FECHA_ENTREGA_TXT       = new JTextField();
    private final JTextField CHOFER_TXT              = new JTextField();
    private final JTextField EMPRESA_TXT             = new JTextField();
    private final JTextField PLACAS_TXT              = new JTextField();
    private final JTextField TRACTO_TXT              = new JTextField();

    // -- tabla de salidas:
    private final Table TABLA_SALIDAS = new Table(true, new String[] {
                                                                                    "#",
                                                                                    "Folio",
                                                                                    "Código de barras",
                                                                                    "Diseño",
                                                                                    "Producto",
                                                                                    "Cantidad/Unidad",
                                                                                    "color"
                                                                                    });

    // -- botones:

    private final JButton REGISTRAR_SALIDA = new JButton("Registrar salida.");

    // -- scroll de la vista:
    private final JScrollPane PANE = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

    private final Salidas SALIDAS = new Salidas();

    // -- CONSTRUCTOR:

    public PanelSalidas() throws Config.EmptyProperty, SQLException, Config.ReadException {

        this.prepareAll();
        this.setLayout(new SalidasLayout(this));
        this.PANE.getViewport().add(this);
    }

    // -- METODOS DE LA CLASE:

    public Component getview()
    {
        return this.PANE;
    }

    public void prepareAll() {

        // -- acomodo de fuentes:

        //etiquetas:
        this.N_PEDIDO_LB            .setFont(this.FUENTE_GENERAL_LB);
        this.SELLOS_LB              .setFont(this.FUENTE_GENERAL_LB);
        this.CANT_PALLET_LB         .setFont(this.FUENTE_GENERAL_LB);
        this.CANT_POR_PALETT_LB     .setFont(this.FUENTE_GENERAL_LB);
        this.TOTAL_UNIDADES_LB      .setFont(this.FUENTE_GENERAL_LB);
        this.FECHA_ENTREGA_LB       .setFont(this.FUENTE_GENERAL_LB);
        this.CHOFER_LB              .setFont(this.FUENTE_GENERAL_LB);
        this.EMPRESA_LB             .setFont(this.FUENTE_GENERAL_LB);
        this.PLACAS_LB              .setFont(this.FUENTE_GENERAL_LB);
        this.TRACTO_LB              .setFont(this.FUENTE_GENERAL_LB);

        //campos de texto:

        this.SELLOS_TXT             .setFont(this.FUENTE_GENERAL_TXT);
        this.CANT_PALLET_TXT        .setFont(this.FUENTE_GENERAL_TXT);
        this.CANT_POR_PALETT_TXT    .setFont(this.FUENTE_GENERAL_TXT);
        this.TOTAL_UNIDADES_TXT     .setFont(this.FUENTE_GENERAL_TXT);
        this.FECHA_ENTREGA_TXT      .setFont(this.FUENTE_GENERAL_TXT);
        this.CHOFER_TXT             .setFont(this.FUENTE_GENERAL_TXT);
        this.EMPRESA_TXT            .setFont(this.FUENTE_GENERAL_TXT);
        this.PLACAS_TXT             .setFont(this.FUENTE_GENERAL_TXT);
        this.TRACTO_TXT             .setFont(this.FUENTE_GENERAL_TXT);


        this.N_PEDIDO_CMB           .setFont(this.FUENTE_GENERAL_TXT);

        this.REGISTRAR_SALIDA       .setFont(this.FUENTE_GENERAL_TXT);

        this.SELLOS_TXT             .setHorizontalAlignment(JTextField.CENTER);
        this.CANT_PALLET_TXT        .setHorizontalAlignment(JTextField.CENTER);
        this.CANT_POR_PALETT_TXT    .setHorizontalAlignment(JTextField.CENTER);
        this.TOTAL_UNIDADES_TXT     .setHorizontalAlignment(JTextField.CENTER);
        this.FECHA_ENTREGA_TXT      .setHorizontalAlignment(JTextField.CENTER);
        this.CHOFER_TXT             .setHorizontalAlignment(JTextField.CENTER);
        this.EMPRESA_TXT            .setHorizontalAlignment(JTextField.CENTER);
        this.PLACAS_TXT             .setHorizontalAlignment(JTextField.CENTER);
        this.TRACTO_TXT             .setHorizontalAlignment(JTextField.CENTER);


        // -- acomodp de los listener:

        // - key:

        this.SELLOS_TXT             .addKeyListener( this );
        this.CANT_PALLET_TXT        .addKeyListener( this );
        this.CANT_POR_PALETT_TXT    .addKeyListener( this );
        this.TOTAL_UNIDADES_TXT     .addKeyListener( this );
        this.FECHA_ENTREGA_TXT      .addKeyListener( this );
        this.CHOFER_TXT             .addKeyListener( this );
        this.EMPRESA_TXT            .addKeyListener( this );
        this.PLACAS_TXT             .addKeyListener( this );
        this.TRACTO_TXT             .addKeyListener( this );

        this.cargaComboBox();

        // - action:
        this.REGISTRAR_SALIDA       .addActionListener( this );

        //item change:
        this.N_PEDIDO_CMB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED)
                {
                    actionOnItemChange();
                }
            }
        });

    }

    public boolean camposVacios() {
         return this.SELLOS_TXT.getText().isEmpty() ||
                this.CANT_PALLET_TXT.getText().isEmpty() ||
                this.CANT_POR_PALETT_TXT.getText().isEmpty() ||
                this.TOTAL_UNIDADES_TXT.getText().isEmpty() ||
                this.FECHA_ENTREGA_TXT.getText().isEmpty() ||
                this.CHOFER_TXT.getText().isEmpty() ||
                this.EMPRESA_TXT.getText().isEmpty() ||
                this.PLACAS_TXT.getText().isEmpty() ||
                this.TRACTO_TXT.getText().isEmpty();
    }

    public void vaciarTextos() {
        this.SELLOS_TXT             .setText( "" );
        this.CANT_PALLET_TXT        .setText( "" );
        this.CANT_POR_PALETT_TXT    .setText( "" );
        this.TOTAL_UNIDADES_TXT     .setText( "" );
        this.FECHA_ENTREGA_TXT      .setText( "" );
        this.CHOFER_TXT             .setText( "" );
        this.EMPRESA_TXT            .setText( "" );
        this.PLACAS_TXT             .setText( "" );
        this.TRACTO_TXT             .setText( "" );
        this.N_PEDIDO_CMB           .setSelectedIndex(0);
    }

    private void guardaSalida() {
        if(this.camposVacios())
        {
            JOptionPane.showMessageDialog(this,
                                            "Falta llenar campos",
                                            "Error",
                                            JOptionPane.ERROR_MESSAGE);
        }
        else
        {

            String nPedido = this.getN_PEDIDO_CMB().getSelectedItem().toString();

            this.SALIDAS.setNumPedido(nPedido);
            this.SALIDAS.setSellos(this.getSELLOS_TXT().getText());
            this.SALIDAS.setCantidadPallet(Integer.parseInt(this.getCANT_PALLET_TXT().getText()));
            this.SALIDAS.setCantidadPorPallet(Integer.parseInt(this.getCANT_POR_PALETT_TXT().getText()));
            this.SALIDAS.setTotalUnidades(Integer.parseInt(this.getTOTAL_UNIDADES_TXT().getText()));
            this.SALIDAS.setFechaEntrega(this.getFECHA_ENTREGA_TXT().getText());
            this.SALIDAS.setChofer(this.getCHOFER_TXT().getText());
            this.SALIDAS.setEmpresa(this.getEMPRESA_TXT().getText());
            this.SALIDAS.setPlacas(this.getPLACAS_TXT().getText());
            this.SALIDAS.setTractoCamion(this.getTRACTO_TXT().getText());

            this.SALIDAS.insertarSalidas();

            this.vaciarTextos();

            PDF.getInstance().generarPDF(this.SALIDAS);

            JOptionPane.showMessageDialog(this,
                                            "Salida registrada",
                                            "Registro de salidas",
                                            JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void actionOnItemChange(){
        SALIDAS.setNumPedido(getN_PEDIDO_CMB().getSelectedItem().toString());
        SALIDAS.busquedaNumPedido(this);
    }

    public void cargaComboBox(){
        this.getN_PEDIDO_CMB().removeAllItems();
        this.getN_PEDIDO_CMB().setModel(this.SALIDAS.obtenerPedidos());
    }

    // -- GET'S Y SET'S:

    public Table getTABLA_SALIDAS() {
        return TABLA_SALIDAS;
    }

    public JButton getREGISTRAR_SALIDA() {
        return REGISTRAR_SALIDA;
    }

    public JLabel getN_PEDIDO_LB() {
        return N_PEDIDO_LB;
    }

    public JLabel getSELLOS_LB() {
        return SELLOS_LB;
    }

    public JLabel getCANT_PALLET_LB() {
        return CANT_PALLET_LB;
    }

    public JLabel getCANT_POR_PALETT_LB() {
        return CANT_POR_PALETT_LB;
    }

    public JLabel getTOTAL_UNIDADES_LB() {
        return TOTAL_UNIDADES_LB;
    }

    public JLabel getFECHA_ENTREGA_LB() {
        return FECHA_ENTREGA_LB;
    }

    public JLabel getCHOFER_LB() {
        return CHOFER_LB;
    }

    public JLabel getEMPRESA_LB() {
        return EMPRESA_LB;
    }

    public JLabel getPLACAS_LB() {
        return PLACAS_LB;
    }

    public JLabel getTRACTO_LB() {
        return TRACTO_LB;
    }

    public JComboBox getN_PEDIDO_CMB() {
        return N_PEDIDO_CMB;
    }

    public JTextField getSELLOS_TXT() {
        return SELLOS_TXT;
    }

    public JTextField getCANT_PALLET_TXT() {
        return CANT_PALLET_TXT;
    }

    public JTextField getCANT_POR_PALETT_TXT() {
        return CANT_POR_PALETT_TXT;
    }

    public JTextField getTOTAL_UNIDADES_TXT() {
        return TOTAL_UNIDADES_TXT;
    }

    public JTextField getFECHA_ENTREGA_TXT() {
        return FECHA_ENTREGA_TXT;
    }

    public JTextField getCHOFER_TXT() {
        return CHOFER_TXT;
    }

    public JTextField getEMPRESA_TXT() {
        return EMPRESA_TXT;
    }

    public JTextField getPLACAS_TXT() {
        return PLACAS_TXT;
    }

    public JTextField getTRACTO_TXT() {
        return TRACTO_TXT;
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

        if(e.getSource() == this.REGISTRAR_SALIDA)
        {
            this.guardaSalida();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(this.getSELLOS_TXT().isFocusOwner())
        {
            if (!Character.isLetter(e.getKeyChar()) &&
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
            this.getREGISTRAR_SALIDA().doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }



}
