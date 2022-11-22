public interface Map {
    boolean move_up();
    boolean move_down();
    boolean move_left();
    boolean move_right();
    Cell getCell();
    void initial_map();
    boolean move(String direction);
}
