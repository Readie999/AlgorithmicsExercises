package task2;


public class SequenceDLListTest2 {
  public static void main(String[] args) {
    SequenceDLList myList = new SequenceDLList();

    try {
	myList.insertFirst(new Integer(1));
	System.out.println(myList.first());
	System.out.println(myList.last());
	System.out.println(myList.element(0));
	myList.deleteFirst();
    } catch (Exception e) {
	System.out.println(e);
    }
    
    try {
	myList.insertLast(new Integer(2));
	System.out.println(myList.first());
	System.out.println(myList.last());
	System.out.println(myList.element(0));
	myList.deleteLast();
    } catch (Exception e) {
	System.out.println(e);
    }
    
    try {
	myList.insertLast(new Integer(3));
	myList.insertFirst(new Integer(4));
	System.out.println(myList.first());
	System.out.println(myList.last());
	System.out.println(myList.element(0));
	System.out.println(myList.element(1));
	myList.deleteLast();
	System.out.println(myList.first());
	System.out.println(myList.last());
	System.out.println(myList.element(0));
	myList.deleteLast();
    } catch (Exception e) {
	System.out.println(e);
    }

    try {
	myList.insert(new Integer(5), 0);
	myList.insert(new Integer(6), 0);
	myList.insert(new Integer(7), 2);
	myList.insert(new Integer(6), 3);
	System.out.println(myList.element(0));
	System.out.println(myList.element(1));
	System.out.println(myList.element(2));
	System.out.println(myList.element(3));
	myList.deleteLast();
	System.out.println(myList.element(0));
	System.out.println(myList.element(1));
	System.out.println(myList.element(2));
	myList.deleteLast();
	System.out.println(myList.element(0));
	System.out.println(myList.element(1));
	myList.deleteLast();
	System.out.println(myList.element(0));
	myList.deleteLast();
    } catch (Exception e) {
	System.out.println(e);
    }
  }
}
