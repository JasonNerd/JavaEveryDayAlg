package codes.c09dfs.p1631;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Original {
    static int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}};     // 右, 下, 左, 上

    // static List<Integer> path = new ArrayList<>();    // debug
    // static int p = 0;
    
    public int minimumEffortPath(int[][] heights) {
        int rows = heights.length, columns = heights[0].length;
        int posX=0, posY=0, tCost=0;
        int[][] trace = new int[rows][columns];
        trace[posX][posY]=1;
        // path.add(heights[posX][posY]);    // debug
        int[] cost = {Integer.MAX_VALUE};   // 最终结果
        dfs(posX, posY, heights, trace, cost, tCost);
        return cost[0];
    }
    public static void dfs(int x, int y, int[][]heights, int[][] trace, int[] cost, int tCost){
        if (x==heights.length-1 && y==heights[0].length-1){
            if (tCost <= cost[0])
                cost[0] = tCost;
            // System.out.println(path);    // debug
            // ++p;
            return;
        }
        int nDir = directions.length;
        for (int i=0; i<nDir; i++){
            int nx=x+directions[i][0], ny=y+directions[i][1];
            if(check(nx, ny, trace)){
                trace[nx][ny] = 1;
                // path.add(heights[nx][ny]);    // debug
                int currentCost = heights[nx][ny] - heights[x][y], olderCost = tCost;
                currentCost = currentCost > 0 ? currentCost : -currentCost;
                tCost = currentCost > olderCost ? currentCost : olderCost;
                if (tCost <= cost[0])      // cut branch
                    dfs(nx, ny, heights, trace, cost, tCost);
                trace[nx][ny] = 0;      // recover the trace
                tCost = olderCost;      // recover the cost
                // path.remove(path.size()-1);    // debug
            }
        }
    }
    public static boolean check(int x, int y, int[][] trace){
        int rows = trace.length, columns = trace[0].length;
        if(x<0 || x>=rows) return false;
        if(y<0 || y>=columns) return false;
        if(trace[x][y]==1) return false;
        return true;
    }
}
