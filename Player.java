import java.util.ArrayList;
import java.util.List;;

public class Player {
    private int hp;
    private int gold;
    private List<Modifier> modifiers;

    public Player(int hp) {
        this.hp = hp;
        this.gold = 0;
        this.modifiers = new ArrayList<>();
    }

    public int getHp() { return hp; }
    public int getGold() { return gold; }
    public List<Modifier> getModifiers() { return modifiers; }

    public void takeDamage(int amount) { hp -= amount; }
    public void earnGold(int amount) { gold += amount; }
    public void spendGold(int amount) { gold -= amount; }
    public void addModifier(Modifier m) { modifiers.add(m); }

    public boolean isAlive() { return hp > 0; }

    public void printStatus() {
        System.out.println("HP: " + hp + " | Gold: " + gold );
    }
 }
    

