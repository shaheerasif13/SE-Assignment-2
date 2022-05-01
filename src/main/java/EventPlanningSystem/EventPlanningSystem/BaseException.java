package EventPlanningSystem.EventPlanningSystem;

public abstract class BaseException extends Exception {

	private String message;

	public BaseException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}