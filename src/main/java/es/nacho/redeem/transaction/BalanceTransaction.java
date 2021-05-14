package es.nacho.redeem.transaction;

import es.nacho.redeem.exception.InsufficientBalanceException;
import es.nacho.redeem.exception.UserNotFoundException;

public interface BalanceTransaction {

    void userToUserBalanceTransaction(long activeUserId, String receiverIdentifier, long amoung) throws UserNotFoundException, InsufficientBalanceException;

}
