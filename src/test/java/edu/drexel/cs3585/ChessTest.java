//**************************************************************************************
// ChessTest.java	
//
// Author: Colton Shoenberger
// UserID: cs3585
//
// JUnit tests for the Chess class
//**************************************************************************************

package edu.drexel.cs3585;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class ChessTest {
	
	@BeforeEach
	// Fake output to prevent printing to System.out during tests
	void dummyOutput() {
	System.setOut(new PrintStream (new OutputStream() {
		public void write(int dummy) {} 
		}));
	}
	
	@Test
	void testValidLength() {
		// Should return false if the length is less than three characters
		assertEquals(Chess.validLength("Ka"), false);
		// Should return false if the length is more than four characters
		assertEquals(Chess.validLength("Ka12,"), false);
		// Should return false if the length is four characters, and the last character is not a comma
		assertEquals(Chess.validLength("Ka12"), false);
		// Should return true
		assertEquals(Chess.validLength("Ka1"), true);
		assertEquals(Chess.validLength("Ka1,"), true);
	}
	
	@Test
	void testValidType() {
		// Should return false if not 'K', 'Q', 'R', 'B', 'N', or 'P' (case sensitive)
		assertEquals(Chess.validType('k'), false);
		assertEquals(Chess.validType('A'), false);
		// Should return true
		assertEquals(Chess.validType('K'), true);
		assertEquals(Chess.validType('Q'), true);
		assertEquals(Chess.validType('R'), true);
		assertEquals(Chess.validType('B'), true);
		assertEquals(Chess.validType('N'), true);
		assertEquals(Chess.validType('P'), true);
	}
	
	@Test
	void testValidColumn() {
		// Should return false if not 'a', 'b', 'c', 'd', 'e', 'f', 'g', or 'h' (case sensitive)
		assertEquals(Chess.validColumn('B'), false);
		assertEquals(Chess.validColumn('i'), false);
		// Should return true
		assertEquals(Chess.validColumn('a'), true);
		assertEquals(Chess.validColumn('b'), true);
		assertEquals(Chess.validColumn('c'), true);
		assertEquals(Chess.validColumn('d'), true);
		assertEquals(Chess.validColumn('e'), true);
		assertEquals(Chess.validColumn('f'), true);
		assertEquals(Chess.validColumn('g'), true);
		assertEquals(Chess.validColumn('h'), true);
	}
	
	@Test
	void testValidRow() {
		// Should return false if not 1-8
		assertEquals(Chess.validRow('0'), false);
		assertEquals(Chess.validRow('9'), false);
		// Should return true
		assertEquals(Chess.validRow('1'), true);
		assertEquals(Chess.validRow('2'), true);
		assertEquals(Chess.validRow('3'), true);
		assertEquals(Chess.validRow('4'), true);
		assertEquals(Chess.validRow('5'), true);
		assertEquals(Chess.validRow('6'), true);
		assertEquals(Chess.validRow('7'), true);
		assertEquals(Chess.validRow('8'), true);
	}
	
	@Test
	void testValidFormat() {
		// Should return false if a word in the string has less than three characters
		assertEquals(Chess.validFormat("Ka1, Qb"), false);
		// Should return false if a word in the string has more than four characters
		assertEquals(Chess.validFormat("Ka1, Qb12, Rc3"), false);
		// Should return false if the fourth character in a word is not a comma
		assertEquals(Chess.validFormat("Ka12"), false);
		// Should return false if the first character is not K, Q, R, B, N, or P (case sensitive)
		assertEquals(Chess.validFormat("ka1"), false);
		assertEquals(Chess.validFormat("Aa1"), false);
		// Should return false if second character is not a, b, c, d, e, f, or g, h (case sensitive)
		assertEquals(Chess.validFormat("QA1"), false);
		assertEquals(Chess.validFormat("Qi1"), false);
		// Should return false if the third character is not 1-8
		assertEquals(Chess.validFormat("Rc0"), false);
		assertEquals(Chess.validFormat("Rc9"), false);
		// The following string is valid
		assertEquals(Chess.validFormat("Ka1, Qb2, Rc3, Bd4, Ne5, Pf6, Pg7, Ph8"), true);
	}

	@Test
	void testOneKing() {
		// Should return false if there are no wKings
		assertEquals(Chess.oneKing("Qa1, Rb2, Bc3, Nd4, Pe5"), false);
		// Should return false if there is more than one wKing
		assertEquals(Chess.oneKing("Ka1, Qb2, Kc3, Pd4"), false);
		// Should return true if there is one wKing
		assertEquals(Chess.oneKing("Qa1, Kb2, Pc3"), true);
	}
	
	@Test
	void testNoOverflow() {
		// Should return false if the string contains more than 16 words (pieces)
		assertEquals(Chess.noOverflow("Ra1, Nb1, Bc1, Qd1, Ke1, Bf1, Ng1, Rh1, "
				+ "Pa2, Pb2, Pc2, Pd2, Pe2, Pf2, Pg2, Ph2, Pa3"), false);
		// Should return true if the string contains 16 or less words (pieces)
		assertEquals(Chess.noOverflow("Ra1, Nb1, Bc1, Qd1, Ke1, Bf1, Ng1, Rh1, "
				+ "Pa2, Pb2, Pc2, Pd2, Pe2, Pf2, Pg2, Ph2"), true);
	}
	
	@Test
	void testValidInput() {
		// Should return false if a word does not pass the validFormat test
		assertEquals(Chess.validInput("Ka1, Qb12, Pc3"), false);
		// Should return true if the input does not pass the oneKing test
		assertEquals(Chess.validInput("Qa1, Pb2, Nc3"), false);
		assertEquals(Chess.validInput("Ka1, Qb2, Kc3, Pd4"), false);
		// Should return false if the input does not pass the noOverflow test
		assertEquals(Chess.validInput("Ra1, Nb1, Bc1, Qd1, Ke1, Bf1, Ng1, Rh1, "
				+ "Pa2, Pb2, Pc2, Pd2, Pe2, Pf2, Pg2, Ph2, Pa3"), false);
		// Should return true
		assertEquals(Chess.validInput("Ra1, Nb1, Bc1, Qd1, Ke1, Bf1, Ng1, Rh1, "
				+ "Pa2, Pb2, Pc2, Pd2, Pe2, Pf2, Pg2, Ph2"), true);
	}
	
	@Test
	void testNoOverlap() {
		// Should return false if any word in the string is not of valid length
		assertEquals(Chess.noOverlap("Ka1, Qb"), false);
		assertEquals(Chess.noOverlap("Ka1, Qb12, Ra8"), false);
		// Should return false if two pieces share a location
		assertEquals(Chess.noOverlap("Ka1, Qb2, Pc3, Pb2, Pd5"), false);
		// Should return true if no two pieces share a location
		assertEquals(Chess.noOverlap("Ka1, Qb1, Pa2, Pb2, Nb3, Rc2"), true);
	}
	
	@Test
	void testPieceExists() {
		// Should return false if the piece does not exist on the board
		assertEquals(Chess.pieceExists("Pc2", "Ka1, Ka8"), false);
		assertEquals(Chess.pieceExists("Pc2", "Ka1, Qc2, Ka8"), false);
		// Should return true if the piece exists on the board
		assertEquals(Chess.pieceExists("Pc2", "Ka1, Qb2, Ka8, Qc7, Pc2, Pd4"), true);
	}
	
	@Test
	void testXIndex() {
		// Should return proper values for a-h
		assertEquals(Chess.xIndex('a'), 0);
		assertEquals(Chess.xIndex('b'), 1);
		assertEquals(Chess.xIndex('c'), 2);
		assertEquals(Chess.xIndex('d'), 3);
		assertEquals(Chess.xIndex('e'), 4);
		assertEquals(Chess.xIndex('f'), 5);
		assertEquals(Chess.xIndex('g'), 6);
		assertEquals(Chess.xIndex('h'), 7);
	}
	
	@Test
	void testYIndex() {
		// Should return proper values for a-h
		assertEquals(Chess.yIndex('1'), 0);
		assertEquals(Chess.yIndex('2'), 1);
		assertEquals(Chess.yIndex('3'), 2);
		assertEquals(Chess.yIndex('4'), 3);
		assertEquals(Chess.yIndex('5'), 4);
		assertEquals(Chess.yIndex('6'), 5);
		assertEquals(Chess.yIndex('7'), 6);
		assertEquals(Chess.yIndex('8'), 7);
	}
	
	@Test
	void testMakeWhitePiece() {
		// Should make the proper type of piece
		assertTrue(Chess.makeWhitePiece('K') instanceof King);
		assertTrue(Chess.makeWhitePiece('Q') instanceof Queen);
		assertTrue(Chess.makeWhitePiece('R') instanceof Rook);
		assertTrue(Chess.makeWhitePiece('B') instanceof Bishop);
		assertTrue(Chess.makeWhitePiece('N') instanceof Knight);
		assertTrue(Chess.makeWhitePiece('P') instanceof Pawn);
		// The boolean white for each piece should be true
		assertTrue(Chess.makeWhitePiece('K').white);
		assertTrue(Chess.makeWhitePiece('Q').white);
		assertTrue(Chess.makeWhitePiece('R').white);
		assertTrue(Chess.makeWhitePiece('B').white);
		assertTrue(Chess.makeWhitePiece('N').white);
		assertTrue(Chess.makeWhitePiece('P').white);
		
	}
	
	@Test
	void testMakeBlackPiece() {
		// makeBlackPiece should make the proper type of piece where the boolean white is false
		assertTrue(Chess.makeBlackPiece('K') instanceof King);
		assertTrue(Chess.makeBlackPiece('Q') instanceof Queen);
		assertTrue(Chess.makeBlackPiece('R') instanceof Rook);
		assertTrue(Chess.makeBlackPiece('B') instanceof Bishop);
		assertTrue(Chess.makeBlackPiece('N') instanceof Knight);
		assertTrue(Chess.makeBlackPiece('P') instanceof Pawn);
		// The boolean white for each piece should be false
		assertFalse(Chess.makeBlackPiece('K').white);
		assertFalse(Chess.makeBlackPiece('K').white);
		assertFalse(Chess.makeBlackPiece('K').white);
		assertFalse(Chess.makeBlackPiece('K').white);
		assertFalse(Chess.makeBlackPiece('K').white);
		assertFalse(Chess.makeBlackPiece('K').white);
	}
	
	@Test
	void testMakePieces() {
		Piece[][] testBoard = new Piece[8][8];
		String testPieces = "Ka1, Qb2, Rc3, Bd4, Ne5, Pf6";
		// Test that the correct piece type is being placed on the board
		assertTrue(Chess.makePieces(testPieces, testBoard, true)[0][0] instanceof King);
		assertTrue(Chess.makePieces(testPieces, testBoard, false)[1][1] instanceof Queen);
		assertTrue(Chess.makePieces(testPieces, testBoard, true)[2][2] instanceof Rook);
		assertTrue(Chess.makePieces(testPieces, testBoard, false)[3][3] instanceof Bishop);
		assertTrue(Chess.makePieces(testPieces, testBoard, true)[4][4] instanceof Knight);
		assertTrue(Chess.makePieces(testPieces, testBoard, false)[5][5] instanceof Pawn);
		// Test that the pieces being placed are the correct color
		assertTrue(Chess.makePieces(testPieces, testBoard, true)[0][0].white);
		assertFalse(Chess.makePieces(testPieces, testBoard, false)[1][1].white);
		assertTrue(Chess.makePieces(testPieces, testBoard, true)[2][2].white);
		assertFalse(Chess.makePieces(testPieces, testBoard, false)[3][3].white);
		assertTrue(Chess.makePieces(testPieces, testBoard, true)[4][4].white);
		assertFalse(Chess.makePieces(testPieces, testBoard, false)[5][5].white);
	}
	
	@Test
	void testToColumn() {
		// Should return proper values for 0-7
		assertEquals(Chess.toColumn(0), 'a');
		assertEquals(Chess.toColumn(1), 'b');
		assertEquals(Chess.toColumn(2), 'c');
		assertEquals(Chess.toColumn(3), 'd');
		assertEquals(Chess.toColumn(4), 'e');
		assertEquals(Chess.toColumn(5), 'f');
		assertEquals(Chess.toColumn(6), 'g');
		assertEquals(Chess.toColumn(7), 'h');
	}
	
	@Test
	void testToRow() {
		// Should return proper values for 0-7
		assertEquals(Chess.toRow(0), 1);
		assertEquals(Chess.toRow(1), 2);
		assertEquals(Chess.toRow(2), 3);
		assertEquals(Chess.toRow(3), 4);
		assertEquals(Chess.toRow(4), 5);
		assertEquals(Chess.toRow(5), 6);
		assertEquals(Chess.toRow(6), 7);
		assertEquals(Chess.toRow(7), 8);
	}
	
	@Test
	void testNoBlocks() {
		Piece[][] testBoard2 = new Piece[8][8];
		// Place a queen at d4
		testBoard2[3][3] = new Queen(true);
		
		// Place a knight at e1
		testBoard2[1][4] = new Knight(true);
		
		// Place pawns around the Queen two spaces in each direction
		testBoard2[1][1] = new Pawn(false);
		testBoard2[1][3] = new Pawn(false);
		testBoard2[1][5] = new Pawn(false);
		testBoard2[3][1] = new Pawn(false);
		testBoard2[3][5] = new Pawn(false);
		testBoard2[5][1] = new Pawn(false);
		testBoard2[5][3] = new Pawn(false);
		testBoard2[5][5] = new Pawn(false);
	
		// Try to make the Queen move to a space beyond each pawn.  Should return false.
		assertFalse(Chess.noBlocks(3, 3, 0, 0, testBoard2));
		assertFalse(Chess.noBlocks(3, 3, 0, 3, testBoard2));
		assertFalse(Chess.noBlocks(3, 3, 0, 6, testBoard2));
		assertFalse(Chess.noBlocks(3, 3, 3, 0, testBoard2));
		assertFalse(Chess.noBlocks(3, 3, 3, 6, testBoard2));
		assertFalse(Chess.noBlocks(3, 3, 6, 0, testBoard2));
		assertFalse(Chess.noBlocks(3, 3, 6, 3, testBoard2));
		assertFalse(Chess.noBlocks(3, 3, 6, 6, testBoard2));
		
		// Should return true if attacking into a piece
		assertTrue(Chess.noBlocks(3, 3, 1, 1, testBoard2));
		
		// Should return true if nothing in the way
		assertTrue(Chess.noBlocks(3, 3, 2, 2, testBoard2));
		
		//
		assertTrue(Chess.noBlocks(3, 3, 3, 3, testBoard2));
		
		// Nothing should obstruct the Knight
		assertTrue(Chess.noBlocks(1, 4, 0, 2, testBoard2));
		assertTrue(Chess.noBlocks(1, 4, 0, 6, testBoard2));
		assertTrue(Chess.noBlocks(1, 4, 2, 2, testBoard2));
		assertTrue(Chess.noBlocks(1, 4, 2, 6, testBoard2));
		assertTrue(Chess.noBlocks(1, 4, 3, 4, testBoard2));
		assertTrue(Chess.noBlocks(1, 4, 5, 4, testBoard2));
	}
	
	@Test
	void testFindKing() {
		Piece[][] testBoard3 = new Piece[8][8];
		// Place the white king at c2
		testBoard3[1][2] = new King(true);
		// Place the black king at f6
		testBoard3[6][5] = new King(false);
		// Place some other random pieces to make the test thorough
		testBoard3[3][3] = new Pawn(true);
		testBoard3[4][5] = new Queen(false);
		testBoard3[5][6] = new Bishop (true);
		
		// Expected location for each king
		int[] whiteExpected = new int[] {2, 1};
		int[] blackExpected = new int[] {5, 6};
		
		// Should yield expected results
		assertTrue(Arrays.equals(Chess.findKing(testBoard3, true), whiteExpected));
		assertTrue(Arrays.equals(Chess.findKing(testBoard3, false), blackExpected));
	}
	
	@Test
	void testSafeKing() {
		Piece[][] testBoard4 = new Piece[8][8];
		// Place the white king at b2
		testBoard4[1][1] = new King(true);
		// Place a black queen at e5, threatening check
		testBoard4[4][4] = new Queen(false);
		// Place a white queen at e3, which can prevent check
		testBoard4[2][4] = new Queen(true);
		// Place an arbitrary black king at h8
		testBoard4[7][7] = new King(false);
		
		// If the white queen moves to c3 or d4, the white king will be safe
		assertTrue(Chess.safeKing(2, 4, 2, 2, testBoard4, testBoard4[2][4]));
		assertTrue(Chess.safeKing(2, 4, 3, 3, testBoard4, testBoard4[2][4]));
		// If the white queen captures the black queen, the white king will be safe
		assertTrue(Chess.safeKing(2, 4, 4, 4, testBoard4, testBoard4[2][4]));
		// If the black queen moves anywhere else, the king will remain in check
		assertFalse(Chess.safeKing(2, 4, 2, 7, testBoard4, testBoard4[2][4]));
		// If the white king moves anywhere but to a1 or c3, he will be safe
		assertTrue(Chess.safeKing(1, 1, 1, 2, testBoard4, testBoard4[1][1]));
	}
	
	@Test
	void testPrintLegalMoves() {
		Piece[][] testBoard5 = new Piece[8][8];
		// Place white pieces
		testBoard5[0][0] = new King(true);
		testBoard5[0][1] = new Queen(true);
		testBoard5[1][0] = new Pawn(true);
		testBoard5[1][1] = new Pawn(true);
		// Place black pieces
		testBoard5[2][2] = new Pawn(false);
		testBoard5[0][4] = new Rook(false);
		testBoard5[7][7] = new King(false);
		
		// Pieces to test
		String pawn = "Pb2";
		String king = "Ka1";
		String queen = "Qb1";
		
		// Expected values
		String pawnExpected = "b3, b4, c3";
		String kingExpected = "No legal moves.";
		String queenExpected = "c1, d1, e1";
		
		assertEquals(Chess.printLegalMoves(pawn, testBoard5), pawnExpected);
		assertEquals(Chess.printLegalMoves(king, testBoard5), kingExpected);
		assertEquals(Chess.printLegalMoves(queen, testBoard5), queenExpected);
	}
	
	@Test
	void testUserInput() {
		Piece[][] emptyBoard = new Piece[8][8];

		// Test an input that passes all while loops
		String test1 = "Ka1, Qb2" + "\nKh8, Rg7" + "\nQb2";
		System.setIn(new ByteArrayInputStream(test1.getBytes(StandardCharsets.UTF_8)));
		assertEquals(Chess.userInput(emptyBoard), "Qb2");
		
		// Test an input that fails the first validInput test
		String test2 = "Ka12" + "\nKa1" + "\nKh8" + "\nKh8";
		System.setIn(new ByteArrayInputStream(test2.getBytes(StandardCharsets.UTF_8)));
		assertEquals(Chess.userInput(emptyBoard), "Kh8");

		// Test an input that fails the first noOverlap test
		String test3 = "Ka1, Pa1" + "\nKa1" + "\nKh8" + "\nKa1";
		System.setIn(new ByteArrayInputStream(test3.getBytes(StandardCharsets.UTF_8)));
		assertEquals(Chess.userInput(emptyBoard), "Ka1");
		
		// Test an input that fails the second validInput test
		String test4 = "Ka1" + "\nKa8, Pd" + "\nKa8, Pd4" + "\nPd4";
		System.setIn(new ByteArrayInputStream(test4.getBytes(StandardCharsets.UTF_8)));
		assertEquals(Chess.userInput(emptyBoard), "Pd4");

		// Test an input that fails the second noOverlap test
		String test5 = "Ka1" + "\nKa8, Ba8" + "\nKa8, Ba7" + "\nBa7";
		System.setIn(new ByteArrayInputStream(test5.getBytes(StandardCharsets.UTF_8)));
		assertEquals(Chess.userInput(emptyBoard), "Ba7");

		// Test an input where the pieceToMove fails the validFormat test
		String test6 = "Ka1" + "\nKa8" + "\nKa18" + "\nKa8";
		System.setIn(new ByteArrayInputStream(test6.getBytes(StandardCharsets.UTF_8)));
		assertEquals(Chess.userInput(emptyBoard), "Ka8");

		// Test an input where the pieceToMove fails the pieceExists test
		String test7 = "Ka1" + "\nKa8" + "\nKa2" + "\nKa1";
		System.setIn(new ByteArrayInputStream(test7.getBytes(StandardCharsets.UTF_8)));
		assertEquals(Chess.userInput(emptyBoard), "Ka1");
	}

}