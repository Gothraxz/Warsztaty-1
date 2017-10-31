/*
#### Warsztat: Gra w zgadywanie liczb.

Napisz prostą grę w zgadywanie liczb. Komputer musi wylosować liczbę w zakresie od 1 do 100. Następnie:

1. Zadać pytanie: "Zgadnij liczbę" i pobrać liczbę z klawiatury.
2. Sprawdzić, czy wprowadzony napis, to rzeczywiście liczba i w razie błędu wyświetlić komunikat "To nie jest liczba", po czym wrócić do pkt. 1
3. Jeśli liczba podana przez użytkownika jest mniejsza niż wylosowana, wyświetlić komunikat "Za mało!", po czym wrócić do pkt. 1.
4. Jeśli liczba podana przez użytkownika jest większa niż wylosowana, wyświetlić komunikat "Za dużo!", po czym wrócić do pkt. 1.
5. Jeśli liczba podana przez użytkownika jest równa wylosowanej, wyświetlić komunikat "Zgadłeś!", po czym zakończyć działanie programu.

##### Przykład:
```
Zgadnij liczbę: cześć
To nie jest liczba.
Zgadnij liczbę: 50
Za mało!
Zgadnij liczbę: 75
Za dużo!
Zgadnij liczbę: 63
Zgadłeś!
```
 */

package zadania;

import java.util.Random;
import java.util.Scanner;

public class Main1 {

	public static void main(String[] args) {

		gameV1();

	}

	public static void gameV1() {

		Random r = new Random();
		int result = r.nextInt(101);
		int guess2 = scannerV1();
		
		while (true) {
			if (guess2 < 1) {
				System.out.println("Poza zakresem!");
				guess2 = scannerV1();
			} else if (guess2 > 100) {
				System.out.println("Poza zakresem!");
				guess2 = scannerV1();
			} else if (guess2 < result) {
				System.out.println("Za mało");
				guess2 = scannerV1();
			} else if (guess2 > result) {
				System.out.println("Za dużo");
				guess2 = scannerV1();
			} else {
				System.out.println("Trafiłeś!");
				break;
			}
		}
		
	}

	static int scannerV1() {
		
		System.out.println("Wprowadź liczbę całkowitą z zakresu 1 - 100: ");
		
		Scanner scan = new Scanner (System.in);
		int guess = 0;
		
		while (!scan.hasNextInt()) {
			System.out.println("Wprowadzono niedozwolne znaki, spróbuj ponownie: ");
			scan.next();
		}
		return guess = scan.nextInt();
		
	}

}
// done
