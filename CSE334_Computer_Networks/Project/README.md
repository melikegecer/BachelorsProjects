Please, run "receive.bat" and "send.bat" in order.

In this project, I have implemented a protocol which uses UDP connection. Since, UDP is not reliable I tried to make it reliable.

PacketOperations
This is an inner class of ReceiveFile.java. This inner class does some packet operations:
- MTU: maximum transmission unit
- MSS: maximum segment size
- SEQ_NO_LIMIT: sequence number limit
- splitData: gets the bytes of a file and creates chunks of that file, a chunk has 1 byte sequence number, 2 byte checksum, remaining is data.
- checkSum: gets the byte array and calculates the check sum of that chunk.
- binaryToInt: converts number binary format to integer format.
- intToBinary: converts integer format to binary format.
