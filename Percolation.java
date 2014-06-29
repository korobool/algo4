public class Percolation {

    private enum Cell {
        CLOSED,
        OPEN,
        FULL
    }

    private Cell[][] cells;
    
    private WeightedQuickUnionUF unionFind;
   
    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        if (N < 0) {
            throw new java.lang.IllegalArgumentException();
        }
        cells     = new Cell[N][N];
        unionFind = new WeightedQuickUnionUF(N * N + 2);
        
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                cells[r][c] = Cell.CLOSED;
            }
        }
    }
  
    private int conv2Dto1D(int i, int j) {
        return i * cells.length + j;
    }
    
    private void connectToOpenNeighbors(int i, int j) {
        int cStart, cLast, rStart, rLast;
        
        if (cells.length > 1) {
            if (i == 1) { 
                rStart = 1;
                rLast  = 2;
                unionFind.union(cells.length * cells.length,
                                        conv2Dto1D(i-1, j-1));
            } else if (i == cells.length) {
                rStart = i - 1;
                rLast  = i;
                unionFind.union(cells.length * cells.length + 1,
                                        conv2Dto1D(i-1, j-1));
            } else {
                rStart = i - 1;
                rLast  = i + 1;
            }
            if (j == 1) { 
                cStart = 1;
                cLast  = 2;
            } else if (j == cells.length) {
                cStart = j - 1;
                cLast  = j;
            } else {
                cStart = j - 1;
                cLast  = j + 1;
            }
        } else {
            unionFind.union(cells.length * cells.length, 0);
            unionFind.union(cells.length * cells.length + 1, 0);
            return; 
        }

        for (int r = rStart; r <= rLast; r++) {
            for (int c = cStart; c <= cLast; c++) {
                if (isOpen(r, c)) {
                    if (!(r == i && c == j)) {
                        unionFind.union(conv2Dto1D(i-1, j-1),
                                        conv2Dto1D(r-1, c-1));
                    }
                }
            }
        }
        
    }
    
    // open site (row i, column j) if it is not already
    public void open(int i, int j) {
        if (i < 1 || i > cells.length || j < 1 || j > cells.length) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (!isOpen(i, j)) {
            cells[i-1][j-1] = Cell.OPEN;
            connectToOpenNeighbors(i, j);
        }
    }
    
    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        if (i < 1 || i > cells.length || j < 1 || j > cells.length) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return cells[i-1][j-1] == Cell.OPEN;
    } 
    
    // is site (row i, column j) full?    
    public boolean isFull(int i, int j) {
        int input = cells.length * cells.length;
        int output = conv2Dto1D(i-1, j-1); 
 
        if (i < 1 || i > cells.length || j < 1 || j > cells.length) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return unionFind.connected(input, output);
    }
    
    // does the system percolate?
    public boolean percolates() {
        int input = cells.length * cells.length;
        int output = cells.length * cells.length + 1;
        
        return unionFind.connected(input, output);
    }
}
