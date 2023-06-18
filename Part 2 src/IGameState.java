public interface IGameState {
    public void saveToFile(String filename);
    public void loadToFile(String filename); 
    public void deleteFile(String filename);

}
