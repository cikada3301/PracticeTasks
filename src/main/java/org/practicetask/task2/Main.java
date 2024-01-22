package org.practicetask.task2;

public class Main {
    public static void main(String[] args) {
        EchoServer echoServer = new EchoServer(7);
        echoServer.start();
    }
}
