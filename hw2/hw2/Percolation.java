package hw2;

import edu.princeton.cs.algs4.UF;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] percolation;
    private int N;
    private int size;          //count the number of open sites
    private int OPEN = 1;
    private int BLOCKED = 0;

    private int top; // the virtual node
    private int bottom; // the virtual node

    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufTopOnly;

    /** create N-by-N grid, with all sites initially blocked. */
    public Percolation(int N) {
        if (N <= 0) throw new IllegalArgumentException("the N must be more than 0.");
        this.N = N;
        percolation = new int[N][N];
        for (int i = 0;i < N;i ++) {
            for (int j = 0;j< N;j ++) {
                percolation[i][j] = BLOCKED;
            }
        }
        this.size = 0;
        this.top = 0; // the virtual top node
        this.bottom = N * N + 1; // the virtual bottom node
        uf = new WeightedQuickUnionUF(N * N + 2);
        ufTopOnly = new WeightedQuickUnionUF(N * N + 1);
    }

    private int xyTo1D(int r, int c) {
        return  (r * this.N + c);
    }

    /** open the site (row, col) if it is not open already. */
    public void open(int row, int col) {
        if (row < 0 || row > (N - 1) || col < 0 || col > (N - 1))
            throw new IndexOutOfBoundsException("the index is out of the percolation.");

        //open the site (row,col)
        if (percolation[row][col] == OPEN) return;
        percolation[row][col] = OPEN;

        //check the left,right,up,down is open or blocked, if it is open union them.
        //顶端必定通
        if (row == 0) {
            uf.union(xyTo1D(row, col), top);
            ufTopOnly.union(xyTo1D(row, col), top);
        }
        if (row > 0 && isOpen(row - 1, col)) {
            uf.union(xyTo1D(row, col), xyTo1D(row - 1, col));
            ufTopOnly.union(xyTo1D(row, col), xyTo1D(row - 1, col));
        }
        if (row < N - 1 && isOpen(row + 1, col)) {
            uf.union(xyTo1D(row, col), xyTo1D(row + 1, col));
            ufTopOnly.union(xyTo1D(row, col), xyTo1D(row + 1, col));
        }
        if (row == N - 1 && !percolates()) {
            uf.union(xyTo1D(row, col), bottom);
        }
        if (col > 0 && isOpen(row, col - 1)) {
            uf.union(xyTo1D(row, col), xyTo1D(row, col - 1));
            ufTopOnly.union(xyTo1D(row, col), xyTo1D(row, col - 1));
        }
        if (col < N - 1 && isOpen(row, col + 1)) {
            uf.union(xyTo1D(row, col), xyTo1D(row, col + 1));
            ufTopOnly.union(xyTo1D(row, col), xyTo1D(row, col + 1));
        }
        size += 1;
    }

    /** is the site (row, col) open? */
    public boolean isOpen(int row, int col) {
        if (row < 0 || row > (N - 1) || col < 0 || col > (N - 1))
            throw new IndexOutOfBoundsException("the index is out of the percolation.");
        return percolation[row][col] == OPEN;
    }
    /** is the site (row, col) full? */
    public boolean isFull(int row, int col) {
        if (row < 0 || row > (N - 1) || col < 0 || col > (N - 1))
            throw new IndexOutOfBoundsException("the index is out of the percolation.");
        if (!isOpen(row, col))
            return false;
        return ufTopOnly.connected(xyTo1D(row, col), top);
    }

    /** number of open sites. */
    public int numberOfOpenSites() {
        return size;
    }

    /** does the system percolate? */
    public boolean percolates() {
        return uf.connected(top, bottom);
    }


    /** use for unit testing (not required). */
    public static void main(String[] args) {
        int[] a = new int[5];
        for(int i = 0;i < a.length;i ++) {
            a[i] = 0;
        }
        System.out.println(a.length);
    }

}
