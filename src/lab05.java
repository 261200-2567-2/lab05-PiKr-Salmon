import java.util.Random;
import java.util.Scanner;

public class lab05 {
    private static character ch;
    private static character en;
    static Scanner sc = new Scanner(System.in);

    protected static Random rand = new Random();

    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        register();
        System.out.println("--- registration info ---");
        System.out.println("BirthDay: " + character.getBD());
        System.out.println();
        ch.allStatus();
        event();


        // Press Shift+F10 or click the green arrow button in the gutter to run the code.
        // Press Shift+F9 to start debugging your code. We have set one breakpoint
        // for you, but you can always add more by pressing Ctrl+F8.
    }

    protected static void register() {
        System.out.println("Hello welcome");
        System.out.println("please register your character:");
        System.out.println();
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        ch = new character(name);
    }

    protected static void event() {
        ch.changeJob();
        ch.levelUp();
        ch.levelUp();
        ch.levelUp();
        ch.levelUp();
        ch.levelUp();
        ch.levelUp();
        ch.allStatus();
        continues();

        ch.changeJob(1);
        System.out.println();
        ch.allStatus();
        continues();

        System.out.println("which equipment you want to equip?: ");
        System.out.println("command: ");
        System.out.println("type - equipment: 1- Sword 2- Arrow 3- Shield");
        System.out.println("type - gem: 1- Ruby 2- Emerald 3- Sapphire");
        ch.equip("equipment",2);
        ch.equip("equipment",1);
        ch.equip("equipment", 3);
        ch.equip("gem",1);
        System.out.println();
        ch.allStatus();
        continues();

        ch.enchant(ch.weapon);
        ch.enchant(ch.weapon);
        ch.enchant(ch.defensive);
        System.out.println();
        ch.allStatus();
        continues();

        Ui();
        en = new character("enemy");
        en.changeJob(rand.nextInt(3)+1);
        en.levelUp();
        en.levelUp();
        en.allStatus();
        continues();

        Ui();
        ch.attack(en);
        continues();

        Ui();
        en.attack(ch);
        continues();

        Ui();
        ch.attack(en);
        continues();

        Ui();

        ch.unEquip(ch.defensive);
        System.out.println();
        ch.allStatus();
        continues();

        ch.unEquip(ch.weapon);
        System.out.println();
        ch.allStatus();
        continues();
    }

    protected static void Ui(){
        if(en != null){
            if(en.isAlive()){
            System.out.println();
            System.out.println("                       " + en.name + "   HP : " + en.Hp + " *");
            System.out.println("             " + "Atk : " +en.totalPower + " | Def : " + en.totalDefend + " *");
            }
        }else{
            System.out.println("* no enemy");
        }
        System.out.println();
        System.out.println("* ");
        System.out.println("* " + ch.name + " : " + ch.Hp);
        System.out.println("* " + "Atk : "+ ch.totalPower + " | Def : " + ch.totalDefend);
    }

    protected static void continues() {
        System.out.println();
        System.out.println("Proceed? (yes/no): ");
        String choice = sc.nextLine();
        if(choice.equalsIgnoreCase("no")){
            System.exit(0);
        }else if(!choice.equalsIgnoreCase("yes")){
            System.out.println("Invalid command");
            event();
        }
    }

}
