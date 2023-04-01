package testcases;

import ca.sheridancollege.project.CardRank;
import ca.sheridancollege.project.CardSuit;
import ca.sheridancollege.project.PlayerCards;
import ca.sheridancollege.project.exception.RankSuitException;
import ca.sheridancollege.project.parentclass.Card;
import ca.sheridancollege.project.parentclass.GroupOfCards;
import ca.sheridancollege.project.parentclass.Player;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;


public class FullCodeTest {

    GroupOfCards groupOfCards = new GroupOfCards(52);
    PlayerCards playerCards = new PlayerCards(26, 0, 25, groupOfCards.getShuffledCards());
    ArrayList<Card> cards = new ArrayList<>();
    Card card =  new Card(CardRank.valueOf("FIVE"), CardSuit.valueOf("DIAMONDS"));




    //Player methods

    @Test
    public void playTestToTestGetPlayerCardMethod(){
        cards.add(card);
        playerCards.setCards(cards);
        Assert.assertEquals(playerCards.getCards(), cards);
    }


    @Test
    public void playTestToTestWhenPlayerPlays(){
        cards.add(card);
        playerCards.setCards(cards);
        Assert.assertEquals(playerCards.getCards().get(0), card);
        playerCards.getCards().remove(0);
        Assert.assertEquals(playerCards.getCards().size(), 0);
    }


    //Card Rank/Suit empty exception test

    @Test
    public void emptyRankOrSuitShouldThrowExceptionTest(){
        Assert.assertThrows(RankSuitException.class, ()->{new Card(null, CardSuit.valueOf("DIAMONDS"));});
    }

    //Card toString() test
    @Test
    public void CardToStringTest(){
        Assert.assertEquals(card.toString(), "Five of Diamonds");
    }

    //War class test

    // testing battle case
    @Test
    public void playGameTestToCheckIfFirstPlayerWinsWithoutWar(){
        Player player1 = new Player("Chifuyu");
        Player player2 = new Player("Levi");
        ArrayList<Card> cards = returnCardsForPlayerOneWithoutWin();
        PlayerCards playerOneCard = new PlayerCards(3,0,2,cards);
        PlayerCards playerTwoCard = new PlayerCards(3,3,5,cards);
        player1.setPlayerCards(playerOneCard);
        player2.setPlayerCards(playerTwoCard);
        //Player1 will get cards with higher ranks so player 1 should win, This is a small game with 6 cards in total

        //checking base logic of comparing cards in a battle
        Card player1Card = playerOneCard.getCards().get(0);
        Card player2Card = playerTwoCard.getCards().get(0);
        int player1Score = 0;
        int player2Score = 0;
        if (player1Card.getRank().getPointValue() > player2Card.getRank().getPointValue()) {
            player1Score += 1;
            player1.getPlayerCards().getCards().add(player1Card);
            player1.getPlayerCards().getCards().add(player2Card);
        } else {
            player2Score += 1;
            player2.getPlayerCards().getCards().add(player1Card);
            player2.getPlayerCards().getCards().add(player2Card);
        }
        Assert.assertEquals(player1Score, 1);
        Assert.assertEquals(player2Score, 0);

    }

    @Test
    public void playGameTestToCheckIfPlayerTwoWinsWithoutWar(){
        Player player1 = new Player("Chifuyu");
        Player player2 = new Player("Levi");
        ArrayList<Card> cards = returnCardsForPlayerTwoWinWithoutWar();
        PlayerCards playerOneCard = new PlayerCards(3,0,2,cards);
        PlayerCards playerTwoCard = new PlayerCards(3,3,5,cards);
        player1.setPlayerCards(playerOneCard);
        player2.setPlayerCards(playerTwoCard);
        //Player1 will get cards with higher ranks so player 1 should win, This is a small game with 6 cards in total

        //checking base logic of comparing cards in a battle
        Card player1Card = playerOneCard.getCards().get(0);
        Card player2Card = playerTwoCard.getCards().get(0);
        int player1Score = 0;
        int player2Score = 0;
        if (player1Card.getRank().getPointValue() > player2Card.getRank().getPointValue()) {
            player1Score += 1;
            player1.getPlayerCards().getCards().add(player1Card);
            player1.getPlayerCards().getCards().add(player2Card);
        } else {
            player2Score += 1;
            player2.getPlayerCards().getCards().add(player1Card);
            player2.getPlayerCards().getCards().add(player2Card);
        }
        Assert.assertEquals(player1Score, 0);
        Assert.assertEquals(player2Score, 1);

    }

    //testing war case

    @Test
    public void playGameTestToCheckIfPlayerOneWinsWithWar(){
        Player player1 = new Player("Chifuyu");
        Player player2 = new Player("Levi");
        ArrayList<Card> player1Top3Cards = new ArrayList<>();
        ArrayList<Card> player2Top3Cards = new ArrayList<>();
        ArrayList<Card> cards = returnCardsForPlayerOneWithWar();
        PlayerCards playerOneCard = new PlayerCards(5,0,4,cards);
        PlayerCards playerTwoCard = new PlayerCards(5,5,9,cards);
        player1.setPlayerCards(playerOneCard);
        player2.setPlayerCards(playerTwoCard);
        //Player1 will get cards with higher ranks so player 1 should win, This is a small game with 6 cards in total

        //checking base logic of comparing cards in a war
        for(int i=1; i<4; i++){
            player1Top3Cards.add(player1.play());
            player1Top3Cards.add(player2.play());
        }

        int player1Score = 0;
        int player2Score = 0;

        Card player1Card4 = player1.play();
        Card player2Card4 = player2.play();
        if(player1Card4.getRank().getPointValue()>player2Card4.getRank().getPointValue()){
            player1Score+=8;
            player1.getPlayerCards().getCards().addAll(player1Top3Cards);
            player1.getPlayerCards().getCards().addAll(player2Top3Cards);
        }else{
            player2Score+=8;
            player2.getPlayerCards().getCards().addAll(player1Top3Cards);
            player2.getPlayerCards().getCards().addAll(player2Top3Cards);
        }
        Assert.assertEquals(player1Score, 8);
        Assert.assertEquals(player2Score, 0);

    }

    @Test
    public void playGameTestToCheckIfPlayerTwoWinsWithWar(){
        Player player1 = new Player("Chifuyu");
        Player player2 = new Player("Levi");
        ArrayList<Card> player1Top3Cards = new ArrayList<>();
        ArrayList<Card> player2Top3Cards = new ArrayList<>();
        ArrayList<Card> cards = returnCardsForPlayerTwoWinWithWar();
        PlayerCards playerOneCard = new PlayerCards(5,0,4,cards);
        PlayerCards playerTwoCard = new PlayerCards(5,5,9,cards);
        player1.setPlayerCards(playerOneCard);
        player2.setPlayerCards(playerTwoCard);
        //player2 will get cards with higher ranks so player2 should win, This is a small game with 6 cards in total

        //checking base logic of comparing cards in a war
        for(int i=1; i<4; i++){
            player1Top3Cards.add(player1.play());
            player1Top3Cards.add(player2.play());
        }

        int player1Score = 0;
        int player2Score = 0;

        Card player1Card4 = player1.play();
        Card player2Card4 = player2.play();
        if(player1Card4.getRank().getPointValue()>player2Card4.getRank().getPointValue()){
            player1Score+=8;
            player1.getPlayerCards().getCards().addAll(player1Top3Cards);
            player1.getPlayerCards().getCards().addAll(player2Top3Cards);
        }else{
            player2Score+=8;
            player2.getPlayerCards().getCards().addAll(player1Top3Cards);
            player2.getPlayerCards().getCards().addAll(player2Top3Cards);
        }
        Assert.assertEquals(player1Score, 0);
        Assert.assertEquals(player2Score, 8);

    }


    private ArrayList<Card> returnCardsForPlayerOneWithoutWin(){
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(CardRank.valueOf("FIVE"), CardSuit.valueOf("DIAMONDS")));
        cards.add(new Card(CardRank.valueOf("SIX"), CardSuit.valueOf("DIAMONDS")));
        cards.add(new Card(CardRank.valueOf("SEVEN"), CardSuit.valueOf("DIAMONDS")));
        cards.add(new Card(CardRank.valueOf("TWO"), CardSuit.valueOf("DIAMONDS")));
        cards.add(new Card(CardRank.valueOf("THREE"), CardSuit.valueOf("DIAMONDS")));
        cards.add(new Card(CardRank.valueOf("FOUR"), CardSuit.valueOf("DIAMONDS")));
        return cards;
    }

    private ArrayList<Card> returnCardsForPlayerTwoWinWithoutWar(){
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(CardRank.valueOf("TWO"), CardSuit.valueOf("DIAMONDS")));
        cards.add(new Card(CardRank.valueOf("THREE"), CardSuit.valueOf("DIAMONDS")));
        cards.add(new Card(CardRank.valueOf("FOUR"), CardSuit.valueOf("DIAMONDS")));
        cards.add(new Card(CardRank.valueOf("FIVE"), CardSuit.valueOf("DIAMONDS")));
        cards.add(new Card(CardRank.valueOf("SIX"), CardSuit.valueOf("DIAMONDS")));
        cards.add(new Card(CardRank.valueOf("SEVEN"), CardSuit.valueOf("DIAMONDS")));
        return cards;
    }

    private ArrayList<Card> returnCardsForPlayerOneWithWar(){
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(CardRank.valueOf("FIVE"), CardSuit.valueOf("DIAMONDS")));
        cards.add(new Card(CardRank.valueOf("SIX"), CardSuit.valueOf("DIAMONDS")));
        cards.add(new Card(CardRank.valueOf("SEVEN"), CardSuit.valueOf("DIAMONDS")));
        cards.add(new Card(CardRank.valueOf("EIGHT"), CardSuit.valueOf("DIAMONDS")));
        cards.add(new Card(CardRank.valueOf("NINE"), CardSuit.valueOf("DIAMONDS")));

        cards.add(new Card(CardRank.valueOf("FIVE"), CardSuit.valueOf("DIAMONDS")));
        cards.add(new Card(CardRank.valueOf("TWO"), CardSuit.valueOf("DIAMONDS")));
        cards.add(new Card(CardRank.valueOf("THREE"), CardSuit.valueOf("DIAMONDS")));
        cards.add(new Card(CardRank.valueOf("FOUR"), CardSuit.valueOf("DIAMONDS")));
        cards.add(new Card(CardRank.valueOf("FIVE"), CardSuit.valueOf("DIAMONDS")));
        return cards;
    }

    private ArrayList<Card> returnCardsForPlayerTwoWinWithWar(){
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card(CardRank.valueOf("FIVE"), CardSuit.valueOf("DIAMONDS")));
        cards.add(new Card(CardRank.valueOf("TWO"), CardSuit.valueOf("DIAMONDS")));
        cards.add(new Card(CardRank.valueOf("THREE"), CardSuit.valueOf("DIAMONDS")));
        cards.add(new Card(CardRank.valueOf("FOUR"), CardSuit.valueOf("DIAMONDS")));
        cards.add(new Card(CardRank.valueOf("FIVE"), CardSuit.valueOf("DIAMONDS")));

        cards.add(new Card(CardRank.valueOf("FIVE"), CardSuit.valueOf("DIAMONDS")));
        cards.add(new Card(CardRank.valueOf("SIX"), CardSuit.valueOf("DIAMONDS")));
        cards.add(new Card(CardRank.valueOf("SEVEN"), CardSuit.valueOf("DIAMONDS")));
        cards.add(new Card(CardRank.valueOf("EIGHT"), CardSuit.valueOf("DIAMONDS")));
        cards.add(new Card(CardRank.valueOf("NINE"), CardSuit.valueOf("DIAMONDS")));
        return cards;
    }


}
