package swe.slidingpuzzle;

import org.tinylog.Logger;

public class Application {
    public static void main(String[] args) {
        String name = "Bob";

        Logger.info("{} Works", name);
        Logger.debug("{} Works even more", name);
    }
}
