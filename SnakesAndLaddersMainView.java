/** SnakesAndLaddersMainView class
  * Class for the GUI of the main playing area
  * @since Jan 22, 2023
  * @author Waleed Rashid
  */

import javax.swing.*;
import java.awt.*;

public class SnakesAndLaddersMainView extends JPanel
{
  SnakesAndLaddersMainModel mainModel; // Main model
  SnakesAndLaddersMenuModel menuModel; // Menu model
  
  private Color [] playerColours = new Color [] {Color.RED, new Color (0,102,255), new Color (25,152,0), Color.YELLOW, new Color (144,0,255), new Color (255,120,0), Color.BLACK}; // Colours of each piece
  
  private JLabel winner = new JLabel ("Welcome"); // Title label which declares winner at the end of a round
  
  // Stat Boxes
  private JTextArea playerStats = new JTextArea (7,12);
  private JTextArea burnsStats = new JTextArea (7,12);
  
  // Buttons
  private JButton rollDice = new JButton ("Roll Dice");
  
  // Bottom Buttons
  private JButton quit = new JButton ("Quit");
  private JButton playAgain = new JButton ("Play Again");
  private JButton returnBack = new JButton ("Return Back");
  private JButton saveResults = new JButton ("Save Results");
  
  // Text that displays the current rolled number for each player
  private JLabel playerRoll = new JLabel ();
  private JLabel burnsRoll = new JLabel ();
  
  private JLabel round = new JLabel (" Round 1"); // Current round
  
  // Images of player's dice
  private JLabel dice1Image = new JLabel ();
  private JLabel dice2Image = new JLabel ();
  private ImageIcon [] dice1Faces = new ImageIcon [] {new ImageIcon ("one.jpg"), new ImageIcon ("two.jpg"), new ImageIcon ("three.jpg"), new ImageIcon ("four.jpg"), new ImageIcon ("five.jpg"), new ImageIcon ("six.jpg")};
  private ImageIcon [] dice2Faces = new ImageIcon [] {new ImageIcon ("one.jpg"), new ImageIcon ("two.jpg"), new ImageIcon ("three.jpg"), new ImageIcon ("four.jpg"), new ImageIcon ("five.jpg"), new ImageIcon ("six.jpg")};
  
  // Colour choices of pieces to assign to player and computer
  private ImageIcon [] pieceColours = new ImageIcon [] {new ImageIcon ("red.png"), new ImageIcon ("blue.png"), new ImageIcon ("green.png"), new ImageIcon ("yellow.png"), new ImageIcon ("purple.png"), new ImageIcon ("orange.png")};
  
  // Indicators of both piece colours
  private JLabel playerPieceSymbol = new JLabel ();
  private JLabel computerPieceSymbol = new JLabel ();
  
  private Graphics g; // Graphics object for drawing
  
  // Console logs
  private JTextArea playerLog = new JTextArea (14,11);
  private JTextArea computerLog = new JTextArea (14,11);
  private JTextArea burnsChat = new JTextArea (3,15);
  
  private JLabel burns = new JLabel (new ImageIcon ("burnsPic.png")); // Image of burns
  
  /** Default constructor for the main playing area GUI
    * @param mainModel Model of the main GUI
    * @param menuModel Model of the menu's model */
  public SnakesAndLaddersMainView (SnakesAndLaddersMainModel mainModel, SnakesAndLaddersMenuModel menuModel)
  {
    super ();
    
    // Connects models
    this.mainModel = mainModel;
    this.menuModel = menuModel;
    
    this.mainLayoutView ();
    this.mainModel.setGUI (this);
    this.registerControllers ();
    this.update (g);
    
  }
  
  /** Method for the main GUI view */
  public void mainLayoutView ()
  {
    BorderLayout mainLayout = new BorderLayout ();
    this.setLayout (mainLayout);
    
    
  // South Section (Bottom buttons)
    JPanel southButtonsPanel = new JPanel ();
    this.add (southButtonsPanel, BorderLayout.SOUTH);
    
    this.quit.setPreferredSize (new Dimension (100,50));
    this.playAgain.setPreferredSize (new Dimension (100,50));
    this.returnBack.setPreferredSize (new Dimension (125,50));
    this.saveResults.setPreferredSize (new Dimension (125,50));
    
    southButtonsPanel.add (this.quit);
    southButtonsPanel.add (this.playAgain);
    southButtonsPanel.add (this.returnBack);
    southButtonsPanel.add (this.saveResults);
    
    this.saveResults.setEnabled (false);
    
    BorderLayout innerLayout = new BorderLayout ();
    JPanel mainCenterPanel = new JPanel ();
    
    this.add (mainCenterPanel, BorderLayout.CENTER);
    
    
  // North Section (Round, Welcome, Player dice result)
    JPanel north = new JPanel ();
    this.add (north, BorderLayout.NORTH);
    
    BorderLayout northLayout = new BorderLayout ();
    north.setLayout (northLayout);
    
    JPanel title = new JPanel ();
    north.add (title, BorderLayout.NORTH);
    
    this.round.setFont (new Font ("Arial", Font.ITALIC, 20));
    this.winner.setFont (new Font ("Arial", Font.BOLD, 25));
    this.playerRoll.setFont (new Font ("Arial", Font.BOLD, 20));
    
    north.add (this.round, BorderLayout.WEST);
    title.add (this.winner, BorderLayout.SOUTH);
    north.add (this.playerRoll, BorderLayout.EAST);
    
    
  // Center Section (Main Board)
    JPanel center = new JPanel ();
    mainCenterPanel.add (center, BorderLayout.CENTER);
    
    SnakesAndLaddersBoardDrawing board = new SnakesAndLaddersBoardDrawing (mainModel, this.menuModel.getColourNum (), this.menuModel.getComputerColour ());
    center.add (board); 
    
    
    
  // West Section (Console Logs, Burns Chat)
    JPanel westPanel = new JPanel ();
    this.add (westPanel, BorderLayout.WEST);
    
    BoxLayout westLayout = new BoxLayout (westPanel, BoxLayout.Y_AXIS);
    westPanel.setLayout (westLayout);
    
    JPanel logPanel = new JPanel ();
    westPanel.add (logPanel);
    
    JPanel statsPanel = new JPanel ();
    westPanel.add (statsPanel);
    
    JPanel burnsPanel = new JPanel ();
    westPanel.add (burnsPanel);
    
    BoxLayout burnsLayout = new BoxLayout (burnsPanel, BoxLayout.Y_AXIS);
    burnsPanel.setLayout (burnsLayout);
    
    JPanel burnsChatPanel = new JPanel ();
    JPanel burnsRollPanel = new JPanel ();
    
    this.playerLog.setEditable (false);
    this.computerLog.setEditable (false);
    this.burnsChat.setEditable (false);
    
    this.playerLog.setFont (new Font ("Arial", Font.BOLD, 12));
    this.computerLog.setFont (new Font ("Arial", Font.BOLD, 12));
    this.burnsChat.setFont (new Font ("Arial", Font.BOLD, 17));
    
    this.playerLog.setText (this.menuModel.getPlayerName () + " Log");
    this.computerLog.setText ("Burns Log");
    
    this.playerStats.setEditable (false);
    this.burnsStats.setEditable (false);
    
    this.burnsRoll.setFont (new Font ("Arial", Font.BOLD, 20));
    
    logPanel.setBorder (BorderFactory.createTitledBorder ("Log"));
    statsPanel.setBorder (BorderFactory.createTitledBorder ("Round Stats"));
    burnsPanel.setBorder (BorderFactory.createTitledBorder ("Burns"));
    
    westPanel.add (logPanel);
    logPanel.add (this.playerLog);
    logPanel.add (this.computerLog);
    westPanel.add (statsPanel);
    statsPanel.add (this.playerStats);
    statsPanel.add (this.burnsStats);
    westPanel.add (burnsPanel);
    burnsPanel.add (burnsChatPanel);
    burnsChatPanel.add (this.burns);
    burnsChatPanel.add (burnsChat);
    burnsPanel.add (burnsRollPanel);
    burnsRollPanel.add (this.burnsRoll);
    
    
    
  // East Section (Dice, Player/Computer Symbols)
    JPanel east = new JPanel ();
    
    mainCenterPanel.add (east, BorderLayout.EAST);
    
    BorderLayout eastLayout = new BorderLayout ();
    
    east.setLayout (eastLayout);
    
    JPanel dicePanel = new JPanel ();
    
    east.add (dicePanel, BorderLayout.NORTH);
    
    JPanel rollDicePanel = new JPanel ();
    this.rollDice.setPreferredSize (new Dimension (150,50));
    east.add (rollDicePanel, BorderLayout.CENTER);
    
    this.rollDice.setFont (new Font ("Arial", Font.BOLD, 15));
    
    rollDicePanel.add (this.rollDice, BorderLayout.CENTER);
    
    dicePanel.add (this.dice1Image);
    dicePanel.add (this.dice2Image);
    
    JPanel playersPanel = new JPanel ();
    BoxLayout playersLayout = new BoxLayout (playersPanel, BoxLayout.Y_AXIS);
    playersPanel.setLayout (playersLayout);
    
    east.add (playersPanel, BorderLayout.SOUTH);
    
    
    JPanel playerPanel = new JPanel ();
    BoxLayout playerLayout = new BoxLayout (playerPanel, BoxLayout.X_AXIS);
    playerPanel.setLayout (playerLayout);
    playersPanel.add (playerPanel);
    
    JPanel computerPanel = new JPanel ();
    BoxLayout computerLayout = new BoxLayout (computerPanel, BoxLayout.X_AXIS);
    computerPanel.setLayout (computerLayout);
    playersPanel.add (computerPanel);
    
    JLabel playerLabel = new JLabel ();
    JLabel computerLabel = new JLabel ("Burns");
    
    playerLabel.setText (this.menuModel.getPlayerName ());
    
    this.playerPieceSymbol.setIcon (pieceColours [this.menuModel.getColourNum ()]);
    this.computerPieceSymbol.setIcon (pieceColours [this.menuModel.getComputerColour ()]);
    
    playerLabel.setFont (new Font ("Times New Roman", Font.ITALIC, 20));
    computerLabel.setFont (new Font ("Times New Roman", Font.ITALIC, 20));
    this.playerPieceSymbol.setPreferredSize (new Dimension (125,125));
    this.computerPieceSymbol.setPreferredSize (new Dimension (125,125));
    
    playerPanel.add (this.playerPieceSymbol);
    playerPanel.add (playerLabel);
    computerPanel.add (computerLabel);
    computerPanel.add (this.computerPieceSymbol);
  }
  
  /** Method which disables the roll dice button after a game is finished */
  public void gameEnded ()
  {
    this.rollDice.setEnabled (false);
  }
  
  /** Method which assigns controllers to the buttons */
  public void registerControllers ()
  {
    SnakesAndLaddersMainController rollDiceController = new SnakesAndLaddersMainController (this.mainModel, this);
    this.rollDice.addActionListener (rollDiceController);
    
    SnakesAndLaddersMainController quitController = new SnakesAndLaddersMainController (this.mainModel, this);
    this.quit.addActionListener (quitController);
    
    SnakesAndLaddersMainController playAgainController = new SnakesAndLaddersMainController (this.mainModel, this);
    this.playAgain.addActionListener (playAgainController);
    
    SnakesAndLaddersMainController returnBackController = new SnakesAndLaddersMainController (this.mainModel, this);
    this.returnBack.addActionListener (returnBackController);
    
    SnakesAndLaddersMainController saveResultsBackController = new SnakesAndLaddersMainController (this.mainModel, this);
    this.saveResults.addActionListener (saveResultsBackController);
  }
  
  /** Method that update's the main GUI's view
    * @param g - draws board with new player positions */
  public void update (Graphics g)
  {
    this.dice1Image.setIcon (dice1Faces [this.mainModel.getDice1Num () - 1]);
    this.dice2Image.setIcon (dice2Faces [this.mainModel.getDice2Num () - 1]);
    
    this.playerRoll.setText (this.menuModel.getPlayerName () + " Rolled: " + this.mainModel.getTotal () + " ");
    this.burnsRoll.setText ("Burns Rolled: " + this.mainModel.getComputerRoll ());
    
    this.round.setText (" Round " + this.mainModel.getCurrentRound () + "/" + this.menuModel.getTotalRounds ());
    
    this.playerLog.setText (this.mainModel.getPlayerLogText ());
    this.computerLog.setText (this.mainModel.getComputerLogText ());
    this.burnsChat.setText (this.mainModel.getComputerMessage ());
    
    // Resets logs and chat when play again button is pressed
    if (this.mainModel.getGameEnded () == true)
    {
      this.playerLog.setText (this.menuModel.getPlayerName () + " Log");
      this.computerLog.setText ("Burns Log");
      this.burnsChat.setText ("");
      this.rollDice.setEnabled (true);
    }
    
    // Disables play again button during final round
    if (this.mainModel.getOnLastRound () == true)
    {
      this.playAgain.setEnabled (false);
    }
    
    // Declares winner of round, shows stats, and allows user to save results
    if (this.mainModel.getIsWinner () == true)
    {
      this.winner.setText ("WINNER: " + this.mainModel.getWinner ());
      this.playerStats.setText (this.menuModel.getPlayerName () + " Stats\n " + this.mainModel.getPlayerStats ());
      this.burnsStats.setText ("Burns Stats\n" + this.mainModel.getComputerStats ());
      this.saveResults.setEnabled (true);
    }
   
    // Sets stats as blank and no winner is declared
    else if (this.mainModel.getIsWinner () == false)
    {
      this.winner.setText ("Welcome");
      this.playerStats.setText (this.menuModel.getPlayerName () + " Stats\n");
      this.burnsStats.setText ("Burns Stats\n");
      this.saveResults.setEnabled (false);
    }
    
    this.repaint (); // Repaints board
  }
  
}