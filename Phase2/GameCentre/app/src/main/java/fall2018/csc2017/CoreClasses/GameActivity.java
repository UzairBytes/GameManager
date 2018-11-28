package fall2018.csc2017.CoreClasses;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Sliding.SlidingBoard;
import Sliding.SlidingBoardManager;
import phase1.Savable;

public abstract class GameActivity extends AppCompatActivity {

    /**
     * The board manager.
     */
    BoardManager boardManager; //TODO make private and add getter method

    // Grid View and calculated column height and width based on device size
    //GestureDetectGridView gridView; //TODO make private and add getter method


    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        saveToFile(StartingActivity.TEMP_SAVE_FILENAME); //TODO Use Savable class.
    }

    /**
     * Returns boardmanager
     *
     * @Return boardmanager
     */
    public BoardManager getBoardManager(){
        return boardManager;
    }

    /**
     * set boardmanager
     */
    public void setBoardManager(BoardManager newBoardManager){
        this.boardManager = newBoardManager;
    }

    /**
     * Load the board manager from the fileName in StartingActivity.
     */
    protected void loadFromFile() {

        try {
            InputStream inputStream = this.openFileInput(StartingActivity.TEMP_SAVE_FILENAME);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                boardManager = (BoardManager) input.readObject();
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
    }

    /**
     * Save the board manager to fileName.
     *
     * @param fileName the name of the file
     */
    public void saveToFile(String fileName) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(
                    this.openFileOutput(fileName, MODE_PRIVATE));
            outputStream.writeObject(boardManager);
            outputStream.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * Override the functionality of the back button
     */
    @Override
    public void onBackPressed(){
        Intent startingActivity = new Intent(this, StartingActivity.class);
        startActivity(startingActivity);
    }
}
