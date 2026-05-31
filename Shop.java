import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shop {
    private List<Modifier> allModifiers;

    public Shop() {
        allModifiers = new ArrayList<>();


        // Rule modifiers
        allModifiers.add(new Modifier("Double Down", "You can double your bet once per round", ModifierType.RULE));
        allModifiers.add(new Modifier("Second Chance", "Once per run, ignore a bust", ModifierType.RULE));
        allModifiers.add(new Modifier("Blackjack Bonus", "Blackjack pays triple gold", ModifierType.RULE));

        // Card modifiers
        allModifiers.add(new Modifier("Wild Sevens", "All 7s count as any value 1-11", ModifierType.CARD));
        allModifiers.add(new Modifier("Ace Up", "Start each hand with an Ace", ModifierType.CARD));
        allModifiers.add(new Modifier("Light Deck", "All face cards worth 8 instead of 10", ModifierType.CARD));

        // Dealer modifiers
        allModifiers.add(new Modifier("Exposed", "Dealer shows both cards", ModifierType.DEALER));
        allModifiers.add(new Modifier("Slow Dealer", "Dealer must hit until 19", ModifierType.DEALER));
        allModifiers.add(new Modifier("Clumsy", "Dealer busts on 22 instead of 21", ModifierType.DEALER));
    }
    public void openShop(Player player, Scanner scanner) {
        System.out.println("\n--- SHOP ---");
        System.out.println("Gold: " + player.getGold());
        System.out.println("Pick a modifier (costs 10 gold) or skip:\n:");

        // Pick 3 random modifiers to offer
        List <Modifier> offered = new ArrayList<>();
        List <Modifier> pool = new ArrayList<>(allModifiers);

        for ( int i = 0; i < 3; i++ ) {
            int index = (int) (Math.random() * pool.size());
            offered.add(pool.remove(index));
        }

        // Display them
        for (int i = 0; i < offered.size(); i++) {
            System.out.print(i + ": ");
            offered.get(i).printModifier();
        }

        System.out.println("3: skip");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();

        if (choice >= 0 && choice < 3) {
            if (player.getGold() >= 10) {
                player.spendGold(10);
                player.addModifier(offered.get(choice));
                System.out.println("Bought: " + offered.get(choice).getName());
            } else {
                System.out.println("Not enough gold!");
            }
        } else {
            System.out.println("Skipped shop.");
        }
     }
}



