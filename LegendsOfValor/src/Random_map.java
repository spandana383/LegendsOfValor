import java.util.ArrayList;
import java.util.List;

public class Random_map implements Map{
    private Cell [][] world; // array of world
    private final int world_size_x;
    private final int world_size_y;


    private int player_space_x;// set where the player is in x-axis
    private int party_space_y;

    Cell cell_1,cell_2;

    public Random_map() {

        this.world_size_x = Components.world_size_x;
        this.world_size_y = Components.world_size_y ;
        world = new Cell[world_size_y][world_size_x];
    }

    @Override
    public boolean move_up(Hero hero) {


        try{
            cell_1 = getWorldCell(hero.getChar_pos_x(), hero.getChar_pos_y());
            cell_2 = getWorldCell(hero.getChar_pos_x() - 1, hero.getChar_pos_y() );

        }
        catch(Exception e){
            TerminalPrinter.Invalid_move();
            return false;

        }

        if(cell_1.getMonster_present() || cell_2.getHero_present() || hero.getChar_pos_x() <= 0 ){
            System.out.println(cell_1.getMonster_present());
            System.out.println(cell_2.getHero_present());
            System.out.println(hero.getChar_pos_x() <= 0);
            TerminalPrinter.cannot_move();
            return false;
        }
        cell_1.setHero_present(Components.hero_not_present);
        cell_2.setHero_present(Components.hero_present);
        hero.setChar_pos_x(hero.getChar_pos_x() - 1);
        return true;
    }

    public Cell getWorldCell(int char_pos_x, int char_pos_y) {
        return world[char_pos_x][char_pos_y];
    }

    @Override
    public void move_monster_forward(Monster m) {
        getWorldCell(m.monster_pos_x, m.monster_pos_y).setMonster_present(false);
        m.setChar_pos_x(m.monster_pos_x + 1);
        getWorldCell(m.monster_pos_x + 1, m.monster_pos_y).setMonster_present(false);


    }

    @Override
    public boolean move_down(Hero hero) {
        String symbol;
        try{
            cell_1 = getWorldCell(hero.getChar_pos_x(), hero.getChar_pos_y());
            cell_2 = getWorldCell(hero.getChar_pos_x() + 1, hero.getChar_pos_y() );
            //symbol = getWorldCell( hero.getChar_pos_y(), hero.getChar_pos_x() + 1).toString();

        }
        catch(Exception e){
            TerminalPrinter.Invalid_move();
            return false;

        }

        if(cell_1.getMonster_present() || cell_2.getHero_present() || hero.getChar_pos_x() >= (Components.world_size_x-1) ){
            TerminalPrinter.cannot_move();
            return false;
        }
        cell_1.setHero_present(Components.hero_not_present);
        cell_2.setHero_present(Components.hero_present);
        hero.setChar_pos_x(hero.getChar_pos_x() + 1);
        return true;
    }

    @Override
    public boolean move_left(Hero hero) {
        String symbol;
        try {
            cell_1 = getWorldCell(hero.getChar_pos_x(), hero.getChar_pos_y());
            cell_2 = getWorldCell(hero.getChar_pos_x(), hero.getChar_pos_y() - 1);
            symbol = getWorldCell(hero.getChar_pos_y() - 1, hero.getChar_pos_x()).toString();

        } catch (Exception e) {
            TerminalPrinter.Invalid_move();
            return false;

        }

        if (cell_1.getMonster_present() || cell_2.getHero_present() || symbol.equals(Components.Inaccessible)) {
            TerminalPrinter.cannot_move();
            return false;
        }
        cell_1.setHero_present(Components.hero_not_present);
        cell_2.setHero_present(Components.hero_present);
        hero.setChar_pos_y(hero.getChar_pos_y() - 1);
        return true;
    }

    @Override
    public boolean move_right(Hero hero) {
        String symbol;
        try {
            cell_1 = getWorldCell(hero.getChar_pos_x(), hero.getChar_pos_y());
            cell_2 = getWorldCell(hero.getChar_pos_x(), hero.getChar_pos_y() + 1);
            symbol = getWorldCell(hero.getChar_pos_y() + 1, hero.getChar_pos_x()).toString();


        } catch (Exception e) {
            TerminalPrinter.Invalid_move();
            return false;

        }

        if (cell_1.getMonster_present() || cell_2.getHero_present() || symbol.equals(Components.Inaccessible)) {
            TerminalPrinter.cannot_move();
            return false;
        }
        cell_1.setHero_present(Components.hero_not_present);
        cell_2.setHero_present(Components.hero_present);
        hero.setChar_pos_y(hero.getChar_pos_y() + 1);

        return true;
    }

    @Override
    public Cell getCell() {
        return world[party_space_y][player_space_x];
    }

    public Cell[][] getWorld(){
        return world;
    }

    @Override
    public void heroRecall(Hero hero, int i) {
        try{
            cell_1 = getWorldCell(hero.getChar_pos_x(), hero.getChar_pos_y());
            cell_2 = getWorldCell(Components.intial_hero_x[i] , Components.intial_hero_y[i]);

        }
        catch(Exception e){
            TerminalPrinter.Invalid_move();

        }

        cell_1.setHero_present(false);
        cell_2.setHero_present(true);
        hero.setChar_pos_x(Components.intial_hero_x[i]);
        hero.setChar_pos_y(Components.intial_hero_y[i]);

    }

    @Override
    public void initial_map() {
        int removed_index;

        List<Cell> cell_pool = new ArrayList<Cell>();
        int space_amount = (world_size_x - 2) * (world_size_y - 2);

        for(int i = 0; i < space_amount*Components.cave_portion; i++){
            cell_pool.add(new Cave_Cell());
        }
        for(int i = 0; i < space_amount*Components.bush_portion; i++){
            cell_pool.add(new Bush_Cell());
        }
        for(int i = 0; i < space_amount*Components.koulou_portion; i++){
            cell_pool.add(new Koulou_Cell());
        }

        for(int i = 0; cell_pool.size() <= space_amount; i++){
            cell_pool.add(new Plain_Cell());
        }
        for (int x = 0; x < world_size_x; x++){ // set first n space on x-axis is common
            for(int y = 0; y < world_size_y; y++){ // set first n space on y-axis is common

                if((y == 0 || y == (world_size_x - 1)) && (x % 3) != 2){
                    world[x][y] = new Nexus_Cell();
                    world[x][y].setPos_x(x);
                    world[x][y].setPos_y(y);
                }
                else {
                    if( (x % 3) == 2 ){
                        world[x][y] = new Inaccesible_Cell();
                    }
                    else{
                        removed_index = Random_Generator.RandomIndex(cell_pool.size());
                        world[x][y] = cell_pool.get(removed_index);
                        cell_pool.remove(removed_index);
                    }
                    world[x][y].setPos_x(x);
                    world[x][y].setPos_y(y);
                }
            }
        }


    }

    public void print_random_map_1(){
        for (int x = 0; x < 8 ; x++){
            for(int y = 0; y < 8 ; y++){
                System.out.print(world[y][x].toString() + "\t\t");
            }
            System.out.println();
            System.out.println();
        }

    }
    public void print_random_map(Player player){
        String symbol;
        StringBuilder map = new StringBuilder();
        for (int x = 0; x < 32 ; x++){
            if( (x % 4) % 2 == 0 ){
                for(int y =0; y < 80; y++){


                    if((y % 2) == 1){
                        //ADD BLANK SPACES
                        map.append(" ");
                    }
                    else if( (y % 10) == 0 || (y % 10) == 4 || (y % 10) == 8){
                        //ADD THE SYMBOL

                        symbol = world[(int) Math.floor(y / 10)][(int) Math.floor(x / 4)].toString();
                        map.append(symbol);
                    }
                    else if( (y % 10) == 2 || (y % 10) == 6) {
                        //ADD THE HYPHEN
                        map.append("-");
                    }
                }

            }
            else if( (x % 4) == 1){
                for(int y =0; y < 80; y++){
                    if((y % 10) == 0 || (y % 10) == 8){
                        //ADD THE HORIZONTAL
                        map.append("|");
                    }
                    else if((y % 10) == 3 || (y % 10) == 4 || (y % 10) == 5 || (y % 10) == 9){
                        //This is where you check if the hero or monster is present in the cell or not and then print the
                        map.append(" ");

                    }
                    else if((y % 10) == 1 ){

                        if(world[x/4][y/10].getHero_present()){
                            map.append(Components.hero_symbol);

                        }

                        else{
                            map.append(" ");
                        }

                    }

                    else if( (y % 10) == 2){
                        if(world[x/4][y/10].getHero_present()){
                            map.append(get_hero_index(x/4,y/10,player));

                        }

                        else{
                            map.append(" ");
                        }

                    }
                    else if((y % 10) == 6 ){
                        if(world[x/4][y/10].getMonster_present()){
                            map.append(Components.monster_symbol);

                        }

                        else{
                            map.append(" ");
                        }

                    }
                    else if( (y % 10) == 7){
                        if(world[x/4][y/10].getMonster_present()){
                            map.append(get_monster_index(x/4,y/10,player));

                        }

                        else{
                            map.append(" ");
                        }

                    }
                }

            }
            else if((x % 4) == 3){
                for(int y =0; y < 80; y++){
                    //JUST ADD A BLANK SPACE
                    map.append(" ");
                }

            }
            map.append("\n");

            //ADD A NEW LINE CHARACTER!

        }
        System.out.println(map);

    }

    private String get_monster_index(int x, int y, Player player) {
        int i;
        for (i = 0; i < player.getMonster_party().size(); i++) {

            if (player.getMonster_party().getMembers(i).getChar_pos_x() == x && player.getMonster_party().getMembers(i).getChar_pos_y() == y) {

                break;
            }
        }
        return String.valueOf(i + 1);
    }

    private String get_hero_index(int x, int y, Player player) {
        int i;
        for (i = 0; i < player.getHeroParty().size(); i++) {
            if (player.getHeroParty().getMembers(i).getChar_pos_x() == x && player.getHeroParty().getMembers(i).getChar_pos_y() == y) {
                break;
            }
        }
        return String.valueOf(i + 1);
    }

    @Override
    public void setInitial_HeroPos() {
        for(int i =0; i < Components.max_heroes ; i++){

        }
    }


    @Override
    public boolean move(String direction, Hero hero) {
        if(direction.equals("W")) return move_up(hero);
        else if (direction.equals("A")) return move_left(hero);
        else if (direction.equals("S")) return move_down(hero);
        else if (direction.equals("D")) return move_right(hero);
        return false;
    }

}
