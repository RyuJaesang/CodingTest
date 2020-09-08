import java.util.*;

class Solution {
    static Set<Set<Integer[]>> pongSet = new HashSet<>();
    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int boardCol;
    static int boardRow;

    public int solution(int[][] board){
        if (board.length==0 || board[0].length == 0) {
            return 0;
        }
        boardRow = board.length;
        boardCol = board[0].length;

        for (int i=0; i < board.length; i++) {
            for (int j=0; j<board[0].length; j++) {
                for (int z=0; z < 4; z++) {
                    // 위, 오른쪽, 아래, 왼쪽으로 스위치
                    switchPong(board, i, j, dir[z][0], dir[z][1]);
                }
            }
        }

        if (pongSet.size() != 0)
            return pongSet.size() / 2;

        return -1;
    }

    public void switchPong(int[][]board, int currentY, int currentX, int y, int x){
        int nextY = currentY + y;
        int nextX = currentX + x;

        int tmp;
        if (nextY<0 || nextY>board.length - 1 || nextX<0 || nextX>board[0].length - 1)
            return ;

        // 스왑
        tmp = board[currentY][currentX];
        board[currentY][currentX] = board[nextY][nextX];
        board[nextY][nextX] = tmp;

        // 스왑 한걸로 검사
        if (isPong(board, currentY, currentX) || isPong(board, nextY, nextX)) {
            //퐁 터지면
            Integer[] block1 = new Integer[2];
            block1[0] = currentY;
            block1[1] = currentX;

            Integer[] block2 = new Integer[2];
            block2[0] = nextY;
            block2[1] = nextX;
            Set<Integer[]> set = new HashSet<>();
            set.add(block1);
            set.add(block2);

            pongSet.add(set);
        }

        // 다시스왑
        tmp = board[currentY][currentX];
        board[currentY][currentX] = board[nextY][nextX];
        board[nextY][nextX] = tmp;

    }

    public boolean isPong(int[][]board, int currentY, int currentX) {
        int var = board[currentY][currentX];
        int verticalIdx;
        int horizontalIdx;
        int area = 1;
        // 위아래
        verticalIdx = currentY - 1;
        while(verticalIdx >= 0) {
            if (board[verticalIdx][currentX] == var) {
                area ++;
            } else {
                break;
            }
            verticalIdx--;
        }
        verticalIdx = currentY + 1;
        while(verticalIdx < boardRow) {
            if (board[verticalIdx][currentX] == var) {
                area ++;
            } else {
                break;
            }
            verticalIdx++;
        }

        if (area >=3 )
            return true;

        // 양옆
        horizontalIdx = currentX - 1;
        area = 1;
        while(horizontalIdx >= 0) {
            if (board[currentY][horizontalIdx] == var) {
                area ++;
            } else {
                break;
            }
            horizontalIdx--;
        }
        horizontalIdx = currentX + 1;
        while(horizontalIdx < boardCol) {
            if (board[currentY][horizontalIdx] == var) {
                area ++;
            } else {
                break;
            }
            horizontalIdx++;
        }

        if (area >= 3)
            return true;

        return false;
    }
}
