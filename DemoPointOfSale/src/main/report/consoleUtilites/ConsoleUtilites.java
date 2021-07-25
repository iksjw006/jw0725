package main.report.consoleUtilites;

public class ConsoleUtilites {
    public static void clearWindow() {
        try {
            if (System.getProperty("os.name").contains("Windows")){
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033\143");
            }
        } catch (Exception ex) {
            System.out.println(new String(new char[50]).replace("\0", "\r\n"));
        }
    }
}
