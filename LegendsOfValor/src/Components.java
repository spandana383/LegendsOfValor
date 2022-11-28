import java.lang.reflect.Array;
import java.util.ArrayList;

public class Components {
    public static int world_size_x = 8;
    public static int world_size_y = 8;


    public static double bush_portion = 0.2;
    public static double cave_portion = 0.2;

    public static double koulou_portion = 0.2;

    public static int max_heroes = 3;

    public static int[] intial_hero_y = new int[]{ 7 , 7 , 7 };
    public static int[] intial_hero_x = new int[]{ 0 , 3 , 6 };

    public static int[] intial_monster_y = new int[]{ 0 , 0 , 0 };
    public static int[] intial_monster_x = new int[]{ 1 , 4 , 7 };

    public static String hero_symbol = "H";
    public static String monster_symbol = "M";

    public static Boolean hero_present = true;
    public static Boolean hero_not_present = false;

    public static Boolean monster_present = true;
    public static Boolean monster_not_present = false;

    public static String move_up = "w";
    public static String move_down = "s";
    public static String move_left = "a";
    public static String move_right = "d";

    public static String show_inventory = "i";
    public static String quit_game = "q";

    public static String attack = "K";
    public static String use_spell = "L";
    public static String use_potion = "N";
    public static String equip = "E";
    public static String teleport = "T";
    public static String recall = "R";
    public static String Inaccessible = "I";
    public static String enterMarket = "M";

    public static String[] direction = {"W", "S", "A", "D"};

    public static int[] hero_col_1 = {0,1};
    public static int[] hero_col_2 = {3,4};
    public static int[] hero_col_3 = {6,7};





}
