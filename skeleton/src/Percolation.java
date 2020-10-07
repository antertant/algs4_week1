import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	public boolean site[][];	//grid data structure
	public int root[][], weight[][];	//root and weight data structure
	public int div;
	public int tmp_n;
	
	// create n-by-n grid, with all sites initially blocked
	public Percolation(int n) {
		this.tmp_n = n;
		this.site = new boolean[n][n];
		this.weight = new int[n][n];
		this.div = n / 10 + 1;
		for(int i = 1; i < n + 1; i++) {
			for(int j = 1; j < n + 1; j++) {
				this.root[i][j] = i * this.div + j;
			}
		}
	}
	
	//search the ancient root of the site
	public int aroot(int row, int col) {
		int i = row - 1;
		int j = col - 1;
		int tmp = this.root[i][j];
		
		do {
			tmp = this.root[tmp / this.div][tmp % this.div];
		} while(tmp != this.root[i][j]);
		
		return tmp;
	}
	
	//union site with its neighbors
	public void union(int row, int col) {
		int i = row - 1;
		int j = col - 1;
		int raroot = this.aroot(row, col);
		int aroot_1 = this.aroot(row+1, col);
		int aroot_2 = this.aroot(row, col+1);
		int aroot_3 = this.aroot(row, col-1);
		if(this.isOpen(row+1, col) && raroot != aroot_1) {
			if(this.weight[i][j] > this.weight[i+1][j]) {
				
			}
		}
	}
	
	//opens the site (row, col) if it is not open already
	public void open(int row, int col) {
		if(!this.site[row - 1][col - 1]) {
			this.site[row - 1][col - 1] = true;
			this.union(row, col);
		}
	}
	
	//is the site (row, col) open?
	public boolean isOpen(int row, int col) {
		if(row <= 0 || row > this.tmp_n || col <= 0 || col > this.tmp_n)
			return false;
		return this.site[row - 1][col - 1];
	}
	
	//is the site (row, col) full?
	public boolean isFull(int row, int col) {
		int tmp = this.aroot(row, col);	
		if(tmp / this.div == 1)
			return true;
		else
			return false;
	}
	
	//returns the number of open sites
	public int numberOfOpenSites() {
		int count = 0;
		for(int i = 0; i < this.tmp_n; i++) {
			for(int j = 0; j < this.tmp_n; j++) {
				if(this.site[i][j])
					count++;
			}
		}
		return count;
	}
	
	//does the system percolate?
	public boolean percolates() {
		for(int i = 0; i < this.tmp_n; i++) {
			if(this.root[this.tmp_n - 1][i] / this.div == 1)
				return true;
		}
		return false;
	}

	//test client
	public static void main(String[] args) {
		System.out.println("Hello World!");

	}

}
