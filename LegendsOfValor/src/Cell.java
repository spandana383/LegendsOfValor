public interface Cell {
    //Every implementation has two boolean values: Hero Present and Monster present. The hero is always in postion 1 and the monster is always in position 2
    Player GoIn(Player player  );
    void setPlayerIsHere( );
    void GoOut( );
    boolean havePlayer();

    public void setPos_x(int pos_x);

    public void setPos_y(int pos_y);

    public int getPos_x();

    public int getPos_y();
    String toString();

    public void setHero_present(Boolean hero_present);

    public void setMonster_present(Boolean monster_present);

    public Boolean getHero_present();

    public Boolean getMonster_present();

}
