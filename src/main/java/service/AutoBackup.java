
package service;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class AutoBackup {
    public static void startAutoBackup() {
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
                String formattedTime = now.format(formatter);

                String folderPath = "backups";
                File folder = new File(folderPath);

                if (!folder.exists()) {
                    if (folder.mkdir()) {
                        System.out.println("Backup folder created: " + folderPath);
                    } else {
                        System.err.println("Failed to create backup folder: " + folderPath);
                        return;
                    }
                }

                String backupFile = folderPath + "/AutoBackup_" + formattedTime + ".sql";

                try {
                    DatabaseBackup.backup(backupFile);
                    Logger.log("Automatic backup completed at " + formattedTime + ": " + backupFile);
                    System.out.println("Automatic backup completed at " + formattedTime + ": " + backupFile);
                } catch (Exception e) {
                    Logger.log("Automatic backup failed at " + formattedTime + ": " + e.getMessage());
                    System.err.println("Automatic backup failed at " + formattedTime + ": " + e.getMessage());
                }
            }
        }, 0, 3000); // Backup mỗi làn 3s, test
    }
}
