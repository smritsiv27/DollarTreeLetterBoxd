# DollarTreeLetterBoxd
Movie review interface on Java.
The main class is the DollarTreeLetterboxed file. 
All classes should be compiled before running. All files marked GUI contain the visual element.
Other files are purely functional.
Three main components:
1) Login page -
   user must sign up before logging in if they do not have an account.
   They must enter a username and password under 10 characters and above 3 characters.
   You cannot have a username that is already taken.
   No " " or ",".
   Will prompt user if they logged in successfully or not
3) Homepage -
   two tabs, one search tab and one profile tab.
   Profile tab displays all reviews you have written so far (and updates when you write a new review).
   Search tab has search bar and displays movie posters associated with a search the user enters.
   Click on the poster to get to details on that movie.
   Can search multiple times.
5) MovieDetails -
   movie details displays movie poster, title, description, average rating, and has all loaded movie reviews on the side.
  Movie reviews on the side can be sorted by date or star rating.
  Button provides user option to write review which can be submitted.
  Submitted review updates both user and movie reviews on the spot.
  Once review is written, you cannot write another review (button is disabled).
  However, if you exit out of the movie panel and click on it again, you are allowed to write another review for the same movie.

Instructions:
Create a Java project with the files included in this repo.
Compile files to get the class files.
Run the program from main class to initiate.
