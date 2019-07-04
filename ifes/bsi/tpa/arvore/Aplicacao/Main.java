/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.bsi.tpa.arvore.Aplicacao;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Main {
    
    public static final Random RANDOM = new Random();
     public static void main(String[] args) {
         System.out.println("Jogo Iniciado");
         
         Board board = new Board();
         Scanner sc = new Scanner(System.in);
         board.display();
         
         System.out.println("Quem joga prmeiro, a (1) maquina ou (2) você?"); 
         
         int escolha = sc.nextInt();
         
         if(board.PLAYER_X == escolha){
             Point point = new Point(RANDOM.nextInt(3), RANDOM.nextInt(3));
             board.move(point, Board.PLAYER_X);
             board.display();
         }
         
         while(!board.isGameOver()){
             boolean k = true;
             
             do{
                 if(!k){
                     System.out.println("Celula ja preenchida");
                     board.display();
                 }
                     
                 System.out.println("Seu movimento (Ex: 0 1): ");
                 Point uPoint = new Point(sc.nextInt(), sc.nextInt());
                 k = board.move(uPoint, Board.PLAYER_O);
             }while(!k);
             
             board.display();
             if(board.isGameOver())
                 break;
             
             board.miniMax(0, Board.PLAYER_X);
             board.move(board.computerMove, Board.PLAYER_X);
             board.display();
         }
         if(board.hasWon(Board.PLAYER_X))
            System.out.println("Perdeu campeao !");
         else if(board.hasWon(Board.PLAYER_O))
             System.out.println("Parabens campeao, voce ganhou !");
         else
             System.out.println("Mil-- Digo.. Velha !");
    }
}
