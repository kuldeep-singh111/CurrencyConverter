import org.json.JSONObject;
import java.util.HashMap;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

public class Main{
    public static void main(String[] args) throws IOException{
       
       Boolean running = true;
       
       do{
          
       HashMap<Integer, String> currencyCodes = new HashMap<Integer,String>();


          // Add currency codes
          
          currencyCodes.put(1, "USD");
          currencyCodes.put(2, "INR");
          currencyCodes.put(3, "CAD");
          currencyCodes.put(4, "EUR");
          currencyCodes.put(5, "HKD");
          
          String fromCode, toCode;
          double amount;
          
          Scanner sc=new Scanner(System.in); 
    
       System.out.println("Welcome to the Currency Converter!");
       
       System.out.println("Currency Converter FROM?");
       System.out.println("1:USD (us Dollar)\t 2:INR (Indian Rupees)\t 3:CAD (Canadian Dollar)\t 4:EUR (EURO)\t 5:HKD (Hong kong Dollar)");
       from = sc.nextInt();
       while(from<1 || from>5);
       {
          System.out.println("Please select  a valid Currency (1-5)");
          System.out.println("1:USD (us Dollar)\t 2:INR (Indian Rupees)\t 3:CAD (Canadian Dollar)\t 4:EUR (EURO)\t 5:HKD (Hong kong Dollar)");
       }
         
         fromCode = currencyCodes.get(from);
       
       System.out.println("Currency Converter To?");
       System.out.println("1:USD (us Dollar)\t 2:INR (Indian Rupees)\t 3:CAD (Canadian Dollar)\t 4:EUR (EURO)\t 5:HKD (Hong Kong Dollar)");
       to = sc.nextInt();
       
       while(to < 1 || to > 5);
       {
          System.out.println("Please select  a valid Currency (1-5)");
          System.out.println("1:USD (us Dollar)\t 2:INR (Indian Rupees)\t 3:CAD (Canadian Dollar)\t 4:EUR (EURO)\t 5:HKD (Hong kong Dollar)");
       }
         
         toCode = currencyCodes.get(to);
       
       
       
       
       
           System.out.println("Amount you wish to convert?");
           amount = sc.nextFloat();
           
           sendHttpGETRequest(fromCode, toCode, amount);
           
           System.out.println("Would you like to make another conversion?");
           System.out.println("1:Yes \t Any other key: No");
           
           if(sc.nextInt() !=1){
              running = false;
           }
           
       }
       while(running);
       
       System.out.println("Thank you for using the Currency Converter!");
    }
       
       
       
       private static void sendHttpGETRequest(String fromCode, String toCode, double amount)
       {
          
         // DecimalFormat f = new DecimalFormat(pattern: , "00.00"); 
         
         
         String GET_URL = "https://api.exchangeratesapi.io/latest?base=" + toCode + "&symbols=" + fromCode;
       URL url = new URL(GET_URL);
       HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
       httpURLConnection.setRequestMethod("GET");
       int responseCode = httpURLConnection.getResponseCode();
       
        if (responseCode == HttpURLConnection.HTTP_OK)    // Success
        { 
           
           BufferedReader in= new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
       String inputLine;
       StringBuffer response = new StringBuffer();
       
       while((inputLine = in.readLine()) !=null){
          response.append(inputLine);
          
       }
       
       in.close();
       
           JSONObject obj = new JSONObject(response.toString());
           Double ExchangeRate = obj.getJSONObject("rates").getDouble(fromCode);
           System.out.println(obj.getJSONObject("rates"));
           System.out.println(exchangeRate); // keep for debugging
           System.out.println();
           System.out.println(amount + fromCode + "=" + amount/exchangeRate + toCode);
        } 
        else{
           System.out.print("GET request failed ");
        }
       }
    } 
    

//This is a currency converter program which has the ability to convert currencies of multiple countries.








