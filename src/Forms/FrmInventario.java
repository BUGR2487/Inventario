package Forms;

import Tools.Config;
import Tools.DataBase.Inventario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class FrmInventario extends JFrame implements ActionListener, KeyListener, FocusListener
{
    JLabel LblCodigoBarras, LblDiseno, LblCodigoInterno, LblCliente, LblProducto, LblCantidadPorPallet;
    JTextField TxtCodigoBarras, TxtDiseno, TxtCodigoInterno, TxtCliente, TxtProducto, TxtCantidadPorPallet;
    JButton BtnRegistrarInventario;
    
    Font font;

    Inventario inventario = null;

    public FrmInventario() throws SQLException, Config.ReadException, Config.EmptyProperty {

        inventario = new Inventario();

        this.setSize(1275, 380);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setTitle("Tools.DataBase.Inventario");
        this.getContentPane().setBackground(Color.WHITE);
        setIconImage(new ImageIcon(getClass().getResource("Imagenes/IconoTprlogistics.png")).getImage());

        font = new Font("HELVETICA", Font.PLAIN, 25);

        LblCodigoBarras = new JLabel();
        LblCodigoBarras.setText("Código de barras");
        LblCodigoBarras.setName("LblCodigoBarras");
        LblCodigoBarras.setBounds(50, 50, 200, 40);
        LblCodigoBarras.setBackground(Color.WHITE);
        LblCodigoBarras.setFont(font);
        LblCodigoBarras.setVisible(true);
        this.add(LblCodigoBarras);

        TxtCodigoBarras = new JTextField();
        TxtCodigoBarras.setName("TxtCodigoBarras");
        TxtCodigoBarras.setBounds(50, 90, 200, 40);
        TxtCodigoBarras.setText("");
        TxtCodigoBarras.setFont(font);
        TxtCodigoBarras.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtCodigoBarras.addKeyListener(this);
        TxtCodigoBarras.setVisible(true);
        this.add(TxtCodigoBarras);

        LblDiseno = new JLabel();
        LblDiseno.setText("Diseño");
        LblDiseno.setName("LblDiseno");
        LblDiseno.setBounds(290, 50, 200, 40);
        LblDiseno.setBackground(Color.WHITE);
        LblDiseno.setFont(font);
        LblDiseno.setVisible(true);
        this.add(LblDiseno);

        TxtDiseno = new JTextField();
        TxtDiseno.setName("TxtDiseno");
        TxtDiseno.setBounds(290, 90, 200, 40);
        TxtDiseno.setText("");
        TxtDiseno.setFont(font);
        TxtDiseno.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtDiseno.addKeyListener(this);
        TxtDiseno.setVisible(true);
        this.add(TxtDiseno);

        LblCodigoInterno = new JLabel();
        LblCodigoInterno.setText("Código interno");
        LblCodigoInterno.setName("LblCodigoInterno");
        LblCodigoInterno.setBounds(530, 50, 200, 40);
        LblCodigoInterno.setBackground(Color.WHITE);
        LblCodigoInterno.setFont(font);
        LblCodigoInterno.setVisible(true);
        this.add(LblCodigoInterno);

        TxtCodigoInterno = new JTextField();
        TxtCodigoInterno.setName("TxtCodigoInterno");
        TxtCodigoInterno.setBounds(530, 90, 200, 40);
        TxtCodigoInterno.setText("");
        TxtCodigoInterno.setFont(font);
        TxtCodigoInterno.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtCodigoInterno.addKeyListener(this);
        TxtCodigoInterno.setVisible(true);
        this.add(TxtCodigoInterno);

        LblCliente = new JLabel();
        LblCliente.setText("Cliente");
        LblCliente.setName("LblCliente");
        LblCliente.setBounds(770, 50, 200, 40);
        LblCliente.setBackground(Color.WHITE);
        LblCliente.setFont(font);
        LblCliente.setVisible(true);
        this.add(LblCliente);

        TxtCliente = new JTextField();
        TxtCliente.setName("TxtCliente");
        TxtCliente.setBounds(770, 90, 200, 40);
        TxtCliente.setText("");
        TxtCliente.setFont(font);
        TxtCliente.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtCliente.addKeyListener(this);
        TxtCliente.setVisible(true);
        this.add(TxtCliente);

        LblCantidadPorPallet = new JLabel();
        LblCantidadPorPallet.setText("Cantidad por pallet");
        LblCantidadPorPallet.setName("LblCantidadPorPallet");
        LblCantidadPorPallet.setBounds(1010, 50, 220, 40);
        LblCantidadPorPallet.setBackground(Color.WHITE);
        LblCantidadPorPallet.setFont(font);
        LblCantidadPorPallet.setVisible(true);
        this.add(LblCantidadPorPallet);

        TxtCantidadPorPallet = new JTextField();
        TxtCantidadPorPallet.setName("TxtCantidadPorPallet");
        TxtCantidadPorPallet.setBounds(1010, 90, 200, 40);
        TxtCantidadPorPallet.setText("");
        TxtCantidadPorPallet.setFont(font);
        TxtCantidadPorPallet.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtCantidadPorPallet.addKeyListener(this);
        TxtCantidadPorPallet.addFocusListener(this);
        TxtCantidadPorPallet.setVisible(true);
        this.add(TxtCantidadPorPallet);

        LblProducto = new JLabel();
        LblProducto.setText("Producto");
        LblProducto.setName("LblProducto");
        LblProducto.setBounds(50, 150, 200, 40);
        LblProducto.setBackground(Color.WHITE);
        LblProducto.setFont(font);
        LblProducto.setVisible(true);
        this.add(LblProducto);

        TxtProducto = new JTextField();
        TxtProducto.setName("TxtProducto");
        TxtProducto.setBounds(50, 190, 1160, 40);
        TxtProducto.setText("");
        TxtProducto.setFont(font);
        TxtProducto.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtProducto.addKeyListener(this);
        TxtProducto.setVisible(true);
        this.add(TxtProducto);

        BtnRegistrarInventario = new JButton();
        BtnRegistrarInventario.setName("BtnRegistrarInventario");
        BtnRegistrarInventario.setText("Registrar Entrada");
        BtnRegistrarInventario.setBounds(532, 260, 200, 45);
        BtnRegistrarInventario.setFont(font);
        BtnRegistrarInventario.setBackground(Color.white);
        BtnRegistrarInventario.addKeyListener(this);
        BtnRegistrarInventario.addActionListener(this);
        BtnRegistrarInventario.setCursor(new Cursor(HAND_CURSOR));
        BtnRegistrarInventario.setFocusPainted(false);
        BtnRegistrarInventario.setVisible(true);
        BtnRegistrarInventario.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        this.add(BtnRegistrarInventario);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == BtnRegistrarInventario)
        {
            if(TxtCodigoBarras.getText().isEmpty() || TxtDiseno.getText().isEmpty() || TxtCodigoInterno.getText().isEmpty() || TxtCliente.getText().isEmpty() || TxtProducto.getText().isEmpty() || TxtCantidadPorPallet.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Falta llenar campos", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                inventario.setCodigoBarras(TxtCodigoBarras.getText());
                inventario.setDiseno(Integer.parseInt(TxtDiseno.getText()));
                inventario.setCodigoInterno(TxtCodigoInterno.getText());
                inventario.setCliente(TxtCliente.getText());
                inventario.setProducto(TxtProducto.getText());
                inventario.setCantidadPorPallet(Integer.parseInt(TxtCantidadPorPallet.getText()));
                inventario.insertarInventario();
                vaciarTextos();
                JOptionPane.showMessageDialog(this, "Producto registrado", "Registro de Productos", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        if(TxtCliente.isFocusOwner())
        {
            if (!Character.isLetter(e.getKeyChar()) && !(e.getKeyChar() == KeyEvent.VK_SPACE) && !(e.getKeyChar() == KeyEvent.VK_BACK_SPACE))
            {
                e.consume();
            }
        }

        if(TxtDiseno.isFocusOwner() || TxtCodigoBarras.isFocusOwner() || TxtCantidadPorPallet.isFocusOwner())
        {
            if (Character.isLetter(e.getKeyChar()) && !(e.getKeyChar() == KeyEvent.VK_SPACE) && !(e.getKeyChar() == KeyEvent.VK_BACK_SPACE))
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
            BtnRegistrarInventario.doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        if(TxtCantidadPorPallet.isFocusOwner())
        {
            DecimalFormat df = new DecimalFormat("#,###");
            if (TxtCantidadPorPallet.getText().length() >= 1)
            {
                TxtCantidadPorPallet.setText(df.format(Integer.valueOf(TxtCantidadPorPallet.getText().replace(".", "").replace(",", ""))) );
            }
        }
    }

    @Override
    public void focusGained(FocusEvent e)
    {

    }

    @Override
    public void focusLost(FocusEvent e)
    {

    }

    private void vaciarTextos()
    {
        TxtCodigoBarras.setText("");
        TxtDiseno.setText("");
        TxtCodigoInterno.setText("");
        TxtCliente.setText("");
        TxtProducto.setText("");
        TxtCantidadPorPallet.setText("");
    }
}
