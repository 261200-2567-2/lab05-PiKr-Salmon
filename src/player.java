public interface player {
    void allStatus();
    void job();
    void equipment1(accessories weapon);
    void equipment2(accessories defensiveWeapon);
    void gem1(accessories slot1);

    void changeJob(int jobSelectionInt);
    void equip(String type,int accSelectionInt);
    void unEquip(accessories type);
    void enchant(accessories type);
    void attack(character enemy);
    boolean isAlive();
}
