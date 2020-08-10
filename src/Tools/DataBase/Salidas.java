package Tools.DataBase;

import Forms.FrmSalidas;
import Tools.Config;

import javax.swing.*;
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
    private Conexion conn = Conexion.getInstance();

    public Salidas() throws SQLException, Config.ReadException, Config.EmptyProperty {
    }

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

    public int insertarSalidas()
    {
        return this.conn.insertarSalidas(this);
    }

    public DefaultComboBoxModel obtenerPedidos()
    {
        return this.conn.obtenerPedidos();
    }
    public void busquedaNumPedido(FrmSalidas frmSalidas)
    {
        this.conn.busquedaNumPedido(frmSalidas, this.getNumPedido());
    }


}
