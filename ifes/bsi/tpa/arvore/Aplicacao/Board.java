/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.bsi.tpa.arvore.Aplicacao;

import java.util.LinkedList;

/**
 *
 * @author User
 */
public class Board {
    //Definindo constantes
    public static final int NO_PLAYER = 0;
    public static final int PLAYER_X = 1;
    public static final int PLAYER_O = 2;
    
    public int[][] board = new int[3][3];
    public Point computerMove;
    
    public boolean isGameOver(){
        return this.hasWon(PLAYER_X) || this.hasWon(PLAYER_O) || this.getAvaliableCells().isEmpty(); // Empate
    }
    
    public boolean hasWon(int player){
        //Checando se as diagonais
        
        if(this.board[0][0] == this.board[1][1] && this.board[0][0] == this.board[2][2] && this.board[0][0] == player) return true;
        if(this.board[2][0] == this.board[1][1] && this.board[2][0] == this.board[0][2] && this.board[2][0] == player) return true;
        
        //Checando as linhas e colunas
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(this.board[i][0] == this.board[i][1] && this.board[i][0] == this.board[i][2] && this.board[i][0] == player) return true;
                if(this.board[0][i] == this.board[1][i] && this.board[0][i] == this.board[2][i] && this.board[0][i] == player) return true;
            }
        }
        return false;
    }
    
    public LinkedList<Point> getAvaliableCells(){
        LinkedList<Point> avaliableCells = new LinkedList<>();
        
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(this.board[i][j] == NO_PLAYER)
                    avaliableCells.add(new Point(i,j));
            }
        }
        return avaliableCells;
    }
    
    public boolean move(Point point, int player){
        if(this.board[point.x][point.y] != NO_PLAYER)
            return false;
        this.board[point.x][point.y] = player;
        return true;
    }
    public int miniMax(int depth, int turn){
        if(this.hasWon(PLAYER_X)) return 1;
        if(this.hasWon(PLAYER_O)) return -1;
        
        LinkedList<Point> avaliableCells = this.getAvaliableCells();
        if(avaliableCells.isEmpty()) return 0; //Empate
        
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        
        for(int i=0; i < avaliableCells.size(); i++){
            Point point = avaliableCells.get(i);
            if(turn == PLAYER_X){
                this.move(point, PLAYER_X);
                int scoreAtual = this.miniMax(depth + 1, PLAYER_O);
                max = Math.max(scoreAtual, max);
                
               //if(depth == 0)
                //    System.out.println("Pontuacao para [ " + point.x + ", " + point.y + " ] = " + scoreAtual);
               
               if(scoreAtual >= 0)
                   if(depth == 0)
                       computerMove = point;
               
               if(scoreAtual == 1){
                   this.board[point.x][point.y] = NO_PLAYER;
                   break;
               }
               
               if(i == avaliableCells.size()-1 && max < 0)
                   if(depth == 0)
                       computerMove = point;
            }
            else if(turn == PLAYER_O){
                this.move(point, PLAYER_O);
                int scoreAtual = this.miniMax(depth + 1, PLAYER_X);
                min = Math.min(scoreAtual, min);
                
                if(min == -1){
                    this.board[point.x][point.y] = NO_PLAYER;
                    break;
                }
            }
            this.board[point.x][point.y] = NO_PLAYER;
        }
        
        if(turn == PLAYER_X) return max;
        else return min;
        //return turn == PLAYER_X ? max : min;
    }
    public void display(){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                String cell = "_";
                
                if(this.board[i][j] == PLAYER_X)
                    cell = "X";
                else if(this.board[i][j] == PLAYER_O)
                    cell = "O";
                System.out.print(" " + cell + " ");
            }
            System.out.println("");
        }
        System.out.println("------------");
    }
}
