import java.io.*;
public class Game{
	public static void main(String args[]){
		MineSweeper mineSweeper=new MineSweeper();
		mineSweeper.init();
		try{
		    System.out.println("\n\n\t\t\t\t\t\tInitializing....");
			Thread.sleep(3000);
		}
		catch(Exception e){
			
		}
		
		mineSweeper.play();
	}
}
