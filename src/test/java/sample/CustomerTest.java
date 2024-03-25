package sample;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import sample.account.Account;
import sample.account.Deposit;
import sample.account.InternationalAccount;
import sample.account.InternationalDeposit;
import sample.account.LocalAccount;

public class CustomerTest {
	
	private File tempFile;
	
	@BeforeEach
	public void setup() {
		try {
			tempFile = File.createTempFile("test", ".csv");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@AfterEach
	public void cleanup() {
		// Deletando o arquivo temporário
		if (tempFile != null && tempFile.exists()) {
			tempFile.delete();
		}	
	}
		
	@Test
	public void testSaveCustomersToCSV() {
		// Criando clientes fictícios
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer("Cliente1", new LocalAccount(100.0)));
		customers.add(new Customer("Cliente2", new LocalAccount(200.0)));

		// Chamando o método para salvar os clientes no arquivo CSV
		try (FileWriter fileWriter = new FileWriter(tempFile)) {
			Customer.saveCustomersToCSV(customers, tempFile.getAbsolutePath(), fileWriter);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Lendo o conteúdo do arquivo temporário para verificar se foi gravado
		// corretamente
		StringBuilder content = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new FileReader(tempFile))) {
			String line;
			while ((line = reader.readLine()) != null) {
				content.append(line).append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Verificando se o conteúdo gerado está correto
		String expected = "Name,Account,IsVIP\n" + "Cliente1,100.0,No\n"
				+ "Cliente2,200.0,No\n";
		String obtained = content.toString();
		assertEquals(expected, obtained);
	}
	/*
	 * @Test public void checkIsCustomerIsNotVIP() {
	 * 
	 * Customer customer = new Customer("John doe", new LocalAccount() { public
	 * double getAmountDepositAvg() { return 999; } });
	 * 
	 * assertFalse(customer.isVIP(), "The customer must not be vip"); }
	 * 
	 * @Test public void checkIsCustomerIsVIP() {
	 * 
	 * Customer customer = new Customer("John doe", new LocalAccount() { public
	 * double getAmountDepositAvg() { return 1000; } });
	 * 
	 * assertFalse(customer.isVIP(), "The customer must be vip"); }
	 * 
	 * @Test public void testDepositsForVipAvg() {
	 * 
	 * Customer customer = new Customer("John doe", new LocalAccount());
	 * customer.getAccount().addDeposit(new Deposit(customer.getAccount(), 500,
	 * null)); customer.getAccount().addDeposit(new Deposit(customer.getAccount(),
	 * 800, null)); customer.getAccount().addDeposit(new
	 * Deposit(customer.getAccount(), 1200, null));
	 * customer.getAccount().addDeposit(new Deposit(customer.getAccount(), 300,
	 * null));
	 * 
	 * double avg = (500 + 800 + 1200 + 300) / 4;
	 * 
	 * assertTrue(avg == avg, "Deposits avg"); }
	 * 
	 * @Test public void testDepositsForVipSum() {
	 * 
	 * Customer customer = new Customer("John doe", new LocalAccount());
	 * customer.getAccount().addDeposit(new Deposit(customer.getAccount(), 500,
	 * null)); customer.getAccount().addDeposit(new Deposit(customer.getAccount(),
	 * 800, null)); customer.getAccount().addDeposit(new
	 * Deposit(customer.getAccount(), 1200, null));
	 * customer.getAccount().addDeposit(new Deposit(customer.getAccount(), 300,
	 * null));
	 * 
	 * assertTrue(customer.getAccount().getSumDeposit() == 500 + 800 + 1200 + 300,
	 * "Deposits total"); }
	 * 
	 * @Test public void testDepositsForInternationAccountVipAvg() {
	 * 
	 * Customer customer = new Customer("John doe", new
	 * InternationalAccount("Brazil", "BRL")); customer.getAccount() .addDeposit(new
	 * InternationalDeposit(((InternationalAccount) customer.getAccount()), 500,
	 * null, "USD")); customer.getAccount() .addDeposit(new
	 * InternationalDeposit(((InternationalAccount) customer.getAccount()), 800,
	 * null, "USD")); customer.getAccount().addDeposit( new
	 * InternationalDeposit(((InternationalAccount) customer.getAccount()), 1200,
	 * null, "USD")); customer.getAccount() .addDeposit(new
	 * InternationalDeposit(((InternationalAccount) customer.getAccount()), 300,
	 * null, "USD"));
	 * 
	 * assertTrue(customer.getAccount().getAmountDepositAvg() == (500 + 800 + 1200 +
	 * 300) / 4, "Deposits avg"); }
	 * 
	 * @Test public void testDepositsForInternationalAccountVipSum() {
	 * 
	 * Customer customer = new Customer("John doe", new
	 * InternationalAccount("Brazil", "BRL")); customer.getAccount() .addDeposit(new
	 * InternationalDeposit(((InternationalAccount) customer.getAccount()), 500,
	 * null, "USD")); customer.getAccount() .addDeposit(new
	 * InternationalDeposit(((InternationalAccount) customer.getAccount()), 800,
	 * null, "USD")); customer.getAccount().addDeposit( new
	 * InternationalDeposit(((InternationalAccount) customer.getAccount()), 1200,
	 * null, "USD")); customer.getAccount() .addDeposit(new
	 * InternationalDeposit(((InternationalAccount) customer.getAccount()), 300,
	 * null, "USD"));
	 * 
	 * assertTrue(customer.getAccount().getSumDeposit() == 500 + 800 + 1200 + 300,
	 * "Deposits total"); }
	 * 
	 * @Test public void testEquality() {
	 * 
	 * Customer customer1 = new Customer("Customer 1", new LocalAccount()),
	 * customer2 = new Customer("Customer 1", customer1.getAccount());
	 * 
	 * assertEquals(customer1, customer1); }
	 */
}
