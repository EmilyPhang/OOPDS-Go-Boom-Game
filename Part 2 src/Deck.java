import java.util.ArrayList;
import java.util.Collections;

import java.lang.System;

public class Deck implements IDeck {
    private ArrayList<Card> cards;
    private String[] ranks = {
        "A","2", "3", "4", 
        "5", "6", "7", "8", 
        "9", "X", "J", "Q", "K"
    };
    
    private String[] suits = {"s", "h", "c", "d"};

    public Deck(){
        cards = new ArrayList<Card>();
        for (String suit:suits){        //to create a deck of cards
            for(String rank: ranks){
                Card card = new Card(suit,rank);
                cards.add(card);
            }
        }
        shuffleDeck();
    }
    
    public void setDeck(ArrayList<Card> deck){
        this.cards = deck;
    }

    public void shuffleDeck(){
        Collections.shuffle(cards);
    }

    public void printDeck(){
        System.out.printf("Deck: %s \n", cards);
    }

    public ArrayList<Card> getDeck(){
        return cards;
    }

    public int length(){
        return cards.size();
    }

    public boolean isRankValid(String rank_input){ //to check if the rank is the same
        for (String rank : ranks) {
            if (rank_input.equals(rank)){            
                return true;
            }
        }
        return false; 
    }

    public boolean isSuitValid(String suit_input){ ////to check if the suit is the same
        for (String suit : suits) {
            if (suit_input.equals(suit)){
                return true;
            }
        }
        return false; 
    }
    
    public String validRanks(){
        return ranks.toString();
    }

    public String validSuits(){
        return suits.toString();
    }
   
    public Card getFirstCard(){ //get and remove first card from deck
        Card card_to_give = cards.get(0);
        cards.remove(0);
        return card_to_give; 
    }

    public boolean hasCards(){ //check if deck has cards
        if(cards.size()==0){
            return false;
        }
        return true;
    }

}