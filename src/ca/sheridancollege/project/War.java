package ca.sheridancollege.project;


import ca.sheridancollege.project.parentclass.Card;
import ca.sheridancollege.project.parentclass.Player;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 * The class that is used to play the game War.
 *
 * @author  Akanksha
 */


public class War {

    private Boolean war;
    private int player1Score = 0;
    private int player2Score = 0;

    private int battleRoundCount = 1;
    private int warRoundCount = 1;
    public War(Boolean war) {
        this.war = war;
    }

    Scanner input =  new Scanner(System.in);

    public void playGame(Player player1, Player player2, int winCardCount){
        long start = System.nanoTime();
        while(warRoundCount<21){
            if(player1.getPlayerCards().getCards().size()<(winCardCount+1) && player2.getPlayerCards().getCards().size()<(winCardCount+1)) {
                if (!this.war) {
                    playBattle(player1, player2);
                } else {
                    playWar(player1, player2);

                }
            }else{
                break;
            }
        }
        if(this.player1Score>this.player1Score) {
            System.out.printf("The winner is %s with a score of %d", player1.getName(), this.player1Score);
        }else{
            System.out.printf("The winner is %s with a score of %d", player2.getName(), this.player2Score);
        }

        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        System.out.println("\nThank You For Playing! The game took: "+(timeElapsed/1000000000)+" seconds");
        System.out.println("\nWould you like to play again? Y/N");
        String playAgain = input.next();
        if(playAgain.equals("Y") || playAgain.equals("y")){
            playGame(player1, player2, 52);
        }else{
            System.out.println("\nOk! Thank You again For Playing!");
            exit(0);
        }
    }

    private void playBattle(Player player1, Player player2){
        Card player1Card = player1.play();
        Card player2Card = player2.play();
        System.out.println("\n**********Start Battle Round "+this.battleRoundCount+"************");
        System.out.println("Player 1: Pick Card? Y/N");
        String pickCard = input.next();
        if(pickCard.equals("Y") || pickCard.equals("y")) {
            System.out.println("Player 1: " + player1Card.toString());
            System.out.println("Player 2: " + player2Card.toString());
            if (player1Card.getRank().getPointValue() > player2Card.getRank().getPointValue()) {
                this.player1Score += 1;
                System.out.printf("\nPlayer 1: %s won the round. The score is %d : %d", player1.getName(), player1Score, player2Score);
                player1.getPlayerCards().getCards().add(player1Card);
                player1.getPlayerCards().getCards().add(player2Card);
            } else if (player1Card.getRank().getPointValue() == player2Card.getRank().getPointValue()) {
                this.war = true;
            } else {
                this.player2Score += 1;
                System.out.printf("\nPlayer 2: %s won the round. The score is %d : %d", player2.getName(), player1Score, player2Score);
                player2.getPlayerCards().getCards().add(player1Card);
                player2.getPlayerCards().getCards().add(player2Card);
            }
            System.out.println("\n**********End of Battle Round " + this.battleRoundCount + "************");
            this.battleRoundCount++;
        }else{
            System.out.println("Player 1: Would you like to continue the game? Y/N");
            String continueGame = input.next();
            if(continueGame.equals("Y") || continueGame.equals("y")){
                playBattle(player1, player2);
            }else{
                System.out.println("Thank you for Playing, but you Quit so the winner is: "+player2.getName());
                exit(0);
            }
        }
    }

    private void playWar(Player player1, Player player2) {
        ArrayList<Card> player1Top3Cards = new ArrayList<>();
        ArrayList<Card> player2Top3Cards = new ArrayList<>();
        System.out.println("\n**********Start War Round "+this.warRoundCount+"************");
        System.out.println("Take top 3 cards out without show");
        for(int i=1; i<4; i++){
            System.out.println("\nPlayer 1 draw "+i+" card");
            player1Top3Cards.add(player1.play());
            System.out.println("\nPlayer 2 draw "+i+"card");
            player1Top3Cards.add(player2.play());
        }
        Card player1Card4 = player1.play();
        Card player2Card4 = player2.play();
        System.out.println(player1Card4.toString());
        System.out.println(player2Card4.toString());
        if(player1Card4.getRank().getPointValue()>player2Card4.getRank().getPointValue()){
            this.player1Score+=8;
            System.out.printf("\nPlayer 1: %s won the round. The score is %d : %d",player1.getName(), player1Score, player2Score);
            player1.getPlayerCards().getCards().addAll(player1Top3Cards);
            player1.getPlayerCards().getCards().addAll(player2Top3Cards);
            this.war = false;
        }else if(player1Card4.getRank().getPointValue() == player2Card4.getRank().getPointValue()){
            this.war  = true;
        }else{
            this.player2Score+=8;
            System.out.printf("\nPlayer 2: %s won the round. The score is %d : %d",player2.getName(), player1Score, player2Score);
            player2.getPlayerCards().getCards().addAll(player1Top3Cards);
            player2.getPlayerCards().getCards().addAll(player2Top3Cards);
            this.war = false;
        }
        System.out.println("\n**********End of War Round "+this.warRoundCount+"************");

        this.warRoundCount++;

    }

}
