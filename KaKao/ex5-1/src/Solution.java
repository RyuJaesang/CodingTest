import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        String play_time = "02:03:55";
        String adv_time = "00:14:15";
        String[] logs = {"01:20:15-01:45:14", "00:25:50-00:48:29", "00:40:31-01:00:00", "01:37:44-02:02:30", "01:30:59-01:53:29"};
//        String[] logs = {"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"};
        // 정렬 안해도 되나?
        Arrays.sort(logs);

        List<String> advTimeList = new ArrayList<>();
        List<LocalTime> answerList = new ArrayList<>();
        for (String log : logs) {
            LocalTime t1 = LocalTime.parse(log.substring(0, 8), DateTimeFormatter.ofPattern("HH:mm:ss"));
            LocalTime t2 = LocalTime.parse(adv_time, DateTimeFormatter.ofPattern("HH:mm:ss"));
            LocalTime total = t1.plusHours(t2.getHour()).plusMinutes(t2.getMinute()).plusSeconds(t2.getSecond());
            advTimeList.add(t1 + "-" + total);
        }

//        System.out.println("logs: " + Arrays.asList(logs));
//        System.out.println("adv time: " + advTimeList);
        LocalTime time = LocalTime.parse("00:00:00", DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalTime maxTime = LocalTime.parse("00:00:00", DateTimeFormatter.ofPattern("HH:mm:ss"));
        Map<String, String> answerMap = new HashMap<>();
        for (int i = 0; i < advTimeList.size(); i++) {
            for (int j = 0; j < logs.length; j++) {

                Integer startTime1 = Integer.valueOf(logs[j].substring(0, 8).replace(":", ""));
                Integer endTime1 = Integer.valueOf(logs[j].substring(9, 17).replace(":", ""));
                Integer startTime2 = Integer.valueOf(advTimeList.get(i).substring(0, 8).replace(":", ""));
                Integer endTime2 = Integer.valueOf(advTimeList.get(i).substring(9, 17).replace(":", ""));

                LocalTime st1 = LocalTime.parse(logs[j].substring(0, 8), DateTimeFormatter.ofPattern("HH:mm:ss"));
                LocalTime et1 = LocalTime.parse(logs[j].substring(9, 17), DateTimeFormatter.ofPattern("HH:mm:ss"));
                LocalTime st2 = LocalTime.parse(advTimeList.get(i).substring(0, 8), DateTimeFormatter.ofPattern("HH:mm:ss"));
                LocalTime et2 = LocalTime.parse(advTimeList.get(i).substring(9, 17), DateTimeFormatter.ofPattern("HH:mm:ss"));

                if (startTime1 >= endTime2) {
                    continue;
                } else if (startTime1 <= endTime2 && startTime1 >= startTime2 && endTime1 >= endTime2) {
                    time = time.plusHours(et2.getHour() - st1.getHour()).plusMinutes(et2.getMinute() - st1.getMinute()).plusSeconds(et2.getSecond() - st1.getSecond());
                } else if (startTime1 <= endTime2 && startTime1 >= startTime2 && endTime1 <= endTime2) {
                    time = time.plusHours(et1.getHour() - st1.getHour()).plusMinutes(et1.getMinute() - st1.getMinute()).plusSeconds(et1.getSecond() - st1.getSecond());
                } else if (startTime1 <= startTime2 && endTime1 >= endTime2) {
                    time = time.plusHours(et2.getHour() - st2.getHour()).plusMinutes(et2.getMinute() - st2.getMinute()).plusSeconds(et2.getSecond() - st2.getSecond());
                } else if (startTime1 <= startTime2 && endTime1 <= endTime2 && endTime1 >= startTime2) {
                    time = time.plusHours(et1.getHour() - st2.getHour()).plusMinutes(et1.getMinute() - st2.getMinute()).plusSeconds(et1.getSecond() - st2.getSecond());
                } else if (endTime1 <= startTime2) {
                    continue;
                }
            }
            if (answerMap.get(time.toString()) == null) {
                answerMap.put(time.toString(), advTimeList.get(i).substring(0, 8));
            }
            if (time.isAfter(maxTime)) {
                maxTime = time;
            }
            System.out.println(time);
            time = LocalTime.parse("00:00:00", DateTimeFormatter.ofPattern("HH:mm:ss"));
        }
        System.out.println("정답:" + answerMap.get(maxTime.toString()));
    }
}