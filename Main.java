import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main{
    static StringBuilder sb = new StringBuilder();
    static int n;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int INF = 100000;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        final int m = Integer.parseInt(st.nextToken());

        int[] distance = new int[n];
        final int[][] edgeArr = new int[m][3];
        int[] route = new int[n+1];
        boolean[] isCycle = new boolean[n];

        Arrays.fill(distance,-INF);
        distance[0]=0;

        for(int j=0;j<m;j++){
            st = new StringTokenizer(br.readLine());
            edgeArr[j][0] = Integer.parseInt(st.nextToken());
            edgeArr[j][1] = Integer.parseInt(st.nextToken());
            edgeArr[j][2] = Integer.parseInt(st.nextToken());
        }

        for(int j=0;j<n-1;j++){
            for(final int[] edge:edgeArr){
                final int s = edge[0];
                final int e = edge[1];
                final int t = edge[2];

                if(distance[s-1]!=-INF &&distance[s-1]+t>distance[e-1]){
                    distance[e-1]=distance[s-1]+t;
                    route[e]=s;
                }
            }
        }
        
        for(final int[] edge:edgeArr){
            final int s = edge[0];
            final int e = edge[1];
            final int t = edge[2];

            if(distance[s-1]!=-INF &&distance[s-1]+t>distance[e-1]){
                isCycle[s-1]=true;
            }
        }
        
        for(int i=0;i<n;i++)
            if(isCycle[i]){
                boolean[] visited = new boolean[n];
                dfs(visited,edgeArr,i);
            }

        printResult(route,n);

        System.out.println(sb);
        return;
    }
    static void printResult(final int[] route,final int idx){
        if(idx==1){
            sb.append("1 ");
            return;
        }
        printResult(route,route[idx]);
        sb.append(idx).append(' ');
    }
    static void dfs(boolean[] visited,final int[][] edgeArr,final int vertex){
        visited[vertex]=true;
        for(final int[] edge:edgeArr){
            if(edge[0]-1==vertex&&!visited[edge[1]-1]){
                if(edge[1]==n){
                    System.out.println(-1);
                    System.exit(0);
                }
                dfs(visited,edgeArr,edge[1]-1);
            }
        }
        return;
    }
}