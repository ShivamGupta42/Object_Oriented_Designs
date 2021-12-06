package designs.chess_advanced.gameplay;

import designs.chess_advanced.chess.model.Board;
import designs.chess_advanced.chess.model.Player;
import designs.chess_advanced.gameplay.contracts.PlayerMove;

import java.util.List;

import static designs.chess_advanced.chess.conditions.PieceCellOccupyBlockerFactory.defaultAdditionalBlockers;

public class GameController {

    public static void gameplay(List<Player> players, Board board) {
        int currentPlayer = 0;
        while (true) {
            Player player = players.get(currentPlayer);
            //TODO: Check if current player has any move possible. If no move possible, then its checkmate.
            PlayerMove playerMove = player.makeMove();
            playerMove.getPiece().move(player, playerMove.getToCell(), board, defaultAdditionalBlockers());
            currentPlayer = (currentPlayer + 1) % players.size();
        }
    }
}
