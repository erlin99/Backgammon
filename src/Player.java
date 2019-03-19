
public class Player {

    public String playerName;
    public char playerColor;
    public String playerColorString;
    public int pipCount;
    public boolean moveMade = false;
    public int currentPosition = -1;
    public int nextPosition;
    

    public Player(String playerName, char playerColor, int pipCount) {
        this.playerName = playerName;
        this.playerColor = playerColor;
        this.pipCount = pipCount;
        setColorString();
    }

    /*
    Method which allows player to click to move pieces. This is called from the action listener
    of each button on the board
     */
    public void clickMove(int position){

        if(!moveMade){
            if(currentPosition == -1){
                currentPosition = position;
                if(Backgammon.counterMap[currentPosition].getColor() != Backgammon.currentPlayer.getPlayerColor()){
                    currentPosition = -1;
                } else {
                    Backgammon.counterMap[currentPosition].setSelected(true);
                }
            } else if(position == currentPosition) {
                currentPosition = -1;
                Backgammon.deSelect();
            }
            else {
                nextPosition = position;
                if(Backgammon.counterMap[nextPosition].getColor() == Backgammon.currentPlayer.getPlayerColor() || Backgammon.counterMap[nextPosition].getColor() == 'B' || Backgammon.counterMap[nextPosition].getNumCounters() < 2){
                    playerMove(currentPosition, nextPosition);
                }
            }
        }
        UI.rePaintMainPanel();
    }

    private void setColorString(){
        if(playerColor == 'W'){
            playerColorString = "White";
        } else if ( playerColor == 'R'){
            playerColorString = "Red";
        }
    }

    //Removes a counter from current Position and adds it to next positions
    public void playerMove(int currentPosition, int nextPosition){

        //Resets the visual of the board
        Backgammon.deSelect();
        UI.rePaintMainPanel();

        //If the move is valid move it there. If there is only one counter take that counter
        if(Moves.getMoves()[currentPosition][nextPosition]){
            if(Backgammon.counterMap[nextPosition].getNumCounters() < 2 && Backgammon.counterMap[nextPosition].getColor() != Backgammon.currentPlayer.getPlayerColor()){
                Backgammon.takeCounter(currentPosition, nextPosition);
                moveMade = true;
            }
            else {
                Backgammon.moveCounter(currentPosition, nextPosition);
                moveMade = true;
            }
        }
        else {
            UI.messagePanelText.append("\nLooks like that is not a valid move!");
        }
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPlayerColor(char playerColor) {
        this.playerColor = playerColor;
    }

    public void setPipCount(int pipCount) {
        this.pipCount = pipCount;
    }

    public void setMoveMade(boolean moveMade) {
        this.moveMade = moveMade;
    }

    public boolean isMoveMade() {
        return moveMade;
    }

    public String getPlayerName() {
        return playerName;
    }

    public char getPlayerColor() {
        return playerColor;
    }

    public int getPipCount() {
        return pipCount;
    }
}
