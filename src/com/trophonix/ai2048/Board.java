package com.trophonix.ai2048;

import java.util.Arrays;

import static com.trophonix.ai2048.Utils.randInt;

public class Board {

	public Tile[][] tiles;

	public Board() {
		tiles = new Tile[4][4];

		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				tiles[x][y] = new Tile(0);
			}
		}

		randTile();
		randTile();
	}

	public void up() {
		for (int y = 1; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				int mod = 0;
				while (true) {
					mod++;

					if (y - mod < 0)
						break;
					if (tiles[x][y - mod].value == 0) {
						tiles[x][y - mod].value = tiles[x][y - (mod - 1)].value;
						tiles[x][y - (mod - 1)].value = 0;
					} else if (tiles[x][y - mod].value == tiles[x][y - (mod - 1)].value
							&& tiles[x][y - mod].combined == false) {
						tiles[x][y - mod].value *= 2;
						tiles[x][y - (mod - 1)].value = 0;
						tiles[x][y - mod].combined = true;

						Main.ai.weights.put(Main.ai.up, Main.ai.weights.get(Main.ai.up) + 3);
						Main.ai.weights.put(Main.ai.down, Main.ai.weights.get(Main.ai.down) - 1);
						Main.ai.weights.put(Main.ai.left, Main.ai.weights.get(Main.ai.left) - 1);
						Main.ai.weights.put(Main.ai.right, Main.ai.weights.get(Main.ai.right) - 1);
					} else
						break;

				}
			}
		}

		randTile();
		checkWin();
		resetCombines();
	}

	public void down() {
		for (int y = 2; y > -1; y--) {
			for (int x = 0; x < 4; x++) {
				int mod = 0;
				while (true) {
					mod++;

					if (y + mod > 3)
						break;
					if (tiles[x][y + mod].value == 0) {
						tiles[x][y + mod].value = tiles[x][y + (mod - 1)].value;
						tiles[x][y + (mod - 1)].value = 0;
					} else if (tiles[x][y + mod].value == tiles[x][y + (mod - 1)].value
							&& tiles[x][y + mod].combined == false) {
						tiles[x][y + mod].value *= 2;
						tiles[x][y + (mod - 1)].value = 0;
						tiles[x][y + mod].combined = true;

						Main.ai.weights.put(Main.ai.up, Main.ai.weights.get(Main.ai.up) - 1);
						Main.ai.weights.put(Main.ai.down, Main.ai.weights.get(Main.ai.down) + 3);
						Main.ai.weights.put(Main.ai.left, Main.ai.weights.get(Main.ai.left) - 1);
						Main.ai.weights.put(Main.ai.right, Main.ai.weights.get(Main.ai.right) - 1);
					} else
						break;

				}
			}
		}

		randTile();
		checkWin();
		resetCombines();
	}

	public void right() {
		for (int x = 2; x > -1; x--) {
			for (int y = 0; y < 4; y++) {
				int mod = 0;
				while (true) {
					mod++;

					if (x + mod > 3)
						break;
					if (tiles[x + mod][y].value == 0) {
						tiles[x + mod][y].value = tiles[x + (mod - 1)][y].value;
						tiles[x + (mod - 1)][y].value = 0;
					} else if (tiles[x + mod][y].value == tiles[x + (mod - 1)][y].value
							&& tiles[x + mod][y].combined == false) {
						tiles[x + mod][y].value *= 2;
						tiles[x + (mod - 1)][y].value = 0;
						tiles[x + mod][y].combined = true;

						Main.ai.weights.put(Main.ai.up, Main.ai.weights.get(Main.ai.up) - 1);
						Main.ai.weights.put(Main.ai.down, Main.ai.weights.get(Main.ai.down) - 1);
						Main.ai.weights.put(Main.ai.left, Main.ai.weights.get(Main.ai.left) - 1);
						Main.ai.weights.put(Main.ai.right, Main.ai.weights.get(Main.ai.right) + 3);
					} else
						break;

				}
			}
		}

		randTile();
		checkWin();
		resetCombines();
	}

	public void left() {
		for (int x = 1; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				int mod = 0;
				while (true) {
					mod++;
					if (x - mod < 0)
						break;
					if (tiles[x - mod][y].value == 0) {
						tiles[x - mod][y].value = tiles[x - (mod - 1)][y].value;
						tiles[x - (mod - 1)][y].value = 0;
					} else if (tiles[x - mod][y].value == tiles[x - (mod - 1)][y].value
							&& tiles[x - mod][y].combined == false) {
						tiles[x - mod][y].value *= 2;
						tiles[x - (mod - 1)][y].value = 0;
						tiles[x - mod][y].combined = true;

						Main.ai.weights.put(Main.ai.up, Main.ai.weights.get(Main.ai.up) - 1);
						Main.ai.weights.put(Main.ai.down, Main.ai.weights.get(Main.ai.down) - 1);
						Main.ai.weights.put(Main.ai.left, Main.ai.weights.get(Main.ai.left) + 3);
						Main.ai.weights.put(Main.ai.right, Main.ai.weights.get(Main.ai.right) - 1);
					} else
						break;
				}
			}
		}

		randTile();
		checkWin();
		resetCombines();
	}

	public void resetCombines() {
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				tiles[x][y].combined = false;
			}
		}
	}

	public String[] getBoard() {
		String ln1 = "", ln2 = "", ln3 = "", ln4 = "";

		for (int i = 0; i < 4; i++) {
			ln1 = ln1 + "[" + tiles[i][0].value + "] ";
		}

		for (int i = 0; i < 4; i++) {
			ln2 = ln2 + "[" + tiles[i][1].value + "] ";
		}

		for (int i = 0; i < 4; i++) {
			ln3 = ln3 + "[" + tiles[i][2].value + "] ";
		}

		for (int i = 0; i < 4; i++) {
			ln4 = ln4 + "[" + tiles[i][3].value + "] ";
		}

		return (String[]) Arrays.asList(ln1, ln2, ln3, ln4).toArray();
	}

	public void randTile() {
		boolean found = false;
		Tile t = null;

		while (!found) {
			t = tiles[randInt(0, 3)][randInt(0, 3)];

			if (t.value == 0) {
				found = true;
			}
		}

		int rand = randInt(0, 4);
		if (rand <= 3)
			t.value = 2;
		else
			t.value = 4;

	}

	public void checkWin() {
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				if (tiles[x][y].value == 2048) {
					System.out.println("The AI won!");

					System.exit(0);
				}

			}
		}
	}

}
