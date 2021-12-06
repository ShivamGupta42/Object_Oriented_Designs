package designs.chess_advanced.chess.moves;

import designs.chess_advanced.chess.model.Cell;

/**
 * Given a cell, it will provide next cell which we can reach to.
 */
interface NextCellProvider {
    Cell nextCell(Cell cell);
}
