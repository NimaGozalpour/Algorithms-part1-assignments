import edu.princeton.cs.algs4.MinPQ;

import java.util.Deque;
import java.util.LinkedList;

public class Solver {

    private SearchNode solutionNode;

    private class SearchNode implements Comparable<SearchNode> {
        public Board board;
        public int move;
        public SearchNode previousNode;
        public int priority;

        public SearchNode(Board board, SearchNode previousNode) {
            this.board = board;
            this.previousNode = previousNode;
            if (previousNode == null) {
                this.move = 0;
            }
            else {
                this.move = previousNode.move + 1;
            }

            this.priority = board.manhattan() + this.move;
        }
        public void insertNeighbors(MinPQ<SearchNode> priorityQueue) {
            for (Board neighbor : board.neighbors()) {
                if (previousNode != null && neighbor.equals(previousNode.board)) continue;

                SearchNode node = new SearchNode(neighbor, this);
                priorityQueue.insert(node);
            }
        }

        public int compareTo(SearchNode that) {
            return this.priority - that.priority;
        }
    }
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();

        MinPQ<SearchNode> solutionPQ = new MinPQ<SearchNode>();
        MinPQ<SearchNode> twinPQ = new MinPQ<SearchNode>();

        SearchNode initialNode = new SearchNode(initial, null);
        solutionPQ.insert(initialNode);

        SearchNode initialTwinNode = new SearchNode(initial.twin(), null);
        twinPQ.insert(initialTwinNode);

        SearchNode node1, node2;
        while (true) {
            node1 = solutionPQ.delMin();
            node2 = twinPQ.delMin();

            if (node1.board.isGoal()) {
                this.solutionNode = node1;
                break;
            }

            if (node2.board.isGoal()) {
               this.solutionNode = null;
               break;
            }

            node1.insertNeighbors(solutionPQ);
            node2.insertNeighbors(twinPQ);
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return solutionNode != null;
    }

    // min number of moves to solve initial board
    public int moves() {
        if (isSolvable()) {
            return solutionNode.move;
        }
        else {
            return -1;
        }
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        if (isSolvable()) {
            Deque<Board> solution = new LinkedList<>();
            SearchNode next = solutionNode;
            while (next != null) {
                solution.addFirst(next.board);
                next = next.previousNode;
            }

            return solution;
        }
        else {
            return null;
        }
    }

    // test client (see below)
    public static void main(String[] args) {
        edu.princeton.cs.algs4.In in = new edu.princeton.cs.algs4.In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            edu.princeton.cs.algs4.StdOut.println("No solution possible");
        else {
            edu.princeton.cs.algs4.StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                edu.princeton.cs.algs4.StdOut.println(board);
        }
    }

}
