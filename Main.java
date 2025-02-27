import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.StringTokenizer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;

import java.util.Queue;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    public static void main(String[] args)throws IOException{
        final int INF = Integer.MAX_VALUE;
        final int n = Integer.parseInt(br.readLine());
        final int m = Integer.parseInt(br.readLine());
        final int[][] graph = new int[n][n];
        final int[] dp = new int[n];
        final int[] dp2 = new int[n];

        for(int i=0;i<n;i++)
            Arrays.fill(graph[i],INF);
        for(int i=0;i<n;i++)
            graph[i][i]=0;
        Arrays.fill(dp,INF);

        int[] indegree = new int[n];
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            final int s = Integer.parseInt(st.nextToken());
            final int e = Integer.parseInt(st.nextToken());
            final int t = Integer.parseInt(st.nextToken());
            graph[s-1][e-1]=t;
            dp[e-1]=t;
            indegree[e-1]++;
        }

        st = new StringTokenizer(br.readLine());
        final int start = Integer.parseInt(st.nextToken());
        dp[start-1]=0;
        final int end = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new LinkedList<>();
        for(int i=0;i<n;i++)
            if(indegree[i]==0){
                queue.offer(i);
            }

        while(!queue.isEmpty()){
            final int curr = queue.poll();
            if(curr==start-1)dp2[curr]=1;
            for(int i=0;i<n;i++)
                if(graph[curr][i]<INF && curr!=i){
                    if(--indegree[i] ==0)queue.offer(i);
                    if(dp[curr]<INF){
                        if(dp[i]==INF){
                            dp[i]=dp[curr]+graph[curr][i];
                            dp2[i]+=dp2[curr];
                        }else{
                            if(dp[i]<dp[curr]+graph[curr][i]){
                                dp[i]=dp[curr]+graph[curr][i];
                                dp2[i]=dp2[curr];
                            }else if(dp[i]==dp[curr]+graph[curr][i]){
                                dp2[i]+=dp2[curr];
                            }
                        }
                    }
                }
        }

        sb.append(dp[end-1]+"\n"+dp2[end-1]+"\n");

        System.out.println(sb);
        return;
    }
}