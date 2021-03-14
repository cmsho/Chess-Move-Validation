//**************************************************************************************
// Piece.java	
//
// Author: Colton Shoenberger
// UserID: cs3585
//
// Defines the rules and methods of each piece
//**************************************************************************************

package edu.drexel.cs3585;

public abstract class Piece {

	// True if piece is white, false is piece is black
	boolean white;

	// Method to determine if a move is valid
	abstract boolean legalMove(int xFrom, int yFrom, int xTo, int yTo, boolean capture);
}

class King extends Piece {

	King(boolean white) {
		this.white = white;
	}

	@Override
	boolean legalMove(int xFrom, int yFrom, int xTo, int yTo, boolean capture) {
		
		// Kings can move exactly one space in any direction
		return (Math.abs(xFrom - xTo) <= 1 && Math.abs(yFrom - yTo) <= 1);
	}
}

class Queen extends Piece {

	Queen(boolean white) {
		this.white = white;
	}

	@Override
	boolean legalMove(int xFrom, int yFrom, int xTo, int yTo, boolean capture) {
		
		// Queens can move in a straight line along a row or column 
		// (dx = 0 or dy = 0)  
		return (((xFrom == xTo && yFrom != yTo) || (xFrom != xTo && yFrom == yTo)) ||
				// Queens can also move diagonally (dx = dy)
				(Math.abs(xFrom - xTo) == Math.abs(yFrom - yTo)));
	}
}

class Rook extends Piece {

	Rook(boolean white) {
		this.white = white;
	}

	@Override
	boolean legalMove(int xFrom, int yFrom, int xTo, int yTo, boolean capture) {
		
		// Rooks can move in a straight line along a row or column (either dx = 0 or dy = 0) 
		return ((xFrom == xTo && yFrom != yTo) || (xFrom != xTo && yFrom == yTo));
	}
}

class Bishop extends Piece {

	Bishop(boolean white) {
		this.white = white;
	}

	@Override
	boolean legalMove(int xFrom, int yFrom, int xTo, int yTo, boolean capture) {
		
		// Bishops can only move diagonally (dx = dy)
		return (Math.abs(xFrom - xTo) == Math.abs(yFrom - yTo));
	}
}

class Knight extends Piece {

	Knight(boolean white) {
		this.white = white;
	}

	@Override
	boolean legalMove(int xFrom, int yFrom, int xTo, int yTo, boolean capture) {
		
		// Knights move in an L shape.  Two spaces on one axis, one space on the other.
		return ((Math.abs(xFrom - xTo) == 2 && Math.abs(yFrom - yTo) == 1) ||
				(Math.abs(xFrom - xTo) == 1 && Math.abs(yFrom - yTo) == 2));
	}
}

class Pawn extends Piece {

	Pawn(boolean white) {
		this.white = white;
	}

	@Override
	boolean legalMove(int xFrom, int yFrom, int xTo, int yTo, boolean capture) {

		// Pawns can only move forward
		return (this.forward(yFrom, yTo) &&	
				// If it is a capture...
				((capture && 
						// ...and the move is forward and diagonal by one, it is legal.  OR
						Math.abs(xFrom - xTo) == 1 && Math.abs(yFrom - yTo) == 1) ||
				// If it is not left, right, or diagonal...
				((xFrom == xTo) &&
						// ...and it is forward one space, it is legal.  OR
						(Math.abs(yFrom - yTo) == 1 ||
						// If it is the pawn's first move...
						(this.firstMove(yFrom) &&
							// and it is forward two spaces, it is legal.
							Math.abs(yTo - yFrom) == 2)))));
	}

	// Pawns can only move forward, which is relative to the pawn's color
	// Returns true if the pawn is moving forward
	private boolean forward(int yFrom, int yTo) {
		if (this.white) {
			return (yFrom <= yTo);
		}
		else {
			return (yTo <= yFrom);
		}
	}

	// If it is the pawn's first move, it can move one or two spaces
	// White pawns start at y = 1, black pawns start at y = 6
	private boolean firstMove(int yFrom) {
		if (this.white) {
			return (yFrom == 1);
		}
		else {
			return (yFrom == 6);
		}
	}
}