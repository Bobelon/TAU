package com.example.TDD_1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.*;

public class VectorTest {
	@Test
	// do wektora "vector" dodaję nowy wektor
	public void add_to_vector() {		
		Vector vector = new VectorImpl(Arrays.asList(1, 2, 3, 4));
		
		vector.add(new VectorImpl(Arrays.asList(2, 3, 4, 5)));		
		assertEquals(Arrays.asList(3, 5, 7, 9), vector.get());
		
		vector.add(new VectorImpl(Arrays.asList(2, 1, 0, -1)));		
		assertEquals(Arrays.asList(5, 6, 7, 8), vector.get());
		
		vector.add(new VectorImpl(Arrays.asList(10, 10, 10, -10)));		
		assertEquals(Arrays.asList(15, 16, 17, -2), vector.get());
	}
	
	@Test(expected = AssertionError.class)
	// do wektora "vector" dodaję nowy wektor, który ma więcej elementów, więc powinien być błąd
	public void add_to_vector_error() {		
		Vector vector = new VectorImpl(Arrays.asList(1, 2, 3));

		vector.add(new VectorImpl(Arrays.asList(2, 3, 4, 5)));
	}
	
	@Test
	// wektor "vectorC" jest sumą wektorów "vectorA" i "vectorB"
	public void add_two_vectors() {
		Vector vectorC = new VectorImpl(null);
		
		vectorC = vectorC.add(new VectorImpl(Arrays.asList(10, 10, 10)), new VectorImpl(Arrays.asList(20, 30, 45)));		
		assertEquals(Arrays.asList(30, 40, 55), vectorC.get());
		
		vectorC = vectorC.add(new VectorImpl(Arrays.asList(-100, 0, 500)), new VectorImpl(Arrays.asList(90, 80, 100)));		
		assertEquals(Arrays.asList(-10, 80, 600), vectorC.get());
		
		vectorC = vectorC.add(new VectorImpl(Arrays.asList(1, 2, 3, 4, 5, 6)), new VectorImpl(Arrays.asList(6, 5, 4, 3, 2, 1)));		
		assertEquals(Arrays.asList(7, 7, 7, 7, 7, 7), vectorC.get());
	}
	
	@Test
	// ustawienie wartości wektora na nową
	public void setters_test() {
		Vector vector = new VectorImpl(Arrays.asList(1, 1, 1));
		
		vector.set(Arrays.asList(2, 2, 2, 3));		
		assertEquals(Arrays.asList(2, 2, 2, 3), vector.get());
		
		vector.set(Arrays.asList(1));		
		assertEquals(Arrays.asList(1), vector.get());
		
		vector.set(Arrays.asList(50, 70, -9));		
		assertEquals(Arrays.asList(50, 70, -9), vector.get());
	}
	
	@Test (expected = AssertionError.class)
	// ustawienie wartości wektora na nową, ale błędną
	public void setters_test_error() {
		Vector vector = new VectorImpl(Arrays.asList(1, 1, 1));
		
		vector.set(new ArrayList<Integer>());
	}
	
	@Test
	public void getters_test() {
		Vector vector = new VectorImpl(Arrays.asList(9, 8, 7, 6, 1));
		
		List<Integer> list = vector.get();
		
		assertEquals(Arrays.asList(9, 8, 7, 6, 1), list);
	}
}
