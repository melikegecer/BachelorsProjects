Please, run "receive.bat" and "send.bat" in order.

In this project, I have implemented a protocol which uses UDP connection. Since, UDP is not reliable I have tried to make it reliable.

PacketOperations
This is an inner class of ReceiveFile.java. This inner class does some packet operations:
- MTU: maximum transmission unit
- MSS: maximum segment size
- SEQ_NO_LIMIT: sequence number limit
- splitData: gets the bytes of a file and creates chunks of that file, a chunk has 1 byte sequence number, 2 byte checksum, remaining is data.
- checkSum: gets the byte array and calculates the check sum of that chunk.
- binaryToInt: converts number binary format to integer format.
- intToBinary: converts integer format to binary format.


SendFile.java
I am taking a file and I am getting its bytes. Then, I am sending these bytes to PacketOperations.splitData. This method returns a list of chunks of that file. Before sending these packet I have send an information packet which includes number of packets and file name to be saved. After, information packet is send the transmission of the file starts.The packet will be sent and waits for ACK. In ACK, there is a sequence number of the packet. If sent packet's and received packet's sequence number is same, then the sent packet will be removed from the list. After transmission is done the socket will be closed and program will be exit.

ReceiveFile.java
Waits for an packet which has information like how many packets will be received and what will be the name of the received file. After that the transmission will start. Checksum of the packet which is received will be calculated. The checksum and the sequence number will be check if both sent packet's and received packet's are same. If they are same ACK, which has sequence number in it, will be sent. Also, as a packet received the program will write the bytes to the file. After transmission is complete the file output stream and socket will be closed and program exits.