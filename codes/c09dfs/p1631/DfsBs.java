package codes.c09dfs.p1631;

public class DfsBs {
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public boolean hasPath(int[][] heights, int[][] trace, int i, int j, int mid){
        int m = heights.length, n = heights[0].length;
        if(i==m-1 && j==n-1)
            return true;
        boolean res = false;
        trace[i][j]=1;
        for(int[] d: dirs){
            int ni = i+d[0], nj = j+d[1];
            if (ni<0 || ni>=m || nj<0 || nj>=n || trace[ni][nj]==1 || res)
                continue;
            int dif = heights[i][j] - heights[ni][nj];
            dif = dif>0?dif:-dif;
            if (dif <= mid && hasPath(heights, trace, ni, nj, mid))
                res = true;
        }
        return res;
    }
    public int minimumEffortPath(int[][] heights) {
        int rows = heights.length, columns = heights[0].length;
        int left = 0, right = 1000000-1, ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            int[][] trace = new int[rows][columns];
            trace[0][0]=1;
            if (hasPath(heights, trace, 0, 0, mid)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int [][] heights = Solution.testCase(5); // 1, 2, 3, 4
        System.out.println(new DfsBs().minimumEffortPath(heights));
    }
}
