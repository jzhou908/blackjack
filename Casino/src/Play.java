import java.util.Scanner;

public class Play{
	public static void main(String args[]) {
		Blackjack bj = new Blackjack();
		Scanner in = new Scanner(System.in);
		System.out.println("How much money do you have?");
		int balance = in.nextInt();
		in.nextLine();
		System.out.println("How much money do you wanna bet?");
		int bet = in.nextInt();
		in.nextLine();
		if(bet>balance) {
			System.out.println("Please bet an amount less than your balance.");
			return;
		}
		bj.newgame(bet);
		bj.print();
		System.out.println("Do you wanna hit or stand?");
		String answer = in.nextLine();
		bj.hitorstand(answer);
		bj.win();
		bj.printwin();
		bj.profit(bet);
		in.close();
	}
}