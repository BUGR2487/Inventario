package Forms;

import Tools.DataBase.Salidas;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import java.io.*;
import java.sql.SQLException;

public class FrmSalidas extends JFrame implements ActionListener, KeyListener, ItemListener
{
    public JLabel  LblCantidadPallet,
            LblCantidadPorPallet,
            LblTotalUnidades,
            LblNumPedido,
            LblChofer,
            LblEmpresa,
            LblPlacas,
            LblTractoCamion,
            LblSellos,
            LblEntregaDelDia;

    public JTextField  TxtCantidadPallet,
                TxtCantidadPorPallet,
                TxtTotalUnidades,
                TxtNumPedido,
                TxtChofer,
                TxtEmpresa,
                TxtPlacas,
                TxtTractoCamion,
                TxtSellos,
                TxtEntregaDelDia;

    JComboBox   CmbNumPedido;
    JButton     BtnRegistrarSalida;

    TableColumnModel columnModel;

    JTable      TblEntradas;
    JScrollPane ScrTabla;

    DefaultTableModel defaultTableModel;
    DefaultTableCellRenderer defaultTableCellRenderer;

    Font font;

    Salidas salidas = null;

    public FrmSalidas() throws SQLException {
        salidas = new Salidas();
        this.setSize(1275, 830);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setTitle("Formulario de salidas");
        this.getContentPane().setBackground(Color.WHITE);
        setIconImage(new ImageIcon(getClass().getResource("Forms/Imagenes/IconoTprlogistics.png")).getImage());

        font = new Font("HELVETICA", Font.PLAIN, 25);

        LblNumPedido = new JLabel();
        LblNumPedido.setText("No. Pedido");
        LblNumPedido.setName("LblNumPedido");
        LblNumPedido.setBounds(50, 50, 200, 40);
        LblNumPedido.setBackground(Color.WHITE);
        LblNumPedido.setFont(font);
        LblNumPedido.setVisible(true);
        this.add(LblNumPedido);

        CmbNumPedido = new JComboBox();
        CmbNumPedido.setName("CmbNumPedido");
        CmbNumPedido.setBounds(50, 90, 200, 40);
        CmbNumPedido.setFont(font);
        CmbNumPedido.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        CmbNumPedido.addKeyListener(this);
        CmbNumPedido.setBackground(Color.white);
        CmbNumPedido.addItemListener(this);
        this.CmbNumPedido.setModel(salidas.obtenerPedidos());
        CmbNumPedido.setVisible(true);
        this.add(CmbNumPedido);

        LblSellos = new JLabel();
        LblSellos.setText("Sellos");
        LblSellos.setName("LblSellos");
        LblSellos.setBounds(50, 150, 200, 40);
        LblSellos.setBackground(Color.WHITE);
        LblSellos.setFont(font);
        LblSellos.setVisible(true);
        this.add(LblSellos);

        TxtSellos = new JTextField();
        TxtSellos.setName("TxtSellos");
        TxtSellos.setBounds(50, 190, 200, 40);
        TxtSellos.setText("");
        TxtSellos.setFont(font);
        TxtSellos.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtSellos.addKeyListener(this);
        TxtSellos.setVisible(true);
        this.add(TxtSellos);

        LblCantidadPallet = new JLabel();
        LblCantidadPallet.setText("Cantidad Pallet");
        LblCantidadPallet.setName("LblCantidadPallet");
        LblCantidadPallet.setBounds(290, 150, 200, 40);
        LblCantidadPallet.setBackground(Color.WHITE);
        LblCantidadPallet.setFont(font);
        LblCantidadPallet.setVisible(true);
        this.add(LblCantidadPallet);

        TxtCantidadPallet = new JTextField();
        TxtCantidadPallet.setName("TxtCantidadPallet");
        TxtCantidadPallet.setBounds(290, 190, 200, 40);
        TxtCantidadPallet.setText("");
        TxtCantidadPallet.setFont(font);
        TxtCantidadPallet.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtCantidadPallet.setBackground(Color.white);
        TxtCantidadPallet.addKeyListener(this);
        TxtCantidadPallet.setEditable(false);
        TxtCantidadPallet.setVisible(true);
        this.add(TxtCantidadPallet);

        LblCantidadPorPallet = new JLabel();
        LblCantidadPorPallet.setText("Cantidad por pallet");
        LblCantidadPorPallet.setName("LblCantidadPorPallet");
        LblCantidadPorPallet.setBounds(530, 150, 220, 40);
        LblCantidadPorPallet.setBackground(Color.WHITE);
        LblCantidadPorPallet.setFont(font);
        LblCantidadPorPallet.setVisible(true);
        this.add(LblCantidadPorPallet);

        TxtCantidadPorPallet = new JTextField();
        TxtCantidadPorPallet.setName("TxtCantidadPorPallet");
        TxtCantidadPorPallet.setBounds(530, 190, 200, 40);
        TxtCantidadPorPallet.setText("");
        TxtCantidadPorPallet.setFont(font);
        TxtCantidadPorPallet.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtCantidadPorPallet.setBackground(Color.white);
        TxtCantidadPorPallet.addKeyListener(this);
        TxtCantidadPorPallet.setEditable(false);
        TxtCantidadPorPallet.setVisible(true);
        this.add(TxtCantidadPorPallet);

        LblTotalUnidades = new JLabel();
        LblTotalUnidades.setText("Total de unidades");
        LblTotalUnidades.setName("LblTotalUnidades");
        LblTotalUnidades.setBounds(770, 150, 220, 40);
        LblTotalUnidades.setBackground(Color.WHITE);
        LblTotalUnidades.setFont(font);
        LblTotalUnidades.setVisible(true);
        this.add(LblTotalUnidades);

        TxtTotalUnidades = new JTextField();
        TxtTotalUnidades.setName("TxtTotalUnidades");
        TxtTotalUnidades.setBounds(770, 190, 200, 40);
        TxtTotalUnidades.setText("");
        TxtTotalUnidades.setFont(font);
        TxtTotalUnidades.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtTotalUnidades.addKeyListener(this);
        TxtTotalUnidades.setEditable(false);
        TxtTotalUnidades.setBackground(Color.white);
        TxtTotalUnidades.setVisible(true);
        this.add(TxtTotalUnidades);

        LblEntregaDelDia = new JLabel();
        LblEntregaDelDia.setText("Fecha de entrega");
        LblEntregaDelDia.setName("LblEntregaDelDia");
        LblEntregaDelDia.setBounds(1010, 150, 220, 40);
        LblEntregaDelDia.setBackground(Color.WHITE);
        LblEntregaDelDia.setFont(font);
        LblEntregaDelDia.setVisible(true);
        this.add(LblEntregaDelDia);

        TxtEntregaDelDia = new JTextField();
        TxtEntregaDelDia.setName("TxtEntregaDelDia");
        TxtEntregaDelDia.setBounds(1010, 190, 200, 40);
        TxtEntregaDelDia.setText("");
        TxtEntregaDelDia.setFont(font);
        TxtEntregaDelDia.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtEntregaDelDia.addKeyListener(this);
        TxtEntregaDelDia.setBackground(Color.white);
        TxtEntregaDelDia.setVisible(true);
        this.add(TxtEntregaDelDia);

        LblChofer = new JLabel();
        LblChofer.setText("Chofer");
        LblChofer.setName("LblChofer");
        LblChofer.setBounds(50, 250, 220, 40);
        LblChofer.setBackground(Color.WHITE);
        LblChofer.setFont(font);
        LblChofer.setVisible(true);
        this.add(LblChofer);

        TxtChofer = new JTextField();
        TxtChofer.setName("TxtChofer");
        TxtChofer.setBounds(50, 290, 200, 40);
        TxtChofer.setText("");
        TxtChofer.setFont(font);
        TxtChofer.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtChofer.addKeyListener(this);
        TxtChofer.setBackground(Color.white);
        TxtChofer.setEditable(false);
        TxtChofer.setVisible(true);
        this.add(TxtChofer);

        LblEmpresa = new JLabel();
        LblEmpresa.setText("Empresa");
        LblEmpresa.setName("LblEmpresa");
        LblEmpresa.setBounds(290, 250, 220, 40);
        LblEmpresa.setBackground(Color.WHITE);
        LblEmpresa.setFont(font);
        LblEmpresa.setVisible(true);
        this.add(LblEmpresa);

        TxtEmpresa = new JTextField();
        TxtEmpresa.setName("TxtEmpresa");
        TxtEmpresa.setBounds(290, 290, 200, 40);
        TxtEmpresa.setText("");
        TxtEmpresa.setFont(font);
        TxtEmpresa.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtEmpresa.addKeyListener(this);
        TxtEmpresa.setBackground(Color.white);
        TxtEmpresa.setEditable(false);
        TxtEmpresa.setVisible(true);
        this.add(TxtEmpresa);

        LblPlacas = new JLabel();
        LblPlacas.setText("Placas");
        LblPlacas.setName("LblPlacas");
        LblPlacas.setBounds(530, 250, 220, 40);
        LblPlacas.setBackground(Color.WHITE);
        LblPlacas.setFont(font);
        LblPlacas.setVisible(true);
        this.add(LblPlacas);

        TxtPlacas = new JTextField();
        TxtPlacas.setName("TxtPlacas");
        TxtPlacas.setBounds(530, 290, 200, 40);
        TxtPlacas.setText("");
        TxtPlacas.setFont(font);
        TxtPlacas.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtPlacas.addKeyListener(this);
        TxtPlacas.setBackground(Color.white);
        TxtPlacas.setEditable(false);
        TxtPlacas.setVisible(true);
        this.add(TxtPlacas);

        LblTractoCamion = new JLabel();
        LblTractoCamion.setText("Tracto Camion");
        LblTractoCamion.setName("LblTractoCamion");
        LblTractoCamion.setBounds(770, 250, 220, 40);
        LblTractoCamion.setBackground(Color.WHITE);
        LblTractoCamion.setFont(font);
        LblTractoCamion.setVisible(true);
        this.add(LblTractoCamion);

        TxtTractoCamion = new JTextField();
        TxtTractoCamion.setName("TxtTractoCamion");
        TxtTractoCamion.setBounds(770, 290, 200, 40);
        TxtTractoCamion.setText("");
        TxtTractoCamion.setFont(font);
        TxtTractoCamion.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtTractoCamion.addKeyListener(this);
        TxtTractoCamion.setBackground(Color.white);
        TxtTractoCamion.setEditable(false);
        TxtTractoCamion.setVisible(true);
        this.add(TxtTractoCamion);

        TblEntradas = new JTable();
        defaultTableModel = new DefaultTableModel();

        TblEntradas.setModel(defaultTableModel);
        TblEntradas.setPreferredScrollableViewportSize(Toolkit.getDefaultToolkit().getScreenSize());
        TblEntradas.setFillsViewportHeight(true);
        TblEntradas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        defaultTableModel.setColumnIdentifiers(new String[] {"#", "Num.Pedido", "Código de barras", "Diseño", "Producto", "Folio", "Cantidad por pallet"});
        ScrTabla = new JScrollPane(TblEntradas, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        ScrTabla.setBounds(50, 370, 1160, 300);
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
        columnModel.getColumn(6).setPreferredWidth(221);

        BtnRegistrarSalida = new JButton();
        BtnRegistrarSalida.setName("BtnRegistrarSalida");
        BtnRegistrarSalida.setText("Registrar Salida");
        BtnRegistrarSalida.setBounds(530, 710, 200, 45);
        BtnRegistrarSalida.setFont(font);
        BtnRegistrarSalida.setBackground(Color.white);
        BtnRegistrarSalida.addKeyListener(this);
        BtnRegistrarSalida.addActionListener(this);
        BtnRegistrarSalida.setCursor(new Cursor(HAND_CURSOR));
        BtnRegistrarSalida.setFocusPainted(false);
        BtnRegistrarSalida.setVisible(true);
        BtnRegistrarSalida.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        this.add(BtnRegistrarSalida);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == BtnRegistrarSalida)
        {
            if(TxtCantidadPallet.getText().isEmpty() || TxtCantidadPorPallet.getText().isEmpty() || TxtTotalUnidades.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Falta llenar campos", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                salidas.setNumPedido(CmbNumPedido.getSelectedItem().toString());
                salidas.setSellos(TxtSellos.getText());
                salidas.setCantidadPallet(Integer.parseInt(TxtCantidadPallet.getText()));
                salidas.setCantidadPorPallet(Integer.parseInt(TxtCantidadPorPallet.getText()));
                salidas.setTotalUnidades(Integer.parseInt(TxtTotalUnidades.getText()));
                salidas.setFechaEntrega(TxtEntregaDelDia.getText());
                salidas.setChofer(TxtChofer.getText());
                salidas.setEmpresa(TxtEmpresa.getText());
                salidas.setPlacas(TxtPlacas.getText());
                salidas.setTractoCamion(TxtTractoCamion.getText());

                salidas.insertarSalidas();

                vaciarTextos();
                crearPDF();
                JOptionPane.showMessageDialog(this, "Salida registrada", "Registro de salidas", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        if(TxtSellos.isFocusOwner())
        {
            if (!Character.isLetter(e.getKeyChar()) && !(e.getKeyChar() == KeyEvent.VK_SPACE) && !(e.getKeyChar() == KeyEvent.VK_BACK_SPACE))
            {
                e.consume();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            BtnRegistrarSalida.doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent e){}

    @Override
    public void itemStateChanged(ItemEvent e)
    {
        if(e.getStateChange() == ItemEvent.SELECTED)
        {
            salidas.setNumPedido(CmbNumPedido.getSelectedItem().toString());
            salidas.busquedaNumPedido(this);
        }
    }

    private void vaciarTextos()
    {
        CmbNumPedido.setSelectedIndex(0);
        TxtCantidadPallet.setText("");
        TxtCantidadPorPallet.setText("");
        TxtTotalUnidades.setText("");
        TxtChofer.setText("");
        TxtEmpresa.setText("");
        TxtPlacas.setText("");
        TxtTractoCamion.setText("");
        TxtSellos.setText("");
        TxtEntregaDelDia.setText("");
    }

    private void crearPDF()
    {
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
}
