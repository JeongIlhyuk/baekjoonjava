import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

class SegmentTree{
    private final int INF = Integer.MAX_VALUE;
    private int n;
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
            tree[node]=start;
            return;
        }
        final int mid = (start+end)/2;
        build(2*node,start,mid);
        build(2*node+1,mid+1,end);

        if(arr[tree[2*node+1]]<arr[tree[2*node]])
            tree[node] = tree[2*node+1];
        else
            tree[node] = tree[2*node];
    }
    public int query(final int left,final int right){
        return query(left,right,1,0,n-1)+1;
    }
    private int query(final int left,final int right, final int node, int start, int end){
        if(right<start||end<left)return -1;
        if(left<=start&&end<=right)return tree[node];

        final int mid = (start+end)/2;
        final int leftResult = query(left,right,2*node,start,mid);
        final int leftVal = leftResult==-1?INF:arr[leftResult];
        final int rightResult = query(left,right,2*node+1,mid+1,end);
        final int righttVal = rightResult==-1?INF:arr[rightResult];
        
        return righttVal<leftVal?rightResult:leftResult;
    }

    public void update(final int idx,final int val){
        arr[idx] = val;
        update(idx,val,1,0,n-1);
    }
    private void update(final int idx,final int val,int node, int start,int end){
        if(start==end){
            tree[node]=start;
            return;
        }
        final int mid = (start+end)/2;
        if(idx<=mid)
            update(idx,val,2*node,start,mid);
        else
            update(idx,val,2*node+1,mid+1,end);

        if(arr[tree[2*node+1]]<arr[tree[2*node]])
            tree[node] = tree[2*node+1];
        else
            tree[node] = tree[2*node];
    }
}


public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        final int n = Integer.parseInt(br.readLine());
        int[] sequence = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        SegmentTree tree = new SegmentTree(sequence);

        final int m = Integer.parseInt(br.readLine());
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            final int cmd = Integer.parseInt(st.nextToken());
            final int a = Integer.parseInt(st.nextToken());
            final int b = Integer.parseInt(st.nextToken());
            if(cmd==1){
                tree.update(a-1,b);
            }else{
                sb.append(tree.query(a-1,b-1)).append('\n');
            }
        }

        System.out.print(sb);
        return;
    }
}