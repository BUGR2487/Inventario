import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FrmPrincipal extends JFrame implements ActionListener, KeyListener
{
    JMenuBar MnuBar;
    JMenu MnuInvenario, MnuEntradas, MnuSalidas, MnuUsuario, MnuAcercaDe;
    JMenuItem MnuItemAgregarInventario, MnuItemAgregarEntradas, MnuItemAgregarSalidas, MnuItemAgregarUsuario, MnuItemAcercaDe;

    Font fontMenu;

    public FrmPrincipal(String Usuario)
    {
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setTitle("Control de inventario - TPRLogistics - Bienvenido: " + Usuario);
        this.getContentPane().setBackground(Color.WHITE);
        setIconImage(new ImageIcon(getClass().getResource("Imagenes/IconoTprlogistics.png")).getImage());
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        fontMenu = new Font("HELVETICA", Font.PLAIN, 15);

        MnuBar = new JMenuBar();
        MnuBar.setName("MnuBar");
        MnuBar.setBackground(Color.WHITE);
        MnuBar.setVisible(true);
        this.setJMenuBar(MnuBar);

        MnuInvenario = new JMenu();
        MnuInvenario.setName("MnuInvenario");
        MnuInvenario.setText("Inventario");
        //MnuInvenario.setIcon(new ImageIcon(getClass().getResource("Imagenes/Mostrar.png")));
        MnuInvenario.setVisible(true);
        MnuInvenario.setFont(fontMenu);
        MnuBar.add(MnuInvenario);

        MnuEntradas = new JMenu();
        MnuEntradas.setName("MnuEntradas");
        MnuEntradas.setText("Entradas");
        //MnuEntradas.setIcon(new ImageIcon(getClass().getResource("Imagenes/Mostrar.png")));
        MnuEntradas.setVisible(true);
        MnuEntradas.setFont(fontMenu);
        MnuBar.add(MnuEntradas);

        MnuSalidas = new JMenu();
        MnuSalidas.setName("MnuSalidas");
        MnuSalidas.setText("Salidas");
        //MnuSalidas.setIcon(new ImageIcon(getClass().getResource("Imagenes/Mostrar.png")));
        MnuSalidas.setVisible(true);
        MnuSalidas.setFont(fontMenu);
        MnuBar.add(MnuSalidas);

        MnuUsuario = new JMenu();
        MnuUsuario.setName("MnuUsuario");
        MnuUsuario.setText("Usuario");
        //MnuUsuario.setIcon(new ImageIcon(getClass().getResource("Imagenes/Mostrar.png")));
        MnuUsuario.setVisible(true);
        MnuUsuario.setFont(fontMenu);
        MnuBar.add(MnuUsuario);

        MnuAcercaDe = new JMenu();
        MnuAcercaDe.setName("MnuAcercaDe");
        MnuAcercaDe.setText("Acerca De");
        //MnuAcercaDe.setIcon(new ImageIcon(getClass().getResource("Imagenes/Mostrar.png")));
        MnuAcercaDe.setVisible(true);
        MnuAcercaDe.setFont(fontMenu);
        MnuBar.add(MnuAcercaDe);

        MnuItemAgregarInventario = new JMenuItem();
        MnuItemAgregarInventario.setName("MnuItemAgregarInventario");
        MnuItemAgregarInventario.setText("Agregar inventario");
        //MnuItemAgregarInventario.setIcon(new ImageIcon(getClass().getResource("Imagenes/Mostrar.png")));
        MnuItemAgregarInventario.setBackground(Color.WHITE);
        MnuItemAgregarInventario.setVisible(true);
        MnuItemAgregarInventario.setFont(fontMenu);
        MnuItemAgregarInventario.addActionListener(this);
        MnuInvenario.add(MnuItemAgregarInventario);

        MnuItemAgregarEntradas = new JMenuItem();
        MnuItemAgregarEntradas.setName("MnuItemAgregarEntradas");
        MnuItemAgregarEntradas.setText("Agregar entradas");
        //MnuItemAgregarEntradas.setIcon(new ImageIcon(getClass().getResource("Imagenes/Mostrar.png")));
        MnuItemAgregarEntradas.setBackground(Color.WHITE);
        MnuItemAgregarEntradas.setVisible(true);
        MnuItemAgregarEntradas.setFont(fontMenu);
        MnuItemAgregarEntradas.addActionListener(this);
        MnuEntradas.add(MnuItemAgregarEntradas);

        MnuItemAgregarSalidas = new JMenuItem();
        MnuItemAgregarSalidas.setName("MnuItemAgregarSalidas");
        MnuItemAgregarSalidas.setText("Agregar salidas");
        //MnuItemAgregarSalidas.setIcon(new ImageIcon(getClass().getResource("Imagenes/Mostrar.png")));
        MnuItemAgregarSalidas.setBackground(Color.WHITE);
        MnuItemAgregarSalidas.setVisible(true);
        MnuItemAgregarSalidas.setFont(fontMenu);
        MnuItemAgregarSalidas.addActionListener(this);
        MnuSalidas.add(MnuItemAgregarSalidas);

        MnuItemAgregarUsuario = new JMenuItem();
        MnuItemAgregarUsuario.setName("MnuItemAgregarUsuario");
        MnuItemAgregarUsuario.setText("Agregar usuario");
        //MnuItemAgregarUsuario.setIcon(new ImageIcon(getClass().getResource("Imagenes/Mostrar.png")));
        MnuItemAgregarUsuario.setBackground(Color.WHITE);
        MnuItemAgregarUsuario.setVisible(true);
        MnuItemAgregarUsuario.setFont(fontMenu);
        MnuItemAgregarUsuario.addActionListener(this);
        MnuUsuario.add(MnuItemAgregarUsuario);

        MnuItemAcercaDe = new JMenuItem();
        MnuItemAcercaDe.setName("MnuItemAcercaDe");
        MnuItemAcercaDe.setText("Desarrollador");
        //MnuItemAcercaDe.setIcon(new ImageIcon(getClass().getResource("Imagenes/Mostrar.png")));
        MnuItemAcercaDe.setBackground(Color.WHITE);
        MnuItemAcercaDe.setVisible(true);
        MnuItemAcercaDe.setFont(fontMenu);
        MnuItemAcercaDe.addActionListener(this);
        MnuAcercaDe.add(MnuItemAcercaDe);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == MnuItemAgregarInventario)
        {
            FrmInventario frmInventario = new FrmInventario();
            frmInventario.show();
        }
        else if(e.getSource() == MnuItemAgregarEntradas)
        {
            FrmEntradas frmEntradas = new FrmEntradas();
            frmEntradas.show();
        }
        else if(e.getSource() == MnuItemAgregarSalidas)
        {
            FrmSalidas frmSalidas = new FrmSalidas();
            frmSalidas.show();
        }
        else if(e.getSource() == MnuItemAgregarUsuario)
        {
            FrmUsuario frmUsuario = new FrmUsuario();
            frmUsuario.show();
        }
        else if(e.getSource() == MnuItemAcercaDe)
        {

        }
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