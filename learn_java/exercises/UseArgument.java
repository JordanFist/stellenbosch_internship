import java.util.ArrayList;
import java.util.Random;

public class UseArgument {
	public static void displayTab(int tab[]){
		for (int i = 0; i < 10; ++i)
			System.out.println(tab[i]);
		System.out.println();	
	}
	public static void shuffle(int tab[]) {
		int swap,n;
		Random rand = new Random(); 
		for (int i = 0; i < 10; ++i) {
			n = rand.nextInt(10);	
			swap = tab[n];
			tab[n] = tab[i];
			tab[i] = swap;
	}

	}
	public static void main(String[] args) {
		int tab[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	
		/* To add if functions above are not static
		UseArgument myTab = new UseArgument();
		myTab.displayTab(tab);
		myTab.shuffle(tab);
		myTab.displayTab(tab);
		*/

		displayTab(tab);
		shuffle(tab);
		displayTab(tab);	
	}
}
