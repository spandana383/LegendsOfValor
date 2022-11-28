import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class LegendsOfValorGame implements Game {


    private Map game_map;
    private Market market = new Market();

    private Player player = new Player();
    private int max_heroes;
    private int monster_level = 1;
    private int num_monsters = 3;


    LegendsOfValorGame() throws FileNotFoundException {
        max_heroes = Components.max_heroes;
        new CharacterFactory();
        new ItemFactory();

    }
    @Override
    public void startARound() {
        int round_counter = 0;
        while(!player.checkQuit()) {
            round_counter++;
            Boolean validMove;
            for (int i = 0; i < player.getHeroParty().size(); i++) {
                Hero hero = (Hero) player.getHeroParty().getMembers(i);

                if (!player.checkQuit()) {
                    validMove = false;
                    while (!validMove) {

                        TerminalPrinter.each_hero_round_begin(hero, i, game_map, player);
                        String turn = TerminalAsk.ask_which_turn(player);
                        if(turn.equals("Q")){ player.Quit(); return;} // quit // if the input is Q, quit the game
                        if (Arrays.asList(Components.direction).contains(turn)) { // move position on map, true if we successfully move
                            if (game_map.move(turn, (Hero) player.getHeroParty().getMembers(i))) {
                                validMove = true;
                            }

                        } else if (turn.equals(Components.attack)) {
                            int monster_index = game_map.can_attack_monster(hero, player); // the monster index that will be attacked
                            if(monster_index != -1){ // if the hero can attack
                                validMove = true;
                                Monster monster = (Monster) player.getMonster_party().getCharacter(monster_index); // monster being attacked
                                hero.attack(monster); // attack the monster
                                if(!monster.checkAlive()){ // if monster is dead
                                    removed_dead_monster(monster); // if monster is dead, remove
                                    hero.gainMoneyExp(monster.getDeadGold(), monster.getDeadExp(1)); // get monster's gold and exp
                                }
                            }
                            else{
                                TerminalPrinter.cant_attack();
                            }

                        } else if (turn.equals(Components.use_potion)) {
                            String index_string = TerminalAsk.ask_which_potion_spell(hero, hero.getConsumables_inventory());
                            if(index_string.equals("L")){ continue;} // leave
                            else if(index_string.equals("Q")){ player.Quit(); break;} // quit
                            else{
                                int index = Integer.parseInt(index_string);
                                Items item = hero.getConsumables_inventory().get(index); // get the item that is selected
                                if (item instanceof Potions) {
                                    validMove = true;
                                    Potions potion = (Potions) item;
                                    potion.consume(hero); // use Potion
                                }
                                else{
                                    TerminalPrinter.not_potion();
                                }
                            }

                        } else if (turn.equals(Components.use_spell)) {
                            String index_string = TerminalAsk.ask_which_potion_spell(hero, hero.getConsumables_inventory());
                            if(index_string.equals("L")){ continue;} // leave
                            else if(index_string.equals("Q")){ player.Quit(); break;} // quit
                            else{
                                int index = Integer.parseInt(index_string);
                                Items item = hero.getConsumables_inventory().get(index); // get the item that is selected
                                if (item instanceof Spell) {
                                    validMove = true;
                                    // here is same code as attack
                                    int monster_index = game_map.can_attack_monster(hero, player); // the monster index that will be attacked
                                    if(monster_index != -1){ // if the hero can attack
                                        validMove = true;
                                        Monster monster = (Monster) player.getMonster_party().getCharacter(monster_index); // monster being attacked
                                        hero.useSpell(monster, index); //use spell to attack the monster
                                        if(!monster.checkAlive()){ // if monster is dead
                                            removed_dead_monster(monster); // if monster is dead, remove
                                            hero.gainMoneyExp(monster.getDeadGold(), monster.getDeadExp(1)); // get monster's gold and exp
                                        }
                                    }
                                }
                                else{
                                    TerminalPrinter.not_spell();
                                }
                            }

                        } else if (turn.equals(Components.equip)) {
                            String index_string = TerminalAsk.ask_equip(hero, hero.getEquipment_inventory());
                            if(index_string.equals("L")){ continue;} // leave
                            else if(index_string.equals("Q")){ player.Quit(); break;} // quit

                            hero.equip(Integer.parseInt(index_string)); // equip
                            validMove = false; //equip doesnt cost a turn

                        } else if (turn.equals(Components.teleport)) {
                            if (teleport(game_map, (Hero) player.getHeroParty().getMembers(i))) {
                                validMove = true;
                            }

                        } else if (turn.equals(Components.recall)) {
                            if ((recall((Hero) player.getHeroParty().getCharacter(i), i))) {
                                TerminalPrinter.print_recall();
                                validMove = true;
                            }

                        }else if (turn.equals(Components.enterMarket)) {
                            if(game_map.can_enter_market(hero)){
                                validMove = true;
                                market.enterMarket(player, hero);
                            }else{
                                TerminalPrinter.cant_enter_market();
                            }

                        }


                    }


                }

                game_map.print_random_map(player);

            }

            //Now it's the monster turn
            for (int i = 0; i < player.getMonster_party().size(); i++) {
                Monster monster = (Monster) player.getMonster_party().getCharacter(i);
                System.out.println("M" + (i + 1) + " " + monster.getName() + "'s turn !!!");
                int hero_index = game_map.can_attack_hero(monster, player);
                if (hero_index != -1) { // that means there is a hero that could be attacked
                    //ATTACK THE HERO
                    Hero hero = (Hero) player.getHeroParty().getCharacter(hero_index);
                    monster.attack(hero);
                    removed_dead_hero(hero); // if hero dead remove it
                } else {
                    game_map.move_monster_forward((Monster) player.getMonster_party().getCharacter(i)); // move forward
                    System.out.println(monster.getName() + " move forward! ");
                }

            }
            System.out.print("\n \n");

            int max_level = player.getHeroParty().getMaxLevel(); // get the max level of heroes
            if(monster_level < max_level){    // create new monster
                monster_level = max_level;
                addNewMonster(monster_level);
            } else if(player.getMonster_party().empty()){ //if there is no monster
                addNewMonster(monster_level);
            }
            game_map.print_random_map(player);

            endARound();//end this round
        }

    }

    private boolean recall(Hero hero , int i) {
        return  game_map.heroRecall(hero, i);
    }

    private boolean teleport(Map game_map , Hero hero) { // this is for teleporting
        if(TerminalAsk.teleport_hero(game_map, hero , player)){
            return true;
        }
        return false;
    }

    private boolean use_spell(Character members) {
        return false;
    }

    private void removed_dead_hero(Hero hero){ //check hero died or not, remove if he is dead
        if(!hero.checkAlive()){   // dead
            Cell cell = game_map.getWorldCell(hero.getChar_pos_x(), hero.getChar_pos_y()); // get current cell
            cell.setHero_present(false);
            player.getHeroParty().remove(hero);
        }
    }

    private void removed_dead_monster(Monster monster){ //check hero died or not, remove if he is dead
        if(!monster.checkAlive()){   // dead
            Cell cell = game_map.getWorldCell(monster.getChar_pos_x(), monster.getChar_pos_y()); // get current cell
            cell.setMonster_present(false);
            player.getMonster_party().remove(monster);
        }
    }



    @Override
    public void endARound() {

    }

    @Override
    public void startGame() throws FileNotFoundException {

        TerminalPrinter.welcome_game();
        game_map = new Random_map();
        game_map.initial_map();
        //game_map.print_random_map();
        createHeroParty(max_heroes);
        System.out.println(player.getHeroParty());
        addNewMonster(1);
        player.setHero_Initial_pos(game_map);
        game_map.print_random_map(player);

    }

    private void addNewMonster(int level){
        Party new_monsters = createMonsterParty(level, num_monsters);
        player.setNewMonster_pos(game_map, new_monsters); //initialize monsters' position
        player.addNewMonster(new_monsters); // add three level 1

    }
    private Party createMonsterParty(int max_level, int num_monsters){
        return  CharacterFactory.getMonsterParty(max_level, num_monsters);
    }


    private void createHeroParty(int NumInParty) { //create heroes
        CharacterFactory.PrintHeroList();
        List<Hero> Hero_list = CharacterFactory.getHeroList();
        for (int i = 0; i < NumInParty;){
            Hero h = TerminalAsk.ask_which_hero(Hero_list); // ask the user
            if(player.checkPartMember(h)){
                TerminalPrinter.already_in_party(h);
                //select again
            }
            else{ // join the party
                TerminalPrinter.join_party(h);
                player.addHeroToParty(h);
                i++;
            }
        }
    }

    @Override
    public void endGame() {

    }

}
