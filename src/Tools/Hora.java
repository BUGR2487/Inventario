package Tools;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Hora extends Time {

    private DateFormat format = new SimpleDateFormat("HH:mm:ss aa");

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

    @Override
    public String toString()
    {
        return this.format.format(this.getTime());
    }


}
