//package service;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//
//public class Logger {
//    private static final String LOG_FILE = "logs/backup_log.txt";
//
//    public static void log(String message) {
//        try {
//            // Kiểm tra và tạo thư mục logs nếu chưa tồn tại
//            File logDir = new File("logs");
//            if (!logDir.exists()) {
//                logDir.mkdir();
//                System.out.println("Thư mục logs đã được tạo.");
//            }
//
//            // Ghi log vào file
//            FileWriter writer = new FileWriter(LOG_FILE, true);
//            writer.write(message + "\n");
//            writer.close();
//        } catch (IOException e) {
//            System.err.println("Không thể ghi log vào file: " + LOG_FILE);
//            e.printStackTrace();
//        }
//    }
//}

package service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final String LOG_DIR = "logs";
    private static final String LOG_FILE = LOG_DIR + "/backup_log.txt";

    static {
        // Tạo thư mục logs nếu chưa tồn tại
        new java.io.File(LOG_DIR).mkdirs();
    }

    public static void log(String message) {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.write(String.format("[%s] %s%n", timestamp, message));
        } catch (IOException e) {
            System.err.println("Failed to write log: " + e.getMessage());
        }
    }
}


