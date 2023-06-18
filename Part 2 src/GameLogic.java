import java.util.ArrayList;

public class GameLogic {
    private int CURRENT_ROUND = 0; 
    private ArrayList<Round> rounds = new ArrayList<Round>();

    public static int TOTAL_PLAYERS = 4;
    public static int NUMBER_OF_CARDS = 7; 
    public static ArrayList<Player> players = new ArrayList<Player>();; 
    public static Deck deck; 
    public static int END_SCORE = 100; 
    public static int PLAYER_TURN = 0; 

    public GameLogic(){
        createPlayer();
    }

    public static void resetDeck(){
        GameLogic.deck = new Deck();
    }    

    public void createPlayer(){
        // Create player
        for (int player_index = 0; player_index < TOTAL_PLAYERS; player_index++) {
            players.add(player_index, new Player(player_index+1));
        }
    }


    public void start(){
        while(continueRound()){
            createRound();
            startRound();
            endRound();
        }
    }
    
    public void createRound(){
        Boolean ToReset = false;
        System.out.printf("\n\nRound %d Started", CURRENT_ROUND+1);
        Round current_round = new Round(CURRENT_ROUND);
        rounds.add(current_round);
    }

    public void startRound(){
        if(Round.getToExit()){
            return;
        }
        else{
        rounds.get(CURRENT_ROUND).start();}
    }

    public void endRound(){
        if(Round.getToExit() || Round.getToReset()){
            CURRENT_ROUND = 0;
            resetScore();
            return;
        }
        else{
        System.out.printf("\n****************************************\n");
        System.out.printf("\n             Round %d Ended             \n", CURRENT_ROUND+1);
        CURRENT_ROUND += 1;
        PLAYER_TURN = 0; // Revert back to all player have turn
        calculateScore();
        
        /*
        System.out.printf("GAME Round STATS\n"); 
        GameLogic.gamePrinter();
        GameLogic.printScore();
        */

        System.out.printf("\n****************************************\n");
        }
    }

    public static String isValidCard(String user_input, Player current_player){
        if(user_input.length() != 2){
            return "Re-enter";
        }
        String user_suit = String.valueOf(user_input.toString().charAt(0));
        String user_rank = String.valueOf(user_input.toString().charAt(1));

        // Check is the card valid
        if (GameLogic.deck.isRankValid(user_rank) && GameLogic.deck.isSuitValid(user_suit)){
            Card user_card = Trick.formatUserCard(user_input); 

            

            Card current_center_card = Trick.getCurrentCenterCard();

            // User doesnt have the center card
            if(current_player.hasCenterCard(current_center_card)){
                if(current_player.hasValidCard(user_card, current_center_card)){
                    return "Valid";        // Valid rank, Valid Suit and User has the card
                }
                else{
                    System.out.println("You have a valid card but you didnt use the card. ");
                    return "Re-enter";
                }
            }
            else{
                return "Lost-a-turn";
            }

            
        }
        System.out.printf("User does not entered the right card format (%s)\n", user_suit+user_rank);
        System.out.println("Please enter a card that follows the suit or rank of the lead card.");
        //System.out.printf("Valid Rank: ", deck.validRanks());
        //System.out.printf("Valid Suit: ", deck.validSuits());
        return "Re-enter";
    }

    
    public static boolean continueTrick(int ROUND_NUMBER){
        
        // Let it run the first time
        if(ROUND_NUMBER == 0 && Trick.TRICK_NUMBER == 0){
            return true; 
        }
        

        // If there is any players who doesn't have card, dont continue the trick
        if(checkPlayersCardInHand()){
            return true;
        }

        if(Round.getToExit()){
            return false;
        }
        else{
            return false; 
        }

    }

    public static boolean checkPlayersCardInHand(){
        boolean to_continue_trick = true; 
        for (Player player : players) {
            if (player.hasCardInHand() == false){
                to_continue_trick = false; 
                return to_continue_trick;
            }
        }
        return to_continue_trick; 
    }

    public void calculateScore(){
        for (Player player : players) {
            for (Card card : player.getCardsInHand()) {
                player.addScore(card.getCardScore());
            }
        }
    }

    public void resetScore(){
        for (Player player : players) {
            player.resetScore();
        }
    }
    
    public boolean continueRound(){
        boolean to_continue_round = true; 
        for (Player player : players) {
            if (player.getScore() >= 100){
                to_continue_round = false;
            }
        }
        return to_continue_round;
    }
    
    // ********
    // PRINTER
    // ********
    public static void printPlayerCards(){
        for (int player_index = 0; player_index < TOTAL_PLAYERS; player_index++) {
            players.get(player_index).showCards();
            // players.get(player_index).showPlayedCards();
        }
    }

    public static void printDeck(){
        deck.printDeck();
    }

    public static void printScore() {
        System.out.print("Score: ");
        for (Player player : players) {
            System.out.printf("Player%d = %d | ", player.getPlayerNum(), player.getScore());
        }
        System.out.println("");
    }

    public static void printCenter(){
        System.out.printf("Center: %s \n", Trick.center_cards.toString());
    }

    public static void gamePrinter(){
        System.out.printf("\n\nTRICK #%d \n", Trick.TRICK_NUMBER+1); 
        printPlayerCards();
        printCenter();
        printDeck();
        printScore();
        System.out.printf("Turn: Player%d \n", Trick.player_index+1);
        // System.out.printf("Length of Deck: %d \n", deck.length());
        // System.out.printf("Start index: %d, Current Player %d \n ", player_index, player_index+1);
    }

    public static boolean continueRound(int score){
        for (Player player : players) {
            if(player.getScore() >= score ) {
                return false; 
            }
        }
        return true;
    }

    public static Player getRoundWinner(){
        int min_score = GameLogic.END_SCORE*2; 
        int min_id = -1; 
        for (Player player : players) {
            if(min_score<player.getScore()){
                min_score = Math.min(min_score, player.getScore()); 
                min_id = player.getPlayerNum()-1;
            }
        }

        return players.get(min_id);
    }
}
