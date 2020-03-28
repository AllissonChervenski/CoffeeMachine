import java.util.Scanner;
public class CoffeeMachine {
    static int water = 400;
    static int milk = 540;
    static int beans = 120;
    static int cups = 9;
    static int cash = 550;

    public String stat(){
        String pw = String.format("%d of water%n", water);
        String pm = String.format("%d of milk%n", milk);
        String pb = String.format("%d of coffee beans%n", beans);
        String pc = String.format("%d of disposable cups%n", cups);
        String pmo = String.format("$%d of money%n", cash);
        return String.format("The coffe machine has:%n%s%s%s%s%s", pw, pm, pb, pc, pmo);
    }

    public String take(){
        int temp = cash;
        cash = 0;
        return String.format("I gave you $%d%n", temp);

    }

    public void fill(int water, int milk, int beans, int cups){
        CoffeeMachine.water += water;
        CoffeeMachine.milk += milk;
        CoffeeMachine.beans += beans;
        CoffeeMachine.cups += cups;

    }

    public enum CoffeType{
        ESPRESSO(250, 0, 16, 4),
        LATTE(350, 75, 20, 7),
        CAPPUCCINO(200, 100, 12, 6);

        private final int agua, leite, grao, preco;

        CoffeType(int agua, int leite, int grao, int preco){
            this.agua = agua;
            this.leite = leite;
            this.grao = grao;
            this.preco = preco;
        }

        public String transicao(){
            String tr = "";
            if(water >= agua && beans >= grao && milk >= leite && cups >= 1){
                water -= agua;
                beans -= grao;
                milk -= leite;
                cups -= 1;
                cash += this.preco;
                tr =  "I have enough resources, making you a coffee!";
            }
         else if(water < agua){
                tr = "Sorry, not enough water!";
            }
            else if(beans < grao){
                tr =  "Sorry, not enough beans!";
            }
            else if(cups < 1){
                tr =  "Sorry, not enough cups!";
            }
            else if(milk < leite){
                tr =  "Sorry, not enough milk!";
            }
            return tr;
        }
    }


    public String buy(String opt){
        String tr = "Write action (buy, fill, take, remaining, exit):";
        CoffeType espresso = CoffeType.ESPRESSO;
        CoffeType latte = CoffeType.LATTE;
        CoffeType cappuccino = CoffeType.CAPPUCCINO;
        switch(opt){
            case "1":
                tr = espresso.transicao();
                break;
            case "2":
                tr =  latte.transicao();
                break;
            case "3":
                tr =  cappuccino.transicao();
                break;
        }
        return tr;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        CoffeeMachine cof = new CoffeeMachine();
        String run = "run";
       do {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
            switch (scan.next()) {
                case "take":
                    System.out.println(cof.take());
                    break;
                case "buy":
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                    String op2 = scan.next();
                    if (!op2.equals("back")) {
                        System.out.println(cof.buy(op2));
                    }
                    break;
                case "fill":
                    System.out.println("Write how many ml of water do you want to add");
                    int f1 = scan.nextInt();
                    System.out.println("Write how many ml of milk do you want to add");
                    int f2 = scan.nextInt();
                    System.out.println("Write how many grams of coffee beans do you want to add");
                    int f3 = scan.nextInt();
                    System.out.println("Write how many disposable cups of coffee do you want to add");
                    int f4 = scan.nextInt();
                    cof.fill(f1, f2, f3, f4);
                    break;
                case "remaining":
                    System.out.println(cof.stat());
                    break;
                case "exit":
                    run = "exit";
                    break;
            }
        }
       while (!run.equals("exit"));
    }
    }


