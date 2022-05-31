package pt.ulusofona.lp2.deisiGreatGame;

public class InvalidInitialBoardException extends Exception {
    private final String message;
    private final int idAbyssOrTool;

    public InvalidInitialBoardException(String message, int idAbyssOrTool) {
        this.message = message;
        this.idAbyssOrTool = idAbyssOrTool;
    }

    public InvalidInitialBoardException(String message) {
        this.message = message;
        this.idAbyssOrTool = -1;
    }

    public String getMessage(){
        return message;
    }

    public boolean isInvalidAbyss(){
        return idAbyssOrTool == 0;
    }

    public boolean isInvalidTool(){
        return idAbyssOrTool == 1;
    }

    public String getTypeId(){
        return "" + idAbyssOrTool;
    }
}
