public class Percolation {
	
    private enum Cell {
        CLOSED,
        OPEN,
        LIQUID
    }
	
    private Cell[][] cells;
    
    private WeightedQuickUnionUF unionFind;
    
    private int conv2Dto1D(int i, int j) {
        return (i - 1) * cells.length + j;
    }
    
    private void connectToOpenNeighbors(int i, int j) {
        int cStart, cLast, rStart, rLast;

        if(i == 1) { 
            rStart = 1;
            rLast  = 2;
        } else if (i == cells.length) {
            rStart = i - 1;
            rLast  = i;
        } else {
            rStart = i - 1;
            rLast  = i + 1;
        }
        if(j == 1) { 
            cStart = 1;
            cLast  = 2;
        } else if (j == cells.length) {
            cStart = j - 1;
            cLast  = j;
        } else {
            cStart = j - 1;
            cLast  = j + 1;
        }
	    for (int r = rStart; r <= rLast; r++) {
            for (int c = cStart; c <= cLast; c++) {
	            if (isOpen(r,c)) {
                    unionFind.union(conv2Dto1D(i,j), conv2Dto1D(r,c));
		        }
            }
        }
    }
	
    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        cells     = new Cell[N][N];
        unionFind = new WeightedQuickUnionUF(N * N + 2);
        
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                cells[r][c] = Cell.CLOSED;
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
    	if (i < 1 || i > cells.length || j < 1 || j > cells.length) {
            throw new java.lang.IndexOutOfBoundsException();
        }
    	return cells[i-1][j-1] == Cell.LIQUID;
    }
    
    // does the system percolate?
    public boolean percolates() {
//   		int input = cells.length * cells.length;
     		int input = 1;
//    		int output = cells.length * cells.length + 1;
      		int output = cells.length * cells.length ;
        	return unionFind.connected(input, output);
    }
}
