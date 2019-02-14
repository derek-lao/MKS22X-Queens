import java.util.*;

public class QueenBoard{
  private int[][] board;

  // the list of answers will have numbers from 0 to 7.
  private int[] answers;
  private int count=0;
  // private ArrayList<int[]> listAnswers=new ArrayList<int[]>();//ArrayList of answers

  // if a square has 0, it is empty. If it has 1, it has queen. I will
  // decide later how to backtrack with numbers

  /**
   * @param size The side size of a board. Since the board is supposed
   * to be square, it will return a size x size board.
   */
  public QueenBoard(int size){
    board=new int[size][size];
    for(int r=0;r<board.length;r++)
    {
      for(int c=0;c<board.length;c++)
      {
        board[r][c]=0;
      }
    }
    answers=new int[board.length];
    for(int i=0;i<board.length;i++)
    {
      answers[i]=-1;
    }
  }

  private boolean outOfBounds(int r,int c){
    return (r<0 || c<0 || r>=board.length || c>=board.length);
  }

  private boolean solveHelper(int[][] data,int row){
    System.out.println(this.toString());
    if(row>=data.length)
    {
      return true;
    }
    else
    {
      for(int col=0;col<board.length;col++)
      {
        if(addQueen(row,col))
        {
          answers[row]=col;
          if(solveHelper(data,row+1))
          {
            return true;
          }
          removeQueen(row,col);
        }
      }
      return false;
    }
  }

  /**
  *@return false when the board is not solveable and leaves the board filled with zeros;
  *        true when the board is solveable, and leaves the board in a solved state
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public boolean solve() throws IllegalStateException{
    for(int r=0;r<board.length;r++)
    {
      for(int c=0;c<board.length;c++)
      {
        if(board[r][c]!=0)
        {
          r=board.length;
          c=board.length;
          throw new IllegalStateException();
        }
      }
    }
    for(int i=0;i<answers.length;i++)
    {
      answers[i]=-1;
    }
    return solveHelper(board,0);
  }



  private void countHelper(int[][] data,int row){
    // System.out.println(count);
    System.out.println(this.toString());
    if(row>=data.length)
    {
      count++;
      // countHelper(data,row-1,answers[row-1]+1);
    }
    else
    {
      for(int col=0;col<board.length;col++)
      {
        if(addQueen(row,col))
        {
          System.out.println("added queen at "+row+","+col);
          answers[row]=col;
          countHelper(data,row+1);
          if(removeQueen(row,col))
          {
            System.out.println("removed queen at "+row+","+col);
          }
          else
          {
            System.out.println("failed to remove queen at "+row+","+col);
          }
        }
        else
        {
          System.out.println("failed to add queen "+row+","+col);
        }
      }
    }
  }

  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions() throws IllegalStateException{
    for(int r=0;r<board.length;r++)
    {
      for(int c=0;c<board.length;c++)
      {
        if(board[r][c]!=0)
        {
          r=board.length;
          c=board.length;
          throw new IllegalStateException();
        }
      }
    }

    for(int i=0;i<answers.length;i++)
    {
      answers[i]=-1;
    }
    count=0;
    countHelper(board,0);
    return count;
  }

  private boolean addQueen(int r, int c){
    if(outOfBounds(r,c))
    {
      return false;
    }
    else
    {
      if(board[r][c]==0)
      {
        int p=r;
        int q=c;
        while(p<board.length&&q<board.length)
        {
          board[p][c]++;
          board[p][q]++;
          p++;
          q++;
        }
        p=r;
        q=c;
        while(p<board.length&&q>-1)
        {
          board[p][q]++;
          p++;
          q--;
        }
        p=r;
        q=c;
        board[p][q]=-10;
        return true;
      }
      else
      {
        return false;
      }
    }
  }

  private boolean removeQueen(int r, int c){
    if(outOfBounds(r,c))
    {
      return false;
    }
    else
    {
      if(board[r][c]==0)
      {
        int p=r;
        int q=c;
        while(p<board.length&&q<board.length)
        {
          board[p][c]--;
          board[p][q]--;
          p++;
          q++;
        }
        p=r;
        q=c;
        while(p<board.length&&q>-1)
        {
          board[p][q]--;
          p++;
          q--;
        }
        p=r;
        q=c;
        board[p][q]=0;
        return true;
      }
      else
      {
        return false;
      }
    }
  }

  /**
  *@return The output string formatted as follows:
  *All numbers that represent queens are replaced with 'Q'
  *all others are displayed as underscores '_'
  *There are spaces between each symbol:
  *"""_ _ Q _
  *Q _ _ _
  *_ _ _ Q
  *_ Q _ _"""
  *(pythonic string notation for clarity,
  *excludes the character up to the *)
  */
  public String toString(){
    String stuff="";
    for(int i=0;i<board.length;i++)
    {
      if(answers[i]<board.length&&answers[i]>=0)
      {
        board[i][answers[i]]=-10;
      }
    }
    for(int r=0;r<board.length;r++)
    {
      for(int c=0;c<board.length;c++)
      {
        if(board[r][c]==-10)
        {
          stuff+="Q ";
        }
        else
        {
          stuff+="_ ";
        }
      }
      stuff+="\n";
    }
    return stuff;
  }







  public void clear(){
    for(int r=0;r<board.length;r++)
    {
      for(int c=0;c<board.length;c++)
      {
        board[r][c]=0;
      }
    }
  }

  public static void main(String[] args){
    QueenBoard stuff=new QueenBoard(3);
    System.out.println(stuff.toString());
    System.out.println(stuff.addQueen(1,1));
    System.out.println("added queen at 1,1");
    System.out.println(stuff.toString());
    System.out.println(stuff.removeQueen(1,1));
    System.out.println("removed queen at 1,1");
    System.out.println(stuff.toString());
  }
}
