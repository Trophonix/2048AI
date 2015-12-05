package com.trophonix.ai2048;

public class Main {

	public static Board board;
	public static AI ai;
	
	public static void main(String[] args) throws InterruptedException {
		board = new Board();
		ai = new AI();
				
		boolean running = true;
		while (running) {
			
			ai.move();
			
			String[] b = board.getBoard();
			
			System.out.println("The board:\n");
			
			System.out.println(b[0]);
			System.out.println(b[1]);
			System.out.println(b[2]);
			System.out.println(b[3] + "\n");
			
			
		}
	}
	
}
