package com.example.TDD_1;

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
		
		assert vectorList.size() == list.size();
		assert vectorList.size() > 0;
		
		for(int i = 0; i < list.size(); i++) {
			newList.add(list.get(i) + vectorList.get(i));
		}
		
		this.list = newList;
	}

	public Vector add(Vector a, Vector b) {
		List<Integer> listA = a.get();
		List<Integer> listB = b.get();
		List<Integer> listC = new ArrayList<Integer>();
		
		assert listA.size() == listB.size();
		assert listA.size() > 0;
		assert listB.size() > 0;
		
		for(int i = 0; i < listA.size(); i++) {
			listC.add(listA.get(i) + listB.get(i));
		}
		
		assert listC.size() == listA.size();
		
		return new VectorImpl(listC);
	}

	public List<Integer> get() {
		return list;
	}

	public void set(List<Integer> list) {
		assert list.size() > 0;
		this.list = list;
	}

}
