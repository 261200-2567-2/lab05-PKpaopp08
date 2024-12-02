public class Accessory {
    private String name;
    private String resistanceBonus;
    private int speedBonus;
    private int spellDamageBonus;
    private int manaBonus;

    public Accessory(String name, String resistanceBonus, int speedBonus, int spellDamageBonus, int manaBonus) {
        this.name = name;
        this.resistanceBonus = resistanceBonus;
        this.speedBonus = speedBonus;
        this.spellDamageBonus = spellDamageBonus;
        this.manaBonus = manaBonus;
    }

    public String getName() {
        return name;
    }
    public String getResistanceBonus() {
        return resistanceBonus;
    }
    public int getSpeedBonus() {
        return speedBonus;
    }
    public int getSpellDamageBonus() {
        return spellDamageBonus;
    }
    public int getManaBonus() {
        return manaBonus;
    }

}
