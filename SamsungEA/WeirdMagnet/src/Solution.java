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

    static int k;

    static ArrayList<int[]> magnet;
    static ArrayList<int[]> magnetMove;

    static int result;


    public static void solution(Scanner sc, int test_case){
        magnet = new ArrayList<>();
        magnetMove = new ArrayList<>();

        result = 0;

        k = sc.nextInt();

        for(int i = 0; i<4; i++){
            int[] tempMagnet = new int[8];

            for(int j = 0; j<8; j++){
                tempMagnet[j] = sc.nextInt();
            }
            magnet.add(tempMagnet);
        }

        for(int i = 0; i<k; i++){
            int magnetIndex = sc.nextInt()-1;
            int magnetDirection = sc.nextInt();
            magnetMove.add(new int[]{magnetIndex, magnetDirection});
        }
        /////////////// preparing ///////////////////////



//        printList(magnetMove);


        // 움직일 갯수
        for(int i = 0; i<k; i++){
            ArrayList<int[]> moveSet = moveSet(magnetMove.get(i)[0], magnetMove.get(i)[1]);
//            Collections.sort(moveSet, new Comparator<int[]>() {
//                @Override
//                public int compare(int[] ints, int[] t1) {
//                    return ints[0] - t1[0];
//                }
//            });
            // 실제 움직임
            for(int j = 0; j<moveSet.size(); j++){
                if(moveSet.get(j)[1] == 1){
                    magnet.set(moveSet.get(j)[0], clockDirection(magnet.get(moveSet.get(j)[0])));
                }
                else if(moveSet.get(j)[1] == -1){
                    magnet.set(moveSet.get(j)[0], unclockDirection(magnet.get(moveSet.get(j)[0])));
                }
            }

        }
        calculateResult();

        System.out.println("#" + test_case + " " + result);

    }

    public static int[] clockDirection(int[] magnet){
        int tempVal = magnet[7];
        for(int i = 6; i>=0; i--){
            magnet[i+1] = magnet[i];
        }
        magnet[0] = tempVal;

        return magnet;
    }

    public static int[] unclockDirection(int[] magnet) {
        int tempVal = magnet[0];
        for (int i = 1; i < 8; i++) {
            magnet[i - 1] = magnet[i];
        }
        magnet[7] = tempVal;

        return magnet;
    }

    public static void calculateResult(){
        for(int i = 0; i< 4; i++){
            if(magnet.get(i)[0] == 1){
                result += Math.pow(2, i);
            }
        }
    }

    public static ArrayList<int[]> moveSet(int index, int direction){
        ArrayList<int[]> moveSet = new ArrayList<>();

        moveSet.add(new int[]{index, direction});
        int iterIndex = 0;

        for(int i=index; i<3; i++){
            if(magnet.get(i)[2] != magnet.get(i+1)[6]){
                moveSet.add(new int[]{i+1, -moveSet.get(iterIndex)[1]});
                iterIndex++;
            }
            else {
                break;
            }
        }

        for(int i = index; i >0; i--){
            if(magnet.get(i-1)[2] != magnet.get(i)[6]){
                if(i == index){
                    moveSet.add(new int[]{i - 1, -direction});
                    iterIndex++;
                }
                else{
                    moveSet.add(new int[]{i - 1, -moveSet.get(iterIndex)[1]});
                    iterIndex++;
                }
            }
            else{
                break;
            }
        }
        return moveSet;
    }

    public static void printList(ArrayList<int[]> inputList){
        for(int i = 0; i < inputList.size(); i++){
            for(int j = 0; j<inputList.get(i).length; j++){
                System.out.print(inputList.get(i)[j]);
            }
            System.out.println();
        }
    }

}
