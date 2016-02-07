package task2;

public class SequenceDLListTest {
  public static void main(String[] args) {
    SequenceDLList myList = new SequenceDLList();
    
    for (int i=0; i<26; i++) {
      myList.insertFirst(new Integer(i));
    }
    
    while(!myList.empty())
      try {
        System.out.println(myList.last());
        myList.deleteLast();
      } catch (SequenceDLListException e) {
        System.out.println(e);
      }
  }
}
