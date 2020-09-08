import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



class Result {

    /*
     * Complete the 'solution' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER k
     *  2. INTEGER_ARRAY arrival
     *  3. INTEGER_ARRAY load
     */

    // Request DTO
    static class Req implements Comparable<Req>{
        int arrival;
        int load;

        public Req(int arrival, int load) {
            this.arrival = arrival;
            this.load = load;
        }

        @Override
        public int compareTo(Req o) {
            return this.arrival - o.arrival;
        }
    }

    static int serverIndex = 0;
    static int[] serverFinish;
    static int[] serverResult;
    static Integer tempArrivalServerMin = 0;


    public static List<Integer> solution(int k, List<Integer> arrival, List<Integer> load) {
        // Write your code here
        serverFinish = new int[k];
        serverResult = new int[k];

        // Request Setting
        List<Req> reqs = new ArrayList<>();
        for (int i=0; i<arrival.size(); i++) {
            reqs.add(new Req(arrival.get(i), load.get(i)));
        }
        Collections.sort(reqs);


        // Processing Every Task with Queue Algorithm
        for(Req req : reqs){
            if(tempArrivalServerMin < req.arrival){
                serverScheduler(serverFinish, serverResult, req.arrival, req.load);
            }
        }

        // Calculate Result
        int maxValue = 0;
        ArrayList<Integer> resultArrayList = new ArrayList<>();

        for(int elem: serverResult){
            if(elem > maxValue){
                maxValue = elem;
            }
        }
        for(int i = 0; i<serverResult.length; i++){
            if(maxValue == serverResult[i]){
                resultArrayList.add(i+1);
            }
        }

//        for(Integer elem : resultArrayList){
//            System.out.println(elem);
//        }
        return resultArrayList;

    }

    // Task Scheduler Logic
    public static void serverScheduler(int[] serverFinish, int[] serverResult, int arrivalTime, int taskLoad){
        for(int i = 0; i < serverFinish.length; i++){
            if(serverFinish[serverIndex] < arrivalTime){
                serverResult[serverIndex] += taskLoad;
                serverFinish[serverIndex] = arrivalTime+taskLoad-1;
                serverIndex++;
                if(serverIndex >= serverFinish.length){
                    serverIndex = 0;
                }
                tempArrivalServerMin = Arrays.stream(serverFinish).min().orElse(-1);
                break;
            } else{
                serverIndex++;
                if(serverIndex >= serverFinish.length){
                    serverIndex = 0;
                }
            }
        }
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int k = Integer.parseInt(bufferedReader.readLine().trim());

        int arrivalCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arrival = IntStream.range(0, arrivalCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int loadCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> load = IntStream.range(0, loadCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> result = Result.solution(k, arrival, load);

//        bufferedWriter.write(
//                result.stream()
//                        .map(Object::toString)
//                        .collect(joining("\n"))
//                        + "\n"
//        );

        bufferedReader.close();
//        bufferedWriter.close();
    }
}