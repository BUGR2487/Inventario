package Forms.Principal;

import Forms.Login.Login;
import Forms.Principal.Panels.*;
import Forms.Principal.Panels.Panel;
import Tools.Config;
import Tools.WindowAdapterImplement;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.sql.SQLException;

public class Principal extends JFrame{

    // -- VARIABLES Y CONSTANTES:

        // -- todas las vistas del usuario:
        private final PanelEntradas       entradas      = new PanelEntradas();
        private final PanelInventario     inventario    = new PanelInventario();
        private final PanelSalidas        salidas       = new PanelSalidas();
        private final PanelTransportes    transportes   = new PanelTransportes();
        private final PanelUsuarios       usuarios      = new PanelUsuarios();

        // -- iconos de pestaña:
        private final Icon entradas_icon        = IconFontSwing.buildIcon(FontAwesome.ANGLE_DOUBLE_DOWN	, 28, Color.red);
        private final Icon inventario_icon        = IconFontSwing.buildIcon(FontAwesome.STACK_OVERFLOW		, 28, Color.red);
        private final Icon salidas_icon        = IconFontSwing.buildIcon(FontAwesome.ANGLE_DOUBLE_UP		, 28, Color.red);
        private final Icon transporte_icon        = IconFontSwing.buildIcon(FontAwesome.BUS	, 28, Color.red);
        private final Icon usuario_icon        = IconFontSwing.buildIcon(FontAwesome.USERS	, 28, Color.red);

        //panel con pestañas:
        private final JTabbedPane tabbedPane;

        //variables de interfaz de uaurio
        private static Login        from;
        private static Principal    instance;
        private String userName = "";

     public Principal(String user, Login fromJFrame) throws Config.EmptyProperty, SQLException, Config.ReadException {
         this.setUserName(user);

         this.instance  = this;
         this.from      = fromJFrame;

         this.setBackground(Color.gray);
         IconFontSwing.register(FontAwesome.getIconFont());

         tabbedPane = new JTabbedPane();
         tabbedPane.setBackground(Color.gray);
         tabbedPane.addTab("Entradas.", entradas_icon , this.entradas.getview(), "Haz click aqui para registrar una entrada");
         tabbedPane.addTab("Inventario.", inventario_icon ,this.inventario.getview(), "Haz click aqui para registrar en el inventario.");
         tabbedPane.addTab("Salidas.", salidas_icon ,this.salidas.getview(), "Haz click aqui para registrar salidas del almacen.");
         tabbedPane.addTab("Transportes.", transporte_icon ,this.transportes, "Haz click aqui para administrar informacion del transporte.");
         tabbedPane.addTab("Usuarios.", usuario_icon ,this.usuarios, "Haz click aqui para administrar la informacion de los usuarios");

         tabbedPane.setFont(Panel.FUENTE_GENERAL_TXT);

         tabbedPane.addChangeListener(new ChangeListener() {
             @Override
             public void stateChanged(ChangeEvent e) {
                 JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
                 int selectedIndex = tabbedPane.getSelectedIndex();
                 switch (selectedIndex)
                 {
                     case 0:
                     {
                        entradas.cargaComboBoxs();
                        entradas.vaciarTextos();
                     }
                     break;
                     case 1:
                     {
                         inventario.vaciarTextos();
                     }
                     break;
                     case 2:
                     {
                         salidas.cargaComboBox();
                         salidas.vaciarTextos();
                     }
                     break;
                     case 3:
                     {
                         transportes.vaciarTextos();
                     }
                     break;
                     case 4:
                     {
                         usuarios.vaciarTextos();
                     }
                     break;
                 }
             }
         });

         this.add(tabbedPane);
         this.setVisible(true);
         this.setResizable(false);
         this.setLocationRelativeTo(null);
         this.getContentPane().setBackground(Color.WHITE);
         setIconImage(new ImageIcon("./Data/Imagenes/IconoTprlogistics.png").getImage());
         this.setExtendedState(JFrame.MAXIMIZED_BOTH);
         this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
         this.addWindowListener(new WindowAdapterImplement(fromJFrame, this));
     }

     // -- METODOS DE LA CLASE:



     // -- GET'S Y SET`S
    public PanelEntradas getEntradas() {
        return entradas;
    }

    public PanelInventario getInventario() {
        return inventario;
    }

    public PanelSalidas getSalidas() {
        return salidas;
    }

    public PanelTransportes getTransportes() {
        return transportes;
    }

    public PanelUsuarios getUsuarios() {
        return usuarios;
    }

    public Icon getEntradas_icon() {
        return entradas_icon;
    }

    public Icon getInventario_icon() {
        return inventario_icon;
    }

    public Icon getSalidas_icon() {
        return salidas_icon;
    }

    public Icon getTransporte_icon() {
        return transporte_icon;
    }

    public Icon getUsuario_icon() {
        return usuario_icon;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        this.setTitle("Control de inventario — Bienvenido " + this.userName);
    }


}
