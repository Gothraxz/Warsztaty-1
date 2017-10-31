package zadania;

import java.util.Scanner;

public class Poligon {

	public static void main(String[] args) {

//		scanTextV1();
//		scanTextV2();
//		scanTextV3();
		System.out.println(scanTextV2());

	}
	// nie działa
	static String scanTextV1() {

		Scanner scan = new Scanner(System.in);

		while(!scan.hasNext("Za mało") || !scan.hasNext("Za dużo") || !scan.hasNext("Trafiłeś")) {
			System.out.println("Wprowadź poprawną komendę");
			scan.next();
		}
		return scan.next().toString();
	}
	// działa jeżeli nie ma spacji w warunkach
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
	// nie działą poprawnie
	static String scanTextV3() {
		Scanner scan = new Scanner(System.in);
		String temp = scan.nextLine();
		
		while (!temp.equals("Za mało") || !temp.equals("Za dużo") || !temp.equals("Trafiłeś")) {
			temp = scan.nextLine();
			System.out.println("Wprowadź poprawną komendę");
		}
		return temp;
	}

}
