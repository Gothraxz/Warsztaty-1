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
4. Wczytaj utworzony plik `popular_words.txt` i na jego podstawie utwórz plik `most_popular_words.txt`,
 który zawierać będzie 10 najbardziej popularnych słów.
5. Utwórz tablicę elementów wykluczonych np. **i**, **lub** , ewentualnie pomiń wszystkie elementy 3-znakowe. 
*/

package zadania;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
		
		fileCreator(pathOne);
		fileCreator(pathTwo);
		
		importData(pathOne, link);
		
		wordsReader(pathOne);
		
	}
	
	public static void wordsReader(Path path) {
		
		List<String> allWords = new ArrayList<>();
		
		try {
			allWords = Files.readAllLines(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// działa do momemtu uzupełnienia listy
		// dopisać filtorwanie listy i usunięcie niepotrzebnych znaków oraz krótkich słów
		// poniżej test listy
//		for (int i = 0; i < allWords.size(); i++) {
//			System.out.println(allWords.get(i));
//		}
		
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