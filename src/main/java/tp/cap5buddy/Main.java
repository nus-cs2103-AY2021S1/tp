package tp.cap5buddy;

import tp.cap5buddy.ui.Ui;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String current = sc.nextLine();
            Ui UserInterface = new Ui();
            UserInterface.input(current);
            String result = UserInterface.reply();
            System.out.println(result);
        }
        sc.close();
    }
}
