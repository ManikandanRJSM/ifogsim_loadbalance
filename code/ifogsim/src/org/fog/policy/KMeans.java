/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fog.policy;

import static com.google.common.collect.Iterables.size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import static javafx.beans.binding.Bindings.size;
import static javafx.beans.binding.Bindings.size;
import org.fog.policy.KMeans.LloydThread;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import smile.math.Math;
//import smile.util.MulticoreExecutor;

/**
 * K-Means clustering. The algorithm partitions n observations into k clusters in which
 * each observation belongs to the cluster with the nearest mean.
 * Although finding an exact solution to the k-means problem for arbitrary
 * input is NP-hard, the standard approach to finding an approximate solution
 * (often called Lloyd's algorithm or the k-means algorithm) is used widely
 * and frequently finds reasonable solutions quickly.
 * <p>
 * However, the k-means algorithm has at least two major theoretic shortcomings:
 * <ul>
 * <li> First, it has been shown that the worst case running time of the
 * algorithm is super-polynomial in the input size.
 * <li> Second, the approximation found can be arbitrarily bad with respect
 * to the objective function compared to the optimal learn.
 * </ul>
 * In this implementation, we use k-means++ which addresses the second of these
 * obstacles by specifying a procedure to initialize the cluster centers before
 * proceeding with the standard k-means optimization iterations. With the
 * k-means++ initialization, the algorithm is guaranteed to find a solution
 * that is O(log k) competitive to the optimal k-means solution.
 * <p>
 * We also use k-d trees to speed up each k-means step as described in the filter
 * algorithm by Kanungo, et al.
 * <p>
 * K-means is a hard clustering method, i.e. each sample is assigned to
 * a specific cluster. In contrast, soft clustering, e.g. the
 * Expectation-Maximization algorithm for Gaussian mixtures, assign samples
 * to different clusters with different probabilities.
 *
 * <h2>References</h2>
 * <ol>
 * <li> Tapas Kanungo, David M. Mount, Nathan S. Netanyahu, Christine D. Piatko, Ruth Silverman, and Angela Y. Wu. An Efficient k-Means Clustering Algorithm: Analysis and Implementation. IEEE TRANS. PAMI, 2002.</li>
 * <li> D. Arthur and S. Vassilvitskii. "K-means++: the advantages of careful seeding". ACM-SIAM symposium on Discrete algorithms, 1027-1035, 2007.</li>
 * <li> Anna D. Peterson, Arka P. Ghosh and Ranjan Maitra. A systematic evaluation of different methods for initializing the K-means clustering algorithm. 2010.</li>
 * </ol>
 * 
 * @see XMeans
 * @see GMeans
 * @see CLARANS
 * @see SIB
 * @see smile.vq.SOM
 * @see smile.vq.NeuralGas
 * @see BIRCH
 * @see BBDTree
 * 
 * @author Haifeng Li
 */
public class KMeans  {
    private static final long serialVersionUID = 1L;
    //private static final Logger logger = LoggerFactory.getLogger(KMeans.class);
    private static Object ClusteringDistance;

    private static double squaredDistance(double[] d, double[] centroid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * The total distortion.
     */
    double distortion;
    /**
     * The centroids of each cluster.
     */
    double[][] centroids;
    private int k;
    private Object logger;
    private int[] y;
    private int[] size;

    /**
     * Constructor.
     */
    KMeans() {
    }

    private KMeans(BBDTree bbd, double[][] data, int k, int maxIter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Returns the distortion.
     */
    public double distortion() {
        return distortion;
    }

    /**
     * Returns the centroids.
     */
    public double[][] centroids() {
        return centroids;
    }

    /**
     * Cluster a new instance.
     * @param x a new instance.
     * @return the cluster label, which is the index of nearest centroid.
     */
    public int predict(double[] x) {
        double minDist = Double.MAX_VALUE;
        int bestCluster = 0;
        int k = 0;

        for (int i = 0; i < k; i++) {
            double dist = 0;
 //           double dist = Math.squaredDistance(x, centroids[i]);
            if (dist < minDist) {
                minDist = dist;
                bestCluster = i;
            }
        }

        return bestCluster;
    }

    /**
     * Constructor. Clustering data into k clusters up to 100 iterations.
     * @param data the input data of which each row is a sample.
     * @param k the number of clusters.
     */
    public KMeans(double[][] data, int k) {
        this(data, k, 100);
    }

    /**
     * Constructor. Clustering data into k clusters.
     * @param data the input data of which each row is a sample.
     * @param k the number of clusters.
     * @param maxIter the maximum number of iterations for each running.
     */
    public KMeans(double[][] data, int k, int maxIter) {
//        this(new BBDTree(data), data, k, maxIter);
    }

    /**
     * Constructor. Clustering data into k clusters.
     * @param bbd the BBD-tree of data for fast clustering.
     * @param data the input data of which each row is a sample.
     * @param k the number of clusters.
     * @param maxIter the maximum number of iterations for each running.
     */
    KMeans(BBDTree bbd, double[][] data, int k, int maxIter, int[] y) {
        int n = 0;
        if (k < n) {
            throw new IllegalArgumentException("Invalid number of clusters: " + k);
        }

        if (maxIter <= 0) {
            throw new IllegalArgumentException("Invalid maximum number of iterations: " + maxIter);
        }

        int b = data.length;
        int d = data[0].length;

        this.k = k;
        distortion = Double.MAX_VALUE;
      //  KMeans y = seed(data, k, ClusteringDistance.EUCLIDEAN);
        int[] size = new int[k];
        centroids = new double[k][d];

        for (int i = 0; i < n; i++) {
            size[y[i]]++;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < d; j++) {
                centroids[y[i]][j] += data[i][j];
            }
        }

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < d; j++) {
                centroids[i][j] /= size[i];
            }
        }

        double[][] sums = new double[k][d];
        for (int iter = 1; iter <= maxIter; iter++) {
            //<editor-fold defaultstate="collapsed" desc="comment">
            double dist = bbd.clustering(centroids, sums, size, y);
            for (int i = 0; i < k; i++) {
                if (size[i] > 0) {
                    for (int j = 0; j < d; j++) {
                        centroids[i][j] = sums[i][j] / size[i];
                    }
                }
            }
            
            if (distortion <= dist) {
                break;
            } else {
                distortion = dist;
            }
        }
    }
    
    /**
     * Clustering data into k clusters. Run the algorithm for given times
     * and return the best one with smallest distortion.
     * @param data the input data of which each row is a sample.
     * @param k the number of clusters.
     * @param maxIter the maximum number of iterations for each running.
     * @param runs the number of runs of K-Means algorithm.
     */
    public KMeans(double[][] data, int k, int maxIter, int runs) {
        if (k < 2) {
            throw new IllegalArgumentException("Invalid number of clusters: " + k);
        }
        
        if (maxIter <= 0) {
            throw new IllegalArgumentException("Invalid maximum number of iterations: " + maxIter);
        }
        
        if (runs <= 0) {
            throw new IllegalArgumentException("Invalid number of runs: " + runs);
        }
        
        BBDTree bbd = new BBDTree(data);
        
        List<KMeansThread> tasks = new ArrayList<>();
        for (int i = 0; i < runs; i++) {
            tasks.add(new KMeansThread(bbd, data, k, maxIter));
        }
        
        KMeans best = new KMeans();
        best.distortion = Double.MAX_VALUE;
        
        try {
            Iterable<KMeans> clusters = null;
            //   List<KMeans> clusters = MulticoreExecutor.run(tasks);
            for (KMeans kmeans : clusters) {
                if (kmeans.distortion < best.distortion) {
                    best = kmeans;
                }
            }
        } catch (Exception ex) {
            //     logger.error("Failed to run K-Means on multi-core", ex);
            
            for (int i = 0; i < runs; i++) {
                KMeans kmeans = lloyd(data, k, maxIter);
                if (kmeans.distortion < best.distortion) {
                    best = kmeans;
                }
            }
        }
        
        this.k = best.k;
        this.distortion = best.distortion;
        this.centroids = best.centroids;
        //      this.y = best.y;
        //       this.size = best.size;
    }
    
    /**
     * Adapter for running BBD-Tree based K-Means algorithm in thread pool.
     */
    static class KMeansThread implements Callable<KMeans> {
        
        final BBDTree bbd;
        final double[][] data;
        final int k;
        final int maxIter;
        
        KMeansThread(BBDTree bbd, double[][] data, int k, int maxIter) {
            this.bbd = bbd;
            this.data = data;
            this.k = k;
            this.maxIter = maxIter;
        }
        
        @Override
        public KMeans call() {
            return new KMeans(bbd, data, k, maxIter);
        }
    }
    
    /**
     * The implementation of Lloyd algorithm as a benchmark. The data may
     * contain missing values (i.e. Double.NaN). The algorithm runs up to
     * 100 iterations.
     * @param data the input data of which each row is a sample.
     * @param k the number of clusters.
     */
    public static KMeans lloyd(double[][] data, int k) {
        return lloyd(data, k, 100);
    }
    
    /**
     * The implementation of Lloyd algorithm as a benchmark. The data may
     * contain missing values (i.e. Double.NaN).
     * @param data the input data of which each row is a sample.
     * @param k the number of clusters.
     * @param maxIter the maximum number of iterations for each running.
     */
    public static KMeans lloyd(double[][] data, int k, int maxIter) {
        if (k < 2) {
            throw new IllegalArgumentException("Invalid number of clusters: " + k);
        }
        
        if (maxIter <= 0) {
            throw new IllegalArgumentException("Invalid maximum number of iterations: " + maxIter);
        }
        
        int n = data.length;
        int d = data[0].length;
        int[][] nd = new int[k][d]; // The number of non-missing values per cluster per variable.
        
        double distortion = Double.MAX_VALUE;
        int[] size = new int[k];
        double[][] centroids = new double[k][d];
//        int[] y = seed(data, k, ClusteringDistance.EUCLIDEAN_MISSING_VALUES);

int np = MulticoreExecutor.getThreadPoolSize();
List<LloydThread> tasks = null;
if (n >= 1000 && np >= 2) {
    tasks = new ArrayList<>(np + 1);
    int step = n / np;
    if (step < 100) {
        step = 100;
    }
    
    int start = 0;
    int end = step;
    for (int i = 0; i < np-1; i++) {
//                boolean /*add*/ = tasks.add(new LloydThread(data, centroids, y, start, end));
start += step;
end += step;
    }
    boolean add;
//            add = tasks.add(new LloydThread(data, centroids, y, start, n));
}
        int m = 0;

for (int iter = 0; iter < maxIter; iter++) {
    Arrays.fill(size, 0);
    for (int i = 0; i < k; i++) {
        Arrays.fill(centroids[i], 0);
        Arrays.fill(nd[i], 0);
    }
    
    for (int i = 0; i < n; i++) {
        //               int m = y[i];
        size[m]++;
        for (int j = 0; j < d; j++) {
            if (!Double.isNaN(data[i][j])) {
                centroids[m][j] += data[i][j];
                nd[m][j]++;
            }
        }
    }
    
    for (int i = 0; i < k; i++) {
        for (int j = 0; j < d; j++) {
            centroids[i][j] /= nd[i][j];
        }
    }
    
    double wcss = Double.NaN;
    if (tasks != null) {
        try {
            wcss = 0.0;
            for (double ss : MulticoreExecutor.run(tasks)) {
                wcss += ss;
            }
        } catch (Exception ex) {
            //              logger.error("Failed to run K-Means on multi-core", ex);
            
            wcss = Double.NaN;
        }
    }
    
    if (Double.isNaN(wcss)) {
        wcss = 0.0;
        for (int i = 0; i < n; i++) {
            double nearest = Double.MAX_VALUE;
            for (int j = 0; j < k; j++) {
                double dist = squaredDistance(data[i], centroids[j]);
                if (nearest > dist) {
                    //y[i] = j;
                    nearest = dist;
                }
            }
            wcss += nearest;
        }
    }
    
    if (distortion <= wcss) {
        break;
    } else {
        distortion = wcss;
    }
}

// In case of early stop, we should recalculate centroids and clusterSize.
Arrays.fill(size, 0);
for (int i = 0; i < k; i++) {
    Arrays.fill(centroids[i], 0);
    Arrays.fill(nd[i], 0);
}

for (int i = 0; i < n; i++) {
//            int m = y[i];
size[m]++;
for (int j = 0; j < d; j++) {
    if (!Double.isNaN(data[i][j])) {
        centroids[m][j] += data[i][j];
        nd[m][j]++;
    }
}
}

for (int i = 0; i < k; i++) {
    for (int j = 0; j < d; j++) {
        centroids[i][j] /= nd[i][j];
    }
}

KMeans kmeans = new KMeans();
kmeans.k = k;
kmeans.distortion = distortion;
kmeans.size = size;
kmeans.centroids = centroids;
//       kmeans.y = y;

return kmeans;
    }
    
    /**
     * The implementation of Lloyd algorithm as a benchmark. Run the algorithm
     * multiple times and return the best one in terms of smallest distortion.
     * The data may contain missing values (i.e. Double.NaN).
     * @param data the input data of which each row is a sample.
     * @param k the number of clusters.
     * @param maxIter the maximum number of iterations for each running.
     * @param runs the number of runs of K-Means algorithm.
     */
    public static KMeans lloyd(double[][] data, int k, int maxIter, int runs) {
        if (k < 2) {
            throw new IllegalArgumentException("Invalid number of clusters: " + k);
        }
        
        if (maxIter <= 0) {
            throw new IllegalArgumentException("Invalid maximum number of iterations: " + maxIter);
        }
        
        if (runs <= 0) {
            throw new IllegalArgumentException("Invalid number of runs: " + runs);
        }
        
        KMeans best = lloyd(data, k, maxIter);
        
        for (int i = 1; i < runs; i++) {
            KMeans kmeans = lloyd(data, k, maxIter);
            if (kmeans.distortion < best.distortion) {
                best = kmeans;
            }
        }
        
        return best;
    }
    
    /**
     * Adapter for running Lloyd algorithm in thread pool.
     */
    static class LloydThread implements Callable<Double> {
        
        /**
         * The start index of data portion for this task.
         */
        final int start;
        /**
         * The end index of data portion for this task.
         */
        final int end;
        final double[][] data;
        final int k;
        final double[][] centroids;
        int[] y;
        
        LloydThread(double[][] data, double[][] centroids, int[] y, int start, int end) {
            this.data = data;
            this.k = centroids.length;
            this.y = y;
            this.centroids = centroids;
            this.start = start;
            this.end = end;
        }
        
        @Override
        public Double call() {
            double wcss = 0.0;
            for (int i = start; i < end; i++) {
                double nearest = Double.MAX_VALUE;
                for (int j = 0; j < k; j++) {
                    double dist = squaredDistance(data[i], centroids[j]);
                    if (nearest > dist) {
                        y[i] = j;
                        nearest = dist;
                    }
                }
                wcss += nearest;
            }
            
            return wcss;
        }
        
        private double squaredDistance(double[] d, double[] centroid) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(String.format("K-Means distortion: %.5f%n", distortion));
        sb.append(String.format("Clusters of %d data points of dimension %d:%n", y.length, centroids[0].length));
        for (int i = 0; i < k; i++) {
            int r = (int) Math.round(1000.0 * size[i] / y.length);
            sb.append(String.format("%3d\t%5d (%2d.%1d%%)%n", i, size[i], r / 10, r % 10));
        }
        
        return sb.toString();
    }
    
    private static class BBDTree {
        
        public BBDTree(double[][] data) {
        }
        
        //       private BBDTree(double[][] data) {
//            throw new BBDTree("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        private double clustering(double[][] centroids, double[][] sums, int[] size, int[] y) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    private KMeans(double[][] data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private double clustering(double[][] centroids, double[][] sums, int[] size, int[] y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

class MulticoreExecutor {
    
    static double[] run(List<LloydThread> tasks) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    static int getThreadPoolSize() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public MulticoreExecutor() {
    }
}
//</editor-fold>
