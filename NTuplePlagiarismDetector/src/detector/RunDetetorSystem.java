package detector;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import util.Synonyms;


public final class RunDetetorSystem {
	
	public static void main(String[] args) {
		
		if (args.length < 3 || args.length > 4) {
			System.err.println("Usage: PlagiarismDetector <Synonym_file> <File1> <File2> [Tuple Size]");
			System.exit(0);
		}
		
		String synonymFilePath = args[0];		
		Map<String, List<String>> synonyms = null;
		try {
			synonyms = Synonyms.buildFromFile(synonymFilePath);
		} catch (IOException e) {
			System.err.println("Couldn't load the synonyms properly. Exiting with : "
		        + e.getMessage());
		}
		
		PlagiarismDetector detector;
		if (args.length == 4) {
			detector = new PlagiarismDetector(synonyms, Integer.parseInt(args[3]));
		} else {
			detector = new PlagiarismDetector(synonyms, 3);
		}
		
		String file1 = args[1];
		String file2 = args[2];
		try {
			System.out.println("Simlarity in file: " + file1 + " file: " + file2 + " is "
			    + detector.percentMatch(file1, file2) +" %");
		} catch (IOException e) {
			System.err.println("Exception in finding percent match using Plagiarism"
				+ " detector: " + e.getMessage());
		}

	}
}
