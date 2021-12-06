package designs.chess_advanced.gameplay.contracts;

import designs.chess_advanced.chess.model.Cell;
import designs.chess_advanced.chess.model.Piece;
import lombok.Getter;

@Getter
public class PlayerMove {

    Piece piece;
    Cell toCell;
}
