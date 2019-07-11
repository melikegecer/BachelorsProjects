import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

class ReceiveFile {

    public static void main(String[] args) {
        int portNumber = Integer.valueOf(args[1]);
        try {
            // create socket
            DatagramSocket serverSocket = new DatagramSocket(portNumber);

            String receiveFileName;
            int numberOfPackets;

            // wait for info pack
            while (true) {
                // receive infos
                byte[] infoByte = new byte[PacketOperations.MTU];
                DatagramPacket infoPacket = new DatagramPacket(infoByte, infoByte.length);
                serverSocket.receive(infoPacket);
                String info = new String(infoPacket.getData());
                String[] infos = info.split("-");
                numberOfPackets = Integer.valueOf(infos[0]);
                receiveFileName = infos[1];
                receiveFileName = receiveFileName.trim();
                if (numberOfPackets != 0 && receiveFileName.length() > 0 && receiveFileName.endsWith(".png")) {
                    break;
                }
            }

            // create file to save
            File f = new File(receiveFileName);
            FileOutputStream fos = new FileOutputStream(f);

            byte[] receiveData = new byte[PacketOperations.MTU];

            int seqNo = 0;
            int prevSeqNo = 0;
            // receive all packets
            while (numberOfPackets > 0) {
                // get packet
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                // get checksum
                byte[] r = PacketOperations.checkSum(receiveData);

                // if checksum and seqNo are OK, go on
                if (r[1] == receiveData[1] && r[2] == receiveData[2] && (r[0] == seqNo)) {

                    String data = seqNo + "-";
                    prevSeqNo = seqNo;
                    seqNo++;
                    if (seqNo >= PacketOperations.SEQ_NO_LIMIT) {
                        seqNo = 0;
                    }

                    byte[] sendData = data.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
                    serverSocket.send(sendPacket);
                    numberOfPackets--;

                    // write to the file
                    for (int j = 3; j < receiveData.length; j++) {
                        fos.write(receiveData[j]);
                    }
                } else if(r[0] == (byte) prevSeqNo){
                    String data = prevSeqNo + "-";
                    byte[] sendData = data.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
                    serverSocket.send(sendPacket);
                }
            }

            // close file writer
            fos.close();

            // close socket, end program
            serverSocket.close();
            return;
        } catch (SocketException ex) {
            Logger.getLogger(ReceiveFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReceiveFile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

class PacketOperations {

    // each chunk will be 1024 Byte
    // 1 Byte for sequence number + 2 Byte for checksum
    // MSS + 3 = MTU
    static final int MTU = 1024;
    static final int MSS = 1021;
    static final int SEQ_NO_LIMIT = 16;

    // splits file into chunks with given MTU
    static ArrayList<DatagramPacket> splitData(byte[] fileBytes, InetAddress IPAddress, int portNumber) {
        ArrayList<DatagramPacket> packetList = new ArrayList<>();

        int count = 0;
        int seqNo = 0;

        // divide file and add to list
        while (count + MSS < fileBytes.length) {
            byte[] chunk = new byte[MTU];

            // 1st byte is sequence number in a chunk
            chunk[0] = (byte) seqNo;
            seqNo++;
            if (seqNo >= SEQ_NO_LIMIT) {
                seqNo = 0;
            }

            // add data into chunk
            for (int i = 3; i < chunk.length; i++) {
                chunk[i] = fileBytes[count + i - 3];
            }

            // add checksum of data into chunk
            chunk = checkSum(chunk);

            // create DatagramPacket object
            // add object to packetList
            packetList.add(new DatagramPacket(chunk, chunk.length, IPAddress, portNumber));
            count += MSS;
        }

        // if there is remaining data, make a last packet
        if (count != fileBytes.length) {
            int len = fileBytes.length - count;
            byte[] chunk = new byte[len + 3];
            if (seqNo >= SEQ_NO_LIMIT) {
                seqNo = 0;
            }
            chunk[0] = (byte) seqNo;

            for (int i = 3; i < chunk.length; i++) {
                chunk[i] = fileBytes[count + i - 3];
            }
            chunk = checkSum(chunk);
            packetList.add(new DatagramPacket(chunk, chunk.length, IPAddress, portNumber));
        }

        return packetList;
    }

    // calculates the checksum of the data and adds into the packet
    static byte[] checkSum(byte[] chunk) {
        int sum = 0;

        for (int i = 3; i < chunk.length - 1; i += 2) {
            // combine 2 byte to have 16 bit
            String combineString = intToBinary(chunk[i]) + "" + intToBinary(chunk[i + 1]);
            int bit16 = binaryToInt(combineString);

            // byte range is [-32768, 32767], i will make it [0, 65535]
            // then i add it to the sum
            sum += (bit16 + 32768);

            // i have 16 bit in checksum therefore my limit is 65535
            // if sum => 65535
            if (sum >= 65535) {
                sum -= 65536;
                sum += 1;
            }
        }

        // since i choose 1 byte for seq no and 2 byte for checksum 
        // i will always have 1 byte of data as extra
        // i should handle them after all couples handled
        String combineString = "00000000" + intToBinary(chunk[chunk.length - 1]);
        int bit16 = binaryToInt(combineString);
        sum += (bit16 + 32768);
        if (sum >= 32768) {
            sum -= 65536;
            sum += 1;
        }

        // sum should be written to the chunk-s 2nd (chunk[1]) and 3rd (chunk[2]) element
        String sumString = intToBinary(sum);

        // last 8 bit is chunk[2]
        int sizeOfString = sumString.length();
        if (sumString.length() > 8) {
            chunk[2] = (byte) binaryToInt(sumString.substring(sizeOfString - 8));
            // if sumString is bigger than 8, then i have to fill chunk[1] with remaining
            chunk[1] = (byte) binaryToInt(sumString);
        } else {
            chunk[2] = (byte) binaryToInt(sumString);
        }

        // other previous bits are chunk[1]
        return chunk;
    }

    // create int representation of bits
    static int binaryToInt(String s) {
        int result = 0;
        int index = 1;

        while (s.length() > 0) {
            int x = (int) s.charAt(s.length() - 1) - 48;
            result += index * x;
            index *= 2;
            s = s.substring(0, s.length() - 1);
        }

        return result;
    }

    // create bit representation of int
    static String intToBinary(int n) {
        String result = "";

        while (0 < n) {
            result = n % 2 + result;
            n /= 2;
        }

        return result;
    }

}
