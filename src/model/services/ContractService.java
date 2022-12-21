package model.services;

import java.time.LocalDate;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	
	private OnlinePaymentService paymentService;

	public ContractService(OnlinePaymentService paymentService) {
		this.paymentService = paymentService;
	}

	public void processContract(Contract contract, Integer months) {
		Double plots = contract.getTotalValue() / months;
		for(int i = 1; i <= months; ++i) {
			Double amount = plots + paymentService.interest(plots, i) + paymentService.paymentFee(paymentService.interest(plots, i) + plots);
			LocalDate dueDate = contract.getDate().plusMonths(i);
			contract.addInstallment(new Installment(dueDate, amount));
		}
	}
}
