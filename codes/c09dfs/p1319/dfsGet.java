package codes.c09dfs.p1319;

import java.util.ArrayList;
import java.util.List;

public class dfsGet {
    boolean[] vis;
    List<Integer>[] graph;

    public int makeConnected(int n, int[][] connections) {
        int m = connections.length;
        if (m < n-1) return -1;
        vis = new boolean[n];
        graph = new List[n];
        for(int i=0; i<n; i++)
            graph[i] = new ArrayList<Integer>();
        for(int[] c: connections){
            graph[c[0]].add(c[1]);
            graph[c[1]].add(c[0]);
        }
        int ans = 0;
        for (int i=0; i<n; i++){
            if (!vis[i]){
                dfs(i);
                ans++;
            }
        }
        return ans - 1;
    }

    public void dfs(int x){
        vis[x] = true;
        for(int y: graph[x]){
            if (!vis[y]){
                dfs(y);
            }
        }
    }
}
