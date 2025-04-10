//package service;
//
//import java.io.IOException;
//
//public class DatabaseBackup {
//    public static void backup(String outputPath) throws IOException {
//        String command = "mysqldump -u root -pPhu090204#my05 TestDB -r " + outputPath;
//        Process runtimeProcess = Runtime.getRuntime().exec(command);
//        try {
//            int processComplete = runtimeProcess.waitFor();
//            if (processComplete == 0) {
//                System.out.println("Backup completed successfully: " + outputPath);
//            } else {
//                System.out.println("Backup failed.");
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}
package service;

import java.io.IOException;

public class DatabaseBackup {
    public static void backup(String outputFile) throws IOException {
        String dbName = "StudentEvaluation";
        String dbUser = "root";
        String dbPassword = "<mật khẩu>"; // Thay 'your_password' bằng mật khẩu MySQL

        String command = String.format(
                "mysqldump -u %s -p%s %s -r %s", dbUser, dbPassword, dbName, outputFile);

        Process process = Runtime.getRuntime().exec(command);

        try {
            int processComplete = process.waitFor();
            if (processComplete == 0) {
                System.out.println("Backup completed successfully: " + outputFile);
            } else {
                System.err.println("Backup failed!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new IOException("Backup interrupted: " + e.getMessage());
        }
    }
}

