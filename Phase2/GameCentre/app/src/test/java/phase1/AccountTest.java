package phase1;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class AccountTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getUsername() {
        /* Should return the username used to initialize the Account object. */
        Account testAccount = new Account("username","password");
        assertEquals("Account.getUsername() Test 1 failed!","username", testAccount.getUsername());
    }

    @Test
    public void getPassword() {
        /* Should return the password used to initialize the Account object. */
        Account testAccount = new Account("username","password");
        assertEquals("Account.getPassword() Test 1 failed!", "password",testAccount.getPassword());
    }

    @Test
    public void getGames() {

    }

    @Test
    public void addGameFile() {
    }

    @Test
    public void getActiveGameName() {
        /* Should return the ActiveGameName initialized in the Account object */
        Account testAccount = new Account("username","password");
        assertEquals("Account.getActiveGameName() Test 1","",testAccount.getActiveGameName());
    }

    @Test
    public void setActiveGameName() {
        /* Should return the ActiveGameName initialized in the Account object and then altered by the setActiveGameName method */
        Account testAccount = new Account("username","password");
        testAccount.setActiveGameName("TestGameName");
        assertEquals("Account.setActiveGameName() Test 1","",testAccount.getActiveGameName());
    }

    @Test
    public void saveAccountGameData() {
    }

    @Test
    public void loadAccountGameData() {
    }

    @Test
    public void getActiveGameFile() {

    }

    @Test
    public void setActiveGameFile() {
    }

    @Test
    public void getLeaderBoard() {
        /* Should return the new leaderboard initialized by the Account object. */
        Account testAccount = new Account("username","password");
        assertEquals("Account.getLeaderboard() Test 1 failed",new LeaderBoard(),testAccount.getLeaderBoard());
    }
}