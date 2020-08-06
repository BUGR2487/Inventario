package Tools.DataBase;

import java.sql.SQLException;

public class Usuario
{
    private int idUsuario = 0;
    private String Nombre = "";
    private String ApellidoPaterno = "";
    private String ApellidoMaterno = "";
    private String Puesto = "";
    private String CorreoElectronico = "";
    private String Contrasena = "";
    private String Contrasena2 = "";

    private Conexion conn = Conexion.getInstance();


    private static final String SQL_SELECT = "SELECT * FROM inventario.usuario;";
    private static final String SQL_SELECT_ID = "SELECT IdUsuario FROM inventario.usuario WHERE CorreoElectronico = ?;";
    private static final String SQL_INSERT = "INSERT INTO `inventario`.`usuario` (`Nombre`, `ApellidoPaterno`, `ApellidoMaterno`, `Puesto`, `CorreoElectronico`, `Contrasena`) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String SQL_UPDATE = "UPDATE `inventario`.`usuario` SET `Nombre` = ?, `ApellidoPaterno` = ?, `ApellidoMaterno` = ?, `Puesto` = ?, `CorreoElectronico` = ?, `Contrasena` = ? WHERE (`IdUsuario` = ?);";
    private static final String SQL_DELETE = "DELETE FROM `inventario`.`usuario` WHERE (`CorreoElectronico` = ?);";

    public Usuario() throws SQLException {
    }

    public int getIdUsuario() { return idUsuario; }

    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNombre()
    {
        return Nombre;
    }

    public void setNombre(String nombre)
    {
        Nombre = nombre;
    }

    public String getApellidoPaterno()
    {
        return ApellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno)
    {
        ApellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno()
    {
        return ApellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno)
    {
        ApellidoMaterno = apellidoMaterno;
    }

    public String getPuesto()
    {
        return Puesto;
    }

    public void setPuesto(String puesto)
    {
        Puesto = puesto;
    }

    public String getCorreoElectronico()
    {
        return CorreoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico)
    {
        CorreoElectronico = correoElectronico;
    }

    public String getContrasena()
    {
        return Contrasena;
    }

    public void setContrasena(String contrasena)
    {
        Contrasena = contrasena;
    }

    public String getContrasena2() { return Contrasena2; }

    public void setContrasena2(String contrasena2) { Contrasena2 = contrasena2; }

    @Override
    public String toString() {
        return "Tools.DataBase.Usuario{" +
                "idUsuario=" + idUsuario +
                ", Nombre='" + Nombre + '\'' +
                ", ApellidoPaterno='" + ApellidoPaterno + '\'' +
                ", ApellidoMaterno='" + ApellidoMaterno + '\'' +
                ", Puesto='" + Puesto + '\'' +
                ", CorreoElectronico='" + CorreoElectronico + '\'' +
                ", Contrasena='" + Contrasena + '\'' +
                '}';
    }

    // -- metodos de la clase

    public int insertar()
    {
        return this.conn.insertarUsuario(this);
    }

    public int actualizar()
    {
        return this.conn.actualizarUsuario(this);
    }

    public  int eliminarUsuario()
    {
        return this.conn.eliminarUsuario(this.getCorreoElectronico());
    }


}