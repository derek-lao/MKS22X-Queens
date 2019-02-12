public class DerekDriver{
  public static void main(String[] args)
  {
    QueenBoard thing=new QueenBoard(3);
    thing.solve();
    thing.countSolutions();
    System.out.println(thing.toString());
    System.out.println(thing.countSolutions());

    QueenBoard actual=new QueenBoard(8);
    actual.solve();
    actual.countSolutions();
    System.out.println(actual.toString());
    System.out.println(actual.countSolutions());
  }
}
