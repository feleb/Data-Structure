package train;


public class Heap {
	
	public static void max_heapify(int []Arr,int i,int n){
		int left=2*i+1;
		int right=2*i+2;
		int largest=i;
		if(left<n && Arr[left]>Arr[i])largest=left;
		if (right<n && Arr[right]>Arr[largest])largest=right;
		int t=Arr[i];
		Arr[i]=Arr[largest];
		Arr[largest]=t;
		if(largest!=i)max_heapify(Arr, largest, n);
	}
	public static void Buliding_max_heap(int []Arr,int n){
		for(int i=(n/2)-1;i>=0;i--){
			max_heapify(Arr, i, n);
		}
	}
	public static int []heap_sort(int []Arr,int n){
		int res[]=new int [n];
		for(int i=0;i<n;i++){
			max_heapify(Arr, 1, n-i);
			res[n-i-1]=Arr[1];
			int t=Arr[1];
			Arr[1]=Arr[n-i];
			Arr[n-i]=t;
		}
		return res;
	}
	public static void SortHeap (int []Arr,int n){
		Buliding_max_heap(Arr, n);
		int heapsize=n;
		for(int i=n-1;i>0;i--){
			int t=Arr[0];
			Arr[0]=Arr[i];
			Arr[i]=t;
			heapsize--;
			max_heapify(Arr, 0, heapsize);
		}
	}
	
	public static void main (String[]args){
		int n=7;
		int []arr={1,4,3,7,8,9,10};
		Buliding_max_heap(arr, n);
		//int res[]=new int [n];
		SortHeap(arr, n);
		//max_heapify(arr, 0, n);
		for(int i=0;i<n;i++)System.out.println(arr[i]);
	}
	

}
