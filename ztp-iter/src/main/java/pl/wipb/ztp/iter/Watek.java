package pl.wipb.ztp.iter;

import java.util.Random;

// ten wątek nie wykorzystuje iteratora
class Watek implements Runnable {

	private Kafelki p;
	private int x, y;
	private int choosenIterator = 0;
	pl.wipb.ztp.iter.Iterator<Tile> choosenIteratorObject = null;

	// x, y to początkowa pozycja do iteracji
	public Watek(Kafelki k, int x, int y)
	{
		this.p = k;
		this.x = x;
		this.y = y;
		choosenIteratorObject = p.getIterator(x,y);

	}


	public void run() {

		while(choosenIteratorObject.hasNext())
		{
			Tile tile = choosenIteratorObject.next();
			tile.flip();
			p.repaint();
			try {
				Thread.currentThread().sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}


	}
}