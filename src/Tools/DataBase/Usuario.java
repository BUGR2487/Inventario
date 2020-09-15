package Tools.DataBase;

import Tools.Config;

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


    public Usuario(){
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
        try {
            Conexion conn = Conexion.getInstance();
            return conn.insertarUsuario(this);
        } catch (SQLException |Config.ReadException|Config.EmptyProperty throwables) {
            return -1;
        }
    }

    public int actualizar()
    {
        try {
            Conexion conn = Conexion.getInstance();
            return conn.actualizarUsuario(this);
        }catch (SQLException |Config.ReadException|Config.EmptyProperty throwables) {
            return -1;
        }
    }

    public  int eliminarUsuario()
    {
        try {
            Conexion conn = Conexion.getInstance();
            return conn.eliminarUsuario(this.getCorreoElectronico());
        }catch (SQLException |Config.ReadException|Config.EmptyProperty throwables) {
            return -1;
        }
    }


}