package test;

public class Resources {
    int milk;
    int water;
    int coffee;
    static Resources tmpStatus;
    private final int B_COFFEE = 20;
    private final int S_COFFEE = 15;
    private final int B_WATER = 150;
    private final int S_WATER = 70;
    private final int B_MILK = 50;
    private final int S_MILK = 30;
    private final int LONG_WATER = 80;
    private final int SHORT_WATER = 50;


    public Resources(int milk, int water, int coffee) {
        this.milk = milk;
        this.water = water;
        this.coffee = coffee;
    }

    public Boolean checkResources(Bevarges bev,Resources status) {
        tmpStatus = status;
        switch (bev.getType()) {
            case "Capuccino": // Capuccino and Late with same amounts
            case "Late":
                switch (bev.getSize()) {
                    case "Big":
                        tmpStatus.coffee -= B_COFFEE;
                        tmpStatus.milk -= B_MILK;
                        tmpStatus.water -= B_WATER;
                        break;
                    case "Small":
                        tmpStatus.coffee -= S_COFFEE;
                        tmpStatus.milk -= S_MILK;
                        tmpStatus.water -= S_WATER;
                        break;
                }
                break;
            case "Esspreso":
                switch (bev.getSize()) {
                    case "Long":
                        tmpStatus.coffee -= B_COFFEE;
                        tmpStatus.water -= LONG_WATER;
                        break;
                    case "Short":
                        tmpStatus.coffee -= S_COFFEE;
                        tmpStatus.water -= SHORT_WATER;
                        break;
                }
                break;
            default:
                System.out.println("There is no bevarage avaible");
                return false;
        }
        if(tmpStatus.water >= 0 && tmpStatus.milk >= 0 && tmpStatus.coffee >= 0 ){
            status = tmpStatus;
            return true;
        }
        System.out.println("We have some inventory issues...wait for maintaince to come!");
        reportResources(status);
        return false;

    }
    public void reportResources(Resources status) {
        System.out.println("Resources status:\n"
        + "Milk = " + status.milk + "\n"
        + "Water = " + status.water + "\n"
        + "Coffee = " + status.coffee + "\n");
    }
}