/**
 * This Class starts and manages the server and threads.
 * 
 * @author Wesley Easton
 * @verion 1.0
 * 
 * COP 4027 Project5
 * File Name:  Server.java
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server 
{
	final static int PORT = 8888;
	private int count = 1;
	
   public static void main(String[] args) throws IOException
   {
	   Game game = new Game();
	   ServerSocket server = new ServerSocket(PORT);
	   int count = 1;
	   int playerId = 0;
	   
	   while(true)
	   {
		   if(count % 2 != 0)
		   {
			   game = new Game();
			   playerId = 1;
		   }
		   else 
		   {
			   playerId = 2;
		   }
		   Socket socket = server.accept();
		   GameService gameService = new GameService(socket, game, playerId);
		   Thread gameThread = new Thread(gameService);
		   gameThread.start();
		   count++;
	   }
   }

}
