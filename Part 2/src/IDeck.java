import java.util.ArrayList;

public interface IDeck {

    public void setDeck(ArrayList<Card> deck);
    public void shuffleDeck();
    public void printDeck();
    public ArrayList<Card> getDeck();
    public int length();
    public boolean isRankValid(String rank_input);
    public boolean isSuitValid(String suit_input);
    public boolean hasCards();
    public Card getFirstCard();
    public String validRanks();
    public String validSuits();
}
