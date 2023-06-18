import java.util.ArrayList;

public interface IPlayer {
    public void setCards(ArrayList<Card> cards);
    public int getPlayerNum();
    public int getScore();
    public void setScore(int score);
    public int getMaxRank(Card center_card);
    public ArrayList<Card> getCardsInHand();
    public ArrayList<Card> getCardsPlayed();
    public void addCard(Card card);
    public void addScore(int score);
    public void resetCardsPlayed();
    public void removeCard(Card input_card);
    public void showCards();
    public void showPlayedCards();
    public void clearCardInHand();
    public void clearCardPlayed();
    public boolean hasCenterCard(Card center_card);
    public boolean hasValidCard(Card input_card, Card current_center_card);
    public boolean hasValidCard(Card input_card);
    public boolean hasCardInHand();
    public void resetScore();
}
