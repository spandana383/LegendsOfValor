public class Sorcerer extends Hero implements Character{
    Sorcerer(String name, int mana, int str, int agi, int dex, int money, int exp) {
        super(name, mana, str, agi, dex, money, exp);
    }

    @Override
    public void skill_increase() {
        super.str_increase();
        super.agi_increase();
        super.dex_increase();
        // favored skill increase
        super.dex_increase();
        super.agi_increase();
    }
}
