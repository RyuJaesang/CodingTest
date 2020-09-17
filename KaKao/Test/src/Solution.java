import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Solution {
    public static void main(String[] args) throws ParseException {
        String a = "00:01:30";
        String b = "00:02:00";

        String sum = "00:03:30";
        String[] logs = {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"};

        long aTime = stringToDate(a);
        long bTime = stringToDate(b);
        long sumTime = stringToDate(sum);

        System.out.println(aTime+bTime);
        System.out.println(sumTime);
    }

    private static long stringToDate(String stringDate){
        Time time = Time.valueOf(stringDate);
        return time.getTime()+32400000;
    }
}
