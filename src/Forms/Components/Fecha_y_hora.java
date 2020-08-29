package Forms.Components;

import Tools.Fecha;
import Tools.Hora;

import javax.swing.*;

public class Fecha_y_hora implements Runnable{

    // -- VARIABLES Y CONSTANTES:
    private JLabel date_lb;

    private Hora hora = null;
    private Fecha fecha = null;

    // -- CONSTRUCTOR:

    public Fecha_y_hora(JLabel date_lb)
    {
        this.date_lb = date_lb;
        this.hora = new Hora();
        this.fecha = new Fecha();
    }

    // -- METODOS DE LA CLASE:

    public String getFechaString()
    {
        return this.fecha.toString();
    }

    public String getHoraString()
    {
        return this.hora.toString();
    }

    // -- GET'S Y SET'S:
    public JLabel getDate_lb() {
        return date_lb;
    }

    public void setDate_lb(JLabel date_lb) {
        this.date_lb = date_lb;
    }

    public Hora getHora() {
        return hora;
    }

    public void setHora(Hora hora) {
        this.hora = hora;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

// -- METODOS OVERRIDE:

    @Override
    public void run() {
        this.fecha.adjustTimestamp();
        this.hora.adjustTimestamp();
        this.date_lb.setText( this.getFechaString()+ " " + this.getHoraString() );
    }
}
