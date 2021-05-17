package es.nacho.redeem.service;

import es.nacho.redeem.model.Employee;
import es.nacho.redeem.model.Transfer;
import es.nacho.redeem.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransferServiceImpl implements TransferService{

    @Autowired
    private TransferRepository transferRepository;

    @Override
    public Transfer saveTransfer(Employee employeeFrom, Employee employeeTo, long amount) {

        return transferRepository.save(new Transfer(LocalDateTime.now(), employeeFrom, employeeTo, amount));

    }
}
