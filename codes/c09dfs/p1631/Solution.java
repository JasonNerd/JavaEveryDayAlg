package codes.c09dfs.p1631;
//https://books.halfrost.com/leetcode/ChapterFour/1600~1699/1631.Path-With-Minimum-Effort/
//https://books.halfrost.com/leetcode/ChapterFour/1300~1399/1319.Number-of-Operations-to-Make-Network-Connected/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solution {
    public static int[][] testCase(int i){
        if (i==1){
            return new int[][]{
                {1,2,2}, 
                {3,8,2}, 
                {5,3,5}
            };
        }else if (i==2){
            return new int[][]{
                {1,2,1,1,1}, 
                {1,2,1,2,1}, 
                {1,2,1,2,1},
                {1,2,1,2,1},
                {1,1,1,2,1}
            };
        }else if (i==3){
            return new int[][]{
                {1,2,3}, 
                {3,8,4}, 
                {5,3,5}
            };
        }else if (i==4){
            return new int[][]{
                {1,10,6,7,9,10,4,9}
            };
        }else{
            return new int[][]{{1,1000000}};
        }
    }

    static int p = 0;
    public int minimumEffortPath(int[][] heights) {
        int rows = heights.length, columns = heights[0].length;
        int posX=0, posY=0, tCost=0;
        int[][] trace = new int[rows][columns];
        trace[posX][posY]=1;
        int[] cost = {Integer.MAX_VALUE};   // 最终结果
        dfs(posX, posY, heights, trace, cost, tCost);
        return cost[0];
    }
    public static void dfs(int x, int y, int[][]heights, int[][] trace, int[] cost, int tCost){
        if (x==heights.length-1 && y==heights[0].length-1){
            if (tCost <= cost[0])
                cost[0] = tCost;
            ++p;
            return;
        }
        List<Position> positions = choose(x, y, trace, heights);
        for (Position pos: positions){
            int nx=pos.x, ny=pos.y;
            if(check(nx, ny, trace)){
                trace[nx][ny] = 1;
                int currentCost = heights[nx][ny] - heights[x][y], olderCost = tCost;
                currentCost = currentCost > 0 ? currentCost : -currentCost;
                tCost = currentCost > olderCost ? currentCost : olderCost;
                if (tCost <= cost[0])      // cut branch
                    dfs(nx, ny, heights, trace, cost, tCost);
                trace[nx][ny] = 0;      // recover the trace
                tCost = olderCost;      // recover the cost
            }
        }
    }
    static class Position{
        int x, y;
        Position(int x, int y){this.x=x; this.y=y;}
    }
    public static List<Position> choose(int x, int y, int[][] trace, int[][]heights){
        // 选择可以走的方向, 并且对方向排序
        int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}};     // 右, 下, 左, 上
        int nDir = directions.length;
        List<Position> positions = new ArrayList<>();
        for (int i=0; i<nDir; i++){
            int nx = x+directions[i][0], ny = y+directions[i][1];
            if(check(nx, ny, trace))
                positions.add(new Position(nx, ny));
        }
        Collections.sort(positions, new Comparator<Position>() {
            @Override
            public int compare(Position p1, Position p2){
                int p1h = heights[p1.x][p1.y] - heights[x][y];
                p1h = p1h > 0 ? p1h : -p1h;
                int p2h = heights[p2.x][p2.y] - heights[x][y];
                p2h = p2h > 0 ? p2h : -p2h;
                return p1h-p2h;
            }
        });
        return positions;
    }
    public static boolean check(int x, int y, int[][] trace){
        int rows = trace.length, columns = trace[0].length;
        if(x<0 || x>=rows) return false;
        if(y<0 || y>=columns) return false;
        if(trace[x][y]==1) return false;
        return true;
    }

    public static void main(String[] args) {
        int [][] heights = testCase(2); // 1, 2, 3
        System.out.println(new Solution().minimumEffortPath(heights));
        System.out.println(p);
    }

}
/**
1. dfs 探路走迷宫
2. 需要一个等大的矩阵记录走过的位置
3. 每当遍历到右下角, 就计算路径上最值之差
4. 一个优化考虑是: 我们不去保存完整路径, 而是动态的更新当前路径中的最大最小值(tMax, tMin)
5. 计算花费 tCost, 一旦花费高于已知的花费cost, 可以立即回头(剪枝策略), 否则直到遍历到右下角, 更新cost
6. 如果可能的话还可以加入启发式的搜索策略, 例如我们总是优先走[tMin, tMax]之间的方向
7. (尽管不一定全局最优, 但直观理解上或许可以更多的剪枝?)

考虑出了很大问题:
1. 题目要求的是相邻的最小值, 因此不需要(tMax, tMin), 但 tCost 可以保留, 含义变为相邻元素的差值绝对值最大值
2. 动态更新时会出现这样的问题, 当一条路径探索完毕后, 返回上一节点并探索另一个方向时, tCost 也是需要更新的, 而且不仅仅是
直接重置为0.
3. 考虑到使用相邻差值绝对值即可剪枝, 一种方法是保留path, cost 最后单独计算
4. 然而, 实际上, 我们确实保留了在进行下一节点探索前的cost值, 所以返回时更新会=回旧值即可.
*/
/**
你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col] 表示格子 (row, col) 的高度。
一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。
你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。
请你返回从左上角走到右下角的最小 体力消耗值 。

输入：heights = [[1,2,2],[3,8,2],[5,3,5]]
输出：2
解释：路径 [1,3,5,3,5] 连续格子的差值绝对值最大为 2 。
这条路径比路径 [1,2,2,2,5] 更优，因为另一条路径差值最大值为 3 。

输入：heights = [[1,2,3],[3,8,4],[5,3,5]]
输出：1
解释：路径 [1,2,3,4,5] 的相邻格子差值绝对值最大为 1 ，比路径 [1,3,5,3,5] 更优。

输入：heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
输出：0
解释：上图所示路径不需要消耗任何体力。

rows == heights.length
columns == heights[i].length
1 <= rows, columns <= 100
1 <= heights[i][j] <= 106
 */

// 问题在于, 似乎利用 tCost 剪枝优化很小
// 必须使用 优化的方向选择: 尽可能走与当前高度差不多的地板.