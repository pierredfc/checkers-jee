package fr.dude.isen.model;

import fr.dude.isen.exceptions.UnauthorizedMoveException;
import fr.dude.isen.model.pawns.*;

import java.io.Serializable;

/**
 * Created by pierredfc on 09/01/2017.
 */
public class Board implements Serializable {

    private Cells cells;
    private BoardManager boardManager;

    private ColorPawn nextUser = ColorPawn.WHITE;

    private User userWhite;
    private User userBlack;

    public Board(int nbRows, int nbColumns, int nbPawnRows) {
        this.initCells(nbRows, nbColumns);
        this.initUsers(nbPawnRows * nbColumns / 2);
        this.initPawns(nbPawnRows);
    }

    private void initCells(int nbRows, int nbColumns) {
        this.cells = new Cells(nbRows, nbColumns);
        this.boardManager = new BoardManager(cells);
    }

    private void initUsers(int nbPawns) {
        this.userWhite = new User(User.USER_WHITE_DEFAULT_NAME, nbPawns, ColorPawn.WHITE, Direction.DOWN, 0);
        this.userBlack = new User(User.USER_BLACK_DEFAULT_NAME, nbPawns, ColorPawn.BLACK, Direction.UP, this.cells.getLastRowIndex());
        this.userWhite.setOpponent(userBlack);
        this.userBlack.setOpponent(userWhite);
    }

    private void initPawns(int nbPawnRows) {
        this.initPawnsUserWhite(nbPawnRows);
        this.initPawnsUserBlack(nbPawnRows);
    }

    private void initPawnsUserWhite(int nbPawnRows) {
        for (int row = 0; row < nbPawnRows; row++) {
            /*for (int column = row % 2 == 0 ? 1 : 0; column < nbColumns; column += 2) {
                this.cells.get(column, row).setPawn(this.userrBlack.newPawn());
            }*/
            fillColumnWithPawns(this.userBlack, row);
        }
    }

    private void initPawnsUserBlack(int nbPawnRows) {
        for (int row = this.cells.getLastRowIndex(); row > this.cells.getLastRowIndex() - nbPawnRows; row--) {
            /*for(int column = (row % 2 == 1 ? 0:1); column < nbColumns; column+=2) {
            }*/
            fillColumnWithPawns(this.userWhite, row);
        }
    }

    private void fillColumnWithPawns(User user, int row) {
        for (int column = 0; column < this.cells.getNbColumns(); column++) {
            Cell cell = cells.get(row, column);
            if (cell.getColor() == ColorCell.DARK) {
                cell.setPawn(user.newPawn());
            }
        }
    }

    public Cells getCells() {
        return this.cells;
    }

    public User getUserWhite() {
        return this.userWhite;
    }

    public User getUserBlack() {
        return this.userBlack;
    }

    public MoveResult play(Position origin, Position destination) {
        final Cell originCell = getCell(origin.getRow(), origin.getColumn());
        final Cell destinationCell = getCell(destination.getRow(), destination.getColumn());
        return play(originCell, destinationCell);

    }

    public MoveResult play(Cell origin, Cell destination) {
        if (origin.hasPawn() && origin.getPawn().getColor() == this.nextUser) {
            User user = origin.getPawn().getColor() == ColorPawn.BLACK ? this.getUserBlack() : this.getUserWhite();
            return this.movePawn(user, origin, destination);
        }
        return null;
    }

    private MoveResult movePawn(User user, Cell origin, Cell destination) {
        try {
            Move move = this.boardManager.move(origin, destination);
            checkPawnToDelete(move, user);
            boolean becomesQueen = checkTransformToQueen(move);
            this.nextUser = getNextUser(move) ? user.getColorPawn() : user.findOpponentColor();
            return new MoveResult(origin, move, nextUser, becomesQueen, this.userWhite.getNbPawns(), this.userBlack.getNbPawns(), this.isUserWins(user));
        } catch (UnauthorizedMoveException e) {
            return null;
        }
    }

    private boolean isUserWins(User user)
    {
        return (user.getOpponent().getNbPawns() == 0);
    }

    private boolean getNextUser(Move move) {
        return move.getPawnCellToDelete() != null;
    }

    private void checkPawnToDelete(Move move, User user) {
        if (move.hasPawnToDelete()) {
            Cell pawnCellToDelete = move.getPawnCellToDelete();
            user.decrementOpponentPawns();
            pawnCellToDelete.setPawn(null);
        }
    }

    public boolean checkTransformToQueen(Move move) {
        Pawn pawn = move.getDestination().getPawn();
        int row = move.getDestination().getPosition().getRow();
        if ((pawn.getColor() == ColorPawn.BLACK && row == this.userBlack.getQueenRow()) || (pawn.getColor() == ColorPawn.WHITE && row == this.userWhite.getQueenRow())) {
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

    public BoardManager getBoardManager() {
        return boardManager;
    }

    public void setCells(Cells cells) {
        this.cells = cells;
    }

    public void setUserWhite(User userWhite) {
        this.userWhite = userWhite;
    }

    public void setUserBlack(User userBlack) {
        this.userBlack = userBlack;
    }

    public ColorPawn getNextUser() {
        return nextUser;
    }

    public void setNextUser(ColorPawn nextUser) {
        this.nextUser = nextUser;
    }


}
