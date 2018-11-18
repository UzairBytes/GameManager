package GameCentre;

import org.junit.Test;
import Checkers.CheckersTile;
import static org.junit.Assert.assertEquals;

public class CheckersTileTest {

    @Test
    public void testHighlightDehighlightTile(){
        CheckersTile redKing = new CheckersTile("red_king");
        CheckersTile emptyWhite = new CheckersTile("empty_white_tile");
        CheckersTile redPawn = new CheckersTile("red_pawn");
        CheckersTile redPawnHighlighted = new CheckersTile("red_pawn_highlighted");

        redPawn.highlight();
        assertEquals("CheckersTile.highlight() failed", redPawn.getId(), redPawnHighlighted.getId());
        redPawn.dehighlight();
        assertEquals("CheckersTile.dehighlight() failed", redPawn.getId(), new CheckersTile("red_pawn").getId());

    }
}
