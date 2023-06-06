package Klondike;

import java.util.*;

public class GameBoard {
	//instance variables
	//p area are the 7 slots in the playing area
	//the R variables are for the stacks in the reserved area
	//excess is for the rest of the deck
	private Deck pArea1;
	private Deck pArea2;
	private Deck pArea3;
	private Deck pArea4;
	private Deck pArea5;
	private Deck pArea6;
	private Deck pArea7;
	private Deck spadesR;
	private Deck clubsR;
	private Deck heartsR;
	private Deck diamondsR;
	private Deck excessHidden;
	private Deck excessRevealed;
	private int count;
	
	public GameBoard(Deck original) {
		pArea1 = new Deck();
		pArea2 = new Deck();
		pArea3 = new Deck();
		pArea4 = new Deck();
		pArea5 = new Deck();
		pArea6 = new Deck();
		pArea7 = new Deck();
		spadesR = new Deck();
		clubsR = new Deck();
		heartsR = new Deck();
		diamondsR = new Deck();
		excessHidden = new Deck();
		excessRevealed = new Deck();
		original.shuffle();
		original = pArea1.addCards(original, original.getTopCard()); // adds only the top card of the original deck to p area 1
		pArea1.updateTopCard();
		original = pArea2.addCards(original, original.get(original.size()-2));//adds the card behind the top card and the top card to p area 2
		pArea2.updateTopCard();
		original = pArea3.addCards(original, original.get(original.size()-3)); //adds 3 cards to p Area 3
		pArea3.updateTopCard();
		original = pArea4.addCards(original, original.get(original.size()-4));//adds 4 cards to p Area 4
		pArea4.updateTopCard();
		original = pArea5.addCards(original, original.get(original.size()-5));
		pArea5.updateTopCard();
		original = pArea6.addCards(original, original.get(original.size()-6));
		pArea6.updateTopCard();
		original = pArea7.addCards(original, original.get(original.size()-7));
		pArea7.updateTopCard();
		original = excessHidden.addCards(original, original.get(0));
		pArea1.revealCard();
		pArea2.revealCard();
		pArea3.revealCard();
		pArea4.revealCard();
		pArea5.revealCard();
		pArea6.revealCard();
		pArea7.revealCard();
		
	}
	
	//getters for the different stacks
	public Deck getPArea1() {
		return pArea1;
	}
	public Deck getPArea2() {
		return pArea2;
	}
	public Deck getPArea3() {
		return pArea3;
	}
	public Deck getPArea4() {
		return pArea4;
	}
	public Deck getPArea5() {
		return pArea5;
	}
	public Deck getPArea6() {
		return pArea6;
	}
	public Deck getPArea7() {
		return pArea7;
	}
	public Deck getSpadesR() {
		return spadesR;
	}
	public Deck getClubsR() {
		return clubsR;
	}
	public Deck getHeartsR() {
		return heartsR;
	}
	public Deck getDiamondsR() {
		return diamondsR;
	}
	public Deck getExcessHidden() {
		return excessHidden;
	}
	public Deck getExcessRevealed() {
		return excessRevealed;
	}
	//excessCards methods
	public String excessToString() {
		if(excessHidden.size()> 0) {
			if(excessRevealed.size()>0) {
				return "[hidden, " + excessRevealed.getTopCard() + "]";
			}
			else {
				return "[hidden, empty]";
			}
		}
		else {
			if(excessRevealed.size()>0) {
				return "[empty, " + excessRevealed.getTopCard() + "]";
 			}
			else {
				return "[empty, empty]";
			}
		}
	}
	public boolean revealExcessCard() {
		if(excessHidden.size()>0) {
			for(int i = 0; i < count; i ++) {
				excessHidden = excessRevealed.addCards(excessHidden, excessHidden.getTopCard());
				excessRevealed.updateTopCard();
				excessHidden.updateTopCard();
			}
			excessHidden.updateTopCard();
			
			excessRevealed.revealCard();
			return true;
		}
		else {
			int times = excessRevealed.size();
			for(int i = 0; i < times; i++) {// adds cards back to hidden in reverse order
				excessRevealed = excessHidden.addCards(excessRevealed, excessRevealed.getTopCard());
				excessHidden.updateTopCard();
				excessRevealed.updateTopCard();
			}
			return true;
		}
	}
	//String method for the reserved stacks
	public String reservedToString() {
		String value = "";
		if(spadesR.size() > 0) {
			value += "[" + spadesR.getTopCard() + "] ";
		}
		else {
			value += "[empty] ";
		}
		if(clubsR.size() > 0) {
			value += "[" + clubsR.getTopCard() + "] ";
		}
		else {
			value += "[empty] ";
		}
		if(heartsR.size() > 0) {
			value += "[" + heartsR.getTopCard() + "] ";
		}
		else {
			value += "[empty] ";
		}
		if(diamondsR.size() > 0) {
			value += "[" + diamondsR.getTopCard() + "] ";
		}
		else {
			value += "[empty] ";
		}
		return value;
	}
	//moving cards methods
	public boolean moveToR(Deck pArea, Card c) {//return true if the top card of the deck can be moved the the Reserved Stacks
		//NOTE: AFTER THIS METHOD IS CALLED PARAMATER Deck pArea MUST CALL THE updateTopCard() METHOD TO KEEP THE DECK UP TO DATE
		if(!c.isRevealed()) {
			return false;
		}
		if(!c.equals(pArea.getTopCard())) {
			return false;
		}
		Suit suit = pArea.getTopCard().getSuit();
		Suit spades = new Suit(1);
		Suit clubs = new Suit(2);
		Suit hearts = new Suit(3);
		if(suit.equals(spades)) {
			if(spadesR.size() == 0) {//checks if the reserved stack is empty and adds topCard if its an ace
				if(pArea.getTopCard().getValue() == 1) {
					pArea = spadesR.addCards(pArea, pArea.getTopCard());
					spadesR.updateTopCard();
					
					return true;
				}
				else {
					return false;
				}
			}
			else {
				if(spadesR.getTopCard().getValue()+1 == pArea.getTopCard().getValue()){//checks if the topcard of the reserved stack has a value of one less than the topcard of 
					pArea = spadesR.addCards(pArea, pArea.getTopCard());
					spadesR.updateTopCard();
					return true;
				}
				else {
					return false;
				}
			}
		}
		else if(suit.equals(clubs)) {
			if(clubsR.size() == 0) {
				if(pArea.getTopCard().getValue() == 1) {
					pArea = clubsR.addCards(pArea, pArea.getTopCard());
					clubsR.updateTopCard();
					return true;
				}
				else {
					return false;
				}
			}
			else {
				if(clubsR.getTopCard().getValue()+1 == pArea.getTopCard().getValue()){
					pArea = clubsR.addCards(pArea, pArea.getTopCard());
					clubsR.updateTopCard();
					return true;
				}
				else {
					return false;
				}
			}
		}
		else if(suit.equals(hearts)) {
			if(heartsR.size() == 0) {
				if(pArea.getTopCard().getValue() == 1) {
					pArea = heartsR.addCards(pArea, pArea.getTopCard());
					heartsR.updateTopCard();
					return true;
				}
				else {
					return false;
				}
			}
			else {
				if(heartsR.getTopCard().getValue()+1 == pArea.getTopCard().getValue()){
					pArea = heartsR.addCards(pArea, pArea.getTopCard());
					heartsR.updateTopCard();
					return true;
				}
				else {
					return false;
				}
			}
		}
		else {
			if(diamondsR.size() == 0) {
				if(pArea.getTopCard().getValue() == 1) {
					pArea = diamondsR.addCards(pArea, pArea.getTopCard());
					return true;
				}
				else {
					return false;
				}
			}
			else {
				if(diamondsR.getTopCard().getValue()+1 == pArea.getTopCard().getValue()){
					pArea = diamondsR.addCards(pArea, pArea.getTopCard());
					return true;
				}
				else {
					return false;
				}
			}
		}
	}
	//something is wrong with this method
	//NOTE: AFTER THIS METHOD IS CALLED PARAMETER Deck pArea MUST CALL THE updateTopCard() METHOD TO KEEP THE DECK UP TO DATE
	//NOTE: AFTER THIS METHOD IS CALLED PARAMATER Deck pArea MUST CALL THE revealCard() METHOD TO KEEP THE DECK UP TO DATE
	public boolean makeMove(Deck pArea, Card c) {//will return true if a move has been made and false if a move cannot be made and will make the move
		if(!c.isRevealed()) {
			return false;
		}
		if(this.moveToR(pArea, c)) {
			return true;
		}
		if(!pArea.equals(pArea1)) {//if the pArea is not pArea1 then check if there is a possible move 
			if(pArea1.size() == 0 && c.getValue() == 13) { //if pArea1 is empty, c has to be a king
				pArea1.addCards(pArea, c);
				pArea1.updateTopCard();
				return true; //if c is not a king, there could still be an available move
			}
			else if((pArea1.getTopCard().getValue()-1 == c.getValue()) && (!c.getSuit().equalsColor(pArea1.getTopCard().getSuit()))){//check if the value of c is less than the value of pArea1.getTopCard by 1 and if the color of the suits are different
				pArea = pArea1.addCards(pArea, c);
				pArea1.updateTopCard();
				return true;
			}
			//if this statement is false check the other decks for a possible move
		}
		if(!pArea.equals(pArea2)) {
			if(pArea2.size() == 0 && c.getValue() == 13) {
				pArea2.addCards(pArea, c);
				pArea2.updateTopCard();
				return true;
			}
			else if((pArea2.getTopCard().getValue()-1 == c.getValue()) && (!c.getSuit().equalsColor(pArea2.getTopCard().getSuit()))){
				pArea = pArea2.addCards(pArea, c);
				pArea2.updateTopCard();
				return true;
			}
		}
		if(!pArea.equals(pArea3)) {
			if(pArea3.size() == 0 && c.getValue() == 13) {
				pArea3.addCards(pArea, c);
				pArea3.updateTopCard();
				return true;
			}
			else if((pArea3.getTopCard().getValue()-1 == c.getValue()) && (!c.getSuit().equalsColor(pArea3.getTopCard().getSuit()))){
				pArea = pArea3.addCards(pArea, c);
				pArea3.updateTopCard();
				return true;
			}
		}
		if(!pArea.equals(pArea4)) {
			if(pArea4.size() == 0 && c.getValue() == 13) {
				pArea4.addCards(pArea, c);
				pArea4.updateTopCard();
				return true;
			}
			else if((pArea4.getTopCard().getValue()-1 == c.getValue()) && (!c.getSuit().equalsColor(pArea4.getTopCard().getSuit()))){
				pArea = pArea4.addCards(pArea, c);
				pArea4.updateTopCard();
				return true;
			}
		}
		if(!pArea.equals(pArea5)) {
			if(pArea5.size() == 0 && c.getValue() == 13) {
				pArea5.addCards(pArea, c);
				pArea5.updateTopCard();
				return true;
			}
			else if((pArea5.getTopCard().getValue()-1 == c.getValue()) && (!c.getSuit().equalsColor(pArea5.getTopCard().getSuit()))){
				pArea = pArea5.addCards(pArea, c);
				pArea5.updateTopCard();
				return true;
			}
		}
		if(!pArea.equals(pArea6)) {
			if(pArea6.size() == 0 && c.getValue() == 13) {
				pArea6.addCards(pArea, c);
				pArea6.updateTopCard();
				return true;
			}
			else if((pArea6.getTopCard().getValue()-1 == c.getValue()) && (!c.getSuit().equalsColor(pArea6.getTopCard().getSuit()))){
				pArea = pArea6.addCards(pArea, c);
				pArea6.updateTopCard();
				return true;
			}
		}
		if(!pArea.equals(pArea7)) {
			if(pArea7.size() == 0 && c.getValue() == 13) {
				pArea7.addCards(pArea, c);
				pArea7.updateTopCard();
				return true;
			}
			else if((pArea7.getTopCard().getValue()-1 == c.getValue()) && (!c.getSuit().equalsColor(pArea7.getTopCard().getSuit()))){
				pArea = pArea7.addCards(pArea, c);
				pArea7.updateTopCard();
				return true;
			}
		}
		return false;
	}
	public boolean checkWin() {
		if(spadesR.size() == 13 && clubsR.size() == 13 && heartsR.size() == 13 && diamondsR.size() == 13) {
			return true;
		}
		return false;
	}
}