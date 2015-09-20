package util;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to build a map of Synonyms from a file.
 * @author ishan
 */
public final class Synonyms {
	
	public static Map<String, List<String>> buildFromFile(String filePath)
			throws IOException {
		
		Path path = Paths.get(filePath);
		Map<String, List<String>> synonymMap = new HashMap<>();
		
		try (BufferedReader br = Files.newBufferedReader(path, Charset.defaultCharset())) {
			String line = "";
			while ((line = br.readLine()) != null) {
				List<String> synonymList = Arrays.asList(line.toLowerCase().split(" "));	
				
				for(String syn : synonymList) {
					if(!synonymMap.containsKey(syn.trim())) {
						synonymMap.put(syn.trim(), synonymList);
					}
				}
			}			
		} catch (IOException e) {
			System.err.println("Exception while reading the synonyms: " + e.getMessage());
			throw e;
		}
		
		return synonymMap;		
	}

}
  