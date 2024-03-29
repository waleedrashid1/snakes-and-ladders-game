/** SnakesAndLaddersMenuView class
  * Class for the GUI of the main, colour, and help menus
  * @since Jan 22, 2023
  * @author Waleed Rashid
  */

import javax.swing.*;
import java.awt.*;

public class SnakesAndLaddersMenuView extends JPanel
{
  SnakesAndLaddersMainModel mainModel; // Main model
  SnakesAndLaddersMenuModel menuModel; // Menu model
  
  private JLabel logo = new JLabel (); // Logo on main menu
  
  // Parent panels for each Menu
  private JPanel menusPanel = new JPanel ();
  private JPanel startMenuPanel = new JPanel ();
  private JPanel gameInfoPanel = new JPanel ();
  private JPanel colourMenuPanel = new JPanel ();
  private JPanel helpPanel = new JPanel ();
  
  // Buttons on Main Menu
  private JButton play = new JButton ("Play");
  private JButton help = new JButton ("Help");
  private JButton quit1 = new JButton ("Quit");
  
  // Game Info Panel
  private JButton next = new JButton ("Next");
  private JButton back3 = new JButton ("Back");
  private JButton quit4 = new JButton ("Quit");
  
  private JTextField playerName = new JTextField (10);
  private JTextField numRounds = new JTextField (2);
  
  // Buttons on Colour Menu
  private JButton start = new JButton ("Start");
  private JButton back2 = new JButton ("Back");
  private JButton quit2 = new JButton ("Quit");
  
  // Buttons on Help Menu
  private JButton back1 = new JButton ("Back");
  private JButton quit3 = new JButton ("Quit");
  
  private CardLayout menusLayout = new CardLayout ();
  
  // Buttons for colour choices of pieces
  private JButton [] playerColourButtons = new JButton [] {new JButton ("Red"), new JButton ("Blue"), new JButton ("Green"), new JButton ("Yellow"), new JButton ("Purple"), new JButton ("Orange"), new JButton ("")};
  
  private JLabel colourChooseLabel = new JLabel ("Choose Your Colour");
  
  // Colour choices of pieces on colour menu
  private Color [] playerColours = new Color [] {Color.RED, new Color (0,102,255), new Color (25,152,0), Color.YELLOW, new Color (144,0,255), new Color (255,120,0), Color.BLACK};
  
  /** Default constructor for the menu GUI
    * @param menuModel Model of the menu */
  public SnakesAndLaddersMenuView (SnakesAndLaddersMenuModel menuModel)
  {
    super ();
    
    this.menuModel = menuModel; // Connects model of menu object with class
    
    this.menuLayoutView ();
    this.registerControllers ();
    this.menuModel.setMenuGUI (this);
  }
  
  /** Method that creates GUI's of each menu */
  public void menuLayoutView ()
  {
  // MAIN MENU
    this.add (this.startMenuPanel);
    
    BoxLayout menuLayout = new BoxLayout (this.startMenuPanel, BoxLayout.Y_AXIS);
    this.startMenuPanel.setLayout (menuLayout);
    
    JPanel logoPanel = new JPanel ();
    this.startMenuPanel.add (logoPanel);
    
    this.logo.setIcon (new ImageIcon ("logo.png"));
    logoPanel.add (this.logo);
    
    JPanel southButtons1Panel = new JPanel ();
    this.startMenuPanel.add (southButtons1Panel);
    
    this.play.setBackground (new Color (136,198,70));
    this.help.setBackground (new Color (205,198,79));
    this.quit1.setBackground (new Color (242,75,80));
    
    this.play.setPreferredSize (new Dimension (120,55));
    this.help.setPreferredSize (new Dimension (120,55));
    this.quit1.setPreferredSize (new Dimension (120,55));
    
    this.play.setFont (new Font ("Arial", Font.BOLD, 20));
    this.help.setFont (new Font ("Arial", Font.BOLD, 20));
    this.quit1.setFont (new Font ("Arial", Font.BOLD, 20));
    
    southButtons1Panel.add (this.play);
    southButtons1Panel.add (this.help);
    southButtons1Panel.add (this.quit1);
    

  // GAME INFO PANEL
    this.add (this.gameInfoPanel);
    
    BorderLayout gameInfoLayout = new BorderLayout ();
    this.gameInfoPanel.setLayout (gameInfoLayout);
    
    JPanel panel = new JPanel ();
    this.gameInfoPanel.add (panel, BorderLayout.CENTER);
    
    GridLayout panelLayout = new GridLayout (2,1);
    panel.setLayout (panelLayout);
    
    panelLayout.setVgap (65);
    gameInfoLayout.setVgap (85);
    
    // North Section
    JPanel namePanel = new JPanel ();
    panel.add (namePanel);
    
    BoxLayout nameLayout = new BoxLayout (namePanel, BoxLayout.Y_AXIS);
    namePanel.setLayout (nameLayout);
    
    JPanel nameLabelPanel = new JPanel ();
    namePanel.add (nameLabelPanel);
    
    JLabel playerNameLabel = new JLabel ("Enter Your Name:");
    playerNameLabel.setFont (new Font ("Arial", Font.BOLD, 15));
    
    this.playerName.setFont (new Font ("Arial", Font.BOLD, 25));
    this.playerName.setForeground (Color.WHITE);
    
    nameLabelPanel.add (playerNameLabel);
    namePanel.add (this.playerName);
    
    // Center Section
    JPanel roundsPanel = new JPanel ();
    panel.add (roundsPanel);
    
    BoxLayout roundsLayout = new BoxLayout (roundsPanel, BoxLayout.Y_AXIS);
    roundsPanel.setLayout (roundsLayout);
    
    JPanel roundLabelPanel = new JPanel ();
    roundsPanel.add (roundLabelPanel);
    JLabel numRoundsLabel = new JLabel ("How many rounds would you like to play? (1-7)");
    
    numRoundsLabel.setFont (new Font ("Arial", Font.BOLD, 15));
    
    
    JPanel roundsTextPanel = new JPanel ();
    roundsPanel.add (roundsTextPanel);
    
    this.numRounds.setEnabled (false);
    this.numRounds.setFont (new Font ("Arial", Font.BOLD, 25));
    this.numRounds.setForeground (Color.WHITE);
    
    roundLabelPanel.add (numRoundsLabel);
    roundsPanel.add (roundsTextPanel);
    roundsTextPanel.add (this.numRounds);
    
    // South Section
    JPanel buttons = new JPanel ();
    this.gameInfoPanel.add (buttons, BorderLayout.SOUTH);
    
    this.next.setBackground (new Color (136,198,70));
    this.back3.setBackground (new Color (96, 169, 252));
    this.quit4.setBackground (new Color (242,75,80));
    
    this.next.setPreferredSize (new Dimension (90,50));
    this.back3.setPreferredSize (new Dimension (90,50));
    this.quit4.setPreferredSize (new Dimension (90,50));
    
    this.next.setFont (new Font ("Arial", Font.BOLD, 17));
    this.back3.setFont (new Font ("Arial", Font.BOLD, 17));
    this.quit4.setFont (new Font ("Arial", Font.BOLD, 17));
    
    this.next.setEnabled (false);
    
    buttons.add (this.next);
    buttons.add (this.back3);
    buttons.add (this.quit4);
    
    
  // COLOUR MENU
    this.add (this.colourMenuPanel);
    
    BorderLayout colourMenuLayout = new BorderLayout ();
    this.colourMenuPanel.setLayout (colourMenuLayout);
    
    // North Section (Label)
    JPanel colourLogoPanel = new JPanel ();
    
    this.colourMenuPanel.add (colourLogoPanel, BorderLayout.NORTH);
    this.colourChooseLabel.setFont (new Font ("Arial", Font.BOLD, 20));
    colourLogoPanel.add (this.colourChooseLabel);
    
    // Center Section (Colour Button Choices)
    JPanel colourButtonsPanel = new JPanel ();
    GridLayout colourButtonsLayout = new GridLayout (3,2);
    
    this.colourMenuPanel.add (colourButtonsPanel, BorderLayout.CENTER);
    
    colourButtonsPanel.setLayout (colourButtonsLayout);
    
    // Sets and displays colour buttons
    for (int i = 0; i < this.playerColourButtons.length - 1; i++)
    {
      this.playerColourButtons [i].setPreferredSize (new Dimension (185,87));
      this.playerColourButtons [i].setFont (new Font ("Arial", Font.BOLD, 20));
      playerColourButtons [i].setBackground (playerColours [i]);
      colourButtonsPanel.add (playerColourButtons [i]);
    }
    
    // South Section (Buttons)
    JPanel southButtons3Panel = new JPanel ();
    this.colourMenuPanel.add (southButtons3Panel, BorderLayout.SOUTH);
    
    this.start.setBackground (new Color (136,198,70));
    this.back2.setBackground (new Color (96, 169, 252));
    this.quit2.setBackground (new Color (242,75,80));
    
    this.start.setPreferredSize (new Dimension (90,50));
    this.back2.setPreferredSize (new Dimension (90,50));
    this.quit2.setPreferredSize (new Dimension (90,50));
    
    this.start.setFont (new Font ("Arial", Font.BOLD, 17));
    this.back2.setFont (new Font ("Arial", Font.BOLD, 17));
    this.quit2.setFont (new Font ("Arial", Font.BOLD, 17));
    
    this.start.setEnabled (false);
    
    southButtons3Panel.add (this.start);
    southButtons3Panel.add (this.back2);
    southButtons3Panel.add (this.quit2);
    

  // INSTRUCTIONS MENU
    this.add (this.helpPanel);
    
    BoxLayout helpLayout = new BoxLayout (this.helpPanel, BoxLayout.Y_AXIS);
    this.helpPanel.setLayout (helpLayout);
    
    JTextArea instructions = new JTextArea (10,10);
    instructions.setText ("Welcome to Snakes and Ladders!\n\nHow to Play:\nYou will compete against Mr. Burns in a race from the 1st space to the 100th\nspace. Landing on the heads of the snakes that are scattered around the\nboard will result in you sliding down to their tails. On the contrary, landing on\nthe bottom of the ladders on the board will allow you to climb to its top, getting\ncloser to that 100th space. Landing at the bottom of a snake or the top of a\nladder will keep the player where they are, not impacting their placement.\nLanding on another player�s piece will allow you to knock them all the way to\nthe beginning of the board. In order to win, a player must roll exactly the\nnumber of spaces until the 100th square (For example, if the player is on\nsquare 96, they must roll a 4 to win). If a number higher than needed to win is\nrolled, the player will proceed until 100, then bounce back the remaining\namount (For example, if a player is on square 96 and roll a 6, they will get to\n100, and bounce back 2 spaces). Special spaces are randomly generated\naround the board, and landing on them can either benefit or detriment your\nchances of victory.\n\nGood Luck!");                     
    instructions.setEditable (false);
    instructions.setFont (new Font ("Arial", Font.PLAIN, 11));
    
    JPanel southButtons4Panel = new JPanel ();
    
    this.helpPanel.add (instructions);
    this.helpPanel.add (southButtons4Panel);
    
    this.back1.setBackground (new Color (96, 169, 252));
    this.quit3.setBackground (new Color (242,75,80));
    
    this.back1.setPreferredSize (new Dimension (90,50));
    this.quit3.setPreferredSize (new Dimension (90,50));
    
    this.back1.setFont (new Font ("Arial", Font.BOLD, 17));
    this.quit3.setFont (new Font ("Arial", Font.BOLD, 17));
    
    southButtons4Panel.add (this.back1);
    southButtons4Panel.add (this.quit3);
    
    
    
    // Sets colour of all backgrounds
    Color background = new Color (131,131,131);
    this.setBackground (background);
    
    this.startMenuPanel.setBackground (background);
    logoPanel.setBackground (background);
    southButtons1Panel.setBackground (background);
    
    this.gameInfoPanel.setBackground (background);
    roundsTextPanel.setBackground (background);
    this.playerName.setBackground (background);
    this.numRounds.setBackground (background);
    nameLabelPanel.setBackground (background);
    roundLabelPanel.setBackground (background);
    
    colourLogoPanel.setBackground (background);
    colourButtonsPanel.setBackground (background);
    southButtons3Panel.setBackground (background);
    
    this.helpPanel.setBackground (background);
    instructions.setBackground (background);
    southButtons4Panel.setBackground (background);
    
    namePanel.setBackground (background);
    roundsPanel.setBackground (background);
    buttons.setBackground (background);
    panel.setBackground (background);
    
    
  // CARD LAYOUT
    this.add (this.menusPanel);
    this.menusPanel.setLayout (this.menusLayout);
    
    
    this.menusPanel.add (this.startMenuPanel, "1");
    this.menusPanel.add (this.gameInfoPanel, "2");
    this.menusPanel.add (this.colourMenuPanel, "3");
    this.menusPanel.add (helpPanel, "4");
  }
  
  /** Method that runs after the "Play" button on the main menu is pressed */
  public void playPressed ()
  {
    this.menusLayout.show (this.menusPanel, "2");
  }
  
  public void nextPressed ()
  {
    this.menusLayout.show (this.menusPanel, "3");
  }
  
  /** Method that runs after the "Start" button on the colour chooser menu */
  public void startPressed ()
  {
    this.menuModel.openGame ();
  }
  
  /** Method that runs after the "Help" button on the main menu is pressed */
  public void helpPressed ()
  {
    this.menusLayout.show (this.menusPanel, "4");
  }
  
  /** Method that runs after the "Back" button on either the help or colour menu is pressed */
  public void backPressed ()
  {
    this.menusLayout.show (this.menusPanel, "1");
    
    this.playerName.setEnabled (true);
    this.numRounds.setEnabled (false);
    this.next.setEnabled (false);
    
    this.playerName.setText ("");
    this.numRounds.setText ("");
  }
  
  /** Method that assigns the controllers to the buttons on the menus */
  public void registerControllers ()
  {
    // Main Menu button controllers
    SnakesAndLaddersMenuController playController = new SnakesAndLaddersMenuController (this.menuModel, this);
    this.play.addActionListener (playController);
    
    SnakesAndLaddersMenuController helpController = new SnakesAndLaddersMenuController (this.menuModel, this);
    this.help.addActionListener (helpController);
    
    SnakesAndLaddersMenuController quit1Controller = new SnakesAndLaddersMenuController (this.menuModel, this);
    this.quit1.addActionListener (quit1Controller);
    
    
    // Game Info Menu controllers
    SnakesAndLaddersNameController nameController = new SnakesAndLaddersNameController (this.menuModel, this.playerName, this.numRounds);
    this.playerName.addActionListener (nameController);
    
    SnakesAndLaddersRoundsController roundsController = new SnakesAndLaddersRoundsController (this.menuModel, this.numRounds, this.next);
    this.numRounds.addActionListener (roundsController);
    
    SnakesAndLaddersMenuController nextController = new SnakesAndLaddersMenuController (this.menuModel, this);
    this.next.addActionListener (nextController);
    
    SnakesAndLaddersMenuController back3Controller = new SnakesAndLaddersMenuController (this.menuModel, this);
    this.back3.addActionListener (back3Controller);
    
    SnakesAndLaddersMenuController quit4Controller = new SnakesAndLaddersMenuController (this.menuModel, this);
    this.quit4.addActionListener (quit4Controller);
    
    
    // Colour Menu button controllers
    for (int i = 0; i < playerColourButtons.length; i++)
    {
      SnakesAndLaddersMenuController colourControllers = new SnakesAndLaddersMenuController (this.menuModel, this);
      this.playerColourButtons [i].addActionListener (colourControllers);
    }
    
    SnakesAndLaddersMenuController startController = new SnakesAndLaddersMenuController (this.menuModel, this);
    this.start.addActionListener (startController);
    
    SnakesAndLaddersMenuController back2Controller = new SnakesAndLaddersMenuController (this.menuModel, this);
    this.back2.addActionListener (back2Controller);
    
    SnakesAndLaddersMenuController quit2Controller = new SnakesAndLaddersMenuController (this.menuModel, this);
    this.quit2.addActionListener (quit2Controller);
    
    
    // Help menu button controllers
    SnakesAndLaddersMenuController quit3Controller = new SnakesAndLaddersMenuController (this.menuModel, this);
    this.quit3.addActionListener (quit3Controller);
    
    SnakesAndLaddersMenuController back1Controller = new SnakesAndLaddersMenuController (this.menuModel, this);
    this.back1.addActionListener (back1Controller);
  }
  
  /** Method that updates the menu's GUI after buttons are clicked */
  public void menuUpdate ()
  {
    // Displays active choice of colour
    this.colourChooseLabel.setText ("Choose Your Colour: " + this.playerColourButtons [this.menuModel.getColourNum ()].getText ());
    this.colourChooseLabel.setForeground (this.playerColours [this.menuModel.getColourNum ()]);
    
    
    // If a colour is currently chosen, it allows the user to press the start button
    if (this.menuModel.getIsColourChosen () == true)
    {
      this.start.setEnabled (true);
    }
    
    // Disables the start button if a colour is not currently chosen
    else if (this.menuModel.getIsColourChosen () == false)
    {
      this.start.setEnabled (false);
    }
    
  }
}
