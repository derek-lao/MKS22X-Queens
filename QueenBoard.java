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
  }

  private boolean outOfBounds(int r,int c){
    return (r<0 || c<0 || r>=board.length || c>=board.length);
  }

  private boolean solveHelper(int[][] data,int row,int c){
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
          if(solveHelper(data,row+1,col))
          {
            return true;
          }
          removeQueen(row,col);
        }
      }
      return false;
    }
  }

  private void countHelper(int[][] data,int row,int col){
    System.out.println(count);
    System.out.println(this.toString());
    if(row>=data.length)
    {
      count++;
      countHelper(data,row-1,answers[row-1]+1);
    }
    else
    {
      for(col=col;col<board.length;col++)
      {
        if(addQueen(row,col))
        {
          answers[row]=col;
          countHelper(data,row+1,0);
          removeQueen(row,col);
        }
      }
      if(row>=1)
      {
        removeQueen(row-1,answers[row-1]);
        countHelper(data,row-1,answers[row-1]+1);
      }
      else{}
    }
  }
  // private void countHelper(int[][] data,int currentRow,int currentColumn){
  //   // System.out.println(this.toString());
  //   // if(currentRow>data.length || currentRow<0)
  //   // {
  //   //   count=count; // because mr. k said I need somethiing to stop the recursion and I should put it in the front
  //   // }
  //   // System.out.println(count);
  //   if(currentRow<data.length && currentRow>-1)
  //   {
  //     for(;currentColumn<data.length;currentColumn++)
  //     {
  //       if(addQueen(currentRow,currentColumn))
  //       {
  //         answers[currentRow]=currentColumn;
  //         countHelper(data,currentRow+1,0);
  //       }
  //       else
  //       {
  //         countHelper(data,currentRow,currentColumn+1);
  //       }
  //     }
  //     if(currentRow>0)
  //     {
  //       int prevAnswer=answers[currentRow-1];
  //       removeQueen(currentRow-1,prevAnswer);
  //       answers[currentRow-1]=-1;
  //       countHelper(data,currentRow-1,prevAnswer+1);
  //     }
  //     // else
  //     // {
  //     //   return num;
  //     // }
  //   }
  //   else
  //   {
  //     int prevAnswer=answers[currentRow-1];
  //     removeQueen(currentRow-1,prevAnswer);
  //     answers[currentRow-1]=-1;
  //     count++;
  //     countHelper(data,currentRow-1,prevAnswer+1);
  //   }
  // }
  //
  //
  //
  private boolean addQueen(int r, int c){
    if(outOfBounds(r,c))
    {
      return false;
    }
    else
    {
      if(board[r][c]==0)
      {
        for(int n=0;n<board.length;n++)
        {
          board[r][n]++;
          board[n][c]++;
        }
        int p=r;
        int q=c;
        while(p<board.length && q<board.length)
        {
          board[p][q]++;
          p++;
          q++;
        }
        p=r;
        q=c;
        while(p>-1&&q>-1)
        {
          board[p][q]++;
          p--;
          q--;
        }
        p=r;
        q=c;
        while(p<board.length && q>-1)
        {
          board[p][q]++;
          p++;
          q--;
        }
        p=r;
        q=c;
        while(p>-1 && q<board.length)
        {
          board[p][q]++;
          p--;
          q++;
        }
        board[r][c]-=5;
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
      if(board[r][c]==1)
      {
        for(int n=0;n<board.length;n++)
        {
          board[r][n]--;
          board[n][c]--;
        }
        int p=r;
        int q=c;
        while(p<board.length && q<board.length)
        {
          board[p][q]--;
          p++;
          q++;
        }
        p=r;
        q=c;
        while(p>-1&&q>-1)
        {
          board[p][q]--;
          p--;
          q--;
        }
        p=r;
        q=c;
        while(p<board.length && q>-1)
        {
          board[p][q]--;
          p++;
          q--;
        }
        p=r;
        q=c;
        while(p>-1 && q<board.length)
        {
          board[p][q]--;
          p--;
          q++;
        }
        board[r][c]+=5;
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
    return solveHelper(board,0,0);
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
    countHelper(board,0,0);
    return count;
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
