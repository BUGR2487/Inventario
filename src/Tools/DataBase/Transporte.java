package Tools.DataBase;

import Tools.Config;

import java.sql.SQLException;

public class Transporte
{
    private int id = 0;
    private String Chofer = "";
    private String Empresa = "";
    private String Placas = "";
    private String TractoCamion = "";


    public Transporte(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int insertar()
    {
        try {
            Conexion conn = Conexion.getInstance();
            return conn.insertarChofer(this);
        }  catch (SQLException |Config.ReadException|Config.EmptyProperty throwables) {
            return -1;
        }
    }

    public static Transporte getTransporteByID(String idChofer)
    {
        try {
            Conexion conn = Conexion.getInstance();
            Transporte result = conn.busquedaChofer(idChofer);
            return result;
        } catch (SQLException |Config.ReadException|Config.EmptyProperty throwables) {
            return null;
        }

    }
}
