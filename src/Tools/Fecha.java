package Tools;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Fecha extends Date {

    private DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    public Fecha() {
        super(Calendar.getInstance().getTimeInMillis());
    }

    public Fecha(long date) {
        super(date);
    }


    public void adjustTimestamp()
    {
        this.setTime(Calendar.getInstance().getTimeInMillis());
    }

    @Override
    public String toString()
    {
        return this.format.format(this.getTime());
    }

}
