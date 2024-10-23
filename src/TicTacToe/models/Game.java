package TicTacToe.models;

import TicTacToe.Exceptions.DuplicateSymbolException;
import TicTacToe.Exceptions.InvalidBotCountException;
import TicTacToe.Exceptions.InvalidDimensionException;
import TicTacToe.Exceptions.InvalidNoOfPlayersException;
import TicTacToe.Strategies.WinningStrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Game {
    private List<Player> players;
    private Board board;
    private List<Move> moves;
    private Player winner;
    private GameState gameState;
    private int nextPlayerIndex;
    private List<WinningStrategy> winningStrategies;
    //winningStrategy is basically players deciding on the winning criteria at the start of the game
    //WinningStrategies could include some or all of the following - win by rows, win by columns, win by diagonal, win by corners

    private Game(List<Player> players, Board board, List<WinningStrategy> winningStrategies) {
        this.players = players;
        this.board = board;
        this.moves = new ArrayList<Move>();//at the start of any game, there will be no moves, it will just be an empty arraylist to which we would be adding all the moves
        this.gameState = GameState.IN_PROGRESS; //whenever a new game starts, gameState would be IN_PROGRESS
        this.nextPlayerIndex = 0; // this will loop through the list of players starting with the zeroth index. However, each time a new game starts, we will randomise the indices of the players
        this.winningStrategies = winningStrategies;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public Player getWinner() {
        return winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    //TODO check why need inner and outer Builder classes while using the Builder design pattern
    //for the Builder class, we need an outer Builder class as well
    //first get the Builder object, then set all the values and then build the Builder object
    public static Builder builder(){
        return new Builder();
        //this will call the builder method to set values for the builder object
        //then the build() method will validate all the values before creating a new Builder object
    }

    //we need to build a Builder class, so that before we create an object, we validate that it meets all the criteria
    //This is because object creation is an expensive operation, and we need to ensure that object is created only if all the validations are met
    //all user input should go via the Builder class, nothing should go to the Game class directly
    public static class Builder{
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;
        private int dimension;




        //constructor is kept private so that no one can access it
        //we will also be initiating the following in the constructor with empty new ArrayLists
        private Builder() {
            this.players = new ArrayList<Player>();
            this.winningStrategies = new ArrayList<WinningStrategy>();
            this.dimension = 0;
            //TODO - Check why the dimension is defined inside the builder and not inside the game
        }

        //create the setter methods for the above
        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        //allowing a single player and winning strategy to be added
        //TODO - Check
        public void addPlayer(Player player){
            players.add(player);
        }

        public void addWinningStrategy(WinningStrategy winningStrategy){
            winningStrategies.add(winningStrategy);
        }

        private void validateBotCounts(){
            int botCount=0;
            for(Player player: players){
                if (player.getPlayerType().equals(PlayerType.BOT)){
                    botCount++;
                }
            }
            if (botCount>1){
                //throw an exception
                throw new InvalidBotCountException("Bot count has exceeded 1");
            }
        }

        private void validateDimension(){
            if (dimension <3 || dimension >10){//3 to 10 is the allowed range
                throw new InvalidDimensionException("Invalid dimension. Please enter a dimension between 3 and 10");
            }
        }

        private void validateNumberOfPlayers(){
            if (players.size() != dimension-1){
                throw new InvalidNoOfPlayersException("Invalid number of players. Player count should be dimension minus 1");
            }
        }

        private void validateUniqueSymbolForPlayers(){
            HashSet<Character> set = new HashSet<>();
            for (Player player : players){
                set.add(player.getSymbol().getSymbolChar());
            }
            if (set.size() != players.size()){
                throw new DuplicateSymbolException("Symbol error: Every player should have a unique symbol");
            }
        }

        private void validate(){
            validateDimension();
            validateBotCounts();
            validateNumberOfPlayers();
            validateUniqueSymbolForPlayers();
        }

        //we create a build method which would first validate everything and then build the game

        public Game build(){
            validate();
            return new Game(players, new Board(dimension), winningStrategies);
        }





    }
}
