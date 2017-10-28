package train;

public class PriorityQueue {
	static int arr[]=new int[1000000];
	static int k=0;
	public static void Max_Heap_Insert (int val){
		arr[k++]=val;
		Heap.Buliding_max_heap(arr, k);
	}
	public static int  max(){
		if(k==0)return -1;
		return arr[0];
	}
	public static int Extract_Max(){
		if(k==0){System.out.println("the queue is already empty");return -1;}
		else {
			int max= arr[0];
			arr[0]=arr[k-1];
			arr[k-1]=max;
			k--;
			Heap.max_heapify(arr, 0, k);
			return max;
		}
	}
	public static void main(String[]args){
		Max_Heap_Insert(1);
		Max_Heap_Insert(4);
		Max_Heap_Insert(8);
		
	
	System.out.println(Extract_Max());	
	System.out.println(Extract_Max());	
	//for(int i=0;i<k;i++)System.out.println(arr[i]);
	}
}
