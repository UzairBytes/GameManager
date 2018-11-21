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
     * A hashmap of GameFiles that belong to this Account
     */
    private HashMap<String, GameFile> games;

    /**
     * The current gameFile being played by this Account.
     */
    public GameFile activeGameFile;

    /**
     * The main save file where the <games> map will be saved, dependent on this account's username.
     */
    private String saveFileName;

    public Account(String user, String pass) {
        this.username = user;
        this.password = pass;
        this.games = new HashMap<>();
        // Set the 'save' file name based off of this username.
        this.saveFileName = "/" + this.username + ".ser";
        this.saveAllGameFiles();
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
    public HashMap<String, GameFile> getGames() {
        System.out.println("ran!");
        this.loadGameFiles();
        return this.games;
    }


    /**
     * Adds a SlidingGameFile to the <games> hashmap, and re-serializes the map.
     */
    public void addGameFile(SlidingGameFile gameFile) {
        this.games.put(gameFile.getName(), gameFile);
        this.activeGameFile = gameFile;
    }

    /**
     * Adds a CheckersgameFile to the <games> hashmap, and re-serializes the map.
     */
    public void addGameFile(CheckersGameFile gameFile) {
        this.games.put(gameFile.getName(), gameFile);
        this.activeGameFile = gameFile;
    }

    /**
     * Adds a TwentyGameFile to the <games> hashmap, and re-serializes the map.
     */
    public void addGameFile(TwentyGameFile gameFile) {
        this.games.put(gameFile.getName(), gameFile);
        this.activeGameFile = gameFile;
    }

    /**
     * Overwrite and save the <games> hashmap in a serializable file.
     */
    public void saveAllGameFiles() {
        try {
            String path = AccountManager.contextPath;
            File file = new File(path + this.saveFileName);
            System.out.println("path:" + path + this.saveFileName);
            FileOutputStream output = new FileOutputStream(file);
            ObjectOutputStream outputStream = new ObjectOutputStream(output);
            outputStream.writeObject(games);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * Retrieve the <games> hashmap from the .ser file.
     */
    @SuppressWarnings("unchecked")
    public void loadGameFiles() {
        try {
            String path = AccountManager.contextPath;
            File file = new File(path + this.saveFileName);
            System.out.println("path:" + path + this.saveFileName);
            FileInputStream input = new FileInputStream(file);
            ObjectInputStream inputStream = new ObjectInputStream(input);
            this.games = (HashMap<String, GameFile>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e1) {
            System.out.println(e1);
        }
    }


}
