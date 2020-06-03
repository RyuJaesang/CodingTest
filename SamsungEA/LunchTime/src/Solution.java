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
import javax.swing.plaf.IconUIResource;
import java.lang.reflect.Array;
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

    public static ArrayList<int[]> peoplesLoc;
    public static ArrayList<int[]> stairsLoc;

    public static ArrayList<int[]> peoples;

    public static ArrayList<Integer> stair1List;
    public static ArrayList<Integer> stair2List;

    public static ArrayList<String> everyPossibility;


    public static void solution(Scanner sc, int test_case){
        peoplesLoc = new ArrayList<>();
        stairsLoc = new ArrayList<>();

        stair1List = new ArrayList<>();
        stair2List = new ArrayList<>();

        peoples = new ArrayList<>();

        everyPossibility = new ArrayList<>();

        int n = sc.nextInt();

        for(int i = 0; i<n; i++){
            for(int j = 0 ;j <n; j++){
                int inputNum = sc.nextInt();
                if(inputNum == 1){
                    peoplesLoc.add(new int[]{i, j});
                }
                else if(inputNum > 1){
                    stairsLoc.add(new int[]{i, j, inputNum});
                }
            }
        }
        createPosList();
        /////////////////// prepare //////////////////


//        System.out.println(stairsList.charAt(1));


//        for(int i = 0; i<everyPossibility.size(); i++){
//
//        }

        int minResult = 9999;

        for(int i = 0; i<everyPossibility.size(); i++){
            int result = 0;
            String stairsList = everyPossibility.get(i);
            peoples = createPeople(peoplesLoc, stairsList);

            while(true){
                if(peoples.isEmpty() && stair1List.isEmpty() && stair2List.isEmpty()){
                    break;
                }
                oneStep();
                result++;
            }
            if(result < minResult){
                minResult = result;
            }

        }

        System.out.println("#" + test_case + " " + minResult);
    }

    public static ArrayList<int[]> createPeople(ArrayList<int[]> loc, String stairsList){
        ArrayList<int[]> resultPeople = new ArrayList<>();

        for(int i =0; i<loc.size(); i++){
            int dist = Math.abs(stairsLoc.get(Character.getNumericValue(stairsList.charAt(i)))[0] - loc.get(i)[0]) + Math.abs(stairsLoc.get(Character.getNumericValue(stairsList.charAt(i)))[1] - loc.get(i)[1]);
            int stairs = Character.getNumericValue(stairsList.charAt(i));

            resultPeople.add(new int[]{dist, stairs});
        }
        return resultPeople;
    }

    public static void printList(ArrayList<int[]> inputList){
        for(int i =0 ;i<inputList.size(); i++){
            for(int j = 0; j<inputList.get(i).length; j++) {
                System.out.print(inputList.get(i)[j] + " ");
            }
            System.out.println();
        }
    }

    public static void oneStep(){
        for(int i  =0; i<stair1List.size(); i++){
            stair1List.set(i, stair1List.get(i) -1);
            if(stair1List.get(i) == 0){
                stair1List.remove(i);
                i--;
            }
        }
        for(int i  =0; i<stair2List.size(); i++){
            stair2List.set(i, stair2List.get(i) -1);
            if(stair2List.get(i) == 0){
                stair2List.remove(i);
                i--;
            }
        }


        for(int i = 0; i<peoples.size(); i++) {

            if(peoples.get(i)[0] > 0) {
                peoples.get(i)[0] -= 1;
            }

            if (peoples.get(i)[0] == -1) {
                if (peoples.get(i)[1] == 0) {
                    if (stair1List.size() < 3) {
                        stair1List.add(stairsLoc.get(0)[2]);
                        peoples.remove(i);
                        i--;
                        continue;
                    }
                }
                if(peoples.get(i)[1] == 1){
                    if(stair2List.size() < 3){
                        stair2List.add(stairsLoc.get(1)[2]);
                        peoples.remove(i);
                        i--;
                        continue;
                    }
                }
            }

            if (peoples.get(i)[0] == 0) {
                if (peoples.get(i)[1] == 0) {
                    if (stair1List.size() < 3) {
                        stair1List.add(stairsLoc.get(0)[2]+1);
                        peoples.remove(i);
                        i--;
                        continue;
                    }
                    else{
                        peoples.get(i)[0] = -1;
                    }
                }
                if(peoples.get(i)[1] == 1){
                    if(stair2List.size() < 3){
                        stair2List.add(stairsLoc.get(1)[2]+1);
                        peoples.remove(i);
                        i--;
                        continue;
                    }
                    else{
                        peoples.get(i)[0] = -1;
                    }
                }
            }
        }
    }

    public static void createPosList(){
        for(int i  =0; i<Math.pow(2, peoplesLoc.size()); i++){
            String pos = Integer.toBinaryString(i);

            while(pos.length() < peoplesLoc.size()){
                pos = "0" + pos;
            }
            everyPossibility.add(pos);
        }
    }



















}