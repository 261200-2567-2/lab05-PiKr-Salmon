import java.time.LocalDate;
import java.util.Scanner;

public class character implements job{
    protected String name;
    protected int level = 1;
    protected String job;
    protected int Hp;
    protected int mana;
    protected double basePower;
    protected double totalPower;    //fix it
    protected double baseDefend;
    protected double totalDefend;   //fix it
    protected int runSpeed;
    protected accessories weapon;
    protected accessories defensive;
    protected accessories gem;

    protected String[] jobContainer = {"tank","swordsman","archer"};
    protected String[] gemTypeContainer = {"ruby","emerald","sapphire"};
    protected String[] equipmentTypeContainer = {"sword","arrow","Shield"};

    protected Scanner sc = new Scanner(System.in);

    protected character(String name) {
        this.name = name;
        this.job = "civilian";
        this.Hp = 100 + (10 * level);
        this.mana = 20 + (5 * level);
        this.basePower = this.totalPower = 13 + (2 * level);
        this.baseDefend = this.totalDefend = 5 + level;
        this.runSpeed = 10 + ((1 + level)/2);
    }

    protected void updStat(int level, int hp, int mana, double basePower,double baseDefend, int runSpeed) {
        this.level = level;
        this.Hp = hp + (10 * level);
        this.mana = mana + (5 * level);
        this.basePower = this.totalPower = basePower + (2 * level);
        this.baseDefend = this.totalDefend = baseDefend + level;
        this.runSpeed = runSpeed + ((1 + level)/2);
    }

    protected void levelUp(){
        level++;
        this.Hp += (10 * level);
        this.mana += (5 * level);
        this.basePower = this.totalPower += (2 * level);
        this.baseDefend = this.totalDefend += level;
        this.runSpeed += ((1 + level)/2);
    }

    protected void UpdJob(int jobSelectionInt){
        this.job = jobContainer[jobSelectionInt - 1];
        switch (job) {
            case "tank" -> updStat(level, 200, 20, 6, 15, 4);
            case "swordsman" -> updStat(level, 100, 5, 15, 8, 10);
            case "archer" -> updStat(level, 50, 30, 18, 6, 12);
    }
}

    @Override
    public double eachTotalPower(){return basePower;}
    @Override
    public double eachTotalDefend(){return basePower;}

    @Override
    public String Job(){return job;}

    @Override
    public void changeJob() {
        System.out.println();
        System.out.println("which job you want to be: ");
        System.out.println("command: ");
        System.out.println("1- Tank 2- Swordsman 3- Archer");
        String jobSelectionInt = sc.nextLine();
        if(jobSelectionInt.equals("1") || jobSelectionInt.equals("2") || jobSelectionInt.equals("3")){
            changeJob(Integer.parseInt(jobSelectionInt));
        }else{
            System.out.println("Invalid job selection - you choose to be civilian ");
        }
    }

    @Override
    public void changeJob(int jobSelectionInt){
        if(job.equals(jobContainer[jobSelectionInt - 1])){
            System.out.println("You already have a job");
        }else {
            UpdJob(jobSelectionInt);
        }
    }

    @Override
    public void equip(String type,int accSelectionInt) {
        if(type.equalsIgnoreCase("equipment")){
            String equipType = equipmentTypeContainer[accSelectionInt - 1];
            if(accSelectionInt == 1){
                switch(job) {
                    case "tank" , "swordsman" -> {weapon = new accessories(equipType,0,10,0,2);
                        this.totalPower += weapon.power;
                        this.runSpeed -= weapon.deRunSpeed;
                    }
                    case "archer" -> System.out.println("you can't equip this weapon");
                }
                this.totalPower += weapon.power;
                this.runSpeed -= weapon.deRunSpeed;
            }else if(accSelectionInt == 2){
                switch(job){
                    case "archer" -> { weapon = new accessories(equipType,0,12,0,1);
                        this.totalPower += weapon.power;
                        this.runSpeed -= weapon.deRunSpeed;
                    }
                    case "tank", "swordsman" -> System.out.println("you can't equip this weapon");
                }
            }else {
                switch(job) {
                    case "tank" -> { defensive = new accessories(equipType,12,0,12,4);
                        this.totalPower += defensive.power;
                        this.runSpeed -= defensive.deRunSpeed;
                    }
                    case "swordsman", "archer" -> System.out.println("you can't equip this weapon");
                }
            }
        }else if(type.equalsIgnoreCase("gem")){
            String gemType = gemTypeContainer[accSelectionInt - 1];
            if(accSelectionInt == 1){
                gem = new accessories(gemType,10,5,8,1);
            }else if(accSelectionInt == 2){
                gem = new accessories(gemType,2,20,5,1);
            }else {
                gem = new accessories(gemType,10,5,8,1);
            }
            this.Hp += gem.hpUp;
            this.totalPower += gem.power;
            this.totalDefend += gem.defend;
            this.runSpeed -= gem.deRunSpeed;
        }
    }

    @Override
    public void unEquip(accessories type) {
        if(type.equals(weapon)){
            weapon = null;
        }else if(type.equals(defensive)){
            defensive = null;
        }else if(type.equals(gem)){
            this.Hp -= gem.hpUp;
            gem = null;
        }
        this.totalPower -= type.power;
        this.totalDefend -= type.defend;
        this.runSpeed += type.deRunSpeed;
    }

    @Override
    public void enchant(accessories type) {
        if(type.equals(weapon)){
            weapon.enchant();
            this.totalPower = basePower + weapon.power;
        }else if(type.equals(defensive)){
            defensive.enchant();
            int totalHp = Hp;
            this.Hp = totalHp + weapon.hpUp ;
            this.totalDefend = baseDefend + defensive.defend;
        }
    }

    @Override
    public void attack(character en) {
        en.Hp -= (int) (0.7 * (this.totalPower - (en.totalDefend * 0.6)));
    }

    @Override
    public void allStatus() {
        System.out.println("-------- Status --------");
        job();
        System.out.println("------------------------");
        System.out.print("name: " + name);
        System.out.print(" - level: " + level);
        System.out.print(" - Hp: " + Hp);
        System.out.println(" - Mana: " + mana);
        if(weapon == null && defensive == null && gem == null ) {
            System.out.print(" - Power: " + (int)basePower);
            System.out.print(" - Defend: " + (int)baseDefend);
        }else if(weapon == null && gem == null){
            System.out.print(" - Power: " + (int)basePower);
            System.out.print(" - Defend: " + (int)totalDefend);
        }else if(defensive == null && gem == null){
            System.out.print(" - Power: " + (int)totalPower);
            System.out.print(" - Defend: " + (int)baseDefend);
        }else {
            System.out.print(" - Power: " + (int)totalPower);
            System.out.print(" - Defend: " + (int)totalDefend);
        }
        System.out.println(" - RunSpeed: " + runSpeed);

        equipment1(weapon);
        equipment2(defensive);
        gem1(gem);
    }

    @Override
    public void job() {
        System.out.println("Classes: " + Job());
        System.out.println("classes power: " + eachTotalPower());
        System.out.println("classes defend: " + eachTotalDefend());
    }

    @Override
    public void equipment1(accessories weapon) {
        System.out.print("(weapon)");
        if(weapon != null){
            System.out.print(" - weapon level: " + weapon.getLevel());
            System.out.println(" - weapon Power: " + (int)weapon.eachTotalPower());
        }else {
            System.out.println(" weapon is unequipped");
        }
    }

    @Override
    public void equipment2(accessories defensiveWeapon) {
        System.out.print("(Shield)");
        if (defensiveWeapon != null){
            System.out.print(" - Shield level: " + defensiveWeapon.getLevel());
            System.out.println(" - Shield defend: " + (int)defensiveWeapon.eachTotalDefend());
        }else {
            System.out.println(" Shield is unequipped");
        }
    }

    @Override
    public void gem1(accessories slot1) {
        System.out.print("(gem)");
        if (slot1 != null){
            System.out.println(" - gem type :" + slot1.getType());
            System.out.println("buff: ");
            slot1.buff();
            System.out.println("debuff: ");
            slot1.deBuff();
        }else {
            System.out.println(" gem is unequipped");
        }
    }

    @Override
    public boolean isAlive() {return Hp > 0;}

    protected static String birthDay;

    protected static String getBD() {
        LocalDate date = LocalDate.now(); // Credit Display Current Date by w3schools.com/java/java_date.asp
        birthDay = date.toString();
        return birthDay;
    }

}