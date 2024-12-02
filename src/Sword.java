public class Sword {
    private String name;
    private int level;
    private int baseDamage;
    private int spellDamage;
    private int runSpeedPenalty;

    public Sword(String name, int level, int baseDamage,int spellDamage , int runSpeedPenalty) {
        this.name = name;
        this.level = level;
        this.baseDamage = baseDamage;
        this.spellDamage = spellDamage;
        this.runSpeedPenalty = runSpeedPenalty;
    }

    public int calculateDamage() {
        return baseDamage + (level * 5);
    }
    public int calculateSpeelDamage() {
        return spellDamage + (level * 10);
    }
    public int getRunSpeedPenalty() {
        return runSpeedPenalty;
    }

    public String getName() {
        return name;
    }
}
