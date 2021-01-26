/**

 ————————————————————————————
            IMSOFT
 ————————————————————————————

 Para que puedas revisar el codigo mas rapido, selecciona una de las siguinetes opciones con todo y
 numero y presiona ctrl + f o cmd + f si estas en mac os y busca el comentario, ahí inicia lo que
 marca ;)

 MENU DE CODIGO:
 1 -- VARIABLES Y CONSTANTES
 2 -- CONSTRUCTOR
 3 -- GET'S Y SET'S
 4 -- METODOS DE LA CLASE
 5 -- METODOS OVERRIDE

**/
package Forms.Login;

import Tools.Config;
import Tools.DataBase.IniciarSesion;
import Tools.TextPrompt;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

//PARA IR RAPIDO DE SECCION A SECCION USA CTRL + F O CMD + F(EN MAC) Y BUSCA LAS SIGUIENTES PALABRAS:
//1 -- VARIABLES Y CONSTANTES:
//2 -- CONSTRUCTOR:
//3 -- GET'S Y SET'S:
//4 -- METODOS DE LA CLASE:
//5 -- METODOS OVERRIDE:


/**
 *
 * JPanel que contiene los botones, campos de texto, etiquetas, imagenes
 * y el modelo de la base de datos que interactua el
 * inicio de sesión.
 *
 */
public class PanelLogin extends JPanel {

    // 1 -- VARIABLES Y CONSTANTES:

    private Color color1 = Color.decode("#51B3FF");
    private Color color2 = Color.decode("#EDEAEA");

    //modelo a BD:
    private IniciarSesion login = new IniciarSesion();

    //variables de control:
    private boolean mostrarPass = false;

    //campos de texto:
    private final JTextField USUARIO_TXT        = new JTextField();
    private final JPasswordField PASSWORD_TXT   = new JPasswordField();

    //etiquetas:
    private final JLabel USUARIO_LB                 = new JLabel("Usuario:");
    private final JLabel PASSWORD_LB                = new JLabel("Contrase\u00f1a:");
    private final JLabel CONTENEDOR_ICONO_LB        = new JLabel();
    private final JLabel CONTENEDOR_MOSTRAR_PASS_LB = new JLabel();

    //botones:
    private final JButton INICIAR_SESION_BTN = new JButton("Iniciar sesi\u00f3n");

    //imagenes o material grefico:
    private final ImageIcon ICONO_IMG           = new ImageIcon("./Data/Imagenes/LogoTprlogistics.png");
    private final ImageIcon OLCULTAR_PASS_IMG   = new ImageIcon("./Data/Imagees/Ocultar.png");
    private final ImageIcon MOSTRAR_PASS_IMG    = new ImageIcon("./Imagenes/Mostrar.png");




    // 2 -- CONSTRUCTOR:

    /**
     * Constructor que genera un JPanel acomodado para el JFrame de {@link Login}. Recibe como parametro
     * una instancia de la clase {@link Login} que implementa el Actionlinener que se usaran en los botones del
     * mismo panel.
     *
     * @param ac {@link Login} -> ActionListener | KeyListener
     *
     * @throws Config.EmptyProperty
     * @throws SQLException
     * @throws Config.ReadException
     */
    PanelLogin (Login ac) throws Config.EmptyProperty, SQLException, Config.ReadException {

        //agrego los placeHolder en los campos de textos:
        this.prepareTextField(ac);

        //configurando actionListener:
        IconFontSwing.register(FontAwesome.getIconFont());
        this.getINICIAR_SESION_BTN().addActionListener(ac);

        //estableciendo imagenes en su contenedor(JLabel):
        this.getICONO_IMG().setImage(this.getICONO_IMG().getImage().getScaledInstance(400, 250,  java.awt.Image.SCALE_SMOOTH));
        this.getCONTENEDOR_ICONO_LB().setIcon(this.getICONO_IMG());
        this.getCONTENEDOR_MOSTRAR_PASS_LB().setIcon(this.getImageHiddeOrShowPass());

        //acomodando los layout
        this.setLayout(new Layout(this));
    }

    // 3 -- GET'S Y SET'S:

    public JTextField getUSUARIO_TXT() {
        return USUARIO_TXT;
    }

    public JPasswordField getPASSWORD_TXT() {
        return PASSWORD_TXT;
    }

    public JLabel getUSUARIO_LB() {
        return USUARIO_LB;
    }

    public JLabel getPASSWORD_LB() {
        return PASSWORD_LB;
    }

    public JLabel getCONTENEDOR_ICONO_LB() {
        return CONTENEDOR_ICONO_LB;
    }

    public JLabel getCONTENEDOR_MOSTRAR_PASS_LB() {
        return CONTENEDOR_MOSTRAR_PASS_LB;
    }

    public JButton getINICIAR_SESION_BTN() {
        return INICIAR_SESION_BTN;
    }

    public ImageIcon getICONO_IMG() {
        return ICONO_IMG;
    }

    public ImageIcon getOLCULTAR_PASS_IMG() {
        return OLCULTAR_PASS_IMG;
    }

    public ImageIcon getMOSTRAR_PASS_IMG() {
        return MOSTRAR_PASS_IMG;
    }

    public IniciarSesion getLogin() {
        return login;
    }

    public void setLogin(IniciarSesion login) {
        this.login = login;
    }

    public boolean isMostrarPass() {
        return mostrarPass;
    }

    public void setMostrarPass(boolean mostrarPass) {
        this.mostrarPass = mostrarPass;
    }


    // 4 -- METODOS DE LA CLASE:

    /**
     *
     * Función que prepara y da formato a los componentes de la interfaz grafica, ya sea etiquetas, campos de texto,
     * placeholder o botones.
     *
     * Recibe como parametro una instacia del JFrame Login(src/Forms/Login/Login.java), que implementa
     * el ActionListener para la interfaz.
     *
     * @param ac {@link Login} -> ActionListener
     */
    private void prepareTextField(Login ac){
        TextPrompt placeholderuser      = new TextPrompt("Ingrese su usuario", this.getUSUARIO_TXT());
        TextPrompt placeholderpassword  = new TextPrompt("Ingrese su password", this.getPASSWORD_TXT());

        IconFontSwing.register(FontAwesome.getIconFont());
        Icon iconuser = IconFontSwing.buildIcon(FontAwesome.USER, 28);
        Icon iconpass = IconFontSwing.buildIcon(FontAwesome.KEY, 28);

        placeholderuser.changeAlpha(0.75f);
        placeholderuser.changeStyle(Font.ITALIC);
        placeholderuser.setIcon(iconuser);

        placeholderpassword.changeAlpha(0.75f);
        placeholderpassword.changeStyle(Font.ITALIC);
        placeholderpassword.setIcon(iconpass);
        this.getUSUARIO_TXT().setHorizontalAlignment(JTextField.CENTER);
        this.getPASSWORD_TXT().setHorizontalAlignment(JTextField.CENTER);

        this.getUSUARIO_TXT().addKeyListener(ac);
        this.getPASSWORD_TXT().addKeyListener(ac);
    }

    /**
     *
     * Funcion que retorna un ImageIcon para mostrar y ocultar el password
     *
     * @return {@link ImageIcon}
     */
    private ImageIcon getImageHiddeOrShowPass()
    {
        return (this.mostrarPass)? this.getMOSTRAR_PASS_IMG() : this.getOLCULTAR_PASS_IMG();
    }

    /**
     * Función que retorna un boleano indicando si el usuario o contraseña existe, para darle acceso al sistema.
     *
     * Retorna un true si el usuario existe, de lo contrario manda false.
     *
     * @return boolean
     * @throws UnsupportedEncodingException
     */
    public boolean identificar() throws UnsupportedEncodingException {

        //se obtienen los valores ingresados:
        String usuario = this.getUSUARIO_TXT().getText();
        String pass = this.getPASSWORD_TXT().getText();

        //se comprueba que no sean valores vacios para ambos, de lo contrario
        // manda un aviso de campo vacio:
        if(usuario.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "No ha ingresado el usuario, por favor intente de nuevo...", "Campos vacios.", JOptionPane.ERROR_MESSAGE);
            this.getUSUARIO_TXT().requestFocus();
            return false;
        }

        if(pass.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "No ha ingresado su clave en el sistema, por favor intente de nuevo...", "Campos vacios.", JOptionPane.ERROR_MESSAGE);
            this.getPASSWORD_TXT().requestFocus();
            return false;
        }

        //se establece los valores en la instancia IniciarSesion los valores recibidos
        this.getLogin().setUsername(usuario);
        //se manda el pasword encriptado:
        this.getLogin().setPassword(pass, true);

        //se limpian los campos de texto
        this.getUSUARIO_TXT().setText("");
        this.getPASSWORD_TXT().setText("");

        //se hace comprobacion contra el usuario guardado
        // en la bd y se retorna el resultado:
        return this.getLogin().login();
    }

    /**
     * Función que retorna el nombre del usuario que ingreso:
     *
     * @return {@link String}
     */
    public String getUsuario(){
        return this.getLogin().getUsername();
    }

    // 5 -- METODOS OVERRIDE:

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
                new Point2D.Float(50,50), //punto de donde inicia el primer color
                color2, // color 1
                new Point2D.Float(x,y), //punto donde empieza el segundo color
                color1)); // segundo color

        //dibujo en un rectangulo del tamaño de la ventana mi gradiente:
        g2.fillRect(clip.x, clip.y, clip.width, clip.height);
    }
}


