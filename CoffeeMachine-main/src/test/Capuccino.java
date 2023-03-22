package test;

import java.util.Timer;
import java.util.TimerTask;

public class Capuccino implements Bevarges{
    String milkType;
    String size;

    public Capuccino(String milkType,String size) {
        this.milkType = milkType;
        this.size = size;
    }

    @Override
    public void prepTime() {
        System.out.println(getSize() + " Capuccino on his way!\n"+"\u231B");
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("\u2705 " +getSize() + " Capuccino with " +getMilkType() + " is ready to go!");
            }
        };
        timer.schedule(task, 8000);
    }

    @Override
    public String getType() {
        return "Capuccino";
    }

    @Override
    public String getSize() {
        return size;
    }
    public String getMilkType() {
        return milkType;
    }
}