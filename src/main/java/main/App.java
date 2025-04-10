package main;

import service.BackupScheduler;

public class App {
    public static void main(String[] args) {
        System.out.println("Starting backup scheduler...");
        BackupScheduler.start();
    }
}
