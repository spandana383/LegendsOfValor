import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class CharacterFactory {
    private static FileReader file;
    private static List<Hero> Hero_list = new ArrayList<Hero>();
    static List<Monster> Monster_list = new ArrayList<Monster>();
    CharacterFactory() throws FileNotFoundException {
        file = new FileReader();
        createHero_list();
        createMonsterList();
    }
    static Hero getHero(String name, int mana, int str, int agi, int dex, int money, int exp, String type){ // create different type of heroes
        if(type.equals("Warrior")){
            return new Warrior(name, mana, str, agi, dex, money, exp);
        }
        else if(type.equals("Sorcerer")){
            return new Sorcerer(name, mana, str, agi, dex, money, exp);
        }
        else if(type.equals("Paladin")){
            return new Paladin(name, mana, str, agi, dex, money, exp);
        }
        return null;
    }
    static Hero getFileHero(){ // get hero from file
        String Hero_next_line = file.read_next_Hero();
        if(!Hero_next_line.equals("file_end")){
            String Hero_data[] = Hero_next_line.split("\\s+");
            return getHero(Hero_data[0], Integer.parseInt(Hero_data[1]), Integer.parseInt(Hero_data[2]), Integer.parseInt(Hero_data[3]), Integer.parseInt(Hero_data[4]), Integer.parseInt(Hero_data[5]), Integer.parseInt(Hero_data[6]), Hero_data[7]);
        }
        return null;
    }
    static Monster getMonster(String name, int level, int damage, int defense, int dodge_abi, String type){ // create a monster
        if(type.equals("Dragon")){
            return new Dragon(name, level, damage, defense, dodge_abi);
        }
        else if(type.equals("Exoskeleton")){
            return new Exoskeleton(name, level, damage, defense, dodge_abi);
        }
        else if(type.equals("Spirit")){
            return new Spirit(name, level, damage, defense, dodge_abi);
        }
        return null;
    }
    static Monster getFileMonster(){ // get monster from file
        String Monster_next_line = file.read_next_monster();
        if(!Monster_next_line.equals("file_end")){
            String monster_data[] = Monster_next_line.split("\\s+");
            return getMonster(monster_data[0], Integer.parseInt(monster_data[1]), Integer.parseInt(monster_data[2]), Integer.parseInt(monster_data[3]), Integer.parseInt(monster_data[4]), monster_data[5]);
        }
        return null;
    }
    static void createHero_list(){
        while(true){
            Hero h = getFileHero();
            if(h == null) break;
            Hero_list.add(h);
        }
    }
    static List getHeroList(){
        return Hero_list;
    }
    static void PrintHeroList(){ // print out the list of heroes that read from file
        TerminalPrinter.Print_heroes(Hero_list);
    }

    static void createMonsterList(){ // create a list that contain all the monsters
        while (true){
            Monster m = getFileMonster();
            if(m == null) break;
            Monster_list.add(m);
        }
    }
    static List<Monster> getMosterList(){
        return Monster_list;
    }

    static Party getMonsterParty( int num_monsters){
        // max level of monster is 10
        List<Monster> monsterList_lev = new ArrayList<Monster>(); // list of monsters that have same level of Heroes
        for (Monster m : Monster_list){
            monsterList_lev.add(m); // add all the same level monster to the list
        }
        Party monster_party = new Party(); // monster party
        for(int i = 0; i < num_monsters; i++){
            Monster m = monsterList_lev.remove(Random_Generator.RandomIndex(monsterList_lev.size())); //
            monster_party.addMember(m);
        }
        return monster_party;
    }


/*
    public static void main(String[] args) throws FileNotFoundException { //for test
        CharacterFactory factory = new CharacterFactory();
        factory.PrintHeroList();
    }
*/
}