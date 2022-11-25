import java.util.ArrayList;
import java.util.List;

public class Player {
    private boolean quit;
    Party hero_party = new Party();
    Party monster_party = new Party();
    private List<Hero> hero_team = new ArrayList<Hero>();
    private List<Monster> monster_team = new ArrayList<Monster>();



    Player(){
        quit = false;
    }

    public Party getMonster_party() {
        return monster_party;
    }

    public void setMonster_team(Party monster_party) {
        /*
        for(int i =0; i < monster_team.size(); i++){
            monster_party.addMember(monster_team.get(i));
        }
        */
        this.monster_party = monster_party;


    }

    boolean checkQuit(){
        return quit;
    }
    void Quit(){
        quit = true;
    }

    public void addHeroToParty(Hero hero){
        hero_party.addMember(hero);
        //hero_team.add(hero);
    }
    public boolean checkPartMember(Character c){
        return hero_party.checkMember(c);
    }
    public Party getHeroParty(){
        return hero_party;
    }

    public List<Hero> getHero_list() {
        for(int i = 0; i < hero_party.size(); i++){
            hero_team.add((Hero)hero_party.getCharacter(i));
        }
        return hero_team;

    }

    public Hero getCharacter(int index){ // return Hero by index
        return (Hero) hero_party.getCharacter(index);
    }

    public void setInitial_pos(Map gameMap) {
        for(int i = 0; i < hero_party.size(); i++){
            hero_party.getMembers(i).setChar_pos_x(Components.intial_hero_x[i]);
            hero_party.getMembers(i).setChar_pos_y(Components.intial_hero_y[i]);
            gameMap.getWorld()[Components.intial_hero_x[i]][Components.intial_hero_y[i]].setHero_present(Components.hero_present);
        }

        for(int i = 0; i < monster_party.size(); i++){
            monster_party.getMembers(i).setChar_pos_x(Components.intial_monster_x[i]);
            monster_party.getMembers(i).setChar_pos_y(Components.intial_monster_y[i]);
            gameMap.getWorld()[Components.intial_monster_x[i]][Components.intial_monster_y[i]].setMonster_present(Components.monster_present);
        }


    }
}
