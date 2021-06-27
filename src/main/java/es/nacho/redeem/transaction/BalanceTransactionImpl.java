package es.nacho.redeem.transaction;

import es.nacho.redeem.exception.InsufficientBalanceException;
import es.nacho.redeem.exception.UserNotFoundException;
import es.nacho.redeem.model.Company;
import es.nacho.redeem.model.Employee;
import es.nacho.redeem.repository.EmployeeRepository;
import es.nacho.redeem.service.AllocationService;
import es.nacho.redeem.service.CompanyService;
import es.nacho.redeem.service.TransferService;
import es.nacho.redeem.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
public class BalanceTransactionImpl implements BalanceTransaction{

    @Autowired
    private UserService userService;

    @Autowired
    private TransferService transferService;
    
    @Autowired
    private CompanyService companyService;
    
    @Autowired
    private AllocationService allocationService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional(rollbackOn = {UserNotFoundException.class, InsufficientBalanceException.class})
    public void userToUserBalanceTransaction(long activeUserId, String receiverIdentifier, long amount) throws UserNotFoundException, InsufficientBalanceException {

        Employee employeeFrom = userService.discountToUserBalance(activeUserId, amount);

        Employee employeeTo;
        if(receiverIdentifier.contains("@")) employeeTo = userService.incrementToUserBalanceByEmail(receiverIdentifier, amount);
        else employeeTo = userService.incrementToUserBalanceById(Long.parseLong(receiverIdentifier), amount);

        transferService.saveTransfer(employeeFrom, employeeTo, amount);
        
    }

    @Override
    @Transactional(rollbackOn = {UserNotFoundException.class, InsufficientBalanceException.class})
    public void userToUsersBalanceTransaction(long nit, long adminId, Collection<Long> employeesIds, long amount)
            throws UserNotFoundException, InsufficientBalanceException {
        Company company = companyService.discountCompanyBudget(nit, amount*employeesIds.size());
        Optional<Employee> temporaryAdmin = employeeRepository.findById(adminId);
        if(!temporaryAdmin.isPresent()) throw new UserNotFoundException();
        
        Employee admin = temporaryAdmin.get();

        Employee employee;
        for (Long employeeId : employeesIds) {
            employee = userService.incrementToUserBalanceById(employeeId, amount);
            allocationService.saveAllocation(company.getName(), amount, "", admin, employee); 
        }
    }

    @Override
    @Transactional(rollbackOn = {Exception.class, InsufficientBalanceException.class})
    public void returnBalanceToCompany(long nit, long userId, long amount) throws InsufficientBalanceException {

        userService.discountToUserBalance(userId, amount);

        companyService.incrementCompanyBudget(nit, amount);

    }
}
