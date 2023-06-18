public interface ICard {
    public void printCard();
    public String getSuit(); 
    public String getRank();
    public String toString();
    public int getRankValue(); //to determine who goes next in the trick
    public int getCardScore(); //calculate score after winning a trick
}
