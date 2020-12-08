package pl.wipb.ztp.iter;

import java.util.Random;

// ten wątek nie wykorzystuje iteratora
class Watek implements Runnable {

	private Kafelki p;
	private int x, y;
	private int choosenIterator = 0;
	Random rand = new Random();

	// x, y to początkowa pozycja do iteracji
	public Watek(Kafelki k, int x, int y)
	{
		this.p = k;
		this.x = x;
		this.y = y;
	}


	public void run() {

		choosenIterator = rand.nextInt(4);
		pl.wipb.ztp.iter.Iterator<Tile> choosenIteratorObject = null;
		// klasyczna podwójna pętla do iteracji
		// tutaj kontrolujemy kolejność odwiedzin
		// zostanie to zastąpione pętlą z użyciem iteratora
		switch(choosenIterator%4) {
			case 0:
				choosenIteratorObject = p.getHorizontalIterator(x, y);
				break;
			case 1:
				choosenIteratorObject = p.getVerticalIterator(x, y);
				break;
			case 2:
				choosenIteratorObject =  p.getLeftHorizontalIterator(x, y);
				break;
			case 3:
				choosenIteratorObject =  p.getLeftVerticalIterator(x, y);
		}

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