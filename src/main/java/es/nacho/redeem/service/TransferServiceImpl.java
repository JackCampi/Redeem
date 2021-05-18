package es.nacho.redeem.service;

import es.nacho.redeem.exception.UserNotFoundException;
import es.nacho.redeem.model.Employee;
import es.nacho.redeem.model.Transfer;
import es.nacho.redeem.repository.EmployeeRepository;
import es.nacho.redeem.repository.TransferRepository;
import es.nacho.redeem.web.dto.transfer.TransferHistoryMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TransferServiceImpl implements TransferService{

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Transfer saveTransfer(Employee employeeFrom, Employee employeeTo, long amount) {

        return transferRepository.save(new Transfer(LocalDateTime.now(), employeeFrom, employeeTo, amount));

    }

    @Override
    public Collection<TransferHistoryMessageDto> getTransferMessages(long id) {

        Optional<Employee> employee = employeeRepository.findById(id);
        if(!employee.isPresent()) throw new UserNotFoundException();
        Employee employeeObject = employee.get();

        ArrayList<TransferHistoryMessageDto> transferHistoryMessageDtos = new ArrayList<>();

        employeeObject.getOutgoingTransfers().forEach(transfer -> {

            transferHistoryMessageDtos.add(new TransferHistoryMessageDto(
                    transfer.getEmployeeTo().getName(),
                    transfer.getDatetime(),
                    transfer.getAmount(),false

            ));

        });

        employeeObject.getIncomingTransfers().forEach(transfer -> {

            transferHistoryMessageDtos.add(new TransferHistoryMessageDto(
                    transfer.getEmployeeFrom().getName(),
                    transfer.getDatetime(),
                    transfer.getAmount(),
                    true
            ));

        });

        Collections.sort(transferHistoryMessageDtos);

        return transferHistoryMessageDtos;

    }
}
