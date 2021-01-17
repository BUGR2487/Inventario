package Forms.Principal.Salidas.Panels;

import Forms.Components.DatePicker;
import Forms.Components.Fecha_y_hora;
import Forms.Components.Table;
import Forms.Panel;
import Forms.Principal.Salidas.Layouts.InsertarSalidasLayout;
import Tools.DataBase.Entradas;
import Tools.DataBase.Inventario;
import Tools.DataBase.Salidas;
import Tools.DataBase.Transporte;
import Tools.Fecha;
import Tools.Hora;
import Tools.TextPrompt;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.ArrayList;

/**
 InsertarSalidasPanel:

 Interfaz que permite al usuario insertar una salida, misma que se reflejara en el inventario al momento de
 registrarse dicha entrada. A continuacion  se muestran los componentes usados en dicha interfaz.

 Etiquetas:
    JLabel - N_PEDIDO_LB
    JLabel - SELLOS_LB
    JLabel - CANT_PALLET_LB
    JLabel - CANT_POR_PALETT_LB
    JLabel - TOTAL_UNIDADES_LB
    JLabel - DISENO_LB
    JLabel - CODIGO_INTERNO_LB
    JLabel - PRODUCTO_LB
    JLabel - CONDICION_LB
    JLabel - OBSERVACIONES_LB
    JLabel - CLIENTE_LB
    JLabel - CHOFER_LB
    JLabel - EMPRESA_LB
    JLabel - PLACAS_LB
    JLabel - TRACTO_LB
    JLabel - FECHA_ENTREGA_LB
    JLabel - CODIGO_BARRAS_LB
    JLabel - FECHA_SALIDA_LB

 Listas:
    JComboBox - N_CODIGO_BARRA
    JComboBox - NAME_CHOFER
    JComboBox - CONDICION_CMB

 Campos de texto:
    JTextField - N_PEDIDO_TXT
    JTextField - SELLOS_TXT
    JTextField - CANT_PALLET_TXT
    JTextField - CANT_POR_PALETT_TXT
    JTextField - TOTAL_UNIDADES_TXT
    JTextField - DISENO_TXT
    JTextField - CODIGO_INTERNO_TXT
    JTextField - PRODUCTO_TXT
    JTextField - OBSERVACIONES_TXT
    JTextField - CLIENTE_TXT
    JTextField - EMPRESA_TXT
    JTextField - PLACAS_TXT
    JTextField - TRACTO_TXT

 Calendario:
    DatePicker(para mas información revise el paquete componentes en la clase DatePicker) - FECHA_ENTREGA

 Tabla:
    Table(para mas información revise el paquete componentes en la clase Table) - TABLA_SALIDAS

 Botones:
    JButton - REGISTRAR_SALIDA_BTN
    JButton - AGREGAR_A_LA_TABLA_BTN
    JButton - LIMPIAR_LA_TABLA_BTN
    JButton - ELIMINAR_DE_LA_TABLA_BTN

 ScrollViewer:
    JScrollPane - PANE

 Runnables:
    Fecha_y_hora(para mas información revise el paquete componentes en la clase Fecha_y_hora) - DATE_CONTROLLER

 Variables Globales:
    int - indexRow
    int - colorRow
    int - veces
    Inventario - product
    Transporte - chofer


 */
public class InsertarSalidasPanel extends JPanel implements ActionListener, KeyListener, Panel {

    // -- VARIABLES:
    // -- etiquetas:

    private final JLabel N_PEDIDO_LB            = new JLabel("No. Pedido");
    private final JLabel SELLOS_LB              = new JLabel("Sellos");
    private final JLabel CANT_PALLET_LB         = new JLabel("Cantidad pallet");
    private final JLabel CANT_POR_PALETT_LB     = new JLabel("Cantidad por pallet");
    private final JLabel TOTAL_UNIDADES_LB      = new JLabel("Total de unidades");
    private final JLabel DISENO_LB              = new JLabel("Diseno");
    private final JLabel CODIGO_INTERNO_LB      = new JLabel("Codigo interno");
    private final JLabel PRODUCTO_LB            = new JLabel("Producto");
    private final JLabel CONDICION_LB           = new JLabel("Condicion");
    private final JLabel OBSERVACIONES_LB       = new JLabel("Observacion");
    private final JLabel CLIENTE_LB             = new JLabel("Cliente");
    private final JLabel CHOFER_LB              = new JLabel("Chofer");
    private final JLabel EMPRESA_LB             = new JLabel("Empresa");
    private final JLabel PLACAS_LB              = new JLabel("Placas");
    private final JLabel TRACTO_LB              = new JLabel("Tracto camion");
    private final JLabel FECHA_ENTREGA_LB       = new JLabel("Fecha de entrega");
    private final JLabel CODIGO_BARRAS_LB       = new JLabel("Codigo de barras.");
    private JLabel FECHA_SALIDA_LB              = null;

    // -- listas:

    private final JComboBox N_CODIGO_BARRA           = new JComboBox();
    private final JComboBox NAME_CHOFER              = new JComboBox();
    private final JComboBox CONDICION_CMB           = new JComboBox();

    // -- campos de texto:

    private final JTextField N_PEDIDO_TXT            = new JTextField();
    private final JTextField SELLOS_TXT              = new JTextField();
    private final JTextField CANT_PALLET_TXT         = new JTextField();
    private final JTextField CANT_POR_PALETT_TXT     = new JTextField();
    private final JTextField TOTAL_UNIDADES_TXT      = new JTextField();
    private final JTextField DISENO_TXT              = new JTextField();
    private final JTextField CODIGO_INTERNO_TXT      = new JTextField();
    private final JTextField PRODUCTO_TXT            = new JTextField();
    private final JTextField OBSERVACIONES_TXT       = new JTextField();
    private final JTextField CLIENTE_TXT             = new JTextField();
    private final JTextField EMPRESA_TXT             = new JTextField();
    private final JTextField PLACAS_TXT              = new JTextField();
    private final JTextField TRACTO_TXT              = new JTextField();

    private final DatePicker FECHA_ENTREGA           = DatePicker.buildDatePicker(true);

    // -- tabla de salidas:
    private final Table TABLA_SALIDAS = new Table(true, new String[] {
            "#",
            "No.Pedido",
            "Codigo barras",
            "Sellos",
            "Cantidad de pallet",
            "Cantidad por pallet",
            "Total de unidades",
            "Fecha de Salida",
            "Hora de salida",
            "Fecha de entrega",
            "Diseno",
            "Codigo interno",
            "Producto",
            "Condicion",
            "Observaciones",
            "Chofer",
            "Empresa",
            "Placas",
            "Tractocamion",
            "color"
    }, false, 1);

    // -- botones:

    private final JButton REGISTRAR_SALIDA_BTN = new JButton("Registrar salida.");
    private final JButton AGREGAR_A_LA_TABLA_BTN = new JButton("Agregar a la tabla.");
    private final JButton LIMPIAR_LA_TABLA_BTN = new JButton("Limpiar la tabla.");
    private final JButton ELIMINAR_DE_LA_TABLA_BTN = new JButton("Eliminar fila(s).");

    // -- scroll de la vista:
    private final JScrollPane PANE = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

    private final TextPrompt  placeholderCantPallet = new TextPrompt("0", this.getCANT_PALLET_TXT()),
                              placeholderCantidadPorPallet = new TextPrompt("0", this.getCANT_POR_PALETT_TXT());


    private int indexRow = 0, colorRow = 0, veces = 0;
    private Inventario product = null;
    private Transporte chofer = null;

    // -- Runnable que actualiza la hora y fecha:

    private Fecha_y_hora DATE_CONTROLLER = null;


    /**
     * Constructor de la interfaz de Insertar Salidas, recibe en su entrada una etiqueta y una
     * instancia de la clase Fecha_y_hora para iniciar un label en la interfaz que siempre este
     * marcando la fecha y hora dentro de la interfaz. Esto con la finalidad de llevar un registro
     * del dia y la hora en que se reportó una salida.
     * @param lb
     * @param controller
     */
    public InsertarSalidasPanel(JLabel lb , Fecha_y_hora controller) {
        this.setFECHA_SALIDA_LB( lb );
        this.DATE_CONTROLLER = controller;
        this.prepareAll();
        this.setLayout(new InsertarSalidasLayout(this));
        this.PANE.getViewport().add(this);

        System.out.println( this.getFECHA_SALIDA_LB().getText() );
    }

    // -- METODOS DE LA CLASE:

    /**
     * Retorna la vista de la interfaz con el scroll ya implementado, en algunas interfaces se alterna entre tener
     * y no el scroll.
     *
     * @return Component
     */
    public Component getview()
    {
        return this.PANE;
    }

    /**
     * Prepara todos los componentes de la interfaz, desde fuentes, centrados de linea e incluso setea los listener
     * de cada componente.
     *
     */
    public void prepareAll() {

        // -- acomodo de fuentes:

        //placeholders:

                this.placeholderCantPallet.changeAlpha(0.75f);
                this.placeholderCantPallet.changeStyle(Font.ITALIC);
                this.placeholderCantPallet.setFont( this.FUENTE_GENERAL_TXT );

                this.placeholderCantidadPorPallet.changeAlpha(0.75f);
                this.placeholderCantidadPorPallet.changeStyle(Font.ITALIC);
                this.placeholderCantidadPorPallet.setFont( this.FUENTE_GENERAL_TXT );

        //etiquetas:
                this.getFECHA_SALIDA_LB().setFont( this.FUENTE_GENERAL_LB );
                this.N_PEDIDO_LB.setFont( this.FUENTE_GENERAL_LB );
                this.SELLOS_LB.setFont( this.FUENTE_GENERAL_LB );
                this.CANT_PALLET_LB.setFont( this.FUENTE_GENERAL_LB );
                this.CANT_POR_PALETT_LB.setFont( this.FUENTE_GENERAL_LB );
                this.TOTAL_UNIDADES_LB.setFont( this.FUENTE_GENERAL_LB );
                this.DISENO_LB.setFont( this.FUENTE_GENERAL_LB );
                this.CODIGO_INTERNO_LB.setFont( this.FUENTE_GENERAL_LB );
                this.PRODUCTO_LB.setFont( this.FUENTE_GENERAL_LB );
                this.CONDICION_LB.setFont( this.FUENTE_GENERAL_LB );
                this.OBSERVACIONES_LB.setFont( this.FUENTE_GENERAL_LB );
                this.CLIENTE_LB.setFont( this.FUENTE_GENERAL_LB );
                this.CHOFER_LB.setFont( this.FUENTE_GENERAL_LB );
                this.EMPRESA_LB.setFont( this.FUENTE_GENERAL_LB );
                this.PLACAS_LB.setFont( this.FUENTE_GENERAL_LB );
                this.TRACTO_LB.setFont( this.FUENTE_GENERAL_LB );
                this.FECHA_ENTREGA_LB.setFont( this.FUENTE_GENERAL_LB );
                this.CODIGO_BARRAS_LB.setFont( this.FUENTE_GENERAL_LB );

        //campos de texto:

                this.N_PEDIDO_TXT.setFont( this.FUENTE_GENERAL_TXT );
                this.SELLOS_TXT.setFont( this.FUENTE_GENERAL_TXT );
                this.CANT_PALLET_TXT.setFont( this.FUENTE_GENERAL_TXT );
                this.CANT_POR_PALETT_TXT.setFont( this.FUENTE_GENERAL_TXT );
                this.TOTAL_UNIDADES_TXT.setFont( this.FUENTE_GENERAL_TXT );
                this.DISENO_TXT.setFont( this.FUENTE_GENERAL_TXT );
                this.CODIGO_INTERNO_TXT.setFont( this.FUENTE_GENERAL_TXT );
                this.PRODUCTO_TXT.setFont( this.FUENTE_GENERAL_TXT );
                this.CONDICION_CMB.setFont( this.FUENTE_GENERAL_TXT );
                this.OBSERVACIONES_TXT.setFont( this.FUENTE_GENERAL_TXT );
                this.CLIENTE_TXT.setFont( this.FUENTE_GENERAL_TXT );
                this.EMPRESA_TXT.setFont( this.FUENTE_GENERAL_TXT );
                this.PLACAS_TXT.setFont( this.FUENTE_GENERAL_TXT );
                this.TRACTO_TXT.setFont( this.FUENTE_GENERAL_TXT );

        //combo box:

                this.NAME_CHOFER.setFont( this.FUENTE_GENERAL_TXT );
                this.N_CODIGO_BARRA.setFont(this.FUENTE_GENERAL_TXT);

       //botones:

                this.REGISTRAR_SALIDA_BTN       .setFont(this.FUENTE_GENERAL_TXT);
                this.AGREGAR_A_LA_TABLA_BTN     .setFont(this.FUENTE_GENERAL_TXT);
                this.LIMPIAR_LA_TABLA_BTN       .setFont(this.FUENTE_GENERAL_TXT);
                this.ELIMINAR_DE_LA_TABLA_BTN   .setFont(this.FUENTE_GENERAL_TXT);
                this.REGISTRAR_SALIDA_BTN       .setToolTipText("Presione para registrar el listado" +
                                                                " agregado a la tabla. Nota: Si no " +
                                                                "registros en la tabla no se guardará " +
                                                                "nada.");
                this.AGREGAR_A_LA_TABLA_BTN     .setToolTipText("Presione para agregar un registro de salida a" +
                                                                " la tabla.");
                this.LIMPIAR_LA_TABLA_BTN       .setToolTipText("Presione para limpiar de registros la tabla.");
                this.ELIMINAR_DE_LA_TABLA_BTN   .setToolTipText("Presione para eliminar el registro o registros " +
                                                                "seleccionados.");

                this.N_PEDIDO_TXT.setHorizontalAlignment( JTextField.CENTER );
                this.SELLOS_TXT.setHorizontalAlignment( JTextField.CENTER );
                this.CANT_PALLET_TXT.setHorizontalAlignment( JTextField.CENTER );
                this.CANT_POR_PALETT_TXT.setHorizontalAlignment( JTextField.CENTER );
                this.TOTAL_UNIDADES_TXT.setHorizontalAlignment( JTextField.CENTER );
                this.DISENO_TXT.setHorizontalAlignment( JTextField.CENTER );
                this.CODIGO_INTERNO_TXT.setHorizontalAlignment( JTextField.CENTER );
                this.PRODUCTO_TXT.setHorizontalAlignment( JTextField.CENTER );
                this.OBSERVACIONES_TXT.setHorizontalAlignment( JTextField.CENTER );
                this.CLIENTE_TXT.setHorizontalAlignment( JTextField.CENTER );

                this.EMPRESA_TXT.setHorizontalAlignment( JTextField.CENTER );
                this.PLACAS_TXT.setHorizontalAlignment( JTextField.CENTER );
                this.TRACTO_TXT.setHorizontalAlignment( JTextField.CENTER );

        // -- acomodp de los listener:

        // - key:

                this.N_PEDIDO_TXT.addKeyListener( this );
                this.SELLOS_TXT.addKeyListener( this );
                this.CANT_PALLET_TXT.addKeyListener( this );
                this.CANT_POR_PALETT_TXT.addKeyListener( this );
                this.TOTAL_UNIDADES_TXT.addKeyListener( this );
                this.DISENO_TXT.addKeyListener( this );
                this.CODIGO_INTERNO_TXT.addKeyListener( this );
                this.PRODUCTO_TXT.addKeyListener( this );
                this.OBSERVACIONES_TXT.addKeyListener( this );
                this.CLIENTE_TXT.addKeyListener( this );
                this.EMPRESA_TXT.addKeyListener( this );
                this.PLACAS_TXT.addKeyListener( this );
                this.TRACTO_TXT.addKeyListener( this );

                this.cargaComboBox();

        // - action:
                this.REGISTRAR_SALIDA_BTN.addActionListener( this );
                this.AGREGAR_A_LA_TABLA_BTN.addActionListener( this );
                this.LIMPIAR_LA_TABLA_BTN.addActionListener( this );
                this.ELIMINAR_DE_LA_TABLA_BTN .addActionListener( this );

        //item change:
                this.NAME_CHOFER.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if(e.getStateChange() == ItemEvent.SELECTED)
                        {
                            String nombreChofer = NAME_CHOFER.getSelectedItem().toString();
                            actionOnItemChange( nombreChofer );

                        }
                    }
                });
                this.N_CODIGO_BARRA.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent e) {
                        if(e.getStateChange() == ItemEvent.SELECTED)
                        {
                            actionOnItemChange();
                        }
                    }
                });

    }

    /**
     * Funcion que comprueba si los campos estan vacios, si los campos de texto de la interfaz están vacios retorna
     * un true, de lo contrario retorna un false.
     *
     * @return boolean
     */
    public boolean camposVacios() {
        return  this.N_PEDIDO_TXT.getText().isEmpty() ||
                this.SELLOS_TXT.getText().isEmpty() ||
                this.CANT_PALLET_TXT.getText().isEmpty() ||
                this.CANT_POR_PALETT_TXT.getText().isEmpty() ||
                this.TOTAL_UNIDADES_TXT.getText().isEmpty() ||
                this.DISENO_TXT.getText().isEmpty() ||
                this.CODIGO_INTERNO_TXT.getText().isEmpty() ||
                this.PRODUCTO_TXT.getText().isEmpty() ||
                this.OBSERVACIONES_TXT.getText().isEmpty() ||
                this.CLIENTE_TXT.getText().isEmpty() ||
                this.EMPRESA_TXT.getText().isEmpty() ||
                this.PLACAS_TXT.getText().isEmpty() ||
                this.TRACTO_TXT.getText().isEmpty();
    }

    /**
     * Funcion que limpia todos los campos de la interfaz con excepcion de la tabla ya que esa cuenta con un metodo
     * propio.
     */
    public void vaciarTextos() {
                this.N_PEDIDO_TXT.setText("");
                this.SELLOS_TXT.setText("");
                this.CANT_PALLET_TXT.setText("");
                this.CANT_POR_PALETT_TXT.setText("");
                this.TOTAL_UNIDADES_TXT.setText("");
                this.DISENO_TXT.setText("");
                this.CODIGO_INTERNO_TXT.setText("");
                this.PRODUCTO_TXT.setText("");
                this.OBSERVACIONES_TXT.setText("");
                this.CLIENTE_TXT.setText("");
                this.EMPRESA_TXT.setText("");
                this.PLACAS_TXT.setText("");
                this.TRACTO_TXT.setText("");

                this.CONDICION_CMB.setSelectedIndex(0);
                this.NAME_CHOFER.setSelectedIndex(0);
                this.N_CODIGO_BARRA.setSelectedIndex(0);
    }

    /**
     * Funcion llamada en el ActionListener para registrar las salidas en la base de datos y posteriormente pasar
     * a su impresion en formato PDF.
     */
    private void guardarSalidas(){

            /*
               [0]     #
               [1]     noPedido
               [2]     codigoBarras
               [3]     sellos
               [4]     cantidadPallet
               [5]     cantidadPorPallet
               [6]     totalUnidades
               [7]     fechaSalida
               [8]     horaSalida
               [9]     fechaEntrega
               [10]    diseno
               [11]    codigoInterno
               [12]    producto
               [13]    condicion
               [14]    observaciones
               [15]    Chofer
               [16]    Empresa
               [17]    Placas
               [18]    Tractocamion
               [19]    color
            */
        ArrayList<ArrayList<String>> rows = this.getTABLA_SALIDAS().getRows();

        ArrayList<Salidas> salidas = new ArrayList<Salidas>();

        for(ArrayList<String> row : rows)
        {
            Salidas salida = new Salidas();

            /*[1]*/      salida.setNumPedido( Integer.valueOf(row.get(1)) );
            /*[2]*/      salida.setCodigoBarras( row.get(2) );
            /*[3]*/      salida.setSellos( Integer.valueOf(row.get(3)) );

            /*[4]*/      salida.setCantidadPallet( Integer.valueOf(row.get(4)) );
            /*[5]*/      salida.setCantidadPorPallet( Integer.valueOf(row.get(5)) );
            /*[6]*/      salida.setTotalUnidades( Integer.valueOf(row.get(6)) );

            /*[7]*/      salida.setFechaSalida( new Fecha(Fecha.parseDate(row.get(7))) );
            /*[8]*/      salida.setHoraSalida( new Hora(Hora.parseTime(row.get(8))) );
            /*[9]*/      salida.setFechaEntrega( new Date(DatePicker.getDateFromStringDate(row.get(9))) );

            /*[10]*/     salida.setDiseno( row.get(10) );
            /*[11]*/     salida.setCodigoInterno( row.get(11) );
            /*[12]*/     salida.setProducto( row.get(12) );

            /*[13]*/     salida.setCondicion( row.get(13) );
            /*[14]*/     salida.setObservaciones( row.get(14) );
                         salida.setTransporte( row.get(15) );

            salida.insertarSalidas();

            salidas.add( salida );
            //this.crearPDF();
        }

        JOptionPane.showMessageDialog(this,
                "El registro de las salidas se a completado correctamente.",
                "Registro completado.",
                JOptionPane.INFORMATION_MESSAGE);

        this.getTABLA_SALIDAS().vaciarTabla();

    }

    /**
     * Funcion que se encarga de agregar el registro de Salida a la tabla para seguir agregando mas salidas.
     */
    private void agregar_a_la_tabla() {
        if(this.camposVacios())
        {
            JOptionPane.showMessageDialog(this,
                    "Falta llenar campos",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        else
        {

            int cantP = Integer.valueOf( this.getCANT_PALLET_TXT().getText() );
            int cantporP = Integer.valueOf( this.getCANT_POR_PALETT_TXT().getText() );

            if(cantP > this.product.getStockPallets())
            {
                JOptionPane.showMessageDialog(this,
                        "La cantidad de pallets Ingresada es mayor a la que existe en inventario, " +
                                "verifique que el campo tenga un valor correcto",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(cantporP > this.product.getStockPiezas())
            {
                JOptionPane.showMessageDialog(this,
                        "La cantidad de pallets Ingresada es mayor a la que existe en inventario, " +
                                "verifique que el campo tenga un valor correcto",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            this.indexRow++;


            ArrayList<String> row = new ArrayList<String>();

            /*
               /*
               [0]     #
               [1]     noPedido
               [2]     codigoBarras
               [3]     sellos
               [4]     cantidadPallet
               [5]     cantidadPorPallet
               [6]     totalUnidades
               [7]     fechaSalida
               [8]     horaSalida
               [9]     fechaEntrega
               [10]    diseno
               [11]    codigoInterno
               [12]    producto
               [13]    condicion
               [14]    observaciones
               [15]    Chofer
               [16]    Empresa
               [17]    Placas
               [18]    Tractocamion
               [19]    color
            */

            row.add( String.valueOf(this.indexRow) );
            row.add( this.getN_PEDIDO_TXT().getText() );
            row.add( this.product.getCodigoBarras() );
            row.add( this.getSELLOS_TXT().getText() );
            row.add( this.getCANT_PALLET_TXT().getText() );
            row.add( this.getCANT_POR_PALETT_TXT().getText() );
            row.add( this.getTOTAL_UNIDADES_TXT().getText() );
            row.add( this.DATE_CONTROLLER.getFecha().toString() );
            row.add( this.DATE_CONTROLLER.getHora().toString() );
            row.add( this.getFECHA_ENTREGA().getAsString() );
            row.add( this.getDISENO_TXT().getText() );
            row.add( this.getCODIGO_INTERNO_TXT().getText() );
            row.add( this.getPRODUCTO_TXT().getText() );
            row.add( (String)this.getCONDICION_CMB().getSelectedItem() );
            row.add( this.getOBSERVACIONES_TXT().getText() );
            row.add( this.chofer.getChofer() );
            row.add( this.chofer.getEmpresa() );
            row.add( this.chofer.getPlacas() );
            row.add( this.chofer.getTractoCamion() );
            row.add( String.valueOf( this.colorRow ) );


            this.getTABLA_SALIDAS().addRow( row.toArray( new String[row.size()] ) );

            this.vaciarTextos();

            if(this.veces == 1) {
                this.veces = 0;
                this.colorRow = (this.colorRow == 1) ? 0 : (this.colorRow + 1);
                return;
            }
            this.veces++;

        }
    }

    private void crearPDF() {
        JFileChooser seleccionadorArchivos = new JFileChooser();
        seleccionadorArchivos.setCurrentDirectory(new File(System.getProperty("user.home")));
        seleccionadorArchivos.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filtroArchivos = new FileNameExtensionFilter("Documentos PDF (*pdf)", "pdf");
        seleccionadorArchivos.setFileFilter(filtroArchivos);

        int seleccion = seleccionadorArchivos.showSaveDialog(this);

        if (seleccion == JFileChooser.APPROVE_OPTION)
        {
            File archivo = seleccionadorArchivos.getSelectedFile();

            Document document = new Document();
            try
            {
                FileOutputStream fileOutputStream = new FileOutputStream(archivo.getAbsoluteFile() + ".pdf");
                PdfWriter.getInstance(document, fileOutputStream);

                document.open();

                Paragraph titulo = new Paragraph("Tools.DataBase.Salidas",
                        FontFactory.getFont("Arial", 22, Font.BOLD, BaseColor.BLUE));
                document.add(titulo);

                PdfPTable pdfTable = new PdfPTable(6);
                pdfTable.addCell("Num.Pedido");
                pdfTable.addCell("Código de barras");
                pdfTable.addCell("Diseño");
                pdfTable.addCell("Producto");
                pdfTable.addCell("Folio");
                pdfTable.addCell("Cantidad por pallet");
                document.add(pdfTable);

                document.close();
            }
            catch (FileNotFoundException | DocumentException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Funcion que es llamada por el ItemListener en la lista de productos por codigo de barras (N_CODIGO_BARRA)
     * el cual busca el codigo de barras en la base de datos y llena los campos de texto con sus valores
     * correspondientes. Los campos que llena son:
     *
     *  - DISENO_TXT
     *  - CODIGO_INTERNO_TXT
     *  - PRODUCTO_TXT
     *  - CLIENTE_TXT
     *
     */
    public void actionOnItemChange(){
        String value = (String) this.getN_CODIGO_BARRA().getSelectedItem();
        if( !value.isEmpty() )
        {
            this.product = Inventario.getInventario(value);
                    this.DISENO_TXT.setText(this.product.getDiseno());
                    this.CODIGO_INTERNO_TXT.setText(this.product.getCodigoInterno());
                    this.PRODUCTO_TXT.setText(this.product.getProducto());
                    this.CLIENTE_TXT.setText( this.product.getCliente() );
                    this.placeholderCantidadPorPallet.setText(String.valueOf( this.product.getStockPiezas() ));
                    this.placeholderCantPallet.setText(String.valueOf( this.product.getStockPallets() ));

        }
        else
        {
            this.product = null;
            this.DISENO_TXT.setText("");
            this.CODIGO_INTERNO_TXT.setText("");
            this.PRODUCTO_TXT.setText("");
            this.CLIENTE_TXT.setText("");
            this.getCANT_POR_PALETT_TXT().setText("");
            this.getCANT_PALLET_TXT().setText("");
            this.placeholderCantidadPorPallet.setText("0");
            this.placeholderCantPallet.setText("0");
        }
    }

    /**
     * Funcion que es llamada por el ItemListener en la lista NAME_CHOFER, el cual busca a un chofer con el nombre
     * seleccionado en la base de datos y llena los campos del formulario con la informacion respectivs. Los campos
     * que llena son:
     *
     *  - EMPRESA_TXT
     *  - PLACAS_TXT
     *  - TRACTO_TXT
     *
     */
    public void actionOnItemChange(String value){
        if( !value.isEmpty() )
        {
            this.chofer = Transporte.getTransporteByID( value );
            this.getEMPRESA_TXT().setText(this.chofer.getEmpresa());
            this.getPLACAS_TXT().setText(this.chofer.getPlacas());
            this.getTRACTO_TXT().setText(this.chofer.getTractoCamion());
        }
        else
        {
            this.chofer = null;
            this.NAME_CHOFER.setSelectedIndex(0);
            this.getEMPRESA_TXT().setText("");
            this.getPLACAS_TXT().setText("");
            this.getTRACTO_TXT().setText("");
        }
    }

    /**
     * Funcion llamada en el Constructor para cargar todas las listas de la interfaz con la informacion de la bd
     * (algunas, no todas) y algunas otras con informacion ya definida.
     */
    public void cargaComboBox(){
        this.getN_CODIGO_BARRA().removeAllItems();
        this.getN_CODIGO_BARRA().setModel(Salidas.obtenerPedidos());
        this.getNAME_CHOFER().removeAllItems();
        this.getNAME_CHOFER().setModel(Entradas.obtenerChofer());
        this.getCONDICION_CMB().removeAllItems();
        this.getCONDICION_CMB().addItem("");
        this.getCONDICION_CMB().addItem("Bueno");
        this.getCONDICION_CMB().addItem("Malo");
        this.getCONDICION_CMB().addItem("Promedio");
    }

    /**
     * Funcion llamada en las diferentes funciones del Keylistener para hacer validaciones en tiempo de escritura
     * del usuario.
     */
    public void keyAction(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
            e.consume();
        }

        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            this.getREGISTRAR_SALIDA_BTN().doClick();
            return;
        }

        if (this.getCANT_PALLET_TXT().isFocusOwner() ||
                this.getCANT_POR_PALETT_TXT().isFocusOwner() ) {

            String type = String.valueOf(e.getKeyChar());

            if (type.matches("[^0-9]") && e.getKeyCode() != KeyEvent.VK_BACK_SPACE)
            {
                e.consume();
                return;
            }

            String val_s1 = this.getCANT_PALLET_TXT().getText();
            String val_s2 = this.getCANT_POR_PALETT_TXT().getText();

            int val1 = (val_s1.isEmpty())? 0:Integer.valueOf( val_s1 );
            int val2 = (val_s2.isEmpty())? 0:Integer.valueOf( val_s2 );
            this.getTOTAL_UNIDADES_TXT().setText( String.valueOf(val1 * val2) );
        }

        if(this.getSELLOS_TXT().isFocusOwner()) {
            String type = String.valueOf(e.getKeyChar());
            if (type.matches("[^0-9]"))
            {
                e.consume();
                return;
            }
        }

    }



    // -- GET'S Y SET'S:

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

    public JLabel getDISENO_LB() {
        return DISENO_LB;
    }

    public JLabel getCODIGO_INTERNO_LB() {
        return CODIGO_INTERNO_LB;
    }

    public JLabel getPRODUCTO_LB() {
        return PRODUCTO_LB;
    }

    public JLabel getCONDICION_LB() {
        return CONDICION_LB;
    }

    public JLabel getOBSERVACIONES_LB() {
        return OBSERVACIONES_LB;
    }

    public JLabel getCLIENTE_LB() {
        return CLIENTE_LB;
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

    public JLabel getFECHA_ENTREGA_LB() {
        return FECHA_ENTREGA_LB;
    }

    public JLabel getCODIGO_BARRAS_LB() {
        return CODIGO_BARRAS_LB;
    }

    public JLabel getFECHA_SALIDA_LB() {
        return FECHA_SALIDA_LB;
    }

    public void setFECHA_SALIDA_LB(JLabel FECHA_SALIDA_LB) {
        this.FECHA_SALIDA_LB = FECHA_SALIDA_LB;
    }

    public JComboBox getN_CODIGO_BARRA() {
        return N_CODIGO_BARRA;
    }

    public JTextField getN_PEDIDO_TXT() {
        return N_PEDIDO_TXT;
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

    public JTextField getDISENO_TXT() {
        return DISENO_TXT;
    }

    public JTextField getCODIGO_INTERNO_TXT() {
        return CODIGO_INTERNO_TXT;
    }

    public JTextField getPRODUCTO_TXT() {
        return PRODUCTO_TXT;
    }

    public JComboBox getCONDICION_CMB() {
        return CONDICION_CMB;
    }

    public JTextField getOBSERVACIONES_TXT() {
        return OBSERVACIONES_TXT;
    }

    public JTextField getCLIENTE_TXT() {
        return CLIENTE_TXT;
    }

    public JComboBox getNAME_CHOFER() {
        return NAME_CHOFER;
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

    public DatePicker getFECHA_ENTREGA() {
        return FECHA_ENTREGA;
    }

    public Table getTABLA_SALIDAS() {
        return TABLA_SALIDAS;
    }

    public JButton getREGISTRAR_SALIDA_BTN() {
        return REGISTRAR_SALIDA_BTN;
    }

    public JButton getAGREGAR_A_LA_TABLA_BTN() {
        return AGREGAR_A_LA_TABLA_BTN;
    }

    public JButton getLIMPIAR_LA_TABLA_BTN() {
        return LIMPIAR_LA_TABLA_BTN;
    }

    public JButton getELIMINAR_DE_LA_TABLA_BTN() {
        return ELIMINAR_DE_LA_TABLA_BTN;
    }

    public JScrollPane getPANE() {
        return PANE;
    }

    public int getIndexRow() {
        return indexRow;
    }

    public void setIndexRow(int indexRow) {
        this.indexRow = indexRow;
    }

    public int getColorRow() {
        return colorRow;
    }

    public void setColorRow(int colorRow) {
        this.colorRow = colorRow;
    }

    public int getVeces() {
        return veces;
    }

    public void setVeces(int veces) {
        this.veces = veces;
    }

    public Inventario getProduct() {
        return product;
    }

    public void setProduct(Inventario product) {
        this.product = product;
    }

    public Fecha_y_hora getDATE_CONTROLLER() {
        return DATE_CONTROLLER;
    }

    public void setDATE_CONTROLLER(Fecha_y_hora DATE_CONTROLLER) {
        this.DATE_CONTROLLER = DATE_CONTROLLER;
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

        if(e.getSource() == this.REGISTRAR_SALIDA_BTN)
        {
            if(!this.getTABLA_SALIDAS().isEmpty()) {
                this.guardarSalidas();
            }
            else {
                JOptionPane.showMessageDialog(this,
                        "La tabla de salidas se encuentra vacía.",
                        "Error al registrar las salidas.",
                        JOptionPane.WARNING_MESSAGE);
            }

        }

        if( e.getSource() == this.AGREGAR_A_LA_TABLA_BTN)
        {
            this.agregar_a_la_tabla();
        }

        if( e.getSource() == this.LIMPIAR_LA_TABLA_BTN )
        {
            this.getTABLA_SALIDAS().vaciarTabla();
        }

        if( e.getSource() == this.ELIMINAR_DE_LA_TABLA_BTN )
        {
            this.getTABLA_SALIDAS().eliminarFila();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.keyAction(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
       this.keyAction(e);
    }

}
