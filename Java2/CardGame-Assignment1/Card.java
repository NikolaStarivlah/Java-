public class Card {
    private int suit;
    private int face;

    public Card(int suit, int face) {
        this.suit = suit;
        this.face = face;
    }

    public int getSuit() {
        return suit;
    }

    public int getFace() {
        return face;
    }

    public int getTotal() {
        return suit + face;
    }

    @Override
    public String toString() {
        return "" + suit + " " + face;
    }
}
