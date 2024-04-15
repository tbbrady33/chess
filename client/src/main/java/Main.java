import chess.*;
import ui.UserInterfaceSwitch;


public class Main {
    public static void main(String[] args) {
        var piece = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        System.out.println("♕ 240 Chess Client: " + piece);
        System.out.println();
        System.out.println("Welcome type, \"Help\" to see the options");
        String[][] board = new String[8][8];
        UserInterfaceSwitch inter = new UserInterfaceSwitch(false);
        inter.request();

    }

    }