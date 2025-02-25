import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

class SegmentTree{
    private int n;
    private long[] tree;
    private final int[] arr;

    public SegmentTree(final int[] arr){
        this.arr = arr;
        n = arr.length;
        tree = new long[4*n];
        build(1,0,n-1);
    }

    private void build(final int node,final int start,final int end){
        if(start==end){
            tree[node]=arr[start];
            return;
        }
        final int mid = (start+end)/2;
        build(2*node,start,mid);
        build(2*node+1,mid+1,end);

        tree[node] = tree[2*node]+tree[2*node+1];
    }
    public long query(final int left,final int right){
        return query(left,right,1,0,n-1);
    }
    private long query(final int left,final int right,final int node, int start,int end){
        if(right<start||end<left)
            return 0;
        if(left<=start&&end<=right)
            return tree[node];

        final int mid= (start+end)/2;
        final long leftSum = query(left,right,2*node,start,mid);
        final long rightSum = query(left,right,2*node+1,mid+1,end);
        
        return leftSum + rightSum;
    }

    public void update(final int idx,final int val){
        arr[idx] = val;
        update(idx,1,0,n-1);
    }
    private void update(final int idx,int node, int start,int end){
        if(start==end){
            tree[node]=arr[start];
            return;
        }
        final int mid = (start+end)/2;
        if(idx<=mid)
            update(idx,2*node,start,mid);
        else
            update(idx,2*node+1,mid+1,end);
        
        tree[node] = tree[2*node+1]+tree[2*node];
    }
}


public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        final int n = Integer.parseInt(st.nextToken());
        final int q = Integer.parseInt(st.nextToken());
        int[] sequence = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        SegmentTree tree = new SegmentTree(sequence);

        for(int i=0;i<q;i++){
            st = new StringTokenizer(br.readLine());
            final int x = Integer.parseInt(st.nextToken());
            final int y = Integer.parseInt(st.nextToken());
            final int a = Integer.parseInt(st.nextToken());
            final int b = Integer.parseInt(st.nextToken());
            sb.append(tree.query(x-1,y-1)).append('\n');
            tree.update(a-1,b);
        }

        System.out.print(sb);
        return;
    }
}