import java.util.ArrayList;
import java.util.List;

public class Player {
    private boolean quit;
    private Party hero_party = new Party();
    private Party monster_party = new Party();
    private int num_monsters = 3;


    Player(){
        quit = false;
    }

    public Party getMonster_party() {
        return monster_party;
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

    public void addNewMonster(Party monster_party){
        this.monster_party.addAll(monster_party);
    }
    public boolean checkPartMember(Character c){
        return hero_party.checkMember(c);
    }
    public Party getHeroParty(){
        return hero_party;
    }
    public Party getMonsterParty(){
        return monster_party;
    }

    public Hero getHero(int index){ // return Hero by index
        return (Hero) hero_party.getCharacter(index);
    }

    public void setHero_Initial_pos(Map gameMap) {  // set the original position of heroes
        for(int i = 0; i < hero_party.size(); i++){
            hero_party.getMembers(i).setChar_pos_x(Components.intial_hero_x[i]);
            hero_party.getMembers(i).setChar_pos_y(Components.intial_hero_y[i]);
            gameMap.getWorld()[Components.intial_hero_y[i]][Components.intial_hero_x[i]].setHero_present(Components.hero_present); // initialize position
        }
    }

    public void setNewMonster_pos(Map gameMap, Party m_party){ // this method is to set new monster's position
        for(int i = 0; i < m_party.size(); i++){
            m_party.getMembers(i).setChar_pos_x(Components.intial_monster_x[i]);
            m_party.getMembers(i).setChar_pos_y(Components.intial_monster_y[i]);
            gameMap.getWorld()[Components.intial_monster_y[i]][Components.intial_monster_x[i]].setMonster_present(Components.monster_present); // initialize position
        }
    }
}
