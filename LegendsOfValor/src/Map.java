public interface Map {
    boolean move_up(Hero hero);
    boolean move_down(Hero hero);
    boolean move_left(Hero hero);
    boolean move_right(Hero hero);
    Cell getCell();
    void initial_map();
    boolean move(String direction, Hero members);

    void print_random_map(Player player);

    Cell[][] getWorld();

    boolean heroRecall(Hero hero , int i);
    Cell getWorldCell(int x, int y);

    void move_monster_forward(Monster m);
    int can_attack_hero(Monster monster, Player player);
    int can_attack_monster(Hero hero, Player player);
    Boolean can_enter_market(Hero hero);
}
