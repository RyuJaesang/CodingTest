import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args){

        int[] progresses = new int[]{93,30,55};
        int[] speeds = new int[]{1,30,5};

        int[] answer = solution(progresses, speeds);

        for(int elem : answer){
            System.out.println(elem);
        }
    }

    public static int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> progressesQueue = new LinkedList(Arrays.stream(progresses).boxed().collect(Collectors.toList()));
        Queue<Integer> speedsQueue = new LinkedList(Arrays.stream(speeds).boxed().collect(Collectors.toList()));
        ArrayList<Integer> result = new ArrayList<>();

        int initProgress = progressesQueue.poll();
        int initSpeed = speedsQueue.poll();
        int day = (int) Math.ceil((float)(100 - initProgress) / initSpeed);
        result.add(1);

        while(!progressesQueue.isEmpty()){
            int curProgress = progressesQueue.poll();
            int curSpeed = speedsQueue.poll();
            int curDay = (int) Math.ceil((float)(100 - curProgress) / curSpeed);

//            System.out.println(curDay);

            if(curDay <= day){
                result.set(result.size()-1, result.get(result.size()-1) +1);
            }
            else{
                result.add(1);
                day = curDay;
            }
        }

        int[] answer = result.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}
