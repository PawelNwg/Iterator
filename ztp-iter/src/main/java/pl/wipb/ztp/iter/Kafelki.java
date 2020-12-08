package pl.wipb.ztp.iter;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

//macierz kafelków
class Kafelki extends JPanel {

	private Tile[][] matrix;
	private int tilesize;
	// kafelek podświetlony (myszką)
	private int hx = -1, hy = -1;

	// inicjalizacja macierzy
	public Kafelki(int cols, int rows, int tilesize) {
		this.setPreferredSize(new Dimension(cols * tilesize, rows * tilesize));
		this.tilesize = tilesize;
		matrix = new Tile[rows][cols];
		for (int i = 0; i < matrix.length; ++i) {
			for (int j = 0; j < matrix[i].length; ++j) {
				matrix[i][j] = new Tile();
			}
		}
	}

	// rysowanie macierzy (oraz jednego podświetlonego)
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < matrix.length; ++i) {
			for (int j = 0; j < matrix[i].length; ++j) {
				if (i == hy && j == hx) {
					g.setColor(matrix[i][j].getColor().brighter());
				} else {
					g.setColor(matrix[i][j].getColor());
				}
				g.fillRect(j * tilesize, i * tilesize + 1, tilesize - 1, tilesize - 1);
			}
		}
	}

	// podświetl
	public void highlight(int x, int y) {
		hx = x;
		hy = y;
		repaint();
	}

	private class HorizontalIterator implements pl.wipb.ztp.iter.Iterator<Tile>
	{

		private int x, y;

		public HorizontalIterator(int x, int y)
		{
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean hasNext()
		{
			if (x == matrix[0].length && y == matrix.length - 1) // jezeli kolejne pole
				return false;
			return true;
		}

		@Override
		public Tile next()
		{
			if (matrix[0].length == x)
			{
				x = 0;
				return matrix[++y][x++];
			}
			return matrix[y][x++];
		}
	}

	private class VerticalIterator implements pl.wipb.ztp.iter.Iterator<Tile>
	{

		private int x, y;

		public VerticalIterator(int x, int y)
		{
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean hasNext()
		{
			if (x == matrix[0].length - 1 && y == matrix.length)
				return false;
			return true;
		}

		@Override
		public Tile next()
		{
			if (y == matrix.length) // kolejny wiersz
			{
				y = 0;
				return matrix[y++][++x];
			}
			return matrix[y++][x];
		}
	}

	private class LeftHorizontalIterator implements pl.wipb.ztp.iter.Iterator<Tile>
	{

		private int x, y;

		public LeftHorizontalIterator(int x, int y)
		{
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean hasNext()
		{
			if (x == 0 && y == matrix.length - 1)
				return false;
			return true;
		}

		@Override
		public Tile next()
		{
			if (x == 0) // kolejny wiersz
			{
				x = matrix[0].length - 1;
				return matrix[++y][x];
			}
			return matrix[y][--x];
		}
	}

	private class LeftVerticalIterator implements pl.wipb.ztp.iter.Iterator<Tile>
	{

		private int x, y;

		public LeftVerticalIterator(int x, int y)
		{
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean hasNext()
		{
			if (x == 0 && y == 0)
				return false;
			return true;
		}

		@Override
		public Tile next()
		{
			if (y == 0) // kolejny wiersz
			{
				y = matrix.length - 1;
				return matrix[y][--x];
			}
			return matrix[--y][x];
		}
	}
	// za to pojawi się metoda pobierająca iterator
	// public Iterator<Tile> iterator( ...

	public pl.wipb.ztp.iter.Iterator<Tile> getHorizontalIterator(int x, int y)
	{

		return new HorizontalIterator(x, y);
	}
	public pl.wipb.ztp.iter.Iterator<Tile> getVerticalIterator(int x, int y)
	{
		return new VerticalIterator(x, y);
	}
	public pl.wipb.ztp.iter.Iterator<Tile> getLeftHorizontalIterator(int x, int y)
	{
		return new LeftHorizontalIterator(x, y);
	}
	public pl.wipb.ztp.iter.Iterator<Tile> getLeftVerticalIterator(int x, int y)
	{
		return new LeftVerticalIterator(x, y);
	}

}