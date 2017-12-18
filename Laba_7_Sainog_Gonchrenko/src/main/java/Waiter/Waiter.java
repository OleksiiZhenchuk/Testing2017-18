package Waiter;

public class Waiter {
    public static void Wait(int time){
        try{
            Thread.sleep(time);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
