package test;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class Esspreso implements Bevarges{
    String size;

    public Esspreso(String size) {
        this.size = size;
    }

    @Override
    public void prepTime() {
        System.out.println(getSize() + " Espresso on his way!\n" +	"\u231B");
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("\u2705 " +getSize() + " Espresso is ready to go!");
            }
        };
        timer.schedule(task, 8000);
    }
    @Override
    public String getType() {
        return "Esspreso";
    }

    @Override
    public String getSize() {
        return size;
    }
}