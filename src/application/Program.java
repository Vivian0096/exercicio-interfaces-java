package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {

	public static void main(String[] args) {
		Scanner inp = new Scanner(System.in);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.println("Entre os dados do contrato:");
		System.out.print("Numero: ");
		int number = inp.nextInt();
		System.out.print("Data (dd/MM/yyyy): ");
		inp.nextLine();
		LocalDate date = LocalDate.parse(inp.next(), fmt);
		System.out.print("Valor do contrato: ");
		double totalValue = inp.nextDouble();
		
		Contract contract = new Contract(number, date, totalValue);
		
		System.out.print("Entre com o numero de parcelas: ");
		int month = inp.nextInt();
		
		ContractService contractService = new ContractService(new PaypalService());
		
		contractService.processContract(contract, month);
		
		System.out.println("Parcelas:");
		
		for(Installment c : contract.getInstallment()) {
			System.out.println(c.getDueDate().format(fmt) + " - " + c.getAmount());
		}
		
		inp.close();
	}
}
