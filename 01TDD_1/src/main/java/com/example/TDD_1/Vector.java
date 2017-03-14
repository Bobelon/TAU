package com.example.TDD_1;

import java.util.List;

public interface Vector {
	public void add(Vector vector);
	public Vector add(Vector a, Vector b);
	public List<Integer> get();
	public void set(List<Integer> list);
}
