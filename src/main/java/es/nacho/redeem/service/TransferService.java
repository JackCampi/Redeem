package es.nacho.redeem.service;

import es.nacho.redeem.data.SortedList;
import es.nacho.redeem.model.Employee;
import es.nacho.redeem.model.Transfer;
import es.nacho.redeem.web.dto.transfer.TransferHistoryMessageDto;
import es.nacho.redeem.web.dto.transfer.history.EmpDto;

import java.util.Collection;

public interface TransferService {

    Transfer saveTransfer(Employee employeeFrom, Employee employeeTo, long amount);

    Collection<TransferHistoryMessageDto> getTransferMessages(long id);

    SortedList<EmpDto> getEmployeeTransMessages(Employee employee, SortedList<EmpDto> sortedList);

}
