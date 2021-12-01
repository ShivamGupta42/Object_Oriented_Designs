package minesweeper;

import designs.minesweeper.Minesweeper2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MinesSweeperTest2 {


    @Test
    public void canary(){
        Assertions.assertTrue(true);
    }

    @Test
    public void gridShouldBeSetupOnBeginningOfGame(){
        Minesweeper2 minesweeper2 = new Minesweeper2();
        Assertions.assertNotNull(minesweeper2.getGrid());
    }

    @Test
    public void exposeAValidCell(){
        Minesweeper2 minesweeper2 = new Minesweeper2();
        minesweeper2.exposeCell(1,2);
        Assertions.assertSame(Minesweeper2.CellStatus.EXPOSED,minesweeper2.getGrid()[1][2]);
    }
}
