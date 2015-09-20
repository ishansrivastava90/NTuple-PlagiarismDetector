package detector;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import datastructures.NTuple;

/**
 * Class to compare files for similarity with synonyms using
 * the N Tuple Plagiarism Detection Algorithm.
 * 
 * @author ishan
 */
public class PlagiarismDetector {

	private final Map<String, List<String> > synonyms;
	private final int tupleSize;
	
	// Default Comparison of exact word match
	public PlagiarismDetector() {
		this.synonyms = new HashMap<>();
		this.tupleSize = 3;
	}
	
	public PlagiarismDetector(Map<String, List<String> > synonyms,
			int tupleSize) {
		this.synonyms = synonyms;
		this.tupleSize = tupleSize;		
	}
	
	public boolean isCopied(String file1, String file2, int threshold) 
		throws IOException {
		return calculateSimilarity(file1, file2) > threshold;
	}
	
	/**
	 * Calculates the num of tuple matches in the files.
	 * 
	 * @param String filename1
	 * @param String filename2
	 * @return # of Matches
	 * @throws IOException 
	 */
	public int calculateSimilarity(String file1, String file2) 
		throws IOException {
		int matches = 0;
		try {
			List<NTuple<String>> list1 = getAllTuples(file1);
			List<NTuple<String>> list2 = getAllTuples(file2);
			
			// Unordered matching of tuples across both lists
			for(NTuple<String> tup1: list1) {
				for(NTuple<String> tup2: list2) {
					
					if(tup1.isSimilar(tup2, synonyms)) {						
						matches++;
						break;
					}
				}
			}
		} catch (IOException e) {
			System.err.println("Exception in calculating similarity: " + e.getMessage());
			throw e;
		}
		return matches;
	}
	
	public double percentMatch(String file1, String file2) 
		throws IOException {
		List<NTuple<String>> list1 = new ArrayList<>();
		try {
			list1 = getAllTuples(file1);
			
		} catch (IOException e) {
			System.err.println("Exception in finding percent Match: " + e.getMessage());
			throw e;
		}
		return (calculateSimilarity(file1, file2) * 100)/ list1.size();
	}
	
	/**
	 * Form all the tuples from a specified file
	 *   
	 * @param filePath
	 * @return List<NTuple<String>>
	 * @throws IOException
	 */
	private List<NTuple<String>> getAllTuples(String filePath) throws IOException {
		Path path = Paths.get(filePath);
		
		List<NTuple<String>> tuples = new ArrayList<NTuple<String>>();
		try (BufferedReader br = Files.newBufferedReader(path, Charset.defaultCharset())) {
			
			String line;
			while ((line = br.readLine()) != null) {			
				tuples.addAll(NTuple.parseTuples(line, tupleSize));
			}
		}
		return tuples;
	}
}