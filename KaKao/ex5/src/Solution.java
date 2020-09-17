import java.sql.Time;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Comparator;


class Solution {
    public static void main(String[] args){
        String playTime = "02:03:55";
        String adv_time = "00:14:15";
        String[] logs = {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"};

        System.out.println(solution(playTime, adv_time, logs));
    }

    public static String solution(String play_time, String adv_time, String[] logs) {
        long playTime = stringToDate(play_time);
        long advTime = stringToDate(adv_time);
        if(playTime <= advTime){
            return "00:00:00";
        }

        Arrays.sort(logs);
        long[][] logDPList = new long[logs.length * 2][2];

//        long[][] logsTime = new long[logs.length][2];

        for(int i = 0; i < logsTime.length; i++){
            String logStart = logs[i].split("-")[0];
            String logEnd = logs[i].split("-")[1];

            logDPList[2*i][0] = i;
            logDPList[2*i][1] = stringToDate(logStart);
            logDPList[2*i + 1][0] = i;
            logDPList[2*i + 1][1] = stringToDate(logEnd);
        }

        long longestWatedTime = Long.MIN_VALUE;
        String answer = "";

        Arrays.sort(new Comparator(){

        });

        for(int i = 0; i<logsTime.length; i++){
            long watchedTime = calPlayTime(logsTime[i][0], logsTime[i][0] + advTime, logsTime);
            if(watchedTime > longestWatedTime){
                longestWatedTime = watchedTime;
                answer = logs[i].split("-")[0];
            }
        }
        return answer;
    }

    private static long calPlayTime(long startTime, long endTime,long[][] logsTime){
        long watchedTime = 0;

        for(long[] logTime : logsTime){
            if(startTime <= logTime[0]){
                if(endTime >= logTime[0]){
                    watchedTime += Math.min(endTime-logTime[0], logTime[1]-logTime[0]);
                }
            } else if(startTime >= logTime[0]){
                if(startTime <= logTime[1]){
                    watchedTime += Math.min(endTime-startTime, logTime[1]-startTime);
                }
            }
        }
        return watchedTime;
    }

    private static long stringToDate(String stringDate){
        Time time = Time.valueOf(stringDate);
        return time.getTime()+32400000;
    }
}