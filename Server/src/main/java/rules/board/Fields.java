package rules.board;

import rules.Cube;
import rules.Messages;
import rules.board.tiles.desert.DesertTile;
import communication.observer.FieldsObserver;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Fields {
    private final List<Field> gameBoard;
    private final Field finishField;
    private DesertTile returnedDesertTile;
    private final FieldsObserver fieldsObserver;

    public Fields(List<Field> gameBoard, FieldsObserver fieldsObserver) {
        this.gameBoard = new LinkedList<>(gameBoard);
        finishField = new Field();
        this.gameBoard.add(finishField);
        returnedDesertTile = null;
        this.fieldsObserver = fieldsObserver;
    }

    public int searchFieldOnThePawnIsStandingUsingCube(Cube cube) {
        for (int i = 0; i <= numberOfFields(); i++) {
            if (getFieldInPosition(i).isPawnInThisField(cube))
                return i;
        }
        return -1;
    }

    public List<Pawn> getPawnsInOrder() {
        List<Pawn> pawns = new LinkedList<>();
        for (int i = numberOfFields(); i >= 0; i--) {
            Field field = getFieldInPosition(i);
            pawns.addAll(field.getPawns());
        }
        return pawns;
    }

    public boolean isFinished() {
        return finishField.getNumberOfPawns() > 0;
    }

    private Field getFieldInPosition(int position) {
        return position >= gameBoard.size() ? gameBoard.get(gameBoard.size() - 1) : gameBoard.get(position);
    }

    public int numberOfFields() {
        return gameBoard.size() - 1;
    }

    public List<Pawn> getPawnsToMoveFromField(int fieldPosition, Cube cube) {
        if (fieldPosition < 0) return Arrays.asList(new Pawn(cube.getColor()));
        Field field = gameBoard.get(fieldPosition);

        int position = field.getPawnPositionInStack(cube);
        return field.getFirstNPawnsFromStack(position + 1);
    }

    public DesertTile getReturnedDesertTile() {
        DesertTile desertTileToReturn = returnedDesertTile;
        returnedDesertTile = null;
        return desertTileToReturn;
    }

    public void makePawnMoveToDestinationField(int position, List<Pawn> pawns) {
        Field field = getFieldInPosition(position);
        int additionalNumbersOfMoves = field.getBonusFromDesertTile();
        returnedDesertTile = field.getDesertTileAndClearIt();

        if(returnedDesertTile != null)
            returnedDesertTile.notifyDesertTileObserver(position, Messages.GET_DESERT_TILE, "");

        position += additionalNumbersOfMoves;
        field = getFieldInPosition(position);
        field.addPawns(pawns, additionalNumbersOfMoves);

        notifyFieldObserver(position);
    }

    public void putDesertTileToField(DesertTile desertTile, int numberOfField) {
        Field field = getFieldInPosition(numberOfField);
        field.putDesertTile(desertTile);
    }

    public List<String> getFieldsWhereNoPutDesertTile() {
        List<String> result = new LinkedList<>();
        result.add("0");
        int k = getNumberFirstEmptyFieldAfterFirstPawns();
        for (int i = 1; i < k; i++) {
            result.add(i + "");
        }
        for (int i = k; i < numberOfFields(); i++) {
            if (gameBoard.get(i - 1).containsDesertTile()
                    || gameBoard.get(i).containsDesertTile()
                    || gameBoard.get(i + 1).containsDesertTile()
                    || gameBoard.get(i).getNumberOfPawns() > 0) {
                result.add("" + i);
            }
        }
        return result;
    }

    private int getNumberFirstEmptyFieldAfterFirstPawns() {
        boolean isPawn = false;
        for (int i = 0; i < numberOfFields(); i++) {
            if(gameBoard.get(i).getNumberOfPawns() > 0)
                isPawn = true;
            if(gameBoard.get(i).getNumberOfPawns() == 0 && isPawn)
                return i;
        }
        return 1;
    }

    public void notifyFieldObserver(int fieldNumber) {
        if(fieldNumber >= 0) fieldsObserver.createInfoForWeb(getFieldInPosition(fieldNumber), fieldNumber);
    }
}
