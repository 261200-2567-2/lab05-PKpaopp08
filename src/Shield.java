public class Shield {
    private String name;
    private int damageReduction;
    private int runSpeedPenalty;

    public Shield(String name, int damageReduction, int runSpeedPenalty) {
        this.name = name;
        this.damageReduction = damageReduction;
        this.runSpeedPenalty = runSpeedPenalty;
    }

    public int reduceDamage(int damage) {
        return Math.max(0, damage - damageReduction);
    }

    public int getRunSpeedPenalty() {
        return runSpeedPenalty;
    }

    public String getName() {
        return name;
    }
}
