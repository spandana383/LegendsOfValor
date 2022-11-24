import java.util.List;

public class TerminalAsk {
    private static Scanin s =  new Scanin();

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
}
