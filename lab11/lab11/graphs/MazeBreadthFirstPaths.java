package lab11.graphs;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private Maze maze;
    private boolean targetFound = false;
    private int s;
    private int t;


    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        marked[s] = true;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs(int v) {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        Queue<Integer> q = new PriorityQueue<Integer>();
        marked[v] = true;
        announce();
        q.add(v);

        if (v == t)
            targetFound = true;

        if (targetFound == true)
            return;

        while (!q.isEmpty()) {
            int j = q.remove();
            for (int w : maze.adj(j)) {
                if (!marked[w]) {
                    marked[w] = true;
                    edgeTo[j] = w;
                    announce();
                    distTo[w] = distTo[j] + 1;
                    q.add(w);
                    if (targetFound) {
                        return;
                    }
                }

            }
        }
    }


    @Override
    public void solve() {
        // bfs();
        bfs(s);
    }
}

