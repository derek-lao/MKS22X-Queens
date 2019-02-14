import java.util.*;

public class QueenBoard{
  private int[][] board;

  // the list of answers will have numbers from 0 to boardsize-1.
  private int[] answers;
  private int count=0;
  // private ArrayList<int[]> listAnswers=new ArrayList<int[]>();//ArrayList of answers

  // if a square has 0, it is empty. If it has -10, it has queen. I will
  // decide later how to backtrack with numbers

  /**
   * @param size The side size of a board. Since the board is supposed
   * to be square, it will return a size x size board.
   */
  public QueenBoard(int size){
    board=new int[size][size];
    for(int r=0; r<board.length; r++)
    {
      for(int c=0; c<board.length; c++)
      {
        board[r][c]=0;
      }
    }
    answers=new int[board.length];
    for(int i=0; i<board.length; i++)
    {
      answers[i]=-1;
    }
  }

  private boolean outOfBounds(int r,int c){
    return (r<0 || c<0 || r>=board.length || c>=board.length);
  }


  private boolean solveHelper(int[][] data,int row){
    // System.out.println(this.toTest());
    if(row>=data.length)
    {
      return true;
    }
    else
    {
      for(int col=0; col<board.length; col++)
      {
        if(addQueen(row,col))
        {
          // System.out.println("added queen successfully at "+row+","+col);
          answers[row]=col;
          if(solveHelper(data,row+1))
          {
            return true;
          }
          if(removeQueen(row,col))
          {
            answers[row]=-1;
            // System.out.println("removed queen successfully at "+row+","+col);
            // System.out.println(this.toTest());
          }
          else
          {
            // System.out.println("Failed to remove queen at "+row+","+col);
          }
        }
        else
        {
          // System.out.println("Failed to add queen at "+row+","+col);
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
    for(int r=0; r<board.length; r++)
    {
      for(int c=0; c<board.length; c++)
      {
        if(board[r][c]!=0)
        {
          r=board.length;
          c=board.length;
          throw new IllegalStateException();
        }
      }
    }
    for(int i=0; i<answers.length; i++)
    {
      answers[i]=-1;
    }
    if(solveHelper(board,0))
    {
      return true;
    }
    else
    {
      clear();
      return false;
    }
  }



  private void countHelper(int[][] data,int row){
    // System.out.println(count);
    // System.out.println(this.toString());
    if(row>=data.length)
    {
      count++;
      // System.out.println("THE COUNT HAS BEEN CHANGED, IT IS NOW "+count);
      // countHelper(data,row-1,answers[row-1]+1);
    }
    else
    {
      for(int col=0;col<board.length;col++)
      {
        if(addQueen(row,col))
        {
          // System.out.println("added queen at "+row+","+col);
          // System.out.println(this.toString());
          answers[row]=col;
          countHelper(data,row+1);
          if(removeQueen(row,col))
          {
            answers[row]=-1;
            // System.out.println("removed queen at "+row+","+col);
            // System.out.println(this.toString());
          }
          else
          {
            // System.out.println("failed to remove queen at "+row+","+col);
          }
        }
        else
        {
          // System.out.println("failed to add queen "+row+","+col);
        }
      }
    }
  }

  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions() throws IllegalStateException{
    for(int r=0; r<board.length; r++)
    {
      for(int c=0; c<board.length; c++)
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
    clear();
    return count;
  }

  private boolean addQueen(int r, int c){
    if(outOfBounds(r,c))
    {
      return false;
    }
    if(board[r][c]==0)
    {
      for(int p=0; p<board.length; p++)
      {
        for(int q=0; q<board.length; q++)
        {
          if (p == r || q == c || (Math.abs(p - r)  == Math.abs(q - c)))
          board[p][q]++;
        }
      }
      board[r][c]=-10;
      return true;
    }
    return false;
  }

  private boolean removeQueen(int r, int c){
    if(outOfBounds(r,c))
    {
      return false;
    }
    if(board[r][c]==-10)
    {
      for(int p=0; p<board.length; p++)
      {
        for(int q=0; q<board.length; q++)
        {
          if (p == r || q == c || (Math.abs(p - r)  == Math.abs(q - c)))
          board[p][q]--;
        }
      }
      board[r][c]=0;
      return true;
    }
    return false;
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
    for(int i=0; i<board.length; i++)
    {
      if(answers[i]<board.length && answers[i]>=0)
      {
        board[i][answers[i]]=-10;
      }
    }
    for(int r=0; r<board.length; r++)
    {
      for(int c=0; c<board.length; c++)
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

  public String toTest(){
    String stuff="";
    for(int r=0; r<board.length; r++)
    {
      for(int c=0; c<board.length; c++)
      {
        if(board[r][c]==-10)
        {
          stuff+="-9 ";
        }
        else
        {
          stuff+=board[r][c]+" ";
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

}
