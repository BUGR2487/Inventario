package Tools;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Hora extends Time {

    private static DateFormat format = new SimpleDateFormat("hh:mm:ss aa");

    public Hora() {
        super(Calendar.getInstance().getTimeInMillis());
    }

    public Hora(long time) {
        super(time);
    }

    public void adjustTimestamp()
    {
        this.setTime(Calendar.getInstance().getTimeInMillis());
    }

    public static long parseTime(String s) {
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
        return this.format.format(this.getTime());
    }

}
