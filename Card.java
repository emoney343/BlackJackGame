public class Card {
    public final String face;
    public final String suit;

    public Card(String face, String suit) {
        this.face = face;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return face + " of " + suit;
    }

    public int getValue() {
    String face = this.face;
    switch (face) {
        case "Ace": return  14;
        case "King": return 13;
        case "Queen": return 12;
        case "Jack":  return 11;
        case "10": return  10;
        case "9": return 9;
        case "8": return 8;
        case "7": return 7;
        case "6": return 6;
        case "5": return 5;
        case "4": return 4;
        case "3": return 3;
        case "2": return 2;
    }
    throw new IllegalArgumentException("Invalid card face: " + face);
    }

     public int getSuits() {
    String suit = this.suit;
    switch (suit) {
        case "Spades": return  1;
        case "Clubs": return 2;
        case "Diamonds": return 3;
        case "Hearts":  return 4;
    }
    throw new IllegalArgumentException("Invalid card Suit: " + suit);
 }
}
    

