public class IceSpell extends Spell implements Consumable{


    IceSpell(String name, int price, int level, int damage, int mana_cost, String type) {
        super(name, price, level, damage, mana_cost, type);
    }

    @Override
    public void consume(Character c) {
        super.consume(c);
    }
}

