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
import java.util.Comparator;
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

    static ArrayList<int[]> cell;
    static int n;
    static int result = 0;


    public static void solution(Scanner sc, int test_case){
        cell = new ArrayList<>();
        result = 0;

        n = sc.nextInt();
        int time = sc.nextInt();
        int cellNum = sc.nextInt();

        for(int i = 0; i < cellNum; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int val = sc.nextInt();
            int dir = sc.nextInt();
            cell.add(new int[]{a, b, val, dir});
        }

        for(int i = 0; i<time; i++){
            moveStep();
            checkBoarder();
            checkMerge();
        }

        getResult();

        System.out.println("#" + test_case + " " + result);
    }

    public static void moveStep(){
        for(int i =0 ;i<cell.size(); i++){
            if(cell.get(i)[3] == 1){
                cell.get(i)[0] -= 1;
            }
            else if(cell.get(i)[3] == 2){
                cell.get(i)[0] += 1;
            }
            else if(cell.get(i)[3] == 3){
                cell.get(i)[1] -= 1;
            }
            else if(cell.get(i)[3] == 4){
                cell.get(i)[1] += 1;
            }
        }
    }

    public static void checkBoarder(){
        for(int i = 0; i<cell.size(); i++){
            if(cell.get(i)[0] == 0){
                cell.get(i)[2] = cell.get(i)[2] /2;
                cell.get(i)[3] = 2;
            }
            else if(cell.get(i)[1] == 0){
                cell.get(i)[2] = cell.get(i)[2] /2;
                cell.get(i)[3] = 4;

            }
            else if(cell.get(i)[0] == n-1){
                cell.get(i)[2] = cell.get(i)[2] /2;
                cell.get(i)[3] = 1;
            }
            else if(cell.get(i)[1] == n-1){
                cell.get(i)[2] = cell.get(i)[2] /2;
                cell.get(i)[3] = 3;
            }
        }
    }

    public static void getResult(){
        for(int i = 0; i < cell.size(); i++){
            result += cell.get(i)[2];
        }
    }

    public static void checkMerge(){

        // Sorting
        Collections.sort(cell, new Comparator<int[]>() {
            @Override
            public int compare(int[] ints, int[] t1) {
                if(ints[0] == t1[0]){
                    return ints[1] - t1[1];
                }
                else{
                    return ints[0] - t1[0];
                }
            }
        });

        // Now check
        for(int i = 0; i<cell.size()-1; i++){
            if(cell.get(i)[0] == cell.get(i+1)[0] && cell.get(i)[1] == cell.get(i+1)[1]){

                if(i+2 < cell.size()) {
                    if (cell.get(i + 2)[0] == cell.get(i + 1)[0] && cell.get(i + 2)[1] == cell.get(i + 1)[1]) {
                        // 4
                        if (i + 3 < cell.size()) {
                            if (cell.get(i + 2)[0] == cell.get(i + 3)[0] && cell.get(i + 2)[1] == cell.get(i + 3)[1]) {
                                int dir = 0;
                                int maxVal = 0;
                                int addVal = 0;
                                for (int j = i; j < i + 4; j++) {
                                    addVal += cell.get(j)[2];
                                    if (cell.get(j)[2] > maxVal) {
                                        maxVal = cell.get(j)[2];
                                        dir = cell.get(j)[3];
                                    }
                                }
                                cell.add(new int[]{cell.get(i)[0], cell.get(i)[1], addVal, dir});
                                for (int j = i + 3; j >= i; j--) {
                                    cell.remove(j);
                                }
                                i--;
                                continue;
                            }
                        }
                        // 3
                        int dir = 0;
                        int maxVal = 0;
                        int addVal = 0;
                        for(int j = i; j < i+3; j++){
                            addVal += cell.get(j)[2];
                            if(cell.get(j)[2] > maxVal){
                                maxVal = cell.get(j)[2];
                                dir = cell.get(j)[3];
                            }
                        }
                        cell.add(new int[]{cell.get(i)[0], cell.get(i)[1], addVal, dir});
                        for(int j = i+2; j >= i; j--){
                            cell.remove(j);
                        }
                        i--;
                        continue;
                    }
                }
                // 2
                int dir = 0;
                int maxVal = 0;
                int addVal = 0;
                for(int j = i; j < i+2; j++){
                    addVal += cell.get(j)[2];
                    if(cell.get(j)[2] > maxVal){
                        maxVal = cell.get(j)[2];
                        dir = cell.get(j)[3];
                    }
                }
                cell.add(new int[]{cell.get(i)[0], cell.get(i)[1], addVal, dir});

                for(int j = i+1; j >= i; j--){
                    cell.remove(j);
                }
                i--;
            }
        }
    }












    public static void printCell(ArrayList<int[]> input){
        for(int i = 0; i< input.size(); i++){
            for(int j = 0; j<input.get(i).length; j++){
                System.out.print(input.get(i)[j] + " ");
            }
            System.out.println();
        }
    }






}