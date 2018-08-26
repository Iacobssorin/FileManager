package filemanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 *
 * @Sorin
 */
public class MyFileManage {

    public static void list(File file) {

        if (file.exists() && file.isDirectory()) {
            File[] files;
            files = file.listFiles();
            for (File file1 : files) {
                System.out.println(file1.getName());
            }
        } else {
            System.out.println("Calea catre fisier nu exista!");
        }
    }

    public static void info(File file) {

        if (file.exists()) {
            System.out.println("Nume: " + file.getName());
            System.out.println("Cale:" + file.getAbsolutePath());
            System.out.println("Marime: " + file.length() + " bytes");

            Instant instant = Instant.ofEpochMilli(file.lastModified());
            LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd. MMMM yyyy. HH:mm:ss");

            System.out.println("Ultima modificare: " + dateTime.format(dtf));
        }
    }

    public static void createDir(File file) {

        if (!file.exists()) {
            file.mkdir();
            System.out.println("Folderul  " + file.getName() + " a fost creat cu succes!");

        } else {
            System.out.println("Folderul cu acest nume exista deja!");
        }
    }

    public static void rename(File newName, File oldName) {
        if (!oldName.exists()) {
            System.out.println("Fisierul|Folderul cu acest nume este inexistent");
            return;
        }
        if (newName.exists()) {
            System.out.println("Fisierul|Folderul cu acest nume exista deja!");
            return;
        }
        if (oldName.renameTo(newName)) {
            System.out.println("Fisierul sau folderul a fost redenumit cu succes");
        } else {
            System.out.println("Redenumirea a esuat!");
        }
    }

    public static void copyDir(File src, File dest) {

        if (src.isDirectory()) {
            if (!dest.exists()) {
                dest.mkdir();

                String fs[] = src.list();
                for (String file : fs) {
                    copyDir(new File(src, file), new File(dest, file));
                }
            }
        } else {
            try (FileInputStream inStream = new FileInputStream(src);
                    FileOutputStream outStream = new FileOutputStream(dest);) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = inStream.read(buffer)) > 0) {
                    outStream.write(buffer, 0, length);
                }
                System.out.println("Copiere cu succes!");
                inStream.close();
                outStream.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public static void move(File source, File dest) {

        if (!dest.exists()) {
            dest.mkdirs();
        }
        if (source.exists() && source.isDirectory()) {
            File[] listOfFiles = source.listFiles();
            if (listOfFiles != null) {
                for (File listed : listOfFiles) {
                    listed.renameTo(new File(dest + "\\" + listed.getName()));
                }
                source.delete();
            }
        } else {
            System.out.println(source + " inexistenta");
        }
    }

    public static void deleteDir(File file) {
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                if (file.isDirectory()) {
                    deleteDir(f);
                } else {
                    file.delete();
                }
            }
        }
        if (file.exists()) {
            file.delete();
            System.out.println("Fisier sters!");
        } else {
            System.out.println("Nu se poate sterge " + file.getName() + ",fisier inexistent");
        }
    }

    void exit() {
        System.out.println("Applicatia sa terminat");
    }
}
