import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors

public class GameState extends File implements IGameState {

    public GameState(String pathname) {
        super(pathname);
        //TODO Auto-generated constructor stub
    }

    public void saveToFile(String filename){
        try {
            if (createNewFile()) {
                File myObj = new File("filename.txt");
                System.out.println("File created: " + getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void loadToFile(String filename){

    }
    
    public void deleteFile(String filename){

    }    
}