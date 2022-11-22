import java.util.Arrays;

public class Koulou_Cell implements Cell{

    Boolean hero_present;
    Boolean monster_present;

    public Koulou_Cell(){
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
    public boolean havePlayer( ) {
        return false;
    }

    public String toString() {
        return "K";
    }


}
