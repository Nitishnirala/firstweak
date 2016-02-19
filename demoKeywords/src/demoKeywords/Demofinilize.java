package demoKeywords;
/**
 * finalize is a method 
 * finalize is used to perform cleanup processing just before 
 * object is garbage collected.
 */
public class Demofinilize {
	
	public void finalize(){
		System.out.println("finilize method");
	}
	public static void main(String[] args) {
		
		Demofinilize objfinilize=new Demofinilize();
		Demofinilize objfinilize1=new Demofinilize();
		System.out.println(objfinilize);
		objfinilize=null;
		System.out.println(objfinilize1);
		objfinilize1=null;
		System.gc();
		//System.out.println(objfinilize1);
		
	}

}
