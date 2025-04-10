package service;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BackupScheduler {
    public static void start() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable backupTask = () -> {
            String outputPath = "backups/backup_" + System.currentTimeMillis() + ".sql";
            try {
                DatabaseBackup.backup(outputPath);
                Logger.log("Backup completed: " + outputPath);
            } catch (IOException e) {
                Logger.log("Backup failed: " + e.getMessage());
                e.printStackTrace();
            }
        };

        // Lịch chạy 24 giờ/lần
        scheduler.scheduleAtFixedRate(backupTask, 0, 24, TimeUnit.HOURS);
    }
}
