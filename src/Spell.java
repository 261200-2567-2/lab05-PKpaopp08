import java.util.ArrayList;
import java.util.List;

public class Spell {
    private String name;
    private int baseDamage;
    private String damageType;
    private int manaCost;

    public Spell(String name, int baseDamage, String damageType, int manaCost) {
        this.name = name;
        this.baseDamage = baseDamage;
        this.damageType = damageType;
        this.manaCost = manaCost;
    }

    public String getName() {
        return name;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public String getDamageType() {
        return damageType;
    }

    public int getManaCost() {
        return manaCost;
    }
}


class Spellbook {
    private List<Spell> AllSpell;


    public Spellbook() {
        this.AllSpell = new ArrayList<>();
    }

    public void addSpell(Spell spell) {
        AllSpell.add(spell);
        System.out.println(spell.getName() + " has been added to the Spellbook.");
    }
    public void removeSpell(String spellName) {
        for (Spell spell : AllSpell) {
            if (spell.getName().equals(spellName)) {
                AllSpell.remove(spell);
                System.out.println(spellName + " has been removed from the Spellbook.");
                return;
            }
        }
        System.out.println(spellName + " not found in the Spellbook.");
    }


    public Spell getSpellByName(String spellName) {
        for (Spell spell : AllSpell) {
            if (spell.getName().equals(spellName)) {
                return spell;
            }
        }
        return null;
    }

    public void printAllSpells() {
        if (AllSpell.isEmpty()) {
            System.out.println("No AllSpell in the Spellbook.");
            return;
        }
        System.out.println("All Spells in the Spellbook:");
        for (Spell spell : AllSpell) {
            System.out.println("- " + spell.getName() + " (Type: " + spell.getDamageType() +
                    ", Damage: " + spell.getBaseDamage() + ", Mana Cost: " + spell.getManaCost() + ")");
        }
    }


}

