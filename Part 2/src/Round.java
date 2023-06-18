import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Round implements IRound {
    private int ROUND_NUMBER;
    private int CURRENT_TRICK = 0;
    private ArrayList<Trick> tricks = new ArrayList<Trick>();
    private static boolean toExit = false;
    private static boolean toReset = false;

    public Round(int round_number) {
        this.ROUND_NUMBER = round_number;
    }

    public static boolean getToExit() {
        return toExit;
    }

    public static boolean getToReset() {
        return toReset;
    }

    public void start() {
        GameLogic.resetDeck();
        Trick.center_cards.clear();
        Trick.current_center_card_index = 0;
        toReset = false;
        for (Player player : GameLogic.players) {
            player.clearCardInHand();
            player.clearCardPlayed();
        }

        Trick.initTrick();
        while (GameLogic.continueTrick(ROUND_NUMBER) && toExit == false && toReset == false) {
            createTrick();
            startTrick();
            endTrick();
        }
    }

    public void createTrick() {
        System.out.printf("\nTrick %d Started", CURRENT_TRICK + 1);
        Trick current_trick = new Trick(CURRENT_TRICK);
        tricks.add(current_trick);
    }

    public void startTrick() {
        Trick current_trick = tricks.get(CURRENT_TRICK);
        System.out.printf("\n\n\n============ TRICK %d ============\n", current_trick.TRICK_NUMBER + 1);

        // 1 trick logic
        for (int player_turn = GameLogic.PLAYER_TURN; player_turn < GameLogic.TOTAL_PLAYERS;) {

            Trick.player_index = Trick.player_index % GameLogic.TOTAL_PLAYERS;
            GameLogic.gamePrinter();
            Scanner input_scanner = new Scanner(System.in);
            System.out.print("\nUser Input > ");
            String user_input = input_scanner.nextLine();
            // input_scanner.close();

            if (user_input.equals("Reset")) {
                System.out.println("Reset and Start a new game."); // don't have start new game function yet
                toReset = true;
                ROUND_NUMBER = 0;
                CURRENT_TRICK = 0;
                break;

            } else if (user_input.equals("Exit")) {
                System.out.println("Exit the game.");
                toExit = true;
                break;

            } else if (user_input.equals("Save")) {
                System.out.println("Saving the game.");
                try {
                    PrintWriter pw = new PrintWriter(new FileWriter("TestGame.txt", false));
                    Deck d1 = new Deck();
                    ArrayList<Card> a1 = new ArrayList<>();
                    ArrayList<String> sb = new ArrayList<>();
                    StringBuffer sb2 = new StringBuffer();

                    a1 = d1.getDeck();
                    for (Card c : a1) {
                        sb2.append(c.toString());
                        sb2.append("\t");

                    }

                    String s1 = sb2.toString().replaceAll("[()]", "");
                    ArrayList<Card> a2 = new ArrayList<>();
                    pw.println("Round:" + (ROUND_NUMBER + 1));
                    pw.println("Trick:" + (current_trick.TRICK_NUMBER + 1));
                    for (Player player : GameLogic.players) {

                        Player p1 = new Player();

                        p1 = player;

                        pw.println("P" + p1.getPlayerNum() + ":" + "\t" + p1.getCardsInHand());
                        for (int i = 0; i < a1.size(); i++) {
                            // System.out.println(a1.get(i)+ "\t" + p1.getCardsInHand().get(0));
                            // System.out.println(p1.getCardsInHand().get(0));
                            // for (Card card : p1.getCardsInHand()) {
                            // if(card.toString().equals(a1.get(i).toString())){
                            // a1.remove(i);
                            // }
                            // }

                        }

                    }
                    pw.println("Center:" + Trick.center_cards);
                    List<Deck> arrayList = new ArrayList<>();
                    // for(Card card : GameLogic.deck.getDeck())
                    // arrayList.add(GameLogic.deck);
                    pw.println("Deck:" + GameLogic.deck.getDeck());
                    pw.println("Score:" + "\t" + GameLogic.players.get(0).getScore() + "\t"
                            + GameLogic.players.get(1).getScore() + "\t" + GameLogic.players.get(2).getScore() + "\t"
                            + GameLogic.players.get(3).getScore());
                    pw.println("Player:" + (Trick.player_index + 1));
                    pw.println("TurnNo:" + (player_turn + 1));
                    pw.close();

                } catch (IOException e) {

                }

            }

            else if (user_input.equals("Load")) {
                System.out.println("Loading the game.");
                ArrayList<String> allresults = new ArrayList<>();

                try {

                    BufferedReader BR = new BufferedReader(new FileReader("TestGame.txt"));
                    String result;
                    while ((result = BR.readLine()) != null) {
                        allresults.add(result);
                        for (String i : allresults) {
                            String[] data = i.split(":");
                            ArrayList<Card> cardList = new ArrayList<>();
                            StringBuffer sb3 = new StringBuffer();
                            switch (data[0]) {
                                case "Round":
                                    ROUND_NUMBER = Integer.parseInt(data[1]) - 1;
                                    break;

                                case "Trick":
                                    current_trick.TRICK_NUMBER = Integer.parseInt(data[1]) - 1;
                                    break;

                                case "P1":
                                    cardList.clear();
                                    sb3.setLength(0);

                                    for (String s : data[1].split(",")) {
                                        sb3.append(s);
                                        String s1 = sb3.toString().replaceAll("\\[|\\]", "");

                                        String suit = s1.substring(0, 1);
                                        String rank = s1.substring(1).trim();
                                        Card card = new Card(suit.trim(), rank.trim());
                                        cardList.add(card);
                                        sb3.setLength(0);
                                    }

                                    if (cardList.size() > 0) {
                                        GameLogic.players.get(0).setCards(cardList);
                                    }
                                    break;

                                case "P2":
                                    cardList.clear();
                                    sb3.setLength(0);

                                    for (String s : data[1].split(",")) {
                                        sb3.append(s);
                                        String s1 = sb3.toString().replaceAll("\\[|\\]", "");

                                        String suit = s1.substring(0, 1);
                                        String rank = s1.substring(1).trim();
                                        Card card = new Card(suit.trim(), rank.trim());
                                        cardList.add(card);

                                        sb3.setLength(0);
                                    }

                                    if (cardList.size() > 0) {
                                        GameLogic.players.get(1).setCards(cardList);
                                    }
                                    break;

                                case "P3":
                                    cardList.clear();
                                    sb3.setLength(0);

                                    for (String s : data[1].split(",")) {
                                        sb3.append(s);
                                        String s1 = sb3.toString().replaceAll("\\[|\\]", "");

                                        String suit = s1.substring(0, 1);
                                        String rank = s1.substring(1).trim();
                                        Card card = new Card(suit.trim(), rank.trim());
                                        cardList.add(card);

                                        sb3.setLength(0);
                                    }

                                    if (cardList.size() > 0) {
                                        GameLogic.players.get(2).setCards(cardList);
                                    }
                                    break;

                                case "P4":
                                    cardList.clear();
                                    sb3.setLength(0);

                                    for (String s : data[1].split(",")) {
                                        sb3.append(s);
                                        String s1 = sb3.toString().replaceAll("\\[|\\]", "");

                                        String suit = s1.substring(0, 1);
                                        String rank = s1.substring(1).trim();
                                        Card card = new Card(suit.trim(), rank.trim());
                                        cardList.add(card);

                                        sb3.setLength(0);
                                    }

                                    if (cardList.size() > 0) {
                                        GameLogic.players.get(3).setCards(cardList);
                                    }
                                    break;

                                case "Center":
                                    for (String s : data[1].split(",")) {
                                        sb3.append(s);
                                        String s1 = sb3.toString().replaceAll("\\[|\\]", "");
                                        Card card = new Card(s1.split("")[0], s1.split("")[1]);
                                        cardList.add(card);
                                    }
                                    if (cardList.size() > 0) {
                                        Trick.center_cards = cardList;
                                    }
                                    break;

                                case "Deck":
                                    cardList.clear();
                                    sb3.setLength(0);

                                    for (String s : data[1].split(",")) {
                                        sb3.append(s);
                                        String s1 = sb3.toString().replaceAll("\\[|\\]", "");

                                        String suit = s1.substring(0, 1);
                                        String rank = s1.substring(1).trim();
                                        Card card = new Card(suit.trim(), rank.trim());
                                        cardList.add(card);

                                        sb3.setLength(0);
                                    }

                                    if (cardList.size() > 0) {
                                        GameLogic.deck.setDeck(cardList);
                                    }
                                    break;

                                case "Score":
                                    String[] array1 = data[1].split("\t");
                                    GameLogic.players.get(0).setScore(Integer.parseInt(array1[1]));
                                    GameLogic.players.get(1).setScore(Integer.parseInt(array1[2]));
                                    GameLogic.players.get(2).setScore(Integer.parseInt(array1[3]));
                                    GameLogic.players.get(3).setScore(Integer.parseInt(array1[4]));
                                    break;

                                case "Player":
                                    Trick.player_index = Integer.parseInt(data[1]) - 1;
                                    break;

                                case "TurnNo":
                                    player_turn = Integer.parseInt(data[1]) - 1;
                                    break;
                            }
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (user_input.equals("d")) {
                System.out.println(
                        "Draw cards from deck until a playable card is obtained. If the deck is empty, skip to the next player."); 
                if (GameLogic.deck.hasCards()) {
                    Card new_card = GameLogic.deck.getFirstCard();
                    GameLogic.players.get(Trick.player_index).addCard(new_card);
                } else {
                    System.out.printf("Deck Has No More Cards. Player %d lost a turn", Trick.player_index + 1);
                    if(Trick.player_index == 3){
                        Trick.player_index=0;
                        player_turn += 1;

                    }else{
                    Trick.player_index += 1; // Give next player
                    player_turn += 1; // Lost a turn when a deck is empty
                    }
                }
            }

            String status = GameLogic.isValidCard(user_input, GameLogic.players.get(Trick.player_index));
            if (status.equals("Valid")) {
                Card user_card = Trick.formatUserCard(user_input);
                // If have card on hand, then play the card
                if (GameLogic.players.get(Trick.player_index).hasCardInHand()) {
                    Card submitted_card = GameLogic.players.get(Trick.player_index).submitCard(user_card);
                    Trick.center_cards.add(submitted_card);
                }
                Trick.player_index += 1; // Give next player
                player_turn += 1; // Turn ended
            } else if ((!GameLogic.deck.hasCards()) && status.equals("Lost-a-turn")) {
                System.out.println("Player Lost a turn because doesnt have a valid card to be played");
                Trick.player_index += 1; // Give next player
                player_turn += 1; // Turn ended
            }

            // If there is any players who doesn't have card, dont continue the trick
            if (!GameLogic.checkPlayersCardInHand()) {
                return;
            }
        }
    }

    public void endTrick() {
        // When 1 trick ends, calculate who wins for this trick
        if (toExit || toReset) {

            return;
        } else {
            Trick current_trick = tricks.get(CURRENT_TRICK);
            current_trick.endTrick();
            CURRENT_TRICK += 1;
        }
    }

}
