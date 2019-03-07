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
    index=-1;
    int row=0;
     int newly =0;
    while (index<field.length-1){
      index++;
      newly=0;
      row=0;
      String line = inf.nextLine();
      for (int i=0;i<line.length();i++){
        if (line.charAt(i)==' '){
        //  System.out.println("newly: "+newly+"i: "+i);
          field[index][row]=Integer.parseInt(line.substring(newly,i));
          row++;
          newly=i+1;}
        if (i==line.length()-1) field[index][directions[1]-1]= Integer.parseInt(line.substring(newly,i+1));
      }
    //  System.out.println(line);
      }
    /*  for (int a=0;a<field.length;a++){
        System.out.println("\n");
        for (int b=0;b<field[0].length;b++){
          System.out.println(field[a][b]);
        }*/
        int direction=1;
      while(inf.hasNextLine()){
        String line=inf.nextLine();
        System.out.println(line);
        String[] direction2= line.split(" ");
         row = Integer.parseInt(direction2[0]);
        int col= Integer.parseInt(direction2[1]);
        int stomp=Integer.parseInt(direction2[2]);
        int highest = 0;
        int highestrow=0;
        int highestcol=0;
        boolean found=true;
        while(found){
          found=false;
        for (int inc=0;inc<=2;inc++){
        for (int i=0;i<=2;i++){
          if (row+inc<field.length && col+i<field[0].length){
          if (field[row+inc][col+i]>highest) {
            field[row+inc][col+i]=highest;
            highestrow=row+inc;
            highestcol=col+i;
            found=true;
        }}
      }}
      field[highestrow][highestcol]-=stomp;}
        for (int a=0;a<field.length;a++){
          System.out.println("\n");
          for (int b=0;b<field[0].length;b++){
            System.out.print(field[a][b]+" ");
          }
    return 10;}}
    catch (FileNotFoundException e){
      e.printStackTrace();
    }
    return 3;
}
  public static int silver(String filename) throws FileNotFoundException{
return 4;
  }
  public static void main(String[] args){
    bronze("Test1.txt");
  }
}
