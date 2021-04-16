package design.minesweeper;

import java.util.Random;

public class Minesweeper {
    public enum CellStatus {UNEXPOSED, EXPOSED, SEALED};
    public enum GameStatus {INPROGRESS, WON, LOST};
    final int SIZE = 10;

    private CellStatus[][] cellStates = new CellStatus[SIZE][SIZE];
    private boolean[][] mines = new boolean[SIZE][SIZE];

    public Minesweeper() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cellStates[i][j] = CellStatus.UNEXPOSED;
            }
        }
    }

    public CellStatus getCellStatus(int row, int column) {
        return cellStates[row][column];
    }

    public void exposeCell(int row, int column) {
        if (cellStates[row][column] == CellStatus.UNEXPOSED) {
            cellStates[row][column] = CellStatus.EXPOSED;

            if (adjacentMinesCountAt(row, column) == 0) {
                exposeNeighborsOf(row, column);
            }
        }
    }

    public void exposeNeighborsOf(int row, int column) {
        for (int i = Math.max(0, row - 1); i < Math.min(row + 2, SIZE); i++) {
            for (int j = Math.max(0, column - 1); j < Math.min(column + 2, SIZE); j++) {
                exposeCell(i, j);
            }
        }
    }

    public void toggleSeal(int row, int column) {
//    if(cellStates[row][column] == CellStatus.UNEXPOSED) {
//      cellStates[row][column] = CellStatus.SEALED;
//    } else {
//      if(cellStates[row][column] == CellStatus.SEALED) {
//        cellStates[row][column] = CellStatus.UNEXPOSED;
//      }
//    }

        //not a fan of nested if-else, we can make this simpler by using return

        if (cellStates[row][column] == CellStatus.UNEXPOSED) {
            cellStates[row][column] = CellStatus.SEALED;
            return;
        }

        if (cellStates[row][column] == CellStatus.SEALED) {
            cellStates[row][column] = CellStatus.UNEXPOSED;
        }

        //the return is smelly

        //if is a statement and often leads to complex nesting...
        //Java is getting better. If using more recent versions of Java, we can do the following.

    /* or better, with switch expression
    cellStates[row][column] = switch(cellStates[row][column]) {
      case UNEXPOSED -> CellStatus.SEALED;
      case SEALED -> CellStatus.UNEXPOSED;
      default -> CellStatus.EXPOSED;
    };
    */
    }

    public boolean isMineAt(int row, int column) {
        return row >= 0 && row < SIZE && column >= 0 && column < SIZE && mines[row][column];
    }

    public void setMineAt(int row, int column) {
        mines[row][column] = true;
    }

    public int adjacentMinesCountAt(int row, int column) {
        int count = 0;

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if (isMineAt(i, j)) {
                    count++;
                }
            }
        }

        return isMineAt(row, column) ? count - 1 : count;
    }

    public GameStatus getGameStatus() {
        boolean allSealedOrExposed = true;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isMineAt(i, j) && cellStates[i][j] != CellStatus.SEALED) {
                    allSealedOrExposed = false;
                }

                if (!isMineAt(i, j) && cellStates[i][j] != CellStatus.EXPOSED) {
                    allSealedOrExposed = false;
                }

                if (isMineAt(i, j) && cellStates[i][j] == CellStatus.EXPOSED) {
                    return GameStatus.LOST;
                }
            }
        }

        return allSealedOrExposed ? GameStatus.WON : GameStatus.INPROGRESS;
    }

    public void setMines(int seed) {
        Random random = new Random(seed);

        int count = 0;
        while (count != SIZE) {
            int row = random.nextInt(SIZE);
            int column = random.nextInt(SIZE);

            if (!isMineAt(row, column)) {
                setMineAt(row, column);
                count++;
            }
        }
    }
}


