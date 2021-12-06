package chess_advanced.model;

import chess_advanced.helpers.TestHelpers;
import designs.chess_advanced.chess.model.Cell;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CellTest {

    @Test
    void testFreeCell() {
        Cell cell = new Cell(0, 0);
        Assertions.assertTrue(cell.isFree());
    }

    @Test
    void testOccupiedCell() {
        Cell cell = new Cell(0, 0);
        cell.setCurrentPiece(TestHelpers.randomPiece());
        Assertions.assertFalse(cell.isFree());
    }
}