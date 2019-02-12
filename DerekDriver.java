public class DerekDriver{
  public static void main(String[] args)
  {
    QueenBoard thing=new QueenBoard(3);
    thing.solve();
    System.out.println(thing.toString());
    thing.clear();
    System.out.println(thing.countSolutions());

    QueenBoard y=new QueenBoard(4);
    y.solve();
    System.out.println(y.toString());
    y.clear();
    System.out.println(y.countSolutions());

    QueenBoard actual=new QueenBoard(8);
    actual.solve();
    System.out.println(actual.toString());
    actual.clear();
    System.out.println(actual.countSolutions());
  }
}
