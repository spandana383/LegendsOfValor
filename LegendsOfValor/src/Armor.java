public class Armor implements Items, Equipable{
    private String name;
    private int level;
    private int price;
    private int reduction;

    Armor(String name, int price, int level, int reduction){
        this.name = name;
        this.price = price;
        this.level = level;
        this.reduction =reduction;
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

    public void setReduction(int reduction) {
        this.reduction = reduction;
    }
    public int getReduction() {
        return reduction;
    }

    @Override
    public String toString() {
        return String.format("%-20s %-10s %-10s %-10s\n", name, price, level, reduction);
    }

    @Override
    public void beEquiped() {

    }
    @Override
    public void beUnequiped() {

    }
}
