package Forms.Principal.Salidas.Panels;

import Forms.Components.DatePicker;
import Forms.Components.Fecha_y_hora;
import Forms.Components.Table;
import Forms.Panel;
import Forms.Principal.Salidas.Layouts.InsertarSalidasLayout;
import Tools.DataBase.Salidas;
import Tools.DataBase.Transporte;
import Tools.Fecha;
import Tools.Hora;
import com.itextpdf.text.*;
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

public class InsertarSalidasPanel extends JPanel implements ActionListener, KeyListener, Panel {

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

    private JLabel FECHA_SALIDA_LB             = null;

    // -- listas:

    private final JComboBox N_PEDIDO_CMB             = new JComboBox();

    // -- campos de texto:

    private final JTextField SELLOS_TXT              = new JTextField();
    private final JTextField CANT_PALLET_TXT         = new JTextField();
    private final JTextField CANT_POR_PALETT_TXT     = new JTextField();
    private final JTextField TOTAL_UNIDADES_TXT      = new JTextField();
    //private final JTextField FECHA_ENTREGA_TXT       = new JTextField();
    private final JTextField CHOFER_TXT              = new JTextField();
    private final JTextField EMPRESA_TXT             = new JTextField();
    private final JTextField PLACAS_TXT              = new JTextField();
    private final JTextField TRACTO_TXT              = new JTextField();

    private final DatePicker FECHA_ENTREGA           = DatePicker.buildDatePicker(true);

    // -- tabla de salidas:
    private final Table TABLA_SALIDAS = new Table(true, new String[] {
            "#",
            "N. Pedido",
            "Sellos",
            "Cantidad Pallet",
            "Cantidad por pallet",
            "Total de unidades",
            "Fecha de salida",
            "Hora de salida",
            "Fecha de entrega",
            "Chofer",
            "Empresa",
            "Placas",
            "Tracto camion",
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


    private int indexRow = 0, colorRow = 0, veces = 0;

    // -- Runnable que actualiza la hora y fecha:

    private Fecha_y_hora DATE_CONTROLLER = null;

    public InsertarSalidasPanel(JLabel lb , Fecha_y_hora controller) {
        this.setFECHA_SALIDA_LB( lb );
        this.DATE_CONTROLLER = controller;
        this.prepareAll();
        this.setLayout(new InsertarSalidasLayout(this));
        this.PANE.getViewport().add(this);

        System.out.println( this.getFECHA_SALIDA_LB().getText() );
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
        this.FECHA_SALIDA_LB        .setFont(this.FUENTE_GENERAL_LB);

        //campos de texto:

        this.SELLOS_TXT             .setFont(this.FUENTE_GENERAL_TXT);
        this.CANT_PALLET_TXT        .setFont(this.FUENTE_GENERAL_TXT);
        this.CANT_POR_PALETT_TXT    .setFont(this.FUENTE_GENERAL_TXT);
        this.TOTAL_UNIDADES_TXT     .setFont(this.FUENTE_GENERAL_TXT);
       // this.FECHA_ENTREGA_TXT      .setFont(this.FUENTE_GENERAL_TXT);
        this.CHOFER_TXT             .setFont(this.FUENTE_GENERAL_TXT);
        this.EMPRESA_TXT            .setFont(this.FUENTE_GENERAL_TXT);
        this.PLACAS_TXT             .setFont(this.FUENTE_GENERAL_TXT);
        this.TRACTO_TXT             .setFont(this.FUENTE_GENERAL_TXT);


        this.N_PEDIDO_CMB           .setFont(this.FUENTE_GENERAL_TXT);

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

        this.SELLOS_TXT             .setHorizontalAlignment(JTextField.CENTER);
        this.CANT_PALLET_TXT        .setHorizontalAlignment(JTextField.CENTER);
        this.CANT_POR_PALETT_TXT    .setHorizontalAlignment(JTextField.CENTER);
        this.TOTAL_UNIDADES_TXT     .setHorizontalAlignment(JTextField.CENTER);
        // this.FECHA_ENTREGA_TXT      .setHorizontalAlignment(JTextField.CENTER);
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
        //this.FECHA_ENTREGA_TXT      .addKeyListener( this );
        this.CHOFER_TXT             .addKeyListener( this );
        this.EMPRESA_TXT            .addKeyListener( this );
        this.PLACAS_TXT             .addKeyListener( this );
        this.TRACTO_TXT             .addKeyListener( this );

        this.cargaComboBox();

        // - action:
        this.REGISTRAR_SALIDA_BTN           .addActionListener( this );
        this.AGREGAR_A_LA_TABLA_BTN         .addActionListener( this );
        this.LIMPIAR_LA_TABLA_BTN           .addActionListener( this );
        this.ELIMINAR_DE_LA_TABLA_BTN       .addActionListener( this );

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
        this.CHOFER_TXT             .setText( "" );
        this.EMPRESA_TXT            .setText( "" );
        this.PLACAS_TXT             .setText( "" );
        this.TRACTO_TXT             .setText( "" );
        this.N_PEDIDO_CMB           .setSelectedIndex(0);
    }

    private void guardarSalidas(){

            /*
            [ 0 ] —> "#",
            [ 1 ] —> "N. Pedido",
            [ 2 ] —> "Sellos",
            [ 3 ] —> "Cantidad Pallet",
            [ 4 ] —> "Cantidad por pallet",
            [ 5 ] —> "Total de unidades",
            [ 6 ] —> "Fecha de salida",
            [ 7 ] —> "Hora de salida",
            [ 8 ] —> "Fecha de entrega",
            [ 9 ] —> "Chofer",
            [ 10 ] —> "Empresa",
            [ 11 ] —> "Placas",
            [ 12 ] —> "Tracto camion",
            [ 13 ] —> "color"
            */
        ArrayList<ArrayList<String>> rows = this.getTABLA_SALIDAS().getRows();

        ArrayList<Salidas> salidas = new ArrayList<Salidas>();

        for(ArrayList<String> row : rows)
        {
            Salidas salida = new Salidas();
            salida.setNumPedido( row.get( 1 ) );
            salida.setSellos( Integer.valueOf( row.get( 2 ) ) );
            salida.setCantidadPallet( Integer.valueOf( row.get( 3 ) ) );
            salida.setCantidadPorPallet( Integer.valueOf( row.get( 4 ) ) );
            salida.setTotalUnidades( Integer.valueOf( row.get( 5 ) ) );
            salida.setFechaSalida( new Fecha( DatePicker.getDateFromStringDate( row.get( 6 ) ) ));
            salida.setHoraSalida( new Hora( DatePicker.getTimeFromStringDate( row.get( 7 ) ) ));
            salida.setFechaEntrega( new Date( DatePicker.getDateFromStringDate( row.get( 8 ) ) ));
            salida.setTransporte( row.get( 9 ) );
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

            String      nPedido = this.getN_PEDIDO_CMB().getSelectedItem().toString();
            int         Sellos = Integer.parseInt(this.getSELLOS_TXT().getText());
            int         CantidadPallet = Integer.parseInt(this.getCANT_PALLET_TXT().getText());
            int         CantidadPorPallet = Integer.parseInt(this.getCANT_POR_PALETT_TXT().getText());
            int         TotalUnidades = Integer.parseInt(this.getTOTAL_UNIDADES_TXT().getText());
            Fecha       fsalida = this.DATE_CONTROLLER.getFecha();
            Hora        dsalida = this.DATE_CONTROLLER.getHora();
            Date        dateEntrega = this.getFECHA_ENTREGA().getDateAsSqlDate();
            String      idChofer = this.getCHOFER_TXT().getText();
            Transporte  chofer = Transporte.getTransporteByID( idChofer );
            this.indexRow++;


            ArrayList<String> row = new ArrayList<String>();
            /*
               [ 0 ] -> "#",
               [ 1 ] -> "N. Pedido",
               [ 2 ] -> "Sellos",
               [ 3 ] -> "Cantidad Pallet",
               [ 4 ] -> "Cantidad por pallet",
               [ 5 ] -> "Total de unidades",
               [ 6 ] -> "Fecha de salida",
               [ 7 ] -> "Hora de salida",
               [ 8 ] -> "Fecha de entrega",
               [ 9 ] -> "Chofer",
               [ 10 ] -> "Empresa",
               [ 11 ] -> "Placas",
               [ 12 ] -> "Tracto camion",
               [ 13 ] -> "color"
            */

            // [ 0 ]
            row.add( String.valueOf( this.indexRow ) );
            // [ 1 ]
            row.add( String.valueOf( nPedido ) );
            // [ 2 ]
            row.add( String.valueOf( Sellos ) );

            // [ 3 ]
            row.add( String.valueOf( CantidadPallet ) );
            // [ 4 ]
            row.add( String.valueOf( CantidadPorPallet ) );
            // [ 5 ]
            row.add( String.valueOf( TotalUnidades ) );

            // [ 6 ]
            row.add( String.valueOf( fsalida ) );
            // [ 7 ]
            row.add( String.valueOf( dsalida ) );
            // [ 8 ]
            row.add( String.valueOf( dateEntrega ) );

            // [ 9 ]
            row.add( String.valueOf( chofer.getChofer() ) );
            // [ 10 ]
            row.add( String.valueOf( chofer.getEmpresa() ) );
            // [ 11 ]
            row.add( String.valueOf( chofer.getPlacas() ) );
            // [ 12 ]
            row.add( String.valueOf( chofer.getTractoCamion() ) );

            // [ 13 ]
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

                Paragraph titulo = new Paragraph("Tools.DataBase.Salidas", FontFactory.getFont("Arial", 22, Font.BOLD, BaseColor.BLUE));
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

    public void actionOnItemChange(){
        String value = (String) this.getN_PEDIDO_CMB().getSelectedItem();
        if( !value.isEmpty() )
        {
            Salidas.busquedaNumPedido(this);
        }
    }

    public void cargaComboBox(){
        this.getN_PEDIDO_CMB().removeAllItems();
        this.getN_PEDIDO_CMB().setModel(Salidas.obtenerPedidos());
    }


    // -- GET'S Y SET'S:


    public JButton getLIMPIAR_LA_TABLA_BTN() {
        return LIMPIAR_LA_TABLA_BTN;
    }

    public JButton getELIMINAR_DE_LA_TABLA_BTN() {
        return ELIMINAR_DE_LA_TABLA_BTN;
    }

    public JButton getAGREGAR_A_LA_TABLA_BTN() {
        return AGREGAR_A_LA_TABLA_BTN;
    }

    public JLabel getFECHA_SALIDA_LB() {
        return FECHA_SALIDA_LB;
    }

    public void setFECHA_SALIDA_LB(JLabel FECHA_SALIDA_LB) {
        this.FECHA_SALIDA_LB = FECHA_SALIDA_LB;
    }

    public Table getTABLA_SALIDAS() {
        return TABLA_SALIDAS;
    }

    public JButton getREGISTRAR_SALIDA_BTN() {
        return REGISTRAR_SALIDA_BTN;
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

    public DatePicker getFECHA_ENTREGA() {
        return FECHA_ENTREGA;
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

    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(this.getSELLOS_TXT().isFocusOwner())
        {
            String type = String.valueOf(e.getKeyChar());
            System.out.println(type);
            if (type.matches("[^0-9]"))
            {
                e.consume();
            }

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_SHIFT)
            e.consume();

        if(e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            this.getREGISTRAR_SALIDA_BTN().doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
