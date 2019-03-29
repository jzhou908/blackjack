import java.util.Scanner;

public class Play{
	public static void main(String args[]) {
		Blackjack bj = new Blackjack();
		Scanner in = new Scanner(System.in);
		boolean again = true;
		bj.start();
		while(again) {
			bj.newgame();
			bj.print();
			if(bj.hitorstandordoubledown()) {
				bj.win();
				bj.printwin();
				bj.profit();
				System.out.println("Do you want to play again? (y/n)");
				String a = in.nextLine();
				if(a.equals("y")){
					again = true;
				}
				else if(a.equals("n")){
					again = false;
				}
			}
			else {
				again = false;
			}
		}
		in.close();
	}
}
