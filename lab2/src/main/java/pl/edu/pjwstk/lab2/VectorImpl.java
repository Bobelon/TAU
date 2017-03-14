package pl.edu.pjwstk.lab2;

import java.util.ArrayList;
import java.util.List;

public class VectorImpl implements Vector {
	private List<Integer> list = new ArrayList<Integer>();

	public VectorImpl(List<Integer> list) {
		this.list = list;
	}

	public void add(Vector vector) {
		List<Integer> vectorList = vector.get();
		List<Integer> newList = new ArrayList<Integer>();
	
		if (vectorList.size() != list.size()) throw new ErrorException();
		if (vectorList.size() <= 0) throw new ErrorException();

		for(int i = 0; i < list.size(); i++) {
			newList.add(list.get(i) + vectorList.get(i));
		}
		
		this.list = newList;
	}

	public Vector add(Vector a, Vector b) {
		List<Integer> listA = a.get();
		List<Integer> listB = b.get();
		List<Integer> listC = new ArrayList<Integer>();

		if (listA.size() != listB.size()) throw new ErrorException();
		if (listA.size() <= 0) throw new ErrorException();
		if (listB.size() <= 0) throw new ErrorException();

		for(int i = 0; i < listA.size(); i++) {
			listC.add(listA.get(i) + listB.get(i));
		}

		if (listC.size() != listA.size()) throw new ErrorException();

		return new VectorImpl(listC);
	}

	public Vector sub(Vector a, Vector b) {
		List<Integer> listA = a.get();
		List<Integer> listB = b.get();
		List<Integer> listC = new ArrayList<Integer>();

		if (listA.size() != listB.size()) throw new ErrorException();
		if (listA.size() <= 0) throw new ErrorException();
		if (listB.size() <= 0) throw new ErrorException();

		for(int i = 0; i < listA.size(); i++) {
			listC.add(listA.get(i) - listB.get(i));
		}

		if (listC.size() != listA.size()) throw new ErrorException();

		return new VectorImpl(listC);
	}

	public List<Integer> get() {
		return list;
	}

	public void set(List<Integer> list) {
		if (list.size() <= 0) throw new ErrorException();
		this.list = list;
	}
}