package Checkers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CheckersTileTest {

    @Test
    public void testHighlightDehighlightTile(){
        CheckersTile redKing = new CheckersTile("red_king");
        CheckersTile emptyWhite = new CheckersTile(CheckersTile.EMPTYWHITETILE);
        CheckersTile redPawn = new CheckersTile("red_pawn");
        CheckersTile redPawnHighlighted = new CheckersTile("red_pawn_highlighted");

        redPawn.highlight();
        assertEquals("CheckersTile.highlight() failed", redPawn.getId(), redPawnHighlighted.getId());
        redPawn.dehighlight();
        assertEquals("CheckersTile.dehighlight() failed", new CheckersTile("red_pawn").getId(), redPawn.getId());

    }
}
