public interface Map {
    boolean move_up(Hero hero);
    boolean move_down(Hero hero);
    boolean move_left(Hero hero);
    boolean move_right(Hero hero);
    Cell getCell();
    void initial_map();
    boolean move(String direction, Hero members);

    public void print_random_map(Player player);


    void setInitial_HeroPos();
    public Cell[][] getWorld();

    void heroRecall(Hero hero , int i);
    Cell getWorldCell(int x, int y);

    void move_monster_forward(Monster m);
}
