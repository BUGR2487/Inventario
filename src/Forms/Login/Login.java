package Forms.Login;

import Forms.Principal.Principal;
import Tools.Config;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

public class Login extends JFrame implements ActionListener, KeyListener{

    private PanelLogin panel;
    private Principal panelUser;

    public Login() throws Config.EmptyProperty, SQLException, Config.ReadException {
        this.panel = new PanelLogin(this);
        this.setContentPane(this.panel);
        this.setBounds(300,30, 500,700);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


    public void cerrarSesion(){
        this.panel.getLogin().setPassword("");
        this.panel.getLogin().setUsername("");
        if(this.panelUser == null)
            this.panelUser.setTitle("");
    }

    private void showDialogError(String err, String errTitle)
    {
        JOptionPane.showMessageDialog(this,
                err,
                errTitle,
                JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.panel.getINICIAR_SESION_BTN())
        {
            if(this.panel.identificar()){

                if(this.panelUser == null) {
                    try {
                        this.panelUser = new Principal(this.panel.getUsuario(), this);

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
                else
                    this.panelUser.setUserName(this.panel.getUsuario());


                this.setVisible(false);
                this.panelUser.setVisible(true);
                System.out.println( this.panelUser.getSize().getHeight() +" / "+ this.panelUser.getSize().getWidth() );
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
