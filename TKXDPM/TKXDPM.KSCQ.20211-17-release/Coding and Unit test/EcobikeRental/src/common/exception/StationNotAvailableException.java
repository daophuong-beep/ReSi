package common.exception;;

/**
 * The StationNotAvailableException wraps all unchecked exceptions You can use this
 * exception to inform
 * 
 * @author nguyenlm
 */
public class StationNotAvailableException extends AimsException {

	private static final long serialVersionUID = 1091337136123906298L;

	public StationNotAvailableException() {

	}

	public StationNotAvailableException(String message) {
		super(message);
	}

}