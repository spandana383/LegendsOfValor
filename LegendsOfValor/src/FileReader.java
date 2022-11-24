import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {  // this class is used to read files
    private final String weapon_file = "Legends_Monsters_and_Heroes/Weaponry.txt";
    private final String armor_file = "Legends_Monsters_and_Heroes/Armory.txt";
    private final String potion_file = "Legends_Monsters_and_Heroes/Potions.txt";
    private final String spell_file = "Legends_Monsters_and_Heroes/Spells.txt";
    private final String hero_file = "Legends_Monsters_and_Heroes/Heroes.txt";
    private final String monster_file = "Legends_Monsters_and_Heroes/Monsters.txt";
    private final Scanner weapon_sc;  // below this variable is the scanners
    private final Scanner armor_sc;
    private final Scanner potion_sc;
    private final Scanner spell_sc;
    private final Scanner Hero_sc;
    private final Scanner monster_sc;

    FileReader() throws FileNotFoundException {
        weapon_sc = new Scanner(new File(weapon_file));//read
        weapon_sc.nextLine(); // ignore the first
        armor_sc = new Scanner(new File(armor_file));//read
        armor_sc.nextLine(); // ignore the first line
        potion_sc = new Scanner(new File(potion_file));//read
        potion_sc.nextLine(); // ignore the first line
        spell_sc = new Scanner(new File(spell_file)); //read
        spell_sc.nextLine(); // ignore the first line
        Hero_sc = new Scanner(new File(hero_file));  //read
        Hero_sc.nextLine(); // ignore the first line
        monster_sc = new Scanner(new File(monster_file));  //read
        monster_sc.nextLine(); // ignore the first line
    }
    public String read_next_weapon() throws FileNotFoundException { // return 1 line of weapons data
        if(weapon_sc.hasNext()){
            return weapon_sc.nextLine();
        }
        weapon_sc.close();
        return "file_end";
    }
    public String read_next_armor() throws FileNotFoundException { // return 1 line of armor data
        if(armor_sc.hasNext()){
            return armor_sc.nextLine();
        }
        armor_sc.close();
        return "file_end";
    }
    public String read_next_potion(){ // return 1 line of potion data
        if(potion_sc.hasNext()){
            return potion_sc.nextLine();
        }
        potion_sc.close();
        return "file_end";
    }
    public String read_next_spell(){ // return 1 line of spell data
        if(spell_sc.hasNext()){
            return spell_sc.nextLine();
        }
        spell_sc.close();
        return "file_end";
    }
    public String read_next_Hero(){ // return 1 line of hero data
        if(Hero_sc.hasNext()){
            return Hero_sc.nextLine();
        }
        Hero_sc.close();
        return "file_end";
    }
    public String read_next_monster(){// return 1 line of monster data
        if(monster_sc.hasNext()){
            return monster_sc.nextLine();
        }
        monster_sc.close();
        return "file_end";
    }
}
