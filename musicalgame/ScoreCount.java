package musicalgame;

/**
 * Class that keeps a score count for the package MusicalGame. You can get information
 * about the current score by requesting the count and total score.
 * 
 * @author Katelynn Heasley
 */

public class ScoreCount {
    
///////////////////fields/////////////////////
    //Fields for count holders
    private int total = 0;
    private int count = 0;
    private int helper = 0;
    
    //Fields for display messages
    private String display = new String();
    private String win = new String();
    
//////////////constructor/////////////////////
/**No-Argument constructor. Initializes a Score Count object.
* Player's points initialize to 0 and the maximum
* points achievable is 8.
*/   
public ScoreCount(){
    total = 8;
    count = 0; 
}

////////////////methods///////////////////////

/**Method to add points to current score.
 * If count is 7, score will end at 8.
 */
public void addCount(){
    helper = getScore();
   
    if(helper < 7){
      count = helper + 1;
      this.count = count;
    }else{
      count = 8;
    }
}

/**Method to return current player's score.
 * @return count of achieved points
 */
public int getScore(){
   return count; 
}

/**Method to return current player's score
 * out of total points remaining.
 * @return String sentence of count out of total score.
 */
public String getCount(){
    display = "Your score is " + count + " out of " + total;
    return display;
}

/**Method to return "win congratulations" when player
 * reaches total points for the game.
 * @return String win game congratulations 
 */
public String winGame(){
    win = "Congratulations, Maestro!!! 8 out of 8!";
    return win;
}

/**Main method to initialize a ScoreCount object
* @param args the command line arguments
*/
public static void main(String[] args) {
    ScoreCount sC = new ScoreCount();
  }
}
