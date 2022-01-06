import java.util.ArrayList;
import java.util.Random;

public class CardGame {
    private Random rnd = new Random();

    public static void main(String[] args) {
        CardGame cardGame = new CardGame();
        cardGame.play();
    }

    private void play() {
        // Create array of cards
        Card[] cards = new Card[52];
        for (int i = 0; i <= 3; i++) {
            for (int j = 2; j <= 14; j++) {
                cards[i * 13 + j - 2] = new Card(i, j);
            }
        }

        // Shuffle array of cards
        shuffleCards(cards);

        // Distribute cards
        ArrayList<Card> player1 = new ArrayList<>();
        ArrayList<Card> player2 = new ArrayList<>();
        ArrayList<Card> player3 = new ArrayList<>();
        ArrayList<Card> player4 = new ArrayList<>();

        for (int i = 0; i < cards.length; i = i + 4) {
            player1.add(cards[i]);
            player2.add(cards[i + 1]);
            player3.add(cards[i + 2]);
            player4.add(cards[i + 3]);
        }

        // print info
        System.out.println("Card Game simulation by <Member 1 Name: ID> and <Member 2 Name: ID>");

        // Print card distribution info
        System.out.println("Distribution of cards:");
        printHand(player1, "1");
        printHand(player2, "2");
        printHand(player3, "3");
        printHand(player4, "4");

        System.out.println("Status of all deals after 13 deals:");

        int player1Wins = 0;
        int player2Wins = 0;
        int player3Wins = 0;
        int player4Wins = 0;

        ArrayList<Card> player1WinningCards = new ArrayList<>();
        ArrayList<Card> player2WinningCards = new ArrayList<>();
        ArrayList<Card> player3WinningCards = new ArrayList<>();
        ArrayList<Card> player4WinningCards = new ArrayList<>();

        for (int i = 0; i < cards.length / 4; i++) {
            // Play one round
            String dealId = (i < 9) ? "0" + (i + 1) : "" + (i + 1);
            Card card1 = player1.get(i);
            Card card2 = player2.get(i);
            Card card3 = player3.get(i);
            Card card4 = player4.get(i);

            // Check who is winner of the deal
            int winnerId = calculateWinner(card1, card2, card3, card4);
            switch (winnerId) {
                case 1:
                    player1Wins++;
                    player1WinningCards.add(card1);
                    break;

                case 2:
                    player2Wins++;
                    player2WinningCards.add(card2);
                    break;

                case 3:
                    player3Wins++;
                    player3WinningCards.add(card3);
                    break;

                case 4:
                    player4Wins++;
                    player4WinningCards.add(card4);
                    break;
            }

            System.out.println("Deal number " + dealId + ": " + card1 + ", " + card2 + ", " + card3 + ", " + card4 +
                    " (Winner: Player-" + winnerId + ")");
        }

        // Print game statistics
        System.out.println("Deal winners:");
        System.out.println("Player:\tNo. of Deal(s) Won");
        System.out.println("1: " + player1Wins);
        System.out.println("2: " + player2Wins);
        System.out.println("3: " + player3Wins);
        System.out.println("4: " + player4Wins);

        // Check overall winner
        int totalWinnerId = totalWinner(player1WinningCards, player2WinningCards, player3WinningCards, player4WinningCards);

        System.out.println("Overall winner(s): Player-" + totalWinnerId);
    }

    private int totalWinner(ArrayList<Card> player1WinningCards, ArrayList<Card> player2WinningCards,
                            ArrayList<Card> player3WinningCards, ArrayList<Card> player4WinningCards) {
        int winnerId = -1;
        int winnerTotal = 0;
        int winnerCards = 0;

        // Check first 2 cards
        if (player1WinningCards.size() > player2WinningCards.size()) {
            winnerId = 1;
            winnerTotal = calculateTotal(player1WinningCards);
            winnerCards = player1WinningCards.size();
        } else if (player1WinningCards.size() < player2WinningCards.size()) {
            winnerId = 2;
            winnerTotal = calculateTotal(player2WinningCards);
            winnerCards = player2WinningCards.size();
        } else {
            int totalWin1 = calculateTotal(player1WinningCards);
            int totalWin2 = calculateTotal(player2WinningCards);
            winnerId = totalWin1 > totalWin2 ? 1 : 2;
            winnerTotal = totalWin1 > totalWin2 ? totalWin1 : totalWin2;
            winnerCards = player1WinningCards.size();
        }

        if (winnerCards < player3WinningCards.size() ||
                (winnerCards == player3WinningCards.size() && winnerTotal < calculateTotal(player3WinningCards))) {
            winnerCards = player3WinningCards.size();
            winnerTotal = calculateTotal(player3WinningCards);
            winnerId = 3;
        }

        if (winnerCards < player4WinningCards.size() ||
                (winnerCards == player4WinningCards.size() && winnerTotal < calculateTotal(player4WinningCards))) {
            winnerId = 4;
        }

        return winnerId;
    }

    private int calculateTotal(ArrayList<Card> playerWinningCards) {
        int total = 0;
        for (Card card : playerWinningCards
        ) {
            total += card.getTotal();
        }
        return total;
    }

    private int calculateWinner(Card card1, Card card2, Card card3, Card card4) {
        Card winner = null;
        int winnerId = -1;

        // Check first 2 cards
        if (card1.getTotal() > card2.getTotal()) {
            winner = card1;
            winnerId = 1;
        } else if (card1.getTotal() < card2.getTotal()) {
            winner = card2;
            winnerId = 2;
        } else {
            winner = card1.getSuit() > card2.getSuit() ? card1 : card2;
            winnerId = card1.getSuit() > card2.getSuit() ? 1 : 2;
        }

        // Check third card
        if (winner.getTotal() < card3.getTotal() ||
                (winner.getTotal() == card3.getTotal() && winner.getSuit() < card3.getSuit())) {
            winner = card3;
            winnerId = 3;
        }

        // Check fourth card
        if (winner.getTotal() < card4.getTotal() ||
                (winner.getTotal() == card4.getTotal() && winner.getSuit() < card4.getSuit())) {
            winnerId = 4;
        }

        return winnerId;
    }

    private void printHand(ArrayList<Card> player, String id) {
        System.out.println("Cards for player " + id);
        for (Card card : player) {
            System.out.println(card);
        }
        System.out.println();
    }

    private void shuffleCards(Card[] cards) {
        for (int i = 0; i < 10000; i++) {
            int first = rnd.nextInt(cards.length);
            int second = rnd.nextInt(cards.length);
            if (first != second) {
                // Swap cards
                Card tmp = cards[first];
                cards[first] = cards[second];
                cards[second] = tmp;
            }
        }
    }
}
