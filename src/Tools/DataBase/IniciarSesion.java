package Tools.DataBase;

import Tools.Auth;
import Tools.Config;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

public class IniciarSesion
{
    private final Conexion conn = Conexion.getInstance();

    private String Username = "";
    private String Password = "";
    static boolean resultado = false;

    public IniciarSesion() throws SQLException, Config.ReadException, Config.EmptyProperty {
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword(boolean encrypt) throws UnsupportedEncodingException {
        return (encrypt)? Password: Auth.desencriptar(Password);

    }

    public void setPassword(String password, boolean encrypt) throws UnsupportedEncodingException {
        Password = (encrypt)? Auth.encriptar( password ): password;
    }

    public void closeSesion(){
        this.Password = "";
        this.Username = "";
    }

    public boolean login()
    {

        String NombreUsuario = this.conn.busquedaUsuario(this);

        return !NombreUsuario.isEmpty();
    }


}
