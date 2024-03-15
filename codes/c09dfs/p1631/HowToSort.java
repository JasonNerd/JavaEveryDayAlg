package codes.c09dfs.p1631;

public class HowToSort {
    
}
/*
class Edge implements Comparable<Edge>{
    public int start;
    public int end;
    public double weight;

    public Edge(int start, int end, double weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
    @Override
    public int compareTo(Edge o) {
        return (int)(this.weight - o.weight);
    }
}
Collections.sort(arr_lst); // 这里的arr_lst是ArrayList<Edge>对象

 */
/*
Collections.sort(arr_lst, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return (int)(o1.weight-o2.weight);
            }
        });

 */
/*
arr_lst.sort((Edge o1, Edge o2) -> (int) (o1.weight - o2.weight));

 */