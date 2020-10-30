package Forms.Principal;

import Forms.Components.Fecha_y_hora;
import Forms.Components.Tabs;
import Forms.Login.Login;
import Forms.Panel;
import Forms.Principal.Entradas.PanelEntradas;
import Forms.Principal.Inventario.PanelInventario;
import Forms.Principal.Salidas.PanelSalidas;
import Forms.Principal.Transporte.PanelTransportes;
import Forms.Principal.Usuario.PanelUsuarios;
import Tools.Config;
import Tools.WindowAdapterImplement;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Principal extends JFrame{

    // -- VARIABLES Y CONSTANTES:

    // -- Runnable que actualiza la hora y fecha:

        private final JLabel FECHA_ENTRADA_LB       = new JLabel("");
        private final JLabel FECHA_SALIDA_LB        = new JLabel("");

        private final Fecha_y_hora DATE_CONTROLLER          = new Fecha_y_hora( this.FECHA_ENTRADA_LB );
        private final Fecha_y_hora DATE_CONTROLLER_SALIDA   = new Fecha_y_hora( this.FECHA_SALIDA_LB );

        // -- Timer que se llamara al runnable anteriormente declarado:

        private final ScheduledExecutorService UPDATE_TIME = Executors.newSingleThreadScheduledExecutor();
        private final ScheduledExecutorService UPDATE_TIME_SALIDA = Executors.newSingleThreadScheduledExecutor();

        // -- todas las vistas del usuario:
        //private final InsertarEntradas entradas      = new InsertarEntradas( FECHA_ENTRADA_LB, DATE_CONTROLLER );
        private final PanelEntradas entradas        = new PanelEntradas( FECHA_ENTRADA_LB, DATE_CONTROLLER );
        private final PanelInventario inventario    = new PanelInventario();
        private final PanelSalidas salidas          = new PanelSalidas( FECHA_SALIDA_LB, DATE_CONTROLLER_SALIDA );
        private final PanelTransportes transportes  = new PanelTransportes();
        private final PanelUsuarios usuarios        = new PanelUsuarios();

        // -- iconos de pestaña:

        private final FontAwesome ANGLE_DOUBLE_DOWN = FontAwesome.ANGLE_DOUBLE_DOWN;
        private final FontAwesome STACK_OVERFLOW    = FontAwesome.STACK_OVERFLOW;
        private final FontAwesome ANGLE_DOUBLE_UP   = FontAwesome.ANGLE_DOUBLE_UP;
        private final FontAwesome BUS               = FontAwesome.BUS;
        private final FontAwesome USERS             = FontAwesome.USERS;

        private final Icon entradas_icon        = IconFontSwing.buildIcon(ANGLE_DOUBLE_DOWN	,28, Color.red);
        private final Icon inventario_icon      = IconFontSwing.buildIcon(STACK_OVERFLOW, 28, Color.red);
        private final Icon salidas_icon         = IconFontSwing.buildIcon(ANGLE_DOUBLE_UP, 28, Color.red);
        private final Icon transporte_icon      = IconFontSwing.buildIcon(BUS	, 28, Color.red);
        private final Icon usuario_icon         = IconFontSwing.buildIcon(USERS	, 28, Color.red);

        //panel con pestañas:
        private final Tabs TABBEDPANE          = new Tabs(false);


        private final Tabs.Tab TAB_ENTRADAS     =
                new Tabs.Tab("Entradas.",
                entradas_icon ,
                this.entradas.getview(), "" +
                "Haz click aqui para registrar una entrada");

        private final Tabs.Tab TAB_INVENTARIO   =
                new Tabs.Tab("Inventario.",
                inventario_icon ,
                this.inventario.getview(),
                "Haz click aqui para registrar en el inventario.");

        private final Tabs.Tab TAB_SALIDAS      =
                new Tabs.Tab("Salidas.",
                salidas_icon,
                this.salidas.getview(),
                "Haz click aqui para registrar salidas del almacen.");

        private final Tabs.Tab TAB_TRANSPORTES  =
                new Tabs.Tab("Transportes.",
                transporte_icon,
                this.transportes,
                "Haz click aqui para administrar informacion del transporte.");

        private final Tabs.Tab TAB_USUARIOS     =
                new Tabs.Tab("Usuarios.",
                usuario_icon,
                this.usuarios,
                "Haz click aqui para administrar la informacion de los usuarios");



        //variables de interfaz de uaurio
        private static Login      from;
        private static Principal  instance;
        private String userName                 = "";




    public Principal(String user, Login fromJFrame) throws Config.EmptyProperty, SQLException, Config.ReadException {
         this.setUserName(user);

         this.instance  = this;
         this.from      = fromJFrame;

         this.setBackground(Color.gray);
         IconFontSwing.register(FontAwesome.getIconFont());
         this.startTimer();
         this.prepareTabs();

         this.add(this.TABBEDPANE);
         this.setVisible(true);
         this.setResizable(false);
         this.setLocationRelativeTo(null);
         this.getContentPane().setBackground(Color.WHITE);
         setIconImage(new ImageIcon("./Data/Imagenes/IconoTprlogistics.png").getImage());
         this.setBounds( Panel.bounds );
         this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
         this.addWindowListener(new WindowAdapterImplement(fromJFrame, this));
     }

     // -- METODOS DE LA CLASE:

    private void prepareTabs(){
        TABBEDPANE.setBackground(Color.gray);
        TABBEDPANE.addTab(TAB_ENTRADAS);
        TABBEDPANE.addTab(TAB_INVENTARIO);
        TABBEDPANE.addTab(TAB_SALIDAS);
        TABBEDPANE.addTab(TAB_TRANSPORTES);
        TABBEDPANE.addTab(TAB_USUARIOS);

        TABBEDPANE.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JTabbedPane tabbedPane = (JTabbedPane) e.getSource();
                int selectedIndex = tabbedPane.getSelectedIndex();
                switch (selectedIndex)
                {
                    /*case 0:
                    {
                        entradas.cargaComboBoxs();
                        entradas.vaciarTextos();
                    }
                    break;*/
                    case 1:
                    {
                        inventario.vaciarTextos();
                    }
                    break;
                    case 2:
                    {
                        salidas.getRegistro().cargaComboBox();
                        salidas.getRegistro().vaciarTextos();
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
                entradas.getConsultarEntrada().hideDatePickers();
                salidas.getConsultar().hideDatePickers();
            }
        });
    }

    private void startTimer(){
        this.UPDATE_TIME
                .scheduleWithFixedDelay(this.DATE_CONTROLLER,
                        0,1, TimeUnit.SECONDS);

        this.UPDATE_TIME_SALIDA.scheduleWithFixedDelay(this.DATE_CONTROLLER_SALIDA,
                0,1, TimeUnit.SECONDS);
    }

    public void stopTimer(){
        if(!this.UPDATE_TIME.isShutdown())
            this.UPDATE_TIME.shutdown();

        if(!this.UPDATE_TIME_SALIDA.isShutdown())
            this.UPDATE_TIME_SALIDA.shutdown();
    }

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

    public Tabs getTabbedPane() {
        return TABBEDPANE;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        this.setTitle("Control de inventario — Bienvenido " + this.userName);
    }


}
