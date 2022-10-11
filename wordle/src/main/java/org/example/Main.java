package org.example;

import java.util.Scanner;
public class Main {
    public String [] searchWord(String userInput, String computerInput){
        int i = 0;
        int j = 0;
        int hitCount = 0;
        String finalWord = "";
        String [] finalResulst = new String[2];
        char [] originalWord = computerInput.toLowerCase().toCharArray();
        String usersWord = userInput.toLowerCase();

        while(i < usersWord.length()){
            if (usersWord.charAt(i) == originalWord[j] && i==j){
                finalWord += Util.getFormattedLetter(usersWord.charAt(i), Util.Result.HIT);
                originalWord[j] = '1';
                i++;
                j = 0;
                hitCount ++;
            } else if (usersWord.charAt(i) == originalWord[j] && i != j) {
                finalWord += Util.getFormattedLetter(usersWord.charAt(i), Util.Result.SEMI_HIT);
                originalWord[j] = '1';
                i++;
                j = 0;
            } else if (usersWord.charAt(i) != originalWord[j] && j == originalWord.length-1) {
                finalWord += Util.getFormattedLetter(usersWord.charAt(i), Util.Result.MISS);
                i++;
                j = 0;
            } else {
                j++;
            }
        }
        finalResulst[0] = finalWord;
        finalResulst[1] = Integer.toString(hitCount);
        return finalResulst;
    }

    public static void main(String[] args) {
        int chance = 1;
        Main obj = new Main();
        Scanner sc = new Scanner(System.in);
        String word = Util.getRandomWord();
        String [] gameResults = new String[2];

        System.out.println("*********************************");
        System.out.println("** Welcome to the Wordle Game! **");
        System.out.println("*********************************\n");

        while (true){
            System.out.println("Enter a five letter word\n");
            String user_word = sc.nextLine();
            if(user_word.length() == 5){
                gameResults = obj.searchWord(user_word, word);
                System.out.println(gameResults[0]);
                System.out.println(word);
                if(Integer.valueOf(gameResults[1]) == word.length()){
                    System.out.println("*********************************");
                    System.out.println("**   Congratulations you won!  **");
                    System.out.println("*********************************\n");
                    break;
                } else if (Integer.valueOf(gameResults[1]) != word.length() && chance == 5) {
                    System.out.println("*********************************");
                    System.out.println("**        Sorry you lost       **");
                    System.out.println("**       The word was " + word +"    **");
                    System.out.println("*********************************\n");
                    break;
                }
                chance++;
            }
        }
    }
}