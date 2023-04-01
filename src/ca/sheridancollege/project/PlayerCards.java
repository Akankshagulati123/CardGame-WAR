package ca.sheridancollege.project;


import ca.sheridancollege.project.parentclass.Card;
import ca.sheridancollege.project.parentclass.GroupOfCards;

import java.util.ArrayList;

/**
 * A class that models cards for each Player in the game.
 * @author Akanksha
 */
public class PlayerCards extends GroupOfCards {
    private ArrayList<Card> cards = new ArrayList<>();
    public PlayerCards(int size , int startIndex, int  endIndex, ArrayList<Card> cardsAre) {
        super(size);
        for(int i=startIndex; i<endIndex; i++){
            this.cards.add(cardsAre.get(i));
        }
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }
}
