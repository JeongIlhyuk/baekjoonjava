import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

class SegmentTree{
    private int[] tree;
    private final int[] arr;

    public SegmentTree(final int[] arr){
        this.arr = arr;
        tree = new int[4*arr.length];
        build(1,0,arr.length-1);
    }

    private void build(final int node,final int start,final int end){
        if(start==end){
            tree[node]=arr[start];
            return;
        }
        final int mid = (start+end)/2;
        build(2*node,start,mid);
        build(2*node+1,mid+1,end);

        tree[node] = tree[2*node]*tree[2*node+1];
    }
    public int query(final int left,final int right){
        return query(left,right,1,0,arr.length-1);
    }
    private int query(final int left,final int right, final int node, int start, int end){
        if(right<start||end<left)return 1;
        if(left<=start&&end<=right)return tree[node];

        final int mid = (start+end)/2;
        final int leftResult = query(left,right,2*node,start,mid);
        final int rightResult = query(left,right,2*node+1,mid+1,end);
        
        return leftResult*rightResult;
    }

    public void update(final int idx,final int val){
        arr[idx] = val;
        update(idx,1,0,arr.length-1);
    }
    private void update(final int idx, final int node, int start,int end){
        if(start==end){
            tree[node]=arr[start];
            return;
        }
        final int mid = (start+end)/2;
        if(idx<=mid)
            update(idx,2*node,start,mid);
        else
            update(idx,2*node+1,mid+1,end);

        tree[node] = tree[2*node]*tree[2*node+1];
    }
}


public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        String line;
        while((line = br.readLine())!=null){
            st = new StringTokenizer(line);
            final int n = Integer.parseInt(st.nextToken());
            final int k = Integer.parseInt(st.nextToken());

            int[] sequence = new int[n];

            st = new StringTokenizer(br.readLine());
            for(int i=0;i<n;i++){
                final int num = Integer.parseInt(st.nextToken());
                if(num>0)
                    sequence[i] = 1;
                else if(num<0)
                    sequence[i] = -1;
                else
                    sequence[i] = 0;
            }

            SegmentTree tree = new SegmentTree(sequence);

            for(int i=0;i<k;i++){
                st = new StringTokenizer(br.readLine());
                final String cmd = st.nextToken();
                final int a = Integer.parseInt(st.nextToken());
                final int b = Integer.parseInt(st.nextToken());
                if(cmd.equals("C")){
                    if(b>0)
                        tree.update(a-1,1);
                    else if(b<0)
                        tree.update(a-1,-1);
                    else
                        tree.update(a-1,0);
                }else{
                    final int result = tree.query(a-1,b-1);
                    if(result>0)
                        sb.append('+');
                    else if(result<0)
                        sb.append('-');
                    else
                        sb.append(0);
                }
            }
            sb.append('\n');
        }

        System.out.print(sb);
        return;
    }
}