public class Percolation {

    private static final int CLOSED = 0;
    private static final int OPEN   = 2;
//    private static final int FULL   = 4;
    private static final int BOTTOM = 8;
    private static final int TOP    = 16;
    private static final int RIGHT  = 32;
    private static final int LEFT   = 64;

    private int[][] cells;
    
    private WeightedQuickUnionUF unionFind;
   
    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        if (N < 1) {
            throw new java.lang.IllegalArgumentException();
        }
        cells     = new int[N][N];
        unionFind = new WeightedQuickUnionUF(N * N + 2);
        
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                cells[r][c] = CLOSED;
            }
        }
    }
  
    private int conv2Dto1D(int i, int j) {
        return i * cells.length + j;
    }
  
    private boolean testBit(int i, int j, int bit) {
        return (cells[i][j] &  bit) != 0;
    }
    
    private void unionWithOpen(int i, int j, int k, int l) {
        if (isOpen(k, l)) {
            unionFind.union(conv2Dto1D(i-1, j-1), conv2Dto1D(k-1, l-1));
        }
//        if (testBit(k-1, l-1, FULL)) {
//            cells[i-1][j-1] |= FULL;
//        }
    }

    private void connectToOpenNeighbors(int i, int j) {
        
        if (cells.length > 1) {
            if (i == 1) {
//                cells[i-1][j-1] |= FULL;
                cells[i-1][j-1] |= BOTTOM;
                unionFind.union(cells.length * cells.length,
                                conv2Dto1D(i-1, j-1));
            } else if (i == cells.length) {
                cells[i-1][j-1] |= TOP;
                unionFind.union(cells.length * cells.length + 1,
                                conv2Dto1D(i-1, j-1));
            } else {
                cells[i-1][j-1] |= BOTTOM;
                cells[i-1][j-1] |= TOP;
            }
            if (j == 1) {
                cells[i-1][j-1] |= RIGHT;
            } else if (j == cells.length) {
                cells[i-1][j-1] |= LEFT;
            } else {
                cells[i-1][j-1] |= RIGHT;
                cells[i-1][j-1] |= LEFT;
            }           

            if (testBit(i-1, j-1, BOTTOM)) {
                unionWithOpen(i, j, i+1, j); 
            }
            if (testBit(i-1, j-1, TOP)) {
                unionWithOpen(i, j, i-1, j); 
            }
            if (testBit(i-1, j-1, RIGHT)) {
                unionWithOpen(i, j, i, j+1);
            }
            if (testBit(i-1, j-1, LEFT)) {
                unionWithOpen(i, j, i, j-1);
            }
       } else {
//            cells[0][0] |= FULL;
            unionFind.union(cells.length * cells.length, 0);
            unionFind.union(cells.length * cells.length + 1, 0);
            return; 
        }
    }
    
    // open site (row i, column j) if it is not already
    public void open(int i, int j) {
        if (i < 1 || i > cells.length || j < 1 || j > cells.length) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (!isOpen(i, j)) {
            cells[i-1][j-1] |= OPEN;
            connectToOpenNeighbors(i, j);
        }
    }
    
    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        if (i < 1 || i > cells.length || j < 1 || j > cells.length) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return testBit(i-1, j-1, OPEN);
    } 
    
    // is site (row i, column j) full?    
    public boolean isFull(int i, int j) {
        int input = cells.length * cells.length;
        int output = conv2Dto1D(i-1, j-1); 
 
        if (i < 1 || i > cells.length || j < 1 || j > cells.length) {
            throw new java.lang.IndexOutOfBoundsException();
        }
//        return testBit(i-1, j-1, FULL);
        return unionFind.connected(input, output);
    }
    
    // does the system percolate?
    public boolean percolates() {
        int input = cells.length * cells.length;
        int output = cells.length * cells.length + 1;
        
        return unionFind.connected(input, output);
    }
}
