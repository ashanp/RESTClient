package ae.co.etisalat.falcon.invoice.client;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONObject;

public class invoiceServicebyAccountIdRESTClient {
	public static void main(String[] args) {
		String string = "";
		try {
			InputStream inquieyStream = new FileInputStream("/Users/ashanp/Development/eclipse/jee-oxygen/workspace/FalconRESTClient/files/invoiceServicebyAccountIdREST.txt");
			InputStreamReader invoiceRead = new InputStreamReader(inquieyStream);
			BufferedReader br = new BufferedReader(invoiceRead);
			String line;
			while ((line = br.readLine()) != null) {
				string += line + "\n";
			}
 
			JSONObject jsonObject = new JSONObject(string);
			System.out.println(jsonObject);

			try {
				URL url = new URL("http://localhost:7001/FalconInvoice/invoiceServicebyAccountIdREST");
				URLConnection connection = url.openConnection();
				connection.setDoOutput(true);
				connection.setRequestProperty("Content-Type", "application/json");
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(5000);
				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
				out.write(jsonObject.toString());
				out.close();
 
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
 
				//while (in.readLine() != null) {
				while ((line = in.readLine()) != null) {
					System.out.println(line);
				}
				System.out.println("\nFalconInvoices REST Service Invoked Successfully..");
				in.close();
			} catch (Exception e) {
				System.out.println("\nError while calling FalconInvoices REST Service");
				System.out.println(e);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
