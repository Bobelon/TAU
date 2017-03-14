package com.example.lab1;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	public static void main (String args[]) {
		Vector vector = new VectorImpl(Arrays.asList(1, 2, 3));
		
		// SPRAWDZENIE, CZY ASERCJE DZIAŁAJĄ:
		try {
			vector.add(new VectorImpl(Arrays.asList(1, 2, 3, 4)));
		}
		catch (AssertionError e){
			System.err.println("BŁĄD 01 - add(Vector) - Zły rozmiar wektora");
		}
		
		try {
			vector.add(new VectorImpl(new ArrayList<Integer>()));
		}
		catch (AssertionError e){
			System.err.println("BŁĄD 02 - add(Vector) - Pusty wektor");
		}
		
		try {
			vector.add(new VectorImpl(new ArrayList<Integer>()), new VectorImpl(new ArrayList<Integer>()));
		}
		catch (AssertionError e){
			System.err.println("BŁĄD 03 - add(Vector, Vector) - Oba wektory są puste");
		}
		
		try {
			vector.add(new VectorImpl(new ArrayList<Integer>()), new VectorImpl(Arrays.asList(1, 2, 3)));
		}
		catch (AssertionError e){
			System.err.println("BŁĄD 04 - add(Vector, Vector) - Pierwszy wektor pusty");
		}
		
		try {
			vector.add(new VectorImpl(Arrays.asList(1, 2, 3)), new VectorImpl(new ArrayList<Integer>()));
		}
		catch (AssertionError e){
			System.err.println("BŁĄD 05 - add(Vector, Vector) - Drugi wektor pusty");
		}
		
		try {
			vector.add(new VectorImpl(Arrays.asList(1, 2, 3, 4, 5)), new VectorImpl(Arrays.asList(1, 2, 3)));
		}
		catch (AssertionError e){
			System.err.println("BŁĄD 06 - add(Vector, Vector) - Wektory o różnych rozmiarach");
		}
		
		try {
			vector.set(new ArrayList<Integer>());
		}
		catch (AssertionError e){
			System.err.println("BŁĄD 07 - set(List) - Pusta lista");
		}
	}
}
