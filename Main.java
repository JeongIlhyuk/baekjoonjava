import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

class SegmentTree{
    int[] tree;
    public SegmentTree(final int[] arr,final int n){
        tree = new int[4*n];
        build(arr,1,0,n-1);
    }

    private void build(final int[] arr,final int node,final int start,final int end){
        if(start==end){
            tree[node]=arr[start];
        }
        final int mid = (start+end)/2;
        build(arr,2*node,start,mid);
        build(arr,2*node+1,mid+1,end);
    }
}

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int[][] dp = new int[2001][2001];
        
        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n+1];
        
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++)
            nums[i] = Integer.parseInt(st.nextToken());
        
        for(int i=1;i<=n;i++)
            dp[i][i] = 1;
        
        for(int i=1;i<=n-1;i++)
            if(nums[i]==nums[i+1]){
                dp[i][i+1] = 1;
            }

        for(int i=2;i<n;i++){
            for(int j=1;j<=n-i;j++){
                if(nums[j]==nums[j+i]&&dp[j+1][j+i-1]==1)
                    dp[j][j+i]=1;
            }
        }

        int m = Integer.parseInt(br.readLine());
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            sb.append(dp[s][e]).append('\n');
        }

        System.out.print(sb);

        return;
    }
}