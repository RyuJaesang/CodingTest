import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public static void main(String[] args){
        int[] nubmers = new int[]{3, 30, 34, 5, 9};
        System.out.println(solution(nubmers));
    }

    public static String solution(int[] numbers) {
        // Initialize
        String answer = "";
        ArrayList<String> numbers_str = new ArrayList<>();
        for(int elem : numbers){
            numbers_str.add(String.valueOf(elem));
        }
        Collections.sort(numbers_str);

        // Processing
        while(!numbers_str.isEmpty()) {
            String largest = numbers_str.get(0);
            numbers_str.remove(0);

            for (int i = 0; i < numbers_str.size(); i++) {
                if(!comparator(largest, numbers_str.get(i))){
                    numbers_str.add(largest);
                    largest = numbers_str.get(i);
                    numbers_str.remove(i);
                    i--;
                };
            }

            answer += largest;
        }

        return answer;
    }

    private static boolean comparator(String origin, String compare){
        int length = Math.min(origin.length(), compare.length());

        for(int i = 0; i<length; i++){
            if(origin.charAt(i) < compare.charAt(i)){
                return false;
            } else if(origin.charAt(i) > compare.charAt(i)){
                return true;
            }
            if(i == length-1){
                if(origin.length() > compare.length()){
                    return origin.charAt(i + 1) > compare.charAt(i);
                } else if(origin.length() < compare.length()){
                    return origin.charAt(i) > compare.charAt(i+1);
                }
            }
        }
        return true;
    }
}
