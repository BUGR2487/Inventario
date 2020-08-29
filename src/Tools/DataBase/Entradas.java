package Tools.DataBase;

import Forms.Principal.Panels.PanelEntradas;
import Tools.Config;
import Tools.Fecha;
import Tools.Hora;

import javax.swing.*;
import java.sql.SQLException;

public class Entradas
{

    private Fecha FechaEntrada = null;
    private Hora HoraEntrada = null;

    private String Diseno = "";
    private String CodigoBarras = "";
    private String CodigoInterno = "";
    private String Cliente = "";
    private String Producto = "";

    private int CantidadPallet = 0;
    private int CantidadPorPallet = 0;
    private int TotalUnidades = 0;
    private int NumOrden = 0;
    private int NumPedido = 0;
    private int Folio = 0;

    private String Condicion = "";
    private String Observaciones = "";

    private Transporte transporte = null;

    // -- CONSTRUCTOR:
    public Entradas(){



    }

    // -- GET'S Y SET'S:


    public Fecha getFechaEntrada() {
        return FechaEntrada;
    }

    public void setFechaEntrada(Fecha fechaEntrada) {
        FechaEntrada = fechaEntrada;
    }

    public Hora getHoraEntrada() {
        return HoraEntrada;
    }

    public void setHoraEntrada(Hora horaEntrada) {
        HoraEntrada = horaEntrada;
    }

    public String getDiseno() {
        return Diseno;
    }

    public void setDiseno(String diseno) {
        Diseno = diseno;
    }

    public String getCodigoBarras() {
        return CodigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        CodigoBarras = codigoBarras;
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

    public int getFolio() {
        return Folio;
    }

    public void setFolio(int folio) {
        Folio = folio;
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

    public static DefaultComboBoxModel obtenerCodigoBarras()
    {
        try {

            Conexion conn = Conexion.getInstance();
            return conn.obtenerCodigoBarras();
        } catch (SQLException |Config.ReadException|Config.EmptyProperty throwables) {
            return null;
        }

    }

    public static void busquedaCodigoBarras(PanelEntradas tab, String codigoDeBarras)
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

    public static void busquedaChofer(PanelEntradas tab, String idChofer)
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
