package TicTacToe;

import TicTacToe.Controller.GameController;
import TicTacToe.Strategies.BotPlayingStrategy.BotPlayingStrategyFactory;
import TicTacToe.models.*;

import java.sql.Array;
import java.sql.SQLOutput;
import java.util.*;

public class TicTacToeGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GameController gameController = new GameController();

        System.out.println("Please enter the board size(nxn) - Enter n between 3 to 10: ");
        int dimension = sc.nextInt();

        System.out.println("Will there be any bot in the game Y/N? ");
        String isBotPresent = sc.next();
        boolean botPresent = false;
        if (isBotPresent.equals("Y") || isBotPresent.equals("y") || isBotPresent.equals("yes") || isBotPresent.equals("Yes") || isBotPresent.equals("YES")){
            botPresent = true;
        }

        List<Player> players = new ArrayList<>();
        int iteratorNumber = dimension-1;
        if (botPresent){
            iteratorNumber=dimension-2;
        }

        HashSet<String> symbolSet = new HashSet<>();



        for (int i=0; i<iteratorNumber; i++){

            //TODO  - need to ensure that no duplicate symbols are added. check here itself
            System.out.println("Enter the name of the player " + (i+1));
            String playerName = sc.next();
            System.out.println("Enter the character symbol of the player " + (i+1));
            String characterSymbol = sc.next();
            symbolSet.add(characterSymbol);


            players.add(new Player(playerName, PlayerType.HUMAN, new Symbol(characterSymbol.charAt(0)) ));
        }

        if (botPresent){
            //TO-DO  - need to ensure that no duplicate symbols are added. check here itself
//            System.out.println("Enter the name of the BOT ");
            String botName = "BOT";
            Random random = new Random();
            String characterSymbol = String.valueOf((char)('!' + random.nextInt(26)));
            System.out.println("Character symbol of the BOT is: " + characterSymbol);

            //TODO - take user input for bot difficulty level and create the object accordingly
            BotDifficultyLevel difficultyLevel = BotDifficultyLevel.EASY;
            Bot bot = new Bot(botName,
                    new Symbol(characterSymbol.charAt(0)),
                    difficultyLevel,
                    BotPlayingStrategyFactory.getBotPlayingStrategyDifficultyLevel(BotDifficultyLevel.EASY));

            players.add(bot);
        }
        //randomise the players in the list
        Collections.shuffle(players);

        Game game = gameController.createGame(dimension, players);
        int playerIndex = 0;

        while (game.getGameState().equals(GameState.IN_PROGRESS)){
            System.out.println("Current Board Status");
            gameController.displayBoard(game);
            playerIndex++;
            playerIndex = playerIndex%players.size();
            Move movePlayed = gameController.executeMove(game, players.get(playerIndex));
            Player winner = gameController.checkWinner(game, movePlayed);
            if(winner!=null){
                gameController.displayBoard(game);
                System.out.println(winner.getName() + " WINS!!!");
                for (Move move : game.getMoves()){
                    System.out.println(move.getPlayer().getName() + " Row: "+ move.getCell().getRow() + " Col: "+ move.getCell().getCol());
                }
                break;
            }


            //TODO - can add the logic for UNDO move here


            //TODO - write logic for giving each player option to play
        }

    }
}
