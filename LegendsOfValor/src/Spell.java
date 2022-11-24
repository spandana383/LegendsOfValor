public class Spell implements Items, Consumable{
    private String name;
    private int level;
    private int price;
    private int mana_cost;
    private int damage;
    private String spellType;
    Spell(String name, int price, int level, int damage, int mana_cost, String type){
        this.name = name;
        this.price =price;
        this.level = level;
        this.damage = damage;
        this.mana_cost = mana_cost;
        this.spellType = type;
    }

    @Override
    public void setname(String name) {
        this.name = name;
    }
    @Override
    public String getname() {
        return this.name;
    }
    @Override
    public void setLevel(int level) {
        this.level = level;
    }
    @Override
    public int getlevel() {
        return this.level;
    }
    @Override
    public void setprice(int price) {
        this.price = price;
    }
    @Override
    public int getprice() {
        return this.price;
    }

    public int getMana_cost() {
        return mana_cost;
    }

    @Override
    public String toString() {
        return String.format("%-20s %-10s %-10s %-10s %-10s %-10s\n", name, price, level, damage, mana_cost, spellType);
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public void consume(Character c) {
        /* for extend */
    }
}