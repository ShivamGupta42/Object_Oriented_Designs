package designs.chess_advanced.chess.conditions;

import designs.chess_advanced.chess.model.Board;
import designs.chess_advanced.chess.model.Cell;
import designs.chess_advanced.chess.model.Piece;
import designs.chess_advanced.chess.model.Player;

/**
 * This check tells whether a piece can occupy a given cell in the board or not.
 */
public interface PieceCellOccupyBlocker {

    boolean isCellNonOccupiableForPiece(Cell cell, Piece piece, Board board, Player player);
}
