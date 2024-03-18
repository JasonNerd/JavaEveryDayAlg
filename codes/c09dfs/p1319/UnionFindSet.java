package codes.c09dfs.p1319;

import java.util.Arrays;

public class UnionFindSet {
    int[] parent;
    int[] size;
    int n;
    int setCnt;
    
    public UnionFindSet(int n){
        this.n = n;
        this.setCnt = n;
        this.parent = new int[n];
        this.size = new int[n];
        Arrays.fill(size, 1);
        for (int i=0; i<n; i++)
            parent[i] = i;
    }

    public boolean union(int x, int y){
        int rx = find(x), ry = find(y);
        if (rx == ry) return false; // 同一个集合
        int rt0 = rx > ry ? rx : ry;
        int rt1 = rx > ry ? ry : rx;
        parent[rt0] = rt1;
        size[rt1] += size[rt0];
        setCnt--;
        return true;
    }

    public int find(int x){
        return x == parent[x] ? x : (parent[x] = find(parent[x]));
    }
}
