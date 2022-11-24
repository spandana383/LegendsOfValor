import java.util.ArrayList;
import java.util.List;

public class Potions implements Items, Consumable{
    private String name;
    private int level;
    private int price;
    private int attribute_increase;
    private List<String> attributes;
    private boolean used = false;

    Potions(String name, int price, int level, int attribute_increase, List<String> attributes){
        this.name = name;
        this.price = price;
        this.level = level;
        this.attributes = attributes;
        this.attribute_increase = attribute_increase;
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
    public void setAttributes(List<String> attributes) {
        this.attributes = attributes;
    }
    public void setAttribute_increase(int attribute_increase) {
        this.attribute_increase = attribute_increase;
    }
    @Override
    public String toString() {
        return String.format("%-20s %-10s %-10s %-20s %-10s\n", name, price, level, attribute_increase, attributes);
    }


    @Override
    public void consume(Character c) {
        Hero hero = (Hero) c;
        ((Hero) c).usePotion(this);
        System.out.println(String.format("\n%s use %s potion\n", c.getName(),this.getname()));
        for(String attribute : attributes){
            if(attribute.equals("Health")){
                hero.gainHP(attribute_increase);
            }
            else if(attribute.equals("Mana")){
                hero.gainMana(attribute_increase);
            }
            else if(attribute.equals("Strength")){
                hero.gainStrenght(attribute_increase);
            }
            else if(attribute.equals("Dexterity")){
                hero.gainDex(attribute_increase);
            }
            else if(attribute.equals("Agility")){
                hero.gainAgi(attribute_increase);
            }
        }
    }
}
