public class QueenBoard{
  private int[][] board;

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
      for(int c=0;c<board[r].length;c++)
      {
        board[r][c]=0;
      }
    }
  }

  private boolean outOfBounds(int r,int c){
    return (r<0 || c<0 || r>=board.length || c>=board[r].length);
  }

  private boolean solveHelper(int[][] data,int currentRow,int currentColumn,){

  }

  private boolean addQueen(int r, int c){
    if(outOfBounds(r,c))
    {
      return false;
    }
    else
    {
      for(int n=0;n<board.length;n++)
      {
        board[r][n]++;
        board[n][c]++;
        board[r][c]--;
      }
      return true;
    }
  }

  private boolean removeQueen(int r, int c){
    if(outOfBounds(r,c))
    {
      return false;
    }
    else
    {
      for(int n=0;n<board.length;n++)
      {
        board[r][n]--;
        board[n][c]--;
        board[r][c]++;
      }
      return true;
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

  }


  /**
  *@return false when the board is not solveable and leaves the board filled with zeros;
  *        true when the board is solveable, and leaves the board in a solved state
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public boolean solve(){

  }

  /**
  *@return the number of solutions found, and leaves the board filled with only 0's
  *@throws IllegalStateException when the board starts with any non-zero value
  */
  public int countSolutions(){

  }
}
