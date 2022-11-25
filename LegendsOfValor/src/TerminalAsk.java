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
            if(index < list.size() && index > 0){
                return list.get(index);
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

    public static String ask_which_turn(Party heroParty) {
        while(true){
            System.out.println("Play your turn ?");
            System.out.print("[W] up [A] left [S] down [D] right,\n[K] Attack [N] Potion [L] Spell [E] Equip\n" +
                    "[T] Teleport [R] Recall\n or type: [I]info [Q] quit\nPlease enter:");
            String move = s.ScanString();
            if(move.equals("W") || move.equals("A") || move.equals("S") || move.equals("D") || move.equals("Q") ||
                    move.equals("K") || move.equals("N") || move.equals("L") || move.equals("E") || move.equals("T") || move.equals("R")){
                return move;
            }
            else if(move.equals("I")) System.out.print(heroParty); // if the input is I print information
            else System.out.println("Please enter a valid move for this turn !");
        }
    }

    public static Boolean teleport_hero(Map game_map, Hero hero, Player player){
        while(true){
            int teleport_x;
            int teleport_y;
            int cur_y_pos;
            int max_x_pos = 0;
            int teleport_col;
            Boolean same_column = false;

            String type;
            System.out.println("Where Do you wanna teleport to ?");
            try{
                teleport_x  = sc.nextInt() - 1;
                teleport_y  = sc.nextInt() - 1;
                cur_y_pos = hero.getChar_pos_y();


                type = game_map.getWorldCell(teleport_y , teleport_x).toString();
                Cell cur_cell = game_map.getWorldCell(hero.getChar_pos_x(),hero.getChar_pos_y());
                Cell cell_to_teleport = game_map.getWorldCell(teleport_x , teleport_y);
                Cell monster_check_cell = game_map.getWorldCell(teleport_x - 1 , teleport_y);

                //To check if the hero is teleporting to the same column
                if(Arrays.asList(Components.hero_col_1).contains(cur_y_pos)){
                    if(Arrays.asList(Components.hero_col_1).contains(teleport_y)){
                        same_column = true;
                    }
                }
                else if(Arrays.asList(Components.hero_col_2).contains(cur_y_pos)){
                    if(Arrays.asList(Components.hero_col_2).contains(teleport_y)){
                        same_column = true;
                    }
                }
                else if(Arrays.asList(Components.hero_col_3).contains(cur_y_pos)){
                    if(Arrays.asList(Components.hero_col_3).contains(teleport_y)){
                        same_column = true;
                    }
                }

                //To check if the hero is trying to teleport forward the existing hero
                if(Arrays.asList(Components.hero_col_1).contains(teleport_y)){
                    max_x_pos = return_hero_row(player , 1);
                }
                else if(Arrays.asList(Components.hero_col_2).contains(teleport_y)){
                    max_x_pos = return_hero_row(player , 2);
                }
                else if(Arrays.asList(Components.hero_col_3).contains(teleport_y)){
                    max_x_pos = return_hero_row(player , 3);
                }
                


                if(type.equals(Components.Inaccessible) || cell_to_teleport.getHero_present() || same_column|| teleport_x < max_x_pos||monster_check_cell.getMonster_present()  ){
                    TerminalPrinter.cannot_move();
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

            }


        }

    }

    private static int return_hero_row(Player player, int col) {
        int x = 0;
        Hero hero;
        if(col == 1){
            for(int i =0 ; i < player.getHeroParty().size() ; i++ ){
                hero = (Hero) player.getHeroParty().getCharacter(i);
                if(Arrays.asList(Components.hero_col_1).contains(hero.getChar_pos_y())){
                    return hero.getChar_pos_x();
                }

            }

        }
        else if(col == 2){
            for(int i =0 ; i < player.getHeroParty().size() ; i++ ){
                hero = (Hero) player.getHeroParty().getCharacter(i);
                if(Arrays.asList(Components.hero_col_2).contains(hero.getChar_pos_y())){
                    return hero.getChar_pos_x();
                }

            }

        }
        else if(col == 3){
            for(int i =0 ; i < player.getHeroParty().size() ; i++ ){
                hero = (Hero) player.getHeroParty().getCharacter(i);
                if(Arrays.asList(Components.hero_col_3).contains(hero.getChar_pos_y())){
                    return hero.getChar_pos_x();
                }

            }

        }
        return x;
    }


}
