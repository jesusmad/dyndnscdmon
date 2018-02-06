import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class DynIP {

	public static void main(String[] args) {

		/*	
		 	API: 		https://dinamico.cdmon.org/onlineService.php
			ENCTYPE:	?enctype=MD5
			USER:	 	&n=username
			PSWMD5:  	&p=81dc9bdb52d04dc20036dbd8313ed055
		 */

		Logger logger = Logger.getLogger("MyLog");  

		final String DOMAIN = "yourdomain.com";
		final String CHECKIP = "http://checkip.amazonaws.com";

		try {	 

			//Initialize and configure the logger
			FileHandler fh = new FileHandler("dynip.log", true);  
			logger.addHandler(fh);
			logger.setUseParentHandlers(false);
			SimpleFormatter formatter = new SimpleFormatter();  
			fh.setFormatter(formatter);  

			//Get the domain's IP stored in the DNS
			InetAddress inetaddr = InetAddress.getByName(DOMAIN);
			String ipdns = inetaddr.getHostAddress();

			//Get current IP
			URL whatismyip = new URL(CHECKIP);
			BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
			String iphost = in.readLine();
			in.close();

			if(!ipdns.equals(iphost)) {

				final String APIUrl = "https://dinamico.cdmon.org/onlineService.php?enctype=MD5&n=username&p=81dc9bdb52d04dc20036dbd8313ed055";

				//API call to update the DNS record with the new IP
				URL url =new URL(APIUrl);
				URLConnection conexion=url.openConnection();

				//Fetch API response
				BufferedReader reader = new BufferedReader (new InputStreamReader(conexion.getInputStream()));
				String apiResponse = reader.readLine();    
				reader.close();

				//Save info into the log
				logger.info("API Response: " + apiResponse);
				logger.info("UPDATE " + DOMAIN + " DNS record IP request from: " + ipdns + " to " +  iphost);

			}
		} 
		catch (UnknownHostException e) { logger.severe(e.getMessage());	} 
		catch (MalformedURLException e) { logger.severe(e.getMessage()); } 
		catch (IOException e) { logger.severe(e.getMessage()); }
	}
}
