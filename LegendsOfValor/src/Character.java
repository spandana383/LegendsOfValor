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
}
