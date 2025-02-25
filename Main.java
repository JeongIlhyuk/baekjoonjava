import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

class SegmentTree{
    private final int MOD = 1000000007;
    private final int n;
    private int[] tree;
    private final int[] arr;

    public SegmentTree(final int[] arr){
        this.arr = arr;
        n = arr.length;
        tree = new int[4*n];
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

        tree[node] = (tree[2*node]%MOD)*(tree[2*node+1]%MOD)%MOD;
    }
    public int query(final int left,final int right){
        return query(left,right,1,0,n-1);
    }
    private int query(final int left,final int right,final int node, int start,int end){
        if(right<start||end<left)return 1;
        if(left<=start&&end<=right)return tree[node];

        final int mid= (start+end)/2;
        final int leftResult = query(left,right,2*node,start,mid);
        final int rightResult = query(left,right,2*node+1,mid+1,end);
        
        return (leftResult%MOD)*(rightResult%MOD)%MOD;
    }
    public void update(final int idx,final int val){
        arr[idx]=val;
        update(idx,1,0,n-1);
    }
    private void update(final int idx,int node,int start,int end){
        if(start == end){
            tree[node] = arr[start];
            return;
        }

        final int mid= (start+end)/2;
        if(idx<=mid)update(idx,2*node,start,mid);
        else update(idx,2*node+1,mid+1,end);
        tree[node] = (tree[2*node]%MOD)*(tree[2*node+1]%MOD)%MOD;
    }
}


public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        final int n = Integer.parseInt(st.nextToken());
        final int m = Integer.parseInt(st.nextToken());
        final int k = Integer.parseInt(st.nextToken());

        int[] sequence = new int[n];
        for(int i=0;i<n;i++)
            sequence[i] = Integer.parseInt(br.readLine());
        SegmentTree tree = new SegmentTree(sequence);
        
        for(int i=0;i<m+k;i++){
            st = new StringTokenizer(br.readLine());
            final int a = Integer.parseInt(st.nextToken());
            final int b = Integer.parseInt(st.nextToken());
            final int c = Integer.parseInt(st.nextToken());
            if(a==1)
                tree.update(b-1,c);
            else
                sb.append(tree.query(b-1,c-1)).append('\n');
        }

        System.out.print(sb);
        return;
    }
}