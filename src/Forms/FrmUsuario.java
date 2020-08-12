package Forms;

import Tools.DataBase.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

public class FrmUsuario extends JFrame implements ActionListener, KeyListener {
    JLabel LblNombre, LblApellidoPaterno, LblApellidoMaterno, LblPuesto, LblCorreoElectronico, LblContrasena, LblContrasena2;
    JTextField TxtNombre, TxtApellidoPaterno, TxtApellidoMaterno, TxtPuesto, TxtCorreoElectronico;
    JPasswordField TxtContrasena, TxtContrasena2;
    JButton BtnRegistrarUsuario, BtnOcultarMostrar;

    Font font;

    ImageIcon ImgOcultarMostrar;
    Icon IcnOcultarMostrar;

    Boolean MostrarOcultar = true;

    Usuario usuario = null;

    public FrmUsuario() throws SQLException {

        usuario = new Usuario();

        this.setSize(1080, 400);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setTitle("Registro de usuarios");
        this.getContentPane().setBackground(Color.WHITE);
        setIconImage(new ImageIcon(getClass().getResource("Imagenes/IconoTprlogistics.png")).getImage());

        font = new Font("HELVETICA", Font.PLAIN, 25);

        LblNombre = new JLabel();
        LblNombre.setText("Nombre(s)");
        LblNombre.setName("LblNombre");
        LblNombre.setBounds(50, 50, 120, 40);
        LblNombre.setBackground(Color.WHITE);
        LblNombre.setFont(font);
        LblNombre.setVisible(true);
        this.add(LblNombre);

        TxtNombre = new JTextField();
        TxtNombre.setName("TxtNombre");
        TxtNombre.setBounds(50, 95, 200, 40);
        TxtNombre.setText("");
        TxtNombre.setFont(font);
        TxtNombre.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtNombre.addKeyListener(this);
        TxtNombre.setVisible(true);
        this.add(TxtNombre);

        LblApellidoPaterno = new JLabel();
        LblApellidoPaterno.setText("Apellido Paterno");
        LblApellidoPaterno.setName("LblApellidoPaterno");
        LblApellidoPaterno.setBounds(300, 50, 190, 40);
        LblApellidoPaterno.setBackground(Color.WHITE);
        LblApellidoPaterno.setFont(font);
        LblApellidoPaterno.setVisible(true);
        this.add(LblApellidoPaterno);

        TxtApellidoPaterno = new JTextField();
        TxtApellidoPaterno.setName("TxtApellidoPaterno");
        TxtApellidoPaterno.setBounds(300, 95, 200, 40);
        TxtApellidoPaterno.setText("");
        TxtApellidoPaterno.setFont(font);
        TxtApellidoPaterno.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtApellidoPaterno.addKeyListener(this);
        TxtApellidoPaterno.setVisible(true);
        this.add(TxtApellidoPaterno);

        LblApellidoMaterno = new JLabel();
        LblApellidoMaterno.setText("Apellido Materno");
        LblApellidoMaterno.setName("LblApellidoMaterno");
        LblApellidoMaterno.setBounds(550, 50, 190, 40);
        LblApellidoMaterno.setBackground(Color.WHITE);
        LblApellidoMaterno.setFont(font);
        LblApellidoMaterno.setVisible(true);
        this.add(LblApellidoMaterno);

        TxtApellidoMaterno = new JTextField();
        TxtApellidoMaterno.setName("TxtApellidoMaterno");
        TxtApellidoMaterno.setBounds(550, 95, 200, 40);
        TxtApellidoMaterno.setText("");
        TxtApellidoMaterno.setFont(font);
        TxtApellidoMaterno.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtApellidoMaterno.addKeyListener(this);
        TxtApellidoMaterno.setVisible(true);
        this.add(TxtApellidoMaterno);

        LblPuesto = new JLabel();
        LblPuesto.setText("Puesto");
        LblPuesto.setName("LblPuesto");
        LblPuesto.setBounds(800, 50, 80, 40);
        LblPuesto.setBackground(Color.WHITE);
        LblPuesto.setFont(font);
        LblPuesto.setVisible(true);
        this.add(LblPuesto);

        TxtPuesto = new JTextField();
        TxtPuesto.setName("TxtPuesto");
        TxtPuesto.setBounds(800, 95, 200, 40);
        TxtPuesto.setText("");
        TxtPuesto.setFont(font);
        TxtPuesto.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtPuesto.addKeyListener(this);
        TxtPuesto.setVisible(true);
        this.add(TxtPuesto);

        LblCorreoElectronico = new JLabel();
        LblCorreoElectronico.setText("Correo Electrónico");
        LblCorreoElectronico.setName("LblCorreoElectronico");
        LblCorreoElectronico.setBounds(50, 150, 210, 40);
        LblCorreoElectronico.setBackground(Color.WHITE);
        LblCorreoElectronico.setFont(font);
        LblCorreoElectronico.setVisible(true);
        this.add(LblCorreoElectronico);

        TxtCorreoElectronico = new JTextField();
        TxtCorreoElectronico.setName("TxtCorreoElectronico");
        TxtCorreoElectronico.setBounds(50, 195, 330, 40);
        TxtCorreoElectronico.setText("");
        TxtCorreoElectronico.setFont(font);
        TxtCorreoElectronico.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtCorreoElectronico.addKeyListener(this);
        TxtCorreoElectronico.setVisible(true);
        this.add(TxtCorreoElectronico);

        LblContrasena = new JLabel();
        LblContrasena.setText("Contraseña");
        LblContrasena.setName("LblContrasena");
        LblContrasena.setBounds(430, 150, 130, 40);
        LblContrasena.setBackground(Color.WHITE);
        LblContrasena.setFont(font);
        LblContrasena.setVisible(true);
        this.add(LblContrasena);

        TxtContrasena = new JPasswordField();
        TxtContrasena.setName("TxtContrasena");
        TxtContrasena.setBounds(430, 195, 240, 40);
        TxtContrasena.setText("");
        TxtContrasena.setFont(font);
        TxtContrasena.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtContrasena.addKeyListener(this);
        TxtContrasena.setVisible(true);
        this.add(TxtContrasena);

        LblContrasena2 = new JLabel();
        LblContrasena2.setText("Repetir Contraseña");
        LblContrasena2.setName("LblContrasena2");
        LblContrasena2.setBounds(720, 150, 220, 40);
        LblContrasena2.setBackground(Color.WHITE);
        LblContrasena2.setFont(font);
        LblContrasena2.setVisible(true);
        this.add(LblContrasena2);

        TxtContrasena2 = new JPasswordField();
        TxtContrasena2.setName("TxtContrasena2");
        TxtContrasena2.setBounds(720, 195, 240, 40);
        TxtContrasena2.setText("");
        TxtContrasena2.setFont(font);
        TxtContrasena2.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        TxtContrasena2.addKeyListener(this);
        TxtContrasena2.setVisible(true);
        this.add(TxtContrasena2);

        BtnOcultarMostrar = new JButton();
        BtnOcultarMostrar.setName("BtnOcultarMostrar");
        CambiarImagenBoton("Imagenes/Mostrar.png");
        BtnOcultarMostrar.setBounds(962, 195, 40, 40);
        BtnOcultarMostrar.setFont(font);
        BtnOcultarMostrar.setBackground(Color.white);
        BtnOcultarMostrar.addActionListener(this);
        BtnOcultarMostrar.setCursor(new Cursor(HAND_CURSOR));
        BtnOcultarMostrar.setFocusPainted(false);
        BtnOcultarMostrar.setVisible(true);
        BtnOcultarMostrar.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        this.add(BtnOcultarMostrar);

        BtnRegistrarUsuario = new JButton();
        BtnRegistrarUsuario.setName("BtnRegistrarUsuario");
        BtnRegistrarUsuario.setText("Registrar Tools.DataBase.Usuario");
        BtnRegistrarUsuario.setBounds(433, 275, 200, 45);
        BtnRegistrarUsuario.setFont(font);
        BtnRegistrarUsuario.setBackground(Color.white);
        BtnRegistrarUsuario.addKeyListener(this);
        BtnRegistrarUsuario.addActionListener(this);
        BtnRegistrarUsuario.setCursor(new Cursor(HAND_CURSOR));
        BtnRegistrarUsuario.setFocusPainted(false);
        BtnRegistrarUsuario.setVisible(true);
        BtnRegistrarUsuario.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        this.add(BtnRegistrarUsuario);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == BtnOcultarMostrar) {
            MostrarContrasena(MostrarOcultar);
        } else if (e.getSource() == BtnRegistrarUsuario) {
            if (TxtNombre.getText().isEmpty() || TxtApellidoPaterno.getText().isEmpty() || TxtApellidoMaterno.getText().isEmpty() || TxtPuesto.getText().isEmpty() || TxtCorreoElectronico.getText().isEmpty() || TxtContrasena.getText().isEmpty() || TxtContrasena2.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Falta llenar campos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if (TxtContrasena.getText().equals(TxtContrasena2.getText())) {
                    usuario.setNombre(TxtNombre.getText());
                    usuario.setApellidoPaterno(TxtApellidoPaterno.getText());
                    usuario.setApellidoMaterno(TxtApellidoMaterno.getText());
                    usuario.setPuesto(TxtPuesto.getText());
                    usuario.setCorreoElectronico(TxtCorreoElectronico.getText());
                    usuario.setContrasena(TxtContrasena.getText());
                    usuario.insertar();//REGRESA UN -1 EN CASO DE ERROR
                    //usuario.actualizar();//RETORNA UN -1 EN CASO DE ERROR
                    //usuario.eliminarUsuario(); //REGRESA UN -1 EN CASO DE ERROR
                    //usuario = Conexion.seleccionarIdUsuario(usuario); //OJO QUE SI NO ENCUENTRA EL USUARIO RETORNA UN NULL, SOLOVALIDA CON UN IF
                    vaciarTextos();
                    JOptionPane.showMessageDialog(this, "Tools.DataBase.Usuario registrado", "Registro de usuario", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Contraseñas diferentes", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (TxtNombre.isFocusOwner() || TxtApellidoPaterno.isFocusOwner() || TxtApellidoMaterno.isFocusOwner() || TxtPuesto.isFocusOwner()) {
            if (!Character.isLetter(e.getKeyChar()) && !(e.getKeyChar() == KeyEvent.VK_SPACE) && !(e.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
                e.consume();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnRegistrarUsuario.doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void MostrarContrasena(Boolean mostrarOcultar) {
        if (mostrarOcultar) {
            CambiarImagenBoton("Imagenes/Ocultar.png");
            TxtContrasena.setEchoChar((char) 0);
            TxtContrasena2.setEchoChar((char) 0);
            MostrarOcultar = false;
        } else {
            CambiarImagenBoton("Imagenes/Mostrar.png");
            TxtContrasena.setEchoChar('•');
            TxtContrasena2.setEchoChar('•');
            MostrarOcultar = true;
        }
    }

    private void CambiarImagenBoton(String RutaImagen) {
        ImgOcultarMostrar = new ImageIcon(this.getClass().getResource(RutaImagen));
        IcnOcultarMostrar = new ImageIcon(ImgOcultarMostrar.getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));
        BtnOcultarMostrar.setIcon(IcnOcultarMostrar);
    }

    private void vaciarTextos() {
        TxtNombre.setText("");
        TxtApellidoPaterno.setText("");
        TxtApellidoMaterno.setText("");
        TxtPuesto.setText("");
        TxtCorreoElectronico.setText("");
        TxtContrasena.setText("");
        TxtContrasena2.setText("");
    }
}
