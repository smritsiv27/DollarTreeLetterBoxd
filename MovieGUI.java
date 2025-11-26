/*
Smriti Siva
DollarTree Letterboxd Final Project
8/2/24
*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.net.*;
import java.util.*;
/**
*This class displays the details and reviews of the movie the user clicked on
*/
public class MovieGUI extends JFrame implements ActionListener { 
   /**
   *panel which displays the movie details and reviews
   */ 
   private JPanel moviePanel;
   /**
   *movie being displayed
   */
   private Movie currentMovie;
   /**
   *user currently logged in
   */
   private User currentUser;
   /**
   *space for the user to write a review of the movie
   */
   private JTextArea reviewEditor;
   /**
   *object of the HomepageGUI class to reload profile tab if a new review is made
   */
   private HomepageGUI homepage;
   /**
   *enters the user review
   */
   private JButton writeReview;
   /**
   *space for the user to rate the movie out of 5 stars
   */
   private JTextField stars;
   /**
   *checks whether writeReview button is already disabled or not
   */
   boolean disableReview;
   /**
   *sets up panel with review and write review functionality
   *@param currentMovie movie being displayed
   *@param currentUser user currently logged in
   *@param homepage object of the HomepageGUI class to reload profile tab if a new review is made
   */
   public MovieGUI(Movie currentMovie, User currentUser, HomepageGUI homepage) { 
      moviePanel  = new JPanel();
      reviewEditor = GUIHelper.getTextArea(100, 200, true, ""); 
      writeReview = new JButton("Review?");
      this.currentUser = currentUser;
      this.currentMovie = currentMovie;  
      this.homepage = homepage;  
      disableReview = false;
      loadMovieDetails();
   }
   /**
   *loads the details of the movie
   */
   private void loadMovieDetails() {
      setSize(1000, 700);
      setTitle("Movie Details");
      moviePanel.setLayout(null);
      moviePanel.removeAll();
      if (currentMovie != null) {
         JLabel movieAvg = new JLabel("AVG STARS: " + currentMovie.getAvgRating(currentUser.getUser()));
         movieAvg.setBounds(12, 40, 120, 20);
         URL url = currentMovie.getPoster();
         ImageIcon icon = new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(100, 150, Image.SCALE_DEFAULT));
         JLabel poster = new JLabel(icon);
         poster.setBounds(12, 70, 100, 150);
         JLabel title = new JLabel(currentMovie.getTitle());
         title.setBounds(120, 50, 500, 50);
         JTextArea description = GUIHelper.getTextArea(50, 100, false,currentMovie.getDesc());
         description.setBounds(120, 100, 500, 100);
         writeReview.setEnabled(true);
         writeReview.setBounds(12, 230, 100, 25);
         writeReview.setActionCommand("review");
         writeReview.addActionListener(this);
         JScrollPane allReviews  = GUIHelper.getScrollTextArea(
                                   300, 200, false, 
                                   currentMovie.getReviewsString(currentUser.getUser()));
         allReviews.setBounds(650, 65, 300, 600);
         JButton sortByStar = new JButton("Rating Sort");
         sortByStar.setBounds(650, 15, 100, 25);
         sortByStar.setActionCommand("starsort");
         sortByStar.addActionListener(this);
         JButton sortByDate = new JButton("Date Sort");
         sortByDate.setBounds(800, 15, 100, 25);
         sortByDate.setActionCommand("datesort");
         sortByDate.addActionListener(this);
         moviePanel.add(movieAvg);
         moviePanel.add(title);
         moviePanel.add(poster);
         moviePanel.add(description);
         moviePanel.add(writeReview);
         moviePanel.add(sortByDate);
         moviePanel.add(sortByStar);
         moviePanel.add(allReviews);
      }
      else {
         JLabel errLabel = new JLabel("Movie not found :(");
         moviePanel.add(errLabel);
      }
      moviePanel.revalidate();
      moviePanel.repaint();
      add(moviePanel, BorderLayout.CENTER);
      setVisible(true);
   }
   /**
   *checks if review or submit buttons have been clicked
   *@param e the mouse click
   */
   @Override 
   public void actionPerformed(ActionEvent e) {
      if (e.getActionCommand().equals("review")) {
         enterReview();
      }
      if (e.getActionCommand().equals("submit")) {
         String review = reviewEditor.getText();
         addReview(review);
         reviewEditor.setText(null);
         loadMovieDetails();
         homepage.loadProfilePage();
         writeReview.setEnabled(false);
         disableReview = true;
      }
      if (e.getActionCommand().equals("datesort")) {
         Collections.sort(currentMovie.getReviews(currentUser.getUser()), new SortReviewsByDate());
         loadMovieDetails();
         if (disableReview) {
            writeReview.setEnabled(false);
         }
      }
      if (e.getActionCommand().equals("starsort")) {
         Collections.sort(currentMovie.getReviews(currentUser.getUser()), new SortReviewsByStar());
         loadMovieDetails();
         if (disableReview) {
            writeReview.setEnabled(false);
         }
      }
   }
   /**
   *creates space for the user to write a review and rate the movie
   */
   public void enterReview() {
      reviewEditor.setBounds(12, 270, 600, 300);
      JLabel starsLabel = new JLabel("STARS (0 - 5) ");
      starsLabel.setBounds(12, 600, 100, 25);
      stars = new JTextField(5);
      stars.setBounds(120, 600, 100, 25);
      JButton submit = new JButton("Submit");
      submit.setBounds(500, 600, 115, 25);
      submit.setActionCommand("submit");
      submit.addActionListener(this);
      moviePanel.add(reviewEditor);
      moviePanel.add(starsLabel);
      moviePanel.add(stars);
      moviePanel.add(submit);
      moviePanel.revalidate();
      moviePanel.repaint();
   }
   /**
   *updates movie reviews and user reviews with the new user review
   *@param review review the user wrote
   */
   public void addReview(String review) {
      int star = 0;
      try {
         star = Integer.parseInt(stars.getText());
         if (star < 0) {
            star = 0;
         }
         else if (star > 5) {
            star = 5;
         }
      }
      catch (Exception e) {
         star = 0;
      }
      Rating r = new Rating(currentUser.getUser(), currentMovie.getID(), star, review);
      currentUser.addReview(r);
      currentMovie.addReview(r);
   }
}