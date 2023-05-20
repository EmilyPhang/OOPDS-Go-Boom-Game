import java.util.ArrayList;

public class Player {
    private int playerNum;
    private ArrayList<Card> cards_inhand;
    private ArrayList<Card> cards_played;
    private int score = 0;

    public Player(int playerNum){
        this.playerNum = playerNum;
        cards_inhand = new ArrayList<Card>();
        cards_played = new ArrayList<Card>();
    }

    public int getPlayerNum(){
        return playerNum;
    }

    // public ArrayList<Card> getHand(){
    //     return cards_inhand;
    // }

    public void addCard(Card card){
        cards_inhand.add(card);
    }

    public int getScore(){
        return score;
    }

    public void addScore(int score){
        this.score += score;
    }

    public ArrayList<Card> getCardsPlayed(){
        return this.cards_played; 
    }
    
    public void resetCardsPlayed(){
        cards_played.clear();
    }

    public int getMaxRank(Card center_card){
        int maxRank = 0; 
        for (Card card : cards_played) {
            // Player suit must be same with Center suit 
            // Get the highest Rank for this player
            if (card.getSuit().equals(center_card.getSuit())){
                maxRank = Math.max(maxRank, card.getRankValue());
            }
        }
        return maxRank; 
    }

    // public int getMaxRank(){
    //     int maxRank = 0; 
    //     for (Card card : cards_played) {
    //         maxRank = Math.max(maxRank, card.getRankValue());
    //     }
    //     return maxRank; 
    // }
    
    // public void removeCard(Card card){
    //     cards_inhand.remove(card);
    // }

    // public String toString(){
    //     return playerNum + ": " + cards_inhand.toString();
    // }

    public void showCards(){
        System.out.printf("Player %d : %s \n", playerNum, cards_inhand);
    }

    public void showPlayedCards(){
        System.out.printf("Player %d - Played Card: %s \n", playerNum, cards_played);
    }

    public Card submitCard(Card input_card){
        for (int i = 0; i < cards_inhand.size(); i++) {
            Card card = cards_inhand.get(i);
            if(
                input_card.getRank().equals(card.getRank()) &
                input_card.getSuit().equals(card.getSuit())
                ){
                    cards_played.add(input_card);
                    cards_inhand.remove(i);
                }
        }
        return input_card; 
    }

    public boolean hasValidCard(Card input_card){
        for (Card card : cards_inhand) {
            if(
                input_card.getRank().equals(card.getRank()) &
                input_card.getSuit().equals(card.getSuit())
            ){
                return true;
            }
        }
        return false; 
    }
}
