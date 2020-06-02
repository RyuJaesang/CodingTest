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

    public static ArrayList<ArrayList<int[]>> apList;
    public static ArrayList<Integer> apCapacity;
    static int[] userA;
    static int[] userB;
    static int[] aLoc;
    static int[] bLoc;
    static int result;


    public static void solution(Scanner sc, int test_case){
        int time = sc.nextInt();
        int apNum = sc.nextInt();

        userA = new int[time];
        userB = new int[time];

        apList = new ArrayList<>();
        apCapacity = new ArrayList<>();

        aLoc = new int[]{1, 1};
        bLoc = new int[]{10, 10};

        result = 0;

        // User sequence
        for(int i = 0; i<time; i++){
            userA[i] = sc.nextInt();
        }
        for(int i = 0; i<time; i++){
            userB[i] = sc.nextInt();
        }
        // AP List
        for(int i = 0; i < apNum; i++){
            int xloc = sc.nextInt();
            int yloc = sc.nextInt();
            int length = sc.nextInt();
            apCapacity.add(sc.nextInt());

            ArrayList<int[]> coverXY = new ArrayList<>();

            // upper side
            for(int j = 0; j <= length; j++){
                for(int k = -j ; k <= j; k++){
                    if(k+xloc <= 10 && yloc-(length-j) <= 10) {
                        coverXY.add(new int[]{k + xloc, yloc - (length - j)});
                    }
                }
            }
            // down side
            for(int j = 1; j <= length; j++){
                for(int k = -(length-j); k <= length-j; k++){
                    if(k+xloc <=10 && j+yloc <=10) {
                        coverXY.add(new int[]{k + xloc, j + yloc});
                    }
                }
            }

            apList.add(coverXY);
        }
        ///////////////// End Of Prepare ///////////////////////////////

        checkCoverage();

        for(int i = 0; i<time; i++){
            updateABLoc(i);
            checkCoverage();
        }

        System.out.println("#" + test_case + " " + result);

    }

    public static void updateABLoc(int time) {
        if (userA[time] == 0) {

        } else if (userA[time] == 1) {
            aLoc[1] -= 1;
        } else if (userA[time] == 2) {
            aLoc[0] += 1;
        } else if (userA[time] == 3) {
            aLoc[1] += 1;
        } else if (userA[time] == 4) {
            aLoc[0] -= 1;
        }

        if (userB[time] == 0) {

        } else if (userB[time] == 1) {
            bLoc[1] -= 1;
        } else if (userB[time] == 2) {
            bLoc[0] += 1;
        } else if (userB[time] == 3) {
            bLoc[1] += 1;
        } else if (userB[time] == 4) {
            bLoc[0] -= 1;
        }
    }

    public static void checkCoverage(){
        ArrayList<Integer> aAP = new ArrayList<>();
        ArrayList<Integer> bAP = new ArrayList<>();

        ArrayList<Integer> resultList = new ArrayList<>();
//        System.out.println(aLoc[0] + "," + aLoc[1]);
//        printAPList();

        for(int i = 0; i<apList.size(); i++){
            for(int j =0; j<apList.get(i).size(); j++){
                if(apList.get(i).get(j)[0] == aLoc[0] && apList.get(i).get(j)[1] == aLoc[1]){
                    aAP.add(i);
                }
            }
            for(int j =0; j<apList.get(i).size(); j++){
                if(apList.get(i).get(j)[0] == bLoc[0] && apList.get(i).get(j)[1] == bLoc[1]){
                    bAP.add(i);
                }
            }
        }

        if(aAP.size() > 0 && bAP.size()>0) {
            for (int i = 0; i < aAP.size(); i++) {
                for (int j = 0; j < bAP.size(); j++) {
                    if (aAP.get(i) == bAP.get(j)) {
                        resultList.add(apCapacity.get(aAP.get(i)));
                    } else {
                        resultList.add(apCapacity.get(aAP.get(i)) + apCapacity.get(bAP.get(j)));
                    }
                }
            }
        }
        else{
            for(int i = 0; i<aAP.size(); i++){
                resultList.add(apCapacity.get(aAP.get(i)));
            }

            for(int i = 0; i<bAP.size(); i++){
                resultList.add(apCapacity.get(bAP.get(i)));

            }
        }
        Collections.sort(resultList, Collections.reverseOrder());

        if(!resultList.isEmpty()){
            result += resultList.get(0);
        }
    }

    public static void printAPList(){
        for(int i =0; i<apList.size(); i++){
            for(int j =0; j<apList.get(i).size(); j++){
                System.out.print("(");
                for(int k = 0; k<2; k++){
                    System.out.print(apList.get(i).get(j)[k] + " ");
                }
                System.out.print("), ");
            }
            System.out.println();
        }
    }
}