package es.nacho.redeem.transaction;

import es.nacho.redeem.exception.InsufficientBalanceException;
import es.nacho.redeem.exception.UserNotFoundException;
import es.nacho.redeem.model.Employee;
import es.nacho.redeem.service.AllocationService;
import es.nacho.redeem.service.TransferService;
import es.nacho.redeem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

import javax.transaction.Transactional;

@Service
public class BalanceTransactionImpl implements BalanceTransaction{

    @Autowired
    private UserService userService;

    @Autowired
    private TransferService transferService;
    
    @Autowired
    private AllocationService allocationService;

    @Override
    @Transactional(rollbackOn = {UserNotFoundException.class, InsufficientBalanceException.class})
    public void userToUserBalanceTransaction(Boolean isAllocation,long activeUserId, String receiverIdentifier, long amount) throws UserNotFoundException, InsufficientBalanceException {

        Employee employeeFrom = userService.discountToUserBalance(activeUserId, amount);

        Employee employeeTo;
        if(receiverIdentifier.contains("@")) employeeTo = userService.incrementToUserBalanceByEmail(receiverIdentifier, amount);
        else employeeTo = userService.incrementToUserBalanceById(Long.parseLong(receiverIdentifier), amount);

        if(isAllocation){
            allocationService.saveAllocation(amount, "", employeeFrom, employeeTo);
        }
        else{
        transferService.saveTransfer(employeeFrom, employeeTo, amount);
        }

    }

    @Override
    @Transactional(rollbackOn = {UserNotFoundException.class, InsufficientBalanceException.class})
    public void userToUsersBalanceTransaction(long adminId, Collection<Long> employeesIds, long amount)
            throws UserNotFoundException, InsufficientBalanceException {
        Employee admin = userService.discountToUserBalance(adminId, amount);

        Employee employee;
        for (Long employeeId : employeesIds) {
            employee = userService.incrementToUserBalanceById(employeeId, amount);
            allocationService.saveAllocation(amount, "", admin, employee); 
        }
    }

}
