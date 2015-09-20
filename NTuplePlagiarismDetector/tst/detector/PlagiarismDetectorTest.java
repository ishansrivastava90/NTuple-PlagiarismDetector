package detector;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import util.Synonyms;

@RunWith(JUnit4.class)
public class PlagiarismDetectorTest {

	private Map<String, List<String>> synonyms;
	
	@Before
	public void setUpSynonyms() {
		try {
			synonyms = Synonyms.buildFromFile("tst/samples/synonyms.txt");
		} catch (IOException e) {
			System.err.println("Couldn't load the synonyms properly. Exiting with : "
		        + e.getMessage());
		}
	}
	
	@Test
	public void testCalculateSimilarity() throws IOException {
		
		PlagiarismDetector detector = new PlagiarismDetector(synonyms, 3);
		
		assertEquals(detector.calculateSimilarity("tst/samples/abc.txt",
				"tst/samples/xyz.txt"), 9);	   
	  }
	
	@Test
	public void testIsCopied() throws IOException {
		PlagiarismDetector detector = new PlagiarismDetector(synonyms, 3);
		assertTrue(detector.isCopied("tst/samples/abc.txt", "tst/samples/xyz.txt", 5));
	}
}
