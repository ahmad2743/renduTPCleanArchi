package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String arg[]){
        Scanner scanner = new Scanner(System.in);
        Random generator = new Random();
        Parser parser = new Parser(scanner);
        NumberGenerator numberGenerator = new NumberGenerator(generator);

        GuessTheNumberGame game = new GuessTheNumberGame(parser, numberGenerator);
        game.playGame();
    }

}
