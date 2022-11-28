import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TerminalAsk {
    private static Scanin s =  new Scanin();
    private static Scanner sc =  new Scanner(System.in);

    static Hero ask_which_hero(List<Hero> list){ // ask Hero we need to add in the party

        while(true){
            System.out.print("Which hero you want to select? Enter the number: ");
            String num = s.ScanString();
            try {
                Integer.parseInt(num);
            }catch(Exception e) {
                System.out.println("invalid input! please input a integer\n");
                continue;
            }
            int index = Integer.parseInt(num);
            if(index < list.size() && index >= 0){
                return list.get(index);
            }
            else {
                System.out.println("input is out of range !!");
                continue;
            }
        }
    }

    static String ask_buy_sell(Hero hero){
        while(true){
            System.out.println(String.format("What would %s want to do?", hero.getName(),hero.getName()));
            System.out.println("[B] buy items    [S] sell items   [L] leave  [Q] quit game ");
            System.out.print("Please Enter: ");
            String input = s.ScanString();
            if(input.equals("B") || input.equals("L") || input.equals("Q")){
                return input;
            }
            else if(input.equals("S")){
                if(hero.getEquipment_inventory().isEmpty() && hero.getConsumables_inventory().isEmpty()){
                    System.out.println("The Hero do not have items to sell!");
                    continue;
                }
                else return input;
            } else{
                System.out.println("Please use a valid input!!");
            }

        }
    }
    static String ask_want_to_buy(List<Items> market_items, Hero hero){
        TerminalPrinter.Print_items(market_items);
        while(true){ // ask player which item they want to buy
            System.out.println(String.format("Which item do %s wants to buy? current remain money %s",hero.getName(), hero.getMoney()));
            System.out.print("If you don't want to buy, press [L]: leave, [I] see all items. Please Enter the number:" );
            String index = s.ScanString();
            if(index.equals("I") ) { // quit
                TerminalPrinter.Print_items(market_items);
                continue;
            }
            else if (index.equals("Q")||index.equals("L") ) { // quit the game
                return index;
            }
            else{
                try {
                    Integer.parseInt(index);
                }catch(Exception e) {
                    System.out.println("invalid input! please input a integer\n");
                    continue;
                }
            }
            int index_int = Integer.parseInt(index);
            if(0 <= index_int && index_int < market_items.size()){
                return index;
            }
            System.out.println("Please select a item again");
        }
    }

    static String ask_item_to_sell(Hero hero, List<Items> item_list){
        if(item_list.isEmpty()){ // when the list is empty
            System.out.println("There is no items that can be sell!!!");
            return "L";
        }
        System.out.println(String.format("\nHere are %s have: ",hero.getName()));
        TerminalPrinter.Print_items(item_list);
        while(true){
            System.out.println(String.format("Which items should be sold?"));
            System.out.println("[I] info [L] leave [Q] quit");
            System.out.print("Please Enter the number: ");
            String index = s.ScanString();
            if(index.equals("I") ) { // quit
                TerminalPrinter.Print_items(item_list);
                continue;
            }
            else if (index.equals("Q") || index.equals("L") ) { // quit the game
                return index;
            }
            else{
                try {
                    Integer.parseInt(index);
                }catch(Exception e) {
                    System.out.println("invalid input! please input a integer\n");
                    continue;
                }
            }
            int index_int = Integer.parseInt(index);
            if(0 <= index_int && index_int < item_list.size()){
                return index;
            }
            System.out.println("Please select a item again");
        }
    }

    static String ask_keep_shopping(Hero hero){
        while(true){// ask the player if they want to keep shopping or not
            System.out.println(String.format("Does %s still have items to buy?", hero.getName()));
            System.out.print("[S] stay in the market, [L] leave. Please enter:");
            String input = s.ScanString();
            if(input.equals("S") || input.equals("L") || input.equals("Q")){
                return input;
            }
            else System.out.println("Please enter a valid input!");
        }
    }

    public static String ask_which_turn(Player player) {
        while(true){
            System.out.println("Play your turn ?");
            System.out.print("[W] up [A] left [S] down [D] right,\n[K] Attack [N] Potion [L] Spell [E] Equip\n" +
                    "[T] Teleport [R] Recall [M] Enter Market\n or type: [I]hero info [MI] monster info [Q] quit\nPlease enter:");
            String move = s.ScanString();
            if(move.equals("W") || move.equals("A") || move.equals("S") || move.equals("D") || move.equals("Q") ||
                    move.equals("K") || move.equals("N") || move.equals("L") || move.equals("E") || move.equals("T") || move.equals("R")|| move.equals("M")){
                return move;
            }
            else if(move.equals("I")) System.out.print(player.getHeroParty()); // if the input is I print information
            else if(move.equals("MI")) System.out.print(player.getMonsterParty());
            else System.out.println("Please enter a valid move for this turn !");
        }
    }

    public static Boolean teleport_hero(Map game_map, Hero hero, Player player){ // this method is for teleporting
        // return true if we can teleport, otherwise return false
        while(true){
            int teleport_x;
            int teleport_y;
            int cur_x_pos;
            int min_y_pos = 0;
            int teleport_col;
            Boolean same_column = false;

            String type;
            System.out.println("Where Do you wanna teleport to ?");
            try{
                System.out.print("Please enter col :");
                teleport_x  = sc.nextInt(); //modify the row/col
                sc.nextLine();
                System.out.print("Please enter row :");
                teleport_y  = sc.nextInt();
                sc.nextLine();
                cur_x_pos = hero.getChar_pos_x();


                type = game_map.getWorldCell(teleport_x , teleport_y).toString();
                Cell cur_cell = game_map.getWorldCell(hero.getChar_pos_x(),hero.getChar_pos_y());
                Cell cell_to_teleport = game_map.getWorldCell(teleport_x , teleport_y);
                Cell monster_check_cell = game_map.getWorldCell(teleport_x - 1 , teleport_y);

                //To check if the hero is teleporting to the same column
                if(CharInLen(Components.hero_col_1, cur_x_pos)){
                    if(CharInLen(Components.hero_col_1, teleport_x)){
                        same_column = true;
                    }
                }
                else if(CharInLen(Components.hero_col_2, cur_x_pos)){
                    if(CharInLen(Components.hero_col_2, teleport_x)){
                        same_column = true;
                    }
                }
                else if(CharInLen(Components.hero_col_3, cur_x_pos)){
                    if(CharInLen(Components.hero_col_3, teleport_x)){
                        same_column = true;
                    }
                }

                //To check if the hero is trying to teleport forward the existing hero
                if(CharInLen(Components.hero_col_1, teleport_x)){
                    min_y_pos = return_hero_row(player , 1);
                }
                else if(CharInLen(Components.hero_col_2, teleport_x)){
                    min_y_pos = return_hero_row(player , 2);
                }
                else if(CharInLen(Components.hero_col_3, teleport_x)){

                    min_y_pos = return_hero_row(player , 3);
                }
                // check we can teleport or not
                if(type.equals(Components.Inaccessible) || cell_to_teleport.getHero_present() || same_column|| teleport_y < min_y_pos||monster_check_cell.getMonster_present()){
                    TerminalPrinter.cannot_move();
                    return false;
                }
                else{
                    cur_cell.setHero_present(false) ;
                    cell_to_teleport.setHero_present(true);
                    hero.setChar_pos_x(teleport_x);
                    hero.setChar_pos_y(teleport_y);
                    return true;
                }
            }
            catch(Exception e){
                TerminalPrinter.cannot_move();
                return false;
            }
        }

    }

    private static int return_hero_row(Player player, int len) {  // this class is for returning the heroes max position in each len
        int min_y = 7;
        Hero hero;
        if(len == 1){
            for(int i =0 ; i < player.getHeroParty().size() ; i++ ){ // return the position if there is hero in len 1
                hero = (Hero) player.getHeroParty().getCharacter(i);
                if(CharInLen(Components.hero_col_1, hero.getChar_pos_x())){
                    min_y = Math.min(min_y, hero.getChar_pos_y());
                }
            }
        }
        else if(len == 2){
            for(int i =0 ; i < player.getHeroParty().size() ; i++ ){// return the position if there is hero in len 2
                hero = (Hero) player.getHeroParty().getCharacter(i);
                if(CharInLen(Components.hero_col_2, hero.getChar_pos_x())){
                    min_y = Math.min(min_y, hero.getChar_pos_y());
                }
            }
        }
        else if(len == 3){
            for(int i = 0 ; i < player.getHeroParty().size() ; i++ ){// return the position if there is hero in len 3
                hero = (Hero) player.getHeroParty().getCharacter(i);
                if(CharInLen(Components.hero_col_3, hero.getChar_pos_x())){
                    min_y = Math.min(min_y, hero.getChar_pos_y());
                }
            }
        }
        return min_y;
    }

    private static boolean CharInLen(int[] hero_col, int col){
        for (int element : hero_col) {
            if (element == col) {
                return true;
            }
        }
        return false;
    }

    static String ask_which_potion_spell(Hero hero, List<Items> Cosumables){ //ask which potion or spell the player wants to use in a battle
        if(Cosumables.isEmpty()){ // when the list is empty
            System.out.println("There is no Spell/Potion that can be used!!!");
            return "L";
        }
        System.out.println(String.format("\nHere are %s have: ",hero.getName()));
        TerminalPrinter.Print_items(Cosumables);
        while(true){
            System.out.println(String.format("Which Spell/Potion should %s use?",hero.getName()));
            System.out.println("[I] info [L] leave [Q] quit");
            System.out.print("Please Enter the number: ");
            String index = s.ScanString();
            if(index.equals("I") ) { // quit
                TerminalPrinter.Print_items(Cosumables);
                continue;
            }
            else if (index.equals("Q")||index.equals("L") ) { // quit the game
                return index;
            }
            else{
                try {
                    Integer.parseInt(index);
                }catch(Exception e) {
                    System.out.println("invalid input! please input a integer\n");
                    continue;
                }
            }
            int index_int = Integer.parseInt(index);
            if(0 <= index_int && index_int < Cosumables.size()){
                return index;
            }
            System.out.println("Please select a item again");
        }
    }

    static String ask_equip(Hero hero, List<Items> equiptments){ //ask player which equipment should the hero equip?
        if(equiptments.isEmpty()){ // when the list is empty
            System.out.println("There is no items that can be equiped!!!");
            return "L";
        }
        System.out.println(String.format("\n Here are equipments %s have: ",hero.getName()));
        TerminalPrinter.Print_items(equiptments);
        while(true){
            System.out.println(String.format("Which equipment should %s equip?",hero.getName()));
            System.out.println("[I] info [L] leave [Q] quit");
            System.out.print("Please Enter the number: ");
            String index = s.ScanString();
            if(index.equals("I") ) { // quit
                TerminalPrinter.Print_items(equiptments);
                continue;
            }
            else if (index.equals("Q")||index.equals("L") ) { // quit the game
                return index;
            }
            else{
                try {
                    Integer.parseInt(index);
                }catch(Exception e) {
                    System.out.println("invalid input! please input a integer\n");
                    continue;
                }
            }
            int index_int = Integer.parseInt(index);
            if(0 <= index_int && index_int < equiptments.size()){
                return index;
            }
            System.out.println("Please select a item again");
        }
    }


}
