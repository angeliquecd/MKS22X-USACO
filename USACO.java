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
    for (int j =0;j<firstline.length();j++){
      if (firstline.charAt(j)==' ') {
        directions[index]= Integer.parseInt(firstline.substring(old,j));
      index++;
      old=j+1;}
      if (j==firstline.length()-1){
        directions[3]= Integer.parseInt(firstline.substring(old,j+1));
      }
    }
    System.out.println(directions[0]+" "+directions[1]+" "+directions[2]+" "+directions[3]);
    System.out.println(firstline);
    int[][] field = new int[directions[0]][directions[1]];
    while (inf.hasNextLine()){
      String line = inf.nextLine();
      //for (int i =0;i<line.length;i++){
        //if (line.charAt(i)=='
        //}
      System.out.println(line);
      }
    return 10;}
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
