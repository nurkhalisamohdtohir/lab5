package exercise2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientApplication {
	
public static void main(String[] args) {
		
		System.out.println("ClientSideApp: sending request\n");

		// Request data
		itemProduct product = new itemProduct();
		product.setName("Roti Canai");
		

		try {

			// Data to establish connection to server
			int portNo = 4228;
			InetAddress serverAddress = InetAddress.getLocalHost();

			// Connect to the server at localhost, port 4228
			Socket socket = new Socket(serverAddress, portNo);

			// Open stream to send object
			ObjectOutputStream objectOS = new ObjectOutputStream(socket.getOutputStream());

			// Send request to server
			System.out.println("Send object to server: " + product);
			objectOS.writeObject(product);
			objectOS.flush();
			
			// Open stream to receive object
			ObjectInputStream objectIS = new ObjectInputStream(socket.getInputStream());
			
			// Get object from stream and display details
			product = (itemProduct) objectIS.readObject();
			
			System.out.println (product.getName() + " " + product.getResult());
			System.out.println ("Id for " + product.getName() + " is " + product.getItemProductId());
			
			// Close all closeable objects
			objectOS.close();
			objectIS.close();
			socket.close();

		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("\nClientSideApp: End of application.\n");

	}

}
