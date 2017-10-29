/*
#### Warsztat: Gra w zgadywanie liczb 2

Odwróćmy teraz sytuację z warsztatu "Gra w zgadywanie liczb": to użytkownik pomyśli sobie liczbę z zakresu 1-1000, a komputer będzie zgadywał i zrobi to maksymalnie w 10 ruchach (pod warunkiem, że gracz nie będzie oszukiwał).

Zadaniem gracza będzie udzielanie odpowiedzi "więcej", "mniej", "trafiłeś".

Do tego warsztatu dołączony jest schemat blokowy algorytmu. Zaimplementuj go w Javie.
![flowchart](img/flowchart.png)
 */

package zadania;

import java.util.Random;
import java.util.Scanner;

public class Main3 {

	public static void main(String[] args) {
		int min = 0;
		int max = 1000;
		int cpuTries = 10;
		int playerChoice = scannerV1();
		while (rangeCheckV1(playerChoice) == false) {
			System.out.println("Poza zakresem!");
			playerChoice = scannerV1();	
		}
		
		while (cpuTries > 0) {
			
		}
		

	}
	
	static int cpuTry(int min, int max) {
		
		Random r = new Random();
		int result = r.nextInt(max+min)-min;
		return result;
		
	}

	static boolean rangeCheckV1(int a) {

		while (true) {
			if (a < 1) {
				return false;
			} else if (a > 1000) {
				return false;
			} else {
				return true;
			}
		}
	}

	static int scannerV1() {

		System.out.println("Wprowadź liczbę całkowitą z zakresu 1 - 1000: ");

		Scanner scan = new Scanner (System.in);

		while (!scan.hasNextInt()) {
			System.out.println("Wprowadzono niedozowlne znaki, spróbuj ponownie: ");
			scan.next();
		}
		return scan.nextInt();

	}

}
