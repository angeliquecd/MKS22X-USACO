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
            highestcol=col+i;
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
    return depth*72*72;
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
for (int[] a: moves){
  for (int b: a){
    b=0;
  }
}
int[] go = new int[8];
go[0]=1; go[1]=0; go[2]=-1; go[3]=0; go[4]=0;
go[5]=1; go[6]=0; go[7]=-1;
for (int t=0;t<time;t++){
for (int a=0;a<moves.length;a++){
  for (int b=0;b<moves[0].length;b++){
    for (int c=0;c<6;c+=2){
      if (a+go[c]>=0 && a+go[c]<moves.length && b+go[c+1]>=0 && b+go[c+1]<moves[0].length){
        moves[a+go[c]][b+go[c+1]]+=1;//fills up grid with moves
      }
     }
    if (grass[a][b]=='*') moves[a][b]=0;//accounts for trees
}}
}
System.out.println(printpuzzle(moves));
System.out.println(moves[r2][c2]);
System.out.println(""+time+" "+r1+" "+c1+" "+r2+" "+c2);
return 4;}
catch (FileNotFoundException e){
  e.printStackTrace();
}
return -1;
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
  }
}
