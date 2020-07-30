import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transporte
{
    private String Chofer = "";
    private String Empresa = "";
    private String Placas = "";
    private String TractoCamion = "";

    private static final String SQL_INSERT = "INSERT INTO `inventario`.`transporte` (`Chofer`, `Empresa`, `Placas`, `TractoCamion`) VALUES (?, ?, ?, ?);";

    public String getChofer() {
        return Chofer;
    }

    public void setChofer(String chofer) {
        Chofer = chofer;
    }

    public String getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(String empresa) {
        Empresa = empresa;
    }

    public String getPlacas() {
        return Placas;
    }

    public void setPlacas(String placas) {
        Placas = placas;
    }

    public String getTractoCamion() {
        return TractoCamion;
    }

    public void setTractoCamion(String tractoCamion) {
        TractoCamion = tractoCamion;
    }

    public static int insertarChofer(Transporte chofer)
    {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        int rows = 0;

        try
        {
            conn = Conexion.getConnection();
            preparedStatement = conn.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, chofer.getChofer());
            preparedStatement.setString(2, chofer.getEmpresa());
            preparedStatement.setString(3, chofer.getPlacas());
            preparedStatement.setString(4, chofer.getTractoCamion());

            //("Ejecutanto query " + SQL_INSERT);
            rows = preparedStatement.executeUpdate();
            //System.out.println("Registros afectados: " + rows);
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
}
