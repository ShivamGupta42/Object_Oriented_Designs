package designs.chess_advanced.chess.conditions;

import designs.chess_advanced.chess.model.Board;
import designs.chess_advanced.chess.model.Cell;
import designs.chess_advanced.chess.model.Piece;
import designs.chess_advanced.chess.model.Player;

/**
 * This tells that a cell cannot occupy a cell if that cell already has any piece from the same player.
 */
public class PieceCellOccupyBlockerSelfPiece implements PieceCellOccupyBlocker {

    @Override
    public boolean isCellNonOccupiableForPiece(Cell cell, Piece piece, Board board, Player player) {
        if (cell.isFree()) {
            return false;
        }
        return cell.getCurrentPiece().getColor() == piece.getColor();
    }
}
