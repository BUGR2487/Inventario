package Tools.DataBase;

import Tools.Config;

import java.sql.SQLException;
import java.util.ArrayList;

public class Inventario
{
    private int id = 0;
    private int StockPallets = 0;
    private int StockPiezas = 0;
    private int entradasPallets = 0;
    private int entradaPiezas = 0;
    private int salidaPallets = 0;
    private int salidaPiezas = 0;

    private String codigoBarras = "";
    private String diseno = "";
    private String codigoInterno = "";
    private String cliente = "";

    private String producto = "";


    public Inventario(){
    }

    public int getEntradasPallets() {
        return entradasPallets;
    }

    public void setEntradasPallets(int entradasPallets) {
        this.entradasPallets = entradasPallets;
    }

    public int getEntradaPiezas() {
        return entradaPiezas;
    }

    public void setEntradaPiezas(int entradaPiezas) {
        this.entradaPiezas = entradaPiezas;
    }

    public int getSalidaPallets() {
        return salidaPallets;
    }

    public void setSalidaPallets(int salidaPallets) {
        this.salidaPallets = salidaPallets;
    }

    public int getSalidaPiezas() {
        return salidaPiezas;
    }

    public void setSalidaPiezas(int salidaPiezas) {
        this.salidaPiezas = salidaPiezas;
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
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getDiseno() {
        return diseno;
    }

    public void setDiseno(String diseno) {
        this.diseno = diseno;
    }

    public String getCodigoInterno() {
        return codigoInterno;
    }

    public void setCodigoInterno(String codigoInterno) {
        this.codigoInterno = codigoInterno;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
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

    public static ArrayList<Inventario> loadTable(){
        try{
            Conexion conn = Conexion.getInstance();
            return conn.loadTableInventario();
        } catch (SQLException |Config.ReadException|Config.EmptyProperty throwables) {
            return null;
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

    public static String getTotalStock()
    {
        try {
            Conexion conn = Conexion.getInstance();
            return conn.getTotalStock();
        } catch (SQLException |Config.ReadException|Config.EmptyProperty throwables) {
            return "Total de Stock no disponible...";
        }
    }

}
