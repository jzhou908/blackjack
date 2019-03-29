import java.util.*;
import java.util.Scanner;
public class Blackjack{
	private int balance;
	private int bet;
	private ArrayList<Card> yourhand;
	private ArrayList<Card> dealerhand;
	private Scanner in = new Scanner(System.in);
	private Deck b;
	private static final String[] RANKS =
		{"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};
	private static final String[] SUITS =
		{"spades", "hearts", "diamonds", "clubs"};
	private static final int[] POINT_VALUES =
		{11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
	public void start() {
		System.out.println("How much money do you have?");
	    balance = in.nextInt();
		in.nextLine();
		while(balance <= 0) {
			System.out.println("Please input more money! How much money do you have?");
		    balance = in.nextInt();
			in.nextLine();
		}
	}
	public void newgame() {
		b = new Deck(RANKS, SUITS, POINT_VALUES);
		b.shuffle();
		yourhand = new ArrayList<Card>();
		yourhand.add(b.deal());
		yourhand.add(b.deal());
		dealerhand = new ArrayList<Card>();
		dealerhand.add(b.deal());
		dealerhand.add(b.deal());
		System.out.println("How much money do you wanna bet?");
		bet = in.nextInt();
		in.nextLine();
		while(bet>balance) {
			System.out.println("Please bet an amount less than your balance. Try again.");
			bet = in.nextInt();
			in.nextLine();
		}
	}
	public void print() {
		System.out.print("Your hand is: ");
		for(Card card: yourhand){
			if(yourhand.get(yourhand.size()-1).equals(card)) {
				System.out.println(card.rank() + ".");
			}
			else {
				System.out.print(card.rank() + ", ");
			}
		}
		System.out.println("The value of your hand is: " + value(yourhand));
		System.out.print("The dealer's first card is: ");
		System.out.println(dealerhand.get(0).rank());
	}
	public void printwin() {
		System.out.println("Your hand is: ");
		for(Card card: yourhand){
			if(yourhand.get(yourhand.size()-1).equals(card)) {
				System.out.println(card.rank() + ".");
			}
			else {
				System.out.print(card.rank() + ", ");
			}
		}
		System.out.println("The value of your hand is: " + value(yourhand));
		System.out.println("The dealer's hand is: ");
		for(Card card: dealerhand){
			if(dealerhand.get(dealerhand.size()-1).equals(card)) {
				System.out.println(card.rank() + ".");
			}
			else {
				System.out.print(card.rank() + ", ");
			}
		}
		System.out.println("The value of the dealer's hand is: " + value(dealerhand));
	}
	public ArrayList<Card> Sort(ArrayList<Card> a){
		int n = a.size(); 
        for (int i = 0; i < n-1; i++) 
            for (int j = 0; j < n-i-1; j++) 
                if (a.get(j).pointValue() > a.get(j+1).pointValue()) { 
                    Card temp = a.get(j); 
                    a.set(j, a.get(j+1)); 
                    a.set(j+1, temp);
                }
        return a;
	}
	public int value(ArrayList<Card> a) {
		int sum = 0;
		ArrayList<Card> sorted = Sort(a);
		for(int i = 0; i < a.size(); i++) {
			if(sorted.get(i).rank().equals("ace") && sum+11 > 21) {
				sum += 1;
			}
			else {
				sum += sorted.get(i).pointValue();
			}
		}
		return sum;
	}
	public boolean doubledown() {
		if(balance - bet >= bet) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean hitorstandordoubledown() {
		if(doubledown() && value(yourhand) < 21) {
			System.out.println("Do you wanna hit or stand or double down?");
			String answer = in.nextLine();
			if(!answer.equalsIgnoreCase("hit") && !answer.equalsIgnoreCase("stand") && !answer.equalsIgnoreCase("double down")) {
				System.out.println("Incorrect input, please put hit or stand or double down.");
				return false;
			}
			if(answer.equals("double down")) {
				System.out.println("How much more do you wanna bet?");
				int bet2 = in.nextInt();
				in.nextLine();
				while(bet2 > bet) {
					System.out.println("Make sure your bet is less than or equal to your original bet. Try Again.");
					bet2 = in.nextInt();
					in.nextLine();
				}
				yourhand.add(b.deal2());
				bet = bet+bet2;
				return true;
			}
			while(answer.equalsIgnoreCase("hit")) {
				yourhand.add(b.deal2());
				System.out.println("Your hand's value is: " + value(yourhand));
				if(value(yourhand) == 21) {
					return true;
				}
				if(value(yourhand) > 21) {
					return true;
				}
				System.out.println("Do you want to hit or stand now?");
				answer = in.nextLine();
				if(!answer.equalsIgnoreCase("hit") && !answer.equalsIgnoreCase("stand")) {
					System.out.println("Incorrect input, please put hit or stand.");
					return false;
				}
			}
		}
		else if(value(yourhand) < 21){
			System.out.println("Do you wanna hit or stand?");
			String answer = in.nextLine();
			if(!answer.equalsIgnoreCase("hit") && !answer.equalsIgnoreCase("stand")) {
				System.out.println("Incorrect input, please put hit or stand.");
				return false;
			}
			while(answer.equalsIgnoreCase("hit")) {
				yourhand.add(b.deal2());
				System.out.println("Your hand's value is: " + value(yourhand));
				if(value(yourhand) == 21) {
					return true;
				}
				if(value(yourhand) > 21) {
					return true;
				}
				System.out.println("Do you want to hit or stand now?");
				answer = in.nextLine();
				if(!answer.equalsIgnoreCase("hit") && !answer.equalsIgnoreCase("stand")) {
					System.out.println("Incorrect input, please put hit or stand.");
					return false;
				}
			}
		}
		else {
			return true;
		}
		return false;
	}
	public boolean win() {
		if(value(yourhand) > 21) {
			System.out.println("You lose.");
			return false;
		}
		while(value(dealerhand) < 17) {
			dealerhand.add(b.deal());
		}
		if(value(yourhand) == 21 && yourhand.size() == 2) {
			if(value(dealerhand) == 21 && dealerhand.size() == 2) {
				System.out.println("TIE");
				return false;
			}
			else {
				System.out.println("You WON!");
				return true;
			}
		}
		else if(value(yourhand) == 21){
			if(value(dealerhand) == 21) {
				System.out.println("TIE");
				return false;
			}
			else {
				System.out.println("You WON!");
				return true;
			}
		}
		else if(value(yourhand) < 21 && value(dealerhand) > 21) {
			System.out.println("You WON!");
			return true;
		}
		else if(value(yourhand) > value(dealerhand) && value(yourhand) < 21) {
			System.out.println("You WON!");
			return true;
		}
		else if(value(yourhand) == value(dealerhand) && value(yourhand) < 21) {
			System.out.println("You TIED.");
			return false;
		}
		else {
			System.out.println("You LOST! Sorry.");
			return false;
		}
	}
	public void profit() {
		if(win() == true) {
			System.out.println("You made " + bet + " dollars.");
			System.out.println("You now have " + (balance+bet) + " dollars.");
			balance = balance + bet;
		}
		else if(value(yourhand) == value(dealerhand) && value(yourhand) <= 21) {
			System.out.println("You tied. You gained and lost nothing.");
		}
		else {
			System.out.println("You lost " + bet + " dollars.");
			System.out.println("You now have " + (balance-bet) + " dollars.");
			balance = balance - bet;
		}
	}
}
