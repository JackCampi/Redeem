package es.nacho.redeem.service;

import es.nacho.redeem.model.Employee;
import es.nacho.redeem.model.Transfer;
import es.nacho.redeem.repository.TranferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransferServiceImpl implements TransferService{

    @Autowired
    private TranferRepository tranferRepository;

    @Override
    public Transfer saveTransfer(Employee employeeFrom, Employee employeeTo, long amount) {

        return tranferRepository.save(new Transfer(LocalDateTime.now(), employeeFrom, employeeTo, amount));

    }
}
