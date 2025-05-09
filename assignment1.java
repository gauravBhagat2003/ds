//Search.java

import java.rmi.*;
public interface Search extends Remote
{
public String query(String search)throws
RemoteException;
}
SearchQuery.java
import java.rmi.*;
import java.rmi.server.*;
public class SearchQuery extends RemoteObject
implements Search
{
public String query(String search) throws
RemoteException
{
String result;
if(search.equals("Reflection in Java"))
result = "Found";
else
result = "Not Found"; return
result;
}
}

//Server.java

import java.net.*;
import java.io.*;
public class Server
{
private Socket socket = null;
private ServerSocket server = null;
private DataInputStream in = null;
public Server(int port)
{
try
{
server = new ServerSocket(port);
System.out.println("Server Started");
System.out.println("Waiting for a client. .. ");
socket = server.accept();
System.out.println("Client accepted");
in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
String line = " ";
while(!line.equals("Over"))
{
try
{
line = in.readUTF();
System.out.println(line);
}
catch(IOException i)
{
System.out.println(i);
}}
System.out.println("Closing Connection");
socket.close();
in.close();
}
catch(IOException i)
{
System.out.println(i);
}
}
public static void main(String args[])
{
Server server = new Server(5000);
}
}


//Client.java

import java.io.*;
import java.net.*;
public class Client
{
private Socket socket = null;
private BufferedReader d = null;
private InputStream input = null;
private DataOutputStream out = null;
public Client(String address , int port)
{
try
{
socket = new Socket(address,port);
System.out.println("Connected");
System.out.println("Done with 1st program Of DS");
d= new BufferedReader(new InputStreamReader(System.in));
out = new DataOutputStream(socket.getOutputStream());
}
catch(UnknownHostException u)
{
System.out.println(u);
return;
}
catch(IOException i)
{
System.out.println(i);
return;
}
String line = " " ;
while(!line.equals("Over"))
{
try
{
line = d.readLine();
out.writeUTF(line);
}
catch(IOException i)
{
System.out.println(i);
}}
try
{
input.close();
out.close();
socket.close();
}
catch(IOException i)
{
System.out.println(i);
}}
public static void main(String args[])
{
Client client = new Client("127.0.0.1",5000);
}
} 