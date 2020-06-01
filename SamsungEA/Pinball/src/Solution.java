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

    static int[][] board;
    static int startI;
    static int startJ;
    static int[][] wormHallList;
    static ArrayList<Integer> resultList;

    public static void solution(Scanner sc, int test_case){
        int n = sc.nextInt();
        board = new int[n+2][n+2];
        wormHallList = new int[5][4];
        resultList = new ArrayList<>();

        for(int i = 1; i<n+1; i++){
            for(int j = 1; j<n+1; j++){
                board[i][j] = sc.nextInt();
            }
        }

        for(int i = 0; i<board.length; i++){
            board[i][0] = 5;
            board[0][i] = 5;
            board[board.length-1][i] = 5;
            board[i][board.length-1] = 5;
        }

        initWormHallList();
        findWormHall();

//        for(int i = 0; i<wormHallList.length; i++){
//            for(int j = 0; j<wormHallList[i].length; j++){
//                System.out.print(wormHallList[i][j]);
//            }
//            System.out.println();
//        }
//        System.out.println();
//
//        printBoard(board);

        for(startI = 0; startI<board.length; startI++){
            for(startJ = 0; startJ<board[0].length; startJ++){
                if(board[startI][startJ] == 0) {
                    for (int k = 1; k < 5; k++) {
                        resultList.add(findResult(k, startI, startJ));
                    }
                }
            }
        }


        Collections.sort(resultList, Collections.reverseOrder());
//        for(int i = 0; i<resultList.size();i ++){
//            System.out.print(resultList.get(i));
//        }


        System.out.println("#" + test_case + " " + resultList.get(0));
    }

    public static int findResult(int direction, int currentI, int currentJ){
        int score = 0;
        int iter_Count = 0;

        while(true){
            if(board[currentI][currentJ] >= -1 && board[currentI][currentJ] < 6){
                // No block
                if(board[currentI][currentJ] == -1){
                    break;
                }
                if(board[currentI][currentJ] == 0) {
                    if (direction == 1) {
                        for(int it = currentJ; it<board[0].length; it++){
                            if(board[currentI][it] != 0 || (startI == currentI && startJ == it && iter_Count > 0)){
                                currentJ = it;
                                break;
                            }
                            else if(it == board[0].length){
                                currentJ = it;
                            }
                        }
                    }
                    else if(direction == 2){
                        for(int it = currentI; it<board.length; it++){
                            if(board[it][currentJ] != 0 || (startI == it && startJ == currentJ && iter_Count > 0)){
                                currentI = it;
                                break;
                            }
                            else if(it == board.length){
                                currentI = it;
                            }
                        }
                    }
                    else if(direction ==3){
                        for(int it = currentJ; it>=0; it--){
                            if(board[currentI][it] != 0 || (startI == currentI && startJ == it && iter_Count > 0)){
                                currentJ = it;
                                break;
                            }
                            else if(it == 0){
                                currentJ = it;
                            }
                        }
                    }
                    else if(direction == 4){
                        for(int it = currentI; it>=0; it--){
                            if(board[it][currentJ] != 0 || (startI == it && startJ == currentJ && iter_Count > 0) ){
                                currentI = it;
                                break;
                            }
                            else if(it == 0){
                                currentI = it;
                            }
                        }
                    }
                }
                // block
                else if(board[currentI][currentJ] == 1){
                    if(direction == 1){
                        int[] result = moveLeft(currentI, currentJ);
                        direction = result[0];
                        currentI = result[1];
                        currentJ = result[2];
                        score++;
                    }
                    else if(direction == 2){
                        int[] result = moveRight(currentI, currentJ);
                        direction = result[0];
                        currentI = result[1];
                        currentJ = result[2];
                        score++;
                    }
                    else if(direction ==3){
                        int[] result = moveUp(currentI, currentJ);
                        direction = result[0];
                        currentI = result[1];
                        currentJ = result[2];
                        score++;
                    }
                    else if(direction == 4){
                        int[] result = moveDown(currentI, currentJ);
                        direction = result[0];
                        currentI = result[1];
                        currentJ = result[2];
                        score++;
                    }
                }
                else if(board[currentI][currentJ] == 2){
                    if(direction == 1){
                        int[] result = moveLeft(currentI, currentJ);
                        direction = result[0];
                        currentI = result[1];
                        currentJ = result[2];
                        score++;
                    }
                    else if(direction == 2){
                        int[] result = moveUp(currentI, currentJ);
                        direction = result[0];
                        currentI = result[1];
                        currentJ = result[2];
                        score++;
                    }
                    else if(direction ==3){
                        int[] result = moveDown(currentI, currentJ);
                        direction = result[0];
                        currentI = result[1];
                        currentJ = result[2];
                        score++;
                    }
                    else if(direction == 4){
                        int[] result = moveRight(currentI, currentJ);
                        direction = result[0];
                        currentI = result[1];
                        currentJ = result[2];
                        score++;
                    }
                }
                else if(board[currentI][currentJ] == 3){
                    if(direction == 1){
                        int[] result = moveDown(currentI, currentJ);
                        direction = result[0];
                        currentI = result[1];
                        currentJ = result[2];
                        score++;
                    }
                    else if(direction == 2){
                        int[] result = moveUp(currentI, currentJ);
                        direction = result[0];
                        currentI = result[1];
                        currentJ = result[2];
                        score++;
                    }
                    else if(direction ==3){
                        int[] result = moveRight(currentI, currentJ);
                        direction = result[0];
                        currentI = result[1];
                        currentJ = result[2];
                        score++;
                    }
                    else if(direction == 4){
                        int[] result = moveLeft(currentI, currentJ);
                        direction = result[0];
                        currentI = result[1];
                        currentJ = result[2];
                        score++;                    }
                }
                else if(board[currentI][currentJ] == 4){
                    if(direction == 1){
                        int[] result = moveUp(currentI, currentJ);
                        direction = result[0];
                        currentI = result[1];
                        currentJ = result[2];
                        score++;
                    }
                    else if(direction == 2){
                        int[] result = moveLeft(currentI, currentJ);
                        direction = result[0];
                        currentI = result[1];
                        currentJ = result[2];
                        score++;
                    }
                    else if(direction ==3){
                        int[] result = moveRight(currentI, currentJ);
                        direction = result[0];
                        currentI = result[1];
                        currentJ = result[2];
                        score++;
                    }
                    else if(direction == 4){
                        int[] result = moveDown(currentI, currentJ);
                        direction = result[0];
                        currentI = result[1];
                        currentJ = result[2];
                        score++;
                    }
                }
                else if(board[currentI][currentJ] == 5){
                    if(direction == 1){
                        int[] result = moveLeft(currentI, currentJ);
                        direction = result[0];
                        currentI = result[1];
                        currentJ = result[2];
                        score++;                     }
                    else if(direction == 2){
                        int[] result = moveUp(currentI, currentJ);
                        direction = result[0];
                        currentI = result[1];
                        currentJ = result[2];
                        score++;
                    }
                    else if(direction ==3){
                        int[] result = moveRight(currentI, currentJ);
                        direction = result[0];
                        currentI = result[1];
                        currentJ = result[2];
                        score++;
                    }
                    else if(direction == 4){
                        int[] result = moveDown(currentI, currentJ);
                        direction = result[0];
                        currentI = result[1];
                        currentJ = result[2];
                        score++;
                    }
                }
            }
            // worm hall
            else{
                int wormHallNum = board[currentI][currentJ] - 6;

                if(wormHallList[wormHallNum][0] == currentI && wormHallList[wormHallNum][1] == currentJ){
                    if(direction == 1) {
                        currentI = wormHallList[wormHallNum][2];
                        currentJ = wormHallList[wormHallNum][3]+1;
                    }
                    else if(direction == 2){
                        currentI = wormHallList[wormHallNum][2]+1;
                        currentJ = wormHallList[wormHallNum][3];
                    }
                    else if(direction == 3){
                        currentI = wormHallList[wormHallNum][2];
                        currentJ = wormHallList[wormHallNum][3]-1;
                    }
                    else if(direction == 4){
                        currentI = wormHallList[wormHallNum][2]-1;
                        currentJ = wormHallList[wormHallNum][3];
                    }
                }
                else{
                    if(direction == 1) {
                        currentI = wormHallList[wormHallNum][0];
                        currentJ = wormHallList[wormHallNum][1]+1;
                    }
                    else if(direction == 2){
                        currentI = wormHallList[wormHallNum][0]+1;
                        currentJ = wormHallList[wormHallNum][1];
                    }
                    else if(direction == 3){
                        currentI = wormHallList[wormHallNum][0];
                        currentJ = wormHallList[wormHallNum][1]-1;
                    }
                    else if(direction == 4){
                        currentI = wormHallList[wormHallNum][0]-1;
                        currentJ = wormHallList[wormHallNum][1];
                    }
                }
            }
            if((currentI == startI && currentJ == startJ) || iter_Count > 99999){
                break;
            }
            iter_Count++;
        }

        return score;
    }

    public static int[] moveLeft(int i, int j){
        int direction = 3;
        int currentI = i;
        int currentJ = j-1;

        return new int[]{direction, currentI, currentJ};
    }

    public static int[] moveRight(int i, int j){
        int direction = 1;
        int currentI = i;
        int currentJ = j+1;

        return new int[]{direction, currentI, currentJ};
    }

    public static int[] moveUp(int i, int j){
        int direction = 4;
        int currentI = i-1;
        int currentJ = j;

        return new int[]{direction, currentI, currentJ};
    }

    public static int[] moveDown(int i, int j){
        int direction = 2;
        int currentI = i+1;
        int currentJ = j;

        return new int[]{direction, currentI, currentJ};
    }


    // Worm Hall Function
    static void initWormHallList() {
        for (int i = 0; i < wormHallList.length; i++) {
            for (int j = 0; j < wormHallList[0].length; j++) {
                wormHallList[i][j] = -1;
            }
        }
    }
    public static void findWormHall(){
        int wormHallNum;

        for(int i = 0; i<board.length; i++){
            for(int j = 0;j <board[0].length; j++){
                if(board[i][j] > 5){
                    if(wormHallList[board[i][j] - 6][0] == -1) {
                        wormHallList[board[i][j] - 6][0] = i;
                        wormHallList[board[i][j] - 6][1] = j;
                    }
                    else{
                        wormHallList[board[i][j] - 6][2] = i;
                        wormHallList[board[i][j] - 6][3] = j;
                    }
                }

            }
        }
    }

    public static void printBoard(int[][] inputBoard){
        for(int i = 0; i<board.length; i++){
            for(int j = 0; j<board[0].length; j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }






































}