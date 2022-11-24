public class Weapon implements Items, Equipable{
    private String name;
    private int level;
    private int price;
    private int damage_val;
    private int requireHands;
    Weapon(String name, int price, int level, int damage, int requireHand){
        this.name = name;
        this.price = price;
        this.level = level;
        this.damage_val = damage;
        this.requireHands = requireHand;
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

    public void setdamage(int damage) {
        this.damage_val = damage;
    }
    public int getDamage_val() {
        return damage_val;
    }
    public void setRequireHands(int requireHands) {
        this.requireHands = requireHands;
    }
    public int getRequireHands() {
        return requireHands;
    }
    @Override
    public String toString() {
        return String.format("%-10s %-10s %-10s %-15s %-10s\n", name, price, level, damage_val, requireHands);
    }

    @Override
    public void beEquiped() {

    }
    @Override
    public void beUnequiped() {

    }

}