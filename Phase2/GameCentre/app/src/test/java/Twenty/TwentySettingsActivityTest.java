package Twenty;

import org.junit.Before;

import fall2018.csc2017.CoreClasses.Account;
import fall2018.csc2017.CoreClasses.AccountManager;
import fall2018.csc2017.CoreClasses.Game;

import static org.junit.Assert.assertEquals;

public class TwentySettingsActivityTest {

    //TODO: Test the updateUndos method, but first validate that it should be public.

    TwentySettingsActivity twentySettings = new TwentySettingsActivity();

    @Before
    public void setup(){
        AccountManager.activeAccount = new Account("twentytest", "1234");
        AccountManager.activeAccount.setActiveGameName(Game.TWENTY_NAME);
        TwentyBoardManager twentyBoardManager = new TwentyBoardManager(3);
        TwentyBoard twentyBoard = twentyBoardManager.twentyBoard;
    }



}
