package Forms.Principal.Usuario;

import Forms.Principal.Panel;
import Tools.Config;
import Tools.DataBase.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Point2D;
import java.sql.SQLException;

public class PanelUsuarios extends JPanel implements ActionListener, KeyListener, Panel {

    // -- VARIABLES:

    // -- etiquetas:
    private final JLabel NOMBRES_LB              = new JLabel("Nombre(s)");
    private final JLabel APELLIDO_P_LB           = new JLabel("Apellido paterno");
    private final JLabel APELLIDO_M_LB           = new JLabel("Apellido materno");

    private final JLabel PUESTO_LB               = new JLabel("Puesto");
    private final JLabel CORREO_LB               = new JLabel("Correo");
    private final JLabel PASS_LB                 = new JLabel("Clave");

    private final JLabel CONFIRMAR_PASS_LB       = new JLabel("Confirmar clave");

    // -- campos de texto:
    private final JTextField NOMBRES_TXT         = new JTextField();
    private final JTextField APELLIDO_P_TXT      = new JTextField();
    private final JTextField APELLIDO_M_TXT      = new JTextField();

    private final JTextField PUESTO_TXT          = new JTextField();
    private final JTextField CORREO_TXT          = new JTextField();
    private final JTextField PASS_TXT            = new JTextField();

    private final JTextField CONFIRMAR_PASS_TXT  = new JTextField();

    // -- botones:
    private final JButton REGISTRAR_USUARIO      = new JButton("Registrar usuario.");

    // -- scroll de la vista:
    private final JScrollPane PANE = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

    private final Usuario USARIO = new Usuario();

    // -- CONSTRUCTOR:

    public PanelUsuarios() throws Config.EmptyProperty, SQLException, Config.ReadException {
        this.prepareAll();
        this.setLayout(new UsuariosLayout(this));
        this.PANE.getViewport().add(this);
    }

    // -- METODOS DE LA CLASE:

    public void prepareAll()
    {
        this.NOMBRES_LB        .setFont(this.FUENTE_GENERAL_LB);
        this.APELLIDO_P_LB     .setFont(this.FUENTE_GENERAL_LB);
        this.APELLIDO_M_LB     .setFont(this.FUENTE_GENERAL_LB);

        this.PUESTO_LB         .setFont(this.FUENTE_GENERAL_LB);
        this.CORREO_LB         .setFont(this.FUENTE_GENERAL_LB);
        this.PASS_LB           .setFont(this.FUENTE_GENERAL_LB);

        this.CONFIRMAR_PASS_LB .setFont(this.FUENTE_GENERAL_LB);

        this.NOMBRES_TXT       .setFont(this.FUENTE_GENERAL_TXT);
        this.APELLIDO_P_TXT    .setFont(this.FUENTE_GENERAL_TXT);
        this.APELLIDO_M_TXT    .setFont(this.FUENTE_GENERAL_TXT);

        this.PUESTO_TXT        .setFont(this.FUENTE_GENERAL_TXT);
        this.CORREO_TXT        .setFont(this.FUENTE_GENERAL_TXT);
        this.PASS_TXT          .setFont(this.FUENTE_GENERAL_TXT);

        this.CONFIRMAR_PASS_TXT.setFont(this.FUENTE_GENERAL_TXT);

        this.REGISTRAR_USUARIO.setFont(this.FUENTE_GENERAL_TXT);

        this.NOMBRES_TXT       .addKeyListener( this );
        this.APELLIDO_P_TXT    .addKeyListener( this );
        this.APELLIDO_M_TXT    .addKeyListener( this );

        this.PUESTO_TXT        .addKeyListener( this );
        this.CORREO_TXT        .addKeyListener( this );
        this.PASS_TXT          .addKeyListener( this );

        this.CONFIRMAR_PASS_TXT.addKeyListener( this );

        this.REGISTRAR_USUARIO.addActionListener( this );

        this.NOMBRES_TXT       .setHorizontalAlignment(JTextField.CENTER);
        this.APELLIDO_P_TXT    .setHorizontalAlignment(JTextField.CENTER);
        this.APELLIDO_M_TXT    .setHorizontalAlignment(JTextField.CENTER);

        this.PUESTO_TXT        .setHorizontalAlignment(JTextField.CENTER);
        this.CORREO_TXT        .setHorizontalAlignment(JTextField.CENTER);
        this.PASS_TXT          .setHorizontalAlignment(JTextField.CENTER);

        this.CONFIRMAR_PASS_TXT.setHorizontalAlignment(JTextField.CENTER);

    }


    // -- GET'S Y SET'S:

    public JLabel getNOMBRES_LB() {
        return NOMBRES_LB;
    }

    public JLabel getAPELLIDO_P_LB() {
        return APELLIDO_P_LB;
    }

    public JLabel getAPELLIDO_M_LB() {
        return APELLIDO_M_LB;
    }

    public JLabel getPUESTO_LB() {
        return PUESTO_LB;
    }

    public JLabel getCORREO_LB() {
        return CORREO_LB;
    }

    public JLabel getPASS_LB() {
        return PASS_LB;
    }

    public JLabel getCONFIRMAR_PASS_LB() {
        return CONFIRMAR_PASS_LB;
    }

    public JTextField getNOMBRES_TXT() {
        return NOMBRES_TXT;
    }

    public JTextField getAPELLIDO_P_TXT() {
        return APELLIDO_P_TXT;
    }

    public JTextField getAPELLIDO_M_TXT() {
        return APELLIDO_M_TXT;
    }

    public JTextField getPUESTO_TXT() {
        return PUESTO_TXT;
    }

    public JTextField getCORREO_TXT() {
        return CORREO_TXT;
    }

    public JTextField getPASS_TXT() {
        return PASS_TXT;
    }

    public JTextField getCONFIRMAR_PASS_TXT() {
        return CONFIRMAR_PASS_TXT;
    }

    public JButton getREGISTRAR_USUARIO() {
        return REGISTRAR_USUARIO;
    }


    // -- METODOS OVERRIDE:

    @Override
    protected void paintComponent(Graphics g){
        //obtengo rl graphics 2D del panel o lienzo como lo quieras ver:
        Graphics2D g2 = (Graphics2D) g.create();
        Rectangle clip = g2.getClipBounds();

        // saco las coordenadas maximas de la ventana:
        float x=this.getWidth();
        float y=this.getHeight();

        //el GradientPaint es el objeto que dibuja el degradado
        g2.setPaint(new GradientPaint(
                new Point2D.Float(x/2,0), //punto de donde inicia el primer color
                color2, // color 1
                new Point2D.Float(x,y), //punto donde empieza el segundo color
                color1)); // segundo color

        //dibujo en un rectangulo del tamaño de la ventana mi gradiente:
        g2.fillRect(clip.x, clip.y, clip.width, clip.height);
    }

    @Override
    public boolean camposVacios() {
        return  this.NOMBRES_TXT       .getText().isEmpty() ||
                this.APELLIDO_P_TXT    .getText().isEmpty() ||
                this.APELLIDO_M_TXT    .getText().isEmpty() ||
                this.PUESTO_TXT        .getText().isEmpty() ||
                this.CORREO_TXT        .getText().isEmpty() ||
                this.PASS_TXT          .getText().isEmpty() ||
                this.CONFIRMAR_PASS_TXT.getText().isEmpty();
    }

    @Override
    public void vaciarTextos() {
                this.NOMBRES_TXT       .setText( "" );
                this.APELLIDO_P_TXT    .setText( "" );
                this.APELLIDO_M_TXT    .setText( "" );
                this.PUESTO_TXT        .setText( "" );
                this.CORREO_TXT        .setText( "" );
                this.PASS_TXT          .setText( "" );
                this.CONFIRMAR_PASS_TXT.setText( "" );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.getREGISTRAR_USUARIO()) {
            if (camposVacios()) {
                JOptionPane.showMessageDialog(this,
                        "Falta llenar campos",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                if (this.getPASS_TXT().getText().equals(this.getCONFIRMAR_PASS_TXT().getText())) {
                    this.USARIO.setNombre(this.getNOMBRES_TXT().getText());
                    this.USARIO.setApellidoPaterno(this.getAPELLIDO_P_TXT().getText());
                    this.USARIO.setApellidoMaterno(this.getAPELLIDO_M_TXT().getText());
                    this.USARIO.setPuesto(this.getPUESTO_TXT().getText());
                    this.USARIO.setCorreoElectronico(this.getCORREO_TXT().getText());
                    this.USARIO.setContrasena(this.getPASS_TXT().getText());

                    this.USARIO.insertar();//REGRESA UN -1 EN CASO DE ERROR

                    //usuario.actualizar();//RETORNA UN -1 EN CASO DE ERROR

                    //usuario.eliminarUsuario(); //REGRESA UN -1 EN CASO DE ERROR

                    //usuario = Conexion.seleccionarIdUsuario(usuario);
                    // OJO QUE SI NO ENCUENTRA EL USUARIO RETORNA UN NULL, SOLOVALIDA CON UN IF

                    vaciarTextos();

                    JOptionPane.showMessageDialog(this,
                            "Tools.DataBase.Usuario registrado",
                            "Registro de usuario",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Contraseñas diferentes",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
