import javax.swing.*;
import java.sql.*;

public class Entradas
{
    private String FechaEntrada = "";
    private String HoraEntrada = "";
    private String CodigoBarras = "";
    private int Diseno = 0;
    private String CodigoInterno = "";
    private String Cliente = "";
    private String Producto = "";
    private int CantidadPallet = 0;
    private int CantidadPorPallet = 0;
    private int TotalUnidades = 0;
    private int NumOrden = 0;
    private int NumPedido = 0;
    private String Condicion = "";
    private String Observaciones = "";
    private int Folio = 0;
    private String Chofer = "";
    private String Empresa = "";
    private String Placas = "";
    private String TractoCamion = "";

    private static final String SQL_INSERT = "INSERT INTO `inventario`.`entradas` (`NoOrden`, `NoPedido`, `FechaEntrada`, `HoraEntrada`, `CodigoBarras`, `Diseno`, `CodigoInterno`, `Cliente`, `Folio`, `Producto`, `CantidadPallet`, `CantidadPorPallet`, `TotalUnidades`, `Condicion`, `Observaciones`, `Chofer`, `Empresa`, `Placas`, `TractoCamion`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String SQL_SELECT_CODIGOBARRAS = "SELECT CodigoBarras FROM inventario.inventario;";
    private static final String SQL_SELECT_BUSQUEDACODIGOBARRAS = "SELECT Folio, Diseno, CodigoInterno, Cliente, Producto, CantidadPorPallet FROM inventario.entradas WHERE CodigoBarras=?;";
    private static final String SQL_SELECT_CHOFER = "SELECT Chofer FROM inventario.transporte;";
    private static final String SQL_SELECT_BUSQUEDACHOFER = "SELECT Empresa, Placas, TractoCamion FROM inventario.transporte WHERE Chofer=?;";

    public String getFechaEntrada() {
        return FechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        FechaEntrada = fechaEntrada;
    }

    public String getHoraEntrada() {
        return HoraEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        HoraEntrada = horaEntrada;
    }

    public String getCodigoBarras() {
        return CodigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        CodigoBarras = codigoBarras;
    }

    public int getDiseno() {
        return Diseno;
    }

    public void setDiseno(int diseno) {
        Diseno = diseno;
    }

    public String getCodigoInterno() {
        return CodigoInterno;
    }

    public void setCodigoInterno(String codigoInterno) {
        CodigoInterno = codigoInterno;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String cliente) {
        Cliente = cliente;
    }

    public String getProducto() {
        return Producto;
    }

    public void setProducto(String producto) {
        Producto = producto;
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

    public int getNumOrden() {
        return NumOrden;
    }

    public void setNumOrden(int numOrden) {
        NumOrden = numOrden;
    }

    public int getNumPedido() {
        return NumPedido;
    }

    public void setNumPedido(int numPedido) {
        NumPedido = numPedido;
    }

    public String getCondicion() {
        return Condicion;
    }

    public void setCondicion(String condicion) {
        Condicion = condicion;
    }

    public String getObservaciones() {
        return Observaciones;
    }

    public void setObservaciones(String observaciones) {
        Observaciones = observaciones;
    }

    public int getFolio() {
        return Folio;
    }

    public void setFolio(int folio) {
        Folio = folio;
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

    public static int insertarEntradas(Entradas entradas)
    {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        int rows = 0;

        try
        {
            conn = Conexion.getConnection();
            preparedStatement = conn.prepareStatement(SQL_INSERT);
            preparedStatement.setInt(1, entradas.getNumOrden());
            preparedStatement.setInt(2, entradas.getNumPedido());
            preparedStatement.setString(3, entradas.getFechaEntrada());
            preparedStatement.setString(4, entradas.getHoraEntrada());
            preparedStatement.setString(5, entradas.getCodigoBarras());
            preparedStatement.setInt(6, entradas.getDiseno());
            preparedStatement.setString(7, entradas.getCodigoInterno());
            preparedStatement.setString(8, entradas.getCliente());
            preparedStatement.setInt(9, entradas.getFolio());
            preparedStatement.setString(10, entradas.getProducto());
            preparedStatement.setInt(11, entradas.getCantidadPallet());
            preparedStatement.setInt(12, entradas.getCantidadPorPallet());
            preparedStatement.setInt(13, entradas.getTotalUnidades());
            preparedStatement.setString(14, entradas.getCondicion());
            preparedStatement.setString(15, entradas.getObservaciones());
            preparedStatement.setString(16, entradas.getChofer());
            preparedStatement.setString(17, entradas.getEmpresa());
            preparedStatement.setString(18, entradas.getPlacas());
            preparedStatement.setString(19, entradas.getTractoCamion());

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

    public ResultSet consultaCodigoBarras()
    {
        Connection conn;
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;

        try
        {
            conn = Conexion.getConnection();
            preparedStatement = conn.prepareStatement(SQL_SELECT_CODIGOBARRAS);
            resultSet = preparedStatement.executeQuery();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return resultSet;
    }

    public DefaultComboBoxModel obtenerCodigoBarras()
    {
        DefaultComboBoxModel ListaModelo = new DefaultComboBoxModel();
        ListaModelo.addElement("");
        ResultSet resultSet = this.consultaCodigoBarras();

        try
        {
            while(resultSet.next())
            {
                ListaModelo.addElement(resultSet.getString("CodigoBarras"));
            }
            resultSet.close();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }

        return  ListaModelo;
    }

    public static ResultSet busquedaCodigoBarras(FrmEntradas frmEntradas, Entradas entradas)
    {
        Connection conn;
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;

        try
        {
            conn = Conexion.getConnection();
            preparedStatement = conn.prepareStatement(SQL_SELECT_BUSQUEDACODIGOBARRAS);
            preparedStatement.setString(1, entradas.getCodigoBarras());
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                frmEntradas.TxtFolio.setText(resultSet.getString("Folio"));
                frmEntradas.TxtDiseno.setText(resultSet.getString("Diseno"));
                frmEntradas.TxtCodigoInterno.setText(resultSet.getString("CodigoInterno"));
                frmEntradas.TxtCliente.setText(resultSet.getString("Cliente"));
                frmEntradas.TxtProducto.setText(resultSet.getString("Producto"));
                frmEntradas.TxtCantidadPorPallet.setText(resultSet.getString("CantidadPorPallet"));
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet consultaChofer()
    {
        Connection conn;
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;

        try
        {
            conn = Conexion.getConnection();
            preparedStatement = conn.prepareStatement(SQL_SELECT_CHOFER);
            resultSet = preparedStatement.executeQuery();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return resultSet;
    }

    public DefaultComboBoxModel obtenerChofer()
    {
        DefaultComboBoxModel ListaModelo = new DefaultComboBoxModel();
        ListaModelo.addElement("");
        ResultSet resultSet = this.consultaChofer();

        try
        {
            while(resultSet.next())
            {
                ListaModelo.addElement(resultSet.getString("Chofer"));
            }
            resultSet.close();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }

        return  ListaModelo;
    }

    public static ResultSet busquedaChofer(FrmEntradas frmEntradas, Entradas entradas)
    {
        Connection conn;
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;

        try
        {
            conn = Conexion.getConnection();
            preparedStatement = conn.prepareStatement(SQL_SELECT_BUSQUEDACHOFER);
            preparedStatement.setString(1, entradas.getChofer());
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                frmEntradas.TxtEmpresa.setText(resultSet.getString("Empresa"));
                frmEntradas.TxtPlacas.setText(resultSet.getString("Placas"));
                frmEntradas.TxtTractoCamion.setText(resultSet.getString("TractoCamion"));
            }
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return resultSet;
    }
}
