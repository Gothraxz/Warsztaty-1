/*
#### Warsztat: Gra w zgadywanie liczb 2

Odwróćmy teraz sytuację z warsztatu "Gra w zgadywanie liczb": to użytkownik pomyśli sobie liczbę z zakresu 1-1000, a komputer będzie zgadywał i zrobi to maksymalnie w 10 ruchach (pod warunkiem, że gracz nie będzie oszukiwał).

Zadaniem gracza będzie udzielanie odpowiedzi "więcej", "mniej", "trafiłeś".

Do tego warsztatu dołączony jest schemat blokowy algorytmu. Zaimplementuj go w Javie.
![flowchart](img/flowchart.png)
 */

package zadania;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main3 {

	public static void main(String[] args) {
		int min = 1;
		int max = 1000;
		int[] range = {min, max};
		int cpuTries = 10;
		int playerChoice = scannerV1();
		while (rangeCheckV1(playerChoice) == false) {
			System.out.println("Poza zakresem!");
			playerChoice = scannerV1();	
		}
		
		while (cpuTries > 0) {
			System.out.println("Wprowadź wskazówkę z poniższej listy:\nZa mało, Za dużo, Trafiłeś");
			int temp = cpuTry(range[0], range[1]);
			if (range[0] == playerChoice && range[1] == playerChoice) {
				System.out.println("CPU wygrało!"); // wykoleja się przy trafieniu wyniku
				break;
			} else {
				range = cpuRange(scanTextV2(), range, temp);
				System.out.println("Zakres: " + Arrays.toString(range) + ", wybór gracza: " + playerChoice);
			}
			cpuTries--;
			System.out.println("Pozostało prób: " + cpuTries);
		}
		

	}
	
	static int[] cpuRange(String command, int[] range, int result) {
		int[] newRange = new int[2];
		switch (command) {
		case "Za mało":
			newRange[0] = result;
			newRange[1] = range[1];
			break;
		case "Za dużo":
			newRange[0] = range[0];
			newRange[1] = result;
			break;
		case "Trafiłeś":
			newRange[0] =  result;
			newRange[1] = result;
			break;
		}
		return newRange;
		
	}
	
	static int cpuTry(int min, int max) {
		
		Random r = new Random();
		int result = r.nextInt(max - min)+min;
		System.out.println("Wylosowano: " + result);
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

		Scanner scan = new Scanner(System.in);

		while (!scan.hasNextInt()) {
			System.out.println("Wprowadzono niedozowlne znaki, spróbuj ponownie: ");
			scan.next();
		}
		return scan.nextInt();

	}
	
	static String scanTextV2() {

		Scanner scan = new Scanner(System.in);
		String temp = "";
		while(true) {
			if (temp.equals("Za mało")) {
				break;
			} else if (temp.equalsIgnoreCase("Za dużo")) {
				break;
			} else if (temp.equals("Trafiłeś")) {
				break;
			} else {
				System.out.println("Wprowadź poprawną komendę: ");
				temp = scan.nextLine().toString();
			}
		}
		return temp;
	}
	

}
