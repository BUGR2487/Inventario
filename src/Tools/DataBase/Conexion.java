package Tools.DataBase;

import Forms.Principal.Salidas.Panels.InsertarSalidasPanel;
import Tools.Config;
import Tools.Fecha;
import Tools.Hora;

import javax.swing.*;
import java.io.UnsupportedEncodingException;
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
                                "`noOrden`, " +
                                "`noPedido`, " +
                                "`fechaEntrada`, " +
                                "`horaEntrada`, " +
                                "`codigoBarras`, " +
                                "`diseno`, " +
                                "`codigoInterno`, " +
                                "`cliente`, " +
                                "`producto`, " +
                                "`cantidadPallet`, " +
                                "`cantidadPorPallet`, " +
                                "`totalUnidades`, " +
                                "`condicion`, " +
                                "`observaciones`, " +
                                "`idTransporte`)" +
                        "VALUES (null,?," +
                                "?,?,?," +
                                "?,?,?," +
                                "?,?,?," +
                                "?,?,?," +
                                "?,?);";

        private static final String SQL_SELECT_ENTRADAS_POR_RANGOS_DE_FECHA = "SELECT * FROM `entradas` " +
                "WHERE `FechaEntrada` BETWEEN ? AND ? ORDER BY `FechaEntrada` DESC";




        // -- login:

        private static final String SQL_SELECT_LOGIN =
                        "SELECT * FROM inventario.usuario " +
                        "WHERE CorreoElectronico=? AND Contrasena=?;";

        // -- inventario:

        private static final String SQL_SELECT_ALL_INVENTARIO = "SELECT * FROM `inventario`";

        private static final String SQL_SELECT_BUSQUEDACODIGOBARRAS = "SELECT * FROM inventario.inventario " +
                "WHERE codigoBarras=?;";

        private static final String SQL_SELECT_TOTAL_STOCK = "SELECT * FROM `totalstock`";

        private static final String SQL_INSERT_INVENTARIO =
                "INSERT INTO `inventario`(" +
                        "`IdInventario`, " +
                        "`codigoBarras`, " +
                        "`diseno`, " +
                        "`codigoInterno`, " +
                        "`cliente`, " +
                        "`entradasPallets`, " +
                        "`entradaPiezas`, " +
                        "`salidaPallets`, " +
                        "`salidaPiezas`, " +
                        "`stockPallets`, " +
                        "`stockPiezas`, " +
                        "`producto`"+
                        ") " +
                        "VALUES  (null,?,?,?,?,?,?,?,?,?,?,?);";

        // -- salidas:

        private static final String SQL_INSERT_SALIDAS =
                "INSERT INTO `inventario`.`salidas` " +
                        "(" +
                        "`IdSalidas`, " +
                        "`noPedido`, " +
                        "`codigoBarras`, " +
                        "`sellos`, " +
                        "`cantidadPallet`, " +
                        "`cantidadPorPallet`, " +
                        "`totalUnidades`, " +
                        "`fechaSalida`, " +
                        "`horaSalida`, " +
                        "`fechaEntrega`, " +
                        "`diseno`, " +
                        "`codigoInterno`, " +
                        "`producto`, " +
                        "`condicion`, " +
                        "`observaciones`, " +
                        "`cliente`, " +
                        "`idTransporte`" +
                        ")  " +
                        "VALUES (null," +
                        "?,?,?," +
                        "?,?,?," +
                        "?,?,?," +
                        "?,?,?," +
                        "?,?,?," +
                        "?);";



        private static final String SQL_SELECT_FIND_BY_RANGE_DATE = "SELECT * FROM `salidas` WHERE " +
                "`FechaSalida` BETWEEN ? AND ? ORDER BY `FechaSalida` DESC";

        // -- transporte:
        private static final String SQL_INSERT_TRANSPORT =
                        "INSERT INTO `inventario`.`transporte` (" +
                                "`chofer`, " +
                                "`empresa`, " +
                                "`placas`, " +
                                "`tractoCamion`" +
                                ") " +
                        "VALUES (?, ?, ?, ?);";

        private static final String SQL_SELECT_CHOFER = "SELECT chofer FROM inventario.transporte;";

        private static final String SQL_SELECT_BUSQUEDACHOFER =
            "SELECT * FROM inventario.transporte WHERE chofer=?;";

        private static final String SQL_SELECT_BUSQUEDACHOFERID =
            "SELECT * FROM inventario.transporte WHERE IdTransporte=?;";

        // -- usuario:
        private static final String SQL_SELECT_USUARIO =
                "SELECT * FROM inventario.usuario " +
                "WHERE correoElectronico = ?;";

        private static final String SQL_SELECT_ID_USUARIO =
                "SELECT IdUsuario FROM inventario.usuario " +
                "WHERE correoElectronico = ?;";

        private static final String SQL_INSERT_USUARIO =
                "INSERT INTO `inventario`.`usuario` (" +
                        "`nombre`, " +
                        "`apellidoPaterno`, " +
                        "`apellidoMaterno`, " +
                        "`puesto`, " +
                        "`correoElectronico`, " +
                        "`contrasena`" +
                        ") " +
                        "VALUES (?, ?, ?, ?, ?, ?);";

        private static final String SQL_UPDATE_USUARIO =
                "UPDATE `inventario`.`usuario` SET " +
                        "`nombre` = ?, " +
                        "`apellidoPaterno` = ?, " +
                        "`apellidoMaterno` = ?, " +
                        "`puesto` = ?, " +
                        "`correoElectronico` = ?, " +
                        "`contrasena` = ? " +
                        "WHERE (`IdUsuario` = ?);";

        private static final String SQL_DELETE_USUARIO =
                "DELETE FROM `inventario`.`usuario` WHERE (`correoElectronico` = ?);";

        private  static final String SQL_GET_ALL_CB = "SELECT * FROM `codigosdebarras`";
        private  static final String SQL_GET_ALL_CB_EN_STOCK = "SELECT * FROM `productosenstock`";


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
            preparedStatement.setString(2, iniciarSesion.getPassword(true));
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                return resultSet.getString("nombre")
                        + " "
                        + resultSet.getString("apellidoPaterno")
                        + " "
                        + resultSet.getString("apellidoMaterno");
            else
                return "";
        }
        catch (SQLException | UnsupportedEncodingException sqlException)
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
                "INSERT INTO `inventario`.`entradas` (" +
                                "`IdEntradas`, " +

                                "`noOrden`, " +
                                "`noPedido`, " +
                                "`fechaEntrada`, " +

                                "`horaEntrada`, " +
                                "`codigoBarras`, " +
                                "`diseno`, " +

                                "`codigoInterno`, " +
                                "`cliente`, " +
                                "`producto`, " +

                                "`cantidadPallet`, " +
                                "`cantidadPorPallet`, " +
                                "`totalUnidades`, " +

                                "`condicion`, " +
                                "`observaciones`, " +
                                "`idTransporte`)" +
                        "VALUES (null,?," +
                                "?,?,?," +
                                "?,?,?," +
                                "?,?,?," +
                                "?,?,?," +
                                "?,?);"
                * */
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
            preparedStatement = conn.prepareStatement(SQL_GET_ALL_CB);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                String codigoBarras = resultSet.getString(1);
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

    public ArrayList<Entradas> getEntradasByRangeDate(Date from, Date to){

        //SQL_SELECT_ENTRADAS_POR_RANGOS_DE_FECHA
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Entradas> list = new ArrayList<>();

        try
        {
            preparedStatement = conn.prepareStatement(SQL_SELECT_ENTRADAS_POR_RANGOS_DE_FECHA);
            preparedStatement.setDate(1, from);
            preparedStatement.setDate(2, to);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
            {
                Entradas entrada = new Entradas();
                /*

                 1      `IdEntradas`
                 2      `noOrden`
                 3      `noPedido`

                 4      `fechaEntrada`
                 5      `horaEntrada`
                 6      `codigoBarras`

                 7      `diseno`
                 8      `codigoInterno`
                 9      `cliente`

                 10     `producto`
                 11     `cantidadPallet`
                 12     `cantidadPorPallet`

                 13     `totalUnidades`
                 14     `condicion`
                 15     `observaciones`

                 16     `idTransporte`
                */
                entrada.setId( resultSet.getInt( 1 ) );
                entrada.setNumOrden( resultSet.getInt( 2 ) );
                entrada.setNumPedido( resultSet.getInt( 3 ) );

                entrada.setFechaEntrada( new Fecha(resultSet.getDate( 4 ).getTime()) );
                entrada.setHoraEntrada( new Hora(resultSet.getTime( 5 ).getTime()) );
                entrada.setCodigoBarras( resultSet.getString( 6 ) );

                entrada.setDiseno( resultSet.getString( 7 ) );
                entrada.setCodigoInterno( resultSet.getString( 8 ) );
                entrada.setCliente( resultSet.getString( 9 ) );

                entrada.setProducto( resultSet.getString( 10 ) );
                entrada.setCantidadPallet( resultSet.getInt( 11 ) );
                entrada.setCantidadPorPallet( resultSet.getInt( 12 ) );

                entrada.setTotalUnidades( resultSet.getInt( 13 ) );
                entrada.setCondicion( resultSet.getString( 14 ) );
                entrada.setObservaciones( resultSet.getString( 15 ) );

                entrada.setTransporte( Transporte.getTransporteByIDTransporte(
                        String.valueOf(resultSet.getInt( 16 ))
                ) );


                list.add(entrada);
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
                String chofer = resultSet.getString("chofer");

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
                choferBuscado.setEmpresa(resultSet.getString("empresa"));
                choferBuscado.setPlacas(resultSet.getString("placas"));
                choferBuscado.setTractoCamion(resultSet.getString("tractoCamion"));
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
                choferBuscado.setEmpresa(resultSet.getString("empresa"));
                choferBuscado.setPlacas(resultSet.getString("placas"));
                choferBuscado.setTractoCamion(resultSet.getString("tractoCamion"));
                choferBuscado.setChofer(resultSet.getString("chofer"));

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

    public ArrayList<Inventario> loadTableInventario()
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Inventario> productos = new ArrayList<>();

        try
        {
            preparedStatement = conn.prepareStatement(SQL_SELECT_ALL_INVENTARIO);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {

                 /*
             1  `IdInventario`
             2  `codigoBarras`
             3  `diseno`
             4  `codigoInterno`
             5  `entradasPallets`
             6  `entradaPiezas`
             7  `salidaPallets`
             8  `salidaPiezas`
             9  `stockPallets`
             10 `cliente`
             11 `stockPiezas`
             12 `producto`
            */
                Inventario producto = new Inventario();
                producto.setId( resultSet.getInt(1) );
                producto.setCodigoBarras( resultSet.getString(2) );
                producto.setDiseno( resultSet.getString(3) );
                producto.setCodigoInterno( resultSet.getString(4) );
                producto.setCliente( resultSet.getString(5) );
                producto.setEntradasPallets( resultSet.getInt(6) );
                producto.setEntradaPiezas( resultSet.getInt(7) );
                producto.setSalidaPallets( resultSet.getInt(8) );
                producto.setSalidaPiezas( resultSet.getInt(9) );
                producto.setStockPallets( resultSet.getInt(10) );
                producto.setStockPiezas( resultSet.getInt(11) );
                producto.setProducto( resultSet.getString(12) );
                productos.add( producto );
            }

            return productos;
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
            return productos;
        }
        finally {
            if(preparedStatement != null){try{preparedStatement.close();}catch (Exception e){}}
            if(resultSet != null){try{resultSet.close();}catch (Exception e1){}}
        }
    }

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

                 /*

             1  `IdInventario`
             2  `codigoBarras`
             3  `diseno`
             4  `codigoInterno`
             5  `entradasPallets`
             6  `entradaPiezas`
             7  `salidaPallets`
             8  `salidaPiezas`
             9  `stockPallets`
             10 `cliente`
             11 `stockPiezas`
             12 `producto`

            */
                entradaBuscada = new Inventario();

                entradaBuscada.setId( resultSet.getInt(1) );
                entradaBuscada.setCodigoBarras( resultSet.getString(2) );
                entradaBuscada.setDiseno( resultSet.getString(3) );

                entradaBuscada.setCodigoInterno( resultSet.getString(4) );
                entradaBuscada.setCliente( resultSet.getString(5) );

                entradaBuscada.setEntradasPallets( resultSet.getInt(6) );
                entradaBuscada.setEntradaPiezas( resultSet.getInt(7) );
                entradaBuscada.setSalidaPallets( resultSet.getInt(8) );
                entradaBuscada.setSalidaPiezas( resultSet.getInt(9) );


                entradaBuscada.setStockPallets( resultSet.getInt(10) );
                entradaBuscada.setStockPiezas( resultSet.getInt(11) );
                entradaBuscada.setProducto( resultSet.getString(12) );
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

    public String getTotalStock()
    {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try
        {
            preparedStatement = conn.prepareStatement(SQL_SELECT_TOTAL_STOCK);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                StringBuffer bf = new StringBuffer();

                bf.append("Total de Pallets: ").append( resultSet.getInt(1) ).append("\n");
                bf.append("Total de Piezas: ").append( resultSet.getInt(2) ).append("\n");

                return bf.toString();

            }

            return "Total de Stock no disponible...";
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
            return "Total de Stock no disponible...";
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
            /*

                `IdInventario`      - no se cuenta el valor es nulo por default para el auto incrementable.
             1  `codigoBarras`
             2  `diseno`
             3  `codigoInterno`

             4  `cliente`

             5  `entradasPallets`
             6  `entradaPiezas`
             7  `salidaPallets`
             8  `salidaPiezas`

             9  `stockPallets`
             10 `stockPiezas`
             11 `producto

            */

            // (null,?,?,?,?,?,?,?,?,?,?,?);";
            preparedStatement = conn.prepareStatement(SQL_INSERT_INVENTARIO);

            preparedStatement.setString(1, inventario.getCodigoBarras());
            preparedStatement.setString(2, inventario.getDiseno());
            preparedStatement.setString(3, inventario.getCodigoInterno());

            preparedStatement.setString(4, inventario.getCliente());

            preparedStatement.setInt(5, 0);
            preparedStatement.setInt(6, 0);
            preparedStatement.setInt(7, 0);
            preparedStatement.setInt(8, 0);

            preparedStatement.setInt(9, inventario.getStockPallets());
            preparedStatement.setInt(10, inventario.getStockPiezas());

            preparedStatement.setString(11, inventario.getProducto());


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
                            IdSalidas
                       [1]  noPedido
                       [2]  codigoBarras
                       [3]  sellos

                       [4]  cantidadPallet
                       [5]  cantidadPorPallet
                       [6]  totalUnidades

                       [7]  fechaSalida
                       [8]  horaSalida
                       [9]  fechaEntrega

                       [10] diseno
                       [11] codigoInterno
                       [12] producto

                       [13] condicion
                       [14] observaciones
                       [15] cliente

                       [16] idTransporte

            */
            preparedStatement = conn.prepareStatement(SQL_INSERT_SALIDAS);

            preparedStatement.setInt(1, salidas.getNumPedido());
            preparedStatement.setString(2, salidas.getCodigoBarras());
            preparedStatement.setInt(3, salidas.getSellos());

            preparedStatement.setInt(4, salidas.getCantidadPallet());
            preparedStatement.setInt(5, salidas.getCantidadPorPallet());
            preparedStatement.setInt(6, salidas.getTotalUnidades());

            preparedStatement.setDate(7, salidas.getFechaSalida());
            preparedStatement.setTime(8, salidas.getHoraSalida());
            preparedStatement.setDate(9, salidas.getFechaEntrega());

            preparedStatement.setString(10, salidas.getDiseno());
            preparedStatement.setString(11, salidas.getCodigoInterno());
            preparedStatement.setString(12, salidas.getProducto());

            preparedStatement.setString(13, salidas.getCondicion());
            preparedStatement.setString(14, salidas.getObservaciones());
            preparedStatement.setString(15, salidas.getCliente());

            Transporte transporte  = salidas.getTransporte();
            preparedStatement.setInt(16, transporte.getId());



            //System.out.println("Ejecutanto query " + preparedStatement.toString());
            rows = preparedStatement.executeUpdate();
            ////System.out.println("Registros afectados: " + rows);

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


    public Salidas[] getSalidasByRange(Date from, Date to){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = conn.prepareStatement(SQL_SELECT_FIND_BY_RANGE_DATE);
            preparedStatement.setDate(1, from);
            preparedStatement.setDate(2, to);

            //System.out.println(preparedStatement);

            resultSet = preparedStatement.executeQuery();

            ArrayList<Salidas> arr = new ArrayList<>();

                while(resultSet.next()) {

                                /*
                        1  int `IdSalidas`         -> null,
                        2  int `noPedido`          -> ?,
                        3  String `codigoBarras`      -> ?,
                        4  int `sellos`            -> ?,
                        5  int `cantidadPallet`    -> ?,
                        6  int `cantidadPorPallet` -> ?,
                        7  int `totalUnidades`     -> ?,
                        8  Date `fechaSalida`       -> ?,
                        9  Time `horaSalida`        -> ?,
                        10 Date `fechaEntrega`      -> ?,
                        11 String `diseno`            -> ?,
                        12 String `codigoInterno`     -> ?,
                        13 String `producto`          -> ?,
                        14 String `condicion`         -> ?,
                        15 String `observaciones`     -> ?,
                        16 String `cliente`           -> ?,
                        17 int `idTransporte`      -> ?

            */
                    Salidas instacia = new Salidas();

                    instacia.setId(resultSet.getInt(1));
                    instacia.setNumPedido(resultSet.getInt(2));
                    instacia.setCodigoBarras(resultSet.getString(3));

                    instacia.setSellos(resultSet.getInt(4));
                    instacia.setCantidadPallet(resultSet.getInt(5));
                    instacia.setCantidadPorPallet(resultSet.getInt(6));
                    instacia.setTotalUnidades(resultSet.getInt(7));

                    instacia.setFechaSalida(new Fecha(resultSet.getDate(8).getTime()));
                    instacia.setHoraSalida(new Hora(resultSet.getTime(9).getTime()));
                    instacia.setFechaEntrega(resultSet.getDate(10));

                    instacia.setDiseno(resultSet.getString(11));
                    instacia.setCodigoInterno(resultSet.getString(12));
                    instacia.setProducto(resultSet.getString(13));
                    instacia.setCondicion(resultSet.getString(14));
                    instacia.setObservaciones(resultSet.getString(15));
                    instacia.setCliente(resultSet.getString(16));

                    instacia.setTransporte(Transporte
                            .getTransporteByIDTransporte(resultSet
                            .getString(17)));

                    arr.add( instacia );

                }

                return arr.toArray( new Salidas[ arr.size() ]);
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
            return null;
        }
        finally
        {
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
            ////System.out.println("Registros afectados: " + rows);
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
            preparedStatement.setString(6, usuario.getContrasena(true));

            //System.out.println("Ejecutanto query " + SQL_INSERT_USUARIO);
            rows = preparedStatement.executeUpdate();
            //System.out.println("Registros afectados: " + rows);
            return rows;
        }
        catch (SQLException | UnsupportedEncodingException throwables)
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
            //System.out.println("Ejecutando Query" + SQL_UPDATE_USUARIO);
            preparedStatement = conn.prepareStatement(SQL_UPDATE_USUARIO);
            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, usuario.getApellidoPaterno());
            preparedStatement.setString(3, usuario.getApellidoMaterno());
            preparedStatement.setString(4, usuario.getPuesto());
            preparedStatement.setString(5, usuario.getCorreoElectronico());
            preparedStatement.setString(6, usuario.getContrasena(true));
            preparedStatement.setString(7, usuario.getCorreoElectronico());

            rows = preparedStatement.executeUpdate();
            //System.out.println("Registros actualizados: " + rows);
            return rows;
        }
        catch(SQLException | UnsupportedEncodingException sqlException)
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
            //System.out.println("Ejecutando Query" + SQL_DELETE_USUARIO);
            preparedStatement = conn.prepareStatement(SQL_DELETE_USUARIO);

            preparedStatement.setString(1, mail);

            rows = preparedStatement.executeUpdate();
            //System.out.println("Registros actualizados: " + rows);
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

                usuario.setIdUsuario(resultSet.getInt(1));
                usuario.setNombre(resultSet.getString(2));
                usuario.setApellidoPaterno(resultSet.getString(3));
                usuario.setApellidoMaterno(resultSet.getString(4));
                usuario.setPuesto(resultSet.getString(5));
                usuario.setCorreoElectronico(resultSet.getString(6));
                usuario.setContrasena(resultSet.getString(7), false);


                return usuario;
            }

            return null;
        }
        catch (SQLException | UnsupportedEncodingException throwables)
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

    public DefaultComboBoxModel obtenerPedidos() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        DefaultComboBoxModel modelCb = new DefaultComboBoxModel();
        modelCb.addElement("");
       try{
           preparedStatement = conn.prepareStatement(SQL_GET_ALL_CB_EN_STOCK);
           resultSet = preparedStatement.executeQuery();

           while (resultSet.next())
           {
               String cb = resultSet.getString(1);
               modelCb.addElement(cb);
           }

           return modelCb;
       }catch (SQLException throwables)
       {
           throwables.printStackTrace(System.out);
           return modelCb;
       }
       finally
       {
           if(preparedStatement != null){try{preparedStatement.close();}catch (Exception e){}}
           if(resultSet != null){try{resultSet.close();}catch (Exception e1){}}
       }

    }
}
