import java.sql.*;

public class Conexion
{
    private static final String JDBC_NOMBRE_BD = "inventario";
    private static final String JDBC_URL = "jdbc:mysql://localhost:8889/" + JDBC_NOMBRE_BD + "?useSSL=false&serverTimezone=UTC";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "root";

    public static Connection getConnection() throws SQLException
    {
            return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    public static void close(ResultSet resultSet)
    {
        try
        {
            resultSet.close();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace(System.out);
        }
    }

    public static void close(PreparedStatement preparedStatement)
    {
        try
        {
            preparedStatement.close();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace(System.out);
        }
    }

    public static  void close(Connection connection)
    {
        try
        {
            connection.close();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace(System.out);
        }
    }
}
