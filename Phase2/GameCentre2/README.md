# GameCentre
A sliding tiles puzzle game using numbers and images. It features a basic login application where new 
users are required to register before playing. The puzzle game comes with 3x3, 4x4, 5x5 game modes that 
users can choose to play!

----
## Installation and Setup
1. Install Android Studio
2. Get the Phase 1 URL for our group project from MarkUs. 
3. Create an Android Virtual Device within Android Studio. Select a Pixel2 smartphone as the device to 
emulate, specifiying the device OS as Android 8.1 API 27. Create and launch the virtual device and ensure 
it loads correctly.

----
## A tour of the code
* **Class Board:** It keeps track of Tile objects in a 2D array. The constructor is given a 1D List of Tiles,
 and these are used to populate the 2D array. This class is also Observable, which means that other classes 
 can sign up to be alerted when the contents change. This happens, as you'll see, when swapTiles is called. 
 The call to notifyAll ends up letting the GameActivity know that the user interface needs to be updated.
 
* **Class BoardManager:** BoardManager manipulates the Board, figuring out whether a tap is legal, checking 
whether the puzzle has been solved, performing a move the user has made, undoing a previous move, and 
calculating the user's score using the number of moves and time taken.

* **Class ComplexityActivity:** This screen allows a user to choose between the provided 3x3, 4x4, 5x5 game 
modes for the number tiles puzzle game as well as allowing the user to change the number of steps they wish 
to undo when they are playing the game(Bonus). A user may also choose to play sliding image tiles instead of sliding 
number tiles.

* **Class GameActivity:** This is the main sliding tile puzzle. It uses a BoardManager to keep track of the 
board, and also manages all the user interface components. A timer and number of steps will be shown when a 
user is playing the game so that they will be able to see how much time/steps they are taking.

* **Class Loadsave:** Loadsave keeps a record of all users' game states by saving to a file should a user 
decide to save their game. A user may choose to load a previous saved game at a later time.

* **Class SignUpActivity:** New users are required to make an account before playing. It uses 
SharedPreferences to store the user's username and password.

* **Class SignUpSignInActivity** This is the interface where users have to sign in into their accounts before 
getting access to the sliding tiles puzzle game. New users can click on register to create an account. Account
holders may choose to change their password as well.

* **Class PasswordChangeActivity:** A user may choose to change their password at any given time. It uses 
SharedPreferences delete the old password and store the new password.(Bonus)

* **Class Session:** A singleton model that stores a specific user's information such as their username, 
password and game scores. There can only be one user logged in at a time. A session ends when a user sign out.

* **Class UserManager:** A class that uses SharedPreferences to store users' usernames and passwords. It
can then use this information to check whether a user has provided the correct information when signing in,
whether a username has already been taken when a new user creates an account and if a user is eligible to 
change their password.

* **Class ScoreActivity:** This screen shows a user's game score after completing a game of sliding tiles. 
It also shows their best score as well as a ranking board that lists the top three people with the highest 
scores in the game.

* **Class ScoreBoardActivity:** This screen shows the ranking of the top 10 players of the game at all time with their
username, top score and ranking. Note that if two players have the same scores, the player who achieved that score
earlier is ranked higher.

* **Class ScoreBoardManager:** Extends the BoardManager class and uses HashMaps to keep record of users' 
best scores. Additional features include getting a list of high scores paired with the respective users who 
obtained the scores. 

* **Class ImageTilesActivity:** This screen allows the user to select one of the two images as the tiles 
background when playing the sliding tiles game.(Bonus)  

* **Class StartingActivity:** This screen is where users can start a new game, load a previously saved game, 
save a current game state, resume a game, as well as signing out. The game state is automatically saved each 
time a user makes five steps. Clicking on "load saved game" will load the game state right after a user has
completed five steps but just before their next five. Resuming a game however, will bring user right back to
where they left off.
/////////////////////////////////////////////////////////////////////////////////
* **Game FlipIt:** This is a board game which the player need to achieve a plain white board with a
set of designed board with blue tiles in place. The user can choose the complexity which
corresponds to the 3*3, 4*4, 5*5 boards. They can also switch the level complexity in each board
length. The moves, time are recorded in order to calculate the score. Detailed information are
explained in the following classes.

* **Class FlipComplexityActiviy:** This activity class keeps track of the complexity of the flipIt game.
The activity contains the seekBar which is a feature we created to allow the users to choose the
complexity they want. The activity only contains view, all the buttons are stored into the
controller class.

* **Class FlipGameActivity:** This is an activity class that contains the timer, the grid view and
also creating and updating the board. The grid view gives an optimized view of the game for the user.
Un undo button is also included to let the user undo during the game.

* **Class FlipGestureDetectGridView:** This class decides the animation when the user touches the tiles.

* **Class FlipIt:** This class contains the initial setting of the flipIt game. For example, it keeps
track of changing the tile color at a specific row, column place. There is also an iterator implemented
to iterate over the tiles.

* **Class FlipItComplexityController:** The complexity controller class keeps track of all the buttons


* **Class FlipItLoadSaveButtonController:** This load the flip it game and save the flip it game.

* **Class FlipManager:** Flip manager manipulates the flips, it create flips according to different
complexity and level, figure out whether the game is over, the score the user get, change the color
of corresponding tiles when one tile is flipped, and undo the movement.

* **Class FlipStartingActivity:** This class could start, resume, load or save the flip it game.
It could also switch the game view to play the game, or switch to choose the complexity, or switch
to the blackjack game.



* **Class FlipItLoadSaveButtonController:**This class extends the LoadSaveButtonController and there
are new game listener, switch to complexity, and switch to new game method. Since these do not
belong to the view, we extracted it from the activity class.


* **Class Game:** This class records the number of columns and number of rows. It create the list
 of tiles according to different complexity. It can figure out the number of total tiles, and return
 the correct tiles according to the given position.


* **Class GameActivity:** This class creates the frame of board, start to run the timer, create
the button to display the tiles, and update the background, activate the undo buttons.

* **Class GameManager:** This class manage the game, it contains the complexity, step counting,
getting and setting step counter, getting and setting timer, getting movements made and score. It
also record whether game is over and undo the step.


* **Class LevelComplexityActivity:** This activity is somehow similar to the FlipComplexityActivity.
User can choose the level complexity within each of the complexity (3*3, 4*4, 5*5). There are 3 levels
inside each complexity board.


* **Class LoadSave** This class is for loading and saving game manager to the file.

* **Class LoadSaveButtonController:** This is an abstract class. It adds listeners to save, resume
and load buttons. It offer the ability to add a new game, switch game and switch complexity.

* **Class MainActivity:**

* **Class MovementController:**

----
## Running the code
1. Run -> run app
2. Select Pixel 2 API 27 as your virtual device.
3. Click Run and Follow the Instructions

----
## Built With Android Studio
## Versioning
We use Git for versioning. For the versions available, see the repository history

----
## Acknowledgements
Great Appreciation to the Princeton University and Onur Kagan
https://introcs.cs.princeton.edu/java/43stack/Stack.java.html
https://github.com/onurkagan/Singleton-Design-Pattern-Example/blob/master/app/src/main/java/com/
onurkagan/singleton_design_pattern_example/UserSingletonModel.java
