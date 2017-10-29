/*
#### Warsztat: Symulator LOTTO.

Jak wszystkim wiadomo, LOTTO to gra liczbowa polegająca na losowaniu 6 liczb z zakresu 1-49. Zadaniem gracza jest poprawne wytypowanie losowanych liczb. Nagradzane jest trafienie 3, 4, 5 lub 6 poprawnych liczb.

Napisz program, który:

 * zapyta o typowane liczby, przy okazji sprawdzi następujące warunki:
 * czy wprowadzony ciąg znaków jest poprawną liczbą,
 * czy użytkownik nie wpisał tej liczby już poprzednio,
 * czy liczba należy do zakresu 1-49,
 * po wprowadzeniu 6 liczb, posortuje je rosnąco i wyświetli na ekranie,
 * wylosuje 6 liczb z zakresu i wyświetli je na ekranie,
 * poinformuje gracza, czy trafił przynajmniej "trójkę".

Aby wylosować 6 liczb z zakresu 1-49 bez powtórzeń możemy utworzyć tablicę z wartościami 1-49, wymieszać jej zawartość i pobrać pierwsze 6 elementów.

Poniższy kod powinien Ci pomóc:
```java
Integer[] arr = new Integer[49];
for (int i = 0; i < arr.length; i++) {
	arr[i] = i;
}
System.out.println(Arrays.toString(arr));
Collections.shuffle(Arrays.asList(arr));
System.out.println(Arrays.toString(arr));
```

Możesz również losować liczby z określonego zakresu (sprawdź w snippetach jak to wykonać) - jeżeli wybierzesz takie rozwiązanie, pamiętaj o sprawdzaniu czy dana wartość nie została wcześniej wylosowana.
 */

package zadania;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main2 {

	public static void main(String[] args) {

		int[] lottery = new int[6];
		int[] win = randomGeneratorV1();
		for (int i = 0; i < lottery.length; i++) {
			lottery[i] = scannerV1();
			while (rangeCheckV1(lottery[i]) == false) {
				System.out.println("Poza zakresem!");
				lottery[i] = scannerV1();	
			}
			for (int j = 0; j < i; j++) {
				if (lottery[i] == lottery[j]) {
					System.out.println("Liczba została już wprowadzona!");
					scannerV1();
				}
			}
		}
		
		Arrays.sort(lottery);
		Arrays.sort(win);
		
		System.out.println("Wprowadzone liczby:");
		System.out.println(Arrays.toString(lottery));
		
		System.out.println("Wylosowane liczby:");
		System.out.println(Arrays.toString(win));
		
		int hit = 0;
		
		for (int i = 0; i < lottery.length; i++) {
			for (int j = 0; j < win.length; j++) {
				if (lottery[j] == win[i]) {
					hit++;
				}
			}
		}
		
		if (hit < 3) {
			System.out.println("Niestety nic nie wygrałeś");
		} else {
			System.out.println("Gratulacje, trafiłeś " + hit + " z 6 liczb!");
		}

	}

	static boolean rangeCheckV1(int a) {

		while (true) {
			if (a < 1) {
				return false;
			} else if (a > 49) {
				return false;
			} else {
				return true;
			}
		}
	}

	static int scannerV1() {

		System.out.println("Wprowadź liczbę całkowitą z zakresu 1 - 49: ");

		Scanner scan = new Scanner (System.in);

		while (!scan.hasNextInt()) {
			System.out.println("Wprowadzono niedozowlne znaki, spróbuj ponownie: ");
			scan.next();
		}
		return scan.nextInt();

	}

	static int[] randomGeneratorV1() {

		Integer[] base = new Integer[49];
		int[] winner = new int[6];

		for (int i = 0; i < base.length; i++) {
			base[i] = i;
		}

		Collections.shuffle(Arrays.asList(base));

		for (int i = 0; i < winner.length; i++) {
			winner[i] = base[i];
		}

		return winner;

	}

}
// done
