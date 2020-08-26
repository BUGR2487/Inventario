package Forms.Principal.Panels;

import Forms.Components.Fecha_y_hora;
import Forms.Components.Table;
import Forms.Principal.Layouts.EntradasLayout;
import Tools.Config;
import Tools.DataBase.Entradas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PanelEntradas extends JPanel
        implements ActionListener, KeyListener, FocusListener, Panel {

    // -- Etiquetas:

    private final JLabel N_ORDEN_LB             = new JLabel("No. Orden");
    private final JLabel N_PEDIDO_LB            = new JLabel("No. Pedido");
    private final JLabel FECHA_ENTRADA_LB       = new JLabel("");

    private final JLabel CODIGO_DE_BARRAS_LB    = new JLabel("C\u00f3digo de barras");
    private final JLabel DISENO_LB              = new JLabel("Dise\u00f1o");
    private final JLabel CODIGO_INTERNO_LB      = new JLabel("C\u00f3digo interno");

    private final JLabel CLIENTE_LB             = new JLabel("Cliente");
    private final JLabel PRODUCTO_LB            = new JLabel("Producto");
    private final JLabel CANT_PALLET_LB         = new JLabel("Cantidad pallet");

    private final JLabel TOTAL_UNIDADES_LB      = new JLabel("Total de unidades");
    private final JLabel CANT_POR_PALETT_LB     = new JLabel("Cantidad por pallet");
    private final JLabel CONDICION_LB           = new JLabel("Condici\u00f3n");

    private final JLabel OBSERVACIONES_LB       = new JLabel("Observaciones");
    private final JLabel CHOFER_LB              = new JLabel("Chofer");
    private final JLabel EMPRESA_LB             = new JLabel("Empresa");

    private final JLabel PLACAS_LB              = new JLabel("Placas");
    private final JLabel TRACTO_LB              = new JLabel("Tracto camion");
    private final JLabel CANTIDAD_FOLIOS_LB     = new JLabel("Cantidad de folios");


    // -- Campos de texto:

    private final JTextField N_ORDEN_TXT             = new JTextField();
    private final JTextField N_PEDIDO_TXT            = new JTextField();
    private final JTextField FECHA_ENTRADA_TXT       = new JTextField();

    private final JTextField HORA_ENTRADA_TXT        = new JTextField();
    private final JTextField DISENO_TXT              = new JTextField();
    private final JTextField OBSERVACIONES_TXT       = new JTextField();

    private final JTextField CODIGO_INTERNO_TXT      = new JTextField();
    private final JTextField CLIENTE_TXT             = new JTextField();
    private final JTextField PRODUCTO_TXT            = new JTextField();

    private final JTextField CANT_PALLET_TXT         = new JTextField();
    private final JTextField TOTAL_UNIDADES_TXT      = new JTextField();
    private final JTextField CANT_POR_PALETT_TXT     = new JTextField();

    private final JTextField EMPRESA_TXT             = new JTextField();
    private final JTextField PLACAS_TXT              = new JTextField();
    private final JTextField TRACTO_TXT              = new JTextField();

    private final JTextField CANTIDAD_FOLIOS_TXT     = new JTextField();

    // -- ComboBox:

    private final JComboBox CODIGO_DE_BARRAS_CMB    = new JComboBox();
    private final JComboBox CONDICION_CMB           = new JComboBox();
    private final JComboBox CHOFER_CMB              = new JComboBox();

    // -- botones:
    private final JButton AGREGAR       = new JButton("Agregar a la tabla.");
    private final JButton GUARDAR       = new JButton("Guardar.");
    private final JButton ELIMINAR      = new JButton("Eliminar fila(s).");
    private final JButton LIMPIAR_TABLA = new JButton("Limpiar tabla.");

    // -- Runnable que actualiza la hora y fecha:

    private final Fecha_y_hora DATE_CONTROLLER = new Fecha_y_hora( this.FECHA_ENTRADA_LB );

    // -- Timer que se llamara al runnable anteriormente declarado:

    private final ScheduledExecutorService UPDATE_TIME = Executors.newSingleThreadScheduledExecutor();

    // -- scroll de la vista:
    private final JScrollPane PANE = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

    // -- variable del modelo:

    private final Entradas ENTRADAS_MDL = new Entradas();

    // -- Tabla de entradas:
    private final Table TABLA_DE_ENTRADAS = new Table(true, new String[] {
                                                                                    "#",
                                                                                    "Folio",
                                                                                    "Código de barras",
                                                                                    "Diseño",
                                                                                    "Producto",
                                                                                    "Cantidad/Unidad",
                                                                                    "color"
                                                                                    });

    // -- Variables de la sesion:
    private String user;
    
    // -- CONSTRUCTOR:

    public PanelEntradas() throws Config.EmptyProperty, SQLException, Config.ReadException {

        this.prepareAll();
        this.setLayout(new EntradasLayout(this));
        this.PANE.getViewport().add(this);
        this.UPDATE_TIME.scheduleAtFixedRate(this.DATE_CONTROLLER, 0, 1, TimeUnit.SECONDS);
    }

    // -- METODOS DE LA CLASE:

    public Component getview()
    {
        return this.PANE;
    }
    private void prepareAll(){

        this.cargaComboBoxs();

        // -- extablecimiento de las fuentes:

        // - etiquetas
        this.N_ORDEN_LB.setFont(this.FUENTE_GENERAL_LB);
        this.N_PEDIDO_LB.setFont(this.FUENTE_GENERAL_LB);
        this.FECHA_ENTRADA_LB.setFont(this.FUENTE_GENERAL_LB);
        this.CODIGO_DE_BARRAS_LB.setFont(this.FUENTE_GENERAL_LB);
        this.DISENO_LB.setFont(this.FUENTE_GENERAL_LB);
        this.CODIGO_INTERNO_LB.setFont(this.FUENTE_GENERAL_LB);
        this.CLIENTE_LB.setFont(this.FUENTE_GENERAL_LB);
        this.PRODUCTO_LB.setFont(this.FUENTE_GENERAL_LB);
        this.CANT_PALLET_LB.setFont(this.FUENTE_GENERAL_LB);
        this.TOTAL_UNIDADES_LB.setFont(this.FUENTE_GENERAL_LB);
        this.CANT_POR_PALETT_LB.setFont(this.FUENTE_GENERAL_LB);
        this.CONDICION_LB.setFont(this.FUENTE_GENERAL_LB);
        this.OBSERVACIONES_LB.setFont(this.FUENTE_GENERAL_LB);
        this.CHOFER_LB.setFont(this.FUENTE_GENERAL_LB);
        this.EMPRESA_LB.setFont(this.FUENTE_GENERAL_LB);
        this.PLACAS_LB.setFont(this.FUENTE_GENERAL_LB);
        this.TRACTO_LB.setFont(this.FUENTE_GENERAL_LB);
        this.CANTIDAD_FOLIOS_LB.setFont(this.FUENTE_GENERAL_LB);

        // - textField
        this.N_ORDEN_TXT.setFont(this.FUENTE_GENERAL_TXT);
        this.N_PEDIDO_TXT.setFont(this.FUENTE_GENERAL_TXT);
        this.FECHA_ENTRADA_TXT.setFont(this.FUENTE_GENERAL_TXT);
        this.HORA_ENTRADA_TXT .setFont(this.FUENTE_GENERAL_TXT);
        this.DISENO_TXT .setFont(this.FUENTE_GENERAL_TXT);
        this.OBSERVACIONES_TXT.setFont(this.FUENTE_GENERAL_TXT);
        this.CODIGO_INTERNO_TXT.setFont(this.FUENTE_GENERAL_TXT);
        this.CLIENTE_TXT.setFont(this.FUENTE_GENERAL_TXT);
        this.PRODUCTO_TXT.setFont(this.FUENTE_GENERAL_TXT);
        this.CANT_PALLET_TXT  .setFont(this.FUENTE_GENERAL_TXT);
        this.TOTAL_UNIDADES_TXT.setFont(this.FUENTE_GENERAL_TXT);
        this.CANT_POR_PALETT_TXT.setFont(this.FUENTE_GENERAL_TXT);
        this.EMPRESA_TXT.setFont(this.FUENTE_GENERAL_TXT);
        this.PLACAS_TXT .setFont(this.FUENTE_GENERAL_TXT);
        this.TRACTO_TXT .setFont(this.FUENTE_GENERAL_TXT);
        this.CANTIDAD_FOLIOS_TXT.setFont(this.FUENTE_GENERAL_TXT);

        // - comboBoxs
        this.CODIGO_DE_BARRAS_CMB.setFont(this.FUENTE_GENERAL_TXT);
        this.CONDICION_CMB.setFont(this.FUENTE_GENERAL_TXT);
        this.CHOFER_CMB.setFont(this.FUENTE_GENERAL_TXT);


        // botones:
        this.AGREGAR.setFont(this.FUENTE_GENERAL_TXT);
        this.GUARDAR.setFont(this.FUENTE_GENERAL_TXT);
        this.ELIMINAR.setFont(this.FUENTE_GENERAL_TXT);
        this.LIMPIAR_TABLA.setFont(this.FUENTE_GENERAL_TXT);
        // -- Agrego listener:

        // - itemsListener:

        this.CODIGO_DE_BARRAS_CMB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED) {
                        completarInfoCB( (String)e.getItem() );
                }
            }
        });
        this.CHOFER_CMB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED)
                {
                    completarInfoChofer( (String)e.getItem() );
                }
            }
        });

        // -- centrar textos:

        this.N_ORDEN_TXT.setHorizontalAlignment(JTextField.CENTER);
        this.N_PEDIDO_TXT.setHorizontalAlignment(JTextField.CENTER);
        this.FECHA_ENTRADA_TXT.setHorizontalAlignment(JTextField.CENTER);
        this.HORA_ENTRADA_TXT.setHorizontalAlignment(JTextField.CENTER);
        this.DISENO_TXT.setHorizontalAlignment(JTextField.CENTER);
        this.OBSERVACIONES_TXT.setHorizontalAlignment(JTextField.CENTER);
        this.CODIGO_INTERNO_TXT.setHorizontalAlignment(JTextField.CENTER);
        this.CLIENTE_TXT.setHorizontalAlignment(JTextField.CENTER);
        this.PRODUCTO_TXT.setHorizontalAlignment(JTextField.CENTER);
        this.CANT_PALLET_TXT.setHorizontalAlignment(JTextField.CENTER);
        this.TOTAL_UNIDADES_TXT.setHorizontalAlignment(JTextField.CENTER);
        this.CANT_POR_PALETT_TXT.setHorizontalAlignment(JTextField.CENTER);
        this.EMPRESA_TXT.setHorizontalAlignment(JTextField.CENTER);
        this.PLACAS_TXT.setHorizontalAlignment(JTextField.CENTER);
        this.TRACTO_TXT.setHorizontalAlignment(JTextField.CENTER);
        this.CANTIDAD_FOLIOS_TXT.setHorizontalAlignment(JTextField.CENTER);

        // - keysListener:

        this.N_ORDEN_TXT.addKeyListener(this);
        this.N_PEDIDO_TXT.addKeyListener(this);
        this.FECHA_ENTRADA_TXT.addKeyListener(this);
        this.HORA_ENTRADA_TXT.addKeyListener(this);
        this.DISENO_TXT.addKeyListener(this);
        this.OBSERVACIONES_TXT.addKeyListener(this);
        this.CODIGO_INTERNO_TXT.addKeyListener(this);
        this.CLIENTE_TXT.addKeyListener(this);
        this.PRODUCTO_TXT.addKeyListener(this);
        this.CANT_PALLET_TXT.addKeyListener(this);
        this.TOTAL_UNIDADES_TXT.addKeyListener(this);
        this.CANT_POR_PALETT_TXT.addKeyListener(this);
        this.EMPRESA_TXT.addKeyListener(this);
        this.PLACAS_TXT.addKeyListener(this);
        this.TRACTO_TXT.addKeyListener(this);
        this.CANTIDAD_FOLIOS_TXT.addKeyListener(this);

        this.CODIGO_DE_BARRAS_CMB.addKeyListener(this);
        this.CONDICION_CMB.addKeyListener(this);
        this.CHOFER_CMB.addKeyListener(this);

        // -- ActionListener
        this.AGREGAR.addActionListener(this);
        this.GUARDAR.addActionListener(this);
        this.ELIMINAR.addActionListener(this);
        this.LIMPIAR_TABLA.addActionListener(this);


    }
    protected void completarInfoCB(String id) {

        ENTRADAS_MDL.busquedaCodigoBarras(this, id);
    }
    protected void completarInfoChofer(String id){
        ENTRADAS_MDL.busquedaChofer(this, id);
    }
    public void cargaComboBoxs() {

        this.CONDICION_CMB.removeAllItems();
        this.CHOFER_CMB.removeAllItems();
        this.CODIGO_DE_BARRAS_CMB.removeAllItems();

        // -- condicion:

        this.CONDICION_CMB.addItem("");
        this.CONDICION_CMB.addItem("Bueno");
        this.CONDICION_CMB.addItem("Malo");
        this.CONDICION_CMB.addItem("Promedio");

        // -- chofer:
        this.CHOFER_CMB.setModel(this.ENTRADAS_MDL.obtenerChofer());

        // -- codigo de barras:
        this.CODIGO_DE_BARRAS_CMB.setModel(this.ENTRADAS_MDL.obtenerCodigoBarras());

    }
    public boolean camposVacios(){
        return  this.N_ORDEN_TXT.getText().isEmpty() ||
                this.N_PEDIDO_TXT.getText().isEmpty() ||
                //this.FECHA_ENTRADA_TXT.getText().isEmpty() ||
                //this.HORA_ENTRADA_TXT .getText().isEmpty() ||
                this.DISENO_TXT .getText().isEmpty() ||
                this.OBSERVACIONES_TXT.getText().isEmpty() ||
                this.CODIGO_INTERNO_TXT.getText().isEmpty() ||
                this.CLIENTE_TXT.getText().isEmpty() ||
                this.PRODUCTO_TXT.getText().isEmpty() ||
                this.CANT_PALLET_TXT  .getText().isEmpty() ||
                this.TOTAL_UNIDADES_TXT.getText().isEmpty() ||
                this.CANT_POR_PALETT_TXT.getText().isEmpty() ||
                this.EMPRESA_TXT.getText().isEmpty() ||
                this.PLACAS_TXT .getText().isEmpty() ||
                this.TRACTO_TXT .getText().isEmpty() ||
                this.CANTIDAD_FOLIOS_TXT.getText().isEmpty();
    }
    private void guardarEntrada( int row){

        ENTRADAS_MDL.setFechaEntrada(this.DATE_CONTROLLER.getFecha());
        ENTRADAS_MDL.setHoraEntrada(this.DATE_CONTROLLER.getHoraString());
        ENTRADAS_MDL.setCodigoBarras((String) this.getTABLA_DE_ENTRADAS().getValueAt(row, 2));

        ENTRADAS_MDL.setDiseno(Integer.parseInt((String) this.getTABLA_DE_ENTRADAS().getValueAt(row, 3)));
        ENTRADAS_MDL.setCliente(this.getCLIENTE_TXT().getText());
        ENTRADAS_MDL.setProducto((String) this.getTABLA_DE_ENTRADAS().getValueAt(row, 4));

        ENTRADAS_MDL.setCantidadPallet(Integer.parseInt(this.getCANT_PALLET_TXT().getText()));
        ENTRADAS_MDL.setCantidadPorPallet(Integer.parseInt((String)
                this.getTABLA_DE_ENTRADAS().getValueAt(row, 5)));
        ENTRADAS_MDL.setCodigoInterno(this.getCODIGO_INTERNO_TXT().getText());

        ENTRADAS_MDL.setTotalUnidades(Integer.parseInt(this.getTOTAL_UNIDADES_TXT().getText()));
        ENTRADAS_MDL.setNumOrden(Integer.parseInt(this.getN_ORDEN_TXT().getText()));
        ENTRADAS_MDL.setNumPedido(Integer.parseInt(this.getN_PEDIDO_TXT().getText()));

        ENTRADAS_MDL.setCondicion(this.getCONDICION_CMB().getSelectedItem().toString());
        ENTRADAS_MDL.setObservaciones(this.getOBSERVACIONES_TXT().getText());
        //ENTRADAS_MDL.setFolio(Integer.parseInt((String) TblENTRADAS_MDL.getValueAt(i, 1)));
        ENTRADAS_MDL.setFolio(0);

        ENTRADAS_MDL.setChofer((String) this.getCHOFER_CMB().getSelectedItem());
        ENTRADAS_MDL.setEmpresa(this.getEMPRESA_TXT().getText());
        ENTRADAS_MDL.setPlacas(this.getPLACAS_TXT().getText());

        ENTRADAS_MDL.setTractoCamion(this.getTRACTO_TXT().getText());

        ENTRADAS_MDL.insertarEntrada();
    }
    public void vaciarTextos() {
        this.N_ORDEN_TXT.setText("");
        this.N_PEDIDO_TXT.setText("");
        this.FECHA_ENTRADA_TXT.setText("");

        this.HORA_ENTRADA_TXT.setText("");
        this.DISENO_TXT.setText("");
        this.OBSERVACIONES_TXT.setText("");

        this.CODIGO_INTERNO_TXT.setText("");
        this.CLIENTE_TXT.setText("");
        this.PRODUCTO_TXT.setText("");

        this.CANT_PALLET_TXT.setText("");
        this.TOTAL_UNIDADES_TXT.setText("");
        this.CANT_POR_PALETT_TXT.setText("");

        this.EMPRESA_TXT.setText("");
        this.PLACAS_TXT.setText("");
        this.TRACTO_TXT.setText("");

        this.CANTIDAD_FOLIOS_TXT.setText("");

        this.CODIGO_DE_BARRAS_CMB.setSelectedIndex(0);
        this.CONDICION_CMB.setSelectedIndex(0);
        this.CHOFER_CMB.setSelectedIndex(0);

    }

    // -- GET'S Y SET'S


    public JButton getELIMINAR() {
        return ELIMINAR;
    }

    public JButton getLIMPIAR_TABLA() {
        return LIMPIAR_TABLA;
    }

    public JButton getGUARDAR() {
        return GUARDAR;
    }

    public JButton getAGREGAR() {
        return AGREGAR;
    }

    public Table getTABLA_DE_ENTRADAS() {
        return TABLA_DE_ENTRADAS;
    }

    public JLabel getN_ORDEN_LB() {
        return N_ORDEN_LB;
    }

    public JLabel getN_PEDIDO_LB() {
        return N_PEDIDO_LB;
    }

    public JLabel getFECHA_ENTRADA_LB() {
        return FECHA_ENTRADA_LB;
    }

    public JLabel getCODIGO_DE_BARRAS_LB() {
        return CODIGO_DE_BARRAS_LB;
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

    public JLabel getCANT_PALLET_LB() {
        return CANT_PALLET_LB;
    }

    public JLabel getTOTAL_UNIDADES_LB() {
        return TOTAL_UNIDADES_LB;
    }

    public JLabel getCANT_POR_PALETT_LB() {
        return CANT_POR_PALETT_LB;
    }

    public JLabel getCONDICION_LB() {
        return CONDICION_LB;
    }

    public JLabel getOBSERVACIONES_LB() {
        return OBSERVACIONES_LB;
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

    public JLabel getCANTIDAD_FOLIOS_LB() {
        return CANTIDAD_FOLIOS_LB;
    }

    public JTextField getN_ORDEN_TXT() {
        return N_ORDEN_TXT;
    }

    public JTextField getN_PEDIDO_TXT() {
        return N_PEDIDO_TXT;
    }

    public JTextField getFECHA_ENTRADA_TXT() {
        return FECHA_ENTRADA_TXT;
    }

    public JTextField getHORA_ENTRADA_TXT() {
        return HORA_ENTRADA_TXT;
    }

    public JTextField getDISENO_TXT() {
        return DISENO_TXT;
    }

    public JTextField getOBSERVACIONES_TXT() {
        return OBSERVACIONES_TXT;
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

    public JTextField getCANT_PALLET_TXT() {
        return CANT_PALLET_TXT;
    }

    public JTextField getTOTAL_UNIDADES_TXT() {
        return TOTAL_UNIDADES_TXT;
    }

    public JTextField getCANT_POR_PALETT_TXT() {
        return CANT_POR_PALETT_TXT;
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

    public JTextField getCANTIDAD_FOLIOS_TXT() {
        return CANTIDAD_FOLIOS_TXT;
    }

    public JComboBox getCODIGO_DE_BARRAS_CMB() {
        return CODIGO_DE_BARRAS_CMB;
    }

    public JComboBox getCONDICION_CMB() {
        return CONDICION_CMB;
    }

    public JComboBox getCHOFER_CMB() {
        return CHOFER_CMB;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    // -- METODOS OVERRIDE:

    // -- Jpanel
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

    // -- ActionPerformed
    @Override
    public void actionPerformed(ActionEvent e) {

        if (this.GUARDAR.equals(e.getSource()))
        {
            if(camposVacios())
            {
                JOptionPane.showMessageDialog(this,
                                     "Falta llenar campos",
                                         "Error",
                                              JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                int  count = Integer.parseInt(this.getTOTAL_UNIDADES_TXT().getText());
                if(this.getTABLA_DE_ENTRADAS().sumatoriaCantidadUnidad() == count)
                {
                    this.getGUARDAR().setEnabled(false);
                    this.getAGREGAR().setEnabled(false);
                    this.getELIMINAR().setEnabled(false);
                    this.getLIMPIAR_TABLA().setEnabled(false);

                    for (int i = 0; i < this.TABLA_DE_ENTRADAS.getRowCount(); i++){ this.guardarEntrada(i);}
                    JOptionPane.showMessageDialog(this,
                                                    "Entradas registradas con exito.",
                                                        "Registro de entrada",
                                                             JOptionPane.INFORMATION_MESSAGE);

                    int seleccion = JOptionPane.showConfirmDialog(null,
                                                                    "¿Quieres agregar otra orden?",
                                                                        "Confirmacion de órden",
                                                                             JOptionPane.YES_NO_OPTION);

                    if (seleccion == JOptionPane.YES_OPTION)
                    {
                        this.getN_PEDIDO_TXT().setText("");
                        this.getN_PEDIDO_TXT().requestFocus();
                        this.getTABLA_DE_ENTRADAS().vaciarTabla();
                        this.vaciarTextos();
                    }
                    else if(seleccion == JOptionPane.NO_OPTION)
                    {
                        this.vaciarTextos();
                        this.getTABLA_DE_ENTRADAS().vaciarTabla();
                    }

                    this.getGUARDAR().setEnabled(true);
                    this.getAGREGAR().setEnabled(true);
                    this.getELIMINAR().setEnabled(true);
                    this.getLIMPIAR_TABLA().setEnabled(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(this,
                            "El total de unidades no es el mismo a los ingresados en la tabla",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

        }

        if(this.AGREGAR.equals(e.getSource()))
        {
            if(this.getCANTIDAD_FOLIOS_TXT().getText().isEmpty())
            {
                JOptionPane.showMessageDialog(this,
                        "No se escribio cantidad de filas",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            else if(this.getCANT_PALLET_TXT().getText().isEmpty())
            {
                JOptionPane.showMessageDialog(this,
                        "No se escribio cantidad de pallet",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            else if((Integer.parseInt(this.getCANTIDAD_FOLIOS_TXT().getText()) * 2) ==
                     Integer.parseInt(this.getCANT_PALLET_TXT().getText()))
            {
                JOptionPane.showMessageDialog(this,
                        "La cantidad tiene que ser el doble a la " +
                                "cantidad de pallet a agregar a la tabla.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if(Integer.parseInt(this.getCANTIDAD_FOLIOS_TXT().getText()) <= 60 &&
                    Integer.parseInt(this.getCANTIDAD_FOLIOS_TXT().getText()) > 0)
            {
                int veces = 0;
                int valor = 0;
                int nRows = this.getTABLA_DE_ENTRADAS().getRowCount();
                int total = (nRows > 0)?Integer.parseInt(this.getCANTIDAD_FOLIOS_TXT().getText())+nRows
                            :Integer.parseInt(this.getCANTIDAD_FOLIOS_TXT().getText());

                for(int i = (nRows > 0)?nRows+1:1; i <= total; i++)
                {
                    String [] fila = new String[7];
                    fila[0] = String.valueOf(i);
                    fila[1] = "0";
                    fila[2] = this.getCODIGO_DE_BARRAS_CMB().getSelectedItem().toString();
                    fila[3] = this.getDISENO_TXT().getText();
                    fila[4] = this.getPRODUCTO_TXT().getText();
                    fila[5] = String.valueOf((Integer.parseInt(this.getCANT_POR_PALETT_TXT().getText())%2 == 0)?
                            Integer.parseInt(this.getCANT_POR_PALETT_TXT().getText()) / 2 : 1);
                    fila[6] = String.valueOf(valor);

                    this.getTABLA_DE_ENTRADAS().addRow(fila);

                    if(veces == 1) {
                        veces = 0;
                        valor = (valor == 1)? 0 : (valor + 1);
                        continue;
                    }
                    veces++;
                }
            }
            else if(Integer.parseInt(this.getCANTIDAD_FOLIOS_TXT().getText()) > 60)
            {
                JOptionPane.showMessageDialog(this,
                        "Solo se pueden agregar hasta 60 elementos, " +
                                "por favor verifique si la cantidad es correcta.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            else if(Integer.parseInt(this.getCANTIDAD_FOLIOS_TXT().getText()) == 0){
                JOptionPane.showMessageDialog(this,
                        "No es posible agregar 0 elementos, verifique que la cantidad sea la " +
                                "correcta e intente de nuevo.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource() == this.LIMPIAR_TABLA)
        {
            int seleccion = JOptionPane.showConfirmDialog(null,
                    "¿Seguro desea limpiar la tabla?",
                    "Confirmacion de órden",
                    JOptionPane.YES_NO_OPTION);

            if (seleccion == JOptionPane.YES_OPTION)
            {
                this.getTABLA_DE_ENTRADAS().vaciarTabla();
            }
        }

        if(e.getSource() == this.ELIMINAR)
        {
            this.getTABLA_DE_ENTRADAS().eliminarFila();
        }

    }

    // -- keylistener
    @Override
    public void keyTyped(KeyEvent e) {
        if(this.getOBSERVACIONES_TXT().isFocusOwner())
        {
                if (!Character.isLetter(e.getKeyChar()) &&
                    !(e.getKeyChar() == KeyEvent.VK_SPACE) &&
                    !(e.getKeyChar() == KeyEvent.VK_BACK_SPACE))
                    {
                        e.consume();
                    }
        }

             if(this.getN_ORDEN_TXT().isFocusOwner() ||
                this.getN_PEDIDO_TXT().isFocusOwner() ||
                this.getCANT_PALLET_TXT().isFocusOwner() ||
                this.getCANT_POR_PALETT_TXT().isFocusOwner() ||
                this.getCANTIDAD_FOLIOS_TXT().isFocusOwner())
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
            this.getGUARDAR().doClick();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }

    // -- itemListener
    @Override
    public void focusGained(FocusEvent e) {
        if(this.getCANT_PALLET_TXT().isFocusOwner())
        {
            this.getCANT_PALLET_TXT().selectAll();
        }

        if(this.getCANT_POR_PALETT_TXT().isFocusOwner())
        {
            this.getCANT_POR_PALETT_TXT().selectAll();
        }
    }
    @Override
    public void focusLost(FocusEvent e) {

    }


}
