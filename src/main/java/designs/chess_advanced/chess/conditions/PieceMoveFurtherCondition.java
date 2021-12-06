package designs.chess_advanced.chess.conditions;

import designs.chess_advanced.chess.model.Board;
import designs.chess_advanced.chess.model.Cell;
import designs.chess_advanced.chess.model.Piece;

/**
 * Condition interface to tell whether a piece can further move from a cell.
 */
public interface PieceMoveFurtherCondition {

    boolean canPieceMoveFurtherFromCell(Piece piece, Cell cell, Board board);
}
