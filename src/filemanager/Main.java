package filemanager;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Sorin
 */
public class Main {

    public static void main(String[] args) {
        label:
        while (true) {
            System.out.println("Introduce-ti una din comenzi(LIST,INFO,CREATE_DIR,RENAME,COPY,MOVE,DELETE or EXIT):");
            try {

                MyFileManage fm = new MyFileManage();
                Scanner s = new Scanner(System.in);

                String cmd = s.nextLine();

                switch (cmd) {
                    case "LIST": {
                        System.out.println("Introduce-ti calea :exemplu(C:\\JavaLearning\\LIST)");

                        File f = new File(s.nextLine());
                        fm.list(f);
                        break;
                    }

                    case "INFO": {
                        System.out.println("Intruduce-ti calea pina la fisier:");
                        File f = new File(s.nextLine());
                        fm.info(f);
                        break;
                    }

                    case "CREATE_DIR": {
                        System.out.println("Introduce-ti calea si numele folderului: ");
                        File f = new File(s.nextLine());
                        fm.createDir(f);
                        break;
                    }

                    case "RENAME": {
                        System.out.println("Introduce-ti calea si numele folderului pt redenumire: ");
                        File oldName = new File(s.nextLine());
                        System.out.println("Introduce-ti calea si numele folderului cu noul nume: ");
                        File newName = new File(s.nextLine());
                        fm.rename(newName, oldName);
                        break;
                    }

                    case "COPY": {
                        System.out.println(": ");
                        File src = new File(s.nextLine());
                        System.out.println("Introduce-ti calea si numele fisierului unde va fii copiat: ");
                        File dest = new File(s.nextLine());
                        fm.copyDir(src, dest);
                        break;
                    }

                    case "MOVE": {
                        System.out.println("Introduce-ti calea si numele fisierului ce trebuie mutat: ");
                        File source = new File(s.nextLine());
                        System.out.println("Introduce-ti calea unde va fii mutat: ");
                        File dest = new File(s.nextLine());

                        fm.move(source, dest);
                        break;
                    }

                    case "DELETE": {
                        System.out.println("Introduce-ti calea si numele fisierului ce trebuie sters: ");
                        File f = new File(s.nextLine());
                        fm.deleteDir(f);
                        break;
                    }

                    case "EXIT": {
                        fm.exit();
                        break label;
                    }

                    default: {
                        System.out.println("Commanda nerecunoscuta!\nIntroduce-ti una din comenziile din lista!");
                    }
                }
            } catch (Exception e) {
                System.out.println(e);

            }
        }
    }

}
