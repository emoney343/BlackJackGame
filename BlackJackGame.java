import java.util.Scanner;

public class BlackJackGame {
    private Player player;
    private Shop shop;
    private Scanner scanner;
    private int floor;

    public BlackJackGame() {
        this.player = new Player(30);
        this.shop = new Shop();
        this.scanner = new Scanner(System.in);
        this.floor = 1;
    }

    public void start() {
        System.out.println("=== BLACKJACK ROGUELIKE ===");
        System.out.println("You start with 30 HP. Don't bust. Don't die.\n");

        while (player.isAlive()) {
            System.out.println("\n--- FLOOR " + floor + " ---");
            player.printStatus();

            playRound();

            if (player.isAlive()) {
                shop.openShop(player, scanner);
                floor++;
            }
        }

        System.out.println("\nGame over. You survived " + (floor - 1) + " floors.");
        scanner.close();
    }

    private int calculateTotal(Card[] hand, int size) {
        int total = 0;
        int aces = 0;

        for (int i = 0; i < size; i++) {
            int value = hand[i].getValue();
            if (value == 14) {
                aces++;
                total += 11;
            } else if (value >= 10) {
                total += 10;
            } else {
                total += value;
            }
        }

        while (total > 21 && aces > 0) {
            total -= 10;
            aces--;
        }

        return total;
    }

    private void playRound() {
        DeckOfCards deck = new DeckOfCards();
        deck.shuffle();

        Card[] playerHand = new Card[11];
        Card[] dealerHand = new Card[11];
        int playerSize = 0;
        int dealerSize = 0;

        playerHand[playerSize++] = deck.dealCard();
        dealerHand[dealerSize++] = deck.dealCard();
        playerHand[playerSize++] = deck.dealCard();
        dealerHand[dealerSize++] = deck.dealCard();

        System.out.println("\nDealer shows: " + dealerHand[0] + " and [hidden]");
        System.out.println("Your hand: " + playerHand[0] + ", " + playerHand[1]);
        System.out.println("Your total: " + calculateTotal(playerHand, playerSize));

        boolean playerBust = false;
        while (true) {
        String input = "";
        while (!input.equals("h") && !input.equals("s")) {
            System.out.print("\nHit or Stand? (h/s): ");
            input = scanner.next().toLowerCase();
            if (!input.equals("h") && !input.equals("s")) {
            System.out.println("Invalid input. Please enter h or s.");
    }
}

            if (input.equals("h")) {
                playerHand[playerSize++] = deck.dealCard();
                int total = calculateTotal(playerHand, playerSize);
                System.out.println("You drew: " + playerHand[playerSize - 1]);
                System.out.println("Your total: " + total);

                if (total > 21) {
                    System.out.println("Bust! You lose 5 HP.");
                    player.takeDamage(5);
                    playerBust = true;
                    break;
                }
            } else {
                break;
            }
        }

        if (playerBust) return;

        System.out.println("\nDealer reveals: " + dealerHand[0] + ", " + dealerHand[1]);
        int dealerTotal = calculateTotal(dealerHand, dealerSize);
        System.out.println("Dealer total: " + dealerTotal);

        while (dealerTotal < 17) {
            dealerHand[dealerSize++] = deck.dealCard();
            dealerTotal = calculateTotal(dealerHand, dealerSize);
            System.out.println("Dealer hits: " + dealerHand[dealerSize - 1] + " | Total: " + dealerTotal);
        }

        int playerTotal = calculateTotal(playerHand, playerSize);

        if (dealerTotal > 21) {
            System.out.println("Dealer busts! You win!");
            player.earnGold(10 + floor * 2);
        } else if (playerTotal > dealerTotal) {
            System.out.println("You win!");
            player.earnGold(10 + floor * 2);
        } else if (dealerTotal > playerTotal) {
            System.out.println("Dealer wins. You lose 5 HP.");
            player.takeDamage(5);
        } else {
            System.out.println("Tie. No gold, no damage.");
        }
    }

    public static void main(String[] args) {
        new BlackJackGame().start();
    }
}