package Forms;

import Tools.Config;
import Tools.DataBase.Entradas;
import Tools.RenderTable;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class FrmEntradas extends JFrame implements ActionListener, KeyListener, FocusListener, ItemListener, Runnable
{
    // Jlabel usados en la vista del form:
    JLabel  LblCodigoBarras,
            LblDiseno,
            LblCodigoInterno,
            LblCliente,
            LblProducto,
            LblCantidadPallet,
            LblCantidadPorPallet,
            LblTotalUnidades,
            LblNumOrden,
            LblNumPedido,
            LblCondicion,
            LblObservaciones,
            LblCantidad,
            LblFolio,
            LblFechaEntrada,
            LblHoraEntrada,
            LblChofer,
            LblEmpresa,
            LblPlacas,
            LblTractoCamion;

    // Jtext field usados en la vista del form Tools.DataBase.Entradas:
   public JTextField  TxtDiseno,
                TxtCodigoInterno,
                TxtCliente,
                TxtProducto,
                TxtCantidadPallet,
                TxtCantidadPorPallet,
                TxtTotalUnidades,
                TxtNumOrden,
                TxtNumPedido,
                TxtObservaciones,
                TxtCantidad,
                TxtFolio,
                TxtFechaEntrada,
                TxtHoraEntrada,
                TxtEmpresa,
                TxtPlacas,
                TxtTractoCamion;

    //JButtons usados en la interfaz grafica:
    JButton BtnRegistrarEntrada,
            BtnAgregarTabla,
            BtnEliminarFilas,
            BtnEliminarFila;

    //JComboBox usados en la interfaz para seleccionar diferentes valores:
    JComboBox   CmbCodigoBarras,
                CmbCondicion,
                CmbChofer;

    TableColumnModel columnModel;

    JTable TblEntradas;

    JScrollPane ScrTabla;

    JProgressBar PrgBarRegistrarEntrada;

    DefaultTableModel defaultTableModel;
    DefaultTableCellRenderer defaultTableCellRenderer;

    Date date = new Date();
    DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    Font font;

    int     cantidadPallet = 0,
            cantidadPorPallet = 0,
            totalUnidades = 0,
            numFila = 0,
            seleccion = 0;

    String  hora = "",
            minutos = "",
            amPm = "";

    Thread hiloHoras;

    Entradas entradas = null;

    Color[] rowColors = {
            Color.decode("#FF9292"), // rojo
            Color.decode("#FFFA5E"), // amarillo
            Color.decode("#96FF5E"), // verde coqueton
            Color.decode("#FD872A"), // azul cyan
            Color.decode("#5EB3FF"), // azul bajito
            Color.decode("#A27545"), // cafe
            Color.decode("#C05EFF"), // rosa
            Color.decode("#5EFFB1") // verde agua
    };


    public FrmEntradas() throws SQLException, Config.ReadException, Config.EmptyProperty {

        entradas = new Entradas();
        this.setSize(1275, 1005);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setTitle("Formulario de entradas");
        this.getContentPane().setBackground(Color.WHITE);
        setIconImage(new ImageIcon("./Data/Imagenes/IconoTprlogistics.png").getImage());

        font = new Font("HELVETICA", Font.PLAIN, 25);

        LblNumOrden = new JLabel();
        LblNumOrden.setText("No. Orden");
        LblNumOrden.setName("LblNumOrden");
        LblNumOrden.setBounds(50, 50, 200, 40);
        LblNumOrden.setBackground(Color.WHITE);
        LblNumOrden.setFont(font);
        LblNumOrden.setVisible(true);
        this.add(LblNumOrden);

        TxtNumOrden = new JTextField();
        TxtNumOrden.setName("TxtNumOrden");
        TxtNumOrden.setBounds(50, 90, 200, 40);
        TxtNumOrden.setText("");
        TxtNumOrden.setFont(font);
        TxtNumOrden.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtNumOrden.addKeyListener(this);
        TxtNumOrden.setVisible(true);
        this.add(TxtNumOrden);

        LblNumPedido = new JLabel();
        LblNumPedido.setText("No. Pedido");
        LblNumPedido.setName("LblNumPedido");
        LblNumPedido.setBounds(290, 50, 200, 40);
        LblNumPedido.setBackground(Color.WHITE);
        LblNumPedido.setFont(font);
        LblNumPedido.setVisible(true);
        this.add(LblNumPedido);

        TxtNumPedido = new JTextField();
        TxtNumPedido.setName("TxtNumPedido");
        TxtNumPedido.setBounds(290, 90, 200, 40);
        TxtNumPedido.setText("");
        TxtNumPedido.setFont(font);
        TxtNumPedido.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtNumPedido.addKeyListener(this);
        TxtNumPedido.setVisible(true);
        this.add(TxtNumPedido);

        LblFechaEntrada = new JLabel();
        LblFechaEntrada.setText("Fecha de entrada");
        LblFechaEntrada.setName("LblFechaEntrada");
        LblFechaEntrada.setBounds(770, 50, 200, 40);
        LblFechaEntrada.setBackground(Color.WHITE);
        LblFechaEntrada.setFont(font);
        LblFechaEntrada.setVisible(true);
        this.add(LblFechaEntrada);

        TxtFechaEntrada = new JTextField();
        TxtFechaEntrada.setName("TxtFechaEntrada");
        TxtFechaEntrada.setBounds(770, 90, 200, 40);
        TxtFechaEntrada.setText(dateFormat.format(date));
        TxtFechaEntrada.setFont(font);
        TxtFechaEntrada.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtFechaEntrada.setHorizontalAlignment(TxtFechaEntrada.RIGHT);
        TxtFechaEntrada.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        TxtFechaEntrada.addKeyListener(this);
        TxtFechaEntrada.setBackground(Color.WHITE);
        TxtFechaEntrada.setEditable(false);
        TxtFechaEntrada.setVisible(true);
        this.add(TxtFechaEntrada);

        LblHoraEntrada = new JLabel();
        LblHoraEntrada.setText("Hora de entrada");
        LblHoraEntrada.setName("LblHoraEntrada");
        LblHoraEntrada.setBounds(1010, 50, 200, 40);
        LblHoraEntrada.setBackground(Color.WHITE);
        LblHoraEntrada.setFont(font);
        LblHoraEntrada.setVisible(true);
        this.add(LblHoraEntrada);

        TxtHoraEntrada = new JTextField();
        TxtHoraEntrada.setName("TxtHoraEntrada");
        TxtHoraEntrada.setBounds(995, 90, 200, 40);
        TxtHoraEntrada.setText(hourFormat.format(date));
        TxtHoraEntrada.setFont(font);
        TxtHoraEntrada.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtHoraEntrada.setHorizontalAlignment(TxtHoraEntrada.RIGHT);
        TxtHoraEntrada.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        TxtHoraEntrada.addKeyListener(this);
        TxtHoraEntrada.setBackground(Color.WHITE);
        //TxtHoraEntrada.setEditable(false);
        TxtHoraEntrada.setVisible(true);
        this.add(TxtHoraEntrada);

        LblCodigoBarras = new JLabel();
        LblCodigoBarras.setText("Código de barras");
        LblCodigoBarras.setName("LblCodigoBarras");
        LblCodigoBarras.setBounds(50, 150, 200, 40);
        LblCodigoBarras.setBackground(Color.WHITE);
        LblCodigoBarras.setFont(font);
        LblCodigoBarras.setVisible(true);
        this.add(LblCodigoBarras);

        CmbCodigoBarras = new JComboBox();
        CmbCodigoBarras.setName("CmbCodigoBarras");
        CmbCodigoBarras.setBounds(50, 190, 200, 40);
        CmbCodigoBarras.setFont(font);
        CmbCodigoBarras.setBackground(Color.WHITE);
        CmbCodigoBarras.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        CmbCodigoBarras.addItemListener(this);
        CmbCodigoBarras.setVisible(true);
        this.CmbCodigoBarras.setModel(entradas.obtenerCodigoBarras());
        this.add(CmbCodigoBarras);

        LblFolio = new JLabel();
        LblFolio.setText("Folio");
        LblFolio.setName("LblFolio");
        LblFolio.setBounds(1010, 150, 200, 40);
        LblFolio.setBackground(Color.WHITE);
        LblFolio.setFont(font);
        LblFolio.setVisible(false);
        this.add(LblFolio);

        TxtFolio = new JTextField();
        TxtFolio.setName("TxtFolio");
        TxtFolio.setBounds(1010, 190, 200, 40);
        TxtFolio.setText("");
        TxtFolio.setFont(font);
        TxtFolio.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtFolio.addKeyListener(this);
        TxtFolio.setEditable(false);
        TxtFolio.setBackground(Color.WHITE);
        TxtFolio.setVisible(false);
        this.add(TxtFolio);

        LblDiseno = new JLabel();
        LblDiseno.setText("Diseño");
        LblDiseno.setName("LblDiseno");
        LblDiseno.setBounds(290, 150, 200, 40);
        LblDiseno.setBackground(Color.WHITE);
        LblDiseno.setFont(font);
        LblDiseno.setVisible(true);
        this.add(LblDiseno);

        TxtDiseno = new JTextField();
        TxtDiseno.setName("TxtDiseno");
        TxtDiseno.setBounds(290, 190, 200, 40);
        TxtDiseno.setText("");
        TxtDiseno.setFont(font);
        TxtDiseno.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtDiseno.addKeyListener(this);
        TxtDiseno.setEditable(false);
        TxtDiseno.setBackground(Color.WHITE);
        TxtDiseno.setVisible(true);
        this.add(TxtDiseno);

        LblCodigoInterno = new JLabel();
        LblCodigoInterno.setText("Código interno");
        LblCodigoInterno.setName("LblCodigoInterno");
        LblCodigoInterno.setBounds(530, 150, 200, 40);
        LblCodigoInterno.setBackground(Color.WHITE);
        LblCodigoInterno.setFont(font);
        LblCodigoInterno.setVisible(true);
        this.add(LblCodigoInterno);

        TxtCodigoInterno = new JTextField();
        TxtCodigoInterno.setName("TxtCodigoInterno");
        TxtCodigoInterno.setBounds(530, 190, 200, 40);
        TxtCodigoInterno.setText("");
        TxtCodigoInterno.setFont(font);
        TxtCodigoInterno.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtCodigoInterno.addKeyListener(this);
        TxtCodigoInterno.setEditable(false);
        TxtCodigoInterno.setBackground(Color.WHITE);
        TxtCodigoInterno.setVisible(true);
        this.add(TxtCodigoInterno);

        LblCliente = new JLabel();
        LblCliente.setText("Cliente");
        LblCliente.setName("LblCliente");
        LblCliente.setBounds(770, 150, 220, 40);
        LblCliente.setBackground(Color.WHITE);
        LblCliente.setFont(font);
        LblCliente.setVisible(true);
        this.add(LblCliente);

        TxtCliente = new JTextField();
        TxtCliente.setName("TxtCliente");
        TxtCliente.setBounds(770, 190, 200, 40);
        TxtCliente.setText("");
        TxtCliente.setFont(font);
        TxtCliente.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtCliente.addKeyListener(this);
        TxtCliente.setEditable(false);
        TxtCliente.setBackground(Color.WHITE);
        TxtCliente.setVisible(true);
        this.add(TxtCliente);

        LblProducto = new JLabel();
        LblProducto.setText("Producto");
        LblProducto.setName("LblProducto");
        LblProducto.setBounds(50, 250, 220, 40);
        LblProducto.setBackground(Color.WHITE);
        LblProducto.setFont(font);
        LblProducto.setVisible(true);
        this.add(LblProducto);

        TxtProducto = new JTextField();
        TxtProducto.setName("TxtProducto");
        TxtProducto.setBounds(50, 290, 1160, 40);
        TxtProducto.setText("");
        TxtProducto.setFont(font);
        TxtProducto.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtProducto.addKeyListener(this);
        TxtProducto.setEditable(false);
        TxtProducto.setBackground(Color.WHITE);
        TxtProducto.setVisible(true);
        this.add(TxtProducto);

        LblCantidadPallet = new JLabel();
        LblCantidadPallet.setText("Cantidad Pallet");
        LblCantidadPallet.setName("LblCantidadPallet");
        LblCantidadPallet.setBounds(50, 350, 200, 40);
        LblCantidadPallet.setBackground(Color.WHITE);
        LblCantidadPallet.setFont(font);
        LblCantidadPallet.setVisible(true);
        this.add(LblCantidadPallet);

        TxtCantidadPallet = new JTextField();
        TxtCantidadPallet.setName("TxtCantidadPallet");
        TxtCantidadPallet.setBounds(50, 390, 200, 40);
        TxtCantidadPallet.setText("0");
        TxtCantidadPallet.setFont(font);
        TxtCantidadPallet.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtCantidadPallet.addKeyListener(this);
        TxtCantidadPallet.addFocusListener(this);
        TxtCantidadPallet.setVisible(true);
        this.add(TxtCantidadPallet);

        LblCantidadPorPallet = new JLabel();
        LblCantidadPorPallet.setText("Cantidad por pallet");
        LblCantidadPorPallet.setName("LblCantidadPorPallet");
        LblCantidadPorPallet.setBounds(290, 350, 220, 40);
        LblCantidadPorPallet.setBackground(Color.WHITE);
        LblCantidadPorPallet.setFont(font);
        LblCantidadPorPallet.setVisible(true);
        this.add(LblCantidadPorPallet);

        TxtCantidadPorPallet = new JTextField();
        TxtCantidadPorPallet.setName("TxtCantidadPorPallet");
        TxtCantidadPorPallet.setBounds(290, 390, 200, 40);
        TxtCantidadPorPallet.setText("0");
        TxtCantidadPorPallet.setFont(font);
        TxtCantidadPorPallet.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtCantidadPorPallet.addKeyListener(this);
        TxtCantidadPorPallet.addFocusListener(this);
        TxtCantidadPorPallet.setVisible(true);
        this.add(TxtCantidadPorPallet);

        LblTotalUnidades = new JLabel();
        LblTotalUnidades.setText("Total de unidades");
        LblTotalUnidades.setName("LblTotalUnidades");
        LblTotalUnidades.setBounds(530, 350, 220, 40);
        LblTotalUnidades.setBackground(Color.WHITE);
        LblTotalUnidades.setFont(font);
        LblTotalUnidades.setVisible(true);
        this.add(LblTotalUnidades);

        TxtTotalUnidades = new JTextField();
        TxtTotalUnidades.setName("TxtTotalUnidades");
        TxtTotalUnidades.setBounds(530, 390, 200, 40);
        TxtTotalUnidades.setText("");
        TxtTotalUnidades.setFont(font);
        TxtTotalUnidades.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtTotalUnidades.addKeyListener(this);
        TxtTotalUnidades.setEditable(false);
        TxtTotalUnidades.setBackground(Color.white);
        TxtTotalUnidades.setVisible(true);
        this.add(TxtTotalUnidades);

        LblCondicion = new JLabel();
        LblCondicion.setText("Condición");
        LblCondicion.setName("LblCondicion");
        LblCondicion.setBounds(770, 350, 220, 40);
        LblCondicion.setBackground(Color.WHITE);
        LblCondicion.setFont(font);
        LblCondicion.setVisible(true);
        this.add(LblCondicion);

        CmbCondicion = new JComboBox();
        CmbCondicion.setName("CmbCondicion");
        CmbCondicion.setBounds(770, 390, 200, 40);
        CmbCondicion.setFont(font);
        CmbCondicion.setBackground(Color.WHITE);
        CmbCondicion.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        CmbCondicion.addKeyListener(this);
        CmbCondicion.addItem("");
        CmbCondicion.addItem("Bueno");
        CmbCondicion.addItem("Malo");
        CmbCondicion.addItem("Promedio");
        CmbCondicion.setVisible(true);
        this.add(CmbCondicion);

        LblObservaciones = new JLabel();
        LblObservaciones.setText("Observaciones");
        LblObservaciones.setName("LblObservaciones");
        LblObservaciones.setBounds(1010, 350, 220, 40);
        LblObservaciones.setBackground(Color.WHITE);
        LblObservaciones.setFont(font);
        LblObservaciones.setVisible(true);
        this.add(LblObservaciones);

        TxtObservaciones = new JTextField();
        TxtObservaciones.setName("TxtObservaciones");
        TxtObservaciones.setBounds(1010, 390, 200, 40);
        TxtObservaciones.setText("");
        TxtObservaciones.setFont(font);
        TxtObservaciones.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtObservaciones.addKeyListener(this);
        TxtObservaciones.setVisible(true);
        this.add(TxtObservaciones);

        LblChofer = new JLabel();
        LblChofer.setText("Chofer");
        LblChofer.setName("LblChofer");
        LblChofer.setBounds(50, 450, 220, 40);
        LblChofer.setBackground(Color.WHITE);
        LblChofer.setFont(font);
        LblChofer.setVisible(true);
        this.add(LblChofer);

        CmbChofer = new JComboBox();
        CmbChofer.setName("CmbChofer");
        CmbChofer.setBounds(50, 490, 200, 40);
        CmbChofer.setFont(font);
        CmbChofer.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        CmbChofer.addKeyListener(this);
        CmbChofer.setBackground(Color.white);
        CmbChofer.setVisible(true);
        CmbChofer.addItemListener(this);
        this.CmbChofer.setModel(entradas.obtenerChofer());
        this.add(CmbChofer);

        LblEmpresa = new JLabel();
        LblEmpresa.setText("Empresa");
        LblEmpresa.setName("LblEmpresa");
        LblEmpresa.setBounds(290, 450, 220, 40);
        LblEmpresa.setBackground(Color.WHITE);
        LblEmpresa.setFont(font);
        LblEmpresa.setVisible(true);
        this.add(LblEmpresa);

        TxtEmpresa = new JTextField();
        TxtEmpresa.setName("TxtEmpresa");
        TxtEmpresa.setBounds(290, 490, 200, 40);
        TxtEmpresa.setText("");
        TxtEmpresa.setFont(font);
        TxtEmpresa.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtEmpresa.addKeyListener(this);
        TxtEmpresa.setBackground(Color.white);
        TxtEmpresa.setVisible(true);
        this.add(TxtEmpresa);

        LblPlacas = new JLabel();
        LblPlacas.setText("Placas");
        LblPlacas.setName("LblPlacas");
        LblPlacas.setBounds(530, 450, 220, 40);
        LblPlacas.setBackground(Color.WHITE);
        LblPlacas.setFont(font);
        LblPlacas.setVisible(true);
        this.add(LblPlacas);

        TxtPlacas = new JTextField();
        TxtPlacas.setName("TxtPlacas");
        TxtPlacas.setBounds(530, 490, 200, 40);
        TxtPlacas.setText("");
        TxtPlacas.setFont(font);
        TxtPlacas.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtPlacas.addKeyListener(this);
        TxtPlacas.setBackground(Color.white);
        TxtPlacas.setVisible(true);
        this.add(TxtPlacas);

        LblTractoCamion = new JLabel();
        LblTractoCamion.setText("Tracto Camion");
        LblTractoCamion.setName("LblTractoCamion");
        LblTractoCamion.setBounds(770, 450, 220, 40);
        LblTractoCamion.setBackground(Color.WHITE);
        LblTractoCamion.setFont(font);
        LblTractoCamion.setVisible(true);
        this.add(LblTractoCamion);

        TxtTractoCamion = new JTextField();
        TxtTractoCamion.setName("TxtTractoCamion");
        TxtTractoCamion.setBounds(770, 490, 200, 40);
        TxtTractoCamion.setText("");
        TxtTractoCamion.setFont(font);
        TxtTractoCamion.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtTractoCamion.addKeyListener(this);
        TxtTractoCamion.setBackground(Color.white);
        TxtTractoCamion.setVisible(true);
        this.add(TxtTractoCamion);

        LblCantidad = new JLabel();
        LblCantidad.setText("Cantidad de folios");
        LblCantidad.setName("LblCantidad");
        LblCantidad.setBounds(1010, 450, 220, 40);
        LblCantidad.setBackground(Color.WHITE);
        LblCantidad.setFont(font);
        LblCantidad.setVisible(true);
        this.add(LblCantidad);

        TxtCantidad = new JTextField();
        TxtCantidad.setName("TxtCantidad");
        TxtCantidad.setBounds(1010, 490, 140, 40);
        TxtCantidad.setText("");
        TxtCantidad.setFont(font);
        TxtCantidad.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtCantidad.addKeyListener(this);
        TxtCantidad.setVisible(true);
        this.add(TxtCantidad);

        BtnAgregarTabla = new JButton();
        BtnAgregarTabla.setName("BtnAgregarTabla");
        BtnAgregarTabla.setText("✔");
        BtnAgregarTabla.setBounds(1160, 490, 50, 40);
        BtnAgregarTabla.setFont(font);
        BtnAgregarTabla.setBackground(Color.white);
        BtnAgregarTabla.addActionListener(this);
        BtnAgregarTabla.setCursor(new Cursor(HAND_CURSOR));
        BtnAgregarTabla.setFocusPainted(false);
        BtnAgregarTabla.setVisible(true);
        BtnAgregarTabla.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        this.add(BtnAgregarTabla);

        TblEntradas = new JTable(){

            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component comp = super.prepareRenderer(renderer, row, column);

                if(!comp.getBackground().equals(getSelectionBackground())) {
                    int indexColor = Integer.valueOf((String) this.getModel().getValueAt(row, 6));
                    comp.setBackground(rowColors[indexColor]);
                }

                return comp;
            }
        };

        TableCellRenderer tableCellRenderer = new RenderTable();
        TblEntradas.setDefaultRenderer(Object.class, tableCellRenderer);
        defaultTableModel = new DefaultTableModel();

        BtnEliminarFilas = new JButton();
        BtnEliminarFilas.setText("Eliminar");

        TblEntradas.setModel(defaultTableModel);
        TblEntradas.setPreferredScrollableViewportSize(Toolkit.getDefaultToolkit().getScreenSize());
        TblEntradas.setFillsViewportHeight(true);
        TblEntradas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        defaultTableModel.setColumnIdentifiers(new String[] {"#", "Folio", "Código de barras", "Diseño", "Producto", "Cantidad/Unidad","color"});
        ScrTabla = new JScrollPane(TblEntradas, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        ScrTabla.setBounds(50, 570, 1160, 300);
        getContentPane().add(ScrTabla);

        defaultTableCellRenderer = new DefaultTableCellRenderer();
        defaultTableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        TblEntradas.getColumnModel().getColumn(0).setCellRenderer(defaultTableCellRenderer);
        TblEntradas.getColumnModel().getColumn(1).setCellRenderer(defaultTableCellRenderer);
        TblEntradas.getColumnModel().getColumn(2).setCellRenderer(defaultTableCellRenderer);
        TblEntradas.getColumnModel().getColumn(3).setCellRenderer(defaultTableCellRenderer);
        TblEntradas.getColumnModel().getColumn(4).setCellRenderer(defaultTableCellRenderer);
        TblEntradas.getColumnModel().getColumn(5).setCellRenderer(defaultTableCellRenderer);
        TblEntradas.getColumnModel().getColumn(6).setCellRenderer(defaultTableCellRenderer);

        columnModel = TblEntradas.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(52);
        columnModel.getColumn(1).setPreferredWidth(221);
        columnModel.getColumn(2).setPreferredWidth(221);
        columnModel.getColumn(3).setPreferredWidth(221);
        columnModel.getColumn(4).setPreferredWidth(221);
        columnModel.getColumn(5).setPreferredWidth(221);

        columnModel.getColumn(6).setMaxWidth(0);
        columnModel.getColumn(6).setMinWidth(0);
        columnModel.getColumn(6).setPreferredWidth(0);

        BtnRegistrarEntrada = new JButton();
        BtnRegistrarEntrada.setName("BtnRegistrarEntrada");
        BtnRegistrarEntrada.setText("Registrar Tools.DataBase.Entradas");
        BtnRegistrarEntrada.setBounds(530, 895, 220, 45);
        BtnRegistrarEntrada.setFont(font);
        BtnRegistrarEntrada.setBackground(Color.white);
        BtnRegistrarEntrada.addKeyListener(this);
        BtnRegistrarEntrada.addActionListener(this);
        BtnRegistrarEntrada.setCursor(new Cursor(HAND_CURSOR));
        BtnRegistrarEntrada.setFocusPainted(false);
        BtnRegistrarEntrada.setVisible(true);
        BtnRegistrarEntrada.setEnabled(false);
        BtnRegistrarEntrada.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        this.add(BtnRegistrarEntrada);

        BtnEliminarFila = new JButton();
        BtnEliminarFila.setName("BtnEliminarFila");
        BtnEliminarFila.setText("Eliminar fila");
        BtnEliminarFila.setBounds(1050, 875, 160, 35);
        BtnEliminarFila.setFont(font);
        BtnEliminarFila.setBackground(Color.white);
        BtnEliminarFila.addKeyListener(this);
        BtnEliminarFila.addActionListener(this);
        BtnEliminarFila.setCursor(new Cursor(HAND_CURSOR));
        BtnEliminarFila.setFocusPainted(false);
        BtnEliminarFila.setVisible(true);
        BtnEliminarFila.setEnabled(false);
        BtnEliminarFila.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        this.add(BtnEliminarFila);

        PrgBarRegistrarEntrada = new JProgressBar();
        PrgBarRegistrarEntrada.setName("PrgBarRegistrarEntrada");
        PrgBarRegistrarEntrada.setBounds(388, 895, 500, 45);
        PrgBarRegistrarEntrada.setFont(font);
        PrgBarRegistrarEntrada.setBackground(Color.white);
        PrgBarRegistrarEntrada.setVisible(false);
        PrgBarRegistrarEntrada.setEnabled(true);
        PrgBarRegistrarEntrada.setValue(0);
        PrgBarRegistrarEntrada.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.add(PrgBarRegistrarEntrada);

        hiloHoras = new Thread(this);
        hiloHoras.start();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == BtnRegistrarEntrada)
        {
            if(TxtDiseno.getText().isEmpty() || TxtCodigoInterno.getText().isEmpty() || TxtCliente.getText().isEmpty() || TxtProducto.getText().isEmpty() || TxtCantidadPallet.getText().isEmpty() ||
               TxtCantidadPorPallet.getText().isEmpty() || TxtTotalUnidades.getText().isEmpty() || TxtNumOrden.getText().isEmpty() || TxtNumPedido.getText().isEmpty() ||
               TxtObservaciones.getText().isEmpty() || TxtEmpresa.getText().isEmpty() || TxtPlacas.getText().isEmpty() || TxtTractoCamion.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Falta llenar campos", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                if(sumatoriaCantidadUnidad() == Integer.parseInt(TxtTotalUnidades.getText()))
                {
                    BtnRegistrarEntrada.setVisible(false);
                    PrgBarRegistrarEntrada.setVisible(true);
                    for (int i = 0; i < TblEntradas.getRowCount(); i++)
                    {
                        entradas.setFechaEntrada(TxtFechaEntrada.getText());
                        entradas.setHoraEntrada(TxtHoraEntrada.getText());
                        entradas.setCodigoBarras((String) TblEntradas.getValueAt(i, 2));
                        entradas.setDiseno(Integer.parseInt((String) TblEntradas.getValueAt(i, 3)));
                        entradas.setCliente(TxtCliente.getText());
                        entradas.setProducto((String) TblEntradas.getValueAt(i, 4));
                        entradas.setCantidadPallet(Integer.parseInt(TxtCantidadPallet.getText()));
                        entradas.setCantidadPorPallet(Integer.parseInt((String) TblEntradas.getValueAt(i, 5)));
                        entradas.setCodigoInterno(TxtCodigoInterno.getText());
                        entradas.setTotalUnidades(Integer.parseInt(TxtTotalUnidades.getText()));
                        entradas.setNumOrden(Integer.parseInt(TxtNumOrden.getText()));
                        entradas.setNumPedido(Integer.parseInt(TxtNumPedido.getText()));
                        entradas.setCondicion(CmbCondicion.getSelectedItem().toString());
                        entradas.setObservaciones(TxtObservaciones.getText());
                        //entradas.setFolio(Integer.parseInt((String) TblEntradas.getValueAt(i, 1)));
                        entradas.setFolio(0);
                        entradas.setChofer((String) CmbChofer.getSelectedItem());
                        entradas.setEmpresa(TxtEmpresa.getText());
                        entradas.setPlacas(TxtPlacas.getText());
                        entradas.setTractoCamion(TxtTractoCamion.getText());

                        entradas.insertarEntrada();

                        LlenarProgressBar();
                    }
                    JOptionPane.showMessageDialog(this, "Tools.DataBase.Entradas registradas", "Registro de entrada", JOptionPane.INFORMATION_MESSAGE);

                    seleccion = JOptionPane.showConfirmDialog(null, "¿Quieres agregar otra orden?", "Confirmacion de órden", JOptionPane.YES_NO_OPTION);

                    if (seleccion == JOptionPane.YES_OPTION)
                    {
                        TxtNumPedido.setText("");
                        TxtNumPedido.requestFocus();
                        vaciarTabla();
                    }
                    else if(seleccion == JOptionPane.NO_OPTION)
                    {
                        vaciarTextos();
                        vaciarTabla();
                    }
                    BtnRegistrarEntrada.setVisible(true);
                    PrgBarRegistrarEntrada.setVisible(false);
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "El total de unidades no es el mismo a los ingresados en la tabla", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if(e.getSource() == BtnAgregarTabla)
        {
            if(TxtCantidad.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(this, "No se escribio cantidad de filas", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if((Integer.parseInt(TxtCantidad.getText()) * 2) == Integer.parseInt(TxtCantidadPallet.getText()))
            {
                JOptionPane.showMessageDialog(this, "La cantidad tiene que ser el doble a la cantidad de pallet a agregar a la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if(Integer.parseInt(TxtCantidad.getText()) <= 60 && Integer.parseInt(TxtCantidad.getText()) > 0)
            {
                int veces = 0;
                int valor = 0;
                for(int i = 1; i <= Integer.parseInt(TxtCantidad.getText()); i++)
                {
                    String [] fila = new String[7];
                    fila[0] = String.valueOf(i + numFila);
                    fila[1] = TxtFolio.getText();
                    fila[2] = CmbCodigoBarras.getSelectedItem().toString();
                    fila[3] = TxtDiseno.getText();
                    fila[4] = TxtProducto.getText();
                    fila[5] = String.valueOf(Integer.parseInt(TxtCantidadPorPallet.getText()) / 2);
                    fila[6] = String.valueOf(valor);

                    defaultTableModel.addRow(fila);

                    if(veces == 1) {
                        veces = 0;
                        valor = (valor == (rowColors.length - 1))? 0 : (valor + 1);
                        continue;
                    }
                    veces++;
                }


                numFila += Integer.parseInt(TxtCantidad.getText());
                BtnEliminarFila.setEnabled(true);
                BtnRegistrarEntrada.setEnabled(true);
            }
            else if(Integer.parseInt(TxtCantidad.getText()) > 60)
            {
                JOptionPane.showMessageDialog(this, "Solo se pueden agregar hasta 60 elementos, por favor verifique si la cantidad es correcta.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else if(Integer.parseInt(TxtCantidad.getText()) == 0){
                JOptionPane.showMessageDialog(this, "No es posible agregar 0 elementos, verifique que la cantidad sea la correcta e intente de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if(e.getSource() == BtnEliminarFila)
        {
            seleccion = JOptionPane.showConfirmDialog(null, "¿Seguro que quiere eliminar la fila seleccionada?", "Eliminar fila seleccionada", JOptionPane.YES_NO_OPTION);

            if (seleccion == JOptionPane.YES_OPTION)
            {
                int filaSelect = TblEntradas.getSelectedRow();
                if(filaSelect >= 0)
                {
                    defaultTableModel.removeRow(filaSelect);
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Fila no seleccionada");
                }
            }
            else if(seleccion == JOptionPane.NO_OPTION)
            {

            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        if(TxtObservaciones.isFocusOwner())
        {
            if (!Character.isLetter(e.getKeyChar()) && !(e.getKeyChar() == KeyEvent.VK_SPACE) && !(e.getKeyChar() == KeyEvent.VK_BACK_SPACE))
            {
                e.consume();
            }
        }

        if(TxtNumOrden.isFocusOwner() || TxtNumPedido.isFocusOwner() || TxtCantidadPallet.isFocusOwner() || TxtCantidadPorPallet.isFocusOwner() || TxtCantidad.isFocusOwner())
        {
            if (Character.isLetter(e.getKeyChar()) && !(e.getKeyChar() == KeyEvent.VK_SPACE) && !(e.getKeyChar() == KeyEvent.VK_BACK_SPACE))
            {
                e.consume();
            }
        }

        if(TxtCantidadPallet.getText().isEmpty())
        {
            TxtCantidadPallet.setText("0");
        }

        if(TxtCantidadPorPallet.getText().isEmpty())
        {
            TxtCantidadPorPallet.setText("0");
        }
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            BtnRegistrarEntrada.doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        if (TxtCantidadPallet.isFocusOwner() || TxtCantidadPorPallet.isFocusOwner())
        {
            cantidadPallet = Integer.parseInt(TxtCantidadPallet.getText());
            cantidadPorPallet = Integer.parseInt(TxtCantidadPorPallet.getText());
            totalUnidades = cantidadPallet * cantidadPorPallet;

            TxtTotalUnidades.setText(String.valueOf(totalUnidades));
            DecimalFormat df = new DecimalFormat("#,###");
            if (TxtTotalUnidades.getText().length() >= 1)
            {
                TxtTotalUnidades.setText(df.format(Integer.valueOf(TxtTotalUnidades.getText().replace(".", "").replace(",", ""))));
            }
        }
    }

    @Override
    public void focusGained(FocusEvent e)
    {
        if(TxtCantidadPallet.isFocusOwner())
        {
            TxtCantidadPallet.selectAll();
        }

        if(TxtCantidadPorPallet.isFocusOwner())
        {
            TxtCantidadPorPallet.selectAll();
        }
    }

    @Override
    public void focusLost(FocusEvent e) {}

    @Override
    public void itemStateChanged(ItemEvent e)
    {
        if(e.getStateChange() == ItemEvent.SELECTED)
        {
            entradas.setCodigoBarras(CmbCodigoBarras.getSelectedItem().toString());
            entradas.setChofer(CmbChofer.getSelectedItem().toString());
            //entradas.busquedaCodigoBarras(this);
            //entradas.busquedaChofer(this);
        }
    }

    private void vaciarTextos()
    {
        CmbCodigoBarras.setSelectedIndex(0);
        TxtDiseno.setText("");
        TxtCodigoInterno.setText("");
        TxtCliente.setText("");
        TxtProducto.setText("");
        TxtCantidadPallet.setText("");
        TxtCantidadPorPallet.setText("");
        TxtTotalUnidades.setText("");
        TxtNumOrden.setText("");
        TxtNumPedido.setText("");
        CmbCondicion.setSelectedIndex(0);
        TxtObservaciones.setText("");
        TxtCantidad.setText("");
        TxtFolio.setText("");
        CmbChofer.setSelectedIndex(0);
        TxtEmpresa.setText("");
        TxtPlacas.setText("");
        TxtTractoCamion.setText("");
    }

    private void vaciarTabla()
    {
        for (int i = 0; i < TblEntradas.getRowCount(); i++)
        {
            defaultTableModel.removeRow(i);
            i-=1;
        }
    }

    @Override
    public void run()
    {
        Thread currentTime = Thread.currentThread();
        while(currentTime == hiloHoras)
        {
            CalcularHora();
            TxtHoraEntrada.setText(hora + ":" + minutos + " " + amPm);
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException interruptedException)
            {
                interruptedException.printStackTrace();
            }
        }
    }

    private void CalcularHora()
    {
        Calendar calendar = new GregorianCalendar();
        Date fechaHoraActual = new Date();
        calendar.setTime(fechaHoraActual);
        amPm = calendar.get(Calendar.AM_PM) == Calendar.AM?"A.M.":"P.M.";

        if(amPm.equals("P.M."))
        {
            int h = calendar.get(Calendar.HOUR_OF_DAY) - 12;
            hora = h>9?""+h:"0"+h;
        }
        else
        {
            hora = calendar.get(Calendar.HOUR_OF_DAY)>9?""+calendar.get(Calendar.HOUR_OF_DAY):"0"+calendar.get(Calendar.HOUR_OF_DAY);
        }
        minutos = calendar.get(Calendar.MINUTE)>9?""+calendar.get(Calendar.MINUTE):"0"+calendar.get(Calendar.MINUTE);
    }

    private int sumatoriaCantidadUnidad()
    {
        int fila = TblEntradas.getRowCount();
        int i;
        int sumatoria = 0;
        StringBuilder valores= new StringBuilder();

        for (i = 0; i < fila; i++)
        {
            String valor = (String) TblEntradas.getValueAt(i, 5);
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

    private void LlenarProgressBar()
    {
        int cont = 0;
        try
        {
            while (cont <= 100)
            {
                PrgBarRegistrarEntrada.setValue(cont + 1);
                Thread.sleep(10);
                cont += 1;
            }
        }
        catch (Exception e) {}
    }


}
