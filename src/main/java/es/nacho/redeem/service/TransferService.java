package es.nacho.redeem.service;

import es.nacho.redeem.model.Employee;
import es.nacho.redeem.model.Transfer;

public interface TransferService {

    Transfer saveTransfer(Employee employeeFrom, Employee employeeTo, long amount);

}
