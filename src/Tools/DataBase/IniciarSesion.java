package Tools.DataBase;

import Tools.Config;

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

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public boolean login()
    {

        String NombreUsuario = this.conn.busquedaUsuario(this);

        return !NombreUsuario.isEmpty();
    }


}
