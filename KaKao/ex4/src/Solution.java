import java.util.*;
class Solution {
    static int[][] board;
    static int[][] shorten;
    static int answer = 0;
    static int M = Integer.MAX_VALUE;

    public static int solution(int n, int s, int a, int b, int[][] fares) {
        board = new int[n][n];
        shorten = new int[n][n];

        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(i==j) {
                    board[i][j] = 0;
                }
                else {
                    board[i][j] = M;
                }
            }
        }
        for(int i=0;i<fares.length;i++) {
            board[fares[i][0]-1][fares[i][1]-1] = fares[i][2];
            board[fares[i][1]-1][fares[i][0]-1] = fares[i][2];
        }

        for(int q=0; q<n; q++) {
            int[] distance = board[q].clone();
            boolean[] used = new boolean[board.length];

            for (int z = 0; z < used.length; z++) {
                int minIndex = -1;
                int minVal = M;

                for(int i=0;i<distance.length;i++) {
                    if(!used[i] && minVal>distance[i]) {
                        minIndex=i;
                        minVal=distance[i];
                    }
                }
                if(minIndex!=-1) {
                    used[minIndex] = true;
                    for(int i=0;i<distance.length;i++) {
                        if(!used[i] && board[minIndex][i] != M && distance[i]>distance[minIndex]+board[minIndex][i] ) {
                            distance[i] = distance[minIndex]+board[minIndex][i];
                        }
                    }
                }
            }
            for(int o=0;o<distance.length;o++) {
                shorten[q][o] = distance[o];
            }
        }
        answer = shorten[s-1][a-1] + shorten[s-1][b-1];
        for(int p=0;p<n;p++) {
            int temp = 0;
            temp += shorten[s-1][p];
            temp += shorten[p][a-1] + shorten[p][b-1];

            if(answer>temp) answer =temp;

        }
        return answer;
    }
}