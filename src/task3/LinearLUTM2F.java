package task3;
class KeyNotFoundInTableException extends Exception {
}

/**
 * <dl>
 * <dt>Purpose: Implementation of LUT using unordered linear search.
 * <dd>
 *
 * <dt>Description:
 * <dd>This class is an implementation of the look-up table abstract data type
 * that uses a sequence array as the underlying data structure. The capacity
 * of the LUT is thus limited. The elements of the look-up table are stored
 * unordered and linear search is used for retrieval.
 * </dl>
 *
 * @author Danny Alexander
 * @version $Date: 2000/01/29
 */

public class LinearLUTM2F {

  /**
   * The member class Key is used for the indexing keys of the LUT. It
   * is a String with basic comparative methods added.
   */
  protected class Key {

    public Key(String s) {
      kString = s;
    }

    public boolean equals(Key k) {
      return kString.equals(k.toString());
    }

    public boolean lessThan(Key k) {
      return (kString.compareTo(k.toString()) < 0);
    }

    public boolean greaterThan(Key k) {
      return (kString.compareTo(k.toString()) > 0);
    }

    public String toString() {
      return kString;
    }

    private String kString;
  }

  /**
   * The member class Entry encapsulates an entry of the LUT and contains
   * a {key, value} pair.
   */
  protected class Entry {

    public Entry(Key k, Object v) {
      key = k;
      value = v;
    }

    protected Key key;
    protected Object value;
  }

  //Single private data member - the LUT is stored in a sequence.
  protected SequenceList seq;

  /**
   * Default constructor creates a default size sequence to store the LUT.
   */
  public LinearLUTM2F() {
    seq = new SequenceList();
  }

// dont need this since now using a list 
//  /**
//   * Constructs a look-up table of specified maximum capacity.
//   */
//  public LinearLUTM2F(int size) {
//    seq = new SequenceArray(size);
//  }

  /**
   * Inserts a new key-value pair into the look-up table.
   */
  public void insert(String key, Object value) throws SequenceListException {

    //Just insert new entries at the easiest place - for the array
    //based sequence class, this is at the end of the sequence.
    Entry newEntry = new Entry(new Key(key), value);
    seq.insertFirst(newEntry);
  }

  /**
   * Removes the key-value pair with the specified key from the look-up
   * table.
   */
  public void remove(String key) throws KeyNotFoundInTableException {

    Key searchKey = new Key(key);
    int index = findPosition(searchKey);
    if (index >= 0) try {
        seq.delete(index);
      } catch (Exception e) {
        System.out.println(e);
      }
    else {
      throw new KeyNotFoundInTableException();
    }
  }

  /**
   * Retrieves the key-value pair with the specified key from the look-up
   * table.
   */
  public Object retrieve(String key) throws KeyNotFoundInTableException {

    Key searchKey = new Key(key);
    int index = findPosition(searchKey);
    if (index >= 0) try {
        Entry searchEntry = (Entry)seq.element(index);
        seq.delete(index);
        seq.insertFirst(searchEntry);
        return searchEntry.value;
      } catch (Exception e) {
        System.out.println(e);
      }
    else {
      throw new KeyNotFoundInTableException();
    }
    return null;
  }

  /**
   * Updates the key-value pair with the specified key with the new
   * specified value.
   */
  public void update(String key, Object value) throws KeyNotFoundInTableException {

    Key searchKey = new Key(key);
    int index = findPosition(searchKey);
    if (index >= 0) try {
        Entry searchEntry = (Entry)seq.element(index);
        searchEntry.value = value;
        seq.delete(index);
        seq.insertFirst(searchEntry);
      } catch (Exception e) {
        System.out.println(e);
      }
    else {
      throw new KeyNotFoundInTableException();
    }
  }

  /**
   * Returns a string listing all the key-entry pairs in the LUT
   */
  public String toString() {
    String output = "";
    for (int i=0; i<seq.size(); i++) {
      try {
        Entry tableEntry = (Entry)seq.element(i);
        output = output + tableEntry.key.toString() + ":" + tableEntry.value.toString() + ", ";
      } catch (Exception e) {
        System.out.println(e);
      }
    }
    return output;
  }

  /**
   * Returns the index of the sequence holding the entry with the
   * specified key. If no match is found an index of -1 is returned.
   */
  protected int findPosition(Key k) {
    return linearSearch(k);
  }

  /**
   * Performs linear search on the sequence holding the LUT and returns
   * the index of the entry containing the specifed key. If no match is
   * found an index of -1 is returned.
   */
  protected int linearSearch(Key k) {

    for (int i=0; i<seq.size(); i++) {
      if (k.equals(keyAt(i))) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Returns the key at the specified position in the sequence.
   */
  protected Key keyAt(int index) {

    try {
      Entry entryAt = (Entry)seq.element(index);
      return entryAt.key;
    } catch (Exception e) {
      System.out.println(e);
    }

    return null;
  }
}
