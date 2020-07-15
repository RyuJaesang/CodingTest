import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public static void main(String[] args){

        int N = 545;

        System.out.println(solution(N));
    }



    public static int solution(int N) {
        String s = Integer.toString(N);
        ArrayList<Integer> n_list = new ArrayList<>();

        for (int i = 0 ;i < s.length(); i++){
            n_list.add(Character.getNumericValue(s.charAt(i)));
//            System.out.println(s.charAt(i));
        }

        Collections.sort(n_list, Collections.reverseOrder());

        String result ="";
        for(int i = 0; i<n_list.size(); i++){
            result += Integer.toString(n_list.get(i));
        }

        return Integer.parseInt(result);
    }
}