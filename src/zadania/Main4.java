/*
#### Warsztat: Kostka do gry

W grach planszowych i papierowych RPG używa się wielu rodzajów kostek do gry, nie tylko tych dobrze znanych, sześciennych. Jedną z popularniejszych kości jest np. kostka dziesięciościenna, a nawet stuścienna! Jako że w grach kośćmi rzuca się często, pisanie za każdym razem np. _"rzuć dwiema kostkami dziesięciościennymi, a do wyniku dodaj 20"_ byłoby nudne, trudne i marnowałoby ogromne ilości papieru. W takich sytuacjach używa się kodu _"rzuć 2D10+20"_.

Kod takiej kostki wygląda następująco:

### xDy+z

gdzie:
* __y__ &ndash; rodzaj kostek, których należy użyć (np. D6, D10),
* __x__ &ndash; liczba rzutów kośćmi; jeśli rzucamy raz, ten parametr jest pomijalny,
* __z__ &ndash; liczba, którą należy dodać (lub odjąć) do wyniku rzutów (opcjonalnie).

__Przykłady:__

* __2D10+10__: 2 rzuty D10, do wyniku dodaj 10,
* __D6__: zwykły rzut kostką sześcienną,
* __2D3__: rzut dwiema kostkami trójściennymi,
* __D12-1__: rzut kością D12, od wyniku odejmij 1.

Napisz funkcję, która:

* przyjmie w parametrze taki kod w postaci String,
* rozpozna wszystkie dane wejściowe:
    * rodzaj kostki,
    * liczbę rzutów,
    * modyfikator,
* wykona symulację rzutów i zwróci wynik.

Typy kostek występujące w grach: D3, D4, D6, D8, D10, D12, D20, D100.
*/

package zadania;

import java.util.Scanner;

public class Main4 {

	public static void main(String[] args) {
		
		String[] dice = {"D3", "D4", "D6", "D8", "D10", "D12", "D20", "D100"};
		int x = 1;
		int y = 0;
		int z = 0;
		char mod = '+';
		String code = "";

		System.out.println("Podaj liczbę rzutów");
		x = scannerV1();
		
		System.out.println("Wprowadź liczbę ścian kości z wymienionych poniżej:\n"
				+ "3, 4, 6, 8, 10, 12, 20, 100");
		y = diceChecker();
		
		System.out.println("Wprowadź symbol modyfikatora rzutu lub 0 by pominąć ten krok:");
		mod = throwMod();
		
		if (mod != '0') {
			System.out.println("Podaj wartość o jaką rzut zostanie zmodyfikowany:");
			z = scannerV1();
		}
		
		System.out.println("Wynik dla rzutu " + diceCode(x, y, z, mod) + " to: \n");
		
	}
	
	public static String diceCode(int x, int y, int z, char mod) {
		// generuje kod rzutu - działa
		StringBuffer generator = new StringBuffer();
		
		if (x > 1) {
			generator.append(x);
		}
		
		generator.append('D');
		generator.append(y);
		
		if (z != 0) {
			generator.append(mod);
			generator.append(z);
		}
		String result = generator.toString();
		return result;
	}

	public static int diceThrow(String input) {
		// czyta kod i zwraca wnik rzutu - do zrobienia
		return 0;
	}
	
	public static int scannerV1() {

		Scanner scan = new Scanner(System.in);

		while (!scan.hasNextInt()) {
			System.out.println("Wproadzono niedozwolone znaki, spróbuj ponownie: ");
			scan.next();
		}
		return scan.nextInt();

	}
	
	public static int diceChecker() {
//		sprawdza czy wprowadzono poprawną liczbę ścian - działa
		int dice = scannerV1();
		while (true) {
			if (dice == 3 ||
				dice == 4 ||
				dice == 6 ||
				dice == 8 ||
				dice == 10 ||
				dice == 12 ||
				dice == 20 ||
				dice == 100) {
					return dice;
			} else {
				System.out.println("Niestety kość o takiej liczbie ścian nie istnieje, spróbuj ponownie: ");
				dice = scannerV1();
			}
		}
	}
	
	public static char throwMod() {
		Scanner scan = new Scanner(System.in);
		
		while(!scan.hasNext("[-+/*0]")) {
			scan.next();
			System.out.println("Wprowadź poprawny symbol działania matematycznego");
		}
		return scan.next().charAt(0);
	}
}
