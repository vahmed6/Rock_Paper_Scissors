import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class RockpaperscissorsGUI extends JPanel implements ActionListener {

   // Instance variables
   private Rockpaperscissors game;
   private JLabel compScoreLbl;
   private JLabel userScoreLbl;
   private JLabel compMoveLbl;
   private JLabel userMoveLbl;
   private JLabel resultLbl;
   private JButton playAgainBtn;

   /**
   * Constructor
   */
   public RockpaperscissorsGUI() {
       super();
       game = new Rockpaperscissors();
      
       // set layout
       setLayout(new BorderLayout());
       createComponents();
   }

   /**
   * Sets the label's properties
   */
   private void setupLabel(JLabel lbl, int fontSize) {
       lbl.setForeground(Color.BLUE); // Set font color
       lbl.setFont(new Font("Arial", Font.BOLD, fontSize)); // Set font
       lbl.setPreferredSize(new Dimension(180, 100));
       // Set alignment
       lbl.setHorizontalAlignment(JLabel.CENTER);
       // Create border
       lbl.setBorder(BorderFactory.createLineBorder(Color.BLUE));
   }

   /**
   * Creates comonents for the game
   */
   private void createComponents() {
       // Set header
       JLabel hdr = new JLabel("ROCK PAPER SCISSORS");
       setupLabel(hdr, 24);
       add(hdr, BorderLayout.NORTH);

       // Create panel for score and move labels
       JPanel subPanel = new JPanel(new GridLayout(2, 2));

       // Set compScore
       this.compScoreLbl = new JLabel("Computer - 0");
       setupLabel(this.compScoreLbl, 18);
       subPanel.add(this.compScoreLbl);

       // Set userScore
       this.userScoreLbl = new JLabel("User - 0");
       setupLabel(this.userScoreLbl, 18);
       subPanel.add(this.userScoreLbl);

       // Set compMove
       this.compMoveLbl = new JLabel();
       setupLabel(this.compMoveLbl, 14);
       subPanel.add(this.compMoveLbl);

       // Set userMove
       this.userMoveLbl = new JLabel();
       setupLabel(this.userMoveLbl, 14);
       subPanel.add(this.userMoveLbl);

       // Add subPanel to the main panel
       add(subPanel, BorderLayout.CENTER);

       // Add buttons for Start and Play again
       JPanel btnPanel = new JPanel();
       JButton startBtn = new JButton("Start");
       startBtn.addActionListener(this);
       btnPanel.add(startBtn);

       // Play again button
       this.playAgainBtn = new JButton("Play Again");
       this.playAgainBtn.setEnabled(false);
       this.playAgainBtn.addActionListener(this);
       btnPanel.add(this.playAgainBtn);

       // Result label
       this.resultLbl = new JLabel("Result: ");

       JPanel southPanel = new JPanel(new GridLayout(2, 1));
       southPanel.add(this.resultLbl);
       southPanel.add(btnPanel);

       // Add south panel to the main panel
       add(southPanel, BorderLayout.SOUTH);
   }

   /**
   * Display option pane for user input
   */
   private void getUserInput() {
       String move = String.valueOf(JOptionPane.showInputDialog(this, "Your input", "", JOptionPane.PLAIN_MESSAGE, null,
               new String[] { "Rock", "Paper", "Scissors" }, null));
      
       // Set user input to label
       this.userMoveLbl.setText(game.getUserMove(move));
      
       // Set computer move
       this.compMoveLbl.setText(game.getCompMove());
      
       // Get result
       this.resultLbl.setText("Result: " + game.result());
   }

   @Override
   public void actionPerformed(ActionEvent ae) {
       String cmd = ae.getActionCommand();

       if (cmd.equalsIgnoreCase("Quit")) {
           JOptionPane.showMessageDialog(this, game.printGameStats());
           System.exit(0);
          
       } else if (cmd.equalsIgnoreCase("Start")) {
           this.playAgainBtn.setEnabled(true);
           ((JButton) ae.getSource()).setText("Quit");

           // Get user input
           getUserInput();

       } else if (cmd.equalsIgnoreCase("Play Again")) {
           // Get user input
           getUserInput();
       }
   }

   public static void main(String[] args) {
       // Create frame to hold the panel
       JFrame frame = new JFrame("Rock Paper Scissors");
       frame.setSize(400, 400);
       frame.setResizable(false);
       frame.setLocationRelativeTo(null);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       // Add the panel
       frame.add(new RockpaperscissorsGUI());
       frame.setVisible(true);
   }
}
