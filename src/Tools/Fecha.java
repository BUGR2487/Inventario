package Tools;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Fecha extends Date {

    private static DateFormat format = new SimpleDateFormat("dd/MM/yyyy");



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

    public static long parseDate(String s) {
        try {
            java.util.Date d = format.parse(s);
            long milliseconds = d.getTime();
            return milliseconds;
        } catch (ParseException e) {
            return Calendar.getInstance().getTimeInMillis();
        }
    }

    @Override
    public String toString()
    {
        return format.format(this.getTime());
    }

}
