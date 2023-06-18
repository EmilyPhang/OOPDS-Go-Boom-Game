import java.util.ArrayList;

public class Player implements IPlayer {
    private int playerNum;
    private ArrayList<Card> cards_inhand;
    private ArrayList<Card> cards_played;
    private int score = 0;


    public Player(int playerNum){
        this.playerNum = playerNum;
        cards_inhand = new ArrayList<Card>();
        cards_played = new ArrayList<Card>();
    }


    public Player(){ //to create a player instance and set the player number or cards later using the setter

    }

    public void setCards(ArrayList<Card> cards){
        this.cards_inhand = cards;
    }

    public int getPlayerNum(){
        return playerNum;
    }

    public ArrayList<Card> getCardsInHand(){
        return cards_inhand;
    }

    public void addCard(Card card){
        cards_inhand.add(card);
    }

    public int getScore(){
        return score;
    }

    public void setScore(int score){
        this.score = score;
    }

    public void resetScore(){
        this.score = 0;
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

    public int getMaxRank(Card center_card){ // get the highest rank card played by the player which matches the suit
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

    
    public void removeCard(Card input_card){
        for (int i = 0; i < cards_inhand.size(); i++) {
            if(
                cards_inhand.get(i).getRank().equals(input_card.getRank()) &&
                cards_inhand.get(i).getSuit().equals(input_card.getSuit())
            ){
                cards_inhand.remove(i);
            }
        }
    }


    public void showCards(){
        System.out.printf("Player %d : %s \n", playerNum, cards_inhand);
    }

    public void showPlayedCards(){
        System.out.printf("Player %d - Played Card: %s \n", playerNum, cards_played);
    }

    public Card submitCard(Card input_card){ //when player submit card, cards played updated and cards in hand removed
        for (int i = 0; i < cards_inhand.size(); i++) {
            Card card = cards_inhand.get(i);
            if(
                input_card.getRank().equals(card.getRank()) &&
                input_card.getSuit().equals(card.getSuit())
                ){
                    cards_played.add(input_card);
                    cards_inhand.remove(i);
                }
        }
        return input_card; 
    }

    public boolean hasCenterCard(Card center_card){ //check if cards in hand has any card that follows center card's suit or rank
        for (Card card : cards_inhand) {
            if(
                center_card.getRank().equals(card.getRank()) ||
                center_card.getSuit().equals(card.getSuit())
            ){
                return true;
            }
        }
        return false; 
    }

    // determines whether a given card is a valid play based on the current center card and player's cards in hand
    public boolean hasValidCard(Card input_card, Card current_center_card){
        if(
            input_card.getRank().equals(current_center_card.getRank()) ||
            input_card.getSuit().equals(current_center_card.getSuit())
        ){
            for (Card card : cards_inhand) {
                if(
                    input_card.getRank().equals(card.getRank()) && input_card.getSuit().equals(card.getSuit())
                ){
                    return true;
                }
            }
        }
        return false; 
    }

    //checks if there is a matching card in the player's hand without any reference to the current center card
    public boolean hasValidCard(Card input_card){
        for (Card card : cards_inhand) {
            if(
                input_card.getRank().equals(card.getRank()) && input_card.getSuit().equals(card.getSuit())
            ){
                return true;
            }
        }
        return false; 
    }

    public boolean hasCardInHand(){
        if(cards_inhand.size() == 0){
            return false;
        }
        return true;
    }

    public void clearCardInHand(){
        cards_inhand.clear();
    }

    public void clearCardPlayed(){
        cards_played.clear();
    }
}
