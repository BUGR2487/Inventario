package Tools.DataBase;

import java.sql.SQLException;

public class Inventario
{
    private String CodigoBarras = "";
    private int Diseno = 0;
    private String CodigoInterno = "";
    private String Cliente = "";
    private String Producto = "";
    private int CantidadPorPallet = 0;

    private int EntradaPallet = 0;
    private int SalidaPallet = 0;
    private int EntradaPiezas = 0;
    private int SalidaPiezas = 0;
    private int StockPallets = 0;
    private int StockPiezas = 0;

    private Conexion conn = Conexion.getInstance();

    public Inventario() throws SQLException {
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

    public int getCantidadPorPallet() {
        return CantidadPorPallet;
    }

    public void setCantidadPorPallet(int cantidadPorPallet) {
        CantidadPorPallet = cantidadPorPallet;
    }

    public int getEntradaPallet() {
        return EntradaPallet;
    }

    public void setEntradaPallet(int entradaPallet) {
        EntradaPallet = entradaPallet;
    }

    public int getSalidaPallet() {
        return SalidaPallet;
    }

    public void setSalidaPallet(int salidaPallet) {
        SalidaPallet = salidaPallet;
    }

    public int getEntradaPiezas() {
        return EntradaPiezas;
    }

    public void setEntradaPiezas(int entradaPiezas) {
        EntradaPiezas = entradaPiezas;
    }

    public int getSalidaPiezas() {
        return SalidaPiezas;
    }

    public void setSalidaPiezas(int salidaPiezas) {
        SalidaPiezas = salidaPiezas;
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

    public int insertarInventario()
    {
        return this.conn.insertarInventario(this);
    }

}
