import java.util.ArrayList;
import java.util.List;

public class Random_map implements Map{
    private Cell [][] world; // array of world
    private final int world_size_x;
    private final int world_size_y;


    private int player_space_x;// set where the player is in x-axis
    private int party_space_y;

    public Random_map() {

        this.world_size_x = Components.world_size_x;
        this.world_size_y = Components.world_size_y ;
        world = new Cell[world_size_y][world_size_x];
    }
    party (3 heroes)
        hero h = party.gethero(1);
        map.moveUp(h);
    @Override
    public boolean move_up(Hero hero) {
        return false;
    }

    @Override
    public boolean move_down() {
        return false;
    }

    @Override
    public boolean move_left() {
        return false;
    }

    @Override
    public boolean move_right() {
        return false;
    }

    @Override
    public Cell getCell() {
        return world[party_space_y][player_space_x];
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

                if((x == 0 || x == (world_size_x - 1)) && (y % 3) != 2){
                    world[y][x] = new Nexus_Cell();
                }
                else if( (y % 3) == 2 ){
                    world[y][x] = new Inaccesible_Cell();
                }
                else{
                    removed_index = Random_Generator.RandomIndex(cell_pool.size());
                    world[y][x] = cell_pool.get(removed_index);
                    cell_pool.remove(removed_index);
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
    public void print_random_map_2(){
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
                    else{
                        //This is where you check if the hero or monster is present in the cell or not and then print the
                        map.append(" ");

                    }
                }

            }
            else if((x % 4) == 3){
                for(int y =0; y < 80; y++){
                    //JUST ADD A BLANK SPACE IF IT'S NOT TOO MUCH TROUBLE
                    map.append(" ");
                }

            }
            map.append("\n");

            //ADD A NEW LINE CHARACTER!!!!!!

        }
        System.out.println(map);

    }


    @Override
    public boolean move(String direction) {
        return false;
    }
}
