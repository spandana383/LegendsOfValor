import java.util.List;

public class TerminalPrinter {
    static void levelUpPrint(Character c){
        System.out.println("WoW!!!! "+ c.getName()+ " level up ! "+ c.getName() + " is now level " +c.getLevel()+"\n");
    }
    static void attactPrint(Character a, Character b, int damage){ // a attack b
        System.out.println(a.getName()+ " attacks "+b.getName()+ " and causes " + damage +" damage!");
        System.out.println(b.getName() + " now has only left "+ b.getHP()+ " HP \n");
    }
    static void dodgePrint(Character a, Character b){
        System.out.println(a.getName() + " attacks " + b.getName());
        System.out.println(b.getName() + " successfully dodges\n");
    }
    static void deadPrint(Character c){
        System.out.println(c.getName()+ " is dead >.<");
    }

    static void equipable_element_Print(List<Equipable> inventory){
        for(Equipable e : inventory){
            System.out.print(e);
        }
    }
    static void remove_equipment_print(Character c, Items e){
        System.out.println(c.getName() + " removes " + e.getname() +"to bags");
    }
    static void equip_print(Character c, Items e){
        System.out.println(c.getName() + " equips " + e.getname());
    }
    static void cannot_move(){
        System.out.println("You can not move here, please select another direction");
    }
    static void gain_exp_money_print(Character c, int money, int exp){
        System.out.println(String.format("%s gains %s gold and %s exp ",c.getName(),money,exp));
    }
    static void Print_start_game(){
        System.out.println("Welcome to Heroes and Monsters RPG game");
    }
    static void already_in_party(Character c){
        System.out.println(c.getName()+" is already in the party!!");
    }
    static void join_party(Character c){
        System.out.println(c.getName()+" is joined to the party!!");
    }
/*    static void each_round_begin(Party party, Map map){
        System.out.print("This is your party: \n" + party.toString());
        System.out.println("P is your current location on the map: ");
        System.out.print(map);
    }*/

    static void Print_heroes(List<Hero> Hero_list){ // print heroes list, for player to select heroes
        String title = String.format("   %-25s %-15s %-10s %-10s %-10s %-10s %-10s %-10s %-10s\n",
                "Name", "HP", "level","mana","str","agi","dex","money","exp");
        String Warriors_out = "Here are warriors:\n" + title;
        String Sorcerers_out = "Here are sorcerers:\n"+ title;
        String Paladins_out = "Here are paladins:\n"+ title;
        for(int i = 0; i < Hero_list.size(); i++){
            Hero h = Hero_list.get(i);
            if(h instanceof Warrior){
                Warriors_out += String.format("[%2s] " + h ,i);
            }
            else if (h instanceof Sorcerer) {
                Sorcerers_out += String.format("[%2s] " + h ,i);
            }
            else if (h instanceof Paladin) {
                Paladins_out += String.format("[%2s] " + h,i);
            }
        }
        System.out.print(Warriors_out+"\n"+Sorcerers_out+"\n"+Paladins_out+"\n");
    }

    static void Print_hero_team(List<Hero> Hero_list){ // print heroes list, for player to select heroes
        System.out.println("Here is your team of recruited Heroes");
        String title = String.format("   %-25s %-15s %-10s %-10s %-10s %-10s %-10s %-10s %-10s\n",
                "Name", "HP", "level","mana","str","agi","dex","money","exp");
        String Warriors_out = "Here are warriors:\n" + title;
        String Sorcerers_out = "Here are sorcerers:\n"+ title;
        String Paladins_out = "Here are paladins:\n"+ title;
        for(int i = 0; i < Hero_list.size(); i++){
            Hero h = Hero_list.get(i);
            if(h instanceof Warrior){
                Warriors_out += String.format("[%2s] " + h ,i);
            }
            else if (h instanceof Sorcerer) {
                Sorcerers_out += String.format("[%2s] " + h ,i);
            }
            else if (h instanceof Paladin) {
                Paladins_out += String.format("[%2s] " + h,i);
            }
        }
        System.out.print(Warriors_out+"\n"+Sorcerers_out+"\n"+Paladins_out+"\n");

        System.out.println("Are you ready to being the Battle?!?!?!");
    }
    static void Print_item_cannot_buy(Items items){
        System.out.println("\n*****The hero can't buy "+items.getname()+" because of insufficient money or level*****\n");
    }
    static void Print_spell_cannot_use(){
        System.out.println("***** this spell can not use because the mana is not enough*****");
    }
    static void welcome_market(){
        System.out.println("*************************************************");
        System.out.println("**           Welcome to the Market             **");
        System.out.println("*************************************************");
    }
    static void welcome_game(){
        System.out.println("*************************************************");
        System.out.println("**       Welcome to Legends of Valor      **");
        System.out.println("*************************************************");
        System.out.println("The monsters and heroes live in a fictional world. They do not get along and therefore fight each other.\n" +
                "Every time the heroes win, they gain experience and money. Heroes use the money to buy a variety of\n" +
                "items to aid them in their battles with the monsters. When they accumulate enough experience they\n" +
                "level up, which improves their skills. The goal of the game is for the heroes to defeat monsters and level\n" +
                "up indefinitely.");

        System.out.println("You are going to form a party and try to kill some monster! ");
        System.out.println("In this world, you can buy some Weapons, Armors, Spells, Potions in the Market. Once you kill a monster,\n" +
                "the Heroes in your party will gain some gold and exp. exp will helps your heroes level up! ");
        System.out.println("* you can equip Weapon/Armor in any battle and it would not cost a turn ");
        System.out.println("* However, using Spells or Potions will cost a turn ");
    }


    static void Print_items(List<Items> item_list){
        boolean existWeapon= false, existArmor= false, existPotion= false, existSpell = false;
        String Weapons_print = "Here are Weapons: \n"+
                String.format("     %-10s %-10s %-10s %-15s %-10s\n",
                        "name", "price", "level", "damage_val", "requireHands");

        String Armors_print = "Here are Armors: \n" +
                String.format("     %-20s %-10s %-10s %-10s\n", "name", "price", "level", "reduction");

        String Potion_print = "Here are Potions: \n" +
                String.format("     %-20s %-10s %-10s %-20s %-10s\n",
                        "name", "price", "level", "attribute_increase", "attributes");
        String Spell_print = "Here are Spells: \n" +
                String.format("     %-20s %-10s %-10s %-10s %-10s %-10s\n", "name", "price", "level", "damage", "mana_cost", "spellType");
        for(int i = 0; i < item_list.size(); i++){
            Items item = item_list.get(i);
            if(item instanceof Weapon) {
                existWeapon =true;
                Weapons_print += String.format("[%2s] " + item ,i);
            }
            if(item instanceof Armor) {
                existArmor = true;
                Armors_print += String.format("[%2s] " + item ,i);
            }
            if(item instanceof Potions) {
                existPotion = true;
                Potion_print += String.format("[%2s] " + item ,i);
            }
            if(item instanceof Spell) {
                existSpell = true;
                Spell_print += String.format("[%2s] " + item ,i);
            }
        }
        String out="";
        if(existWeapon) out += (Weapons_print+"\n");
        if(existArmor) out += (Armors_print+"\n");
        if(existPotion) out += (Potion_print+"\n");
        if(existSpell) out += (Spell_print+"\n");
        System.out.print(out);
    }

    static void Print_equipable(List<Items> item_list){ // print equipable items
        String Weapons_print = "Here are Weapons: \n"+
                String.format("     %-10s %-10s %-10s %-15s %-10s\n",
                        "name", "price", "level", "damage_val", "requireHands");
        String Armors_print = "Here are Armors: \n" +
                String.format("     %-20s %-10s %-10s %-10s\n", "name", "price", "level", "reduction");
        for(int i = 0; i < item_list.size(); i++){
            Items item = item_list.get(i);
            if(item instanceof Weapon) {
                Weapons_print += String.format("[%2s] " + item ,i);
            }
            if(item instanceof Armor) {
                Armors_print += String.format("[%2s] " + item ,i);
            }

        }
        System.out.print(Weapons_print+"\n"+Armors_print+"\n");
    }
  /*  static void Print_battle_start(Party party, Party Monster_party){
        System.out.println("Your party encounter a group of Monster!!!!");
        System.out.println("*************** Battle Begin ! *****************");
        System.out.println("This is your party: ");
        System.out.print(party);
        System.out.println("Here are Monsters: ");
        System.out.print(Monster_party);
    }*/
    static void Print_buy(Hero hero, Items item){
        System.out.println(" **Cha-Ching** **Cha-Ching**");
        System.out.println(String.format("%s successfully spent %s dollar on %s", hero.getName(), item.getprice(),item.getname()));
    }

    public static void each_hero_round_begin(Hero h, Map game_map, Player player) {
        //game_map.print_random_map(player);
        System.out.println("HERO " + h.getName()+ " MAKE YOUR MOVE");


    }

    public static void Invalid_move() {
        System.out.println("Invalid Move, Try again");
    }
}
