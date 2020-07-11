package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {

    private int timeTrial;  // times
    private double[] fractions;
    // u = (x1 + x2 + ... + xt) / T
    // x1 = 204/400
    /** perform T independent experiments on an N-by-N grid. */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (T <= 0 || N <= 0)
            throw new IllegalArgumentException("the N or T is out of index.");
        Percolation p = pf.make(N);
        this.timeTrial = T;
        fractions = new double[T];
        for (int i = 0;i < T;i ++) {
            while (!p.percolates()) {

                int r = StdRandom.uniform(N);
                int c = StdRandom.uniform(N);
                if (!p.isOpen(r, c)) {
                    p.open(r, c);
                }
            }
            fractions[i] = (double) p.numberOfOpenSites();
        }
    }

    /** sample mean of percolation threshold. */
    public double mean() {
        return StdStats.mean(fractions);
    }

    /** sample standard deviation of percolation threshold. */
    public double stddev() {
        return StdStats.stddev(fractions);
    }

    /** low endpoint of 95% confidence interval. */
    public double confidenceLow() {
        double mea = mean();
        double std = stddev();
        double sig = 1.96;
        return  mea - (sig * std) / Math.sqrt(timeTrial);
    }

    /** high endpoint of 95% confidence interval. */
    public double confidenceHigh() {
        double mea = mean();
        double std = stddev();
        double sig = 1.96;
        return  mea + (sig * std) / Math.sqrt(timeTrial);
    }


}
