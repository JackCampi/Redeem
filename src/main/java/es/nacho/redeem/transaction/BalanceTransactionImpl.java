package es.nacho.redeem.transaction;

import es.nacho.redeem.exception.InsufficientBalanceException;
import es.nacho.redeem.exception.UserNotFoundException;
import es.nacho.redeem.model.Employee;
import es.nacho.redeem.service.TransferService;
import es.nacho.redeem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BalanceTransactionImpl implements BalanceTransaction{

    @Autowired
    private UserService userService;

    @Autowired
    private TransferService transferService;

    @Override
    @Transactional(rollbackOn = {UserNotFoundException.class, InsufficientBalanceException.class})
    public void userToUserBalanceTransaction(long activeUserId, String receiverIdentifier, long amoung) throws UserNotFoundException, InsufficientBalanceException {

        Employee employeeFrom = userService.discountToUserBalance(activeUserId, amoung);

        Employee employeeTo;
        if(receiverIdentifier.contains("@")) employeeTo = userService.incrementToUserBalanceByEmail(receiverIdentifier, amoung);
        else employeeTo = userService.incrementToUserBalanceById(Long.parseLong(receiverIdentifier), amoung);

        transferService.saveTransfer(employeeFrom, employeeTo, amoung);

    }
}
