package Klondike;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		ArrayList<Card> d = new ArrayList<Card>();
		int counter = 0;
		for(int i = 1; i <= 4; i++) {
			for(int j = 1; j < 14; j++) {
				d.add(new Card(j, new Suit(i), false));
				counter ++;
			}
		}
		System.out.println("What difficulty would you like to play at? (hard or easy)");
		Deck deck = new Deck(d);
		GameBoard solitaire = new GameBoard(deck);
		while(!solitaire.checkWin()) {
			//print the gameBoard
			System.out.println();
			System.out.print(solitaire.excessToString() + "         ");
			System.out.println(solitaire.reservedToString());
			System.out.println(solitaire.getPArea1());
			System.out.println(solitaire.getPArea2());
			System.out.println(solitaire.getPArea3());
			System.out.println(solitaire.getPArea4());
			System.out.println(solitaire.getPArea5());
			System.out.println(solitaire.getPArea6());
			System.out.println(solitaire.getPArea7());
			
			while(true) {
				System.out.print("Would you like to move a card in the Playing Area? ");
				String response = input.next();
				//if the card that is being moved is in the playing Area
				if(response.toLowerCase().contains("y")) {
					//ask for the Suit
					System.out.print("What suit is the card you want to move? ");
					response = input.next();
					Suit suit = new Suit();
					if(response.toLowerCase().contains("sp")) {
						suit.setSuit(1);
					}
					else if(response.toLowerCase().contains("cl")) {
						suit.setSuit(2);
					}
					else if(response.toLowerCase().contains("he")) {
						suit.setSuit(3);
					}
					else {
						suit.setSuit(4);
					}
					//ask for the value
					System.out.print("What is the value of the card you want to move? ");
					response = input.next();
					//make a new Card with the value that was given
					Card c = new Card();
					c.setSuit(suit);
					//setting the Suit value also works correctly
					//setting the int value works correctly
					for(int i = 1; i < 14; i++) {
						String val =  Integer.toString(i);
						if(response.equals(val)) {
							c.setValue(i);
							c.reveal();
						}
					}
					System.out.println(c);
					//check which pArea stack the card is from and check if the move is possible 
					if(solitaire.getPArea1().indexOf(c) >= 0) {
						//something is wrong with the makeMove method
						//its returning false when it should be returning true
						if(!solitaire.makeMove(solitaire.getPArea1(), c)) {
							System.out.println("Sorry this move cannot be made");
							continue;
						}
						else {
							solitaire.getPArea1().updateTopCard();
							solitaire.getPArea1().revealCard();
							break; //this move is done and will print out the next iteration of the game
						}
					}
					else if(solitaire.getPArea2().indexOf(c) >= 0) {
						if(!solitaire.makeMove(solitaire.getPArea2(), c)) {
							System.out.println("Sorry this move cannot be made");
							continue;
						}
						else {
							solitaire.getPArea2().updateTopCard();
							solitaire.getPArea2().revealCard();
							break;//this move is done and will print out the next iteration of the game
						}
					}
					else if(solitaire.getPArea3().indexOf(c) >= 0) {
						if(!solitaire.makeMove(solitaire.getPArea3(), c)) {
							System.out.println("Sorry this move cannot be made");
							continue;
						}
						else {
							solitaire.getPArea3().updateTopCard();
							solitaire.getPArea3().revealCard();
							break;//this move is done and will print out the next iteration of the game
						}
					}
					else if(solitaire.getPArea4().indexOf(c) >= 0) {
						if(!solitaire.makeMove(solitaire.getPArea4(), c)) {
							System.out.println("Sorry this move cannot be made");
							continue;
						}
						else {
							solitaire.getPArea4().updateTopCard();
							solitaire.getPArea4().revealCard();
							break;//this move is done and will print out the next iteration of the game
						}
					}
					else if(solitaire.getPArea5().indexOf(c) >= 0) {
						if(!solitaire.makeMove(solitaire.getPArea5(), c)) {
							System.out.println("Sorry this move cannot be made");
							continue;
						}
						else {
							solitaire.getPArea5().updateTopCard();
							solitaire.getPArea5().revealCard();
							break;//this move is done and will print out the next iteration of the game
						}
					}
					else if(solitaire.getPArea6().indexOf(c) >= 0) {
						if(!solitaire.makeMove(solitaire.getPArea6(), c)) {
							System.out.println("Sorry this move cannot be made");
							continue;
						}
						else {
							solitaire.getPArea6().updateTopCard();
							solitaire.getPArea6().revealCard();
							break;//this move is done and will print out the next iteration of the game
						}     
					}                                                   
					else if(solitaire.getPArea7().indexOf(c) >= 0) {
						if(!solitaire.makeMove(solitaire.getPArea7(), c)) {
							System.out.println("Sorry this move cannot be made");
							continue;
						}
						else {
							solitaire.getPArea7().updateTopCard();
							solitaire.getPArea7().revealCard();
							break;//this move is done and will print out the next iteration of the game
						}
					}
					//else { //AFTER COMMENTING THIS OUT AND TESTING I KNOW THAT THE MAKEMOVE METHOD WORKS BECAUSE OF LINE 152 BUT THE PREVIOUS IF STATEMENTS DONT WORK
						//System.out.println("Sorry this move cannot be made");
						//continue;
					//}
				}
				else {
					System.out.print("Would you like to move or reveal a card from the Excess Pile? ");
					response = input.next();
					if(response.toLowerCase().contains("y")) {
						System.out.print("Would you like to move the card from the Excess Pile to the Playing Area? ");	
						response = input.next();
						//if the player wants to move a card from the excess pile to the playing area, then make the move
						if(response.toLowerCase().contains("y")) {
							if(!solitaire.makeMove(solitaire.getExcessRevealed(), solitaire.getExcessRevealed().getTopCard())){ //THIS LINE WORKS
								System.out.println("Sorry this move cannot be made");
							}
							else {
								solitaire.getExcessRevealed().updateTopCard();
								break;//this move is done and will print out the next iteration of the game
							}
						}
						else {
							//ask if the player wants to reveal a card from the Excess Pile
							System.out.print("Would you like to reveal a card from the Excess Pile? ");
							response = input.next();
							if(response.toLowerCase().contains("y")) {
								solitaire.revealExcessCard();
								break;
							}
						}
					}
							//since no other moves want to be made, the Player must want to move a card from the reserved pile to the Playing Area
				else {
						System.out.print("What is the suit of the Reserved Stack that you want to move from? ");
						response = input.next();
						if(response.toLowerCase().contains("sp")) {
							if(!solitaire.makeMove(solitaire.getSpadesR(), solitaire.getSpadesR().getTopCard())) {
								System.out.println("Sorry this move cannot be made");
								continue;
							}
							else {
								solitaire.getSpadesR().updateTopCard();
								break; //this move is done and will print out the next iteration of the game
							}
						}
						else if(response.toLowerCase().contains("cl")) {
							if(!solitaire.makeMove(solitaire.getClubsR(), solitaire.getClubsR().getTopCard())) {
								System.out.println("Sorry this move cannot be made");
								continue;
							}
							else {
								solitaire.getClubsR().updateTopCard();
								break; //this move is done and will print out the next iteration of the game
							}
						}
						else if(response.toLowerCase().contains("he")) {
							if(!solitaire.makeMove(solitaire.getHeartsR(), solitaire.getHeartsR().getTopCard())) {
								System.out.println("Sorry this move cannot be made");
								continue;
							}
							else {
								solitaire.getHeartsR().updateTopCard();
								break; //this move is done and will print out the next iteration of the game
							}
						}
						else {
							if(!solitaire.makeMove(solitaire.getDiamondsR(), solitaire.getDiamondsR().getTopCard())) {
								System.out.println("Sorry this move cannot be made");
								continue;
							}
							else{
								solitaire.getDiamondsR().updateTopCard();
								break;//this move is done and will print out the next iteration of the game
							}
						}
					}
				}
			}
		}
	}
}