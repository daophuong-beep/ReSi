package subsystem;

import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import entity.transaction.CreditCard;
import entity.transaction.Transaction;

/**
 * The {@code InterbankInterface} class is used to communicate with the
 * {@link subsystem.InterbankSubsystem InterbankSubsystem} to make transaction
 * 
 * @author NhungTTH
 * 
 */
public interface InterbankInterface {

	/**
	 * Process transaction, and then return the transaction
	 * 
	 * @param card     - the credit card used for payment
	 * @param amount   - the amount to pay
	 * @param contents - the transaction contents
	 * @return {@link entity.transaction.Transaction Transaction} - if the
	 *         transaction is successful
	 * @throws PaymentException      if responded with a pre-defined error code
	 * @throws UnrecognizedException if responded with an unknown error code or
	 *                               something goes wrong
	 */
	public abstract Transaction processTransaction(CreditCard card, int amount, String contents, String type)
			throws PaymentException, UnrecognizedException;

}
