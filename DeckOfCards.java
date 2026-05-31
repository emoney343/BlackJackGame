//Method to create a deck of cards

import java.security.SecureRandom;

public class DeckOfCards {
    public Card[] deck;
    public int currentCard;
    public static final int NUMBER_OF_CARDS = 52;
    public static final SecureRandom random = new SecureRandom();

    // Figure out how to display the suits symbols
    public DeckOfCards() {
        String[] faces = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        String[] suits = { "Spades", "Clubs", "Diamonds", "Hearts"};

        deck = new Card[NUMBER_OF_CARDS];
        currentCard = 0;

        for (int i=0; i < deck.length; i++) {
            deck[i] = new Card(faces[i % 13], suits[i / 13]);
        
        }

     
        }
    //Single card
    public Card dealCard() {
        if (currentCard < deck.length) return deck[currentCard++];
        return null;
    }

    //Multiple Cards
    public Card[] dealCards(int numberOfCards) {
        int remaining = cardsRemaining();
        int cardsToDeal = Math.min(numberOfCards, remaining);
        Card[] hand = new Card[cardsToDeal];
        for (int i = 0; i < cardsToDeal; i++) {
            hand[i] = dealCard();
        }
        return hand;
    }

    //Peek at next card
    public Card peekNextCard() {
        if (currentCard < deck.length) return deck[currentCard];
        return null;
    }

    // How many cards are left
    public int cardsRemaining() {
        return deck.length - currentCard;
    }

    // Check if deck is empty
    public boolean isEmpty() {
        return currentCard >= deck.length;
    }

    // Reset deck (order unchanged)
    public void reset() {
        currentCard = 0;
    }

    // Print remaining cards
    public void printRemainingCards() {
        for (int i = currentCard; i < deck.length; i++) {
            System.out.println(deck[i]);
        }
        
        
    }

    //Shuffle entire deck
public void shuffle() {
    currentCard = 0;
    for (int i=0; i < deck.length; i++) {
        int j = random.nextInt(NUMBER_OF_CARDS);
        Card temp = deck[i];
        deck[i] = deck[j];
        deck[j] = temp;
    }
}

// Shuffle only remaining undealth cards
public void reshuffleRemaining() {
    for (int i = currentCard; i < deck.length; i++){
        int j = currentCard + random.nextInt(deck.length - currentCard);
        Card temp = deck[i];
        deck[i] = deck[j];
        deck[j] = temp;
    }
    
}

}



    



