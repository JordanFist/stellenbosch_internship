class box {
	public int value;
	public box next, previous;

	public box (int value, box next, box previous) {
		this.value = value;
		this.next = next;
		this.previous = previous;
	}
}

class link {
	public box head;
	public box tail;

	public link (box head, box tail) {
		this.head = head;
		this.tail = tail;
	}
}

class set {
	public link l;

	public set (link l) {
		this.l = l;
	}
}

public class linkedList {
	public static void display(set s) {
		box b = s.l.head;
		while(b != null) {
			System.out.println(b.value);
			b = b.next;	
		}
	}
	public static boolean isEndMark(box b) {
		return (b == null);		
	}
    	public static void addHead(set s, int value) {
		box b1 = new box(value, null, null);
        	if (isEndMark(s.l.head) == true && isEndMark(s.l.tail) == true)
			s.l.tail = b1;
		b1.next = s.l.head;
		s.l.head = b1;
		if (isEndMark(b1.next) == false)
			b1.next.previous = b1;
    	}
	
    	public static void addTail(set s, int value) {
        	box b1 = new box(value, null, null);
		if (isEndMark(s.l.head) == true && isEndMark(s.l.tail) == true)
			s.l.head = b1;
		b1.previous = s.l.tail;	
		s.l.tail = b1;
		if (isEndMark(b1.previous) == false)
			b1.previous.next = b1;
    	}

    	public static box removeHead(set s) {
        	box b1 = s.l.head;
		if (s.l.head != null) {
			s.l.head = s.l.head.next;
			s.l.head.previous = b1.previous;
			b1.next = null;
			b1.previous = null;		
		}
		return b1;
    	}

    	public static box removeTail(set s) {
        	box b1 = s.l.tail;
		if (s.l.tail != null) {
			s.l.tail = s.l.tail.previous;
			s.l.tail.next = b1.next;
			b1.next = null;	
			b1.previous = null;
		}
		return b1;
    	}

    	public static void main(String[] args) {
		link l1 = new link(null, null);		
		set s1 = new set(l1);
		addHead(s1, 1);
		addTail(s1, 2);
		addTail(s1, 3);
		addHead(s1, 0);
		display(s1);
		removeHead(s1);
		removeTail(s1);
		System.out.println();
		display(s1);
    	}
}
