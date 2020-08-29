package Tools.DataBase;

import Tools.Config;

import java.sql.SQLException;

public class Inventario
{
    private int id = 0;
    private int StockPallets = 0;
    private int StockPiezas = 0;

    private String CodigoBarras = "";
    private String Diseno = "";
    private String CodigoInterno = "";
    private String Cliente = "";

    private String Producto = "";


    public Inventario(){
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStockPallets() {
        return StockPallets;
    }

    public void setStockPallets(int stockPallets) {
        StockPallets = stockPallets;
    }

    public int getStockPiezas() {
        return StockPiezas;
    }

    public void setStockPiezas(int stockPiezas) {
        StockPiezas = stockPiezas;
    }

    public String getCodigoBarras() {
        return CodigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        CodigoBarras = codigoBarras;
    }

    public String getDiseno() {
        return Diseno;
    }

    public void setDiseno(String diseno) {
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

    public int insertarInventario()
    {
        try {
            Conexion conn = Conexion.getInstance();
            return conn.insertarInventario( this );
        } catch (SQLException |Config.ReadException|Config.EmptyProperty throwables) {
            return -1;
        }
    }

    public static Inventario getInventario(String codigoDeBarras)
    {
        try {
            Conexion conn = Conexion.getInstance();
            return conn.getInventario(codigoDeBarras);
        } catch (SQLException |Config.ReadException|Config.EmptyProperty throwables) {
            return null;
        }
    }

}
