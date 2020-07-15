import java.util.*;

public class Solution {

    public static void main(String[] args){
        int num = 10;
        int[] mark = new int[]{1,2,3,1};

        System.out.println(solution(num, mark));
    }

    public static int solution(int num, int[] mark) {
        ArrayList<Integer> visitNum = new ArrayList<>();
        boolean[] visit = new boolean[mark.length];

        int answer = 0;
        visitNum.add(answer);
        visit[answer] = true;

        for(int i = 0; i < num; i++){
            answer = mark[answer];
            if(!visit[answer]){
                visitNum.add(answer);
                visit[answer] = true;
            }
            else{
                int index = visitNum.indexOf(answer);

                int devideIter = (num-index) % (visitNum.size()-index);
                answer = visitNum.get(devideIter+index);
                break;
            }
        }
        return answer;
    }
}