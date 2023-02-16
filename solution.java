package org.example;

import java.util.Random;
import java.util.Scanner;

class Parser{
    private final Scanner scanner;
    private int userGuess;

    Parser(Scanner scanner ){
        this.scanner = scanner;
    }
    public int getUserNumber(){
        String input = this.scanner.nextLine();
        try{
            this.userGuess = Integer.parseInt(input);
        }catch (NumberFormatException e) {
            System.out.println("Your input was '" + input + "', please enter a valid integer. ");
        }
        return this.userGuess;
    }
    public String IsUserContinuesGame(){
        String input = scanner.nextLine();
        return input.trim().toLowerCase();
    }

}

class NumberGenerator{
    private final Random generator;

    NumberGenerator(Random generator){
        this.generator = generator;
    }

    int getGenerateNumber(){
        return this.generator.nextInt(100) + 1;
    }
}

class GuessTheNumberGame{
    private final Parser parser;
    private final NumberGenerator generator;
    private int userGuess;
    private int attempts;
    private final int maxAttempts;
    private boolean continuePlaying;

    GuessTheNumberGame(Parser parser, NumberGenerator generator){
        this.parser = parser;
        this.generator = generator;
        this.userGuess = 0;
        this.attempts = 0;
        this.maxAttempts = 10;
        continuePlaying = true;
    }

    public void playGame(){
        System.out.println("-===========================-");
        System.out.println("=== GUESS THE NUMBER GAME ===");
        System.out.println("-===========================-");
        while(this.continuePlaying) {
            System.out.println("Guess the number (between 1 and 100)!");
            int number = this.generator.getGenerateNumber();
            System.out.println("debug : the expected number is " + number);
            while (number != this.userGuess && this.attempts < this.maxAttempts) {
                this.userGuess = parser.getUserNumber();
                this.attempts++;
                if (this.userGuess == number) {
                    System.out.println("You found it after " + this.attempts + " tries!");
                } else {
                    String divergence = this.userGuess < number ? "smaller" : "greater";
                    System.out.println("Wrong! Your number is " + divergence + " than the correct one. " + (this.maxAttempts-this.attempts) + "/" + this.maxAttempts + " tries left");
                }
            }
            if (number != this.userGuess) {
                System.out.println("You lose after " + this.maxAttempts + " tries, the expected number was " + number);
            }

            System.out.println("----------------------------------------------------");
            System.out.println("Do you want to try again with a new number (yes/no)?");
            String userResponse = this.parser.IsUserContinuesGame();
            this.continuePlaying = userResponse.equals("yes");
            if (this.continuePlaying) {
                this.userGuess = 0;
                this.attempts = 0;
            }
        }
        System.out.println("== Thanks for playing! ==");
        }
    }

