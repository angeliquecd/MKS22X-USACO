 import java.util.*;
import java.io.*;
public class USACO{
  public static int bronze(String filename) {
    try{
    File text = new File(filename);
    Scanner inf = new Scanner(text);
    String firstline = inf.nextLine();
    int old = 0;
    int index= 0;
    int[] directions = new int[4];
    /*for (int j =0;j<firstline.length();j++){
      if (firstline.charAt(j)==' ') {
        directions[index]= Integer.parseInt(firstline.substring(old,j));
      index++;
      old=j+1;}
      if (j==firstline.length()-1){
        directions[3]= Integer.parseInt(firstline.substring(old,j+1));
      }
    }*/
  //  System.out.println(directions[0]+" "+directions[1]+" "+directions[2]+" "+directions[3]);
    //System.out.println(firstline);
    String[] directionline = firstline.split(" ");
    int[][] field = new int[Integer.parseInt(directionline[0])][Integer.parseInt(directionline[1])];
  //  printpuzzle(field);
    index=-1;
    int row=0;
     int newly =0;
    while (index<field.length-1){
      index++;
      String line = inf.nextLine();
    //  System.out.println(line);
      String[] lines = line.split(" ");
      for (int i=0;i<field[0].length;i++){
      //  System.out.println(Integer.parseInt(lines[i]));
        field[index][i]=Integer.parseInt(lines[i]);//fills up grid
        //printpuzzle(field);
      }
    //  System.out.println(line);
      }
      //System.out.println(printpuzzle(field));

      while(inf.hasNextLine()){//collects instruction data
        String line=inf.nextLine();
      //  System.out.println(line);
        String[] direction2= line.split(" ");
         row = Integer.parseInt(direction2[0])-1;
        int col= Integer.parseInt(direction2[1])-1;
        int stomp=Integer.parseInt(direction2[2]);
      //  System.out.println(""+row+" "+col+" "+stomp);
        int highest = 0;
        int highestrow=0;
        int highestcol=0;
        for (int inc=0;inc<=2;inc++){
        for (int i=0;i<=2;i++){
          if (row+inc<field.length && col+i<field[0].length){
          if (field[row+inc][col+i]>highest) {
            highest=field[row+inc][col+i];
            highestrow=row+inc;
            highestcol=col+i;//identifies highest elevation thing
            }
        }
      }
    }
      field[highestrow][highestcol]-=stomp;//lowers the highest one
      for (int inc=0;inc<=2;inc++){
      for (int i=0;i<=2;i++){
        if (row+inc<field.length && col+i<field[0].length){
        if (field[row+inc][col+i]>field[highestrow][highestcol]){
          field[row+inc][col+i]=field[highestrow][highestcol];}//sets other ones to same level
      }
    }
    }
  //    System.out.println(printpuzzle(field));
    }//end of directions
  int elevation = Integer.parseInt(directionline[2]);//given in directionline
  int depth=0;
  for (int a=0;a< field.length;a++){
    for (int b=0;b<field[0].length;b++){
      if (field[a][b]<elevation){
        depth+=elevation-field[a][b];//finds depth by comparing to elevation
      }
    }
  }
  System.out.println(depth*72*72);
    return depth*72*72;//what the website told us to multiply by
  }//end of try
    catch (FileNotFoundException e){
      e.printStackTrace();
    }
    return -1;//unacheivable
}

public static String printpuzzle(int[][] puzzle){
  String value="";
  for (int a=0;a< puzzle.length;a++){
    for (int b=0;b<puzzle[0].length;b++){
      value+=" "+puzzle[a][b];
    }
    value+="\n";
  }
  return value;
}

public static int silver(String filename) {
    try{
//read in the file
File text = new File(filename);
Scanner inf = new Scanner(text);
String firstline = inf.nextLine();
String[] directions = firstline.split(" ");
char[][] grass = new char[Integer.parseInt(directions[0])][Integer.parseInt(directions[1])];
int time = Integer.parseInt(directions[2]);
//System.out.println(printpuzzle(grass));
String line ="";
for (int i=0;i<grass.length;i++){
  line=inf.nextLine();
  for (int j=0;j<grass[0].length;j++){
    grass[i][j]=line.charAt(j);}
}
//System.out.println(printpuzzle(grass));
String[] spots = inf.nextLine().split(" ");
int r1 = Integer.parseInt(spots[0])-1; int c1 = Integer.parseInt(spots[1])-1;
int r2 = Integer.parseInt(spots[2])-1; int c2 = Integer.parseInt(spots[3])-1;
int[][] moves = new int[grass.length][grass[0].length];
//int[][] fresh = new int[grass.length][grass[0].length];
//done with setting up

for (int a=0;a<moves.length;a++){
  for (int b=0;b<moves[0].length;b++){
    if (grass[a][b]=='*')moves[a][b]=-9;//random off limit number
    else moves[a][b]=0;
  }
}
moves[r1][c1]=1;
//initializes board

int[] go = {1,0,-1,0,0,1,0,-1};
for (int t=0;t<time;t++){//limits it to time alotted
  moves= setup(moves,go,grass);//alters move every second to a new 'fresh' board
}

//System.out.println(printpuzzle(moves));
//System.out.println(moves[r2][c2]);
//System.out.println(""+time+" "+r1+" "+c1+" "+r2+" "+c2);
return moves[r2][c2];}
catch (FileNotFoundException e){
  e.printStackTrace();
}
return -1;//unreachable
  }

public static int[][] setup(int[][] moves,int[] go,char[][] grass){
  int[][] fresh = new int[moves.length][moves[0].length];//makes a fresh array, which
  //means that old moves disappear and it just goes off of the new spots you could have made it to
    for(int i = 0; i < moves.length; i++){
      for(int j = 0; j < moves[0].length; j++){
        if (moves[i][j]==-9) fresh[i][j]=-9;
        if(moves[i][j]>0){//looks for a place where a theoretical move could be
          for (int a=0;a<=6;a+=2){
            ispossible(moves,fresh,i+go[a],j+go[a+1],moves[i][j]);}//searches for possible moves
          //System.out.println(printpuzzle(fresh));}
        //fresh[i][j]=0;
      //moves[i][j]=0;
    }
      }
      }
    //  System.out.println("new time");
      return fresh;
}
public static void ispossible(int[][] moves, int[][] fresh,int row, int col, int value){
  if(row >= 0 && row < fresh.length && col >= 0 && col < fresh[0].length && moves[row][col]>-1){
  fresh[row][col] += value;//determines if it's a valid move and then transfers data from the
  //last move to the new row, because the new second is just an extension of the old time
}
}
  public static String printpuzzle(char[][] puzzle){
    String value="";
    for (int a=0;a< puzzle.length;a++){
      for (int b=0;b<puzzle[0].length;b++){
        value+=" "+puzzle[a][b];
      }
      value+="\n";
    }
    return value;
  }


  public static void main(String[] args){
    bronze("Test1.txt");
    bronze("Test2.txt");
    bronze("Test3.txt");
    bronze("Test4.txt");
    bronze("Test5.txt");
    silver("Testee1.txt");
    silver("Testee2.txt");
    silver("Testee3.txt");
    silver("Testee4.txt");
    silver("Testee5.txt");
  }
}
