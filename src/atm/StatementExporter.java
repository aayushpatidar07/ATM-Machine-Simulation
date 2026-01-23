package atm;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * StatementExporter class handles exporting account statements to various formats
 * Supports CSV, TXT, and HTML export formats for transaction history
 * 
 * @author ATM Machine Simulation Team
 * @version 1.0
 * @since 2026-01-23
 */
public class StatementExporter {
    
    private static final DateTimeFormatter FILE_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
    
    /**
     * Exports account statement to CSV format
     * @param accountNumber Account number
     * @param accountHolderName Account holder name
     * @param transactions List of transaction strings
     * @param outputPath Output file path
     * @return true if export successful, false otherwise
     */
    public static boolean exportToCSV(String accountNumber, String accountHolderName, 
                                     List<String> transactions, String outputPath) {
        try {
            String filename = generateFilename(outputPath, accountNumber, "csv");
            FileWriter writer = new FileWriter(filename);
            
            // CSV Header
            writer.write("Account Statement - CSV Export\n");
            writer.write("Account Number," + accountNumber + "\n");
            writer.write("Account Holder," + accountHolderName + "\n");
            writer.write("Generated On," + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n");
            writer.write("\n");
            writer.write("Transaction Type,Amount (Rs.),Balance After (Rs.),Timestamp\n");
            
            // Transaction data
            for (String transaction : transactions) {
                String[] parts = transaction.split("\\|");
                if (parts.length >= 4) {
                    String type = parts[0].trim();
                    String amount = parts[1].trim().replace("₹", "");
                    String balance = parts[2].trim().replace("Balance: ₹", "");
                    String timestamp = parts[3].trim();
                    writer.write(String.format("%s,%s,%s,%s\n", type, amount, balance, timestamp));
                }
            }
            
            writer.close();
            System.out.println("[SUCCESS] Statement exported to: " + filename);
            return true;
        } catch (IOException e) {
            System.out.println("[ERROR] Failed to export statement: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Exports account statement to TXT format
     * @param accountNumber Account number
     * @param accountHolderName Account holder name
     * @param currentBalance Current account balance
     * @param transactions List of transaction strings
     * @param outputPath Output file path
     * @return true if export successful, false otherwise
     */
    public static boolean exportToTXT(String accountNumber, String accountHolderName, 
                                     double currentBalance, List<String> transactions, 
                                     String outputPath) {
        try {
            String filename = generateFilename(outputPath, accountNumber, "txt");
            FileWriter writer = new FileWriter(filename);
            
            // TXT Header
            writer.write("========================================================\n");
            writer.write("              ACCOUNT STATEMENT                         \n");
            writer.write("========================================================\n\n");
            writer.write("Account Number    : " + accountNumber + "\n");
            writer.write("Account Holder    : " + accountHolderName + "\n");
            writer.write("Current Balance   : Rs. " + String.format("%.2f", currentBalance) + "\n");
            writer.write("Statement Date    : " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n");
            writer.write("Total Transactions: " + transactions.size() + "\n\n");
            writer.write("========================================================\n");
            writer.write("                 TRANSACTION HISTORY                    \n");
            writer.write("========================================================\n\n");
            
            // Transaction data
            for (int i = 0; i < transactions.size(); i++) {
                writer.write((i + 1) + ". " + transactions.get(i) + "\n");
            }
            
            writer.write("\n========================================================\n");
            writer.write("                  END OF STATEMENT                      \n");
            writer.write("========================================================\n");
            
            writer.close();
            System.out.println("[SUCCESS] Statement exported to: " + filename);
            return true;
        } catch (IOException e) {
            System.out.println("[ERROR] Failed to export statement: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Exports account statement to HTML format
     * @param accountNumber Account number
     * @param accountHolderName Account holder name
     * @param currentBalance Current account balance
     * @param transactions List of transaction strings
     * @param outputPath Output file path
     * @return true if export successful, false otherwise
     */
    public static boolean exportToHTML(String accountNumber, String accountHolderName, 
                                      double currentBalance, List<String> transactions, 
                                      String outputPath) {
        try {
            String filename = generateFilename(outputPath, accountNumber, "html");
            FileWriter writer = new FileWriter(filename);
            
            // HTML Header
            writer.write("<!DOCTYPE html>\n");
            writer.write("<html>\n<head>\n");
            writer.write("<title>Account Statement - " + accountNumber + "</title>\n");
            writer.write("<style>\n");
            writer.write("body { font-family: Arial, sans-serif; margin: 20px; background-color: #f5f5f5; }\n");
            writer.write(".header { background-color: #003366; color: white; padding: 20px; text-align: center; }\n");
            writer.write(".info { background-color: white; padding: 20px; margin: 20px 0; border-radius: 5px; }\n");
            writer.write("table { width: 100%; border-collapse: collapse; background-color: white; }\n");
            writer.write("th { background-color: #003366; color: white; padding: 12px; text-align: left; }\n");
            writer.write("td { padding: 10px; border-bottom: 1px solid #ddd; }\n");
            writer.write("tr:hover { background-color: #f2f2f2; }\n");
            writer.write(".footer { text-align: center; margin-top: 20px; color: #666; }\n");
            writer.write("</style>\n</head>\n<body>\n");
            
            // Header
            writer.write("<div class='header'>\n");
            writer.write("<h1>ACCOUNT STATEMENT</h1>\n");
            writer.write("</div>\n");
            
            // Account Info
            writer.write("<div class='info'>\n");
            writer.write("<table>\n");
            writer.write("<tr><td><strong>Account Number:</strong></td><td>" + accountNumber + "</td></tr>\n");
            writer.write("<tr><td><strong>Account Holder:</strong></td><td>" + accountHolderName + "</td></tr>\n");
            writer.write("<tr><td><strong>Current Balance:</strong></td><td>Rs. " + String.format("%.2f", currentBalance) + "</td></tr>\n");
            writer.write("<tr><td><strong>Statement Date:</strong></td><td>" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "</td></tr>\n");
            writer.write("<tr><td><strong>Total Transactions:</strong></td><td>" + transactions.size() + "</td></tr>\n");
            writer.write("</table>\n</div>\n");
            
            // Transaction Table
            writer.write("<table>\n");
            writer.write("<tr><th>S.No</th><th>Transaction Type</th><th>Amount (Rs.)</th><th>Balance After (Rs.)</th><th>Timestamp</th></tr>\n");
            
            for (int i = 0; i < transactions.size(); i++) {
                String[] parts = transactions.get(i).split("\\|");
                if (parts.length >= 4) {
                    writer.write("<tr>");
                    writer.write("<td>" + (i + 1) + "</td>");
                    writer.write("<td>" + parts[0].trim() + "</td>");
                    writer.write("<td>" + parts[1].trim() + "</td>");
                    writer.write("<td>" + parts[2].trim() + "</td>");
                    writer.write("<td>" + parts[3].trim() + "</td>");
                    writer.write("</tr>\n");
                }
            }
            
            writer.write("</table>\n");
            
            // Footer
            writer.write("<div class='footer'>\n");
            writer.write("<p>This is a system generated statement. No signature required.</p>\n");
            writer.write("<p>&copy; 2026 ATM Machine Simulation. All rights reserved.</p>\n");
            writer.write("</div>\n");
            
            writer.write("</body>\n</html>");
            writer.close();
            
            System.out.println("[SUCCESS] Statement exported to: " + filename);
            return true;
        } catch (IOException e) {
            System.out.println("[ERROR] Failed to export statement: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Generates unique filename with timestamp
     * @param basePath Base output path
     * @param accountNumber Account number
     * @param extension File extension
     * @return Generated filename
     */
    private static String generateFilename(String basePath, String accountNumber, String extension) {
        String timestamp = LocalDateTime.now().format(FILE_DATE_FORMAT);
        String path = (basePath != null && !basePath.isEmpty()) ? basePath : ".";
        if (!path.endsWith("\\") && !path.endsWith("/")) {
            path += "\\";
        }
        return path + "Statement_" + accountNumber + "_" + timestamp + "." + extension;
    }
}
