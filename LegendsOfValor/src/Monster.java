public class Monster implements Character{
    private String name;
    private int level;
    private int HP;
    private int base_damage;
    private int defense_val;
    private int dodge_ability;
    private final double defense_factor;
    private final double dodge_ability_factor;
    private final int dead_gold_factor;
    private final int dead_exp_factor;
    private final double spell_affect_factor;
    private final int level_HP_ratio;

    Monster(String name, int level, int damage, int defense, int dodge_abi){
        dodge_ability_factor = 0.01;
        dead_gold_factor = 100;
        dead_exp_factor = 2;
        spell_affect_factor = 0.1;
        level_HP_ratio = 100;
        defense_factor = 0.05;
        this.name = name;
        this.level = level;
        this.base_damage = damage;
        this.defense_val = defense;
        this.dodge_ability = dodge_abi;
        this.HP = level*level_HP_ratio;
    }

    @Override
    public void attack(Character c) { // this attack character c
        if(!c.dodge()){ // successfully attack
            c.takeDamage(base_damage, this);
        }
        else{ //successfully dodge
            TerminalPrinter.dodgePrint(this,c);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public boolean dodge() { // dodge damage
        double dodge_probability = dodge_ability * dodge_ability_factor; // calculate dodge probability
        return Random_Generator.TrueFalseGen(dodge_probability); // return true if successfully dodge
    }

    @Override
    public boolean checkAlive() {
        if(HP <= 0){
            return false;
        }
        return true;
    }

    @Override
    public void dead() {
        TerminalPrinter.deadPrint(this);
    }
    @Override
    public void takeDamage(int damage , Character hero) { // take damage by Hero
        int revise_damage = damage - (int)Math.round(defense_val*defense_factor);
        revise_damage = Math.max(0 , revise_damage); // if the damage is lower than 0, cancel it
        HP = HP - revise_damage;
        TerminalPrinter.attactPrint(hero, this, revise_damage); // print get damage
        if(!checkAlive()){
            dead();
        }
    }
    @Override
    public int getHP() {
        return HP;
    }

    @Override
    public void gainExp(int exp) {
        /* for extend*/
    }

    @Override
    public void gainGold(int extra_money) {
        /*for extend*/
    }


    public int getDeadGold() { // return gold that this monster dead;
        return level*dead_gold_factor;
    }
    public int getDeadExp(int monster_num) { // return Exp that this monster dead;
        return monster_num*dead_exp_factor;
    }
    public void getSpellAffect(Spell spell, Hero hero){ // affected by Hero's spell
        int affect_val = (int) Math.round(spell.getDamage()*spell_affect_factor);
        if(spell instanceof IceSpell){
            base_damage -= affect_val;
            System.out.println(String.format("%s use ice spell on %s", this.getName(), name));
            System.out.println(String.format("%s's base damage value decrease %s by ice spell", name, affect_val));
        }
        if(spell instanceof FireSpell){
            defense_val -= affect_val;
            System.out.println(String.format("%s use fire spell on %s", this.getName(), name));
            System.out.println(String.format("%s's base defense value decrease %s by fire spell", name, affect_val));
        }
        if(spell instanceof LightingSpell){
            dodge_ability -= affect_val;
            System.out.println(String.format("%s use lighting spell on %s", this.getName(), name));
            System.out.println(String.format("%s's base damage value decrease %s by lighting spell", name, affect_val));
        }

    }

    @Override
    public String toString() {
        String HP_string = HP+"/"+level*level_HP_ratio;
        String m = String.format("%-20s %-10s %-15s %-10s %-10s %-10s\n", name, level, HP_string, base_damage, defense_val, dodge_ability);
        return m;
    }

    @Override
    public String getTitle(){ // Name HP mana str agi dex money exp
        String title = String.format("   %-20s %-10s %-15s %-10s %-10s %-10s \n",
                "Monster's Name", "level", "HP", "damage","defense","dodge_ability");
        return title;
    }
}
