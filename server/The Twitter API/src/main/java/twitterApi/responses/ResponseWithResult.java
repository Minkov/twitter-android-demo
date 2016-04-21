package twitterApi.responses;

public class ResponseWithResult<T> {
	private T result;

	public ResponseWithResult() {
		this(null);
	}

	public ResponseWithResult(T result) {
		this.setResult(result);
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
}
