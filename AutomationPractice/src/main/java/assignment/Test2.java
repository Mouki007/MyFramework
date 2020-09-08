package assignment;

public class Test2 {
protected int x,y;
	static Test2 t;
	static int count =0;
	private String function() {
		return ("YSE");
	}
	public final static String function(int data) {
		return ("OHH YES");
	}
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

	//	Test2 obj=new Test2();
	//	System.out.println(obj.function());
		
		/*Test2 t2=new Test2();
		t2=null;
		System.gc();
		Thread.sleep(1000);
		t=null;
		System.gc();
		Thread.sleep(1000);
		System.out.println("finalize method called "+count+ " times");
	*/
		/*Test2 t2=new Test2();
	 System.out.println(t2.x+ " "+t2.y);*/
		
		boolean a=false;
		if(a=true)
			System.out.println("a is true");
		else
			System.out.println("a is false");
		
		}
	@Override
	protected void finalize() {
		count++;
		t=this;
	}

}
