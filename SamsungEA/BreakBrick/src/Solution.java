/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import com.sun.security.jgss.GSSUtil;
import org.w3c.dom.ls.LSOutput;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.FileInputStream;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{

    static int[][] board;
    static int count;
    public static void main(String args[]) throws Exception
    {
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
        //System.setIn(new FileInputStream("res/input.txt"));

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

        for(int test_case = 1; test_case <= T; test_case++)
        {
            solution(sc, test_case);
        }
    }
    public static void solution(Scanner sc, int test_case) {
        int n = sc.nextInt();
        int w = sc.nextInt();
        int h = sc.nextInt();


        board = new int[h][w];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        ArrayList<Integer> solutionList = solution(n, w, h);
        Collections.sort(solutionList);

        System.out.println("#" + test_case + " " + solutionList.get(0) + "\t");
    }

    public static int getFirstBrick(int col){
        for(int j = 0; j<board.length; j++){
            if(board[j][col] != 0){
                return j;
            }
        }
        return 0;
    }

    public static void removeMarign(){
        for(int i = 0; i< board[0].length; i++){
            LinkedList<Integer> marginQueue = new LinkedList<Integer>();

            for(int j = board.length-1; j>=0; j--){

                if(board[j][i] == 0){
                    marginQueue.add(j);
                }
                if(board[j][i] != 0 && !marginQueue.isEmpty()){
                    board[marginQueue.poll()][i] = board[j][i];
                    board[j][i] = 0;
                    j++;
                }
            }
        }
    }

    public static void breakBrick(int i, int j){
        if(board[i][j] == 0){
        }
        else {
            int boardValue = board[i][j];
            board[i][j] = 0;
            for(int row = (-1 * boardValue) + 1; row <= boardValue - 1; row++){
                if(i+row >= 0 && i+row < board.length) {
//                    System.out.println(row);
//                    System.out.println("recall i: " + (i+row) );
//                    System.out.println("recall j: " + j );
                    breakBrick(i+row, j );
                }
            }
            for(int col = (-1 * boardValue) + 1; col <= boardValue - 1; col++){
                if(j + col >= 0 && j + col < board[0].length) {
                    breakBrick(i, j+col);
                }
            }
        }
    }

    public static int countBlock(){
        int count = 0;

        for(int i = 0; i<board[0].length; i++){
            for(int j = 0; j<board.length; j++){
                if(board[j][i] != 0){
                    count ++;
                }
            }
        }

        return count;
    }

    public static ArrayList<Integer> solution(int n, int h, int w){
        ArrayList<Integer> resultList = new ArrayList<>();
        int[][] tempBoard = copyBoard(board);

        if(n == 1){
            for(int i = 0; i<board[0].length; i++){
                breakBrick(getFirstBrick(i), i);
                resultList.add(countBlock());
                board = copyBoard(tempBoard);
            }
        }
        else if(n==2){
            for(int i = 0; i<board[0].length; i++){
                breakBrick(getFirstBrick(i), i);
                removeMarign();
                int[][] tempBoard1 = copyBoard(board);

                for(int j = 0; j<board[0].length; j++){
                    breakBrick(getFirstBrick(j), j);
                    removeMarign();
                    resultList.add(countBlock());
                    board = copyBoard(tempBoard1);
                }
                board = copyBoard(tempBoard);
            }
        }
        else if(n==3){
            for(int i = 0; i<board[0].length; i++){
                breakBrick(getFirstBrick(i), i);
                removeMarign();
                int[][] tempBoard1 = copyBoard(board);

                for(int j = 0; j<board[0].length; j++){
                    breakBrick(getFirstBrick(j), j);
                    removeMarign();
                    int[][] tempBoard2 = copyBoard(board);

                    for(int k = 0; k<board[0].length; k++) {
                        breakBrick(getFirstBrick(k), k);
                        removeMarign();
                        resultList.add(countBlock());
                        board = copyBoard(tempBoard2);
                    }
                    board = copyBoard(tempBoard1);
                }
                board = copyBoard(tempBoard);
            }
        }
        else if(n==4){
            for(int i = 0; i<board[0].length; i++){
                breakBrick(getFirstBrick(i), i);
                removeMarign();
                int[][] tempBoard1 = copyBoard(board);

                for(int j = 0; j<board[0].length; j++){
                    breakBrick(getFirstBrick(j), j);
                    removeMarign();
                    int[][] tempBoard2 = copyBoard(board);

                    for(int k = 0; k<board[0].length; k++) {
                        breakBrick(getFirstBrick(k), k);
                        removeMarign();
                        int[][] tempBoard3 = copyBoard(board);

                        for(int r = 0; r<board[0].length; r++) {
                            breakBrick(getFirstBrick(r), r);
                            removeMarign();
                            resultList.add(countBlock());
                            board = copyBoard(tempBoard3);
                        }
                        board = copyBoard(tempBoard2);
                    }
                    board = copyBoard(tempBoard1);
                }
                board = copyBoard(tempBoard);
            }
        }
        return resultList;
    }
    public static void printBoard(int[][] inputBoard){
        for(int i = 0; i<inputBoard[0].length; i++){
            for(int j = 0 ;j<inputBoard.length; j++){
                System.out.print(inputBoard[i][j]);
            }
            System.out.println();
        }
    }

    public static int[][] copyBoard(int[][] inputBoard){
        int[][] resultBoard = new int[inputBoard.length][inputBoard[0].length];

        for(int i = 0; i<inputBoard.length; i++){
            for(int j = 0; j<inputBoard[0].length; j++){
                resultBoard[i][j] = inputBoard[i][j];
            }
        }
        return resultBoard;
    }


}