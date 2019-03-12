package minnie.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeStamp {

    private static final int MIN_SECONDS = 60;
    private static final int HOUR_SECONDS = 60 * 60;
    private static final int DAY_SECONDS = 24 * 60 * 60;
    private static final int MONTH_SECONDS = 30 * 24 * 60 * 60;
    private static final int YEAR_SECONDS = 12 * 30 * 24 * 60 * 60;


    public static String getCurrentTime() {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
        df.setTimeZone(TimeZone.getTimeZone("GMT+1"));
        String strDate = df.format(new Date());
        return strDate;
    }

    public static int convertInSeconds(String time) {
        int hoursInSec = Integer.parseInt(time.substring(0, 2)) * HOUR_SECONDS;
        int minInSec = Integer.parseInt(time.substring(3, 5)) * MIN_SECONDS;
        int sec = Integer.parseInt(time.substring(6, 8));
        int dayInSec = Integer.parseInt(time.substring(9, 11)) * DAY_SECONDS;
        int monthsInSec = Integer.parseInt(time.substring(12, 14)) * MONTH_SECONDS;
        int yearInSec = Integer.parseInt(time.substring(15, 19)) * YEAR_SECONDS;

        return hoursInSec + minInSec + sec + dayInSec + monthsInSec + yearInSec;
    }

    // Represents time passed in hours, minutes, seconds
    public static String timePassed(String pastDate) {
        int passedSeconds = convertInSeconds(getCurrentTime()) - convertInSeconds(pastDate);
        int y, mon, d, h, m, s;

        y = Math.abs(passedSeconds / YEAR_SECONDS);
        mon = Math.abs((passedSeconds % YEAR_SECONDS) / MONTH_SECONDS);
        d = Math.abs(((passedSeconds % YEAR_SECONDS) % MONTH_SECONDS) / DAY_SECONDS);
        h = Math.abs((((passedSeconds % YEAR_SECONDS) % MONTH_SECONDS) % DAY_SECONDS) / HOUR_SECONDS);
        m = Math.abs(((((passedSeconds % YEAR_SECONDS) % MONTH_SECONDS) % DAY_SECONDS) % HOUR_SECONDS) / MIN_SECONDS);
        s = Math.abs(((((passedSeconds % YEAR_SECONDS) % MONTH_SECONDS) % DAY_SECONDS) % HOUR_SECONDS) % MIN_SECONDS);

        if (y != 0) {
            return y + " g";
        } else if (mon != 0) {
            return mon + " m";
        } else if (d != 0) {
            return d + " d";
        } else if (h != 0) {
            return h + " h";
        } else if (m != 0) {
            return m + " min";
        } else {
            return s + " sec";
        }
    }
}