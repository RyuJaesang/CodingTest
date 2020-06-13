import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        solution(sc, n);
    }

    // 이 ArrayList는 String 배열로 [teamName, 이긴횟수, 이긴세트수] 순으로 저장
   static ArrayList<Object[]> team;

    public static void solution(Scanner sc, int n){

        team = new ArrayList<>();

        for(int i  = 0; i< n * (n-1); i++){
            String teamA = sc.next();
            int teamASet = sc.nextInt();
            String teamB = sc.next();
            int teamBSet = sc.nextInt();

            int indexA = findIndex(team, teamA);
            int indexB = findIndex(team, teamB);

            // team A 가 이겼을 때
            if(teamASet > teamBSet){
                //team A 정리
                if(indexA == -1){
                    team.add(new Object[]{teamA, 1,teamASet-teamBSet});
                }
                else{
                    int teamAalreadyWin = (int) team.get(indexA)[1];
                    int teamAalreadySet = (int) team.get(indexA)[2];
                    teamAalreadySet += teamASet-teamBSet;

                    team.set(indexA, new Object[]{teamA, teamAalreadyWin+1, teamAalreadySet});
                }

                //team B 정리
                if(indexB == -1){
                    team.add(new Object[]{teamB, 0,teamBSet-teamASet});
                }
                else{
                    int teamBalreadyWin = (int) team.get(indexB)[1];
                    int teamBalreadySet = (int) team.get(indexB)[2];
                    teamBalreadySet += teamBSet-teamASet;

                    team.set(indexB, new Object[]{teamB, teamBalreadyWin, teamBalreadySet});
                }
            }

            // team B가 이겼을 때
            else if(teamASet < teamBSet){
                //team A 정리

                if(indexA == -1){
                    team.add(new Object[]{teamA, 0,teamASet-teamBSet});
                }
                else{
                    int teamAalreadyWin = (int) team.get(indexA)[1];
                    int teamAalreadySet = (int) team.get(indexA)[2];
                    teamAalreadySet += teamASet-teamBSet;

                    team.set(indexA, new Object[]{teamA, teamAalreadyWin, teamAalreadySet});
                }

                //team B 정리
                if(indexB == -1){
                    team.add(new Object[]{teamB, 1,teamBSet-teamASet});
                }
                else{
                    int teamBalreadyWin = (int) team.get(indexB)[1];
                    int teamBalreadySet = (int) team.get(indexB)[2];
                    teamBalreadySet += teamBSet-teamASet;

                    team.set(indexB, new Object[]{teamB, teamBalreadyWin+1, teamBalreadySet});
                }
            }
        }

        Collections.sort(team, new Comparator<Object[]>() {
            @Override
            public int compare(Object[] objects, Object[] t1) {
                if((Integer)objects[1] == (Integer)t1[1]){
                    if((Integer)objects[2] == (Integer)t1[2]){
                        return t1[0].toString().charAt(0) - objects[0].toString().charAt(0);
                    }
                    return (Integer)t1[2] - (Integer)objects[2];
                }
                return (Integer)t1[1] - (Integer)objects[1];

            }
        });

        for(int i  =0 ; i<team.size(); i++){
            System.out.println(team.get(i)[0].toString() + " " + team.get(i)[1].toString() + " " + team.get(i)[2].toString());
        }


    }

    public static int findIndex(ArrayList<Object[]> input, String teamName){
        for(int i = 0; i< input.size(); i++){
            if(input.get(i)[0].equals(teamName)){
                return i;
            }
        }
        return -1;
    }

}
