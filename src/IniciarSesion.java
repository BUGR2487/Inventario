import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IniciarSesion
{
    private String Username = "";
    private String Password = "";
    static boolean resultado = false;

    private static final String SQL_SELECT = "SELECT * FROM inventario.usuario WHERE CorreoElectronico=? AND Contrasena=?;";

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

    public static boolean busquedaUsuario(IniciarSesion iniciarSesion)
    {
        Connection conn;
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;

        try
        {
            conn = Conexion.getConnection();
            preparedStatement = conn.prepareStatement(SQL_SELECT);
            preparedStatement.setString(1, iniciarSesion.getUsername());
            preparedStatement.setString(2, iniciarSesion.getPassword());
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                resultado = true;
                if(resultado)
                {
                    String NombreUsuario = resultSet.getString("Nombre") + " " + resultSet.getString("ApellidoPaterno") + " " + resultSet.getString("ApellidoMaterno");
                    FrmPrincipal frmPrincipal = new FrmPrincipal(NombreUsuario);
                    frmPrincipal.show();
                }
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return resultado;
    }
}
