package Forms;

import Tools.DataBase.Transporte;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class FrmTransporte extends JFrame implements ActionListener, KeyListener
{
    JLabel LblChofer, LblEmpresa, LblPlacas, LblTractoCamion;
    JTextField TxtChofer, TxtEmpresa, TxtPlacas, TxtTractoCamion;
    JButton BtnTransporte;
    
    Font font;

    Transporte chofer = null;

    public FrmTransporte() throws SQLException {
        chofer = new Transporte();

        this.setSize(1040, 500);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setTitle("Formulario de transporte");
        this.getContentPane().setBackground(Color.WHITE);
        setIconImage(new ImageIcon(getClass().getResource("Forms/Imagenes/IconoTprlogistics.png")).getImage());

        font = new Font("HELVETICA", Font.PLAIN, 25);

        LblChofer = new JLabel();
        LblChofer.setText("Chofer");
        LblChofer.setName("LblChofer");
        LblChofer.setBounds(50, 50, 200, 40);
        LblChofer.setBackground(Color.WHITE);
        LblChofer.setFont(font);
        LblChofer.setVisible(true);
        this.add(LblChofer);

        TxtChofer = new JTextField();
        TxtChofer.setName("TxtChofer");
        TxtChofer.setBounds(50, 90, 920, 40);
        TxtChofer.setText("");
        TxtChofer.setFont(font);
        TxtChofer.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtChofer.addKeyListener(this);
        TxtChofer.setVisible(true);
        this.add(TxtChofer);

        LblEmpresa = new JLabel();
        LblEmpresa.setText("Empresa");
        LblEmpresa.setName("LblEmpresa");
        LblEmpresa.setBounds(50, 150, 200, 40);
        LblEmpresa.setBackground(Color.WHITE);
        LblEmpresa.setFont(font);
        LblEmpresa.setVisible(true);
        this.add(LblEmpresa);

        TxtEmpresa = new JTextField();
        TxtEmpresa.setName("TxtEmpresa");
        TxtEmpresa.setBounds(50, 190, 920, 40);
        TxtEmpresa.setText("");
        TxtEmpresa.setFont(font);
        TxtEmpresa.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtEmpresa.addKeyListener(this);
        TxtEmpresa.setVisible(true);
        this.add(TxtEmpresa);

        LblPlacas = new JLabel();
        LblPlacas.setText("Placas");
        LblPlacas.setName("LblPlacas");
        LblPlacas.setBounds(50, 250, 200, 40);
        LblPlacas.setBackground(Color.WHITE);
        LblPlacas.setFont(font);
        LblPlacas.setVisible(true);
        this.add(LblPlacas);

        TxtPlacas = new JTextField();
        TxtPlacas.setName("TxtPlacas");
        TxtPlacas.setBounds(50, 290, 450, 40);
        TxtPlacas.setText("");
        TxtPlacas.setFont(font);
        TxtPlacas.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtPlacas.addKeyListener(this);
        TxtPlacas.setVisible(true);
        this.add(TxtPlacas);

        LblTractoCamion = new JLabel();
        LblTractoCamion.setText("Tracto Camion");
        LblTractoCamion.setName("LblTractoCamion");
        LblTractoCamion.setBounds(520, 250, 200, 40);
        LblTractoCamion.setBackground(Color.WHITE);
        LblTractoCamion.setFont(font);
        LblTractoCamion.setVisible(true);
        this.add(LblTractoCamion);

        TxtTractoCamion = new JTextField();
        TxtTractoCamion.setName("TxtTractoCamion");
        TxtTractoCamion.setBounds(520, 290, 450, 40);
        TxtTractoCamion.setText("");
        TxtTractoCamion.setFont(font);
        TxtTractoCamion.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtTractoCamion.addKeyListener(this);
        TxtTractoCamion.setVisible(true);
        this.add(TxtTractoCamion);

        BtnTransporte = new JButton();
        BtnTransporte.setName("BtnTransporte");
        BtnTransporte.setText("Registrar Tools.DataBase.Transporte");
        BtnTransporte.setBounds(388, 370, 250, 45);
        BtnTransporte.setFont(font);
        BtnTransporte.setBackground(Color.white);
        BtnTransporte.addKeyListener(this);
        BtnTransporte.addActionListener(this);
        BtnTransporte.setCursor(new Cursor(HAND_CURSOR));
        BtnTransporte.setFocusPainted(false);
        BtnTransporte.setVisible(true);
        BtnTransporte.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        this.add(BtnTransporte);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == BtnTransporte)
        {
            if(TxtChofer.getText().isEmpty() || TxtEmpresa.getText().isEmpty() || TxtPlacas.getText().isEmpty() || TxtTractoCamion.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Falta llenar campos", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                chofer.setChofer(TxtChofer.getText());
                chofer.setEmpresa(TxtEmpresa.getText());
                chofer.setPlacas(TxtPlacas.getText());
                chofer.setTractoCamion(TxtTractoCamion.getText());

                chofer.insertar();

                vaciarTextos();
                JOptionPane.showMessageDialog(this, "Tools.DataBase.Transporte registradas", "Registro de Transportes", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        if(TxtChofer.isFocusOwner())
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

    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }

    private void vaciarTextos()
    {
        TxtChofer.setText("");
        TxtEmpresa.setText("");
        TxtPlacas.setText("");
        TxtTractoCamion.setText("");
    }
}
