package designs.chess_advanced.chess.conditions;

import designs.chess_advanced.chess.model.Piece;

/**
 * This condition allows a move only if cell is making a move from its initial position. That is first move ever.
 */
public class MoveBaseConditionFirstMove implements MoveBaseCondition {

    public boolean isBaseConditionFullfilled(Piece piece) {
        return piece.getNumMoves() == 0;
    }
}
