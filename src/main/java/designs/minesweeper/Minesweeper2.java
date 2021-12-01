package designs.minesweeper;

public class Minesweeper2 {



    public enum CellStatus {UNEXPOSED, EXPOSED, MINED}

    private final int SIZE = 10;
    private CellStatus[][] grid = new CellStatus[SIZE][SIZE];


    public Minesweeper2() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = CellStatus.UNEXPOSED;
            }
        }
    }

    public CellStatus[][] getGrid() {
        return grid;
    }

    public void exposeCell(int i, int j) {
        //Boundary conditions

        if(grid[i][j]== CellStatus.UNEXPOSED){
            grid[i][j]= CellStatus.EXPOSED;
        }

        if(countOfAdjacentCellsWithMines(i,j)==0){
            exposeAllNeighbours(i,j);
        }
    }

    private void exposeAllNeighbours(int i, int j) {
    }

    private int countOfAdjacentCellsWithMines(int i, int j) {
        return 2;
    }

}
