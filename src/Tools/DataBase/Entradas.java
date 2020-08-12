package Tools.DataBase;

import Forms.FrmEntradas;
import Tools.Config;

import javax.swing.*;
import java.sql.SQLException;

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
    private final Conexion conn = Conexion.getInstance();

    public Entradas() throws SQLException, Config.ReadException, Config.EmptyProperty {
    }

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

    public int insertarEntrada()
    {
        return this.conn.insertarEntradas(this);
    }
    public DefaultComboBoxModel obtenerCodigoBarras()
    {
        return this.conn.obtenerCodigoBarras();
    }
    public void busquedaCodigoBarras(FrmEntradas frmEntradas)
    {
        this.conn.busquedaCodigoBarras(frmEntradas, this.getCodigoBarras());
    }

    public DefaultComboBoxModel obtenerChofer()
    {
        return this.conn.obtenerChofer();
    }

    public void busquedaChofer(FrmEntradas frmEntradas)
    {
        this.conn.busquedaChofer(frmEntradas, this.getChofer());
    }

}
