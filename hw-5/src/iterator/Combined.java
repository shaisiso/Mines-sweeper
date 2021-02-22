package iterator;


import java.util.Iterator;


public class Combined<E> implements Iterable<E>{
	private Iterable<E> first;
	private Iterable<E> second;
	public Combined(Iterable<E> first, Iterable<E> second) {
		this.first=first;
		this.second =second;
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			int where=1;
			Iterator<E> a1=first.iterator();
			Iterator<E> a2=second.iterator();
			@Override
			public boolean hasNext() {
				return (a1.hasNext() || a2.hasNext());
			}

			@Override
			public E next() {
				E res;
				if (where==1) { //return from first
					if (a2.hasNext())
						where=2;
					res=a1.next();
					return res;
				}
				else {//where=2--return from second
					if (a1.hasNext())
						where=1;
					res=a2.next();
					return res;
				}
			}
			
		};
	}



}
