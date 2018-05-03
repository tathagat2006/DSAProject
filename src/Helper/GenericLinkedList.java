package Helper;

public class GenericLinkedList<T> {
	private class Node {
		T data;
		Node next;
	}

	private Node head;
	private Node tail;
	private int size;

	public GenericLinkedList() {
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size() == 0;
	}

	public void display() {
		System.out.println("---------------------------------------------");
		Node temp = this.head;

		while (temp != null) {
			System.out.print(temp.data + "\t");
			temp = temp.next;
		}

		System.out.println(".");
		System.out.println("---------------------------------------------");
	}

	public T getFirst() throws Exception {
		if (this.size == 0) {
			throw new Exception("List is empty");
		}

		return this.head.data;
	}

	public T getLast() throws Exception {
		if (this.size == 0) {
			throw new Exception("List is empty");
		}

		return this.tail.data;
	}

	public T getAt(int idx) throws Exception {
		if (this.size == 0) {
			throw new Exception("List is empty");
		}

		if (idx < 0 || idx >= this.size) {
			throw new Exception("Index out of bound");
		}

		Node temp = this.head;
		for (int i = 0; i < idx; i++) {
			temp = temp.next;
		}

		return temp.data;
	}

	private Node getNodeAt(int idx) throws Exception {
		if (this.size == 0) {
			throw new Exception("List is empty");
		}

		if (idx < 0 || idx >= this.size) {
			throw new Exception("Index out of bound");
		}

		Node temp = this.head;
		for (int i = 0; i < idx; i++) {
			temp = temp.next;
		}

		return temp;
	}

	public void addLast(T data) {
		// create
		Node nn = new Node();

		// set values of nn
		nn.data = data;
		nn.next = null;

		// attach
		if (this.size > 0) {
			this.tail.next = nn;
		}

		// update summary
		if (this.size == 0) {
			this.head = nn;
			this.tail = nn;
			this.size++;
		} else {
			this.tail = nn;
			this.size++;
		}
	}

	public void addFirst(T data) {
		// create
		Node nn = new Node();

		// set values of nn
		nn.data = data;
		nn.next = null;

		// attach
		nn.next = this.head;

		// update summary
		if (this.size > 0) {
			this.head = nn;
			this.size++;
		} else {
			this.head = nn;
			this.tail = nn;
			this.size++;
		}
	}

	public void addAt(T data, int idx) throws Exception {
		if (idx < 0 || idx > this.size) {
			throw new Exception("Index out of bound");
		}

		if (idx == 0) {
			this.addFirst(data);
		} else if (idx == this.size) {
			this.addLast(data);
		} else {
			// create
			Node nn = new Node();

			// set values of nn
			nn.data = data;
			nn.next = null;

			// attach
			Node nm1 = this.getNodeAt(idx - 1);
			Node np1 = nm1.next;

			nm1.next = nn;
			nn.next = np1;

			// update summary
			this.size++;
		}
	}

	public T removeFirst() throws Exception {
		if (this.size == 0) {
			throw new Exception("List is empty");
		}

		T rv = this.head.data;

		if (this.size == 1) {
			this.head = null;
			this.tail = null;
		} else {
			this.head = this.head.next;
		}

		this.size--;

		return rv;
	}

	public T removeLast() throws Exception {
		if (this.size == 0) {
			throw new Exception("List is empty");
		}

		T rv = this.tail.data;

		if (this.size == 1) {
			this.head = null;
			this.tail = null;
		} else {
			Node sm2 = this.getNodeAt(this.size - 2);
			sm2.next = null;
			this.tail = sm2;
		}

		this.size--;

		return rv;
	}

	public T removeAt(int idx) throws Exception {
		if (this.size == 0) {
			throw new Exception("List is empty");
		}

		if (idx < 0 || idx >= this.size) {
			throw new Exception("Index out of bound");
		}

		if (idx == 0) {
			return this.removeFirst();
		} else if (idx == this.size - 1) {
			return this.removeLast();
		} else {
			Node nm1 = this.getNodeAt(idx - 1);
			Node n = nm1.next;
			Node np1 = n.next;

			nm1.next = np1;
			this.size--;

			return n.data;
		}
	}
}
