package Forms;

import Forms.Login.Login;
import Tools.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class FrmPrincipal extends JFrame implements ActionListener, KeyListener
{
    JMenuBar MnuBar;

    JMenu    MnuInvenario,
             MnuEntradas,
             MnuSalidas,
             MnuUsuario,
             MnuAcercaDe;

    JMenuItem   MnuItemAgregarInventario,
                MnuItemAgregarEntradas,
                MnuItemAgregarSalidas,
                MnuItemAgregarUsuario,
                MnuItemAcercaDe;

    Font fontMenu;

    private String user;

    static FrmPrincipal instance;
    static Login comeFrominstance;


    public FrmPrincipal(String Usuario, Login from)
    {
        instance = this;
        comeFrominstance = from;
        this.setUser(Usuario);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.WHITE);
        setIconImage(new ImageIcon("./Data/Imagenes/IconoTprlogistics.png").getImage());
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                int choose =JOptionPane.showConfirmDialog(null,
                        "De verdad quiere cerrar su sesi\u00f3n?", "Cerrar sesi\u00f3n?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if (choose == JOptionPane.YES_OPTION)
                {
                    instance.setVisible(false);
                    from.cerrarSesion();
                    from.setVisible(true);
                }
            }
        });
        fontMenu = new Font("HELVETICA", Font.PLAIN, 15);

        MnuBar = new JMenuBar();
        MnuBar.setName("MnuBar");
        MnuBar.setBackground(Color.WHITE);
        MnuBar.setVisible(true);
        this.setJMenuBar(MnuBar);

        MnuInvenario = new JMenu();
        MnuInvenario.setName("MnuInvenario");
        MnuInvenario.setText("Tools.DataBase.Inventario");
        //MnuInvenario.setIcon(new ImageIcon(getClass().getResource("Data.Imagenes/Mostrar.png")));
        MnuInvenario.setVisible(true);
        MnuInvenario.setFont(fontMenu);
        MnuBar.add(MnuInvenario);

        MnuEntradas = new JMenu();
        MnuEntradas.setName("MnuEntradas");
        MnuEntradas.setText("Tools.DataBase.Entradas");
        //MnuEntradas.setIcon(new ImageIcon(getClass().getResource("Data.Imagenes/Mostrar.png")));
        MnuEntradas.setVisible(true);
        MnuEntradas.setFont(fontMenu);
        MnuBar.add(MnuEntradas);

        MnuSalidas = new JMenu();
        MnuSalidas.setName("MnuSalidas");
        MnuSalidas.setText("Tools.DataBase.Salidas");
        //MnuSalidas.setIcon(new ImageIcon(getClass().getResource("Data.Imagenes/Mostrar.png")));
        MnuSalidas.setVisible(true);
        MnuSalidas.setFont(fontMenu);
        MnuBar.add(MnuSalidas);

        MnuUsuario = new JMenu();
        MnuUsuario.setName("MnuUsuario");
        MnuUsuario.setText("Tools.DataBase.Usuario");
        //MnuUsuario.setIcon(new ImageIcon(getClass().getResource("Data.Imagenes/Mostrar.png")));
        MnuUsuario.setVisible(true);
        MnuUsuario.setFont(fontMenu);
        MnuBar.add(MnuUsuario);

        MnuAcercaDe = new JMenu();
        MnuAcercaDe.setName("MnuAcercaDe");
        MnuAcercaDe.setText("Acerca De");
        //MnuAcercaDe.setIcon(new ImageIcon(getClass().getResource("Data.Imagenes/Mostrar.png")));
        MnuAcercaDe.setVisible(true);
        MnuAcercaDe.setFont(fontMenu);
        MnuBar.add(MnuAcercaDe);

        MnuItemAgregarInventario = new JMenuItem();
        MnuItemAgregarInventario.setName("MnuItemAgregarInventario");
        MnuItemAgregarInventario.setText("Agregar inventario");
        //MnuItemAgregarInventario.setIcon(new ImageIcon(getClass().getResource("Data.Imagenes/Mostrar.png")));
        MnuItemAgregarInventario.setBackground(Color.WHITE);
        MnuItemAgregarInventario.setVisible(true);
        MnuItemAgregarInventario.setFont(fontMenu);
        MnuItemAgregarInventario.addActionListener(this);
        MnuInvenario.add(MnuItemAgregarInventario);

        MnuItemAgregarEntradas = new JMenuItem();
        MnuItemAgregarEntradas.setName("MnuItemAgregarEntradas");
        MnuItemAgregarEntradas.setText("Agregar entradas");
        //MnuItemAgregarEntradas.setIcon(new ImageIcon(getClass().getResource("Data.Imagenes/Mostrar.png")));
        MnuItemAgregarEntradas.setBackground(Color.WHITE);
        MnuItemAgregarEntradas.setVisible(true);
        MnuItemAgregarEntradas.setFont(fontMenu);
        MnuItemAgregarEntradas.addActionListener(this);
        MnuEntradas.add(MnuItemAgregarEntradas);

        MnuItemAgregarSalidas = new JMenuItem();
        MnuItemAgregarSalidas.setName("MnuItemAgregarSalidas");
        MnuItemAgregarSalidas.setText("Agregar salidas");
        //MnuItemAgregarSalidas.setIcon(new ImageIcon(getClass().getResource("Data.Imagenes/Mostrar.png")));
        MnuItemAgregarSalidas.setBackground(Color.WHITE);
        MnuItemAgregarSalidas.setVisible(true);
        MnuItemAgregarSalidas.setFont(fontMenu);
        MnuItemAgregarSalidas.addActionListener(this);
        MnuSalidas.add(MnuItemAgregarSalidas);

        MnuItemAgregarUsuario = new JMenuItem();
        MnuItemAgregarUsuario.setName("MnuItemAgregarUsuario");
        MnuItemAgregarUsuario.setText("Agregar usuario");
        //MnuItemAgregarUsuario.setIcon(new ImageIcon(getClass().getResource("Data.Imagenes/Mostrar.png")));
        MnuItemAgregarUsuario.setBackground(Color.WHITE);
        MnuItemAgregarUsuario.setVisible(true);
        MnuItemAgregarUsuario.setFont(fontMenu);
        MnuItemAgregarUsuario.addActionListener(this);
        MnuUsuario.add(MnuItemAgregarUsuario);

        MnuItemAcercaDe = new JMenuItem();
        MnuItemAcercaDe.setName("MnuItemAcercaDe");
        MnuItemAcercaDe.setText("Desarrollador");
        //MnuItemAcercaDe.setIcon(new ImageIcon(getClass().getResource("Data.Imagenes/Mostrar.png")));
        MnuItemAcercaDe.setBackground(Color.WHITE);
        MnuItemAcercaDe.setVisible(true);
        MnuItemAcercaDe.setFont(fontMenu);
        MnuItemAcercaDe.addActionListener(this);
        MnuAcercaDe.add(MnuItemAcercaDe);
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        if(!user.isEmpty()){
            this.user = user;
            this.setTitle("Control de inventario - TPRLogistics - Bienvenido: " + this.user);
            return;
        }
        this.setTitle("");
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == MnuItemAgregarInventario)
        {
            FrmInventario frmInventario = null;
            try {
                frmInventario = new FrmInventario();
                frmInventario.show();
            } catch (SQLException throwables) {
                showDialogError("Ocurrio un error al conectar con la base de datos, verifique el error y vuelva a intentarlo.",
                        "MY_SQL_ERROR_CONNECTION");
                System.exit(1);
            } catch (Config.EmptyProperty emptyProperty) {
                showDialogError(emptyProperty.getMessage(),
                        "ERR_PROPERTY");
                System.exit(1);
            } catch (Config.ReadException readException) {
                showDialogError(readException.getMessage(),
                        "ERR_PROPERTY");
                System.exit(1);
            }

        }
        else if(e.getSource() == MnuItemAgregarEntradas)
        {
            FrmEntradas frmEntradas = null;
            try {
                frmEntradas = new FrmEntradas();
                frmEntradas.show();
            } catch (SQLException throwables) {
                showDialogError("Ocurrio un error al conectar con la base de datos, verifique el error y vuelva a intentarlo.",
                        "MY_SQL_ERROR_CONNECTION");
                System.exit(1);
            } catch (Config.EmptyProperty emptyProperty) {
                showDialogError(emptyProperty.getMessage(),
                        "ERR_PROPERTY");
                System.exit(1);
            } catch (Config.ReadException readException) {
                showDialogError(readException.getMessage(),
                        "ERR_PROPERTY");
                System.exit(1);
            }

        }
        else if(e.getSource() == MnuItemAgregarSalidas)
        {
            FrmSalidas frmSalidas = null;
            try {
                frmSalidas = new FrmSalidas();
                frmSalidas.show();
            } catch (SQLException throwables) {
                showDialogError("Ocurrio un error al conectar con la base de datos, verifique el error y vuelva a intentarlo.",
                        "MY_SQL_ERROR_CONNECTION");
                System.exit(1);
            }catch (Config.EmptyProperty emptyProperty) {
                showDialogError(emptyProperty.getMessage(),
                        "ERR_PROPERTY");
                System.exit(1);
            } catch (Config.ReadException readException) {
                showDialogError(readException.getMessage(),
                        "ERR_PROPERTY");
                System.exit(1);
            }

        }
        else if(e.getSource() == MnuItemAgregarUsuario)
        {
            FrmUsuario frmUsuario = null;
            try {
                frmUsuario = new FrmUsuario();
                frmUsuario.show();
            } catch (SQLException throwables) {
                showDialogError("Ocurrio un error al conectar con la base de datos, verifique el error y vuelva a intentarlo.",
                        "MY_SQL_ERROR_CONNECTION");
                System.exit(1);
            }catch (Config.EmptyProperty emptyProperty) {
                showDialogError(emptyProperty.getMessage(),
                        "ERR_PROPERTY");
                System.exit(1);
            } catch (Config.ReadException readException) {
                showDialogError(readException.getMessage(),
                        "ERR_PROPERTY");
                System.exit(1);
            }

        }
        else if(e.getSource() == MnuItemAcercaDe)
        {

        }
    }

    private void showDialogError(String err, String errTitle)
    {
        JOptionPane.showMessageDialog(this,
                err,
                errTitle,
                JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {

    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }
}
