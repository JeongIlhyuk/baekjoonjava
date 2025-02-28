import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.StringTokenizer;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.Stack;
import java.util.Queue;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static ArrayList<Integer>[] graph;
    public static void main(String[] args)throws IOException{
        //노드 수
        st = new StringTokenizer(br.readLine());
        final int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        //간선 정보
        graph=new ArrayList[n];
        for(int i=0;i<n;i++)
            graph[i] = new ArrayList<>();
        while(m-->0){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e= Integer.parseInt(st.nextToken());
            graph[s-1].add(e-1);
        }
        //ATM 현금 보유량
        final FindScc fs = new FindScc(n);
        for(int i=0;i<n;i++)
            fs.atm[i] = Integer.parseInt(br.readLine());
        //출발 장소,레스토랑 수
        st = new StringTokenizer(br.readLine());
        final int s= Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        //레스토랑 노드 정보
        st = new StringTokenizer(br.readLine());
        while(p-->0){
            final int num = Integer.parseInt(st.nextToken());
            fs.isRestaurant[num-1]=true;
        }

        fs.find();

        //위상 정렬
        
        System.out.println(sb);

        br.close();
        return;
    }
    static class FindScc{
        private final int n;
        private int leastId=0;
        
        private int sccNum = 0;

        private final int[] id;
        private final int[] lowest;
        private final boolean[] isStack;

        private final int[] sccId;
        
        private final int[] atm;
        private final boolean[] isRestaurant;
        
        private final Stack<Integer> stack = new Stack<>();
        
        private final ArrayList<ArrayList<Integer>> sccList=new ArrayList<>();
        private final ArrayList<Integer> sccAtmSum=new ArrayList<>();
        private final ArrayList<Boolean> hasRestaurant=new ArrayList<>();
        public FindScc(int n){
            this.n=n;
            lowest = new int[n];
            isStack=new boolean[n];
            id=new int[n];
            
            sccId=new int[n];
            atm = new int[n];
            isRestaurant = new boolean[n];
            Arrays.fill(id,-1);
        }
        private void dfs(final int v){
            id[v]=lowest[v]=leastId++;
            stack.push(v);
            isStack[v]=true;
            for(final int adjV:graph[v]){
                if(id[adjV]==-1){
                    dfs(adjV);
                    lowest[v]=Math.min(lowest[v],lowest[adjV]);
                }else if(isStack[adjV]){
                    lowest[v]=Math.min(lowest[v],id[adjV]);
                }
            }
    
            if(lowest[v]==id[v]){
                ArrayList<Integer> scc = new ArrayList<>();

                int s,GMoney=0;
                boolean isR = false;
                do{
                    s = stack.pop();
                    scc.add(s);
                    isStack[s]=false;

                    sccId[s]=sccNum;

                    GMoney += atm[s];
                    if(isRestaurant[s])isR = true;
                }while(s!=v);
                sccList.add(scc);

                hasRestaurant.add(isR);
                sccAtmSum.add(GMoney);
                sccNum++;
            }
        }
        public void find(){
            for(int i=0;i<n;i++)
                if(id[i]==-1)this.dfs(i);
        }
    }
}