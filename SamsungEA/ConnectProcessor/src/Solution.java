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
import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.Collections;
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

    static int maxPnum;
    static int minLineNum;


    public static void solution(Scanner sc, int test_case){
        minLineNum = 9999;
        maxPnum = 0;

        int n = sc.nextInt();

        int[][] board = new int[n][n];
        for(int i= 0; i< n ; i++){
            for(int j = 0; j< n ; j++){
                board[i][j] = sc.nextInt();
            }
        }

        int[][] tempBoard = copyBoard(board);

        for(int i = 0; i < board[0].length; i++){
            tempBoard[i][0] = 0;
            tempBoard[0][i] = 0;
            tempBoard[board[0].length-1][i] = 0;
            tempBoard[i][board[0].length-1] = 0;
        }

        ArrayList<int[]> coreList = new ArrayList<int[]>();
        for(int i= 0; i< n ; i++){
            for(int j = 0; j< n ; j++){
                if(tempBoard[i][j] == 1){
                    coreList.add(new int[]{i, j});
                }
            }
        }

        dfs(board, 0, 0, coreList, 0);

        System.out.println("#"+ test_case +" " + minLineNum);

    }

    public static void dfs(int [][] board, int lineNum, int pnum, ArrayList<int[]> coreList, int idx){
//        System.out.println("line num : " + lineNum);
//        System.out.println("p num : " + pnum);
//        System.out.println("Min line num : " + minLineNum);
//        System.out.println("Max p num : " + maxPnum);
//        printBoard(board);
        if(idx == coreList.size()){
            if(pnum > maxPnum){
                maxPnum = pnum;
                minLineNum = lineNum;
                return;
            }
            else if(pnum == maxPnum){
                if(lineNum < minLineNum){
                    minLineNum = lineNum;
                    return;
                }
            }
        }
        else{
            int[][] tempBoard = copyBoard(board);

            for(int k = 0; k < 5; k++){
                // left side dfs
                if(k == 0){

                    int leftCount = 0;
                    int leftLine = 0;
                    for (int j = 0; j < coreList.get(idx)[1]; j++) {
                        if (board[coreList.get(idx)[0]][j] == 0) {
                            leftCount++;
                        }
                    }
                    if (leftCount == coreList.get(idx)[1]) {
                        board = copyBoard(tempBoard);

                        for (int j = 0; j < coreList.get(idx)[1]; j++) {
                            board[coreList.get(idx)[0]][j] = 1;
                            leftLine++;
                        }
                        dfs(board, lineNum+leftLine, pnum+1, coreList, idx+1);
                    }
                }
                // right side dfs
                else if(k==1){

                    int rightCount = 0;
                    int rightLine = 0;
                    for (int j = coreList.get(idx)[1] + 1; j < board[0].length; j++) {
                        if (board[coreList.get(idx)[0]][j] == 0) {
                            rightCount++;
                        }
                    }
                    if (rightCount == board[0].length - coreList.get(idx)[1] - 1) {
                        board = copyBoard(tempBoard);

                        for (int j = coreList.get(idx)[1] + 1; j < board[0].length; j++) {
                            board[coreList.get(idx)[0]][j] = 1;
                            rightLine++;
                        }
                        dfs(board, lineNum+rightLine, pnum+1, coreList, idx+1);
                    }
                }
                // down side dfs
                else if(k==2){

                    int downCount = 0;
                    int downLine = 0;
                    for (int j = coreList.get(idx)[0] + 1; j < board.length; j++) {
                        if (board[j][coreList.get(idx)[1]] == 0) {
                            downCount++;
                        }
                    }
                    if (downCount == board.length - coreList.get(idx)[0] - 1) {
                        board = copyBoard(tempBoard);

                        for (int j = coreList.get(idx)[0] + 1; j < board.length; j++) {
                            board[j][coreList.get(idx)[1]] = 1;
                            downLine++;
                        }
                        dfs(board, lineNum + downLine, pnum+1, coreList, idx+1);
                    }
                }
                // upper side dfs
                else if(k==3){

                    int upperCount = 0;
                    int upperLine = 0;
                    for (int j = 0; j < coreList.get(idx)[0]; j++) {
                        if (board[j][coreList.get(idx)[1]] == 0) {
                            upperCount++;
                        }
                    }
                    if (upperCount == coreList.get(idx)[0]) {
                        board = copyBoard(tempBoard);

                        for (int j = 0; j < coreList.get(idx)[0]; j++) {
                            board[j][coreList.get(idx)[1]] = 1;
                            upperLine++;
                        }
                        dfs(board, lineNum+upperLine, pnum+1, coreList, idx+1);
                    }
                }
                else{
                    board = copyBoard(tempBoard);

                    dfs(board, lineNum, pnum, coreList, idx+1);
                }
            }
        }
    }

    public static int[][] copyBoard(int[][] inputBoard){
        int[][] resultBoard = new int[inputBoard.length][inputBoard[0].length];

        for(int i = 0; i < inputBoard.length; i++){
            System.arraycopy(inputBoard[i], 0, resultBoard[i],0 , resultBoard[0].length);
        }
        return resultBoard;
    }

    public static void printBoard(int[][] inputBoard){
        for(int i= 0; i< inputBoard.length ; i++){
            for(int j = 0; j< inputBoard[0].length ; j++){
                System.out.print(inputBoard[i][j]);
            }
            System.out.println();
        }
    }

}