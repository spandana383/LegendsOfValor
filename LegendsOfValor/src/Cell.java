public interface Cell {
    //Every implementation has two boolean values: Hero Present and Monster present. The hero is always in postion 1 and the monster is always in position 2
    Player GoIn(Player player  );
    void setPlayerIsHere( );
    void GoOut( );
    boolean havePlayer();

    String toString();

}
