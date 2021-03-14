Chess
Author: Colton Shoenberger
cs3585@drexel.edu


HOW TO BUILD:

This program utilizes Apache Maven 3.6.1.
If you do not already have Maven installed on your computer,
you can download it here: https://maven.apache.org/download.cgi
Unzip/extract the Maven folder wherever you prefer to keep program files.
Be sure to add the Maven 'bin' folder to your $PATH.

This program also uses Java version 1.8.
If you do not already have the Java SE Runtime Kit version 1.8 or newer installed,
you can download it here: https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
Unzip/extract the folder wherever you prefer to keep program files.
Be sure to add the JDK 'bin' folder to your $PATH.

To build this program, navigate to the base directory ("Chess") in your favorite shell.
Enter the command 'mvn clean install'.
This will compile the .class files, run tests, create reports, and create a JAR.
To run the program, enter the command 'mvn exec:java' (make sure you've installed first).

If you're averse to Maven, you can alternatively load the project in Eclipse.
I utilized Eclipse IDE 2019-03.  You can download Eclipse here: https://www.eclipse.org/downloads/
To load this program in Eclipse, click File -> Import -> General -> Existing project into workspace
Click the radio button next to "Select Root Directory" and browse for the "Chess" directory.
Once you've selected the "Chess" directory, you will see "Chess" under "Projects".
Check the box next to "Chess" and click finish.
You should now see the project in your package explorer.
To run the program within Eclipse, open the Chess.java file and click Run As -> Java Application.


HOW TO TEST:

If you ran 'mvn clean install', the tests have already been run.
You can also run tests with the command 'mvn test'.
The test results will print to the console.
The results are also saved in text files in the directory ../Chess/target/surefire-reports/

The JaCoCo (Java Code Coverage) plug-in is utilized for code coverage tests.
To view the code coverage, navigate to ../Chess/target/site/jacoco/
From this directory, open the file index.html.  You will see a summary of the code coverage.

If you'd prefer to test the program in Eclipse, follow these instructions.
Open the ChessTest.java file.  Click Run -> Run Configurations.
From the "Test" tab, select the radio button next to "Run all tests in the selected project, package, or source folder".
Click "Apply" and then click "Run".  The results of the tests should appear on the left-hand side.
For code coverage, the process is similar.  Click Run -> Coverage Configurations.
From the "Test" tab, select the radio button next to "Run all tests in the selected project, package, or source folder".
Click "Apply" and then click "Run".  The results of the tests should appear on the left-hand side.


HOW TO FORMAT INPUT:

Each piece is represented as a string of three characters.

The first character represents the type of the piece.  This MUST be uppercase.
'K' for King, 'Q' for Queen, 'R' for Rook, 'B' for Bishop, 'N' for Knight, and 'P' for Pawn.

The second character represents the column on the board.  This MUST be lowercase.
'a', 'b', 'c', 'd', 'e', 'f', 'g', or 'h' are valid.

The third character represents the row on the board.  This must be a digit.
'1', '2', '3', '4', '5', '6', '7', '8' are valid.

The program requires that each team have exactly one king placed on the board.
The program prevents either team from having more than 16 pieces on the board.

Each piece is separated by whitespace and (optionally) a comma
For example, the following strings are valid input for WHITE: or BLACK:
Rf1, Kg1, Pf2 Ph2, Pg3
Kb8, Ne8, Pa7, Pb7, Pc7, Ra5


ADDITIONAL REMARKS:

If you're hunting for my source code, you'll find it here:
../Chess/src/main/java/edu/drexel/cs3585/
If you're hunting for my test cases, you'll find them here:
../Chess/src/test/java/edu/drexel/cs3585/

StaticAnalysisReport.pdf summarizes my use of static analysis tools in developing this program.
CoverageReport.pdf summarizes the code coverage achieved.