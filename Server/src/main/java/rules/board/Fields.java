package rules.board;

import communication.observer.FieldsObserver;
import rules.Messages;
import rules.board.tiles.desert.DesertTile;
import rules.board.tiles.desert.DesertTilePage;
import rules.board.tiles.desert.UnclassifiedDesertTilePage;

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

    public List<Pawn> getPawnsInOrder() {
        List<Pawn> pawns = new LinkedList<>();
        for (int i = numberOfFields(); i >= 0; i--) {
            Field field = getFieldInPosition(i);
            pawns.addAll(field.getPawns());
        }
        return pawns;
    }

    public DesertTile getReturnedDesertTile() {
        DesertTile desertTileToReturn = returnedDesertTile;
        returnedDesertTile = null;
        return desertTileToReturn;
    }

    public boolean isFinished() {
        return finishField.getNumberOfPawns() > 0;
    }

    public void moveThePawns(int numberOfFieldsToMove, String pawnColor) {
        int fieldNumberWithPawnInColor = lookingForFieldNumberOnThePawnIsStanding(pawnColor);
        List<Pawn> pawns = getPawnsToMoveFromField(fieldNumberWithPawnInColor, pawnColor);
        notifyFieldObserver(fieldNumberWithPawnInColor);

        int nextFieldPosition = fieldNumberWithPawnInColor + numberOfFieldsToMove;
        makePawnMoveToDestinationField(nextFieldPosition, pawns);
    }

    public void putDesertTileToField(DesertTile desertTile, int numberOfField) {
        Field field = getFieldInPosition(numberOfField);
        field.putDesertTile(desertTile);
    }

    private int calculatePosition(int position) {
        return position >= gameBoard.size() ? gameBoard.size() - 1 : position;
    }

    private Field getFieldInPosition(int position) {
        return gameBoard.get(position);
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

    private List<Pawn> getPawnsToMoveFromField(int fieldPosition, String pawnColor) {
        if (fieldPosition == -1) return Arrays.asList(new Pawn(pawnColor));
        return getFieldInPosition(fieldPosition).getPawnsAbovePawnInColor(pawnColor);
    }

    private int lookingForFieldNumberOnThePawnIsStanding(String pawnColor) {
        for (int i = 0; i <= numberOfFields(); i++) {
            if (getFieldInPosition(i).isPawnInThisField(pawnColor))
                return i;
        }
        return -1;
    }

    private void makePawnMoveToDestinationField(int position, List<Pawn> pawns) {
        position = calculatePosition(position);
        Field field = getFieldInPosition(position);
        returnedDesertTile = field.getDesertTileAndClearIt();
        DesertTilePage desertTilePage = new UnclassifiedDesertTilePage();

        if(returnedDesertTile != null) {
            returnedDesertTile.notifyDesertTileObserver(position, Messages.GET_DESERT_TILE, "");
            position += returnedDesertTile.getBonusPoints();
            desertTilePage = returnedDesertTile.getDesertTilePage();
            returnedDesertTile.switchPageToNonActive();
            field = getFieldInPosition(position);
        }

        desertTilePage.addPawnsToField(field, pawns);

        notifyFieldObserver(position);
    }

    private void notifyFieldObserver(int fieldNumber) {
        if(fieldNumber >= 0)
            fieldsObserver.createInfoForWeb(getFieldInPosition(fieldNumber).getPawns(), fieldNumber);
    }

    private int numberOfFields() {
        return gameBoard.size() - 1;
    }
}
