import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Usuario
{
    private int idUsuario = 0;
    private String Nombre = "";
    private String ApellidoPaterno = "";
    private String ApellidoMaterno = "";
    private String Puesto = "";
    private String CorreoElectronico = "";
    private String Contrasena = "";
    private String Contrasena2 = "";

    private static final String SQL_SELECT = "SELECT * FROM inventario.usuario;";
    private static final String SQL_SELECT_ID = "SELECT IdUsuario FROM inventario.usuario WHERE CorreoElectronico = ?;";
    private static final String SQL_INSERT = "INSERT INTO `inventario`.`usuario` (`Nombre`, `ApellidoPaterno`, `ApellidoMaterno`, `Puesto`, `CorreoElectronico`, `Contrasena`) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String SQL_UPDATE = "UPDATE `inventario`.`usuario` SET `Nombre` = ?, `ApellidoPaterno` = ?, `ApellidoMaterno` = ?, `Puesto` = ?, `CorreoElectronico` = ?, `Contrasena` = ? WHERE (`IdUsuario` = ?);";
    private static final String SQL_DELETE = "DELETE FROM `inventario`.`usuario` WHERE (`CorreoElectronico` = ?);";

    public int getIdUsuario() { return idUsuario; }

    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNombre()
    {
        return Nombre;
    }

    public void setNombre(String nombre)
    {
        Nombre = nombre;
    }

    public String getApellidoPaterno()
    {
        return ApellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno)
    {
        ApellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno()
    {
        return ApellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno)
    {
        ApellidoMaterno = apellidoMaterno;
    }

    public String getPuesto()
    {
        return Puesto;
    }

    public void setPuesto(String puesto)
    {
        Puesto = puesto;
    }

    public String getCorreoElectronico()
    {
        return CorreoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico)
    {
        CorreoElectronico = correoElectronico;
    }

    public String getContrasena()
    {
        return Contrasena;
    }

    public void setContrasena(String contrasena)
    {
        Contrasena = contrasena;
    }

    public String getContrasena2() { return Contrasena2; }

    public void setContrasena2(String contrasena2) { Contrasena2 = contrasena2; }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", Nombre='" + Nombre + '\'' +
                ", ApellidoPaterno='" + ApellidoPaterno + '\'' +
                ", ApellidoMaterno='" + ApellidoMaterno + '\'' +
                ", Puesto='" + Puesto + '\'' +
                ", CorreoElectronico='" + CorreoElectronico + '\'' +
                ", Contrasena='" + Contrasena + '\'' +
                '}';
    }

    public static int seleccionarIdUsuario(Usuario usuario)
    {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int rows = 0;

        try
        {
            conn = Conexion.getConnection();
            preparedStatement = conn.prepareStatement(SQL_SELECT_ID);
            resultSet = preparedStatement.executeQuery();

            int idUsuario = resultSet.getInt("id_Usuario");

            System.out.println("ID recibido: " + idUsuario);

            preparedStatement.setString(1, usuario.getCorreoElectronico());

            usuario = new Usuario();
            usuario.setIdUsuario(idUsuario);

            System.out.println("Ejecutanto query " + SQL_SELECT_ID);
            rows = preparedStatement.executeUpdate();
            System.out.println("Registros afectados: " + rows);
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace(System.out);
        }
        finally
        {
            Conexion.close(resultSet);
            Conexion.close(preparedStatement);
            Conexion.close(conn);
        }
        return rows;
    }

    public static int insertarUsuario(Usuario usuario)
    {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        int rows = 0;

        try
        {
            conn = Conexion.getConnection();
            preparedStatement = conn.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, usuario.getApellidoPaterno());
            preparedStatement.setString(3, usuario.getApellidoMaterno());
            preparedStatement.setString(4, usuario.getPuesto());
            preparedStatement.setString(5, usuario.getCorreoElectronico());
            preparedStatement.setString(6, usuario.getContrasena());

            System.out.println("Ejecutanto query " + SQL_INSERT);
            rows = preparedStatement.executeUpdate();
            System.out.println("Registros afectados: " + rows);
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace(System.out);
        }
        finally
        {
            Conexion.close(preparedStatement);
            Conexion.close(conn);
        }
        return rows;
    }

    public static int actualizarUsuario(Usuario usuario)
    {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        int rows = 0;

        try
        {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando Query" + SQL_UPDATE);
            preparedStatement = conn.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, usuario.getApellidoPaterno());
            preparedStatement.setString(3, usuario.getApellidoMaterno());
            preparedStatement.setString(4, usuario.getPuesto());
            preparedStatement.setString(5, usuario.getCorreoElectronico());
            preparedStatement.setString(6, usuario.getContrasena());
            preparedStatement.setString(7, usuario.getCorreoElectronico());

            rows = preparedStatement.executeUpdate();
            System.out.println("Registros actualizados: " + rows);
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace(System.out);
        }
        finally
        {
            Conexion.close(preparedStatement);
            Conexion.close(conn);
        }

        return rows;
    }

    public static int eliminarUsuario(Usuario usuario)
    {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        int rows = 0;

        try
        {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando Query" + SQL_DELETE);
            preparedStatement = conn.prepareStatement(SQL_DELETE);

            preparedStatement.setString(1, usuario.getCorreoElectronico());

            rows = preparedStatement.executeUpdate();
            System.out.println("Registros actualizados: " + rows);
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace(System.out);
        }
        finally
        {
            Conexion.close(preparedStatement);
            Conexion.close(conn);
        }

        return rows;
    }
}