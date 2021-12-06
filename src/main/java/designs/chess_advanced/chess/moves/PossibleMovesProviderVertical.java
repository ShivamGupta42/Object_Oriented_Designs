package designs.chess_advanced.chess.moves;

import designs.chess_advanced.chess.conditions.MoveBaseCondition;
import designs.chess_advanced.chess.conditions.PieceCellOccupyBlocker;
import designs.chess_advanced.chess.conditions.PieceMoveFurtherCondition;
import designs.chess_advanced.chess.model.Board;
import designs.chess_advanced.chess.model.Cell;
import designs.chess_advanced.chess.model.Piece;
import designs.chess_advanced.chess.model.Player;

import java.util.ArrayList;
import java.util.List;

import static designs.chess_advanced.chess.moves.VerticalMoveDirection.*;


public class PossibleMovesProviderVertical extends PossibleMovesProvider {
    private VerticalMoveDirection verticalMoveDirection;

    public PossibleMovesProviderVertical(int maxSteps, MoveBaseCondition baseCondition,
                                         PieceMoveFurtherCondition moveFurtherCondition, PieceCellOccupyBlocker baseBlocker,
                                         VerticalMoveDirection verticalMoveDirection) {
        super(maxSteps, baseCondition, moveFurtherCondition, baseBlocker);
        this.verticalMoveDirection = verticalMoveDirection;
    }


    @Override
    protected List<Cell> possibleMovesAsPerCurrentType(Piece piece, Board board, List<PieceCellOccupyBlocker> additionalBlockers, Player player) {
        List<Cell> result = new ArrayList<>();
        if (this.verticalMoveDirection == UP || this.verticalMoveDirection == BOTH) {
            result.addAll(findAllNextMoves(piece, board::getUpCell, board, additionalBlockers, player));
        }
        if (this.verticalMoveDirection == DOWN || this.verticalMoveDirection == BOTH) {
            result.addAll(findAllNextMoves(piece, board::getDownCell, board, additionalBlockers, player));
        }
        return result;
    }
}
