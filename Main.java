import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

class MinSegmentTree{
    private final int INF = Integer.MAX_VALUE;
    private int n;
    private int[] tree;
    private final int[] arr;

    public MinSegmentTree(final int[] arr){
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

        tree[node] = Math.min(tree[2*node],tree[2*node+1]);
    }
    public int query(final int left,final int right){
        return query(left,right,1,0,n-1);
    }
    private int query(final int left,final int right,final int node, int start,int end){
        if(right<start||end<left)
            return INF;
        if(left<=start&&end<=right)
            return tree[node];

        final int mid= (start+end)/2;
        final int leftResult = query(left,right,2*node,start,mid);
        final int rightResult = query(left,right,2*node+1,mid+1,end);
        
        return Math.min(leftResult,rightResult);
    }
}

    class MaxSegmentTree{
        private final int INF = Integer.MAX_VALUE;
        private int n;
        private int[] tree;
        private final int[] arr;
    
        public MaxSegmentTree(final int[] arr){
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
    
            tree[node] = Math.max(tree[2*node],tree[2*node+1]);
        }
        public int query(final int left,final int right){
            return query(left,right,1,0,n-1);
        }
        private int query(final int left,final int right,final int node, int start,int end){
            if(right<start||end<left)
                return -INF;
            if(left<=start&&end<=right)
                return tree[node];
    
            final int mid= (start+end)/2;
            final int leftResult = query(left,right,2*node,start,mid);
            final int rightResult = query(left,right,2*node+1,mid+1,end);
            
            return Math.max(leftResult,rightResult);
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
        int[] sequence = new int[n];
        
        for(int i=0;i<n;i++){
            sequence[i] = Integer.parseInt(br.readLine());
        }
        MinSegmentTree minTree = new MinSegmentTree(sequence);
        MaxSegmentTree maxTree = new MaxSegmentTree(sequence);
        
        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            final int b = Integer.parseInt(st.nextToken());
            final int c = Integer.parseInt(st.nextToken());
            sb.append(minTree.query(b-1,c-1)).append(' ').append(maxTree.query(b-1,c-1)).append('\n');
        }

        System.out.print(sb);
        return;
    }
}