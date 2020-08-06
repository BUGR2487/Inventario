package Tools.DataBase;

import Forms.FrmEntradas;
import Forms.FrmSalidas;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

/***
 * Clase encargada de realizar la conexion a la base de datos en mysql.
 *
 *
 * @author Imsoft
 */
public class Conexion
{
    //instacia para hacer el singleton:
    private static Conexion instance = null;

    //variables privadas y estaticas que configuran la conexion a la base de datos:
    private static final String JDBC_NOMBRE_BD = "inventario";
    private static final String JDBC_URL = "jdbc:mysql://localhost:8889/" + JDBC_NOMBRE_BD + "?useSSL=false&serverTimezone=UTC";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "root";

    //Querys:

        // -- entradas:

        private static final String SQL_INSERT_ENTRADAS = "INSERT INTO `inventario`.`entradas` (`NoOrden`, `NoPedido`, `FechaEntrada`, `HoraEntrada`, `CodigoBarras`, `Diseno`, `CodigoInterno`, `Cliente`, `Folio`, `Producto`, `CantidadPallet`, `CantidadPorPallet`, `TotalUnidades`, `Condicion`, `Observaciones`, `Chofer`, `Empresa`, `Placas`, `TractoCamion`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        private static final String SQL_SELECT_CODIGOBARRAS = "SELECT CodigoBarras FROM inventario.inventario;";
        private static final String SQL_SELECT_BUSQUEDACODIGOBARRAS = "SELECT Folio, Diseno, CodigoInterno, Cliente, Producto, CantidadPorPallet FROM inventario.entradas WHERE CodigoBarras=?;";
        private static final String SQL_SELECT_CHOFER = "SELECT Chofer FROM inventario.transporte;";
        private static final String SQL_SELECT_BUSQUEDACHOFER = "SELECT Empresa, Placas, TractoCamion FROM inventario.transporte WHERE Chofer=?;";

        // -- login:

        private static final String SQL_SELECT_LOGIN = "SELECT * FROM inventario.usuario WHERE CorreoElectronico=? AND Contrasena=?;";

        // -- inventario:

        private static final String SQL_INSERT_INVENTARIO = "INSERT INTO `inventario`.`inventario` (`CodigoBarras`, `Diseno`, `CodigoInterno`, `Cliente`, `CantidadPorPallet`, `Producto`) VALUES  (?, ?, ?, ?, ?, ?);";

        // -- salidas:

        private static final String SQL_INSERT_SALIDAS = "INSERT INTO `inventario`.`salidas` (`NoPedido`, `Sellos`, `CantidadPallet`, `CantidadPorPallet`, `TotalUnidades`, `FechaEntrega`, `Chofer`, `Empresa`, `Placas`, `TractoCamion`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        private static final String SQL_SELECT_NOPEDIDO = "SELECT NoPedido FROM inventario.entradas;";
        private static final String SQL_SELECT_BUSQUEDANOPEDIDO = "SELECT CantidadPallet, CantidadPorPallet, TotalUnidades, Chofer, Empresa, Placas, TractoCamion FROM inventario.entradas WHERE NoPedido=?;";

        // -- transporte:
        private static final String SQL_INSERT_TRANSPORT = "INSERT INTO `inventario`.`transporte` (`Chofer`, `Empresa`, `Placas`, `TractoCamion`) VALUES (?, ?, ?, ?);";

        // -- usuario:
        private static final String SQL_SELECT_USUARIO = "SELECT * FROM inventario.usuario WHERE CorreoElectronico = ?;";
        private static final String SQL_SELECT_ID_USUARIO = "SELECT IdUsuario FROM inventario.usuario WHERE CorreoElectronico = ?;";
        private static final String SQL_INSERT_USUARIO = "INSERT INTO `inventario`.`usuario` (`Nombre`, `ApellidoPaterno`, `ApellidoMaterno`, `Puesto`, `CorreoElectronico`, `Contrasena`) VALUES (?, ?, ?, ?, ?, ?);";
        private static final String SQL_UPDATE_USUARIO = "UPDATE `inventario`.`usuario` SET `Nombre` = ?, `ApellidoPaterno` = ?, `ApellidoMaterno` = ?, `Puesto` = ?, `CorreoElectronico` = ?, `Contrasena` = ? WHERE (`IdUsuario` = ?);";
        private static final String SQL_DELETE_USUARIO = "DELETE FROM `inventario`.`usuario` WHERE (`CorreoElectronico` = ?);";


    //variables privadas de la clase:
    private Connection conn = null;

    private Conexion() throws SQLException {
        this.conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

   //Metodos estaticos:
        public static Conexion getInstance() throws SQLException {
            if (instance == null)
                instance = new Conexion();
            return instance;
        }

        // -- Usuarios:
        public static Usuario seleccionarIdUsuario(String mail) throws SQLException {
            return getInstance().getUsuario(mail);
        }


//metodos pertenecientes a la clase

    // -- Login

    public String busquedaUsuario(IniciarSesion iniciarSesion)
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try
        {
            preparedStatement = conn.prepareStatement(SQL_SELECT_LOGIN);
            preparedStatement.setString(1, iniciarSesion.getUsername());
            preparedStatement.setString(2, iniciarSesion.getPassword());
            resultSet = preparedStatement.executeQuery();
            return resultSet.getString("Nombre") + " " + resultSet.getString("ApellidoPaterno") + " " + resultSet.getString("ApellidoMaterno");
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
            return "";
        }
        finally {
            if(preparedStatement != null){try{preparedStatement.close();}catch (Exception e){}}
            if(resultSet != null){try{resultSet.close();}catch (Exception e1){}}
        }
    }

    // -- Entradas
    public int insertarEntradas(Entradas entradas)
    {
        PreparedStatement preparedStatement = null;
        int rows = 0;
        try
        {
            preparedStatement = conn.prepareStatement(SQL_INSERT_ENTRADAS);
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

            rows = preparedStatement.executeUpdate();
            return rows;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace(System.out);
            return -1;
        }
        finally
        {
            if (preparedStatement != null){try{preparedStatement.close();}catch (Exception e){}}
        }

    }

    private  ArrayList<String> __consultaCodigoBarras()
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<String> list = new ArrayList<>();

        try
        {
            preparedStatement = conn.prepareStatement(SQL_SELECT_CODIGOBARRAS);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                String codigoBarras = resultSet.getString("CodigoBarras");
                list.add(codigoBarras);
            }

            return list;
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
            return null;
        }
        finally {
            if (resultSet != null){try{resultSet.close();}catch(Exception e){}}
        }

    }

    public DefaultComboBoxModel obtenerCodigoBarras()
    {
        DefaultComboBoxModel ListaModelo = new DefaultComboBoxModel();
        ListaModelo.addElement("");
        ArrayList<String> list = this.__consultaCodigoBarras();

        if(list != null)
        {
            for(String element : list)
            {
                ListaModelo.addElement(element);
            }
        }

        return  ListaModelo;
    }

    public void busquedaCodigoBarras(FrmEntradas frmEntradas, String codigoBarras)
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try
        {
            preparedStatement = conn.prepareStatement(SQL_SELECT_BUSQUEDACODIGOBARRAS);
            preparedStatement.setString(1, codigoBarras);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                frmEntradas.TxtFolio.setText(resultSet.getString("Folio"));
                frmEntradas.TxtDiseno.setText(resultSet.getString("Diseno"));
                frmEntradas.TxtCodigoInterno.setText(resultSet.getString("CodigoInterno"));
                frmEntradas.TxtCliente.setText(resultSet.getString("Cliente"));
                frmEntradas.TxtProducto.setText(resultSet.getString("Producto"));
                frmEntradas.TxtCantidadPorPallet.setText(resultSet.getString("CantidadPorPallet"));
            }
            return;
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
            return;
        }finally {
            if(preparedStatement != null){try{preparedStatement.close();}catch (Exception e){}}
            if(resultSet != null){try{resultSet.close();}catch (Exception e1){}}
        }


    }

    private ArrayList<String> __consultaChofer()
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<String> list = new ArrayList<String>();

        try
        {
            preparedStatement = conn.prepareStatement(SQL_SELECT_CHOFER);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                String chofer = resultSet.getString("Chofer");

                list.add(chofer);
            }

            return list;
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
            return null;
        }
        finally {
            if(preparedStatement != null){try{preparedStatement.close();}catch (Exception e){}}
            if(resultSet != null){try{resultSet.close();}catch (Exception e1){}}
        }

    }

    public DefaultComboBoxModel obtenerChofer()
    {
        DefaultComboBoxModel ListaModelo = new DefaultComboBoxModel();
        ListaModelo.addElement("");
        ArrayList<String> choferes = this.__consultaChofer();

        if(choferes == null)
            return  ListaModelo;

            for(String chofer : choferes)
            {
                ListaModelo.addElement(chofer);
            }
            return  ListaModelo;
    }

    public void busquedaChofer(FrmEntradas frmEntradas, String chofer)
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try
        {
            preparedStatement = conn.prepareStatement(SQL_SELECT_BUSQUEDACHOFER);
            preparedStatement.setString(1, chofer);
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
        finally {
            if(preparedStatement != null){try{preparedStatement.close();}catch (Exception e){}}
            if(resultSet != null){try{resultSet.close();}catch (Exception e1){}}
        }
    }

    // -- Inventario:
    public int insertarInventario(Inventario inventario)
    {
        PreparedStatement preparedStatement = null;
        int rows = 0;

        try
        {
            preparedStatement = conn.prepareStatement(SQL_INSERT_INVENTARIO);
            preparedStatement.setString(1, inventario.getCodigoBarras());
            preparedStatement.setInt(2, inventario.getDiseno());
            preparedStatement.setString(3, inventario.getCodigoInterno());
            preparedStatement.setString(4, inventario.getCliente());
            preparedStatement.setString(5, inventario.getProducto());
            preparedStatement.setInt(6, inventario.getCantidadPorPallet());

            //System.out.println("Ejecutanto query " + SQL_INSERT);
            rows = preparedStatement.executeUpdate();
            //System.out.println("Registros afectados: " + rows);

            return rows;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace(System.out);
            return -1;
        }
        finally
        {
            if(preparedStatement != null){try{preparedStatement.close();}catch (Exception e){}}
        }

    }

    // -- Salidas:
    public int insertarSalidas(Salidas salidas)
    {
        PreparedStatement preparedStatement = null;
        int rows = 0;

        try
        {
            preparedStatement = conn.prepareStatement(SQL_INSERT_SALIDAS);
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

            System.out.println("Ejecutanto query " + SQL_INSERT_SALIDAS);
            rows = preparedStatement.executeUpdate();
            System.out.println("Registros afectados: " + rows);

            return rows;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace(System.out);
            return -1;
        }
        finally
        {
            if(preparedStatement != null){try{preparedStatement.close();}catch (Exception e){}}
        }

    }

    private ArrayList<String> __consultaNumPedido()
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<String> list =  new ArrayList<String>();

        try
        {
            preparedStatement = conn.prepareStatement(SQL_SELECT_NOPEDIDO);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                list.add(resultSet.getString("NoPedido"));
            }
            return list;
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
            return null;
        }
        finally {
            if(preparedStatement != null){try{preparedStatement.close();}catch (Exception e){}}
            if(resultSet != null){try{resultSet.close();}catch (Exception e1){}}
        }

    }

    public DefaultComboBoxModel obtenerPedidos()
    {
        DefaultComboBoxModel ListaModelo = new DefaultComboBoxModel();
        ListaModelo.addElement("");
        ArrayList<String> pedidos = this.__consultaNumPedido();

        if (pedidos != null)
        {
            for(String pedido : pedidos)
            {
                ListaModelo.addElement(pedido);
            }
        }

        return  ListaModelo;
    }

    public void busquedaNumPedido(FrmSalidas frmSalidas, String nPedido)
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try
        {
            preparedStatement = conn.prepareStatement(SQL_SELECT_BUSQUEDANOPEDIDO);
            preparedStatement.setString(1, nPedido);
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
        finally {
            if(preparedStatement != null){try{preparedStatement.close();}catch (Exception e){}}
            if(resultSet != null){try{resultSet.close();}catch (Exception e1){}}
        }

    }

    // -- Tranporte:
    public int insertarChofer(Transporte chofer)
    {
        PreparedStatement preparedStatement = null;
        int rows = 0;

        try
        {
            preparedStatement = conn.prepareStatement(SQL_INSERT_TRANSPORT);

            preparedStatement.setString(1, chofer.getChofer());
            preparedStatement.setString(2, chofer.getEmpresa());
            preparedStatement.setString(3, chofer.getPlacas());
            preparedStatement.setString(4, chofer.getTractoCamion());

            //("Ejecutanto query " + SQL_INSERT_TRANSPORT);
            rows = preparedStatement.executeUpdate();
            //System.out.println("Registros afectados: " + rows);
            return rows;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace(System.out);
            return -1;
        }
        finally
        {
            if(preparedStatement != null){try{preparedStatement.close();}catch (Exception e){}}
        }

    }

    // -- Ususario:
    public int insertarUsuario(Usuario usuario)
    {
        PreparedStatement preparedStatement = null;
        int rows = 0;

        try
        {
            preparedStatement = conn.prepareStatement(SQL_INSERT_USUARIO);
            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, usuario.getApellidoPaterno());
            preparedStatement.setString(3, usuario.getApellidoMaterno());
            preparedStatement.setString(4, usuario.getPuesto());
            preparedStatement.setString(5, usuario.getCorreoElectronico());
            preparedStatement.setString(6, usuario.getContrasena());

            System.out.println("Ejecutanto query " + SQL_INSERT_USUARIO);
            rows = preparedStatement.executeUpdate();
            System.out.println("Registros afectados: " + rows);
            return rows;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace(System.out);
            return -1;
        }
        finally
        {
            if(preparedStatement != null){try{preparedStatement.close();}catch (Exception e){}}
        }

    }

    public int actualizarUsuario(Usuario usuario)
    {
        PreparedStatement preparedStatement = null;
        int rows = 0;

        try
        {
            System.out.println("Ejecutando Query" + SQL_UPDATE_USUARIO);
            preparedStatement = conn.prepareStatement(SQL_UPDATE_USUARIO);
            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, usuario.getApellidoPaterno());
            preparedStatement.setString(3, usuario.getApellidoMaterno());
            preparedStatement.setString(4, usuario.getPuesto());
            preparedStatement.setString(5, usuario.getCorreoElectronico());
            preparedStatement.setString(6, usuario.getContrasena());
            preparedStatement.setString(7, usuario.getCorreoElectronico());

            rows = preparedStatement.executeUpdate();
            System.out.println("Registros actualizados: " + rows);
            return rows;
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace(System.out);

            return -1;
        }
        finally
        {
            if(preparedStatement != null){try{preparedStatement.close();}catch (Exception e){}}
        }


    }

    public int eliminarUsuario(String mail)
    {
        PreparedStatement preparedStatement = null;
        int rows = 0;

        try
        {
            System.out.println("Ejecutando Query" + SQL_DELETE_USUARIO);
            preparedStatement = conn.prepareStatement(SQL_DELETE_USUARIO);

            preparedStatement.setString(1, mail);

            rows = preparedStatement.executeUpdate();
            System.out.println("Registros actualizados: " + rows);
            return rows;
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace(System.out);
            return -1;
        }
        finally
        {
            if(preparedStatement != null){try{preparedStatement.close();}catch (Exception e){}}
        }

    }

    public Usuario getUsuario(String mail)
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Usuario usuario = null;
        try
        {
            preparedStatement = conn.prepareStatement(SQL_SELECT_USUARIO);
            preparedStatement.setString(1, mail);
            System.out.println("Ejecutanto query " + SQL_SELECT_USUARIO);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int idUsuario = resultSet.getInt("id_Usuario");
                System.out.println("ID recibido: " + idUsuario);
                usuario = new Usuario();
                usuario.setIdUsuario(idUsuario);

                return usuario;
            }

            return null;
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace(System.out);
            return null;
        }
        finally
        {
            if(preparedStatement != null){try{preparedStatement.close();}catch (Exception e){}}
            if(resultSet != null){try{resultSet.close();}catch (Exception e1){}}
        }

    }

}
