package javainterface;


	
	import java.util.ArrayList;
	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	
	class AtmSystem {
	    public double balance;
	    public ArrayList<String> transactionHistory;

	    public AtmSystem() {
	        this.balance = 0;
	        this.transactionHistory = new ArrayList<>();
	    }

	    public void checkBalance(JTextArea outputArea) {
	        outputArea.setText("Current Balance is: $ " + balance);
	    }

	    public void depositMoney(double amount, JTextArea outputArea) {
	        if (amount > 0) {
	            balance += amount;
	            transactionHistory.add("Deposited amount: $ " + amount);
	            outputArea.setText("Deposited Successfully: $ " + amount);
	        } else {
	            outputArea.setText("Invalid amount. Please enter a valid value.");
	        }
	    }

	    public void withdrawMoney(double amount, JTextArea outputArea) {
	        if (amount > 0 && amount <= balance) {
	            balance -= amount;
	            transactionHistory.add("Withdrew amount: $ " + amount);
	            outputArea.setText("Withdrawn Successfully: $ " + amount);
	        } else if (amount > balance) {
	            outputArea.setText("Insufficient balance. Please verify.");
	        } else {
	            outputArea.setText("Invalid amount. Please enter a valid value.");
	        }
	    }

	    public void viewTransactionHistory(JTextArea outputArea) {
	        StringBuilder history = new StringBuilder("Transaction History:\n");
	        if (transactionHistory.isEmpty()) {
	            history.append("No transactions found.");
	        } else {
	            for (String transaction : transactionHistory) {
	                history.append(transaction).append("\n");
	            }
	        }
	        outputArea.setText(history.toString());
	    }
	}

	public class Interface_atm extends JFrame {
	    private AtmSystem atmSystem;
	    private JTextField inputField;
	    private JTextArea outputArea;

	    public Interface_atm() {
	        atmSystem = new AtmSystem();

	        setTitle("ATM Interface");
	        setSize(500, 700);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLayout(new BorderLayout());

	        // Output area
	        outputArea = new JTextArea();
	        outputArea.setEditable(false);
	        JScrollPane scrollPane = new JScrollPane(outputArea);
	        add(scrollPane, BorderLayout.CENTER);

	        // Input area
	        JPanel inputPanel = new JPanel();
	        inputPanel.setLayout(new GridLayout(2,1));

	        inputField = new JTextField();
	        inputPanel.add(inputField);

	        JButton enterButton = new JButton("Enter");
	        inputPanel.add(enterButton);

	        add(inputPanel, BorderLayout.SOUTH);

	        // Buttons
	        JPanel buttonPanel = new JPanel();
	        buttonPanel.setLayout(new GridLayout(5, 1));

	        JButton checkBalanceButton = new JButton("Check Balance");
	        JButton depositMoneyButton = new JButton("Deposit Money");
	        JButton withdrawMoneyButton = new JButton("Withdraw Money");
	        JButton viewHistoryButton = new JButton("View Transaction History");
	        JButton exitButton = new JButton("Exit");

	        buttonPanel.add(checkBalanceButton);
	        buttonPanel.add(depositMoneyButton);
	        buttonPanel.add(withdrawMoneyButton);
	        buttonPanel.add(viewHistoryButton);
	        buttonPanel.add(exitButton);

	        add(buttonPanel, BorderLayout.CENTER);

	        // Button actions
	        checkBalanceButton.addActionListener(e -> atmSystem.checkBalance(outputArea));

	        depositMoneyButton.addActionListener(e -> {
	            try {
	                double amount = Double.parseDouble(inputField.getText());
	                atmSystem.depositMoney(amount, outputArea);
	                inputField.setText("");
	            } catch (NumberFormatException ex) {
	                outputArea.setText("Invalid input. Please enter a numeric value.");
	            }
	        });

	        withdrawMoneyButton.addActionListener(e -> {
	            try {
	                double amount = Double.parseDouble(inputField.getText());
	                atmSystem.withdrawMoney(amount, outputArea);
	                inputField.setText("");
	            } catch (NumberFormatException ex) {
	                outputArea.setText("Invalid input. Please enter a numeric value.");
	            }
	        });

	        viewHistoryButton.addActionListener(e -> atmSystem.viewTransactionHistory(outputArea));

	        exitButton.addActionListener(e -> System.exit(0));

	        setVisible(true);
	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(Interface_atm::new);
	    }
	}


	
