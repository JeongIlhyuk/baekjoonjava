import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

class Edge{
    private final int start;
    private final int end;
    private final int time;
    public Edge(int start,int end,int time){
        this.start = start;
        this.end = end;
        this.time = time;
    }
    public int getStart(){
        return start;
    }
    public int getEnd(){
        return end;
    }
    public int getTime(){
        return time;
    }
}

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        final int INF = Integer.MAX_VALUE;

        final int tc = Integer.parseInt(br.readLine());
        for(int i=0;i<tc;i++){
            st = new StringTokenizer(br.readLine());
            final int n = Integer.parseInt(st.nextToken());
            final int m = Integer.parseInt(st.nextToken());
            final int w = Integer.parseInt(st.nextToken());

            int[] distance = new int[n];
            final Edge[] edgeArr = new Edge[2*m+w];

            // Arrays.fill(distance,INF);
            distance[0] = 0;

            for(int j=0;j<2*m;j+=2){
                st = new StringTokenizer(br.readLine());
                final int s = Integer.parseInt(st.nextToken());
                final int e = Integer.parseInt(st.nextToken());
                final int t = Integer.parseInt(st.nextToken());
                edgeArr[j] = new Edge(s,e,t);
                edgeArr[j+1] = new Edge(e,s,t);
            }

            for(int j=2*m;j<2*m+w;j++){
                st = new StringTokenizer(br.readLine());
                final int s = Integer.parseInt(st.nextToken());
                final int e = Integer.parseInt(st.nextToken());
                final int t = -Integer.parseInt(st.nextToken());
                edgeArr[j] = new Edge(s,e,t);
            }

            for(int j=0;j<n-1;j++){
                for(final Edge edge:edgeArr){
                    final int s = edge.getStart();
                    final int e = edge.getEnd();
                    final int t = edge.getTime();
    
                    if(distance[s-1]!=INF &&distance[s-1]+t<distance[e-1]){
                        distance[e-1]=distance[s-1]+t;
                    }
                }
            }

            boolean tag = false;
            for(final Edge edge:edgeArr){
                final int s = edge.getStart();
                final int e = edge.getEnd();
                final int t = edge.getTime();

                if(distance[s-1]!=INF &&distance[s-1]+t<distance[e-1]){
                    sb.append("YES\n");
                    tag = true;
                    break;
                }
            }
            if(!tag)sb.append("NO\n");
        }

        System.out.print(sb);
        return;
    }
}