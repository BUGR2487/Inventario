package Forms;

import Tools.Config;
import Tools.DataBase.IniciarSesion;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class FrmIniciarSesion extends JFrame implements ActionListener, KeyListener
{
    JTextField TxtUsuario;
    JPasswordField TxtContrasena;
    JLabel LblImagenEmpresa, LblUsuario, LblContrasena;
    JButton BtnIniciarSesion, BtnOcultarMostrar;

    Font font;

    ImageIcon imageIcon, ImgOcultarMostrar;
    Icon icon, IcnOcultarMostrar;

    Boolean MostrarOcultar = true;

    IniciarSesion iniciarSesion = null;

    public FrmIniciarSesion() throws SQLException, Config.ReadException, Config.EmptyProperty {
        iniciarSesion = new IniciarSesion();

        this.setSize(500, 650);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setTitle("Iniciar Sesión");
        this.getContentPane().setBackground(Color.WHITE);
        setIconImage(new ImageIcon(getClass().getResource("Imagenes/IconoTprlogistics.png")).getImage());

        font = new Font("HELVETICA", Font.PLAIN, 25);

        imageIcon = new ImageIcon(this.getClass().getResource("Imagenes/LogoTprlogistics.png"));
        icon = new ImageIcon(imageIcon.getImage().getScaledInstance(440, 200,  java.awt.Image.SCALE_SMOOTH));

        LblImagenEmpresa = new JLabel();
        LblImagenEmpresa.setIcon(icon);
        LblImagenEmpresa.setName("LblImagenEmpresa");
        LblImagenEmpresa.setBounds(27,  25, 440, 200);
        LblImagenEmpresa.setBackground(Color.WHITE);
        LblImagenEmpresa.setVisible(true);
        this.add(LblImagenEmpresa);

        LblUsuario = new JLabel();
        LblUsuario.setText("Tools.DataBase.Usuario");
        LblUsuario.setName("LblUsuario");
        LblUsuario.setBounds(198, 250, 90, 40);
        LblUsuario.setBackground(Color.WHITE);
        LblUsuario.setFont(font);
        LblUsuario.setVisible(true);
        this.add(LblUsuario);

        TxtUsuario = new JTextField();
        TxtUsuario.setName("TxtUsuario");
        TxtUsuario.setBounds(43, 290, 400, 40);
        TxtUsuario.setText("");
        TxtUsuario.setFont(font);
        TxtUsuario.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtUsuario.addKeyListener(this);
        TxtUsuario.setVisible(true);
        this.add(TxtUsuario);

        LblContrasena = new JLabel();
        LblContrasena.setText("Contraseña");
        LblContrasena.setName("LblContrasena");
        LblContrasena.setBounds(178, 350, 130, 40);
        LblContrasena.setBackground(Color.WHITE);
        LblContrasena.setFont(font);
        LblContrasena.setVisible(true);
        this.add(LblContrasena);

        TxtContrasena = new JPasswordField();
        TxtContrasena.setName("TxtContrasena");
        TxtContrasena.setBounds(43, 390, 355, 40);
        TxtContrasena.setText("");
        TxtContrasena.setFont(font);
        TxtContrasena.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtContrasena.addKeyListener(this);
        TxtContrasena.setVisible(true);
        this.add(TxtContrasena);

        BtnOcultarMostrar = new JButton();
        BtnOcultarMostrar.setName("BtnOcultarMostrar");
        CambiarImagenBoton("Imagenes/Mostrar.png");
        BtnOcultarMostrar.setBounds(403, 390, 40, 40);
        BtnOcultarMostrar.setFont(font);
        BtnOcultarMostrar.setBackground(Color.white);
        BtnOcultarMostrar.addKeyListener(this);
        BtnOcultarMostrar.addActionListener(this);
        BtnOcultarMostrar.setCursor(new Cursor(HAND_CURSOR));
        BtnOcultarMostrar.setFocusPainted(false);
        BtnOcultarMostrar.setVisible(true);
        BtnOcultarMostrar.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        this.add(BtnOcultarMostrar);

        BtnIniciarSesion = new JButton();
        BtnIniciarSesion.setName("BtnIniciarSesion");
        BtnIniciarSesion.setText("Iniciar Sesión");
        BtnIniciarSesion.setBounds(163, 490, 160, 45);
        BtnIniciarSesion.setFont(font);
        BtnIniciarSesion.setBackground(Color.white);
        BtnIniciarSesion.addKeyListener(this);
        BtnIniciarSesion.addActionListener(this);
        BtnIniciarSesion.setCursor(new Cursor(HAND_CURSOR));
        BtnIniciarSesion.setFocusPainted(false);
        BtnIniciarSesion.setVisible(true);
        BtnIniciarSesion.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        this.add(BtnIniciarSesion);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == BtnOcultarMostrar)
        {
            MostrarContrasena(MostrarOcultar);
        }
        else if(e.getSource() == BtnIniciarSesion)
        {
            VerificarUsuario(TxtUsuario.getText(), TxtContrasena.getPassword());
        }
    }

    @Override
    public void keyTyped(KeyEvent e){}

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            BtnIniciarSesion.doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent e){}

    private void MostrarContrasena(Boolean mostrarOcultar)
    {
        if (mostrarOcultar)
        {
            CambiarImagenBoton("Imagenes/Ocultar.png");
            TxtContrasena.setEchoChar((char)0);
            MostrarOcultar = false;
        }
        else
        {
            CambiarImagenBoton("Imagenes/Mostrar.png");
            TxtContrasena.setEchoChar('•');
            MostrarOcultar = true;
        }
    }

    private void VerificarUsuario(String usuario, char[] contrasena)
    {
        iniciarSesion.setUsername(usuario);
        iniciarSesion.setPassword(String.valueOf(contrasena));

        if(iniciarSesion.login())
            dispose();
        else
        {
            JOptionPane.showMessageDialog(this, "Datos no validos...", "Tools.DataBase.Usuario Incorrecto", JOptionPane.ERROR_MESSAGE);
            TxtUsuario.requestFocus();
        }
    }

    private void CambiarImagenBoton(String RutaImagen)
    {
        ImgOcultarMostrar = new ImageIcon(this.getClass().getResource(RutaImagen));
        IcnOcultarMostrar = new ImageIcon(ImgOcultarMostrar.getImage().getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH));
        BtnOcultarMostrar.setIcon(IcnOcultarMostrar);
    }
}