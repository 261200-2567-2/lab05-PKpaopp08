import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Character {
    public String name;
    private String characterClass;
    private int level;
    private int baseSpeed;
    private int currentSpeed;
    private int baseDamage;
    private int spellDamage;
    private int maxHP;
    private int currentHP;
    private int maxMana;
    private int currentMana;
    private List<Character> partyMembers;
    private List<String> resistances;
    private List<String> weaknesses;
    public List<Spell> spellKnow;
    private List<String> accessories;

    private Sword equippedSword;
    private Shield equippedShield;
    private Accessory equippedAccessory;

    public Character(int level, String name, String characterClass) {
        this.level = level;
        this.name = name;
        this.characterClass = characterClass;
        this.partyMembers = new ArrayList<>();
        this.resistances = new ArrayList<>();
        this.weaknesses = new ArrayList<>();
        this.spellKnow = new ArrayList<>();
        this.accessories = new ArrayList<>();

        this.baseSpeed = 100;
        this.currentSpeed = baseSpeed;
        this.baseDamage = calculateBaseDamage();
        this.spellDamage = calculateSpellDamage();
        this.maxHP = calculateMaxHP();
        this.currentHP = maxHP;
        this.maxMana = calculateMaxMana();
        this.currentMana = maxMana;

        // Test resistances/weaknesses
        // resistances.add("fire");
        // weaknesses.add("frost");
    }

    private int calculateMaxHP() {
        return 100 + (level * 20);
    }

    private int calculateMaxMana() {
        return 50 + (level * 10);
    }

    private int calculateBaseDamage() {
        return 10 + (level * 5);
    }

    private int calculateSpellDamage() {
        return 20 + (level * 10);
    }

    public void levelUp() {
        level++;
        maxHP = calculateMaxHP();
        currentHP = maxHP;
        maxMana = calculateMaxMana();
        currentMana = maxMana;
        baseDamage = calculateBaseDamage();
        spellDamage = calculateSpellDamage();
        System.out.println(name + " leveled up to " + level + "!");
    }
    public void EquippedAccessory(Accessory accessory) {
        equippedAccessory = accessory;
        System.out.println(name + " equipped accessory: " + accessory.getName());
        currentSpeed += accessory.getSpeedBonus();
        spellDamage += accessory.getSpellDamageBonus();
        maxMana += accessory.getManaBonus();
        resistances.add(accessory.getResistanceBonus());
    }
    public void addResistance(String resistance) {
        resistances.add(resistance);
    }
    public void addWeakness(String weakness) {
        weaknesses.add(weakness);
    }

    public void equipSword(Sword sword) {
        equippedSword = sword;
        calculateBaseDamage();
        calculateSpellDamage();
        updateRunSpeed();
        System.out.println(name + " equipped sword: " + sword.getName());
    }

    public void equipShield(Shield shield) {
        equippedShield = shield;
        updateRunSpeed();
        System.out.println(name + " equipped shield: " + shield.getName());
    }

    public void equipAccessory(Accessory accessory) {
        equippedAccessory = accessory;
        System.out.println(name + " equipped accessory: " + accessory.getName());
    }

    private void updateRunSpeed() {
        int swordPenalty = (equippedSword != null) ? equippedSword.getRunSpeedPenalty() : 0;
        int shieldPenalty = (equippedShield != null) ? equippedShield.getRunSpeedPenalty() : 0;
        currentSpeed = baseSpeed - swordPenalty - shieldPenalty;
    }
    public void askForAttack(Character target) {

        System.out.println(target.name + " is in your party. Do you really want to attack? (y/n)");

        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine().toLowerCase();

        if (response.equals("y")) {
            int damage = (equippedSword != null) ? equippedSword.calculateDamage() : baseDamage;
            if (target.checkWeakness("physical")) {
                damage *= 2;
            } else if (target.checkResistance("physical")) {
                damage /= 2;
            }
            target.takeDamage(damage);
            System.out.println(name + " attacked " + target.name + " for " + damage + " damage!");
        } else {
            System.out.println(name + " decided not to attack " + target.name + ".");
        }

    }
    public void askForCastSpell(String spellName,Character target) {
        System.out.println(target.name + " is in your party. Do you really want to cast "+ spellName +" ? (y/n)");

        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine().toLowerCase();

        if (response.equals("y")) {
            Spell spell = spellKnow.stream()
                    .filter(s -> s.getName().equals(spellName))
                    .findFirst()
                    .orElse(null);

            if (spell == null) {
                System.out.println(name + " doesn't know the spell: " + spellName);
                return;
            }
            if (currentMana < spell.getManaCost()) {
                System.out.println(name + " doesn't have enough mana!");
                return;
            }
            int damage = spell.getBaseDamage();
            currentMana -= spell.getManaCost();

            if (spell.getDamageType() == "healing") {
                target.healing(damage);
            } else {
                if (target.checkWeakness(spell.getDamageType())) {
                    damage *= 2;
                } else if (target.checkResistance(spell.getDamageType())) {
                    damage /= 2;
                }
                target.takeDamage(damage);
                System.out.println(name + " cast " + spell.getName() + " on " + target.name + " for " + damage + " damage!");
            }
        } else {
            System.out.println(name + " decided not to attack " + target.name + ".");
        }
    }
    public void attack(Character target) {
        if (partyMembers.contains(target)) {
            askForAttack(target);
        }else {
            int damage = (equippedSword != null) ? equippedSword.calculateDamage() : baseDamage;
            if (target.checkWeakness("physical")) {
                damage *= 2;
            } else if (target.checkResistance("physical")) {
                damage /= 2;
            }
            target.takeDamage(damage);
            System.out.println(name + " attacked " + target.name + " for " + damage + " damage!");
        }
    }

    public void castSpell(String spellName, Character target) {
        if (partyMembers.contains(target)) {
            askForCastSpell(spellName,target);
        }else {
            Spell spell = spellKnow.stream()
                    .filter(s -> s.getName().equals(spellName))
                    .findFirst()
                    .orElse(null);

            if (spell == null) {
                System.out.println(name + " doesn't know the spell: " + spellName);
                return;
            }
            if (currentMana < spell.getManaCost()) {
                System.out.println(name + " doesn't have enough mana!");
                return;
            }
            int damage = spell.getBaseDamage();
            currentMana -= spell.getManaCost();

            if (spell.getDamageType() == "healing") {
                target.healing(damage);
            } else {
                if (target.checkWeakness(spell.getDamageType())) {
                    damage *= 2;
                } else if (target.checkResistance(spell.getDamageType())) {
                    damage /= 2;
                }
                target.takeDamage(damage);
                System.out.println(name + " cast " + spell.getName() + " on " + target.name + " for " + damage + " damage!");
            }
        }


    }
    public void healing(int damage){
        if(currentHP + damage > maxHP){
            currentHP = maxHP;
            currentHP = Math.max(currentHP, 0);
        }else {
            currentHP += damage;
            currentHP = Math.max(currentHP, 0);
        }
        System.out.println(name + " heal " + damage + " Current HP: " + currentHP);
    }

    public void takeDamage(int damage) {
        int reducedDamage = (equippedShield != null) ? equippedShield.reduceDamage(damage) : damage;
        if(currentHP < damage ){
            currentHP = maxHP;
            currentHP = Math.max(currentHP, 0);
            System.out.println(name + " took " + reducedDamage + " damage. Current HP: 0 (dead)" );
        }else {
            currentHP -= reducedDamage;
            currentHP = Math.max(currentHP, 0);
            System.out.println(name + " took " + reducedDamage + " damage. Current HP: " + currentHP);
        }


    }

    public boolean checkResistance(String type) {
        return resistances.contains(type);
    }

    public boolean checkWeakness(String type) {
        return weaknesses.contains(type);
    }
    public void addPartyMember(Character newMember) {
        if (!partyMembers.contains(newMember)) {
            partyMembers.add(newMember);
            System.out.println(newMember.name + " has joined the party!");
            newMember.addPartyMember(this);
        } else {
            System.out.println(newMember.name + " is already a party member.");
        }
    }

    public void printStatus() {
        System.out.println("Name: " + name +
                "\nClass: " + characterClass +
                "\nLevel: " + level +
                "\nHP: " + currentHP + "/" + maxHP +
                "\nMana: " + currentMana + "/" + maxMana +
                "\nSpeed: " + currentSpeed +
                "\nBase Damage: " + baseDamage +
                "\nSpell Damage: " + spellDamage);

        System.out.println("Party Members:");
        if (partyMembers.isEmpty()) {
            System.out.println("- None");
        } else {
            for (Character member : partyMembers) {
                System.out.println("- " + member.name);
            }
        }

        System.out.println("Resistances:");
        if (resistances.isEmpty()) {
            System.out.println("- None");
        } else {
            for (String resistance : resistances) {
                System.out.println("- " + resistance);
            }
        }

        System.out.println("Weaknesses:");
        if (weaknesses.isEmpty()) {
            System.out.println("- None");
        } else {
            for (String weakness : weaknesses) {
                System.out.println("- " + weakness);
            }
        }

        System.out.println("Spells Known:");
        if (spellKnow.isEmpty()) {
            System.out.println("- None");
        } else {
            for (Spell spell : spellKnow) {
                System.out.println("- " + spell.getName() + " (Type: " + spell.getDamageType() + ", Mana Cost: " + spell.getManaCost() + ")");
            }
        }
    }

    public void showSpells() {
        System.out.println(name + "'s Spells:");
        for (Spell spell : spellKnow) {
            System.out.println("- " + spell.getName() + " (Type: " + spell.getDamageType() + ", Mana Cost: " + spell.getManaCost() + ")");
        }
    }
}
