import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Salidas
{
    private String NumPedido = "";
    private String Sellos = "";
    private int CantidadPallet = 0;
    private int CantidadPorPallet = 0;
    private int TotalUnidades = 0;
    private String FechaEntrega = "";
    private String Chofer = "";
    private String Empresa = "";
    private String Placas = "";
    private String TractoCamion = "";

    private static final String SQL_INSERT = "INSERT INTO `inventario`.`salidas` (`NoPedido`, `Sellos`, `CantidadPallet`, `CantidadPorPallet`, `TotalUnidades`, `FechaEntrega`, `Chofer`, `Empresa`, `Placas`, `TractoCamion`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String SQL_SELECT_NOPEDIDO = "SELECT NoPedido FROM inventario.entradas;";
    private static final String SQL_SELECT_BUSQUEDANOPEDIDO = "SELECT CantidadPallet, CantidadPorPallet, TotalUnidades, Chofer, Empresa, Placas, TractoCamion FROM inventario.entradas WHERE NoPedido=?;";

    public String getNumPedido() {
        return NumPedido;
    }

    public void setNumPedido(String numPedido) {
        NumPedido = numPedido;
    }

    public String getSellos() {
        return Sellos;
    }

    public void setSellos(String sellos) {
        Sellos = sellos;
    }

    public int getCantidadPallet() {
        return CantidadPallet;
    }

    public void setCantidadPallet(int cantidadPallet) {
        CantidadPallet = cantidadPallet;
    }

    public int getCantidadPorPallet() {
        return CantidadPorPallet;
    }

    public void setCantidadPorPallet(int cantidadPorPallet) {
        CantidadPorPallet = cantidadPorPallet;
    }

    public int getTotalUnidades() {
        return TotalUnidades;
    }

    public void setTotalUnidades(int totalUnidades) {
        TotalUnidades = totalUnidades;
    }

    public String getFechaEntrega() {
        return FechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        FechaEntrega = fechaEntrega;
    }

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

    public static int insertarSalidas(Salidas salidas)
    {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        int rows = 0;

        try
        {
            conn = Conexion.getConnection();
            preparedStatement = conn.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, salidas.getNumPedido());
            preparedStatement.setString(2, salidas.getSellos());
            preparedStatement.setInt(3, salidas.getCantidadPallet());
            preparedStatement.setInt(4, salidas.getCantidadPorPallet());
            preparedStatement.setInt(5, salidas.getTotalUnidades());
            preparedStatement.setString(6, salidas.getFechaEntrega());
            preparedStatement.setString(7, salidas.getChofer());
            preparedStatement.setString(8, salidas.getEmpresa());
            preparedStatement.setString(9, salidas.getPlacas());
            preparedStatement.setString(10, salidas.getTractoCamion());

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

    public ResultSet consultaNumPedido()
    {
        Connection conn;
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;

        try
        {
            conn = Conexion.getConnection();
            preparedStatement = conn.prepareStatement(SQL_SELECT_NOPEDIDO);
            resultSet = preparedStatement.executeQuery();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return resultSet;
    }

    public DefaultComboBoxModel obtenerPedidos()
    {
        DefaultComboBoxModel ListaModelo = new DefaultComboBoxModel();
        ListaModelo.addElement("");
        ResultSet resultSet = this.consultaNumPedido();

        try
        {
            while(resultSet.next())
            {
                ListaModelo.addElement(resultSet.getString("NoPedido"));
            }
            resultSet.close();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }

        return  ListaModelo;
    }

    public static ResultSet busquedaNumPedido(FrmSalidas frmSalidas, Salidas salidas)
    {
        Connection conn;
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;

        try
        {
            conn = Conexion.getConnection();
            preparedStatement = conn.prepareStatement(SQL_SELECT_BUSQUEDANOPEDIDO);
            preparedStatement.setString(1, salidas.getNumPedido());
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                frmSalidas.TxtCantidadPallet.setText(resultSet.getString("CantidadPallet"));
                frmSalidas.TxtCantidadPorPallet.setText(resultSet.getString("CantidadPorPallet"));
                frmSalidas.TxtTotalUnidades.setText(resultSet.getString("TotalUnidades"));
                frmSalidas.TxtChofer.setText(resultSet.getString("Chofer"));
                frmSalidas.TxtEmpresa.setText(resultSet.getString("Empresa"));
                frmSalidas.TxtPlacas.setText(resultSet.getString("Placas"));
                frmSalidas.TxtTractoCamion.setText(resultSet.getString("TractoCamion"));
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return resultSet;
    }
}
