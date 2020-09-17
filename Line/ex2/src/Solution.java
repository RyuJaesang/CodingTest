import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public static void main(String[] args){
        int[] ball = new int[]{1, 2, 3, 4, 5, 6};
        int[] order = new int[]{6, 2, 5, 1, 4, 3};

        int[] result = solution(ball, order);

        for(int elem : result){
            System.out.println(elem);
        }
    }

    public static int[] solution(int[] ball, int[] order) {
        int[] answer = new int[ball.length];
        int answerIdx = 0;

        LinkedList<Integer> balls = new LinkedList<>();
        Queue<Integer> blockedList = new LinkedList<>();

        for (int i=0; i<ball.length; i++) {
            balls.add(ball[i]);
        }

        for (int i=0; i<order.length; i++) {
            int firstBall = balls.getFirst();
            int lastBall = balls.getLast();
            int cur = order[i];

            while (blockedList.contains(firstBall) || blockedList.contains(lastBall)) {
                if (blockedList.contains(firstBall)) {
                    balls.removeFirst();
                    answer[answerIdx++] = firstBall;
                    firstBall = balls.getFirst();
                } else if (blockedList.contains(lastBall)) {
                    balls.removeLast();
                    answer[answerIdx++] = lastBall;
                    lastBall = balls.getLast();
                }
            }

            if (firstBall == cur) {
                answer[answerIdx++] = cur;
                balls.removeFirst();
            } else if (lastBall == cur) {
                answer[answerIdx++] = cur;
                balls.removeLast();
            } else {
                blockedList.add(cur);
            }
        }


        return answer;
    }
}