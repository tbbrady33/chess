import chess.*;
import ui.EscapeSequences;
import ui.MakeBoard;
import ui.UserInterface;
import ui.UserInterfaceSwitch;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;


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

    public static void printBoard(String[][] board, ChessGame.TeamColor team){
        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        out.print(EscapeSequences.ERASE_SCREEN);

        ChessBoard Initial = new ChessBoard();
        Initial.resetBoard();

        MakeBoard chess = new MakeBoard(Initial.getChessarray(), team);
        chess.MakeHeader(out);
        chess.drawBoard(out);
    }

    private static String[][] intialBoard(String[][] chessarray){
        for (int i = 1; i <= 8; i++) {
            chessarray[2][i - 1] = " ";
        }
        for (int i = 1; i <= 8; i++) {
            chessarray[3][i - 1] = " ";
        }
        for (int i = 1; i <= 8; i++) {
            chessarray[4][i - 1] = " ";
        }
        for (int i = 1; i <= 8; i++) {
            chessarray[5][i - 1] = " ";
        }

        for (int i = 1; i <= 8; i++){
            chessarray[1][i-1] = "P White";
        }
        // Black pawns
        for (int i = 1; i <= 8; i++){
            chessarray[6][i-1] = "P Black";
        }
        // White Rooks
        chessarray[0][0] = "R White";
        chessarray[0][7] = "R White";

        // Black Rooks
        chessarray[7][0] = "R Black";
        chessarray[7][7] = "R Black";

        // White Knights
        chessarray[0][1] = "N White";
        chessarray[0][6] = "N White";

        // Black Knights
        chessarray[7][1] ="N Black";
        chessarray[7][6] = "N Black";

        //White Bishops
        chessarray[0][2] = "B White";
        chessarray[0][5] = "B White";

        //Black Bishops
        chessarray[7][2] = "B Black";
        chessarray[7][5] = "B Black";

        // White Queen
        chessarray[0][3] = "Q White";

        //Black Queen
        chessarray[7][3] = "Q Black";

        //White King
        chessarray[0][4] = "K White";

        // Black King
        chessarray[7][4] = "K Black";
        return chessarray;
    }
}