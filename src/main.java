import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

public class main {
    public static int TOTAL_PLAYERS = 4;
    public static int NUMBER_OF_CARDS = 7; 
    public static ArrayList<Player> players; 
    public static Deck deck; 
    public static ArrayList<Card> center_cards = new ArrayList<Card>(); 
    public static Dictionary<String, Integer> who_go_first = new Hashtable<>();
    public static int player_index; 
    public static int current_center_card_index = 0; 
    public static int TRICK_COUNTER = 1; 

    public static void printPlayerCards(){
        for (int player_index = 0; player_index < TOTAL_PLAYERS; player_index++) {
            players.get(player_index).showCards();
            // players.get(player_index).showPlayedCards();
        }
    }

    public static void printDeck(){
        deck.printDeck();
    }

    public static void printCenter(){
        System.out.printf("Center: %s \n", center_cards.toString());
    }

    public static void initWhoGoFirst(){
        who_go_first.put("A", 0);
        who_go_first.put("5", 0);
        who_go_first.put("9", 0);
        who_go_first.put("K", 0);

        who_go_first.put("2", 1);
        who_go_first.put("6", 1);
        who_go_first.put("X", 1);

        who_go_first.put("3", 2);
        who_go_first.put("7", 2);
        who_go_first.put("J", 2);

        who_go_first.put("4",3);
        who_go_first.put("8", 3);
        who_go_first.put("Q", 3);
    }
    
    public static void gamePrinter(){
        System.out.printf("\n\nTRICK #%d \n", TRICK_COUNTER); 
        printPlayerCards();
        printCenter();
        printDeck();
        printScore();
        System.out.printf("Turn: Player%d \n", player_index+1);
        // System.out.printf("Length of Deck: %d \n", deck.length());
        // System.out.printf("Start index: %d, Current Player %d \n ", player_index, player_index+1);
    }

    public static void printScore() {
        System.out.print("Score: ");
        for (Player player : players) {
            System.out.printf("Player%d = %d | ", player.getPlayerNum(), player.getScore());
        }
        System.out.println("");
    }

    public static void calculateScore(){
        Card center_card = center_cards.get(current_center_card_index);
        for (Player player : players) {
            for (Card player_card : player.getCardsPlayed()) {
                // if same suits, get the score
                if(center_card.getSuit().equals(player_card.getSuit())){                    
                    player.addScore(player_card.getCardScore());
                }
            }
        }
    }

    public static void initGame(){
        deck = new Deck();
        initWhoGoFirst();
        center_cards.add(deck.getFirstCard());

        players = new ArrayList<Player>();
        // Create player
        for (int player_index = 0; player_index < TOTAL_PLAYERS; player_index++) {
            players.add(player_index, new Player(player_index+1));
        }

        // Distribute Card
        for (int card_index = 0; card_index < NUMBER_OF_CARDS; card_index++) {
            for (int player_index = 0; player_index < TOTAL_PLAYERS; player_index++) {
                players.get(player_index).addCard(deck.getFirstCard()); 
            }
        }

        player_index = who_go_first.get(center_cards.get(0).getRank());
    }

    public static Card getCurrentCenterCard(){
        return center_cards.get(current_center_card_index);
    }

    public static boolean isValidCard(String user_input, Player current_player){
        if(user_input.length() != 2){
            return false;
        }
        String user_suit = String.valueOf(user_input.toString().charAt(0));
        String user_rank = String.valueOf(user_input.toString().charAt(1));

        // Check is the card valid
        if (deck.isRankValid(user_rank) || deck.isSuitValid(user_suit)){
            Card user_card = getUserCard(user_input); 

            if(center_cards.size()==0){
                return true;        //It is valid for the center cards to be null and set by the previous winner
            }

            Card current_center_card = getCurrentCenterCard();
            // Check is the input and current center card valid
            if(
                current_center_card.getRank().equals(user_card.getRank()) ||
                current_center_card.getSuit().equals(user_card.getSuit())
            ){
                // Check user has the card or not
                if(current_player.hasValidCard(user_card)){
                    return true;        // Valid rank, Valid Suit and User has the card
                }
                else{
                    System.out.printf("User does't has the card (%s) \n", user_card.toString());
                }
            }
            else{
                System.out.printf("User input(%s) doesn't match the center card (%s)", user_card.toString(), current_center_card.toString());
            }  
        }
        System.out.printf("User does not entered the right card format (%s)\n", user_suit+user_rank);
        System.out.println("Please enter a card that follows the suit or rank of the lead card.");
        //System.out.printf("Valid Rank: ", deck.validRanks());
        //System.out.printf("Valid Suit: ", deck.validSuits());
        return false;
    }

    public static Card getUserCard(String user_input){
        String user_suit = String.valueOf(user_input.toString().charAt(0));
        String user_rank = String.valueOf(user_input.toString().charAt(1));
        return new Card(user_suit, user_rank);
    }

    public static void resetCenterCard(){
        //  NEW TRICK WILL HAVE NEW CENTER
        TRICK_COUNTER += 1; 
        center_cards.clear();
        current_center_card_index = 0; 
    }

    public static int getWinner(){
        int winner_index = 0; 
        int winner_rank_score = 0; 
        for (Player player : players) {
            if (player.getMaxRank(getCurrentCenterCard()) > winner_rank_score){
                winner_index = player.getPlayerNum()-1;
                winner_rank_score = player.getMaxRank(getCurrentCenterCard());
            }
        }

        System.out.printf("\n*** Player%d wins Trick #%d ***\n", winner_index+1, TRICK_COUNTER);
        System.out.printf("GAME STATS\n"); 
        //gamePrinter();
        // printScore();

        System.out.printf("\n******************************\n");
        return winner_index;
    }

    // public static int whoGoNext(){
    //     int max_index = 0;
    //     int max_rank = 0; 
    //     for (Player player : players) {
    //         if(player.getMaxRank() > max_rank){
    //             max_index = player.getPlayerNum()-1;
    //             max_rank = Math.max(max_rank, player.getMaxRank());
    //         }
    //     }
    //     return max_index; 
    // }

    public static void main(String[] args){
        // Initalize game 
        initGame();

        // Game  Start
        while(true){
            System.out.printf("\n\n\n=== TRICK %d ===\n", TRICK_COUNTER); 
            // 1 trick logic 
            for (int player_turn = 0; player_turn < TOTAL_PLAYERS;) {

                player_index = player_index % TOTAL_PLAYERS; 
                gamePrinter(); 
                Scanner input_scanner = new Scanner(System.in);
                System.out.print("\nUser Input>");
                String user_input = input_scanner.nextLine();
                System.out.println("User Input is: " + user_input);  // Output user input
                
                if (user_input.equals("s")){
                    System.out.println("Start a new game.");
                }
                else if (user_input.equals("x")){
                    System.out.println("Exit the game.");
                }
                else if (user_input.equals("d")){
                    System.out.println("Draw cards from deck until a playable card is obtained. If the deck is empty, skip to the next player.");
                    Card new_card = deck.getFirstCard();
                    players.get(player_index).addCard(new_card);
                    if(deck.length()==0){
                        player_index += 1;       //Give next player 
                        player_turn += 1;        // Lost a turn when a deck is empty
                    }
                }
                else if (isValidCard(user_input, players.get(player_index))){
                    System.out.println("Player Submit Card");
                    Card user_card = getUserCard(user_input); 
                    Card submitted_card = players.get(player_index).submitCard(user_card);
                    center_cards.add(submitted_card);
                    player_index += 1;       //Give next player
                    player_turn += 1;       // Turn neded

                }
            }

            // When 1 trick ends, calculate who wins for this trick
            
            calculateScore();
            player_index = getWinner();
            resetCenterCard();
            
        }
    }
}
