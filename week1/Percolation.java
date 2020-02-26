import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {

    private final int topRoot;
    private final int bottomRoot;
    private int openSites = 0;

    private final int gridDimension;
    private final WeightedQuickUnionUF unionFind;
    private final WeightedQuickUnionUF fullSites;
    private boolean [][] sites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException();
        }

        gridDimension = n;

        unionFind = new WeightedQuickUnionUF(gridDimension * gridDimension + 2);
        fullSites = new WeightedQuickUnionUF(gridDimension * gridDimension + 1);

        bottomRoot = gridDimension * gridDimension + 1;
        topRoot = gridDimension * gridDimension;


        sites = new boolean[gridDimension][gridDimension];

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        check(row, col);
        if (!isOpen(row, col)) {
            sites[row - 1][col - 1] = true;
            openSites++;

            int index = index(row, col);
            if (row == gridDimension) {
                unionFind.union(index, bottomRoot);

                if (gridDimension == 1){
                    unionFind.union(index, topRoot);
                    fullSites.union(index, topRoot);
                }
                else if (isOpen(row - 1, col)) {
                    unionFind.union(index(row - 1, col), index);
                    fullSites.union(index(row - 1, col), index);
                }
            }
            else if (row == 1) {
                unionFind.union(index, topRoot);
                fullSites.union(index, topRoot);

                if (isOpen(row + 1, col)) {
                    unionFind.union(index(row + 1, col), index);
                    fullSites.union(index(row + 1, col), index);
                }
            }

            else {
                if (isOpen(row + 1, col)) {
                    unionFind.union(index(row + 1, col), index);
                    fullSites.union(index(row + 1, col), index);
                }

                if (isOpen(row - 1, col)) {
                    unionFind.union(index(row - 1, col), index);
                    fullSites.union(index(row - 1, col), index);
                }
            }



            if (col > 1) {
                if (isOpen(row, col - 1)) {
                    unionFind.union(index(row, col - 1), index);
                    fullSites.union(index(row, col - 1), index);
                }
            }

            if (col < gridDimension) {
                if (isOpen(row, col + 1)) {
                    unionFind.union(index(row, col + 1), index);
                    fullSites.union(index(row, col + 1), index);
                }
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        check(row, col);
        return sites[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        check(row, col);
        int index = index(row, col);
        return fullSites.connected(index, topRoot);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return unionFind.connected(topRoot, bottomRoot);
    }


    private void check(int row, int col) {
        if (row < 1 || row > gridDimension || col < 1 || col > gridDimension) {
            throw new IllegalArgumentException();
        }
    }

    private int index(int row, int col) {
        return gridDimension * (row - 1) + col - 1;
    }

    // test client (optional)
    // public static void main(String[] args)
}
