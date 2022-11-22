import java.util.Arrays;

public class Plain_Cell implements Cell{

    Boolean hero_present;
    Boolean monster_present;

    public Plain_Cell(){
        hero_present = false;
        monster_present = false;

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



}
