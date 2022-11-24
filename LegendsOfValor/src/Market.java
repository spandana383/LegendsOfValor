import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Market implements Event{
    private List<Items> item_list = new ArrayList<Items>();
    private Hero hero;

    public Market() {
        item_list = ItemFactory.getItem_list();
    }

    public void PrintItems(){
        TerminalPrinter.Print_items(item_list);
    }

    public void enterMarket(Hero h){ // Hero enter to the market
        hero = h;
        System.out.println(hero.getName()+" goes in to Market!");
        while (!Player.checkQuit()){
            String buy_cell = TerminalAsk.ask_buy_sell(hero);  // ask the hero want to buy, sell, or leave
            if(buy_cell.equals("B")){ // if the player choose to buy
                buyItem();
            }else if(buy_cell.equals("L")){
                return;
            }else if(buy_cell.equals("Q")){//quit game
                Player.Quit();
                return;
            }else if(buy_cell.equals("S")){ // if the player choose to sell
                sellItem();
            }
        }
    }

    public Hero leaveMarket(){
        return hero;
    }

    public void sellItem(){
        List<Items> item_list = new ArrayList<Items>();
        item_list.addAll(hero.getConsumables_inventory());
        item_list.addAll(hero.getEquipment_inventory());

        String item_index = TerminalAsk.ask_item_to_sell(hero, item_list); // return the index that need to be sold
        if(item_index.equals("Q")){
            Player.Quit();
        } else if(item_index.equals("L")){
        } else{
            Items item = item_list.get(Integer.parseInt(item_index));// get the item that need to be sold
            hero.sell(item);
            this.item_list.add(item);
        }
    }

    public void buyItem(){
        while(!Player.checkQuit()){ // if quit is not true
            String item_index_string = TerminalAsk.ask_want_to_buy(item_list, hero);
            if(item_index_string.equals("L")){ // hero leave the market
                return;
            }
            else if(item_index_string.equals("Q")){ // quit
                Player.Quit(); // quit the game
                return;
            }
            int item_index = Integer.parseInt(item_index_string); // item_index
            if(hero.CheckBuy(item_list.get(item_index))){ // if the item can be bought
                hero.buy(item_list.remove(item_index)); // buy it
            }
            String keep = TerminalAsk.ask_keep_shopping(hero); // ask if the hero wants to keep shopping
            if(keep.equals("L")){ // leave
                return;
            }
            else if (keep.equals("Q")){ // quit
                Player.Quit();
                return;
            }
        }
    }



    @Override
    public boolean event_occur(double probability) {
        return Random_Generator.TrueFalseGen(probability);
    }



    //for test
    public static void main(String[] args) throws FileNotFoundException {
        Market m = new Market();
        new Player();
        new ItemFactory();
        new CharacterFactory();
        Hero h = (Hero) CharacterFactory.getHeroList().get(0);
        System.out.print(h);

        m.enterMarket(h);
        System.out.print(h);
    }
}
