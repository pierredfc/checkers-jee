package fr.dude.isen.model;

import fr.dude.isen.exceptions.UnauthorizedMoveException;
import fr.dude.isen.model.cells.Cell;
import fr.dude.isen.model.cells.Cells;
import fr.dude.isen.model.cells.ColorCell;
import fr.dude.isen.model.pawns.*;
import fr.dude.isen.model.serializable.SerializableBoard;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.io.Serializable;

/**
 * Class that represents a checkers board.
 */
public class Board implements SerializableBoard {

    /**
     * Represents the cells of the board.
     */
    private Cells cells;

    /**
     * Manager of the board for move purpose.
     */
    private BoardManager boardManager;

    /**
     * Whose turn is next! By default, white starts to play.
     */
    private ColorPawn nextPlayer = ColorPawn.WHITE;

    /**
     * Player who owns the white pawns.
     */
    private Player playerWhite;

    /**
     * Player who owns the black pawns.
     */
    private Player playerBlack;

    public Board(int nbRows, int nbColumns, int nbPawnRows) {
        this.initCells(nbRows, nbColumns);
        this.initUsers(nbPawnRows * nbColumns / 2);
        this.initPawns(nbPawnRows);
    }

    /**
     * Create the cells of the board.
     * @param nbRows
     * @param nbColumns
     */
    private void initCells(int nbRows, int nbColumns) {
        this.cells = new Cells(nbRows, nbColumns);
        this.boardManager = new BoardManager(cells);
    }

    /**
     * Create the two players.
     * @param nbPawns
     */
    private void initUsers(int nbPawns) {
        this.playerWhite = new Player("Default User 1", nbPawns, ColorPawn.WHITE, Direction.DOWN, 0);
        this.playerBlack = new Player("Default User 2", nbPawns, ColorPawn.BLACK, Direction.UP, this.cells.getLastRowIndex());
        this.playerWhite.setOpponent(playerBlack);
        this.playerBlack.setOpponent(playerWhite);
    }

    /**
     * Fill the cells of the board with player's pawns.
     * @param nbPawnRows The number of pawns by row
     */
    private void initPawns(int nbPawnRows) {
        this.initPawnsUserWhite(nbPawnRows);
        this.initPawnsUserBlack(nbPawnRows);
    }


    private void initPawnsUserWhite(int nbPawnRows) {
        for (int row = 0; row < nbPawnRows; row++) {
            fillColumnWithPawns(this.playerBlack, row);
        }
    }

    private void initPawnsUserBlack(int nbPawnRows) {
        for (int row = this.cells.getLastRowIndex(); row > this.cells.getLastRowIndex() - nbPawnRows; row--) {
            fillColumnWithPawns(this.playerWhite, row);
        }
    }

    /**
     * For the current row, fill the column with pawns according to the player's pattern.
     * @param player
     * @param row The row index to be filled
     */
    private void fillColumnWithPawns(Player player, int row) {
        for (int column = 0; column < this.cells.getNbColumns(); column++) {
            Cell cell = cells.get(row, column);
            if (cell.getColor() == ColorCell.DARK) {
                cell.setPawn(player.newPawn());
            }
        }
    }

    /**
     * Make a play with Positions.
     * @param origin Initial position of the pawn
     * @param destination Final position of the pawn
     * @return a MoveResult
     */
    public MoveResult play(Position origin, Position destination) {
        final Cell originCell = getCell(origin.getRow(), origin.getColumn());
        final Cell destinationCell = getCell(destination.getRow(), destination.getColumn());
        return play(originCell, destinationCell);

    }

    /**
     * Make a play with Cells.
     * @param origin Initial cell of the pawn
     * @param destination Final cell of the pawn
     * @return a MoveResult
     */
    public MoveResult play(Cell origin, Cell destination) {
        if (origin.hasPawn() && origin.getPawn().getColor() == this.nextPlayer) {
            Player player = origin.getPawn().getColor() == ColorPawn.BLACK ? this.getPlayerBlack() : this.getPlayerWhite();
            return this.movePawn(player, origin, destination);
        }
        return null;
    }

    /**
     * Move a player's pawn from an origin cell to a destination cell.
     * @param player The player who owns the pawn
     * @param origin Origin cell of the pawn
     * @param destination Destination cell of the pawn
     * @return a MoveResult
     */
    private MoveResult movePawn(Player player, Cell origin, Cell destination) {
        try {
            Move move = this.boardManager.move(origin, destination);
            checkPawnToDelete(move, player);
            boolean becomesQueen = checkTransformToQueen(move);
            this.nextPlayer = isPawnHasBeenDeleted(move) ? player.getColorPawn() : player.findOpponentColor();
            return new MoveResult(origin, move, nextPlayer, becomesQueen, this.playerWhite.getNbPawns(), this.playerBlack.getNbPawns(), this.isUserWins(player));
        } catch (UnauthorizedMoveException e) {
            return null;
        }
    }

    /**
     * @param player
     * @return true if the player has won. False otherwise.
     */
    private boolean isUserWins(Player player)
    {
        return (player.getOpponent().getNbPawns() == 0);
    }

    /**
     * @param move
     * @return true if a pawn has been deleted. False otherwise.
     */
    private boolean isPawnHasBeenDeleted(Move move) {
        return move.getPawnCellToDelete() != null;
    }

    /**
     * Remove a pawn if it has been deleted during a move.
     * @param move The pawn's move
     * @param player The player who make the move
     */
    private void checkPawnToDelete(Move move, Player player) {
        if (move.hasPawnToDelete()) {
            Cell pawnCellToDelete = move.getPawnCellToDelete();
            player.decrementOpponentPawns();
            pawnCellToDelete.setPawn(null);
        }
    }

    /**
     * Check and transform a pawn to a queen if conditions are fulfilled.
     * @param move The pawn's move
     * @return true if the pawn becomes a queen. False otherwise.
     */
    public boolean checkTransformToQueen(Move move) {
        Pawn pawn = move.getDestination().getPawn();
        int row = move.getDestination().getPosition().getRow();
        if ((pawn.getColor() == ColorPawn.BLACK && row == this.playerBlack.getQueenRow()) || (pawn.getColor() == ColorPawn.WHITE && row == this.playerWhite.getQueenRow())) {
            pawn.toQueen();
            return true;
        }
        return false;
    }

    public Cell getCell(int row, int col) {
        return this.cells.get(row, col);
    }

    public Cell getCell(Position position) {
        return getCell(position.getRow(), position.getColumn());
    }

    @JsonIgnore
    public BoardManager getBoardManager() {
        return boardManager;
    }

    /**
     * Serialized field
     * @return
     */
    @Override
    public Cells getCells() {
        return this.cells;
    }

    /**
     * Serialized field
     * @return
     */
    @Override
    public Player getPlayerWhite() {
        return this.playerWhite;
    }

    /**
     * Serialized field
     * @return
     */
    @Override
    public Player getPlayerBlack() {
        return this.playerBlack;
    }

    /**
     * Serialized field
     * @return Next player who has to move a pawn
     */
    @Override
    public ColorPawn getNextPlayer() {
        return nextPlayer;
    }
}
