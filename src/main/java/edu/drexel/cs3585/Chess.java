//**************************************************************************************
// Chess.java	
//
// Author: Colton Shoenberger
// UserID: cs3585
//
// Provides move validation for the game of chess
//**************************************************************************************

package edu.drexel.cs3585;

import java.util.Scanner;

public class Chess {
	
	public static void main (String[] args) {
		
		// Create a chessboard, represented as a 2D array
		Piece[][] board = new Piece[8][8];

		// Greet the user
		System.out.println("This program provides move validation for the game of chess. "
				+ "Consult readMe.txt for formatting rules regarding user input.");
		
		// Have the user input the pieces to be placed on the board
		String pieceToMove = userInput(board);
		
		// Print legal moves
		System.out.println("LEGAL MOVES FOR " + pieceToMove + ": " + printLegalMoves(pieceToMove, board));

	}

	// The userInput method collects input from the user and calls supporting methods to validate the input.
	// As input is validated, the pieces are placed on the board
	// The method returns a string for the final piece to move
	static String userInput(Piece[][] board) {
		// Have the user enter the white pieces to be placed on the board
		Scanner scan = new Scanner(System.in, "UTF-8");
		System.out.print("WHITE: ");
		String whitePieces = scan.nextLine();

		// Check the input to confirm it is valid.  If it is invalid, have the user re-enter the input
		while (!validInput(whitePieces) || !noOverlap(whitePieces)) {
			System.out.println("Please try again.");
			System.out.print("WHITE: ");
			whitePieces = scan.nextLine();
		}
		
		// Place white pieces on the board
		makePieces(whitePieces, board, true);
		
		// Have the user enter the black pieces to be placed on the board
		System.out.print("BLACK: ");
		String blackPieces = scan.nextLine();
		String allPieces = whitePieces + " " + blackPieces;

		// Check the input to confirm it is valid.  If it is invalid, have the user re-enter the input
		while (!validInput(blackPieces) || !noOverlap(allPieces)) {
			System.out.println("Please try again.");
			System.out.print("BLACK: ");
			blackPieces = scan.nextLine();
			allPieces = whitePieces + " " + blackPieces;
		}
		
		// Place black pieces on the board
		makePieces(blackPieces, board, false);

		// Have the user enter the piece to move
		System.out.print("PIECE TO MOVE: ");
		String pieceToMove = scan.nextLine();

		// Confirm input is valid and that the piece exists on the board.  If not, have user re-enter input
		while (!validFormat(pieceToMove) || !pieceExists(pieceToMove, allPieces)) {
			System.out.println("Please try again.");
			System.out.print("PIECE TO MOVE: ");
			pieceToMove = scan.nextLine();
		}
		
		scan.close();
		return pieceToMove;
	}
	
	static boolean validInput(String str) {
		return (validFormat(str) && oneKing(str) && noOverflow(str));
	}
	
	
	// Method for checking the input to confirm the formatting is valid.
	static boolean validFormat(String str) {
		String nextPiece;
		char type = 0;
		char column = 0;
		char row = 0;
		Scanner scan = new Scanner(str);

		// Check the input to confirm it is valid
		while (scan.hasNext()) {

			// Assign each word to nextPiece
			nextPiece = scan.next();		
			
			// If nextPiece is of valid length, assign the first three characters to type, column, and row
			if (validLength(nextPiece)) {
				type = nextPiece.charAt(0);
				column = nextPiece.charAt(1);
				row = nextPiece.charAt(2);
			}

			// If nextPiece is not of valid length, or one of the characters is not proper, return false
			if (!validLength(nextPiece) || !validType(type) || !validColumn(column) || !validRow(row)) {
				scan.close();
				System.out.println("Input invalid.  Consult readMe.txt for formatting rules.");
				return false;
			}
		}
		// If it passes all the above tests, return true
		scan.close();
		return true;
	}
	
	// Method to validate each word in the string is the proper length to specify a piece
	static boolean validLength(String str) {
		return (str.length() == 3 || (str.length() == 4 && str.charAt(3) == ','));
	}
	
	// Method to validate the first character properly specifies the piece's type
	static boolean validType(char type) {
		return (type == 'K' || type == 'Q' || type == 'R' || type == 'B' || type == 'N' || type == 'P');
	}
	
	// Method to validate the second character properly specifies the piece's column
	static boolean validColumn(char column) {
		return (column == 'a' || column == 'b' || column == 'c' || column == 'd' || 
				column == 'e' || column == 'f' || column == 'g' || column == 'h');
	}
	
	// Method to validate the third character properly specifies the piece's row
	static boolean validRow(char row) {
		return (row == '1' || row == '2' || row == '3' || row == '4' ||
				row == '5' || row == '6' || row == '7' || row == '8');
	}
	
	// Method to validate that each team has exactly one King piece
	static boolean oneKing(String str) {

		String input = str;
		String nextPiece;
		Scanner count = new Scanner(input);

		// Create an integer variable to keep track of the number of kings
		int kings = 0;

		// Scan each piece being added to the board
		while(count.hasNext()) {
			nextPiece = count.next();
			// If the piece is a king, increment the count of kings
			if (nextPiece.charAt(0) == 'K'){
				kings++;
			}	
		}

		// There should be exactly one king
		if (kings != 1) {
			count.close();
			System.out.println("There should be exactly one King piece placed on the board.");
			return false;
		}
		else {
			count.close();
			return true;
		}
	}

	
	// Method to confirm a team does not have more than 16 pieces
	static boolean noOverflow(String str) {

		String input = str;
		Scanner count = new Scanner(input);

		// Integer variable to keep track of the number of pieces
		int pieces = 0;

		// Count the number of piecces being added to the board
		while(count.hasNext()) {
			count.next();
			pieces++;
		}

		// There should not be more than sixteen pieces per team
		if (pieces > 16) {
			count.close();
			System.out.println("Each team cannot have more than sixteen pieces on the board.");
			return false;
		}
		else {
			count.close();
			return true;
		}
	}
	
	
	// Method for confirming no two white pieces are assigned to the same board location
	static boolean noOverlap(String str) {
		
		String input = str;
		String nextPiece, location;
		Scanner count = new Scanner(input);

		// Create an integer vairable to represent the number of pieces in the input line
		int totalPieces = 0;

		// Count the number of pieces in the line, store the value in totalPieces
		while (count.hasNext()) {
			totalPieces++;
			count.next();
		}
		count.close();
		
		// Create an array to store the piece locations
		String[] occupiedSpaces = new String[totalPieces];

		Scanner scan = new Scanner(input);

		// For each piece in the string...
		for(int i = 0; i < totalPieces; i++) {
			nextPiece = scan.next();

			// If input is less than three characters, return false
			if (!validLength(nextPiece)) {
				scan.close();
				return false;
			}

			// Store the piece's location in the occupiedSpaces array
			location = nextPiece.substring(1, 3);
			occupiedSpaces[i] = location;

			// Compare each new location to the existing locations
			for(int j = 0; j < i; j++) {
				// If an overlap is found, the noOverlap method will return false
				if (occupiedSpaces[j].equals(location)) {
					System.out.println("Two pieces cannot be assigned to the same location.");
					scan.close();
					return false;
				}
			}
		} 
		// If the end of the loop was reached without finding any overlap, return true
		scan.close();
		return true;
	}

	
	// Method to place pieces on the board
	static Piece[][] makePieces(String pieces, Piece[][] board, boolean white) {
		// Assign the current board to newBoard, which will later replace the current board
		Piece[][] newBoard = board;

		Scanner scan = new Scanner(pieces);
		String newPiece;
		char type, row, column;
		int xVal, yVal;

		while (scan.hasNext()) {

			// Scan each word in the string and assign it to newPiece
			newPiece = scan.next();

			// Evaluate the first three characters of newPiece to determine its type and location
			type = newPiece.charAt(0);
			column = newPiece.charAt(1);
			row = newPiece.charAt(2);

			// Convert the row and column char to proper (x,y) int values
			xVal = xIndex(column);
			yVal = yIndex(row);

			// Create a new piece and place it at the proper location in the array
			if (white) {
				newBoard[yVal][xVal] = makeWhitePiece(type);
			}
			else {
				newBoard[yVal][xVal] = makeBlackPiece(type);
			}
		}
		scan.close();
		return newBoard;
	}

	
	// Method for converting the column value from input to an integer for assigning its location in the board array
	static int xIndex(char x) {
			 if (x == 'a') {return 0;}
		else if (x == 'b') {return 1;}
		else if (x == 'c') {return 2;}
		else if (x == 'd') {return 3;}
		else if (x == 'e') {return 4;}
		else if (x == 'f') {return 5;}
		else if (x == 'g') {return 6;}
		else return 7;
	}


	// Method for converting the row value from input to an integer for assigning its location in the board array
	static int yIndex(char y) {
			 if (y == '1') {return 0;}
		else if (y == '2') {return 1;}
		else if (y == '3') {return 2;}
		else if (y == '4') {return 3;}
		else if (y == '5') {return 4;}
		else if (y == '6') {return 5;}
		else if (y == '7') {return 6;}
		else return 7;
	}

	
	// Method for creating new white pieces
	static Piece makeWhitePiece(char type) {
			 if (type == 'K') {return new King(true);}
		else if (type == 'Q') {return new Queen(true);}
		else if (type == 'R') {return new Rook(true);}
		else if (type == 'B') {return new Bishop(true);}
		else if (type == 'N') {return new Knight(true);}
		else return new Pawn(true);
	}


	// Method for creating new black pieces
	static Piece makeBlackPiece(char type) {
			 if (type == 'K') {return new King(false);}
		else if (type == 'Q') {return new Queen(false);}
		else if (type == 'R') {return new Rook(false);}
		else if (type == 'B') {return new Bishop(false);}
		else if (type == 'N') {return new Knight(false);}
		else return new Pawn(false);
	}


	// Method that checks to confirm the PieceToMove exists
	static boolean pieceExists(String pieceToMove, String allPieces) {

		String comparePiece;
		Scanner scan = new Scanner(allPieces);

		// Search for the piece
		while (scan.hasNext()) {

			comparePiece = scan.next();

			// If we've confirmed the piece exists, return true
			if (pieceToMove.equals(comparePiece.substring(0,3))) {
				scan.close();
				return true;
			}
		}
		// If we've reached the end of the loop, the piece does not exist on the board.
		scan.close();
		System.out.println("This piece does not exist on the board.");
		return false;
	}	
	
	
	// Method to print the legal moves
	static String printLegalMoves(String pieceToMove, Piece[][] board) {
		
		String legalMoves = "";
		String prefix = "";
		StringBuilder build = new StringBuilder();
		
		// Determine the location of pieceToMove
		char column = pieceToMove.charAt(1);
		char row = pieceToMove.charAt(2);
		int xVal = xIndex(column);
		int yVal = yIndex(row);
		Piece piece = board[yVal][xVal];
		// Count the total number of legal moves
		int numMoves = 0;

		// Determine legal moves and print them
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {

				// If the space we're trying to move to is occupied, evaluate a potential capture move
				Piece to = board[y][x];
				boolean capture = (to != null);

				// If the move is potentially legal
				if (piece.legalMove(xVal, yVal, x, y, capture) &&
					// and it is not occupied by a piece of the same color
					(!capture || piece.white != to.white) &&
					// and there are no pieces blocking the movement path
					noBlocks(xVal, yVal, x, y, board) &&
					// and the king is not exposed to check
					safeKing(xVal, yVal, x, y, board, piece)) {

					// Print the legal move
					build.append(prefix);
					prefix = ", ";
					build.append(toColumn(x) + "" + toRow(y));
					numMoves++;
				}
			}
			legalMoves = build.toString();
		}
		// Inform the user if there are no legal moves
		if (numMoves == 0) {
			legalMoves = "No legal moves.";
		}
		
		return legalMoves;
	}
	
	
	// Method for checking if a piece is blocked.  Returns true if no pieces are blocking the potential movement
	static boolean noBlocks(int xFrom, int yFrom, int xTo, int yTo, Piece[][] board) {
		// Destination
		Piece dest = board[yTo][xTo];
		
		// Determine the distance (number of spaces) the piece will be moving
		int dist = Math.max(Math.abs(xFrom - xTo), Math.abs(yFrom - yTo));

		// Determine the direction(s) of movement along the x and y axes
		int dx, dy;

			 if (xFrom < xTo) 	{dx = 1;}  // Right
		else if (xFrom == xTo) 	{dx = 0;}
		else 					{dx = -1;} // Left
	
			 if (yFrom < yTo) 	{dy = 1;}  // Up
		else if (yFrom == yTo)	{dy = 0;}
		else 					{dy = -1;} // Down
		
		// The knight piece does not have to worry about being blocked.
		// All pieces besides the knight move in a straight line. 
		// Thus, the following if statement properly exempts the knight.
		if (xFrom == xTo || yFrom == yTo || Math.abs(xFrom - xTo) == Math.abs(yFrom - yTo)) {
			
			// Check each space along the movement path for blocks
			for (int i = 1; i < dist; i++) {
				int x = xFrom + (i * dx);
				int y = yFrom + (i * dy);

				// If a space along the movement path is occupied, and we are not at the destination, return false
				if (board[y][x] != null && board[y][x] != dest) {
					return false;
				}
			}
		}
		// If not blocked, return true
		return true;
	}

	
	// Method to determine if a potential move would leave the king exposed to check.
	static boolean safeKing(int xFrom, int yFrom, int xTo, int yTo, Piece[][] board, Piece piece) {

		// Boolean to specify whether the king is safe; switches to false if exposed to check
		boolean safe = true;

		// Store the location of the king for the player whose turn it is to move
		int[] king = findKing(board, piece.white);
		
		// Remember the original state of the "to" location (in order to reset the board at the end of this method)
		Piece before = board[yTo][xTo];
		// Place the piece at the location we're considering moving it to
		board[yTo][xTo] = piece;
		// Remove it from its original location (reset at end of method)
		board[yFrom][xFrom] = null;
		
		// If the piece that we moved was the King, store its new location
		if (piece instanceof King) {
			king[0] = xTo;
			king[1] = yTo;
		}
		
		// Examine the entire board
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				Piece enemy = board[y][x];
				
				// If we find a piece
				if (enemy != null &&
					// and it belongs to the opponent's team
					enemy.white != piece.white &&
					// and it has a path to the King's location
					enemy.legalMove(x, y, king[0], king[1], true) &&
					// and its path to the King is not blocked
					noBlocks(x, y, king[0], king[1], board)) {
					// then the King is in check
					safe = false;
				}
			}
		}

		// Return the board to its original state
		board[yFrom][xFrom] = piece;
		board[yTo][xTo] = before;
		
		return safe;
	}


	// Returns the location of the King of the given color
	static int[] findKing(Piece[][] board, boolean white) {
		// Array to store the king's location
		int[] location = new int[2];
		
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				Piece piece = board[y][x];
				
				// True if we found the king of the correct color
				if (piece instanceof King && piece.white == white) {
					// Store its location in the array
					location[0] = x;
					location[1] = y;
				}
			}
		}
		return location;
	}
	
	// Method for converting the xIndex of a piece on the board to the char value corresponding to its column
	static char toColumn(int x) {
			 if (x == 0) {return 'a';}
		else if (x == 1) {return 'b';}
		else if (x == 2) {return 'c';}
		else if (x == 3) {return 'd';}
		else if (x == 4) {return 'e';}
		else if (x == 5) {return 'f';}
		else if (x == 6) {return 'g';}
		else return 'h';
	}


	// Method for converting the yIndex of a piece on the board to the int value corresponding to its row
	static int toRow(int y) {
			 if (y == 0) {return 1;}
		else if (y == 1) {return 2;}
		else if (y == 2) {return 3;}
		else if (y == 3) {return 4;}
		else if (y == 4) {return 5;}
		else if (y == 5) {return 6;}
		else if (y == 6) {return 7;}
		else return 8;
	}
	
}