package Tools.DataBase;

import Forms.Principal.Salidas.PanelSalidas;
import Tools.Config;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.TimeZone;

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

    //Querys:

        // -- entradas:

        private static final String SQL_INSERT_ENTRADAS =
                        "INSERT INTO `inventario`.`entradas` (" +
                                "`IdEntradas`, " +
                                "`NoOrden`, " +
                                "`NoPedido`, " +
                                "`FechaEntrada`, " +
                                "`HoraEntrada`, " +
                                "`CodigoBarras`, " +
                                "`Diseno`, " +
                                "`CodigoInterno`, " +
                                "`Cliente`, " +
                                "`Producto`, " +
                                "`CantidadPallet`, " +
                                "`CantidadPorPallet`, " +
                                "`TotalUnidades`, " +
                                "`Condicion`, " +
                                "`Observaciones`, " +
                                "`idTransporte` )" +
                        "VALUES (null,?," +
                                "?,?,?," +
                                "?,?,?," +
                                "?,?,?," +
                                "?,?,?," +
                                "?,?);";

        private static final String SQL_SELECT_CODIGOBARRAS = "SELECT CodigoBarras FROM inventario.inventario;";

        private static final String SQL_SELECT_CHOFER = "SELECT Chofer FROM inventario.transporte;";

        private static final String SQL_SELECT_BUSQUEDACHOFER =
                "SELECT * FROM inventario.transporte WHERE Chofer=?;";

        private static final String SQL_SELECT_BUSQUEDACHOFERID =
                "SELECT * FROM inventario.transporte WHERE IdTransporte=?;";

        // -- login:

        private static final String SQL_SELECT_LOGIN =
                        "SELECT * FROM inventario.usuario " +
                        "WHERE CorreoElectronico=? AND Contrasena=?;";

        // -- inventario:

        private static final String SQL_SELECT_BUSQUEDACODIGOBARRAS = "SELECT * FROM inventario.inventario " +
                "WHERE CodigoBarras=?;";

        private static final String SQL_INSERT_INVENTARIO =
                "INSERT INTO `inventario`(" +
                        "`IdInventario`, " +
                        "`CodigoBarras`, " +
                        "`Diseno`, " +
                        "`CodigoInterno`, " +
                        "`Cliente`, " +
                        "`StockPallets`, " +
                        "`StockPiezas`, " +
                        "`Producto`) " +
                        "VALUES  (null, ?, ?, ?, ?, ?, ?, ?);";

        // -- salidas:

        private static final String SQL_INSERT_SALIDAS =
                "INSERT INTO `inventario`.`salidas` " +
                        "(`IdSalidas`, " +
                        "`NoPedido`, " +
                        "`Sellos`, " +
                        "`CantidadPallet`, " +
                        "`CantidadPorPallet`, " +
                        "`TotalUnidades`, " +
                        "`FechaSalida`, " +
                        "`HoraSalida`, " +
                        "`observaciones`, " +
                        "`condicion`, " +
                        "`idTransporte`)  " +
                        "VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?,?);";

        private static final String SQL_SELECT_NOPEDIDO = "SELECT NoPedido FROM inventario.entradas;";

        private static final String SQL_SELECT_BUSQUEDANOPEDIDO =
                        "SELECT CantidadPallet,CantidadPorPallet,\n" +
                                "                                TotalUnidades, \n" +
                                "                                idTransporte\n" +
                                "                                FROM entradas \n" +
                                "                                WHERE NoPedido = ? limit 1";

        // -- transporte:
        private static final String SQL_INSERT_TRANSPORT =
                        "INSERT INTO `inventario`.`transporte` (" +
                                "`Chofer`, " +
                                "`Empresa`, " +
                                "`Placas`, " +
                                "`TractoCamion`" +
                                ") " +
                        "VALUES (?, ?, ?, ?);";

        // -- usuario:
        private static final String SQL_SELECT_USUARIO =
                "SELECT * FROM inventario.usuario " +
                "WHERE CorreoElectronico = ?;";

        private static final String SQL_SELECT_ID_USUARIO =
                "SELECT IdUsuario FROM inventario.usuario " +
                "WHERE CorreoElectronico = ?;";

        private static final String SQL_INSERT_USUARIO =
                "INSERT INTO `inventario`.`usuario` (" +
                        "`Nombre`, " +
                        "`ApellidoPaterno`, " +
                        "`ApellidoMaterno`, " +
                        "`Puesto`, " +
                        "`CorreoElectronico`, " +
                        "`Contrasena`" +
                        ") " +
                        "VALUES (?, ?, ?, ?, ?, ?);";

        private static final String SQL_UPDATE_USUARIO =
                "UPDATE `inventario`.`usuario` SET " +
                        "`Nombre` = ?, " +
                        "`ApellidoPaterno` = ?, " +
                        "`ApellidoMaterno` = ?, " +
                        "`Puesto` = ?, " +
                        "`CorreoElectronico` = ?, " +
                        "`Contrasena` = ? " +
                        "WHERE (`IdUsuario` = ?);";

        private static final String SQL_DELETE_USUARIO =
                "DELETE FROM `inventario`.`usuario` WHERE (`CorreoElectronico` = ?);";


    //variables privadas de la clase:
    private Connection conn = null;

    private Conexion() throws SQLException, Config.ReadException, Config.EmptyProperty {
        Config cnf = Config.getInstance();

        String url = cnf.getProperty(Config.JDBC_URL);

        url = url + "&serverTimezone=" + TimeZone.getDefault().getID();

        String user = cnf.getProperty(Config.MYSQL_USER);
        String pass = cnf.getProperty(Config.MYSQL_PASS);

        if(url.isEmpty())
            throw new Config.EmptyProperty("URL vacío");

        this.conn = DriverManager.getConnection(url, user, pass);
    }

   //Metodos estaticos:
        public static Conexion getInstance() throws SQLException, Config.ReadException, Config.EmptyProperty {
            if (instance == null)
                instance = new Conexion();
            return instance;
        }

        // -- Usuarios:
        public static Usuario seleccionarIdUsuario(String mail)
                throws SQLException, Config.ReadException, Config.EmptyProperty {
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
            if(resultSet.next())
                return resultSet.getString("Nombre")
                        + " "
                        + resultSet.getString("ApellidoPaterno")
                        + " "
                        + resultSet.getString("ApellidoMaterno");
            else
                return "";
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
            /*

            "INSERT INTO `inventario`.`entradas` " +
                                "`IdEntradas`, " +
                                "`NoOrden`, " +
                                "`NoPedido`, " +
                                "`FechaEntrada`, " +    //3
                                "`HoraEntrada`, " +
                                "`CodigoBarras`, " +
                                "`Diseno`, " +          //6
                                "`CodigoInterno`, " +
                                "`Cliente`, " +
                                "`Producto`, " +
                                "`CantidadPallet`, " +
                                "`CantidadPorPallet`, " +//12
                                "`TotalUnidades`, " +
                                "`Condicion`, " +
                                "`Observaciones`, " +   //15
                                "`idTransporte` " +     //6
                        "VALUES (null,?," +
                                "?,?,?," +
                                "?,?,?," +
                                "?,?,?," +
                                "?,?,?," +
                                "?,?);";
             */
            preparedStatement = conn.prepareStatement(SQL_INSERT_ENTRADAS);
            preparedStatement.setInt(1, entradas.getNumOrden());
            preparedStatement.setInt(2, entradas.getNumPedido());
            preparedStatement.setDate(3, entradas.getFechaEntrada());

            preparedStatement.setTime(4, Time.valueOf(entradas.getHoraEntrada().toLocalTime()));
            preparedStatement.setString(5, entradas.getCodigoBarras());
            preparedStatement.setString(6, entradas.getDiseno());

            preparedStatement.setString(7, entradas.getCodigoInterno());
            preparedStatement.setString(8, entradas.getCliente());

            preparedStatement.setString(9, entradas.getProducto());
            preparedStatement.setInt(10, entradas.getCantidadPallet());
            preparedStatement.setInt(11, entradas.getCantidadPorPallet());

            preparedStatement.setInt(12, entradas.getTotalUnidades());
            preparedStatement.setString(13, entradas.getCondicion());
            preparedStatement.setString(14, entradas.getObservaciones());

            preparedStatement.setInt(15, entradas.getTransporte().getId());

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

    public Transporte busquedaChofer(String chofer)
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Transporte choferBuscado = null;
        try
        {
            preparedStatement = conn.prepareStatement(SQL_SELECT_BUSQUEDACHOFER);
            preparedStatement.setString(1, chofer);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                choferBuscado = new Transporte();

                choferBuscado.setId( resultSet.getInt("IdTransporte"));
                choferBuscado.setEmpresa(resultSet.getString("Empresa"));
                choferBuscado.setPlacas(resultSet.getString("Placas"));
                choferBuscado.setTractoCamion(resultSet.getString("TractoCamion"));
                choferBuscado.setChofer(chofer);

            }

            return choferBuscado;
        }
        catch (SQLException sqlException)
        {
            return null;
        }finally {
            if(preparedStatement != null){try{preparedStatement.close();}catch (Exception e){}}
            if(resultSet != null){try{resultSet.close();}catch (Exception e1){}}
        }
    }

    public Transporte busquedaChoferById(String id)
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Transporte choferBuscado = null;
        try
        {
            preparedStatement = conn.prepareStatement(SQL_SELECT_BUSQUEDACHOFERID);
            preparedStatement.setString(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                choferBuscado = new Transporte();

                choferBuscado.setId( resultSet.getInt("IdTransporte"));
                choferBuscado.setEmpresa(resultSet.getString("Empresa"));
                choferBuscado.setPlacas(resultSet.getString("Placas"));
                choferBuscado.setTractoCamion(resultSet.getString("TractoCamion"));
                choferBuscado.setChofer(resultSet.getString("Chofer"));

            }

            return choferBuscado;
        }
        catch (SQLException sqlException)
        {
            return null;
        }finally {
            if(preparedStatement != null){try{preparedStatement.close();}catch (Exception e){}}
            if(resultSet != null){try{resultSet.close();}catch (Exception e1){}}
        }
    }

    // -- Inventario:

    public Inventario getInventario(String codigoBarras)
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Inventario entradaBuscada = null;

        try
        {
            preparedStatement = conn.prepareStatement(SQL_SELECT_BUSQUEDACODIGOBARRAS);
            preparedStatement.setString(1, codigoBarras);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                entradaBuscada = new Inventario();

                entradaBuscada.setId( resultSet.getInt(1) );
                entradaBuscada.setCodigoBarras( resultSet.getString(2) );
                entradaBuscada.setDiseno( resultSet.getString(3) );
                entradaBuscada.setCodigoInterno( resultSet.getString(4) );
                entradaBuscada.setCliente( resultSet.getString(5) );
                entradaBuscada.setStockPallets( resultSet.getInt(6) );
                entradaBuscada.setStockPiezas( resultSet.getInt(7) );
                entradaBuscada.setProducto( resultSet.getString(8) );
            }

            return entradaBuscada;
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

    public int insertarInventario(Inventario inventario)
    {
        PreparedStatement preparedStatement = null;
        int rows = 0;

        try
        {

            //(null, ?, ?, ?, ?, ?, ?, ?, ?)
            preparedStatement = conn.prepareStatement(SQL_INSERT_INVENTARIO);
            preparedStatement.setString(1, inventario.getCodigoBarras());
            preparedStatement.setString(2, inventario.getDiseno());
            preparedStatement.setString(3, inventario.getCodigoInterno());
            preparedStatement.setString(4, inventario.getCliente());
            preparedStatement.setInt(5, inventario.getStockPallets());
            preparedStatement.setInt(6, inventario.getStockPiezas());
            preparedStatement.setString(7, inventario.getProducto());

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

            /*
            "INSERT INTO `inventario`.`salidas` " +
                                "(`IdSalidas`, " +
                                "`NoPedido`, " +
                                "`Sellos`, " +
                                "`CantidadPallet`, " +
                                "`CantidadPorPallet`, " +
                                "`TotalUnidades`, " +
                                "`FechaSalida`, " +
                                "`HoraSalida`, " +
                                "`observaciones`, " +
                                "`condicion`, " +
                                "`idTransporte`)  " +
                        "VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?,?);";
            */
            preparedStatement = conn.prepareStatement(SQL_INSERT_SALIDAS);

            preparedStatement.setString(1, salidas.getNumPedido());

            preparedStatement.setInt(2, salidas.getSellos());
            preparedStatement.setInt(3, salidas.getCantidadPallet());
            preparedStatement.setInt(4, salidas.getCantidadPorPallet());
            preparedStatement.setInt(5, salidas.getTotalUnidades());

            preparedStatement.setDate(6, salidas.getFechaSalida());
            preparedStatement.setTime(7, salidas.getHoraSalida());

            preparedStatement.setString(8, salidas.getObservaciones());
            preparedStatement.setString(9, salidas.getCondicion());

            preparedStatement.setInt(10, salidas.getTransporte().getId());

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

    public void busquedaNumPedido(PanelSalidas frmSalidas, String nPedido)
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try
        {
            preparedStatement = conn.prepareStatement(SQL_SELECT_BUSQUEDANOPEDIDO);
            preparedStatement.setInt(1, Integer.valueOf( nPedido ));
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                frmSalidas.getCANT_PALLET_TXT().setText(resultSet.getString("CantidadPallet"));
                frmSalidas.getCANT_POR_PALETT_TXT().setText(resultSet.getString("CantidadPorPallet"));
                frmSalidas.getTOTAL_UNIDADES_TXT().setText(resultSet.getString("TotalUnidades"));

                String idTransporte = resultSet.getString("idTransporte");

                Transporte transporte = Transporte.getTransporteByIDTransporte( idTransporte );

                if (transporte == null)
                {
                    frmSalidas.getCHOFER_TXT().setText("");
                    frmSalidas.getEMPRESA_TXT().setText("");
                    frmSalidas.getPLACAS_TXT().setText("");
                    frmSalidas.getTRACTO_TXT().setText("");
                }
                else {
                    frmSalidas.getCHOFER_TXT().setText( transporte.getChofer() );
                    frmSalidas.getEMPRESA_TXT().setText( transporte.getEmpresa() );
                    frmSalidas.getPLACAS_TXT().setText( transporte.getPlacas() );
                    frmSalidas.getTRACTO_TXT().setText( transporte.getTractoCamion() );
                }
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
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                usuario = new Usuario();

                usuario.setIdUsuario(resultSet.getInt("id_Usuario"));
                usuario.setNombre(resultSet.getString("Nombre"));
                usuario.setApellidoPaterno(resultSet.getString("ApellidoPaterno"));
                usuario.setApellidoMaterno(resultSet.getString("ApellidoMaterno"));
                usuario.setPuesto(resultSet.getString("Puesto"));
                usuario.setCorreoElectronico(resultSet.getString("CorreoElectronico"));
                usuario.setContrasena(resultSet.getString("Contrasena"));


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
