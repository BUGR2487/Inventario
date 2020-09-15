package Tools.DataBase;

import Forms.Principal.Salidas.PanelSalidas;
import Tools.Config;
import Tools.Fecha;
import Tools.Hora;

import javax.swing.*;
import java.sql.SQLException;

public class Salidas
{
    private int id = 0;
    private String numPedido = "";

    private int sellos = 0;
    private int cantidadPallet = 0;
    private int cantidadPorPallet = 0;
    private int totalUnidades = 0;

    private Fecha fechaSalida = null;
    private Hora  horaSalida = null;

    private String observaciones = "";
    private String condicion = "";

    private Transporte transporte = null;

    public Salidas(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(String numPedido) {
        this.numPedido = numPedido;
    }

    public int getSellos() {
        return sellos;
    }

    public void setSellos(int sellos) {
        this.sellos = sellos;
    }

    public int getCantidadPallet() {
        return cantidadPallet;
    }

    public void setCantidadPallet(int cantidadPallet) {
        this.cantidadPallet = cantidadPallet;
    }

    public int getCantidadPorPallet() {
        return cantidadPorPallet;
    }

    public void setCantidadPorPallet(int cantidadPorPallet) {
        this.cantidadPorPallet = cantidadPorPallet;
    }

    public int getTotalUnidades() {
        return totalUnidades;
    }

    public void setTotalUnidades(int totalUnidades) {
        this.totalUnidades = totalUnidades;
    }

    public Fecha getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Fecha fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Hora getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Hora horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public Transporte getTransporte() {
        return transporte;
    }

    public void setTransporte(Transporte transporte) {
        this.transporte = transporte;
    }

    public void setTransporte(String id) {
        this.transporte = Transporte.getTransporteByID( id );
    }

    public int insertarSalidas()
    {
        try {
            Conexion conn = Conexion.getInstance();
            return conn.insertarSalidas(this);
        } catch (SQLException |Config.ReadException|Config.EmptyProperty throwables) {
            return -1;
        }
    }


    public static DefaultComboBoxModel obtenerPedidos()
    {
        try{
            Conexion conn = Conexion.getInstance();
            return conn.obtenerPedidos();
        } catch (SQLException |Config.ReadException|Config.EmptyProperty throwables) {
            return null;
        }
    }

    public static void busquedaNumPedido(PanelSalidas frmSalidas)
    {
        try{
            Conexion conn = Conexion.getInstance();
            conn.busquedaNumPedido(frmSalidas, (String)frmSalidas.getN_PEDIDO_CMB().getSelectedItem());
        } catch (SQLException |Config.ReadException|Config.EmptyProperty throwables) {
            return;
        }
    }


}
