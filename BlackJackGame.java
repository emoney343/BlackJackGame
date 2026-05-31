import java.util.Arrays;
import java.util.Scanner;
import java.util.Comparator;

public class BlackJackGame {

    // Method to get straight
    static boolean isStraight(int[] arr) {
        int[] a = arr.clone();
        Arrays.sort(a);

        for (int i = 1; i < a.length; i++) {
            if (a[i] != a[i - 1] + 1) return false;
        }
        return true;
    }

    // Method to get highcard
    static int highCard(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
        }
        return max;
    }

    private static int evaluateAndPrint(Card[] hand) {

        // Store counts
        int[] counts = new int[15];     
        int[] suitCounts = new int[5];
        int[] values = new int[hand.length];

        // Get values of hands and suits in hand
        for (int i = 0; i < hand.length; i++) {
            int v = hand[i].getValue();
            int s = hand[i].getSuits();

            values[i] = v;
            counts[v]++;
            suitCounts[s]++;
        }

        int pairs = 0;
        int triples = 0;
        int quads = 0;

        for (int v = 2; v <= 14; v++) {
            if (counts[v] == 2) pairs++;
            else if (counts[v] == 3) triples++;
            else if (counts[v] == 4) quads++;
        }

        boolean flush = false;
        for (int s = 1; s <= 4; s++) {
            if (suitCounts[s] == 5) flush = true;
        }

        boolean straight = isStraight(values);

        int score = 0;

        // Print hand evaluation
        if (flush && straight) {
            System.out.println("Straight Flush! +9");
            score += 9;
        } else if (quads == 1) {
            System.out.println("Four of a Kind + 8");
            score += 8;
        } else if (triples == 1 && pairs == 1) {
            System.out.println("Full House + 7");
            score += 7;
        } else if (flush) {
            System.out.println("Flush + 6");
            score += 6;
        } else if (straight) {
            System.out.println("Straight + 5");
            score += 5;
        } else if (triples == 1) {
            System.out.println("Three of a Kind + 4");
            score += 4;
        } else if (pairs == 2) {
            System.out.println("Two Pair + 3");
            score += 3;
        } else if (pairs == 1) {
            System.out.println("One Pair + 2");
            score += 2;
        } else {
            System.out.println("High Card: " + highCard(values) + "+ 1");
            score += 1;
        }

        return score;


    }

    // Method to sort hand by value
    static void sortHandByValue(Card[] hand) {
    Arrays.sort(hand, Comparator.comparingInt(Card::getValue));
}

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int maxRounds = 5;
        int totalScore = 0;

        // Loop for each round
        for (int round = 1; round <= maxRounds; round++) {
            System.out.println("Round " + round + (" of ") + maxRounds);

        // Shuffle, deal, and organize deck based on value
        DeckOfCards deck = new DeckOfCards();
        deck.shuffle();

        Card[] playerCards = deck.dealCards(10);

        sortHandByValue(playerCards);

        // Used to check for duplicates
        boolean[] used = new boolean[10];

        // Basic Game logic, display deck and allow user to play card based on index
        System.out.println("Your cards: ");
        
        for (int i = 0; i < playerCards.length; i ++) {
            
        System.out.println(i + ": " + playerCards[i]);
            
        }

        Card[] hand1 = new Card[5];

        System.out.println("Pick a number 0-9 to play card");

        for (int i = 0; i < 5; i++) {
            System.out.println("Pick card: "); 
            int index = scanner.nextInt();
            
            // Checks for out of bounds number
            if (index < 0 || index > 9) {
                System.out.println("Invalid card, please pick again.");
                i--;
                continue;
            // Checks for duplicates
            } else if (used[index]) {
                System.out.println("You already picked that card.");
                i--;
            
            // Add played card to player card index
            } else {
            used[index] = true;
            hand1[i] = playerCards[index];
            }

        } 
        // Print final played hand, evaluate it, add to and display score
        System.out.println("Your final hand");
            for (Card c : hand1)
                System.out.println(c);

        int roundScore = evaluateAndPrint(hand1);
        totalScore += roundScore;
        System.out.println("Score: " + totalScore);
}

// Print final score
System.out.println("Final Score: " + totalScore);
scanner.close();
    }


}





    
    




        

    
        

        // // Peek at next card
        // System.out.println("\nNext card (peek): " + deck.peekNextCard());

        // // Print remaining cards
        // System.out.println("\nNext card (peek): " + deck.peekNextCard());

        // // Reset deck
        // deck.reset();
        // System.out.println("\nDeck reset. Cards Remaining: " + deck.cardsRemaining());
        // System.out.println("Next card after reset: " + deck.peekNextCard());

        // int[] hand1 = new int[5];
        // int key = 3;

        // boolean res = isElementPresent(hand1, key);
        // System.out.println("Is " + key + " present in the hand: " + res);
    
    


