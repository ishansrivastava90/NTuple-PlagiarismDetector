package datastructures;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Representation of N Tuple data structure of type <T>
 * @author ishan
 */
public final class NTuple<T> {

	List<T> elements;
	
	public NTuple() {
		elements = new ArrayList<T>();
	}
	
	public NTuple(List<T> list) {
		elements = new ArrayList<T>();
		for (T element : list) {
			elements.add(element);
		}
	}

	public void add(T element) {
		elements.add(element);
	}
	
	public int size() {
		return elements.size();
	}
	
	public T get(int index) {
		return elements.get(index);
	}
	
	public List<T> asList() {
		return elements;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (T word : elements) {
			builder.append(word.toString() + ", ");
		}
		return String.format("( %s )", builder.toString());
	}
	
	/**
	 * Parses a string text and returns tuples of specified size.
	 * 
	 * @param String text
	 * @param tupleSize
	 * @return List<NTuple<String>> 
	 */
	public static List<NTuple<String>> parseTuples (String text, int tupleSize) {
		List<NTuple<String>> tuples = new ArrayList<NTuple<String>>();
		
		List<String> splitwords = Arrays.asList(text.toLowerCase().split(" ")); 
		
		//Some more checking
		if(splitwords.size() < tupleSize) {
			return tuples; //return empty list
		}
		
		for(int ind = 0; ind <= splitwords.size() - tupleSize; ind++) {
			
			NTuple<String> newTuple =
				new NTuple<String>(splitwords.subList(ind, ind + tupleSize));
			
			if (newTuple.size() != 0) {
				tuples.add(newTuple);
			}
		}
		return tuples;
	}
	
	/**
	 * Determines if the specified tuples are similar or not
	 * based on criteria.
	 * 
	 * @param t1
	 * @param t2
	 * @return boolean
	 */
	public boolean isSimilar(NTuple<String> t, Map<T, List<T>> options) {
		if (elements.size() != t.size()) {
			return false;
		}
		
		for (int ind = 0; ind < t.size(); ind++) {
			
			// Exact Match 
			if (elements.get(ind).equals(t.get(ind))) {
				continue;
			}
			
			// matches one of the specified alternatives
			if (!options.isEmpty() && options.containsKey(t.get(ind)) 
				&& options.get(t.get(ind)).contains(elements.get(ind))) {
				continue;
			}
			
			return false;
		}
		
		return true;
	}
}
