public class Hello{

	
// TEST 02 Branch Commit 
public static class Info{
		int num;
		boolean visited;
		
		Info(int num, boolean visited)
		{
			this.num = num;
			this.visited = visited;
		}
	}
	 
	static int deadCnt = 0;
	static int n=0;
	static int d=0; // 1�씠硫� + , 0�씠硫� -
	static int direct=0; // 1�씠硫� + , 0�씠硫� -
	static int k=0;
	static int j=0;
	static LinkedList<Info> list = new LinkedList<Info>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		d = sc.nextInt();
		k = sc.nextInt();
		j = sc.nextInt();
		
		// 珥덇린�솕
		for(int i=0; i<n; i++)
			list.add(new Info(i+1, false));
		
		if(d==1)
			direct = 1;
		else
			direct = -1;
		
		run(0,k);
						
		
	}
	
	static public void run(int start, int cnt)
	{
				
		if(deadCnt==list.size()-1){
			for(Info rst : list)
				if(!rst.visited)
					System.out.println(rst.num);
			return;
		}	
		cnt--;
		
		int next = start+direct;
		if(next>=6)
			next= next-6;
		else if(next<0)
			next= next+6;
		

		if( list.get(next).visited)
			run(next,cnt+1);
		else{
			
			if(cnt==0){
				list.get(next).visited = true;
				k+=j;
				deadCnt++;
				run(next,k);
			}
			else
			{
				run(start+direct,cnt--);
			}
		}
	}
	
	
	static public void print(){
		
		for(int i =0; i<list.size(); i++)
			System.out.print(list.get(i).visited +"  ");
		System.out.println();
	}
}
