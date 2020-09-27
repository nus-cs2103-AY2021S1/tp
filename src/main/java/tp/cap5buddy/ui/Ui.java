package tp.cap5buddy.ui;

import tp.cap5buddy.parser.ParserManager;

import java.util.Scanner;

public class Ui {
    private boolean isExit;
    private String current_Input;
    private static Scanner sc = new Scanner(System.in);
    private static ParserManager pm = new ParserManager();
    public Ui() {
        this.isExit = false;
        this.current_Input = null;
    }

    private void setInput(String input) {
        this.current_Input = input;
    }

    private String sendToParser() {
        return pm.parse(this.current_Input);
    }

    private void printResult(String result) {
        System.out.println(result);
    }

    public void startScanner() {
        System.out.println("Starting scanner");
        String current = sc.nextLine();
        setInput(current);
        String result = sendToParser();
        printResult(result);
        System.out.println("Entering while loop");
        while (sc.hasNextLine()) {
            current = sc.nextLine();
            setInput(current);
            result = sendToParser();
            printResult(result);
        }
        sc.close();
    }
}
