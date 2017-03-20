package system.base.tree.vo;

public enum IdPidEnum {
	SUCCESS(0), ERROR_FatherIsYourSelft(1), ERROR_FatherIsYourSon(2);
	public final int key;

	private IdPidEnum(int key) {
		this.key = key;
	}
}
