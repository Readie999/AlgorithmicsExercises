package task4;
public class BinaryTreeLUTTest {
  public static void main(String[] args) {
    try {
      BinaryTreeLUT myLUT = new BinaryTreeLUT();

      myLUT.insert("Priscilla", new Integer(41));
      myLUT.insert("Travis", new Integer(34));
      myLUT.insert("Samuel", new Integer(28));
      myLUT.insert("Helena", new Integer(39));
      myLUT.insert("Andrew", new Integer(14));
      myLUT.insert("Kay", new Integer(24));
 //     myLUT.insert("Z", new Integer(25));
      myLUT.insert("John", new Integer(67));
      System.out.println(myLUT);

      myLUT.remove("Priscilla");
      System.out.println(myLUT);

//

      myLUT.remove("Travis");
      System.out.println(myLUT);

      myLUT.remove("Samuel");
      System.out.println(myLUT);

      myLUT.remove("Andrew");
      System.out.println(myLUT);

    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
