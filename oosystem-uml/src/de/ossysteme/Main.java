package de.ossysteme;

public class Main {

    public static void main(String[] args) {
        Robot robot = new Robot();
        //noinspection InfiniteLoopStatement
        while (true) {
            robot.run();
        }
    }
}
