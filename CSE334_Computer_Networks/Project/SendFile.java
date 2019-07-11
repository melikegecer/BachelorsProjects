import java.io.*;
import java.net.*;
import java.sql.Timestamp;
import java.util.ArrayList;

class SendFile {

    private static int TIME_OUT = 3000;

    public static void main(String[] args) {
        String[] ip_port = args[1].split(":");
        String fileName = args[3];
        int portNumber = Integer.valueOf(ip_port[1]);
        String ipAddress = ip_port[0];

        try {
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName(ipAddress);

            // get bytes of a file
            File f = new File(fileName);
            FileInputStream fis = new FileInputStream(f);
            byte[] fileBytes = new byte[(int) f.length()];
            fis.read(fileBytes);
            fis.close();

            // keep packets in a list
            ArrayList<DatagramPacket> sendPacketList = PacketOperations.splitData(fileBytes, IPAddress, portNumber);

            // info packet
            int numberOfPackets = sendPacketList.size();
            String info = numberOfPackets + "-" + "r" + fileName;
            byte[] infoBytes = info.getBytes();
            DatagramPacket infoPacket = new DatagramPacket(infoBytes, infoBytes.length, IPAddress, portNumber);
            clientSocket.send(infoPacket);

            byte[] receiveData = new byte[PacketOperations.MTU];

            // set timer
            clientSocket.setSoTimeout(TIME_OUT);

            int count = 0;

            // send all packets
            while (sendPacketList.size() > 0) {
                // send packet
                System.out.println(new Timestamp(System.currentTimeMillis()) + " [send data] " + count + " " + (sendPacketList.get(0).getData().length - 3));
                clientSocket.send(sendPacketList.get(0));

                // wait for ACK, if did not received send packet again
                try {
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    clientSocket.receive(receivePacket);
                    String seqNo_ACK = new String(receivePacket.getData());
                    String[] split = seqNo_ACK.split("-");
                    byte seqNo = Byte.valueOf(split[0].trim());

                    // check seq no, if equal remove packet from list
                    if (sendPacketList.get(0).getData()[0] == seqNo) {
                        count += PacketOperations.MSS;
                        System.out.println(new Timestamp(System.currentTimeMillis()) + " [recv ack] " + count);
                        sendPacketList.remove(0);
                    }
                } catch (SocketTimeoutException ex) {
                    // if it did not receive the ACK of the packet, it will not remove it from the list
                    // therefore it will try the same packet again
                    System.out.println(new Timestamp(System.currentTimeMillis()) + "UDP CLIENT _ SOCKET TIME OUT EXCEPTION _ RECEIVE ACK");
                    TIME_OUT += 1000;
                } catch (NumberFormatException ex) {
                    // if seq no corrupted, try again (can not convert to a byte)
                    System.out.println(new Timestamp(System.currentTimeMillis()) + "UDP CLIENT _ CORRUPTION");
                }
                TIME_OUT -= 500;
            }

            // close socket, and program
            clientSocket.close();
            return;
        } catch (SocketException ex) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "UDP CLIENT _ SOCKET EXCEPTION");
        } catch (UnknownHostException ex) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "UDP CLIENT _ UNKNOWN HOST EXCEPTION");
        } catch (FileNotFoundException ex) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "UDP CLIENT _ FILE NOT FOUND EXCEPTION");
        } catch (IOException ex) {
            System.out.println(new Timestamp(System.currentTimeMillis()) + "UDP CLIENT _ IO EXCEPTION");
        }
    }
}
