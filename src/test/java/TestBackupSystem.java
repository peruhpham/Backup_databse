import service.DatabaseBackup;
import service.Logger;

//public class TestBackupSystem {
//    public static void main(String[] args) {
//        String testPath = "test_backup.sql";
//        try {
//            System.out.println("Running backup test...");
//            DatabaseBackup.backup(testPath);
//            Logger.log("Test backup completed: " + testPath);
//        } catch (Exception e) {
//            Logger.log("Test backup failed: " + e.getMessage());
//        }
//    }
//}

import service.DatabaseBackup;
import service.DatabaseOperations;
import service.Logger;
import service.AutoBackup;

import java.util.Scanner;

public class TestBackupSystem {
    public static void main(String[] args) {
        // Khởi động backup tự động
        AutoBackup.startAutoBackup();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Add Student");
            System.out.println("2. Delete Student");
            System.out.println("3. Backup Database");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter Student ID: ");
                        String id = scanner.nextLine();
                        System.out.print("Enter Student Name: ");
                        String name = scanner.nextLine();
                        DatabaseOperations.addStudent(id, name);
                        break;

                    case 2:
                        System.out.print("Enter Student ID to delete: ");
                        String deleteId = scanner.nextLine();
                        DatabaseOperations.deleteStudent(deleteId);
                        break;

                    case 3:
                        String backupFile = "StudentEvaluation_Backup_" +
                                java.time.LocalDate.now() + ".sql";
                        DatabaseBackup.backup(backupFile);
                        Logger.log("Database backup completed: " + backupFile);
                        break;

                    case 4:
                        System.out.println("Exiting...");
                        Logger.log("System exited.");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
                Logger.log("Error: " + e.getMessage());
            }
        }
    }
}


