package test;


import java.util.ArrayList;
import java.util.Scanner;

//**********************************
//This project there is no reference for user Typo's
//**********************************

public class CoffeeMachine {
    private final int full = 500; // amount in ml\gr
    ArrayList<Bevarges> orders;
    static Resources status;
    int orderSize;
    double orderProfit;
    String bevargeType;

    public CoffeeMachine() {
        orders = new ArrayList<>();
        if(status == null){
            status = new Resources(full,full,full);
        }
    }

    public void menu(){
        System.out.println("Hi and welcome to Tal's coffee machine !\n" +
                "how many orders would you like?");
        Scanner input = new Scanner(System.in);
        orderSize = input.nextInt();
        if(orderSize == 0){
            System.out.println("Thanks!, see you later (:");
        }
        for(int i =0; i < orderSize; i++){
            System.out.println("\u2615" + "what coffee would like for order number " + (i+1) + ":?"+"\u2615" + "\n" +
             "Type the exact name:\n" + "Capuccino\n" + "Espresso\n" + "Late\n");
            bevargeType = input.next();
            switch(bevargeType) {
                case "Capuccino":
                    menuChooseNotEspresso(i,bevargeType);
                    break;
                case "Espresso":
                    menuChooseEspresso(i);
                    break;
                case "Late":
                    menuChooseNotEspresso(i,bevargeType);
                    break;
                default:

            }
        }
    }

    public void menuChooseNotEspresso(int index,String bevargeType){
        Bevarges tmpBev = null;
        String bevargeSize;
        String milkType;

        Scanner input = new Scanner(System.in);
        System.out.println("would you like Big(200 ml) or Small(100 ml)?");

        bevargeSize = input.next();
        System.out.println("\uD83E\uDD5B" + " which milk would you like? "+"\uD83E\uDD5B" + "\n" +
                "Cow milk\n" + "Soy milk(extra 0.5$)\n" + "Almond milk(extra 0.5$)\n");
        milkType = input.next();
        if(bevargeType.equals("Capuccino")){
            tmpBev = new Capuccino(milkType,bevargeSize);
        }
        else{
            tmpBev = new Late(milkType,bevargeSize);
        }
        // only if the bevarge is fit with quants
        if(status.checkResources(tmpBev,status)){
            orders.add(tmpBev);
            orderProfit += calculateProfit(bevargeType,bevargeSize,milkType);
        }
    }

    public void menuChooseEspresso(int index){
        String bevargeSize;

        System.out.println("would you like Long(80 ml) or Short(50 ml)?");
        Scanner input = new Scanner(System.in);
        bevargeSize = input.next();
        Bevarges tmpBev = new Esspreso(bevargeSize);
        // only if the bevarge is fit with quants
        if(status.checkResources(tmpBev,status)){
            orders.add(tmpBev);
            orderProfit += calculateProfit(bevargeType,bevargeSize,"Non");
        }
    }

    public double calculateProfit(String type,String size, String milkType){
        double tmpProfit = 0;
        switch(type) {
            case "Capuccino":
            case "Late":
                if(size.equals("Big")){
                    tmpProfit += 3.5;
                }
                else {
                    tmpProfit += 2.75;
                }
                if(!(milkType.equals("Cow"))){
                    tmpProfit += 0.5;
                }
                break;
            case "Espresso":
                if(size.equals("Long")){
                    tmpProfit += 2;
                }
                else{
                    tmpProfit += 1;
                }
                break;
            default:

        }
        return  tmpProfit;
    }

    public void payment(){
        double tmpPament = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("Totall price is: " + orderProfit +" NIS\n"
        + "Please choose 0 for credit or 1 for cash:");
        int clientChose = input.nextInt();
        if(clientChose > 0){
            System.out.println("Please insert your payment:");
            tmpPament += input.nextDouble();
            while(tmpPament < orderProfit){
                System.out.println("So close! but you need just more " + (orderProfit-tmpPament) + "NIS");
                tmpPament += input.nextDouble();
            }
            change(tmpPament);
        }
        else{
            System.out.println("Please insert your card:");
        }
        for(int i =0; i < orders.size(); i++){
            orders.get(i).prepTime();
        }
        System.out.println("\uD83C\uDF35" + " Thank you for choosing Tal's offee machine, See Ya (: " + "\uD83C\uDF35");
        // set for next order
        orderProfit = 0;
    }

    public void change(double clientPayment){
        double tmpClientPayment = clientPayment;
        double nisPenis = 0;
        double nisChange = 0;
        int nisCoins = 0;
        nisChange = clientPayment - orderProfit;
        nisCoins = (int)nisChange;
        nisPenis = nisChange - nisCoins;
        if(nisCoins == 0 && nisPenis == 0){
            System.out.println("No change needed!");
        }
        System.out.println(	"\uD83D\uDCB0" + " Here is the change: " + 	"\uD83D\uDCB0" + "\n");
        changeInNisCoins(nisCoins);
        changeInPenisCoins(nisPenis);
    }

    public void changeInNisCoins(int nisCoins){
        int coinsOf10 = 0;
        int coinsOf5 = 0;
        int coinsOf2 = 0;
        int coinsOf1 = 0;
        while(nisCoins > 0){
            if(nisCoins >= 10){
                coinsOf10++;
                nisCoins -= 10;
            }
            else if(nisCoins >= 5){
                coinsOf5++;
                nisCoins-=5;
            }
            else if(nisCoins >= 2){
                coinsOf2++;
                nisCoins-=2;
            }
            else{
                coinsOf1++;
                nisCoins-=1;
            }
        }
        System.out.println("Coins of 10: " + coinsOf10 + "\n"+
                "Coins of 5: " + coinsOf5 +"\n"+
                "Coins of 2: " + coinsOf2 + "\n"+
                "Coins of 1: " + coinsOf1 + "\n");
    }
    public void changeInPenisCoins(double penisCoins){
        int coinsOfHalfPenny = 0;
        int coinsOfPenny = 0;
        while(penisCoins > 0){
            if(penisCoins >= 0.5){
                coinsOfHalfPenny++;
                penisCoins-= 0.5;
            }
            else{
                coinsOfPenny++;
                penisCoins -= 0.1;
            }
        }
        System.out.println("Coins of HalfPenny: " + coinsOfHalfPenny + "\n"+
                "Coins of Penny: " + coinsOfPenny);
    }


    public static void main(String[] args) {
        CoffeeMachine machine = new CoffeeMachine();
        machine.menu();
        machine.payment();
    }
}