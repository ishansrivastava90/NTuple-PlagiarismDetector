package util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public final class SynonymsTest {
	
	@Before
	public void setUpSynonyms() {
	}
	
	@Test
	public void testBuildFromFile() {
		Map<String, List<String>> synonyms = null;

		try {
			synonyms = Synonyms.buildFromFile("tst/samples/synonyms.txt");
		} catch (IOException e) {
			System.err.println("Couldn't load the synonyms properly. Exiting with : "
		        + e.getMessage());
			assertTrue(false);
		}
		
		List <String> words = Arrays.asList("copy", "plagiarism", "great", "awesome",
		                                     "search", "find", "look", "table", "desk", "top");
		assertEquals(synonyms.size(), 10);
		
		for (String word : words) {
			assertTrue(synonyms.containsKey(word));
		}
	}
}
