package rpg.item;

import java.io.Serializable;

public class Item implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum ItemType { HEALTH_POTION, WEAPON, ARMOR }

    private final String name;
    private final ItemType type;
    private final int value; // Healing amount or bonus damage/defense

    public Item(String name, ItemType type, int value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public String getName() { return name; }
    public ItemType getType() { return type; }
    public int getValue() { return value; }

    @Override
    public String toString() {
        return name + " (" + type + ": +" + value + ")";
    }
}