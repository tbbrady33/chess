package ui;

import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessPosition;

import java.io.PrintStream;
import java.util.ArrayList;

import static ui.EscapeSequences.*;

public class makeBoard {
    private String[][] board = new String[8][8];

    private static final int Board_Size = 8;
    private static final int Square_Size = 3;
    private static ChessGame.TeamColor team;
    private static final String Black_King = EscapeSequences.SET_TEXT_COLOR_BLACK + "K";
    public makeBoard(ChessPiece[][] board, ChessGame.TeamColor team){

        if (team == ChessGame.TeamColor.BLACK){
            for(int j = 0; j < board.length; j++){
                for(int i = 0; i < board.length / 2; i++) {
                    ChessPiece temp = board[j][i];
                    board[j][i] = board[j][board.length - i -1];
                    board[j][board.length - i - 1] = temp;
                }
            }
            this.board = new String[8][8];
            for(int j = 0; j < board.length; j++){
                for(int i = 0; i < board.length; i++){
                    if(board[j][i] == null){
                        this.board[j][i] = " ";
                    } else if (board[j][i].getPieceType().equals(ChessPiece.PieceType.PAWN) && board[j][i].getTeamColor().equals(ChessGame.TeamColor.BLACK)) {
                        this.board[j][i] = "P Black";
                    } else if (board[j][i].getPieceType().equals(ChessPiece.PieceType.PAWN) && board[j][i].getTeamColor().equals(ChessGame.TeamColor.WHITE)) {
                        this.board[j][i] = "P White";
                    } else if (board[j][i].getPieceType().equals(ChessPiece.PieceType.ROOK) && board[j][i].getTeamColor().equals(ChessGame.TeamColor.BLACK)) {
                        this.board[j][i] = "R Black";
                    } else if (board[j][i].getPieceType().equals(ChessPiece.PieceType.ROOK) && board[j][i].getTeamColor().equals(ChessGame.TeamColor.WHITE)) {
                        this.board[j][i] = "R White";
                    } else if (board[j][i].getPieceType().equals(ChessPiece.PieceType.KNIGHT) && board[j][i].getTeamColor().equals(ChessGame.TeamColor.WHITE)) {
                        this.board[j][i] = "N White";
                    } else if (board[j][i].getPieceType().equals(ChessPiece.PieceType.KNIGHT) && board[j][i].getTeamColor().equals(ChessGame.TeamColor.BLACK)) {
                        this.board[j][i] = "N Black";
                    } else if (board[j][i].getPieceType().equals(ChessPiece.PieceType.BISHOP) && board[j][i].getTeamColor().equals(ChessGame.TeamColor.BLACK)) {
                        this.board[j][i] = "B Black";
                    } else if (board[j][i].getPieceType().equals(ChessPiece.PieceType.BISHOP) && board[j][i].getTeamColor().equals(ChessGame.TeamColor.WHITE)) {
                        this.board[j][i] = "B White";
                    } else if (board[j][i].getPieceType().equals(ChessPiece.PieceType.QUEEN) && board[j][i].getTeamColor().equals(ChessGame.TeamColor.WHITE)) {
                        this.board[j][i] = "Q White";
                    }else if (board[j][i].getPieceType().equals(ChessPiece.PieceType.QUEEN) && board[j][i].getTeamColor().equals(ChessGame.TeamColor.BLACK)) {
                        this.board[j][i] = "Q Black";
                    }else if (board[j][i].getPieceType().equals(ChessPiece.PieceType.KING) && board[j][i].getTeamColor().equals(ChessGame.TeamColor.BLACK)) {
                        this.board[j][i] = "K Black";
                    }else if (board[j][i].getPieceType().equals(ChessPiece.PieceType.KING) && board[j][i].getTeamColor().equals(ChessGame.TeamColor.WHITE)) {
                        this.board[j][i] = "K White";
                    }
                }
            }
        } else if (team == ChessGame.TeamColor.WHITE) {

            this.board = new String[8][8];
            for(int j = 0; j < board.length; j++){
                for(int i = 0; i < board.length; i++){
                    if(board[j][i] == null){
                        this.board[j][i] = " ";
                    } else if (board[j][i].getPieceType().equals(ChessPiece.PieceType.PAWN) && board[j][i].getTeamColor().equals(ChessGame.TeamColor.BLACK)) {
                        this.board[j][i] = "P Black";
                    } else if (board[j][i].getPieceType().equals(ChessPiece.PieceType.PAWN) && board[j][i].getTeamColor().equals(ChessGame.TeamColor.WHITE)) {
                        this.board[j][i] = "P White";
                    } else if (board[j][i].getPieceType().equals(ChessPiece.PieceType.ROOK) && board[j][i].getTeamColor().equals(ChessGame.TeamColor.BLACK)) {
                        this.board[j][i] = "R Black";
                    } else if (board[j][i].getPieceType().equals(ChessPiece.PieceType.ROOK) && board[j][i].getTeamColor().equals(ChessGame.TeamColor.WHITE)) {
                        this.board[j][i] = "R White";
                    } else if (board[j][i].getPieceType().equals(ChessPiece.PieceType.KNIGHT) && board[j][i].getTeamColor().equals(ChessGame.TeamColor.WHITE)) {
                        this.board[j][i] = "N White";
                    } else if (board[j][i].getPieceType().equals(ChessPiece.PieceType.KNIGHT) && board[j][i].getTeamColor().equals(ChessGame.TeamColor.BLACK)) {
                        this.board[j][i] = "N Black";
                    } else if (board[j][i].getPieceType().equals(ChessPiece.PieceType.BISHOP) && board[j][i].getTeamColor().equals(ChessGame.TeamColor.BLACK)) {
                        this.board[j][i] = "B Black";
                    } else if (board[j][i].getPieceType().equals(ChessPiece.PieceType.BISHOP) && board[j][i].getTeamColor().equals(ChessGame.TeamColor.WHITE)) {
                        this.board[j][i] = "B White";
                    } else if (board[j][i].getPieceType().equals(ChessPiece.PieceType.QUEEN) && board[j][i].getTeamColor().equals(ChessGame.TeamColor.WHITE)) {
                        this.board[j][i] = "Q White";
                    }else if (board[j][i].getPieceType().equals(ChessPiece.PieceType.QUEEN) && board[j][i].getTeamColor().equals(ChessGame.TeamColor.BLACK)) {
                        this.board[j][i] = "Q Black";
                    }else if (board[j][i].getPieceType().equals(ChessPiece.PieceType.KING) && board[j][i].getTeamColor().equals(ChessGame.TeamColor.BLACK)) {
                        this.board[j][i] = "K Black";
                    }else if (board[j][i].getPieceType().equals(ChessPiece.PieceType.KING) && board[j][i].getTeamColor().equals(ChessGame.TeamColor.WHITE)) {
                        this.board[j][i] = "K White";
                    }
                }
            }
        }
        this.team = team;
    }


    public void MakeHeader(PrintStream out){
        setGrey(out);

        String[] headers = new String[9];
        if (team == ChessGame.TeamColor.BLACK) {
            headers[0] = " ";
            headers[1] = "H";
            headers[2] = "G";
            headers[3] = "F";
            headers[4] = "E";
            headers[5] = "D";
            headers[6] = "C";
            headers[7] = "B";
            headers[8] = "A";
        } else if (team == ChessGame.TeamColor.WHITE) {
            headers[0] = " ";
            headers[1] = "A";
            headers[2] = "B";
            headers[3] = "C";
            headers[4] = "D";
            headers[5] = "E";
            headers[6] = "F";
            headers[7] = "G";
            headers[8] = "H";
        }
        for(int boardCol = 0; boardCol < Board_Size +1; boardCol++){
            int prefixLength = 2;
            int suffixLength = 2;

            out.print("  ");
            out.print(SET_BG_COLOR_DARK_GREY);
            out.print(SET_TEXT_ITALIC);
            out.print(SET_TEXT_BOLD);
            out.print(SET_TEXT_COLOR_WHITE);
            out.print(headers[boardCol]);
            setGrey(out);
            out.print("  ");

        }
        out.println();

    }

    public void drawBoardHighlight(PrintStream out, ArrayList<ChessMove> moves){
        for(int boardRow = 0; boardRow < Board_Size; boardRow++){
            int prefixLength = Square_Size / 2;
            int suffixLength = Square_Size - prefixLength - 1;

            out.print(SET_BG_COLOR_DARK_GREY);
            out.print(SET_TEXT_ITALIC);
            out.print(SET_TEXT_BOLD);
            out.print(SET_TEXT_COLOR_WHITE);
            out.print("  ");
            if (team == ChessGame.TeamColor.WHITE) {
                out.print(8 -boardRow);
            } else if (team == ChessGame.TeamColor.BLACK) {
                out.print(boardRow + 1);
            }
            out.print("  ");
            if(team == ChessGame.TeamColor.WHITE){
                drawRowHighlight(out,7 -boardRow,moves);
            } else if (team == ChessGame.TeamColor.BLACK) {
                drawRowHighlight(out,boardRow,moves);
            }

            out.println();
            out.print(SET_BG_COLOR_LIGHT_GREY);
        }
    }

    public void drawRowHighlight(PrintStream out, int row, ArrayList<ChessMove> moves){
        for(int boardRow = 0; boardRow < Board_Size; boardRow++){
            int prefixLength = Square_Size / 2;
            int suffixLength = Square_Size - prefixLength - 1;

            ChessPosition pos = new ChessPosition(row,boardRow);
            if (moves.contains(pos)){
                out.print(SET_BG_COLOR_MAGENTA);
            }
            else {
                out.print(SET_BG_COLOR_DARK_GREY);
            }
            out.print(SET_TEXT_ITALIC);
            out.print(SET_TEXT_BOLD);
            out.print(SET_TEXT_COLOR_WHITE);
            out.print("  ");
            if (team == ChessGame.TeamColor.WHITE) {
                out.print(8 -boardRow);
            } else if (team == ChessGame.TeamColor.BLACK) {
                out.print(boardRow + 1);
            }
            out.print("  ");
            if(team == ChessGame.TeamColor.WHITE){
                drawRow(out,7 -boardRow);
            } else if (team == ChessGame.TeamColor.BLACK) {
                drawRow(out,boardRow);
            }

            out.println();
            out.print(SET_BG_COLOR_LIGHT_GREY);
        }
    }
    public void drawBoard(PrintStream out){
        for(int boardRow = 0; boardRow < Board_Size; boardRow++){
            int prefixLength = Square_Size / 2;
            int suffixLength = Square_Size - prefixLength - 1;

            out.print(SET_BG_COLOR_DARK_GREY);
            out.print(SET_TEXT_ITALIC);
            out.print(SET_TEXT_BOLD);
            out.print(SET_TEXT_COLOR_WHITE);
            out.print("  ");
            if (team == ChessGame.TeamColor.WHITE) {
                out.print(8 -boardRow);
            } else if (team == ChessGame.TeamColor.BLACK) {
                out.print(boardRow + 1);
            }
            out.print("  ");
            if(team == ChessGame.TeamColor.WHITE){
                drawRow(out,7 -boardRow);
            } else if (team == ChessGame.TeamColor.BLACK) {
                drawRow(out,boardRow);
            }

            out.println();
            out.print(SET_BG_COLOR_LIGHT_GREY);
        }
    }

    private void drawRow(PrintStream out, int row){
        int count = 0;
        for (int boardCol = 0; boardCol < Board_Size/2; boardCol++){
            if(team == ChessGame.TeamColor.BLACK) {
                if (row % 2 != 0) {
                    printBlackSquare(out, row, count);
                    printWhiteSquare(out, row, count + 1);
                } else {
                    printWhiteSquare(out, row, count);
                    printBlackSquare(out, row, count + 1);
                }
                count += 2;
            } else if (team == ChessGame.TeamColor.WHITE) {
                if (row % 2 == 0) {
                    printBlackSquare(out, row, count);
                    printWhiteSquare(out, row, count + 1);
                } else {
                    printWhiteSquare(out, row, count);
                    printBlackSquare(out, row, count + 1);
                }
                count += 2;
                
            }
        }
        out.print(SET_BG_COLOR_BLACK);
    }

    private void printBlackSquare(PrintStream out,int row, int col){
        out.print(SET_BG_COLOR_BROWN);
        out.print("  ");
        if(board[row][col].equals(" ")) {
            out.print(board[row][col]);
        } else if (board[row][col].indexOf("Black") != -1) {
            out.print(SET_TEXT_COLOR_BLACK);
            out.print(board[row][col].charAt(0));
        }else if(board[row][col].indexOf("White") != -1){
            out.print(SET_TEXT_COLOR_RED);
            out.print(board[row][col].charAt(0));
        }
        out.print("  ");
    }

    private void printWhiteSquare(PrintStream out, int row, int col){
        out.print(SET_BG_COLOR_WHITE);
        out.print("  ");
        if(board[row][col].equals(" ")) {
            out.print(board[row][col]);
        } else if (board[row][col].indexOf("Black") != -1) {
            out.print(SET_TEXT_COLOR_BLACK);
            out.print(board[row][col].charAt(0));
        }else if(board[row][col].indexOf("White") != -1){
            out.print(SET_TEXT_COLOR_RED);
            out.print(board[row][col].charAt(0));
        }
        out.print("  ");
    }

    private static void setGrey(PrintStream out) {
        out.print(SET_BG_COLOR_DARK_GREY);
        out.print(SET_TEXT_COLOR_DARK_GREY);
    }


}
