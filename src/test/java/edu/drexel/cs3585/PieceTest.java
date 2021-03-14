//**************************************************************************************
// PieceTest.java	
//
// Author: Colton Shoenberger
// UserID: cs3585
//
// JUnit tests for the Piece class
//**************************************************************************************

package edu.drexel.cs3585;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PieceTest {

	//------------------------------------------------------------------------------
	// Begin with Piece Tests
	//------------------------------------------------------------------------------
		
		// Note: Parameters for legalMove are (xFrom, yFrom, xTo, yTo, capture)
		
		// Initialize new pieces to test
		Piece wKing = new King(true);
		Piece bQueen = new Queen(false);
		Piece wRook = new Rook(true);
		Piece bBishop = new Bishop(false);
		Piece wKnight = new Knight(true);
		Piece wPawn = new Pawn(true);
		Piece bPawn = new Pawn(false);
		
		@Test
		void testKing() {
			// Legal Moves
			// One space in any direction
			assertEquals(wKing.legalMove(1, 1, 0, 0, true), true);
			assertEquals(wKing.legalMove(1, 1, 0, 1, false), true);
			assertEquals(wKing.legalMove(1, 1, 0, 2, true), true);
			assertEquals(wKing.legalMove(1, 1, 1, 0, false), true);
			assertEquals(wKing.legalMove(1, 1, 1, 2, true), true);
			assertEquals(wKing.legalMove(1, 1, 2, 0, false), true);
			assertEquals(wKing.legalMove(1, 1, 2, 1, true), true);
			assertEquals(wKing.legalMove(1, 1, 2, 2, false), true);
			
			// Illegal Moves
			// More than one space away
			assertEquals(wKing.legalMove(1, 1, 0, 3, true), false);
			assertEquals(wKing.legalMove(1, 1, 1, 3, false), false);
			assertEquals(wKing.legalMove(1, 1, 2, 3, true), false);
			assertEquals(wKing.legalMove(1, 1, 3, 3, false), false);
			assertEquals(wKing.legalMove(1, 1, 3, 2, true), false);
			assertEquals(wKing.legalMove(1, 1, 3, 1, false), false);
			assertEquals(wKing.legalMove(1, 1, 3, 0, true), false);
			assertEquals(wKing.legalMove(1, 1, 5, 6, false), false);
			assertEquals(wKing.legalMove(1, 1, 7, 7, true), false);	
		}
		
		@Test
		void testQueen() {
			// Legal Moves
			// Vertical
			assertEquals(bQueen.legalMove(2, 2, 0, 2, true), true);
			assertEquals(bQueen.legalMove(2, 2, 1, 2, true), true);
			assertEquals(bQueen.legalMove(2, 2, 3, 2, true), true);
			assertEquals(bQueen.legalMove(2, 2, 4, 2, true), true);
			assertEquals(bQueen.legalMove(2, 2, 5, 2, true), true);
			assertEquals(bQueen.legalMove(2, 2, 6, 2, true), true);
			assertEquals(bQueen.legalMove(2, 2, 7, 2, true), true);
			// Horizontal
			assertEquals(bQueen.legalMove(2, 2, 2, 0, true), true);
			assertEquals(bQueen.legalMove(2, 2, 2, 1, true), true);
			assertEquals(bQueen.legalMove(2, 2, 2, 3, true), true);
			assertEquals(bQueen.legalMove(2, 2, 2, 4, true), true);
			assertEquals(bQueen.legalMove(2, 2, 2, 5, true), true);
			assertEquals(bQueen.legalMove(2, 2, 2, 6, true), true);
			assertEquals(bQueen.legalMove(2, 2, 2, 7, true), true);
			// Diagonal
			assertEquals(bQueen.legalMove(2, 2, 0, 0, true), true);
			assertEquals(bQueen.legalMove(2, 2, 1, 1, true), true);
			assertEquals(bQueen.legalMove(2, 2, 3, 3, true), true);
			assertEquals(bQueen.legalMove(2, 2, 4, 4, true), true);
			assertEquals(bQueen.legalMove(2, 2, 5, 5, true), true);
			assertEquals(bQueen.legalMove(2, 2, 6, 6, true), true);
			assertEquals(bQueen.legalMove(2, 2, 7, 7, true), true);
			assertEquals(bQueen.legalMove(2, 2, 0, 4, true), true);
			assertEquals(bQueen.legalMove(2, 2, 1, 3, true), true);
			assertEquals(bQueen.legalMove(2, 2, 3, 1, true), true);
			assertEquals(bQueen.legalMove(2, 2, 4, 0, true), true);
			
			// Illegal Moves
			// L-shape
			assertEquals(bQueen.legalMove(2, 2, 0, 1, true), false);
			assertEquals(bQueen.legalMove(2, 2, 1, 0, true), false);
			assertEquals(bQueen.legalMove(2, 2, 0, 3, true), false);
			assertEquals(bQueen.legalMove(2, 2, 3, 0, true), false);
			assertEquals(bQueen.legalMove(2, 2, 1, 4, true), false);
			assertEquals(bQueen.legalMove(2, 2, 4, 1, true), false);
			assertEquals(bQueen.legalMove(2, 2, 3, 4, true), false);
			assertEquals(bQueen.legalMove(2, 2, 4, 3, true), false);
		}
		
		@Test
		void testRook() {
			// Legal Moves
			// Vertical
			assertEquals(wRook.legalMove(5, 6, 0, 6, false), true);
			assertEquals(wRook.legalMove(5, 6, 1, 6, false), true);
			assertEquals(wRook.legalMove(5, 6, 2, 6, false), true);
			assertEquals(wRook.legalMove(5, 6, 3, 6, false), true);
			assertEquals(wRook.legalMove(5, 6, 4, 6, false), true);
			assertEquals(wRook.legalMove(5, 6, 6, 6, false), true);
			assertEquals(wRook.legalMove(5, 6, 7, 6, false), true);
			// Horizontal
			assertEquals(wRook.legalMove(5, 6, 5, 0, false), true);
			assertEquals(wRook.legalMove(5, 6, 5, 1, false), true);
			assertEquals(wRook.legalMove(5, 6, 5, 2, false), true);
			assertEquals(wRook.legalMove(5, 6, 5, 3, false), true);
			assertEquals(wRook.legalMove(5, 6, 5, 4, false), true);
			assertEquals(wRook.legalMove(5, 6, 5, 5, false), true);
			assertEquals(wRook.legalMove(5, 6, 5, 7, false), true);
			
			// Illegal Moves
			// Diagonal
			assertEquals(wRook.legalMove(5, 6, 4, 5, false), false);
			assertEquals(wRook.legalMove(5, 6, 4, 7, false), false);
			assertEquals(wRook.legalMove(5, 6, 6, 4, false), false);
			assertEquals(wRook.legalMove(5, 6, 6, 7, false), false);
			// L-shape
			assertEquals(wRook.legalMove(5, 6, 3, 5, false), false);
			assertEquals(wRook.legalMove(5, 6, 4, 4, false), false);
			assertEquals(wRook.legalMove(5, 6, 3, 7, false), false);
			assertEquals(wRook.legalMove(5, 6, 6, 4, false), false);	
		}
		
		@Test
		void testBishop() {
			// Legal Moves
			// Diagonal
			assertEquals(bBishop.legalMove(3, 4, 0, 1, true), true);
			assertEquals(bBishop.legalMove(3, 4, 1, 2, true), true);
			assertEquals(bBishop.legalMove(3, 4, 2, 3, true), true);
			assertEquals(bBishop.legalMove(3, 4, 4, 5, true), true);
			assertEquals(bBishop.legalMove(3, 4, 5, 6, true), true);
			assertEquals(bBishop.legalMove(3, 4, 6, 7, true), true);
			assertEquals(bBishop.legalMove(3, 4, 7, 0, true), true);
			assertEquals(bBishop.legalMove(3, 4, 6, 1, true), true);
			assertEquals(bBishop.legalMove(3, 4, 5, 2, true), true);
			assertEquals(bBishop.legalMove(3, 4, 4, 3, true), true);
			assertEquals(bBishop.legalMove(3, 4, 2, 5, true), true);
			assertEquals(bBishop.legalMove(3, 4, 1, 6, true), true);
			assertEquals(bBishop.legalMove(3, 4, 0, 7, true), true);
			
			// Illegal Moves
			// Vertical
			assertEquals(bBishop.legalMove(3, 4, 1, 4, true), false);
			assertEquals(bBishop.legalMove(3, 4, 2, 4, true), false);
			assertEquals(bBishop.legalMove(3, 4, 5, 4, true), false);
			assertEquals(bBishop.legalMove(3, 4, 6, 4, true), false);
			// Horizontal
			assertEquals(bBishop.legalMove(3, 4, 3, 2, true), false);
			assertEquals(bBishop.legalMove(3, 4, 3, 3, true), false);
			assertEquals(bBishop.legalMove(3, 4, 3, 5, true), false);
			assertEquals(bBishop.legalMove(3, 4, 3, 6, true), false);
			// L-shape
			assertEquals(bBishop.legalMove(3, 4, 1, 5, true), false);
			assertEquals(bBishop.legalMove(3, 4, 2, 2, true), false);
			assertEquals(bBishop.legalMove(3, 4, 4, 6, true), false);
			assertEquals(bBishop.legalMove(3, 4, 5, 3, true), false);
		}
		
		@Test
		void testKnight() {
			// Legal Moves
			// L-shape
			assertEquals(wKnight.legalMove(2, 5, 0, 4, false), true);
			assertEquals(wKnight.legalMove(2, 5, 0, 6, false), true);
			assertEquals(wKnight.legalMove(2, 5, 1, 3, false), true);
			assertEquals(wKnight.legalMove(2, 5, 1, 7, false), true);
			assertEquals(wKnight.legalMove(2, 5, 3, 3, false), true);
			assertEquals(wKnight.legalMove(2, 5, 3, 7, false), true);
			assertEquals(wKnight.legalMove(2, 5, 4, 4, false), true);
			assertEquals(wKnight.legalMove(2, 5, 4, 6, false), true);
			
			// Illegal Moves
			// Vertical
			assertEquals(wKnight.legalMove(2, 5, 2, 1, false), false);
			assertEquals(wKnight.legalMove(2, 5, 2, 2, false), false);
			assertEquals(wKnight.legalMove(2, 5, 2, 3, false), false);
			assertEquals(wKnight.legalMove(2, 5, 2, 4, false), false);
			assertEquals(wKnight.legalMove(2, 5, 2, 6, false), false);
			assertEquals(wKnight.legalMove(2, 5, 2, 7, false), false);
			// Horizontal
			assertEquals(wKnight.legalMove(2, 5, 0, 5, false), false);
			assertEquals(wKnight.legalMove(2, 5, 1, 5, false), false);
			assertEquals(wKnight.legalMove(2, 5, 3, 5, false), false);
			assertEquals(wKnight.legalMove(2, 5, 4, 5, false), false);
			assertEquals(wKnight.legalMove(2, 5, 5, 5, false), false);
			assertEquals(wKnight.legalMove(2, 5, 6, 5, false), false);
			// Diagonal
			assertEquals(wKnight.legalMove(2, 5, 0, 7, false), false);
			assertEquals(wKnight.legalMove(2, 5, 1, 6, false), false);
			assertEquals(wKnight.legalMove(2, 5, 3, 4, false), false);
			assertEquals(wKnight.legalMove(2, 5, 4, 3, false), false);
			assertEquals(wKnight.legalMove(2, 5, 5, 2, false), false);
			assertEquals(wKnight.legalMove(2, 5, 6, 1, false), false);
			assertEquals(wKnight.legalMove(2, 5, 7, 0, false), false);
		}
		
		@Test
		void testWhitePawn() {
			
			// Legal Moves
			// Test that pawns can move one or two spaces forward from starting location 
			// Note: White pawns start on row 1
			assertEquals(wPawn.legalMove(1, 1, 1, 2, false), true);
			assertEquals(wPawn.legalMove(1, 1, 1, 3, false), true);
			assertEquals(wPawn.legalMove(6, 1, 6, 2, false), true);
			assertEquals(wPawn.legalMove(6, 1, 6, 3, false), true);
			// Test that pawns elsewhere can move one space
			assertEquals(wPawn.legalMove(1, 2, 1, 3, false), true);
			assertEquals(wPawn.legalMove(6, 6, 6, 7, false), true);
			// Test that pawns with a capture opportunity can move forward diagonally
			assertEquals(wPawn.legalMove(1, 2, 0, 3, true), true);
			assertEquals(wPawn.legalMove(1, 2, 2, 3, true), true);
			assertEquals(wPawn.legalMove(6, 1, 5, 2, true), true);
			assertEquals(wPawn.legalMove(6, 1, 7, 2, true), true);
			
			// Illegal Moves
			// Test that pawns cannot move backward
			assertEquals(wPawn.legalMove(1, 1, 1, 0, false), false);
			assertEquals(wPawn.legalMove(1, 2, 1, 1, false), false);
			assertEquals(wPawn.legalMove(6, 6, 6, 5, true), false);
			assertEquals(wPawn.legalMove(6, 6, 6, 0, true), false);
			// Test that pawns without a capture opportunity cannot move left, right, diagonal, or L-shape
			assertEquals(wPawn.legalMove(1, 2, 0, 2, false), false);
			assertEquals(wPawn.legalMove(1, 2, 0, 3, false), false);
			assertEquals(wPawn.legalMove(1, 2, 2, 2, false), false);
			assertEquals(wPawn.legalMove(1, 2, 2, 3, false), false);
			assertEquals(wPawn.legalMove(1, 2, 0, 4, false), false);
			// Test that pawns with a capture opportunity cannot move left, right, or L-shape
			assertEquals(wPawn.legalMove(1, 2, 0, 2, true), false);
			assertEquals(wPawn.legalMove(1, 2, 2, 2, true), false);
			assertEquals(wPawn.legalMove(1, 2, 2, 4, true), false);
			// Test that a pawn cannot move too many spaces forward
			assertEquals(wPawn.legalMove(1, 1, 1, 4, false), false);
			assertEquals(wPawn.legalMove(1, 2, 1, 4, false), false);
		}
		
		@Test
		void testBlackPawn() {
			// Legal Moves
			// Test that pawns can move one or two spaces forward from starting location
			// Note: Black pawns start on row 6
			assertEquals(bPawn.legalMove(2, 6, 2, 5, false), true);
			assertEquals(bPawn.legalMove(2, 6, 2, 4, false), true);
			assertEquals(bPawn.legalMove(5, 6, 5, 5, false), true);
			assertEquals(bPawn.legalMove(5, 6, 5, 4, false), true);
			// Test that pawns elsewhere can move one space
			assertEquals(bPawn.legalMove(2, 5, 2, 4, false), true);
			assertEquals(bPawn.legalMove(5, 4, 5, 3, false), true);
			// Test that pawns with a capture opportunity can move forward diagonally
			assertEquals(bPawn.legalMove(2, 6, 1, 5, true), true);
			assertEquals(bPawn.legalMove(2, 6, 3, 5, true), true);
			assertEquals(bPawn.legalMove(5, 5, 4, 4, true), true);
			assertEquals(bPawn.legalMove(5, 5, 6, 4, true), true);
			
			// Illegal Moves
			// Test that pawns cannot move backward
			assertEquals(bPawn.legalMove(2, 6, 2, 7, false), false);
			assertEquals(bPawn.legalMove(2, 5, 2, 6, false), false);
			assertEquals(bPawn.legalMove(5, 1, 5, 2, false), false);
			assertEquals(bPawn.legalMove(5, 1, 5, 6, false), false);
			// Test that pawns without a capture opportunity cannot move left, right, diagonal, or L-shape
			assertEquals(bPawn.legalMove(2, 6, 1, 6, false), false);
			assertEquals(bPawn.legalMove(2, 6, 3, 6, false), false);
			assertEquals(bPawn.legalMove(2, 6, 1, 5, false), false);
			assertEquals(bPawn.legalMove(2, 6, 3, 5, false), false);
			assertEquals(bPawn.legalMove(2, 6, 3, 4, false), false);
			// Test that pawns with a capture opportunity cannot move left, right, or L-shape
			assertEquals(bPawn.legalMove(2, 6, 1, 6, true), false);
			assertEquals(bPawn.legalMove(2, 6, 3, 6, true), false);
			assertEquals(bPawn.legalMove(2, 6, 3, 4, true), false);
			// Test that pawns cannot move too many spaces forward
			assertEquals(bPawn.legalMove(2, 6, 2, 3, false), false);
			assertEquals(bPawn.legalMove(2, 5, 2, 3, false), false);
		}

}