public class Main {
    public static void main(String[] args) {
        Spellbook spellbook = new Spellbook();
        Spell fireball = new Spell("fireball", 50, "fire", 10);
        Spell frostbite = new Spell("frostbite", 40, "frost", 10);
        Spell lightningBolt = new Spell("lightningBolt", 60, "lightning", 15);
        Spell healingWord= new Spell("healingWord", 100, "healing", 8);

        spellbook.addSpell(fireball);
        spellbook.addSpell(frostbite);
        spellbook.addSpell(healingWord);
        spellbook.addSpell(lightningBolt);

        Accessory ringOfPower = new Accessory("Ring of Power", "fire", 5, 15, 20);


        Character Sor1 = new Character(1, "Elnara Moon Snow", "Sorcerer");
        Sor1.printStatus();
        Sor1.EquippedAccessory(ringOfPower);

        Sor1.spellKnow.add(fireball);
        Sor1.spellKnow.add(frostbite);
        Sor1.spellKnow.add(lightningBolt);

        Sor1.levelUp();
        Character Cleric = new Character(2, "Pedo", "Cleric");
        Sor1.addPartyMember(Cleric);
        Cleric.spellKnow.add(healingWord);
        Sor1.printStatus();
        Sor1.castSpell("fireball", Cleric);
        Cleric.castSpell("healingWord", Cleric);
        Cleric.printStatus();
        /*Character enemy = new Character(1, "Enemy", "Fighter");
        enemy.addResistance("fire");
        enemy.addWeakness("frost");
        Sor1.castSpell("fireball", enemy);
        Sor1.castSpell("frostbite", enemy);
        Sor1.printStatus();
        Sor1.castSpell("healingWord", enemy);
        Sor1.castSpell("lightningBolt", enemy);
        enemy.printStatus();*/
    }
}
