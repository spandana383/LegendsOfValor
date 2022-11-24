import java.util.Arrays;



public class Bush_Cell implements Cell{
    Boolean hero_present; //String none => hero 1 or 2 or 3
    // party <0,1> , <2,4>, <2,1>
    Boolean monster_present;

    public Bush_Cell(){
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

}
