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

public class PossibleMovesProviderHorizontal extends PossibleMovesProvider {

    public PossibleMovesProviderHorizontal(int maxSteps, MoveBaseCondition baseCondition,
                                           PieceMoveFurtherCondition moveFurtherCondition, PieceCellOccupyBlocker baseBlocker) {
        super(maxSteps, baseCondition, moveFurtherCondition, baseBlocker);
    }

    protected List<Cell> possibleMovesAsPerCurrentType(Piece piece, final Board board, List<PieceCellOccupyBlocker> additionalBlockers, Player player) {
        List<Cell> result = new ArrayList<>();
        result.addAll(findAllNextMoves(piece, board::getLeftCell, board, additionalBlockers, player));
        result.addAll(findAllNextMoves(piece, board::getRightCell, board, additionalBlockers, player));
        return result;
    }
}
