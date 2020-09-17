import java.util.*;
class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println(solution(new int[] {3, 3, 3, 3, 3, 3, 3, 3, 3, 3}));
    }

    public static int solution(int[] cards) {
        int answer = 0;
        LinkedList<Integer> cardList = new LinkedList<>();

        for (int i=0; i<cards.length; i++) {
            int card = cards[i];
            if (card > 10)
                card = 10;
            cardList.add(card);
        }
        SubDealer subDealer = new SubDealer(cardList);
        Player dealer = new Player();
        Player player = new Player();

        int temp = 0;
        while ((temp = game(subDealer, dealer, player)) != -1) {
            answer += temp;
            dealer.clear();
            player.clear();
        }
        return answer;
    }

    private static int game(SubDealer subDealer, Player dealer, Player player) {
        int visibleCard = 0;

        int card = subDealer.give();
        if (card == -1){
            return -1;
        }
        if (player.send(card) == -1){
            return -2;
        }
        card = subDealer.give();
        if (card == -1){
            return -1;
        }
        if (dealer.send(card) == -1){
            return 2;
        }

        card = subDealer.give();
        if (card == -1){
            return -1;
        }
        if (player.send(card) == -1){
            return -2;
        }
        card = subDealer.give();
        if (card == -1){
            return -1;
        }
        if (dealer.send(card) == -1){
            return 2;
        }
        visibleCard = card;

        while (player.isSend(visibleCard)) {
            card = subDealer.give();
            if (card == -1){
                return -1;
            }
            if (player.send(card) == -1){
                return -2;
            }
        }

        if (player.total == 21 && 21 > dealer.total){
            return 3;
        }

        while (dealer.dealerSend()) {
            card = subDealer.give();
            if (card == -1){
                return -1;
            }
            if (dealer.send(card) == -1){
                return 2;
            }
        }

        if (player.total > dealer.total && player.total == 21){
            return 3;
        }
        if (player.total == dealer.total){
            return 0;
        }
        if (player.total < dealer.total){
            return -2;
        }
        return 2;
    }

    private static class Player {
        int total = 0;
        int oneCount = 0;

        private boolean isSend(int visibleCard) {
            if (total == 21)
                return false;
            if (visibleCard == 1 || visibleCard >= 7) {
                if (total >= 17) return false;
                for (int count=1; count<=oneCount; count++) {
                    if ((total + count*10) >= 17 && (total + count*10) <= 21) {
                        total += count*10;
                        return false;
                    }
                }
                return true;
            } else if (visibleCard >= 4 && visibleCard <= 6)
                return false;
            else if (visibleCard >= 2 && visibleCard <= 3) {
                if (total >= 12) return false;
                for (int count=1; count<=oneCount; count++) {
                    if ((total + count*10) >= 12 && (total + count*10) <= 21) {
                        total += count*10;
                        return false;
                    }
                }
                return true;
            }
            return true;
        }

        private int send(int card) {
            if (card == 1){
                oneCount++;
            }
            total += card;

            for (int count=1; count<=oneCount; count++) {
                if ((total + count*10) == 21) {
                    total = 21;
                    return 0;
                }
            }

            if (total > 21){
                return -1;
            }
            return 1;
        }

        private void clear() {
            total = 0;
            oneCount = 0;
        }

        private boolean dealerSend() {
            return total < 17;
        }
    }

    private static class SubDealer {
        Queue<Integer> cardList;

        SubDealer(Queue<Integer> cardList) {
            this.cardList = cardList;
        }

        int give() {
            if (!cardList.isEmpty()){
                return cardList.poll();
            } else{
                return -1;
            }
        }
    }
}