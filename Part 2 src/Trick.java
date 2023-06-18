import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Scanner;

public class Trick{
    public static int TRICK_NUMBER = 0; 
    public static ArrayList<Card> center_cards = new ArrayList<Card>(); 
    public static HashMap<String, Integer> who_go_first = new HashMap<>();
    public static int player_index; 
    public static int current_center_card_index = 0; 

    public Trick(int trick_number){
        this.TRICK_NUMBER = trick_number; 
    }

    public int getTrickNumber(){
        return TRICK_NUMBER;
    }

    // to see who go first
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

    public static Card getCurrentCenterCard(){
        return center_cards.get(current_center_card_index);
    }

    public void resetCenterCard(){
        //  NEW TRICK WILL HAVE NEW CENTER
        TRICK_NUMBER += 1; 
        center_cards.clear();
        current_center_card_index = 0; 
    }

    //converting user input into a Card object
    public static Card formatUserCard(String user_input){
        String user_suit = String.valueOf(user_input.toString().charAt(0));
        String user_rank = String.valueOf(user_input.toString().charAt(1));
        return new Card(user_suit, user_rank);
    }

    
    public int getTrickWinner(){
        int winner_index = 0; 
        int winner_rank_score = 0; 
        for (Player player : GameLogic.players) {
            if (player.getMaxRank(getCurrentCenterCard()) > winner_rank_score){
                winner_index = player.getPlayerNum()-1;
                winner_rank_score = player.getMaxRank(getCurrentCenterCard());
            }
        }
        System.out.printf("\n****************************************\n");

        System.out.printf("\n        Player %d wins Trick #%d        \n", winner_index+1, TRICK_NUMBER+1);
        
        /*
        Just for display and check my output
        System.out.printf("GAME STATS\n"); 
        GameLogic.gamePrinter();
        GameLogic.printScore();
        */

        System.out.printf("\n****************************************\n");

        while(true){
            Scanner input_scanner = new Scanner(System.in);
            System.out.print("\n\nEnter your existing card to be made as the center card > ");
            String user_input = input_scanner.nextLine();
            // input_scanner.close();

            String status = Trick.isValidCard(user_input,  GameLogic.players.get(winner_index)); 
            if (status.equals("Valid")){
                resetCenterCard();
                
                GameLogic.PLAYER_TURN=0;
                Card user_card = Trick.formatUserCard(user_input); 
                GameLogic.players.get(winner_index).removeCard(user_card); 
                center_cards.add(user_card);
                GameLogic.PLAYER_TURN += 1;     // The winner lost a turn
                return (winner_index + 1) % GameLogic.TOTAL_PLAYERS;
            }
        }
    }

    //validates whether a user's input for a card is in the correct format, if the rank and suit are valid, and if the current player has the card in their hand
    public static String isValidCard(String user_input, Player current_player){
        if(user_input.length() != 2){
            return "Re-enter";
        }
        String user_suit = String.valueOf(user_input.toString().charAt(0));
        String user_rank = String.valueOf(user_input.toString().charAt(1));

        // Check is the card valid
        if (GameLogic.deck.isRankValid(user_rank) && GameLogic.deck.isSuitValid(user_suit)){
            Card user_card = Trick.formatUserCard(user_input); 

            if(current_player.hasValidCard(user_card)){
                return "Valid";        // Valid rank, Valid Suit and User has the card
            }
            else{
                System.out.println("You have a valid card but you didnt use the card. ");
                return "Re-enter";
            } 
        }

        System.out.printf("User does not entered the right card format -> (%s)\n", user_suit+user_rank);
        System.out.println("Please enter a card that follows the suit or rank of the lead card.");
        return "Re-enter";
    }


    // initGame -> initTrick
    //determine who go first for the trick, add deck's first card to center, distribute 7 cards for each player
    public static void initTrick(){
        initWhoGoFirst();
        center_cards.add(GameLogic.deck.getFirstCard());
        // Distribute Card
        for (int card_index = 0; card_index < GameLogic.NUMBER_OF_CARDS; card_index++) {
            for (int player_index = 0; player_index < GameLogic.TOTAL_PLAYERS; player_index++) {
                GameLogic.players.get(player_index).addCard(GameLogic.deck.getFirstCard()); 
            }
        }

        player_index = who_go_first.get(center_cards.get(0).getRank());
    }

    public void endTrick(){

        // If there is any players who doesn't have card, dont continue the trick
        if(!GameLogic.checkPlayersCardInHand()){
            return;
        }
        player_index = getTrickWinner();
    }
}
