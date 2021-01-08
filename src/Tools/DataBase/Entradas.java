package Tools.DataBase;

import Forms.Principal.Entradas.Panels.InsertarEntradas;
import Tools.Config;
import Tools.Fecha;
import Tools.Hora;

import javax.swing.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class Entradas
{
    private int id = 0;
    private int cantidadPallet = 0;
    private int cantidadPorPallet = 0;
    private int totalUnidades = 0;
    private int numOrden = 0;
    private int numPedido = 0;

    private Fecha   fechaEntrada = null;
    private Hora    horaEntrada = null;

    private String diseno = "";
    private String codigoBarras = "";
    private String codigoInterno = "";
    private String cliente = "";
    private String producto = "";
    private String condicion = "";
    private String observaciones = "";

    private Transporte transporte = null;

    // -- CONSTRUCTOR:
    public Entradas(){



    }

    // -- GET'S Y SET'S:


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getNumOrden() {
        return numOrden;
    }

    public void setNumOrden(int numOrden) {
        this.numOrden = numOrden;
    }

    public int getNumPedido() {
        return numPedido;
    }

    public void setNumPedido(int numPedido) {
        this.numPedido = numPedido;
    }

    public Fecha getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Fecha fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Hora getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Hora horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getDiseno() {
        return diseno;
    }

    public void setDiseno(String diseno) {
        this.diseno = diseno;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
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

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Transporte getTransporte() {
        return transporte;
    }

    public void setTransporte(Transporte transporte) {
        this.transporte = transporte;
    }

    public void setTransporte(String idTransporte) {
        this.buscaTransporte( idTransporte );
    }

    // -- METODOS DEL MODELO:

    private void buscaTransporte( String id )
    {
            this.transporte = Transporte.getTransporteByID( id );
        if (this.transporte == null)
        {
            JOptionPane.showMessageDialog(null,
                    "No se encontr\u00f3 el transporte indicado, compruebe que " +
                            "realmente le transporte este" +
                            "dado de alta en el sistema",
                    "Aviso!",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public int insertarEntrada()
    {

        try {
            Conexion conn = Conexion.getInstance();
            return conn.insertarEntradas(this);
        } catch (SQLException |Config.ReadException|Config.EmptyProperty throwables) {
            return -1;
        }
    }


    public static ArrayList<Entradas> getEntradasPorRango(Date from, Date to)
    {
        try {
            Conexion conn = Conexion.getInstance();
            ArrayList<Entradas> list = conn.getEntradasByRangeDate(from, to);
            return list;
        } catch (Config.ReadException | Config.EmptyProperty | SQLException throwables) {
            return null;
        }

    }


    public static DefaultComboBoxModel obtenerCodigoBarras()
    {
        try {

            Conexion conn = Conexion.getInstance();
            return conn.obtenerCodigoBarras();
        } catch (SQLException |Config.ReadException|Config.EmptyProperty throwables) {
            return null;
        }

    }

    public static void busquedaCodigoBarras(InsertarEntradas tab, String codigoDeBarras)
    {
        try {
            Conexion conn = Conexion.getInstance();

            Inventario result =  Inventario.getInventario(codigoDeBarras);

           if(result != null)
           {
               tab.getDISENO_TXT().setText(String.valueOf(result.getDiseno()));
               tab.getCLIENTE_TXT().setText(result.getCliente());
               tab.getPRODUCTO_TXT().setText(result.getProducto());

               tab.getCODIGO_INTERNO_TXT().setText(String.valueOf(result.getCodigoInterno()));
               tab.getCANT_POR_PALETT_TXT().setText(String.valueOf(result.getStockPiezas()));
           }

        } catch (SQLException |Config.ReadException|Config.EmptyProperty throwables) {
            return;
        }
    }

    public static DefaultComboBoxModel obtenerChofer()
    {
        try {
            Conexion conn = Conexion.getInstance();
            return conn.obtenerChofer();
        } catch (SQLException |Config.ReadException|Config.EmptyProperty throwables) {
            return null;
        }
    }

    public static void busquedaChofer(InsertarEntradas tab, String idChofer)
    {

            Transporte result = Transporte.getTransporteByID(idChofer);
            if (result != null)
            {
                tab.getEMPRESA_TXT().setText(result.getEmpresa());
                tab.getPLACAS_TXT().setText(result.getPlacas());
                tab.getTRACTO_TXT().setText(result.getTractoCamion());
            }
            else
            {
                JOptionPane.showMessageDialog(null,
                        "No se encontr\u00f3 el transporte indicado, compruebe que " +
                                "realmente le transporte este" +
                                "dado de alta en el sistema",
                        "Aviso!",
                        JOptionPane.WARNING_MESSAGE);
            }


    }

}
