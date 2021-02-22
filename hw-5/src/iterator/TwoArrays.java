package iterator;

import java.util.Iterator;

public class TwoArrays implements Iterable<Integer> {
	private int []a1;
	private int[] a2;

	public TwoArrays(int[] a1, int[] a2) {
		this.a1=a1;
		this.a2=a2;
	}

	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() { 
			int index1 = 0, index2 = 0,where=1;
			@Override
			public boolean hasNext() { 
				return index1<a1.length || index2<a2.length;
			}

			@Override
			public Integer next() {
				if (where==1) { //print from a1
					if (index2<a2.length)//save next array to print from
						where=2;
					return a1[index1++];

				}
				else { //where=2 -- print from a2
					if (index1<a1.length)//save next array to print from
						where=1;
					return a2[index2++];
				}
			}
		};
	}
	
}
