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
import java.util.*;
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

    public static int[][] board;
    static ArrayList<int[]> activeList;
    static ArrayList<int[]> unactiveList;
    static ArrayList<int[]> proliferationList;

    public static void solution(Scanner sc, int test_case){
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        board = new int[n + (2*k)][m + (2*k)];
        activeList = new ArrayList<>();
        unactiveList = new ArrayList<>();
        proliferationList = new ArrayList<>();

        for(int i = k; i < k+n; i++){
            for(int j = k; j< k+m; j++){
                board[i][j] = sc.nextInt();
            }
        }

        initQueue();
//        printQueues();
        for(int i = 0 ; i< k; i++){
//            System.out.println("Iter " + i);
            minusListandMigrate();
//            printBoard(board);
//            printQueues();

        }

        System.out.println("#" + test_case + " " + (unactiveList.size()+activeList.size()));

    }

    public static void oneStep(){


    }

    public static void minusListandMigrate(){
        ArrayList<int[]> tempProlifList = new ArrayList<>();

//        for(int i = 0; i<activeList.size(); i++){
//            activeList.get(i)[2] -=1;
//            if(activeList.get(i)[2] == 0){
//                proliferationList.add(new int[]{activeList.get(i)[0], activeList.get(i)[1], board[activeList.get(i)[0]][activeList.get(i)[1]]});
//                activeList.remove(i);
//                i--;
//            }
//        }
        for(int i = 0; i<activeList.size(); i++){
            activeList.get(i)[2] -= 1;
            if (activeList.get(i)[2] == 0) {
                activeList.remove(i);
                i--;
            }
        }

        for(int i = 0; i<unactiveList.size(); i++) {
            unactiveList.get(i)[2] -= 1;
            if (unactiveList.get(i)[2] == 0) {
                activeList.add(new int[]{unactiveList.get(i)[0], unactiveList.get(i)[1], board[unactiveList.get(i)[0]][unactiveList.get(i)[1]]});
                tempProlifList.add(new int[]{unactiveList.get(i)[0], unactiveList.get(i)[1], board[unactiveList.get(i)[0]][unactiveList.get(i)[1]]});

                unactiveList.remove(i);
                i--;
            }
        }

        sortList(proliferationList);
        for(int i = 0; i<proliferationList.size(); i++){
            proliferation(proliferationList.get(i)[0], proliferationList.get(i)[1], proliferationList.get(i)[2]);
            proliferationList.remove(i);
            i--;
        }

        for(int i = 0; i<tempProlifList.size(); i++){
            proliferationList.add(tempProlifList.get(i));
        }

    }

    public static void printBoard(int[][] inputBoard){
        for(int i = 0; i<inputBoard.length; i++){
            for(int j = 0; j< inputBoard[0].length ; j ++){
                System.out.print(inputBoard[i][j]);
            }
            System.out.println();
        }
    }

    public static void sortList(ArrayList<int[]> targetList){
        Collections.sort(targetList, new Comparator<int[]>() {
            @Override
            public int compare(int[] ints, int[] t1) {
                return t1[2] - ints[2];
            }
        });
    }

    public static void proliferation(int i, int j, int value){
        if(board[i-1][j] == 0){
            board[i-1][j] = value;
            unactiveList.add(new int[]{i-1, j, value});
        }
        if(board[i+1][j] == 0){
            board[i+1][j] = value;
            unactiveList.add(new int[]{i+1, j, value});
        }
        if(board[i][j+1] == 0){
            board[i][j+1] = value;
            unactiveList.add(new int[]{i, j+1, value});
        }
        if(board[i][j-1] == 0){
            board[i][j-1] = value;
            unactiveList.add(new int[]{i, j-1, value});
        }
    }

    public static void initQueue(){
        for(int i = 0; i<board.length; i++){
            for(int j = 0; j<board[0].length; j++){
                if(board[i][j] != 0){
                    unactiveList.add(new int[]{i, j, board[i][j]});
                }
            }
        }
    }

    public static void printQueues(){
        System.out.println("Proliferation List");
        for(int i = 0; i<proliferationList.size(); i++){
            for(int j = 0; j<proliferationList.get(i).length; j++){
                System.out.print(proliferationList.get(i)[j] + " ");
            }
            System.out.println();
        }

        System.out.println("Active List");
        for(int i = 0; i<activeList.size(); i++){
            for(int j = 0; j<activeList.get(i).length; j++){
                System.out.print(activeList.get(i)[j] + " ");
            }
            System.out.println();
        }

        System.out.println("Unactive List");
        for(int i = 0; i<unactiveList.size(); i++){
            for(int j = 0; j<unactiveList.get(i).length; j++){
                System.out.print(unactiveList.get(i)[j] + " ");
            }
            System.out.println();
        }
    }






































}