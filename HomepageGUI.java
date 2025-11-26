/*
Smriti Siva
DollarTree Letterboxd Final Project
8/2/24
*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.net.*;
/**
*This class creates the homepage for the program
*/
public class HomepageGUI extends JFrame implements MouseListener, ActionListener {  
   /**
   *the search panel within the search tab
   */
   private JPanel searchPanel;
   /**
   *profile panel within the profile tab
   */
   private JPanel profilePanel;
   /**
   *field where the user enters their search
   */
   private JFormattedTextField searchBar;
   /**
   *stores the movie object (value) and its location (key) on the panel as a key-value pair
   */
   private HashMap<Point, Movie> movieCoords = new HashMap<Point, Movie>();
   /**
   *the movie clicked on
   */
   private Movie currentMovie;
   /**
   *user currently logged in
   */
   private User currentUser;
   /**
   *number of rows for movie poster display
   */
   private final int DISP_ROWS = 3;
   /**
   *number of columns for movie poster display
   */
   private final int DISP_COLS = 5;
   /**
   *width of movie posters
   */
   private final int MOVIE_WIDTH = 100;
   /**
   *height of movie posters
   */
   private final int MOVIE_HEIGHT = 150;
   /**
   *constructs homepage with profile and search tabs
   *@param currentUser the user currently logged in
   */
   public HomepageGUI(User currentUser) {
      this.currentUser = currentUser;
      setSize(700, 600);
      setTitle("Welcome " + this.currentUser.getUser() + "!");
      this.profilePanel = new JPanel();
      profilePanel.setLayout(null);
      this.searchPanel = new JPanel();
      searchPanel.setLayout(null);
      JTabbedPane home = new JTabbedPane();
      home.addTab("PROFILE", null, profilePanel, null);
      home.addTab("SEARCH", null, searchPanel, null);
      add(home, BorderLayout.CENTER);
      setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      setVisible(true);
      loadProfilePage();
      loadSearchPage();
   } 
   /**
   *checks if search button has been clicked
   *@param e the button click
   */
   @Override 
   public void actionPerformed(ActionEvent e) {
      if (e.getActionCommand().equals("search")) {
         String query = searchBar.getText();
         query = query.replaceAll(" ", "+");
         loadSearchPage();
         printPosters(query);
      }
   }
   /**
   *checks which movie was selected based on point clicked on panel
   *@param e the mouse click
   */
   @Override
   public void mouseClicked(MouseEvent e) {
      try {
         int x = searchPanel.getMousePosition().x;
         int y = searchPanel.getMousePosition().y;
         Point p = new Point(x,y);
         currentMovie = checkBounds(p);
         MovieGUI movieDetails = new MovieGUI(currentMovie, currentUser, this);
         movieDetails.setVisible(true);
      }
      catch (Exception ex) {
      }
   }
   @Override
   public void mouseEntered(MouseEvent e) {
   }
   @Override
   public void mouseExited(MouseEvent e) {
   }
   @Override
   public void mousePressed(MouseEvent e) {
   }
   @Override
   public void mouseReleased(MouseEvent e) {
   }
   /**
   *loads information and setup within profile tab
   */
   public void loadProfilePage() {
      profilePanel.removeAll();
      profilePanel.revalidate();
      profilePanel.repaint();
      int numReviews = this.currentUser.getReviews().size();
      JLabel welcome = new JLabel("Welcome " + currentUser.getUser() + "! You have " + numReviews + " reviews");
      welcome.setBounds(10, 7, 400, 20);
      JScrollPane usrReviews = GUIHelper.getScrollTextArea(300, 200, false, currentUser.getReviewsString());
      usrReviews.setBounds(50, 50, 500, 400);  
      profilePanel.add(usrReviews);  
      profilePanel.add(welcome);
   }
   /**
   *loads information and setup within search tab
   */
   public void loadSearchPage() {
      searchPanel.removeAll();
      JLabel pickAMovie = new JLabel("Pick a movie, any movie...");
      pickAMovie.setBounds(10, 7, 200, 20);
      pickAMovie.setLocation(5, 5);
      this.searchBar = new JFormattedTextField(); 
      searchBar.setSize(350, 25);
      searchBar.setLocation(175, 5);
      JButton searchButton = new JButton("SEARCH");
      searchButton.setBounds(525, 5, 90, 24);
      searchPanel.add(pickAMovie);
      searchPanel.add(searchBar);
      searchPanel.add(searchButton);
      searchButton.setActionCommand("search");
      searchButton.addActionListener(this);
   }
   /**
   *prints out up to 15 movie posters based on user search
   *@param query what the user searched up
   */
   public void printPosters(String query) {
      MovieList ml = new MovieList(query);
      ArrayList<Movie> movies = ml.getMovieList();
      int index = 0;
      int xPos = 0;
      int yPos = 0;
      for (int i = 0; i < DISP_COLS; i++) {
         if (i == 0) {
            xPos = 12;
         }
         else{
            xPos += 140;
         }
         for (int j = 0; j < DISP_ROWS; j++) {
            if (movies.size() > index) {
               Movie m = movies.get(index);
               URL url = m.getPoster();
               ImageIcon icon = new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(MOVIE_WIDTH, MOVIE_HEIGHT, Image.SCALE_DEFAULT));
               JLabel label = new JLabel(icon);
               if (j == 0) {
                  yPos = 50;
               }
               else{
                  yPos += 160;
               }
               label.setBounds(xPos, yPos, MOVIE_WIDTH, MOVIE_HEIGHT);
               Point p = new Point(xPos, yPos);
               movieCoords.put(p, m);
               searchPanel.add(label);
               label.addMouseListener(this);
               index++;
            }
            else {
               break;
            }
         }
      }
      searchPanel.revalidate();
      searchPanel.repaint();
   }
   /**
   *checks which movie poster the user clicked on
   *@param clickedP the point at which the user clicked
   *@return the movie the user selected
   */
   public Movie checkBounds(Point clickedP) {
      int x = clickedP.x;
      int y = clickedP.y;
      for (Point p : movieCoords.keySet()) {
         if (x >= p.x && x <= p.x + MOVIE_WIDTH) {
            if (y >= p.y && y <= p.y + MOVIE_HEIGHT) {
               return movieCoords.get(p);
            }
         }
      }
      return null;
   }
}