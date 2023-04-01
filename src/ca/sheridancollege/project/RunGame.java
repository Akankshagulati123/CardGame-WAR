package ca.sheridancollege.project;
import java.util.*;

import ca.sheridancollege.project.parentclass.GroupOfCards;
import ca.sheridancollege.project.parentclass.Player;

public class RunGame {

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        String[] randomNames = {"Chifuyu", "Mikey", "Eren", "Levi", "Itachi" };

        System.out.println("Please enter your name");
        String playerOneName = input.next();
        Player player1 = new Player(playerOneName);

        //select random name for computer from randomNames list
        Random r = new Random();
        Player player2 = new Player(randomNames[r.nextInt(randomNames.length)]);

        //create deck of cards and then distribute cards in between the players
        System.out.printf("Distribute Cards to\nPlayer 1: %s\nPlayer 2: %s\n",player1.getName(), player2.getName());
        GroupOfCards  groupOfCards = new GroupOfCards(52);

        PlayerCards playerOneCard = new PlayerCards(26, 0, 25, groupOfCards.getShuffledCards());
        PlayerCards playerTwoCard = new PlayerCards(26, 26, 51, groupOfCards.getShuffledCards());

        //distribute cards
        player1.setPlayerCards(playerOneCard);
        player2.setPlayerCards(playerTwoCard);


        //Start the game
        War startGame = new War(false);
        startGame.playGame(player1, player2, 52);
    }
}
