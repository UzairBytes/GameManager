package phase1;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

import Checkers.CheckersGameFile;
import Twenty.TwentyGameFile;

/**
 * Account class manages user accounts.
 */
public class Account implements Serializable {

    /**
     * This Account's username
     */
    private String username;

    /**
     * This Account's password
     */
    private String password;

    /**
     * A 2-D hashmap of GameFiles that belong to this Account. Each entry is a type of game,
     * and each hashmap inside contains the game files for said game.
     */
    private HashMap<String, HashMap<String, GameFile>> accountGameData;

    /**
     * The current gameFile being played by this Account.
     */
    private GameFile activeGameFile;

    /**
     * The main save file where the <games> map will be saved, dependent on this account's username.
     */
    private String saveFileName;

    /**
     * Name of the game that is currently active in this account.
     */
    private String activeGameName = "";

    public Account(String user, String pass) {
        this.username = user;
        this.password = pass;
        this.accountGameData = new HashMap<>();
        this.initializeGameFiles();
        // Set the 'save' file name based off of this username.
        this.saveFileName = "/" + this.username + ".ser";
        this.saveAccountGameData();
    }

    /**
     * Initializes the <accountGameData> attribute in this class.
     */
    private void initializeGameFiles(){
        HashMap<String, GameFile> slidingMap = new HashMap<String, GameFile>();
        HashMap<String, GameFile> twentyMap = new HashMap<String, GameFile>();
        HashMap<String, GameFile> checkersMap = new HashMap<String, GameFile>();
        this.accountGameData.put(Game.SLIDING_NAME, slidingMap);
        this.accountGameData.put(Game.TWENTY_NAME, twentyMap);
        this.accountGameData.put(Game.CHECKERS_NAME, checkersMap);
    }


    /**
     * Getter for username
     *
     * @return this account's username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Getter for password
     *
     * @return this account's password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Returns hashmap that contains all the account's games and corresponding gamefiles.
     *
     * @return said hashmap
     */
    public HashMap<String, GameFile> getGames(String gameType) {
        System.out.println("ran!");
        this.loadAccountGameData();
        return this.accountGameData.get(gameType);
    }


    /**
     * Inserts a gameFile in the current game's map of gameFiles.
     * @param gameFile: The gameFile to be inserted.
     */
    public void addGameFile(GameFile gameFile) {
        HashMap<String, GameFile> gameFiles = this.accountGameData.get(this.activeGameName);
        gameFiles.put(gameFile.getName(), gameFile);
        this.activeGameFile = gameFile;
    }

    public String getActiveGameName(){
        return this.activeGameName;
    }

    public void setActiveGameName(String activeGameName){
        this.activeGameName = activeGameName;
    }

    /**
     * Overwrite and save the <games> hashmap in a serializable file.
     */
    public void saveAccountGameData() {
        try {
            String path = AccountManager.contextPath;
            File file = new File(path + this.saveFileName);
            System.out.println("path:" + path + this.saveFileName);
            FileOutputStream output = new FileOutputStream(file);
            ObjectOutputStream outputStream = new ObjectOutputStream(output);
            outputStream.writeObject(this.accountGameData);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * Retrieve the <games> hashmap from the .ser file.
     */
    @SuppressWarnings("unchecked")
    public void loadAccountGameData() {
        try {
            String path = AccountManager.contextPath;
            File file = new File(path + this.saveFileName);
            System.out.println("path:" + path + this.saveFileName);
            FileInputStream input = new FileInputStream(file);
            ObjectInputStream inputStream = new ObjectInputStream(input);
            this.accountGameData = (HashMap<String, HashMap<String, GameFile>>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e1) {
            System.out.println(e1);
        }
    }

    /*
     * Getter for the active game file of this account.
     * @return the current active game file of this account.
     */
    public GameFile getActiveGameFile(){
        return this.activeGameFile;
    }


    /*
     * Setter for the active game file of this account.
     * @param the current active game file for this account.
     */
    public void setActiveGameFile(GameFile gameFile){
        this.activeGameFile = gameFile;
    }


}
