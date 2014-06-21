public class Percolation {
	
	private enum Cell {
        CLOSED,
        OPEN,
        LIQUID
    }
	
    private Cell[][] cells;
    
    private WeightedQuickUnionUF unionFind;
	
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
    
    }
    
    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
    	if (i < 1 || i > cells.length || j < 1 || j > cells.length) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return cells[i][i] == Cell.OPEN;
    } 
    
    // is site (row i, column j) full?    
    public boolean isFull(int i, int j) {
    	if (i < 1 || i > cells.length || j < 1 || j > cells.length) {
            throw new java.lang.IndexOutOfBoundsException();
        }
    	return cells[i][i] == Cell.LIQUID;
    }
    
    // does the system percolate?
        public boolean percolates() {
            return false;
    }
}