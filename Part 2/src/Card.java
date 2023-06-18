public class Card implements ICard { 
    private String rank;
    private String suit;
    

    public Card(String suit ,String rank){ //define instance variables
        this.suit = suit;
        this.rank = rank; 
    }
    
    public void printCard(){
        System.out.println(toString());
    }

    //getter
    public String getSuit(){ 
        return suit;
    }

    public String getRank(){
        return rank;
    }

    public String toString(){
        return suit + rank;
    }

    public int getRankValue() //to determine who goes next in the trick
    {
        int rankValue = 0;
        switch(getRank()){
            case "X":
                rankValue = 10;
                break;
            case "J":
                rankValue = 11;
                break;
            case "Q":
                rankValue = 12;
                break;
            case "K":
                rankValue = 13;
                break;
            case "A":
                rankValue = 14;
                break;
            default:
                rankValue = Integer.parseInt(getRank()); //converting a string value to an integer
                break;
        }
        return rankValue;
    }

    public int getCardScore(){ //calculate score after winning a Round
        int rankScore = 0;
        switch (rank) {
            case "A":
                rankScore = 1;
                break;
            case "X":
            case "J":
            case "Q":
            case "K":
                rankScore = 10;
                break;
            default:
                rankScore = Integer.parseInt(rank);
                break;
        }
        return rankScore;
    }
}

