public class PercolationStats {
	
	// perform T independent computational experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
    }
    
	// sample mean of percolation threshold 
    public double mean() {
        return 0;
    } 
    
	// sample standard deviation of percolation threshold 
    public double stddev() {
        return 0;
    }
    
	// returns lower bound of the 95% confidence interval
    
    public double confidenceLo() {
        return 0;
    }
    
    // returns upper bound of the 95% confidence interval
    public double confidenceHi() {
        return 0;
    }
   
    // test client, described below
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(N, T);
        
        double mean = stats.mean();
        double stddev = stats.stddev();
        double[] interval = new double[2];
        
        interval[0] = mean - ((1.96 * stddev) / Math.sqrt(T));
        interval[1] = mean + ((1.96 * stddev) / Math.sqrt(T));
        
        System.out.println("mean                    = " + mean);
        System.out.println("stddev                  = " + stddev);
        System.out.println("95% confidence interval = " + interval[0] + ", " + interval[1]);
    }
}