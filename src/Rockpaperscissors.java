import java.util.Random;

public class Rockpaperscissors {
	 private User user;
	   private Computer computer;
	   private int userScore;
	   private int computerScore;
	   private int numberOfGames;
	   private Move currCompMove;
	   private Move currUserMove;

	   private enum Move {
	       ROCK, PAPER, SCISSORS;

	       /**
	       * Compares this move with another move to determining a tie, a win, or
	       * a loss.
	       *
	       * @param otherMove
	       *            move to compare to
	       * @return 1 if this move beats the other move, -1 if this move loses to
	       *         the other move, 0 if these moves tie
	       */
	       public int compareMoves(Move otherMove) {
	           // Tie
	           if (this == otherMove)
	               return 0;

	           switch (this) {
	           case ROCK:
	               return (otherMove == SCISSORS ? 1 : -1);
	           case PAPER:
	               return (otherMove == ROCK ? 1 : -1);
	           case SCISSORS:
	               return (otherMove == PAPER ? 1 : -1);
	           }

	           // Should never reach here
	           return 0;
	       }
	   }

	   private class User {

	       /**
	       * Gets the user move
	       */
	       public Move getMove(String move) {
	           char firstLetter = move.charAt(0);
	           if (firstLetter == 'R')
	               return Move.ROCK;
	           else if (firstLetter == 'P')
	               return Move.PAPER;
	           else
	               return Move.SCISSORS;
	       }
	   }

	   private class Computer {
	       public Move getMove() {
	           Move[] moves = Move.values();
	           Random random = new Random();
	           int index = random.nextInt(moves.length);
	           return moves[index];
	       }
	   }

	   /**
	   * Constructor
	   */
	   public Rockpaperscissors() {
	       user = new User();
	       computer = new Computer();
	       userScore = 0;
	       computerScore = 0;
	       numberOfGames = 0;
	   }

	   /**
	   * Returns the user's current move
	   * @return
	   */
	   public String getUserMove(String move) {
	       this.currUserMove = user.getMove(move);
	       return this.currUserMove.toString();
	   }
	  
	   /**
	   * Returns the computer's current move
	   * @return
	   */
	   public String getCompMove() {
	       this.currCompMove = computer.getMove();
	       return this.currCompMove.toString();
	   }
	  
	   /**
	   * Compares the computer and user move and returns the result
	   * @return
	   */
	   public String result() {
	       int compareMoves = this.currUserMove.compareMoves(this.currCompMove);
	       String result = "";
	       switch (compareMoves) {
	       case 0: // Tie
	           result = "Tie!";
	           break;
	          
	       case 1: // User wins
	           result = this.currUserMove + " beats " + this.currCompMove + ". You won!";
	           userScore++;
	           break;
	          
	       case -1: // Computer wins
	           result = this.currCompMove + " beats " + this.currUserMove + ". You lost.";
	           computerScore++;
	       }
	       numberOfGames += 1;
	      
	       return result;
	   }
	  
	   /**
	   * Prints out the statistics of the game. Calculates ties as 1/2 a win in
	   * percentage won.
	   */
	   public String printGameStats() {
	       int wins = userScore;
	       int losses = computerScore;
	       int ties = numberOfGames - userScore - computerScore;
	       double percentageWon = (wins + ((double) ties) / 2) / numberOfGames;

	       StringBuffer sb = new StringBuffer();
	       sb.append("<html><body><pre>");
	      
	       // Line
	       sb.append("+");
	       sb.append(printDashes(68));
	       sb.append("+<br>");

	       // Print titles
	       sb.append(String.format("| %6s | %6s | %6s | %12s | %14s |<br>", "WINS", "LOSSES", "TIES", "GAMES PLAYED",
	               "PERCENTAGE WON"));

	       // Line
	       sb.append("|");
	       sb.append(printDashes(10));
	       sb.append("+");
	       sb.append(printDashes(10));
	       sb.append("+");
	       sb.append(printDashes(10));
	       sb.append("+");
	       sb.append(printDashes(16));
	       sb.append("+");
	       sb.append(printDashes(18));
	       sb.append("|<br>");

	       // Print values
	       sb.append(String.format("| %6d | %6d | %6d | %12d | %13.2f%% |<br>", wins, losses, ties, numberOfGames,
	               percentageWon * 100));

	       // Line
	       sb.append("+");
	       sb.append(printDashes(68));
	       sb.append("+<br>");
	       sb.append("</pre></body></html>");
	      
	       return sb.toString();
	   }

	   private String printDashes(int numberOfDashes) {
	       StringBuffer sb = new StringBuffer();
	       for (int i = 0; i < numberOfDashes; i++) {
	           sb.append("-");
	       }
	       return sb.toString();
	   }
	

}
