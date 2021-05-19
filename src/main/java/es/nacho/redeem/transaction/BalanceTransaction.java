package es.nacho.redeem.transaction;

import java.util.Collection;

import es.nacho.redeem.exception.InsufficientBalanceException;
import es.nacho.redeem.exception.UserNotFoundException;

public interface BalanceTransaction {

    void userToUserBalanceTransaction(Boolean isAllocation, long activeUserId, String receiverIdentifier, long amount) throws UserNotFoundException, InsufficientBalanceException;
    void userToUsersBalanceTransaction(long adminId, Collection<Long> employeesIds, long amount) throws UserNotFoundException, InsufficientBalanceException;
}
