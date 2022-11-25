import java.util.Arrays;



public class Bush_Cell implements Cell{
    Boolean hero_present; //String none => hero 1 or 2 or 3
    // party <0,1> , <2,4>, <2,1>
    Boolean monster_present;

    int pos_x;
    int pos_y;

    public Bush_Cell(){
        hero_present = false;
        monster_present = false;

    }

    public void setPos_x(int pos_x) {
        this.pos_x = pos_x;
    }

    public void setPos_y(int pos_y) {
        this.pos_y = pos_y;
    }

    public int getPos_x() {
        return pos_x;
    }

    public int getPos_y() {
        return pos_y;
    }

    @Override
    public Player GoIn(Player player) {
        return null;
    }

    @Override
    public void setPlayerIsHere( ) {

    }

    @Override
    public void GoOut( ) {

    }

    @Override
    public boolean havePlayer( ) {
        return false;
    }

    @Override
    public String toString() {
        return "B";
    }

    @Override
    public void setHero_present(Boolean hero_present) {
        this.hero_present = hero_present;

    }

    @Override
    public void setMonster_present(Boolean monster_present) {
        this.monster_present = monster_present;

    }

    @Override
    public Boolean getHero_present() {
        return hero_present;
    }

    @Override
    public Boolean getMonster_present() {
        return monster_present;
    }

}
