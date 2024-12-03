public class accessories implements equipment,gem{
    protected String type;
    protected int hpUp;
    protected int level = 1;
    protected double power;
    protected double defend;
    protected int deRunSpeed;

    protected accessories( String type, int hpUp, double power, double defend, int deRunSpeed){
        this.type = type;
        this.hpUp = hpUp;
        this.power = power;
        this.defend = defend;
        this.deRunSpeed = deRunSpeed;
    }

    private void levelUp(){
        level++;
        this.hpUp = (int) (hpUp * (1+(0.1 * this.level)));
        this.power = power * (1+(0.1 * this.level));
        this.defend = defend * (1+(0.05 * this.level));
    }


    @Override
    public void enchant(){levelUp();}

    @Override
    public int getLevel() {return level;}

    @Override
    public double eachTotalPower() {return power;}

    @Override
    public double eachTotalDefend() {return defend;}

    @Override
    public String getType() {return type;}

    @Override
    public void buff() {
        switch (type){
            case "ruby" -> {hpUp = (int) ((double) hpUp * 1.12); System.out.println("hp up 12%");}
            case "emerald" -> {power *= 1.25; System.out.println("power up 25%"); }
            case "sapphire" -> {defend *= 1.18; System.out.println("defend up 18%"); }
        }
    }

    @Override
    public void deBuff() {
        switch (type){
            case "ruby" -> {power *= 0.92; System.out.println("power down 8%"); }
            case "emerald" -> {defend *= 0.78; System.out.println("defend down 22%"); }
            case "sapphire" -> {hpUp = (int) ((double) hpUp * 0.87); System.out.println("hp down 13%"); }
        }
    }


}
