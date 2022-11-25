import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LegendsOfValorGame implements Round_based_game{


    private Map game_map;

    private Player player = new Player();
    private int max_heroes;

    private int num_monsters = 3;


    LegendsOfValorGame() throws FileNotFoundException {
        max_heroes = Components.max_heroes;
        new CharacterFactory();
        new ItemFactory();

    }
    @Override
    public void startARound() {
        Boolean validMove = false;
        for(int i = 0; i < player.getHeroParty().size() ; i++){
            if(!player.checkQuit()){
                validMove = false;
                while(!validMove){
                    TerminalPrinter.each_hero_round_begin((Hero) player.getHeroParty().getMembers(i), game_map, player);
                    String turn = TerminalAsk.ask_which_turn(player.getHeroParty());
                    if(checkQ(turn)) break; // if the input is Q, quit the game
                    if(Arrays.asList(Components.direction).contains(turn) ) { // move position on map, true if we successfully move
                        if(game_map.move(turn , (Hero)player.getHeroParty().getMembers(i))){
                            validMove = true;
                        }

                    }
                    else if(turn.equals(Components.attack)){
                        if(perform_attack(player.getHeroParty().getMembers(i))){
                            validMove = true;

                        }
                    }
                    else if(turn.equals(Components.use_potion)){
                        if(use_potion(player.getHeroParty().getMembers(i))){
                            validMove = true;

                        }

                    }
                    else if(turn.equals(Components.use_spell)){
                        if(use_spell(player.getHeroParty().getMembers(i))){
                            validMove = true;

                        }

                    }
                    else if(turn.equals(Components.equip)){
                        if(equip(player.getHeroParty().getMembers(i))){
                            validMove = true;

                        }

                    }
                    else if(turn.equals(Components.teleport)){
                        if(teleport(game_map , (Hero) player.getHeroParty().getMembers(i))){
                            validMove = true;

                        }
                        ;
                    }
                    else if(turn.equals(Components.recall)){
                        if((recall((Hero) player.getHeroParty().getCharacter(i), i) )){
                            validMove = true;
                        }

                    }


                }

                //endARound();
            }

            game_map.print_random_map(player);
        }

        //Now it's the monster turn
        for(int i = 0; i < player.getMonster_party().size() ; i++){
            if(can_attack_hero((Monster)player.getMonster_party().getCharacter(i))){
                //ATTACK THE HERO
            }
            else{
                game_map.move_monster_forward((Monster)player.getMonster_party().getCharacter(i));
            }
            game_map.print_random_map(player);
        }


    }

    private boolean can_attack_hero(Monster monster) {
        Boolean attack = true;
        return attack;
    }

    private boolean recall(Hero hero , int i) {
        game_map.heroRecall( hero, i);

        return true;
    }

    private boolean teleport(Map game_map , Hero hero) {
        if(TerminalAsk.teleport_hero(game_map, hero , player)){
            return true;
        }
        return false;
    }

    private boolean equip(Character members) {
        return false;
    }

    private boolean use_spell(Character members) {
        return false;
    }

    private boolean use_potion(Character members) {
        return false;
    }

    private Boolean perform_attack(Character members) {
        return false;
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
        TerminalPrinter.Print_hero_team( player.getHero_list());
        createMonsterParty(num_monsters);
        player.setInitial_pos(game_map);
        game_map.print_random_map(player);

    }

    private void createMonsterParty(int num_monsters) {

        player.setMonster_team( CharacterFactory.getMonsterParty(num_monsters));
    }

    private void createHeroParty(int NumInParty) {
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

    public boolean checkQ(String input){ // 'Q' for quit.
        // return true , if the input is  Q
        return input.equals("Q");
    }
}
