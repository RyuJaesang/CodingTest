class Solution {
    public static void main(String[] args){
        int m = 6;
        int n = 4;
        int[][] picture = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};

        int[] result = solution(m, n, picture);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }

    static int[][] board;
    static int numberOfArea;
    static int maxSizeOfOneArea;
    static int tempSize = 0;

    public static int[] solution(int m, int n, int[][] picture) {
        board = new int[m][n];
        for(int i = 0; i< m; i++){
            for(int j = 0; j<n; j++){
                board[i][j] = picture[i][j];
            }
        }
        numberOfArea = 0;
        maxSizeOfOneArea = 0;

        for(int i = 0; i < m; i++){
            for(int j = 0; j<n; j++){
                if(board[i][j] != 0){
                    numberOfArea++;
                    tempSize = 0;

                    findField(board[i][j], i, j);

                    if(tempSize > maxSizeOfOneArea){
                        maxSizeOfOneArea = tempSize;
                    }
                }
            }
        }
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;

        return answer;
    }

    public static void findField(int val, int curY, int curX){
        if(curX < 0 || curX >= board[0].length || curY < 0 || curY >= board.length){
            return;
        } else if(val != board[curY][curX]){
            return;
        }
        board[curY][curX] = 0;
        tempSize++;

        findField(val, curY+1, curX);
        findField(val, curY, curX+1);
        findField(val, curY-1, curX);
        findField(val, curY, curX-1);

    }
}