package minesweeper;

import design.minesweeper.Minesweeper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static design.minesweeper.Minesweeper.CellStatus.*;
import static design.minesweeper.Minesweeper.GameStatus.*;
import static design.minesweeper.Minesweeper.CellStatus.UNEXPOSED;
import static org.junit.jupiter.api.Assertions.*;

public class MinesweeperTest {
    private Minesweeper minesweeper;

    @BeforeEach
    public void initialize() {
        minesweeper = new Minesweeper();
    }

    @Test
    void canary(){
        assertTrue(true);
    }

    @Test
    void initialStateOfCellIsUnexposed(){
        assertEquals(UNEXPOSED, minesweeper.getCellStatus(1, 2));
    }

    @Test
    void exposeAnUnexposedCell(){
        minesweeper.exposeCell(1, 2);

        assertEquals(EXPOSED, minesweeper.getCellStatus(1, 2));
    }

    @Test
    void exposeAnExposedCell(){
        minesweeper.exposeCell(1, 3);
        minesweeper.exposeCell(1, 3);

        assertEquals(EXPOSED, minesweeper.getCellStatus(1, 3));
    }

    @Test
    void exposeACellOutsideRange(){
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> minesweeper.exposeCell(1, 12));
    }

    @Test
    void sealAnUnexposedCell(){
        minesweeper.toggleSeal(1, 2);

        assertEquals(SEALED, minesweeper.getCellStatus(1, 2));
    }

    @Test
    void unsealASealedCell(){
        minesweeper.toggleSeal(2, 1);
        minesweeper.toggleSeal(2, 1);

        assertEquals(UNEXPOSED, minesweeper.getCellStatus(2, 1));
    }

    @Test
    void sealAnExposedCell(){
        minesweeper.exposeCell(2, 3);
        minesweeper.toggleSeal(2, 3);

        assertEquals(EXPOSED, minesweeper.getCellStatus(2, 3));
    }

    @Test
    void exposeASealedCell(){
        minesweeper.toggleSeal(2, 3);
        minesweeper.exposeCell(2, 3);

        assertEquals(SEALED, minesweeper.getCellStatus(2, 3));
    }

    @Test
    void exposeCallsExposeNeighbors(){
        AtomicBoolean called = new AtomicBoolean(false);

        Minesweeper minesweeper = new Minesweeper() {
            @Override
            public void exposeNeighborsOf(int row, int column) {
                assertEquals(1, row);
                assertEquals(3, column);
                called.set(true);
            }
        };

        minesweeper.exposeCell(1, 3);
        assertTrue(called.get());
    }

    @Test
    void exposeCalledOnAlreadyExposedCellDoesNotCallExposeNeighbors(){
        AtomicBoolean called = new AtomicBoolean(false);

        Minesweeper minesweeper = new Minesweeper() {
            @Override
            public void exposeNeighborsOf(int row, int column) {
                called.set(true);
            }
        };

        minesweeper.exposeCell(1, 3);
        called.set(false);

        minesweeper.exposeCell(1, 3);
        assertFalse(called.get());
    }

    @Test
    void exposingASealedCellDoesNotCallExposeNeighbors(){
        AtomicBoolean called = new AtomicBoolean(false);

        Minesweeper minesweeper = new Minesweeper() {
            @Override
            public void exposeNeighborsOf(int row, int column) {
                called.set(true);
            }
        };

        minesweeper.toggleSeal(1, 3);

        minesweeper.exposeCell(1, 3);
        assertFalse(called.get());
    }

    @Test
    void exposeNeighborsOfCallsExposeCellOnNeighbors(){
        StringBuilder neighbors = new StringBuilder();

        Minesweeper minesweeper = new Minesweeper() {
            @Override
            public void exposeCell(int row, int column) {
                neighbors.append(row + "," + column + ",");
            }
        };

        minesweeper.exposeNeighborsOf(1, 3);
        assertEquals("0,2,0,3,0,4,1,2,1,3,1,4,2,2,2,3,2,4,", neighbors.toString());
    }

    @Test
    void exposeNeighborsOfOnTopLeftCellCallsExposeCellOnNeighbors(){
        StringBuilder neighbors = new StringBuilder();

        Minesweeper minesweeper = new Minesweeper() {
            @Override
            public void exposeCell(int row, int column) {
                neighbors.append(row + "," + column + ",");
            }
        };

        minesweeper.exposeNeighborsOf(0, 0);
        assertEquals("0,0,0,1,1,0,1,1,", neighbors.toString());
    }

    @Test
    void exposeNeighborsOfOnBottomRightCellCallsExposeCellOnNeighbors(){
        StringBuilder neighbors = new StringBuilder();

        Minesweeper minesweeper = new Minesweeper() {
            @Override
            public void exposeCell(int row, int column) {
                neighbors.append(row + "," + column + ",");
            }
        };

        minesweeper.exposeNeighborsOf(9, 9);
        assertEquals("8,8,8,9,9,8,9,9,", neighbors.toString());
    }

    @Test
    void exposeNeighborsOfOnBorderCellCallsExposeCellOnNeighbors(){
        StringBuilder neighbors = new StringBuilder();

        Minesweeper minesweeper = new Minesweeper() {
            @Override
            public void exposeCell(int row, int column) {
                neighbors.append(row + "," + column + ",");
            }
        };

        minesweeper.exposeNeighborsOf(3, 0);
        assertEquals("2,0,2,1,3,0,3,1,4,0,4,1,", neighbors.toString());
    }

    @Test
    void noMineAtALocation(){
        assertFalse(minesweeper.isMineAt(3, 2));
    }

    @Test
    void setMineAndCheck(){
        minesweeper.setMineAt(3, 2);

        assertTrue(minesweeper.isMineAt(3, 2));
    }

    @Test
    void isMineAtOutOfBoundsReturnsFalse(){
        assertAll(
                () -> assertFalse(minesweeper.isMineAt(-1, 4)),
                () -> assertFalse(minesweeper.isMineAt(10, 4)),
                () -> assertFalse(minesweeper.isMineAt(5, -4)),
                () -> assertFalse(minesweeper.isMineAt(1, 14))
        );
    }

    @Test
    void exposingAnAdjacentCellDoesNotCallExposeNeighbors(){
        AtomicBoolean called = new AtomicBoolean(false);

        Minesweeper minesweeper = new Minesweeper() {
            @Override
            public void exposeNeighborsOf(int row, int column) {
                called.set(true);
            }

            @Override
            public int adjacentMinesCountAt(int row, int column) {
                return 1;
            }
        };

        minesweeper.setMineAt(2, 3);
        minesweeper.exposeCell(2, 4);
        assertFalse(called.get());
    }

    @Test
    void adjacentMinesCountAtIs0(){
        assertEquals(0, minesweeper.adjacentMinesCountAt(4, 3));
    }

    @Test
    void setMineAndCheckAdjacentMinesCountIs0(){
        minesweeper.setMineAt(3, 4);

        assertEquals(0, minesweeper.adjacentMinesCountAt(3, 4));
    }

    @Test
    void setMineAndCheckAdjacentMinesCountIs1(){
        minesweeper.setMineAt(3, 4);

        assertEquals(1, minesweeper.adjacentMinesCountAt(3, 5));
    }

    @Test
    void setMineAndCheckAdjacentMinesCountIs2(){
        minesweeper.setMineAt(3, 4);
        minesweeper.setMineAt(2, 6);

        assertEquals(2, minesweeper.adjacentMinesCountAt(3, 5));
    }

    @Test
    void setMineAndCheckAdjacentMinesCountNearBorder(){
        minesweeper.setMineAt(0, 1);

        assertEquals(1, minesweeper.adjacentMinesCountAt(0, 0));
    }

    @Test
    void checkAdjacentMinesCountNearBorder(){
        assertEquals(0, minesweeper.adjacentMinesCountAt(0, 9));
    }

    @Test
    void setMineAndCheckAdjacentMinesCountNearBottomBorder(){
        minesweeper.setMineAt(9, 8);

        assertEquals(1, minesweeper.adjacentMinesCountAt(9, 9));
    }

    @Test
    void checkAdjacentMinesCountNearBottomBorder(){
        assertEquals(0, minesweeper.adjacentMinesCountAt(9, 0));
    }

    @Test
    void checkGameStatusAtTheStart(){
        minesweeper.setMineAt(1, 2);

        assertEquals(INPROGRESS, minesweeper.getGameStatus());
    }

    @Test
    void gameLostWhenMineExposed(){
        minesweeper.setMineAt(2, 3);
        minesweeper.exposeCell(2, 3);

        assertEquals(LOST, minesweeper.getGameStatus());
    }

    @Test
    void gameInProgressWhenAllMinesSealedButCellsRemainUnexposed(){
        for (int i = 0; i < 10; i++) {
            minesweeper.setMineAt(i, i);
            minesweeper.toggleSeal(i, i);
        }

        assertEquals(INPROGRESS, minesweeper.getGameStatus());
    }

    @Test
    void gameInProgressWhenAllMinesSealedButAnEmptyCellIsSealed(){
        minesweeper.toggleSeal(2, 4);

        for (int i = 0; i < 10; i++) {
            minesweeper.setMineAt(i, i);
            minesweeper.toggleSeal(i, i);
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(i != j) {
                    minesweeper.exposeCell(i, j);
                }
            }
        }

        assertEquals(INPROGRESS, minesweeper.getGameStatus());
    }

    @Test
    void gameInProgressWhenAllMinesSealedButAnAdjacentCellIsUnExposed(){
        for (int i = 0; i < 10; i++) {
            minesweeper.setMineAt(i, i);
            minesweeper.toggleSeal(i, i);
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(i != j) {
                    if(!(i == 0 && j == 1)) {
                        minesweeper.exposeCell(i, j);
                    }
                }
            }
        }

        assertEquals(INPROGRESS, minesweeper.getGameStatus());
    }

    @Test
    void gameWonWhenAllMinesAreSealedAndAllOtherCellsExposed(){
        for (int i = 0; i < 10; i++) {
            minesweeper.setMineAt(i, i);
            minesweeper.toggleSeal(i, i);
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                minesweeper.exposeCell(i, j);
            }
        }

        assertEquals(WON, minesweeper.getGameStatus());
    }

    @Test
    void setMinesWithASeedAndVerify10MinesSet(){
        minesweeper.setMines(0);

        int count = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(minesweeper.isMineAt(i, j)) {
                    count++;
                }
            }
        }

        assertEquals(10, count);
    }

    @Test
    void setMinesWithTwoDifferentSeedAndVerifyMinesLocationsAreNotTheSame(){
        Minesweeper minesweeper1 = new Minesweeper();
        Minesweeper minesweeper2 = new Minesweeper();

        minesweeper1.setMines(1);
        minesweeper2.setMines(2);

        boolean allMinesAtTheSameLocations = true;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(minesweeper1.isMineAt(i, j) && !minesweeper2.isMineAt(i, j)) {
                    allMinesAtTheSameLocations = false;
                }
            }
        }

        assertFalse(allMinesAtTheSameLocations);
    }
}

