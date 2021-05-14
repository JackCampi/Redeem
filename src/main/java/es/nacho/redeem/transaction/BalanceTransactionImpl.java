package es.nacho.redeem.transaction;

import es.nacho.redeem.exception.InsufficientBalanceException;
import es.nacho.redeem.exception.UserNotFoundException;
import es.nacho.redeem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BalanceTransactionImpl implements BalanceTransaction{

    @Autowired
    private UserService userService;

    @Override
    @Transactional(rollbackOn = {UserNotFoundException.class, InsufficientBalanceException.class})
    public void userToUserBalanceTransaction(long activeUserId, String receiverIdentifier, long amoung) throws UserNotFoundException, InsufficientBalanceException {

        userService.discountToUserBalance(activeUserId, amoung);

        if(receiverIdentifier.contains("@")) userService.incrementToUserBalanceByEmail(receiverIdentifier, amoung);
        else userService.incrementToUserBalanceById(Long.parseLong(receiverIdentifier), amoung);

    }
}
