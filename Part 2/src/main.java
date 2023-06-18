public class main {
    public static void main(String[] args){

        GameLogic gameLogic = new GameLogic(); 

        while(GameLogic.continueRound(GameLogic.END_SCORE) && Round.getToExit() == false){
            gameLogic.createRound();
            gameLogic.startRound();
            gameLogic.endRound();
            
        }
    }
}
