import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;


public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static final int INF = Integer.MAX_VALUE;
    static int end;

    public static void main(String[] args)throws IOException{

        st = new StringTokenizer(br.readLine());
        final int n = Integer.parseInt(st.nextToken());
        final int start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        final int m = Integer.parseInt(st.nextToken());

        final long[] dist = new long[n];
        final long[] profit = new long[n];
        final Edge[] edges = new Edge[m];
        final boolean[] isCycle = new boolean[n];
        

        Arrays.fill(dist,-INF);
        
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            final int s = Integer.parseInt(st.nextToken());
            final int e = Integer.parseInt(st.nextToken());
            final int p = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(s,e,p);
        }
        
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++)
        profit[i]=Integer.parseInt(st.nextToken());
        dist[start]=profit[start];
        
        for(int i=0;i<n-1;i++){
            for(final var edge:edges){
                final int s = edge.s;
                final int e = edge.e;
                final long p = edge.p;
                if(dist[s]!=-INF && dist[s]-p+profit[e]>dist[e]){
                    dist[e] = dist[s]-p+profit[e];
                }
            }
        }
        if(dist[end]==-INF){
            System.out.println("gg");
            System.exit(0);
        }

        for(final var edge:edges){
            final int s = edge.s;
            final int e = edge.e;
            final long p = edge.p;
            if(dist[s]!=-INF &&dist[s]-p+profit[e]>dist[e]){
                isCycle[s]=true;
            }
        }
        for(int i=0;i<n;i++){
            if(isCycle[i]){
                boolean[] vist = new boolean[n];
                dfs(vist,edges,i);
            }
        }

        sb.append(dist[end]);

        System.out.println(sb);
        return;
    }
    static class Edge{
        final int s,e,p;
        public Edge(int s,int e,int p){
            this.s=s;
            this.e=e;
            this.p=p;
        }
    }
    static void dfs(boolean[] vist,Edge[] edges,final int v){
        vist[v] = true;
        if(v==end){
            System.out.println("Gee");
            System.exit(0);
        }
        for(final var edge:edges){
            if(edge.s==v && !vist[edge.e]){
                dfs(vist,edges,edge.e);
            }
        }
    }
}