public class Modifier {
    private String name;
    private String description;
    private ModifierType type;

    public Modifier(String name, String description, ModifierType type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public String getName() { return name; }
    public String getDescription() { return description; } 
    public ModifierType getType() { return type; }

    public void printModifier() {
        System.out.println("[" + type + "] " + name + " - " + description);
    }
}
