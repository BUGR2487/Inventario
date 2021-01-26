package Forms.Login;

import Forms.Principal.Principal;
import Tools.Config;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

//PARA IR RAPIDO DE SECCION A SECCION USA CTRL + F O CMD + F(EN MAC) Y BUSCA LAS SIGUIENTES PALABRAS:
//1 - VARIABLES
//2 - CONSTRUCTOR
//3 - METODOS PERSONALIZADOS
//4 - METODOSOVERRIDE

/**
 * JFrame que genera la ventana de login, esta misma tiene su propio laayout y panel
 *
 * - {@link PanelLogin}: src/Forms/Login/PanelLogin.java
 *
 */
public class Login extends JFrame implements ActionListener, KeyListener{

    //1 - VARIABLES:

    //variable del panel Login:
    private PanelLogin panel;

    //variable de la ventana principal una vez haya iniciado sesión el usuario:
    private Principal panelUser;

    //2 - CONSTRUCTOR:

    /**
     * Contrustor del JFrame que inicia y abre la ventana del login justo en el arranque del programa
     *
     * @throws Config.EmptyProperty
     * @throws SQLException
     * @throws Config.ReadException
     */
    public Login() throws Config.EmptyProperty, SQLException, Config.ReadException {
        //inicial el panel a usar en el login:
        this.panel = new PanelLogin(this);

        //se establece dicho panel como el panel contenedor del JFrame:
        this.setContentPane(this.panel);

        //seestablece las dimensiones de la ventana y la posicion en la que va a aparecer:
        this.setBounds(300,30, 500,700);

        //evitamos que pueda cambiar su tamaño:
        this.setResizable(false);

        //establecemos que cuando se cierre esta ventana finalice el programa:
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //hacemos la ventana visible para el usuario:
        this.setVisible(true);
    }


    //3 - METODOS PERSONALIZADOS:

    /**
     * Función que cierra la sesion del usuario colocando en nulo la instacia del
     * usuario que inicio sesion y dejando la ventana lista para otro usuario
     */
    public void cerrarSesion() {
        this.panel.getLogin().closeSesion();
        if(this.panelUser == null)
            this.panelUser.setTitle("");
    }


    /**
     *
     * Función que genera una ventana pop de confirmación de error usada para mostrar al cliente los errores que
     * pudiesen salir a lo largo del programa ya sea de un dato mal ingresado.
     *
     * @param err {@link String} con la descripción del error.
     * @param errTitle {@link String} con el titulo del error.
     */
    private void showDialogError(String err, String errTitle)
    {
        JOptionPane.showMessageDialog(this,
                err,
                errTitle,
                JOptionPane.ERROR_MESSAGE);
    }

    //4 - METODOS OVERRIDE:

    @Override
    public void actionPerformed(ActionEvent e) {
        //se detecta si el usuario dio click:
        if(e.getSource() == this.panel.getINICIAR_SESION_BTN())
        {
            try {
                //comprueba que de verdad exista el usuario en la bd
                if(this.panel.identificar()){

                    //checa si ya se habia iniciado el programa anteriormente:
                    if(this.panelUser == null) {
                        try {
                            this.panelUser = new Principal(this.panel.getUsuario(), this);

                        } catch (SQLException throwables) {
                            showDialogError("Ocurrio un error al conectar con la base de datos, verifique " +
                                            "el error y vuelva a intentarlo.",
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
                    else
                        //al ya haber tenido un usuario logueado anteriormente evita crear otra instancia y prepara
                        // la ya creada con nuevos valores
                        this.panelUser.setUserName(this.panel.getUsuario());

                    //hacemos visible la vista con la nueva sesión y el panel de
                    // login desaparece a la vista del usuario:
                    this.setVisible(false);
                    this.panelUser.setVisible(true);

                }
                else
                {
                    //si no existe el usuario se le muestra un mensaje de error:
                    this.showDialogError("Usuario no registrado en el sistema, " +
                            "pruebe con un acceso valido.", "Login");
                }
            } catch (UnsupportedEncodingException unsupportedEncodingException) {
                //Al haber un error en la comprobación tira el siguiente aviso:
                this.showDialogError("Usuario no identificado.", "Login error");
                return;
            }

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
        {
            this.panel.getINICIAR_SESION_BTN().doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }



}
