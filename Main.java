import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;


public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    public static void main(String[] args)throws IOException{

        st = new StringTokenizer(br.readLine());
        final int n = Integer.parseInt(st.nextToken());
        final int m = Integer.parseInt(st.nextToken());

        final long[] sum = new long[n+1];
        final long[] remain = new long[m];

        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=n;i++){
            sum[i] = sum[i-1]+Integer.parseInt(st.nextToken());
            final int idx = (int)(sum[i]%m);
            remain[idx]++;
        }

        long cnt = remain[0];
        for(int i=0;i<m;i++){
            cnt+=remain[i]*(remain[i]-1)/2;
        }

        sb.append(cnt);

        System.out.println(sb);
        return;
    }
}