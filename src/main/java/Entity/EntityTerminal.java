package Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class EntityTerminal {

    private Scanner input = new Scanner(System.in);

    public void printString(String message){
        System.out.println(message);
    }

    public void drawCardNotification(String drawn, boolean noCard){
        if (noCard) System.out.println("Cannot play a card! Draw one more card");
        System.out.println("The card you draw is " + drawn);
    }

    public String typeSetColor() {
        ArrayList<String> colors = new ArrayList<>();
        Collections.addAll(colors, "red", "blue", "yellow", "green");

        System.out.println("Type a color you want to set:");
        String setColor = null;
        int wrongTimeCounter = 0;

        while (wrongTimeCounter < 3){
            setColor = input.nextLine();
            if (!colors.contains(setColor) && wrongTimeCounter < 2) {
                System.out.println("Wrong color! Type again:");
                setColor = input.nextLine();
            }
            else if (!colors.contains(setColor) && wrongTimeCounter == 2) {
                System.out.println("Wrong color 3 times! Color randomly chosen");
                setColor = colors.get((int)(Math.random() * colors.size()));
            } else {
                break;
            }
            wrongTimeCounter++;
        }
        System.out.println("Color " + setColor + " is set.");
        return setColor;
    }
}
