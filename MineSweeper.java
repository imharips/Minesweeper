import java.util.*;
class MineSweeper{
	static Scanner sc=new Scanner(System.in);
	public char difficulty;
	public Cell cells[][];
	public int size;
	public int totalBombs;
	
	public void init(){
		clear();
		System.out.print("\n\n\t\t\t\tChoose the difficulty\n\n\t\t\t\tEasy - e\n\n\t\t\t\tMedium - m\n\n\t\t\t\tHard - h");
		System.out.print("\n\n\t\t\t\tEnter : ");
		difficulty=sc.next().charAt(0);
		switch(difficulty){
			case 'e':
			    size=7;
				break;
			case 'm':
			    size=9;
				break;
			case 'h':
			    size=11;
				break;
			default:
			    size=9;
		}
		
		cells=new Cell[size][size];
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				cells[i][j]=new Cell();
			}
		}
		totalBombs=(size+size-2)+(int)(Math.random()*((size+size-(size+size-2))+1));
		int temp=0;
		
		while(temp<totalBombs){
			int row=0+(int)(Math.random()*((size-1-0)+1));
			int col=0+(int)(Math.random()*((size-1-0)+1));
			if(cells[row][col].bombs==-1){
				//assert:row+" "+col;
				continue;
			}
			else{
			    cells[row][col].bombs=-1;
				//assert:row+" "+col;
			    temp++;
			}
		}
		
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				if(cells[i][j].bombs==-1){
				   if(i-1>=0 && j-1>=0 && cells[i-1][j-1].bombs!=-1){
					   cells[i-1][j-1].bombs++;
				   }
				   if(i-1>=0 && cells[i-1][j].bombs!=-1){
					   cells[i-1][j].bombs++;
				   }
				   if(i-1>=0 && j+1<=size-1 && cells[i-1][j+1].bombs!=-1){
					   cells[i-1][j+1].bombs++;
				   }
				   if(j-1>=0 && cells[i][j-1].bombs!=-1){
					   cells[i][j-1].bombs++;
				   }
				   if(j+1<=size-1 && cells[i][j+1].bombs!=-1){
					   cells[i][j+1].bombs++;
				   }
				   if(i+1<=size-1 && j-1>=0 && cells[i+1][j-1].bombs!=-1){
					   cells[i+1][j-1].bombs++;
				   }
				   if(i+1<=size-1 && cells[i+1][j].bombs!=-1 && cells[i+1][j].bombs!=-1){
					   cells[i+1][j].bombs++;
				   }
				   if(i+1<=size-1 && j+1<=size-1 && cells[i+1][j+1].bombs!=-1){
					   cells[i+1][j+1].bombs++;
				   }
				}
			}
		}
		clear();
	}
	
	public void expose(int i,int j){
		if( i<=size-1 && j<=size-1 && i>=0 && j>=0){
			if(cells[i][j].bombs!=0){
			    return;
			}
			if(!cells[i][j].isOpened){
			    cells[i][j].isOpened=true;
			}
		    else{
			    return;
		    }
		}
		else{
			return;
		}
		
		if(i-1>=0 && j-1>=0 && cells[i-1][j-1].bombs>0){
			cells[i-1][j-1].isOpened=true;
		}
		expose(i-1,j-1);
		
		if(i-1>=0 && cells[i-1][j].bombs>0){
			cells[i-1][j].isOpened=true;
		}
		expose(i-1,j);
		
		if(i-1>=0 && j+1<=size-1 && cells[i-1][j+1].bombs>0){
			cells[i-1][j+1].isOpened=true;
		}
		expose(i-1,j+1);
		
		if(j-1>=0 && cells[i][j-1].bombs>0){
			cells[i][j-1].isOpened=true;
		}
		expose(i,j-1);
		
		if( j+1<=size-1 && cells[i][j+1].bombs>0){
			cells[i][j+1].isOpened=true;
		}
		expose(i,j+1);
		
		if(i+1<=size-1 && j-1>=0 && cells[i+1][j-1].bombs>0){
			cells[i+1][j-1].isOpened=true;
		}
		expose(i+1,j-1);
		
		if(i+1<=size-1 && cells[i+1][j].bombs>0){
			cells[i+1][j].isOpened=true;
		}
		expose(i+1,j);
		
		if(i+1<=size-1 && j+1<=size-1 && cells[i+1][j+1].bombs>0){
			cells[i+1][j+1].isOpened=true;
		}
		expose(i+1,j+1);
	}
	
	public boolean win(){
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				if(!cells[i][j].isOpened && cells[i][j].bombs!=-1 && !cells[i][j].isFlagged){
					return false;
				}
			}
		}
		return true;
	}
	
	public void print(){
		System.out.println("\t\t\t\tTotal Bombs : "+totalBombs);
		String lines="+";
		String indices="";
		for(int i=0;i<size;i++){
			indices+=String.format("%5s",i+"");
			lines+="----+";
		}
		System.out.println("\t\t\t\t"+indices);
		for(int i=0;i<size;i++){
			System.out.println("\t\t\t\t"+lines);
			System.out.print("\t\t\t\t");
			for(int j=0;j<size;j++){
				if(cells[i][j].isFlagged){
					System.out.print("|");
				    System.out.printf("%4s","F ");
					//System.out.print(Color.
				}
			    else if(!cells[i][j].isOpened){
					System.out.print("|");
				    System.out.printf("%4s","   ");
				}
				else if(cells[i][j].bombs>=0){
					System.out.print("|");
					System.out.printf(" %2d ",cells[i][j].bombs);
				}
			}
			System.out.println("| "+i);
		}
		System.out.println("\t\t\t\t"+lines+"\n");
	}
	
	public void exposing(){
		String lines="+";
		for(int i=0;i<size;i++){
			lines+="----+";
		}
		for(int i=0;i<size;i++){
			System.out.println("\t\t\t\t"+lines);
			
			System.out.print("\t\t\t\t");
			for(int j=0;j<size;j++){
					System.out.print("|");
					System.out.printf(" %2d ",cells[i][j].bombs);
			}
			System.out.println("|");
		}
		System.out.println("\t\t\t\t"+lines+"\n");
	}
	
	public void play(){
		clear();
		while(true){
			clear();
			System.out.println("\n\n");
			System.out.println("\n\n");
			print();
			//exposing();
			System.out.print("\t\t\t\tEnter the row(0 - "+(size-1)+") and column(0 - "+(size-1)+") : ");
			int row=sc.nextInt();
			int col=sc.nextInt();
			
			System.out.print("\t\t\t\tFlag(f) or Open(o) : ");
			char choice=sc.next().charAt(0);
			if(!cells[row][col].isOpened){
			if(choice=='f'){
				cells[row][col].isFlagged=!cells[row][col].isFlagged?true:false;
			}
			else if(cells[row][col].bombs>0){
				cells[row][col].isOpened=true;
			}
			else if(cells[row][col].bombs==-1){
				result('l');
				clear();
				System.out.println("\n\n");
			    System.out.println("\n\n");
				exposing();
				
				System.out.println("\n\n\t\t\t\t!!! Game Over .... Press Enter To Exit!!!");
				sc.nextLine();
				sc.nextLine();
				try{
			        new ProcessBuilder("cmd","/c","color 07").inheritIO().start().waitFor();
		        }
		        catch(Exception e){
			
		        }
				clear();
				break;
			}
			else if(cells[row][col].bombs==0){
			     expose(row,col);
			}
			
			if(win()){
				result('w');
				System.out.println("\t\t\t\tYou Won.....Press Enter To Exit!!!");
				sc.nextLine();
				sc.nextLine();
				try{
			        new ProcessBuilder("cmd","/c","color 07").inheritIO().start().waitFor();
		        }
		        catch(Exception e){
			
		        }
				clear();
				return;
			}
			}
		}
	}
	
	static void clear(){
		try{
			new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
		}
		catch(Exception e){
			
		}
	}
	
	static void result(char ch){
		String s="";
		switch(ch){
			case 'w':
			    s="color 0A";
				break;
			case 'l':
			    s="color 0c";
				break;
		}
		try{
			new ProcessBuilder("cmd","/c",s).inheritIO().start().waitFor();
		}
		catch(Exception e){
			
		}
	}
}