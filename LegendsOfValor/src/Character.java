public interface Character {
    void attack(Character c);
    String getName();
    int getLevel();
    boolean dodge();
    boolean checkAlive();
    void dead();
    void takeDamage(int damage, Character c);
    int getHP();

    void gainExp(int exp);
    void gainGold(int extra_money);

    String getTitle();

    public void setChar_pos_x(int char_pos_x);

    public void setChar_pos_y(int char_pos_y);

    public int getChar_pos_x();

    public int getChar_pos_y();
}
