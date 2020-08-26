package Forms.Components;

import javax.swing.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Fecha_y_hora implements Runnable{

    // -- VARIABLES Y CONSTANTES:
    private final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");

    private JLabel date_lb;

    private int hora        = 0;
    private int minutos     = 0;
    private int segundos    = 0;

    private int dia         = 0;
    private int mes         = 0;
    private int year        = 0;

    private String am_pm;

    private String stringFormat;

    // -- CONSTRUCTOR:

    public Fecha_y_hora(JLabel date_lb)
    {
        this.date_lb = date_lb;
    }

    // -- METODOS DE LA CLASE:

    public String getFecha()
    {
        return String.format("%1$s/%2$s/%3$s", this.dia, this.mes, this.year);
    }

    public String getHoraString()
    {
        return String.format("%1$s:%2$s:%3$s %4$s", this.hora, this.minutos, this.segundos,this.am_pm);
    }

    // -- GET'S Y SET'S:

    public DateFormat getDATE_FORMAT() {
        return DATE_FORMAT;
    }

    public JLabel getDate_lb() {
        return date_lb;
    }

    public void setDate_lb(JLabel date_lb) {
        this.date_lb = date_lb;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getStringFormat() {
        return stringFormat;
    }

    public void setStringFormat(String stringFormat) {
        this.stringFormat = stringFormat;
    }


    // -- METODOS OVERRIDE:

    @Override
    public void run() {
        Calendar calendario = Calendar.getInstance();

        this.hora           = calendario.get( Calendar.HOUR_OF_DAY ) - 12;
        this.minutos        = calendario.get( Calendar.MINUTE );
        this.segundos       = calendario.get( Calendar.SECOND );

        this.am_pm          = (calendario.get(Calendar.AM_PM) == Calendar.AM)? "AM":"PM";

        this.dia            = calendario.get( Calendar.DAY_OF_WEEK_IN_MONTH );
        this.mes            = calendario.get( Calendar.MONTH ) + 1;
        this.year           = calendario.get( Calendar.YEAR );

        this.stringFormat   = DATE_FORMAT.format(calendario.getTime());

        this.date_lb.setText( this.stringFormat );

    }
}
