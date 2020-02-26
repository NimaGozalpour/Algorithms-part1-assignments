import java.util.LinkedList;

public class Board {

    private final int[][] board;
    private final int manthan, hamming;
    private LinkedList<Board> neighbors;
    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        if (tiles[0].length < 2){
            throw new IllegalArgumentException();
        }

        this.board = tiles;


        int ham = 0;
        int man = 0;
        int row;
        int col;
        for (int i = 0; i < this.dimension(); i++) {
            for (int j = 0; j < this.dimension(); j++) {
                if (board[i][j] == i * this.dimension() + j + 1) {
                    ham ++;
                }

                if (board[i][j] == 0) continue;
                row = (board[i][j] - 1) / this.dimension();
                col = (board[i][j] - 1) - row * this.dimension();
                man += Math.abs(i - row) + Math.abs(j - col);

            }

        }
        this.hamming = this.dimension()*this.dimension() - 1 - ham;
        this.manthan = man;
    }

    // string representation of this board
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(this.dimension());

        for (int i = 0; i < this.dimension(); i++) {
            stringBuilder.append("\n");
            for (int j = 0; j < this.dimension(); j++) {
                stringBuilder.append(" " + this.board[i][j]);
            }

        }


        return stringBuilder.toString();


    }

    // board dimension n
    public int dimension() {
        return board[0].length;
    }

    // number of tiles out of place
    public int hamming() {

        return this.hamming;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {


        return this.manthan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return this.manhattan() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null) return false;
        if (this.getClass() != y.getClass()) return false;

        Board that = (Board) y;

        if (this.dimension() != that.dimension()) return false;
        for (int i = 0; i < this.dimension(); i++) {
            for (int j = 0; j < this.dimension(); j++) {
                if (board[i][j] != that.board[i][j]) {
                    return false;
                }
            }

        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        if (neighbors != null) return neighbors;

        neighbors  = new LinkedList<Board>();

        int zeroI = 0;
        int zeroJ = 0;

        int flag = 0;
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (board[i][j] == 0) {
                    zeroI = i;
                    zeroJ = j;
                    flag = 1;
                    break;

                }
            }
            if (flag == 1) break;
        }

        for (int k = 0; k < 4; k++) {
            int blockI = zeroI;
            int blockJ = zeroJ;

            switch (k) {
                case 0: blockI++;
                    break;
                case 1: blockI--;
                    break;
                case 2: blockJ++;
                    break;
                case 3: blockJ--;
                    break;
            }

            if (blockI < 0 || blockI >= dimension() || blockJ < 0 || blockJ >= dimension()) continue;
            int[][] heighborBlocks = copyBoard();

            heighborBlocks[zeroI][zeroJ] = heighborBlocks[blockI][blockJ];
            heighborBlocks[blockI][blockJ] = 0;

            Board neighbor = new Board(heighborBlocks);
            neighbors.add(neighbor);
        }

        return neighbors;

    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {

        int[][] newBoard = this.copyBoard();
        if (newBoard[0][0] != 0 && newBoard[0][1] != 0) {
            int temp = newBoard[0][0];
            newBoard[0][0] = newBoard[0][1];
            newBoard[0][1] = temp;
        }
        else {
            int temp = newBoard[1][0];
            newBoard[1][0] = newBoard[1][1];
            newBoard[1][1] = temp;
        }

        return new Board(newBoard);
    }

    private int[][] copyBoard() {
        int[][] newBoard = new int[dimension()][dimension()];
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                newBoard[i][j] = board[i][j];
            }
        }
        return newBoard;
    }

    // unit testing (not graded)
    public static void main(String[] args) {

    }

}
