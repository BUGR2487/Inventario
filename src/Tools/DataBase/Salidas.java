package Tools.DataBase;

import Forms.Principal.Salidas.Panels.InsertarSalidasPanel;
import Tools.Config;
import Tools.Fecha;
import Tools.Hora;

import javax.swing.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

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

    private Date fechaEntrega = null;

    private Transporte transporte = null;

    public Salidas(){
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
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

    public static ArrayList<Salidas> getRangoDeSalidasPorFecha(Date from, Date to)
    {
        try {
            Conexion conn = Conexion.getInstance();
            Salidas[] result = conn.getSalidasByRange(from, to);

            if(result != null)
            {
                ArrayList<Salidas> list = new ArrayList<Salidas>();

                for (Salidas s : result)
                {
                    list.add( s );
                }

                return list;
            }
            return null;
        } catch (SQLException | Config.ReadException | Config.EmptyProperty throwables) {
            return null;
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

    public static void busquedaNumPedido(InsertarSalidasPanel frmSalidas)
    {
        try{
            Conexion conn = Conexion.getInstance();
            conn.busquedaNumPedido(frmSalidas, (String)frmSalidas.getN_PEDIDO_CMB().getSelectedItem());
        } catch (SQLException |Config.ReadException|Config.EmptyProperty throwables) {
            return;
        }
    }


}
