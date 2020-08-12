package Tools.DataBase;

import Tools.Config;

import java.sql.SQLException;

public class Transporte
{
    private String Chofer = "";
    private String Empresa = "";
    private String Placas = "";
    private String TractoCamion = "";
    private final Conexion conn = Conexion.getInstance();

    public Transporte() throws SQLException, Config.ReadException, Config.EmptyProperty {
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
        return this.conn.insertarChofer(this);
    }
}
