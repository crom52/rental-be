package crom.rental.common.constant;

public enum TempResidenceStatus {
	NOT_VERIFIED("0"), VERIFIED("1"), WAITING_APPROVE("2"), PENDING("3");

	TempResidenceStatus(String status) {
		this.status = status;
	}

	private final String status;

	public static TempResidenceStatus fromString(String status) {
		for (TempResidenceStatus e : values()) {
			if (e.status.equalsIgnoreCase(status)) {
				return e;
			}
		}
		return null;
	}
}
