package task2;

class SequenceDLListException extends Exception {
	SequenceDLListException() {
		super();
	}

	SequenceDLListException(String s) {
		super(s);
	}
}

public class SequenceDLList {

	protected class Node {

		public Node(Object o) {
			this(o, null, null);
		}

		public Node(Object o, Node n, Node p) {
			datum = o;
			next = n;
			previous = p;
		}

		// The Node data structure consists of three object references.
		// One for the datum contained in the node and the other two for
		// the next & previous node in the list.

		protected Object datum;
		protected Node next, previous;
	}

	// We use object references to the head and tail of the list (the head
	// and tail of the sequence, respectively).
	private Node listHead;
	private Node listTail;

	// Only require a single constructor, which sets both object
	// references to null.
	/**
	 * Constructs an empty sequence object.
	 */
	public SequenceDLList() {
		listHead = null;
		listTail = null;
	}

	/**
	 * Adds a new item at the start of the sequence.
	 */
	public void insertFirst(Object o) {
		// There is a special case when the sequence is empty.
		// Then the both the head and tail pointers needs to be
		// initialised to reference the new node.
		if (listHead == null) {
			listHead = new Node(o, listHead, null);
			listTail = listHead;
		}

		// In the general case, we simply add a new node at the start
		// of the list via the head pointer.
		else {

			listHead.previous = new Node(o, listHead, null);
			listHead = listHead.previous;
		}
	}

	/**
	 * Adds a new item at the end of the sequence.
	 */
	public void insertLast(Object o) {
		// There is a special case when the sequence is empty.
		// Then the both the head and tail pointers needs to be
		// initialised to reference the new node.
		if (listHead == null) {
			listHead = new Node(o, listHead, null);
			listTail = listHead;
		}

		// In the general case, we simply add a new node to the end
		// of the list via the tail pointer.
		// until assigned listTail.next =null so next is null
		else {
			listTail.next = new Node(o, listTail.next, listTail);
			listTail = listTail.next;
		}
	}

	/**
	 * Adds a new item at a specified position in the sequence.
	 */
	public void insert(Object o, int index) throws SequenceDLListException {
		// Check the index is positive.
		if (index < 0) {
			throw new SequenceDLListException("Indexed Element out of Range");
		}

		// There is a special case when the sequence is empty.
		// Then the both the head and tail pointers needs to be
		// initialised to reference the new node.
		if (listHead == null) {
			if (index == 0) {
				listHead = new Node(o, listHead, null);
				listTail = listHead;
			} else {
				throw new SequenceDLListException("Indexed element is out of range");
			}
		}

		// There is another special case for insertion at the head of
		// the sequence.
		else if (index == 0) {
			listHead.previous = new Node(o, listHead, null);
			listHead = listHead.previous;
		}

		// In the general case, we need to chain down the linked list
		// from the head until we find the location for the new
		// list node. If we reach the end of the list before finding
		// the specified location, we know that the given index was out
		// of range and throw an exception.
		else {
			Node nodePointer = listHead;
			int i = 1;
			while (i < index) {
				nodePointer = nodePointer.next;
				i += 1;
				if (nodePointer == null) {
					throw new SequenceDLListException("Indexed Element out of Range");
				}
			}

			// Now we've found the node before the position of the
			// new one, so we 'hook in' the new Node.

			nodePointer.next = new Node(o, nodePointer.next, nodePointer);

			// set the previous Node on the Node one above the new Node
			// ensure that this doesn't occur when adding to the last entry in
			// the list
			if (nodePointer.next.next != null) {
				nodePointer.next.next.previous = nodePointer.next;
			}
			// Finally we need to check that the tail pointer is
			// correct. Another special case occurs if the new
			// node was inserted at the end, in which case, we need
			// to update the tail pointer.
			if (nodePointer == listTail) {
				listTail = listTail.next;
			}
		}
	}

	/**
	 * Removes the item at the start of the sequence.
	 */
	public void deleteFirst() throws SequenceDLListException {
		// Check there is something in the sequence to delete.
		if (listHead == null) {
			throw new SequenceDLListException("Sequence Underflow");
		}

		// There is a special case when there is just one item in the
		// sequence. Both pointers then need to be reset to null.
		if (listHead.next == null) {
			listHead = null;
			listTail = null;
		}

		// In the general case, we just unlink the first node of the
		// list.
		else {
			listHead = listHead.next;
			listHead.previous = null;
		}
	}

	/**
	 * Removes the item at the end of the sequence.
	 */
	public void deleteLast() throws SequenceDLListException {
		// Check there is something in the sequence to delete.
		if (listHead == null) {
			throw new SequenceDLListException("Sequence Underflow");
		}

		// There is a special case when there is just one item in the
		// sequence. Both pointers then need to be reset to null.
		if (listHead.next == null) {
			listHead = null;
			listTail = null;
		}

		// In the general case, we need to chain all the way down the
		// list in order to reset the link of the second to last
		// element to null.
		else {
			Node nodePointer = listTail.previous;

			// Unlink the last node and reset the tail pointer.
			nodePointer.next = null;
			listTail = nodePointer;
		}
	}

	/**
	 * Removes the item at the specified position in the sequence.
	 */
	public void delete(int index) throws SequenceDLListException {
		// Check there is something in the sequence to delete.
		if (listHead == null) {
			throw new SequenceDLListException("Sequence Underflow");
		}

		// Check the index is positive.
		if (index < 0) {
			throw new SequenceDLListException("Indexed Element out of Range");
		}

		// There is a special case when there is just one item in the
		// sequence. Both pointers then need to be reset to null.
		if (listHead.next == null) {
			if (index == 0) {
				listHead = null;
				listTail = null;
			} else {
				throw new SequenceDLListException("Indexed element is out of range.");
			}
		}

		// There is also a special case when the first element has to
		// be removed.

		else if (index == 0) {
			deleteFirst();
		}

		// In the general case, we need to chain down the list to find
		// the node in the indexed position.
		else {
			Node nodePointer = listHead;
			int i = 1;
			while (i < index) {
				nodePointer = nodePointer.next;
				i += 1;
				if (nodePointer.next == null) {
					throw new SequenceDLListException("Indexed Element out of Range");
				}

			}

			// Unlink the node and reset the tail pointer if that
			// node was the last one.
			if (nodePointer.next == listTail) {
				listTail = nodePointer;
			}
			// set the next link to be the one that used to be 2 places away
			nodePointer.next = nodePointer.next.next;
			// set the backward link for the Node after the deleted one
			if (nodePointer.next != null) {
				nodePointer.next.previous = nodePointer;
			}
		}
	}

	/**
	 * Returns the item at the start of the sequence.
	 */
	public Object first() throws SequenceDLListException {
		if (listHead != null) {
			return listHead.datum;
		} else {
			throw new SequenceDLListException("Indexed Element out of Range");
		}
	}

	/**
	 * Returns the item at the end of the sequence.
	 */
	public Object last() throws SequenceDLListException {
		if (listTail != null) {
			return listTail.datum;
		} else {
			throw new SequenceDLListException("Indexed Element out of Range");
		}
	}

	/**
	 * Returns the item at the specified position in the sequence.
	 */
	public Object element(int index) throws SequenceDLListException {
		// Check the index is positive.
		if (index < 0) {
			throw new SequenceDLListException("Indexed Element out of Range");
		}

		// We need to chain down the list until we reach the indexed
		// position

		Node nodePointer = listHead;
		int i = 0;
		while (i < index) {
			if (nodePointer.next == null) {
				throw new SequenceDLListException("Indexed Element out of Range");
			} else {
				nodePointer = nodePointer.next;
				i += 1;
			}
		}

		return nodePointer.datum;
	}

	/**
	 * Tests whether there are any items in the sequence.
	 */
	public boolean empty() {
		return (listHead == null);
	}

	/**
	 * Returns the number of items in the sequence.
	 */
	public int size() {
		// Chain down the list counting the elements

		Node nodePointer = listHead;
		int size = 0;
		while (nodePointer != null) {
			size += 1;
			nodePointer = nodePointer.next;
		}
		return size;
	}

	/**
	 * Empties the sequence.
	 */
	public void clear() {
		listHead = null;
		listTail = null;
	}
}
