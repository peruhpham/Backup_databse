
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
                // Định dạng thời gian cho tên file
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
                String formattedTime = now.format(formatter);

                // Đường dẫn thư mục backups
                String folderPath = "backups";
                File folder = new File(folderPath);

                // Kiểm tra và tạo thư mục nếu chưa tồn tại
                if (!folder.exists()) {
                    if (folder.mkdir()) {
                        System.out.println("Backup folder created: " + folderPath);
                    } else {
                        System.err.println("Failed to create backup folder: " + folderPath);
                        return;
                    }
                }

                // Tạo đường dẫn file backup
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
        }, 0, 3000); // Backup mỗi 24 giờ
    }
}
