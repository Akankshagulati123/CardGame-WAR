/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project.parentclass;

import ca.sheridancollege.project.CardRank;
import ca.sheridancollege.project.CardSuit;
import ca.sheridancollege.project.exception.RankSuitException;

/**
 * A class to be used as the base Card class for the project. Must be general enough to be instantiated for any Card
 * game. Students wishing to add to the code should remember to add themselves as a modifier.
 *
 * @author dancye
 * @modifier Akanksha
 */
public class Card {

    private CardRank rank;
    private CardSuit suit;

    public CardRank getRank() {
        return rank;
    }

    public CardSuit getSuit() {
        return suit;
    }

    public Card(CardRank rank, CardSuit suit){
        if(rank == null || suit == null){
            throw new RankSuitException("The Rank and Suit of the Card can't be empty");
        }
        this.rank = rank;
        this.suit = suit;
    }
    //default modifier for child classes

    /**
     * Students should implement this method for their specific children classes
     *
     * @return a String representation of a card. Could be an UNO card, a regular playing card etc.
     */
    @Override
    public String toString(){
        return String.format("%s of %s", rank, suit);
    }

}

