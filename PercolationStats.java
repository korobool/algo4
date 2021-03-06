public class PercolationStats {
        
    private double[] values;
    private int t;
    
    // perform T independent computational experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }

        Percolation percolation;
        
        values = new double[T];

        t = T;
        
        for (int p = 0; p < T; p++) {
            
            int n = 0;
            percolation = new Percolation(N);
            
            while (n < N * N) {
                
                int i = StdRandom.uniform(N) + 1;
                int j = StdRandom.uniform(N) + 1;
                
                if (percolation.isOpen(i, j))
                    continue;
                
                n++;
                percolation.open(i, j);
                
                if (percolation.percolates()) {
                    values[p] = n / (double) (N * N);
                    break;
                }
            }
        }
    }
    
    // sample mean of percolation threshold 
    public double mean() {
        return StdStats.mean(values);
    } 
    
    // sample standard deviation of percolation threshold 
    public double stddev() {
        return StdStats.stddev(values);
    }
    
    // returns lower bound of the 95% confidence interval
    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(t));
    }
    
    // returns upper bound of the 95% confidence interval
    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(t));
    }
   
    // test client, described below
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(N, T);
        
       
        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = " + stats.confidenceLo() + ", "
                           + stats.confidenceHi());
    }
}
