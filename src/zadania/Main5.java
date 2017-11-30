/*
#### Warsztat: Wyszukiwarka najpopularniejszych słów

1. Zaimportuj do projektu bibliotekę jsoup, możesz ją pobrać z adresu: https://jsoup.org/download.
2. Wyszukaj w popularnych serwisach internetowych nagłówków artykułów,
 a następnie zapisz pojedyncze słowa w nich występujące do pliku o nazwie `popular_words.txt`.
Przykład pobrania tytułów z tagu html **span** z atrybutem class o wartości **title**
````java
Connection connect = Jsoup.connect("http://www.onet.pl/");
try {
    Document document = connect.get();
    Elements links = document.select("span.title");
    for (Element elem : links) {
        System.out.println(elem.text());
    }
} catch (IOException e) {
    e.printStackTrace();
}

````
3. Wywołaj pobieranie dla wybranych serwisów internetowych.
4. Wczytaj utworzony plik `popular_words.txt` i na jego podstawie utwórz plik 
`most_popular_words.txt`, który zawierać będzie 10 najbardziej popularnych słów.
5. Utwórz tablicę elementów wykluczonych np. **i**, **lub** , 
ewentualnie pomiń wszystkie elementy 3-znakowe. 
*/

package zadania;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main5 {

	public static void main(String[] args) {
		
		Path pathOne = Paths.get("src/zadania", "popular_words.txt");
		Path pathTwo = Paths.get("src/zadania", "most_popular_words.txt");
		
		String link = "http://www.onet.pl/";
		
		String[] ban = {"lub", "oraz", "także", "od", "do", "aż", "za", "się", 
				"nad", "ws", "na", "to", "ma", "że", "tam", "co", "będą", "będzie", "jest", 
				"aby", "ale", "są"};
		
		fileCreator(pathOne);
		fileCreator(pathTwo);
		
		importData(pathOne, link);
		
		wordsReader(pathOne, pathTwo, link, ban);
		
	}
	
	public static void wordsReader(Path path, Path path2, String link, String[] ban) {

		String allWords = "";

		try {
			allWords = Files.readAllLines(path).toString().toLowerCase();
		} catch (IOException e) {
			e.printStackTrace();
		}

		StringTokenizer tok = new StringTokenizer(allWords, " _-=+,.!?;:[]()&1234567890„\"\'");

//		String[] ban = {"lub", "oraz", "także", "od", "do", "aż", "za", "się", 
//				"nad", "ws", "na", "to", "ma", "że", "tam", "co", "będą", "będzie", "jest", 
//				"aby", "ale", "są"};

		ArrayList<String> words = new ArrayList<>();
		String[] temp = new String[tok.countTokens()];

		int tempI = 0;
		while (tok.hasMoreTokens()) {
			temp[tempI] = tok.nextToken();
			tempI++;
		}

		for (int i = 0; i < temp.length; i++) {
			boolean wordExist = false;
			boolean wordIsBanned = false;

			for (int j = 0; j < words.size(); j++) {
				if (words.get(j).toString().equals(temp[i])) {
					wordExist = true;
					break;
				}
			}
			
			for (int k = 0; k < ban.length; k++) {
				if (temp[i].equalsIgnoreCase(ban[k]) || temp[i].length() < 2) {
					wordIsBanned = true;
				}
			}

			if (wordExist == false && wordIsBanned == false) {
				words.add(temp[i]);
			}

		}
		
		words.sort(null);

		String[][] result = new String[words.size()][words.size()];
		int[] resultCount = new int[words.size()];

		for (int i = 0; i < result.length; i++) {
			result[i][0] = words.get(i);
		}
		
		Arrays.fill(resultCount, 0);
		
		for (int i = 0; i < resultCount.length; i++) {
			for (int j = 0; j < temp.length; j++) {
				if (result[i][0].equals(temp[j])) {
					resultCount[i]++;
				}
			}
		}
		
		for (int i = 0; i < result.length; i++) {
			result[i][1] = resultCount[i]+"";
		}

		int[] tenHighest = new int[10];
		int max = 0;
		int index = 0;
		
		for (int i = 0; i < 10; i++) {
			max = resultCount[0];
			for (int j = 1; j < resultCount.length; j++) {
				if (max < resultCount[j]) {
					max = resultCount[j];
					index = j;
				}
			}
			tenHighest[i] = index;
			resultCount[index] = Integer.MIN_VALUE;
			
		}
		
		try {
			Files.write(path2, ("Najpopularniejsze słowa z portalu " + link 
					+ " wraz z liczbą wystąpień:\n\n").getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		for (int i = 0; i < tenHighest.length; i++) {
			String line = result[tenHighest[i]][0] + ": " + result[tenHighest[i]][1] + "\n";
			try {
				Files.write(path2, line.getBytes(), StandardOpenOption.APPEND);
				System.out.print("Dodano: " + line);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void fileCreator(Path path) {
		
		if (!Files.exists(path)) {
			try {
				Files.createFile(path);
			} catch (IOException e) {
				e.printStackTrace();
			}	
		} else {
			try {
				Files.delete(path);
				Files.createFile(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void importData(Path path, String link) {
		Connection connect = Jsoup.connect(link);
		try {
		    Document document = connect.get();
		    Elements links = document.select("span.title");
		    for (Element elem : links) {
		        String line = elem.text() + "\n";
		    	Files.write(path, line.getBytes(), StandardOpenOption.APPEND);
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}

}
//done