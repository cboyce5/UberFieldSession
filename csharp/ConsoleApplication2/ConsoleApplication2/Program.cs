using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
namespace ConsoleApplication2
{
    class Program
    {
        private static System.Net.Sockets.NetworkStream stream;
        private static System.Net.Sockets.TcpClient serverSocket;
        static void Main(string[] args)
        {
            try
            {
                serverSocket = new System.Net.Sockets.TcpClient("suchlol.com", 6969);
                if (serverSocket.Connected)
                {
                    System.Console.WriteLine("Receiving info from Kafka.");
                    
                }
                stream = serverSocket.GetStream();
                if (stream.CanRead)
                {
                    System.Console.WriteLine("Stream is working.");
                }
                // Send the message to the connected TcpServer. 
                //in1 = new BufferedStream(new InputStreamReader(serverSocket.getInputStream()));
            }
            catch (Exception e)
            {
                System.Console.WriteLine(e);
            }
            //perform the update portion.
            //while true! oh no!  couldn't find a Console utility that would do it while the Console it open.
            while(true){
                try
                {
                    Byte[] data;
                    data = new Byte[256];
                    // String to store the response ASCII representation.
                    String responseData = String.Empty;
                    // Read the first batch of the TcpServer response bytes.
                    Int32 bytes = stream.Read(data, 0, data.Length);
                    responseData = System.Text.Encoding.ASCII.GetString(data, 0, bytes);
                    Console.WriteLine("Received: {0}", responseData);
                }
                catch (IOException e)
                {
                    System.Console.WriteLine(e.Message);
                }
            }
            //perform the update portion.
            try
            {
                serverSocket.Close();
            }
            catch (IOException e)
            {
                Console.WriteLine(e.StackTrace);
            }

        }
    }
}
