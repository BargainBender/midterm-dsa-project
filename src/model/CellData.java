package model;

public class CellData {
	private int treeCount;
	private int status;
	
	public CellData() {
		this.treeCount = 0;
		this.setStatus(CellDataStatus.UNSET);
	}
	
	public int getTreeCount() {
		return treeCount;
	}

	public void setTreeCount(int treeCount) {
		this.treeCount = treeCount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public final class CellDataStatus {
		public static final int UNSET = 0;
		public static final int SET = 1;
		public static final int NON_PROPERTY = 2;
	}
}
