package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		System.out.println("Enter with rent data: ");
		System.out.print("Model car: ");
		
		String carModel = sc.nextLine();
		
		System.out.println("Withdrawal (dd/MM/yyyy hh:mm): ");
		LocalDateTime star = LocalDateTime.parse(sc.nextLine(), fmt);
		
		System.out.println("Devolution (dd/MM/yyyy hh:mm): ");
		LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);
		
		CarRental cr = new CarRental(star, finish, new Vehicle(carModel) );
		
		System.out.println("Enter with price per hour: ");
		double pricePerhour = sc.nextDouble();
		
		System.out.println("Enter with price per day: ");
		double pricePerDay = sc.nextDouble();
		
		RentalService rentalService = new RentalService(pricePerhour, pricePerDay, new BrazilTaxService());
		
		rentalService.processInvoice(cr);
		
		System.out.println("Invoice: " );
		System.out.println("Basic payment: " + String.format("%.2f", cr.getInvoice().getBasicPayment()));
		System.out.println("Tax: " +  String.format("%.2f",cr.getInvoice().getTax()));
		System.out.println("Total payment: " + String.format("%.2f",cr.getInvoice().getTotalPayment()));
		sc.close();
	}

}
