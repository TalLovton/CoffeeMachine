package test;

import java.util.Timer;
import java.util.TimerTask;

public class Late implements Bevarges{
    String milkType;
    String size;

    public Late(String milkType, String size) {
        this.milkType = milkType;
        this.size = size;
    }

    @Override
    public void prepTime() {
        System.out.println(getSize() + " Late on his way!\n"+	"\u231B");
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("\u2705 " + getSize() + " Late with " +getMilkType() + " is ready to go!");
            }
        };
        timer.schedule(task, 8000);
    }



    @Override
    public String getType() {
        return "Late";
    }

    @Override
    public String getSize() {
        return size;
    }
    public String getMilkType() {
        return milkType;
    }
}
