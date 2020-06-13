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

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
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

        for(int test_case = 1; test_case <= 1; test_case++)
        {

            /////////////////////////////////////////////////////////////////////////////////////////////
			/*
				 이 부분에 여러분의 알고리즘 구현이 들어갑니다.
			 */
            /////////////////////////////////////////////////////////////////////////////////////////////
            solution(sc, test_case);
        }
    }

    static int cut;
    static int[][] board;
    static ArrayList<int[]> highest;
    static int result;
    static int n;

    public static void solution(Scanner sc, int test_case){
        n = sc.nextInt();
        cut = sc.nextInt();
        board = new int[n][n];
        highest = new ArrayList<>();
        result = 0;

        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                board[i][j] = sc.nextInt();
            }
        }

        findHighest();

        int[][] tempBoard = board;
        board = new int[n+2][n+2];

        for(int i = 0; i<n+2; i++){
            for(int j = 0; j<n+2; j++){
                if(i == 0){
                    board[i][j] = 100;
                }
                else if(i == n+1){
                    board[i][j] = 100;
                }
                else if(j == 0){
                    board[i][j] = 100;
                }
                else if(j==n+1){
                    board[i][j] = 100;
                }
                else{
                    board[i][j] = tempBoard[i-1][j-1];
                }
            }
        }

        printBoard(board);
        printList(highest);




        for(int index = 0; index<highest.size(); index++){
            boolean[][] visit = new boolean[n+2][n+2];

            for(int i = 0; i<n+2; i++){
                for(int j = 0; j<n+2; j++){
                    if(i == 0){
                        visit[i][j] = true;
                    }
                    else if(i == n+1){
                        visit[i][j] = true;
                    }
                    else if(j == 0){
                        visit[i][j] = true;
                    }
                    else if(j==n+1){
                        visit[i][j] = true;
                    }
                    else{
                        visit[i][j] = false;
                    }
                }
            }

            visit[highest.get(index)[0]][highest.get(index)[1]] = true;
            dfs(0, visit, false, highest.get(index)[0], highest.get(index)[1]);
        }

        System.out.println("#" + test_case + " " + result);
    }

    public static void dfs(int count, boolean[][] visit, boolean useK, int i, int j){
        if(board[i+1][j] >= board[i][j] && board[i][j+1] >= board[i][j] && board[i-1][j] >= board[i][j] && board[i][j-1] >= board[i][j] && useK){
            if(result < count){
                result = count;
//                System.out.println(" i ; " + i);
//                System.out.println(" j ; " + j);
            }
        }
        else{
            if(j+1 < n+1) {
                if (board[i][j + 1] < board[i][j] && !visit[i][j + 1]) {
                    visit[i][j + 1] = true;
                    dfs(count + 1, visit, useK, i, j + 1);
                    visit[i][j + 1] = false;
                }
                if (board[i][j + 1] >= board[i][j] && board[i][j + 1] - cut < board[i][j] && !visit[i][j + 1] && !useK) {
                    visit[i][j + 1] = true;
                    dfs(count + 1, visit, true, i, j + 1);
                    visit[i][j + 1] = false;
                }
            }

            if(j-1 >= 0) {
                if (board[i][j - 1] < board[i][j] && !visit[i][j - 1]) {
                    visit[i][j - 1] = true;
                    dfs(count + 1, visit, useK, i, j - 1);
                    visit[i][j - 1] = false;
                }
                if (board[i][j - 1] >= board[i][j] && board[i][j - 1] - cut < board[i][j] && !visit[i][j - 1] && !useK) {
                    visit[i][j - 1] = true;
                    dfs(count + 1, visit, true, i, j - 1);
                    visit[i][j - 1] = false;
                }
            }

            if(i+1 < n+1) {
                if (board[i + 1][j] < board[i][j] && !visit[i + 1][j]) {
                    visit[i + 1][j] = true;
                    dfs(count + 1, visit, useK, i + 1, j);
                    visit[i + 1][j] = false;
                }
                if (board[i + 1][j] >= board[i][j] && board[i + 1][j] - cut < board[i][j] && !visit[i + 1][j] && !useK) {
                    visit[i + 1][j] = true;
                    dfs(count + 1, visit, true, i + 1, j);
                    visit[i + 1][j] = false;
                }
            }

            if(i-1 >= 0) {
                if (board[i - 1][j] < board[i][j] && !visit[i - 1][j]) {
                    visit[i - 1][j] = true;
                    dfs(count + 1, visit, useK, i - 1, j);
                    visit[i - 1][j] = true;
                }
                if (board[i - 1][j] >= board[i][j] && board[i - 1][j] - cut < board[i][j] && !visit[i - 1][j] && !useK) {
                    visit[i - 1][j] = true;
                    dfs(count + 1, visit, true, i - 1, j);
                    visit[i - 1][j] = true;
                }
            }
            if(result < count){
                result = count;
            }
        }
    }


    public static void printBoard(int[][] inputBoard){
        for(int i =0 ;i <inputBoard.length; i++){
            for(int j =0; j<inputBoard[i].length; j++){
                System.out.print(inputBoard[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void findHighest(){
        int max = 0;

        for(int i  = 0; i < board.length; i++){
            for(int j = 0; j<board[i].length; j++){
                if(max < board[i][j]){
                    max = board[i][j];
                }
            }
        }

        for(int i  = 0; i < board.length; i++){
            for(int j = 0; j<board[i].length; j++){
                if(max == board[i][j]){
                    highest.add(new int[]{i+1, j+1});
                }
            }
        }
    }

    public static void printList(ArrayList<int[]> inputList){
        for(int i =0; i<inputList.size(); i++){
            for(int j = 0; j<inputList.get(i).length; j++){
                System.out.print(inputList.get(i)[j] + " ");
            }
            System.out.println();
        }
    }

















}