package eg.edu.alexu.csd.filestructure.graphs;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;



class pair implements Comparable <pair>{

	int ID;
	int dis;
	public pair (int ID,int dis){
		this.ID=ID;
		this.dis=dis;
	}
	public int compareTo(pair h){
		if(dis>h.dis)return 1;
		else return -1;
	}
	
}	
	
public class ShortestPath implements IGraph {
	
	 int V,E;	
	 LinkedList<Point> edges[] ;
	PriorityQueue<pair> p; 
	ArrayList<Integer> getDijkstraProcessedOrder= new ArrayList<>();

	
	@Override
	public void readGraph(File file) {
		
		FileReader fr=null;
		BufferedReader br=null;
		try {
			fr= new FileReader(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		br=new BufferedReader(fr);
		Scanner sc=new Scanner(br);
		 V=sc.nextInt();E=sc.nextInt();
		 edges=new LinkedList[V];
		for(int i=0;i<V;i++)edges[i]=new LinkedList<>();
		while(sc.hasNextInt()){
			int from=sc.nextInt();
			int to=sc.nextInt();
			int weight=sc.nextInt();
			Point t=new Point(to,weight);//edges[i]= point(j ,w) there is edge from node i to node j with weight w
			edges[from].add(t);
		}
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return E;
	}

	@Override
	public ArrayList<Integer> getVertices() {
		ArrayList<Integer> Ve=new ArrayList<>();	
		for(int i=0;i<V;i++){
				Ve.add(i);
		}
		return Ve ;
	}

	@Override
	public ArrayList<Integer> getNeighbors(int v) {
		// TODO Auto-generated method stub
		ArrayList<Integer> Neighbors=new ArrayList<>();
		for(int i=0;i<edges[v].size();i++){
			Neighbors.add((int)edges[v].get(i).getX());
		}
		
		return Neighbors;
	}

	@Override
	public void runDijkstra(int src, int[] distances) {
	 p=new PriorityQueue<>();
		pair test=new pair(src,0);
		p.add(test);
		for(int i=0;i<V;i++){
			if(i==src)continue;
			test=new pair(i,Integer.MAX_VALUE);
			p.add( test);
		}
	
		while (!p.isEmpty()){
			pair cur=p.poll();
				getDijkstraProcessedOrder.add(cur.ID);
			distances[cur.ID]=cur.dis;
			
			for(int i=0;i<edges[cur.ID].size();i++){
				update(cur.ID,(int) edges[cur.ID].get(i).getX(),(int)edges[cur.ID].get(i).getY(),distances);
			}
		}
	}

	@Override
	public ArrayList<Integer> getDijkstraProcessedOrder() {
		
		return getDijkstraProcessedOrder;
	}

	@Override
	public boolean runBellmanFord(int src, int[] distances) {
		distances[src]=0;
		for(int i=0;i<V;i++){
			if(src==i)continue;
			distances[i]=Integer.MAX_VALUE;
		}
		for(int i=0;i<V-1;i++){
			for(int j=0;j<V;j++){
				for(int k=0;k<edges[j].size();k++){
					relax(j, (int)edges[j].get(k).getX(),(int)edges[j].get(k).getY(), distances);
				}
			}
		}
		
		for(int j=0;j<V;j++){
				for(int k=0;k<edges[j].size();k++){
					int old=distances[ (int)edges[j].get(k).getX()];
					relax(j, (int)edges[j].get(k).getX(),(int)edges[j].get(k).getY(), distances);
					if(distances[ (int)edges[j].get(k).getX()]<old)return true;
				}
			}
		
		return false;
	}
	
	public void update(int f,int t,int w,int[] distances){
		int newdis=distances[f]+w;
		Iterator<pair> itr=p.iterator();
		while(itr.hasNext()){
			pair test=itr.next();
			if(t==test.ID&&newdis<test.dis){
				test.ID=t;
				p.remove(test);
				test=new pair(t,newdis);
				p.add(test);break;
			}
			
		}
	}
	public void relax(int f,int t,int w,int []distances){
		if(distances[f]+w<distances[t])
			distances[t]=w+distances[f];
	}
}
