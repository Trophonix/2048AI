package com.trophonix.ai2048;

import java.util.HashMap;
import java.util.Map;

import static com.trophonix.ai2048.Utils.randInt;

public class AI {

	public Neuron up, down, left, right;

	public Map<Neuron, Integer> weights;

	public AI() {
		up = new Neuron();
		down = new Neuron();
		left = new Neuron();
		right = new Neuron();

		weights = new HashMap<Neuron, Integer>();
		weights.put(up, 200);
		weights.put(down, 200);
		weights.put(left, 200);
		weights.put(right, 200);
	}

	public void move() {
		int rand = randInt(0, weights.get(up) + weights.get(down) + weights.get(left) + weights.get(right));

		if (rand <= weights.get(up)) {
			System.out.println("up");
			Main.board.up();
		} else if (rand > weights.get(up) && rand <= weights.get(up) + weights.get(down)) {
			System.out.println("down");
			Main.board.down();
		} else if (rand > weights.get(up) + weights.get(down)
				&& rand <= weights.get(up) + weights.get(down) + weights.get(left)) {
			System.out.println("left");
			Main.board.left();
		} else if (rand > weights.get(up) + weights.get(down) + weights.get(left) && rand <= weights.get(up) + weights.get(down) + weights.get(left) + weights.get(right)) {
			System.out.println("right");
			Main.board.right();
		}
	}

}
