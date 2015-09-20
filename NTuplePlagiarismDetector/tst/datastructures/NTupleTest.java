package datastructures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class NTupleTest {
	
	NTuple <String> ntuple;
	@Before
	public void setUpTuple() {
		ntuple = new NTuple<>();
	}
	
	@Test
	public void testSize() {
		assertEquals(ntuple.size(), 0);
		ntuple.add("TestElement");
		ntuple.add("element2");
		
		assertEquals(ntuple.size(), 2);		
	}
	
	
	@Test
	public void testAdd() {		
		ntuple.add("TestElement");
		ntuple.add("element2");
		
		assertTrue(ntuple.get(0).equals("TestElement"));
		assertTrue(ntuple.get(1).equals("element2"));
	}
	
	@Test
	public void testParseTuples() {
		String text = "This is a test text .";		
		List<NTuple<String>> tuples = NTuple.parseTuples(text, 2);
		
		assertEquals(tuples.size(), 5);
	}

	@Test
	public void testIsSimilar() {
		
		Map<String, List<String>> tupleSyn = new HashMap<>();
		tupleSyn.put("Element1", Arrays.asList("Key1", "Word1"));
		tupleSyn.put("Key1", Arrays.asList("Element1", "Word1"));
		tupleSyn.put("Word1", Arrays.asList("Key1", "Element1"));
		
		ntuple.add("Element1");
		ntuple.add("Element2");
		
		NTuple<String> ntuple2 = new NTuple<>();
		ntuple2.add("Key1");
		ntuple2.add("Element2");
		
		assertTrue(ntuple.isSimilar(ntuple2, tupleSyn));
	}
		
}
