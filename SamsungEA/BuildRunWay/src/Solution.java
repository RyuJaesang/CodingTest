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
import java.lang.reflect.Array;
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

        for(int test_case = 1; test_case <= T; test_case++)
        {

            /////////////////////////////////////////////////////////////////////////////////////////////
			/*
				 이 부분에 여러분의 알고리즘 구현이 들어갑니다.
			 */
            /////////////////////////////////////////////////////////////////////////////////////////////
            solution(sc, test_case);
        }
    }

    static int[][] board;
    static int result;
    static int runwayLen;

    public static void solution(Scanner sc, int test_case){

        int n = sc.nextInt();
        runwayLen = sc.nextInt();

        board = new int[n][n];
        result = 0;

        for(int i = 0; i<n; i++){
            for(int j = 0 ;j<n; j++){
                board[i][j] = sc.nextInt();
            }
        }

        findPossiblity();

        System.out.println("#" + test_case + " " + result);
    }

    public static void findPossiblity(){

        // Dive in row
        for(int i = 0; i < board.length; i++){

            ArrayList<Integer> runwayList = new ArrayList<>();
            for(int j = 0; j<board[0].length-1; j++){
                if(board[i][j] - board[i][j+1] == 1){
                    //check can build runway
                    int can = 0;
                    for(int k = 0; k<runwayLen-1; k++){
                        if(j+2+k < board[0].length) {
                            if (board[i][j + 1 + k] == board[i][j + 2 + k] && !runwayList.contains(j+2+k)) {
                                can++;
                                runwayList.add(j+2+k);
                                runwayList.add(j+1+k);
                            }
                        }
                    }
                    if(can!=runwayLen-1){
                        break;
                    }

                }

                if(board[i][j] - board[i][j+1] == -1){
                    //check can build runway
                    int can = 0;
                    for(int k = 0; k<runwayLen-1; k++){
                        if(j -1 - k >= 0) {
                            if (board[i][j - k] == board[i][j -1 - k] && !runwayList.contains(j-1-k)) {
                                can++;
                                runwayList.add(j-1-k);
                                runwayList.add(j-k);
                            }
                        }
                    }
                    if(can!=runwayLen-1){
                        break;
                    }
                }
                if(Math.abs(board[i][j] - board[i][j+1])>1){
                    break;
                }
                if(j == board[0].length-2){
//                    System.out.println("row : " + i);
                    result++;
                }
            }
        }

        // Dive in column
        for(int i = 0; i< board[0].length; i++){

            ArrayList<Integer> runwayList = new ArrayList<>();
            for(int j = 0; j<board.length-1; j++){
                if(board[j][i] - board[j+1][i] == 1){
                    //check can build runway
                    int can = 0;
                    for(int k = 0; k<runwayLen-1; k++){
                        if(j+2+k < board.length) {
                            if (board[j + 1 + k][i] == board[j + 2 + k][i] && !runwayList.contains(j+2+k)) {
                                can++;
                                runwayList.add(j+2+k);
                                runwayList.add(j+1+k);
                            }
                        }
                    }
                    if(can!=runwayLen-1){
                        break;
                    }
                }

                if(board[j][i] - board[j+1][i] == -1){
                    //check can build runway
                    int can = 0;
                    for(int k = 0; k<runwayLen-1; k++){
                        if(j -1 - k >= 0) {
                            if (board[j - k][i] == board[j -1 - k][i] && !runwayList.contains(j-1-k)) {
                                can++;
                                runwayList.add(j-1-k);
                                runwayList.add(j-k);
                            }
                        }
                    }
                    if(can!=runwayLen-1){
                        break;
                    }
                }
                if(Math.abs(board[j][i] - board[j+1][i])>1){
                    break;
                }
                if(j == board.length-2){
                    result++;
//                    System.out.println("col : " + i);

                }
            }
        }
    }

    public static void printBoard(int[][] inputBoard){
        for(int i = 0; i<inputBoard.length; i++){
            for(int j = 0 ;j<inputBoard[0].length; j++){
                System.out.print(inputBoard[i][j]);
            }
            System.out.println();
        }
    }























}