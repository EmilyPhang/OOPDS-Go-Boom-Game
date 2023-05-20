import java.util.ArrayList;
import java.util.Collections;

import java.lang.System;

public class Deck{
    private ArrayList<Card> cards;
    private String[] ranks = {
        "A","2", "3", "4", 
        "5", "6", "7", "8", 
        "9", "X", "J", "Q", "K"
    };
    private String[] suits = {"s", "h", "c", "d"};

    public Deck(){
        cards = new ArrayList<Card>();
        for (String suit:suits){ //"for-each" loop, to loop array
            for(String rank: ranks){
                Card card = new Card(suit,rank);
                cards.add(card);
            }
        }
        shuffleDeck();
    }
 
    public void shuffleDeck(){
        Collections.shuffle(cards);
    }

    public void printDeck(){
        System.out.printf("Deck: %s \n", cards);
    }

    public int length(){
        return cards.size();
    }

    public boolean isRankValid(String rank_input){
        for (String rank : ranks) {
            if (rank_input.equals(rank)){            
                return true;
            }
        }
        return false; 
    }

    public boolean isSuitValid(String suit_input){
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
    // public ArrayList<Card> getCards(){
    //     return cards;
    // }

    // public ArrayList<Card> deal(int numCards) { 
    //     ArrayList<Card> hand = new ArrayList<Card>();
    //     for (int i = 0; i < numCards; i++) {
    //         hand.add(cards.remove(0));
    //     }
    //     return hand;
    // }


    public Card getFirstCard(){
        Card card_to_give = cards.get(0);
        cards.remove(0);
        return card_to_give; 
    }

}