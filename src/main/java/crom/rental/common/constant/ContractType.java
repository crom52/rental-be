package crom.rental.common.constant;

public enum ContractType {
	TERMLY("1"), NONE_TERMLY("2"),;

	private final String type;

	ContractType(String type) {
		this.type = type;
	}

	public static ContractType fromString(String type) {
		for (ContractType e : values()) {
			if (e.type.equalsIgnoreCase(type)) {
				return e;
			}
		}
		return null;
	}

}
