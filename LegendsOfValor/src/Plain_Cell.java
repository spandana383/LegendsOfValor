import java.util.Arrays;

public class Plain_Cell implements Cell{

    Boolean hero_present;
    Boolean monster_present;
    private int pos_x;
    private int pos_y;

    public Plain_Cell(){
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
    public void GoOut() {

    }

    @Override
    public boolean havePlayer() {
        return false;
    }

    @Override
    public String toString() {
        return "P";
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
