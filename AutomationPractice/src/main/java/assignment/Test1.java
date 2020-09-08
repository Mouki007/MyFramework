package assignment;

public class Test1 extends Exception{
}
class main{
	public static void main(String[] args) {
		// TODO Auto-generated method stub
try {
	throw new Test1();
}catch(Test1 t) {
	System.out.println("&quot;Got the Test exception &quote;");
}finally {
	System.out.println("&quot;Inside final block &quote;");

}
	}

}
