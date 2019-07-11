#include <ESP8266WiFi.h>
#include <SoftwareSerial.h>

SoftwareSerial NodeSerial(D2, D3);

// keeps motor's status
int motorStatus = 0;

// wifi ssid and password
const char* ssid = "melwifi";
const char* password = "melike123*";
WiFiServer WebServer(80);
WiFiClient client;

void setup() {
  Serial.begin(115200);
  delay(10);
  Serial.println();
  Serial.println();
// pins used to communicate with arduino
  pinMode(D2, INPUT);
  pinMode(D3, OUTPUT);
  NodeSerial.begin(4800);

  WiFi.disconnect();
  WiFi.mode(WIFI_STA);
  Serial.print("Connecting to ");
  Serial.println(ssid);
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.println("Connected to WiFi");
  WebServer.begin();
  Serial.println("Web Server started");
  Serial.print("You can connect to the ESP8266 at this URL: ");
  Serial.print("http://");
  Serial.print(WiFi.localIP());
  Serial.println("/");
}

void loop() {
  client = WebServer.available();
  if (!client) {
    return;
  }

  Serial.println("New User");
  while (!client.available()) {
    delay(1);
  }

  String request = client.readStringUntil('\r\n');
  Serial.println(request);
  client.flush();

  if (request.indexOf("/STOP") != -1) {
    motorStatus = 0;
  }
  if (request.indexOf("/GO-FORWARD") != -1) {
    motorStatus = 1;
  }
  if (request.indexOf("/GO-BACKWARD") != -1) {
    motorStatus = 2;
  }
  if (request.indexOf("/GO-LEFT") != -1) {
    motorStatus = 3;
  }
  if (request.indexOf("/GO-RIGHT") != -1) {
    motorStatus = 4;
  }
  if (request.indexOf("/GO-NORTH") != -1) {
    motorStatus = 5;
  }
  if (request.indexOf("/GO-SOUTH") != -1) {
    motorStatus = 6;
  }
  if (request.indexOf("/GO-EAST") != -1) {
    motorStatus = 7;
  }
  if (request.indexOf("/GO-WEST") != -1) {
    motorStatus = 8;
  }

  NodeSerial.print(motorStatus);
  Serial.println(motorStatus);
  delay(100);

  client.println("HTTP/1.1 200 OK");
  client.println("Content-Type: text/html; charset=UTF-8");
  client.println("");
  client.println("<!DOCTYPE HTML>");
  client.println("<html>");
  client.println("<head>");
  client.println("<title>Project-1</title>");
  client.println("</head>");
  client.println("<body>");
  client.println("<a href=\"/\">Refresh Status</a>");
  client.println("</br></br>");

  if (motorStatus == 0) {
    client.print("<font color=\"red\">STOPPED</font></br>");
    client.println("<table><tr><td width=\"50\" height=\"50\"></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-FORWARD\"><img src=\"https://maxcdn.icons8.com/windows8/PNG/26/Arrows/up_arrow-26.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\"></td></tr><tr><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-LEFT\"><img src=\"https://maxcdn.icons8.com/windows8/PNG/26/Arrows/left_arrow-26.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\" align=\"middle\"><a href=\"/STOP\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Very_Basic/cancel_filled-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-RIGHT\"><img src=\"https://maxcdn.icons8.com/windows8/PNG/26/Arrows/right_arrow-26.png\" width=\"26\" height=\"26\"></a></td></tr><tr><td width=\"50\" height=\"50\"></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-BACKWARD\"><img src=\"https://maxcdn.icons8.com/windows8/PNG/26/Arrows/down_arrow-26.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\"></td></tr></table><table><tr><td width=\"50\" height=\"50\"></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-NORTH\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Maps/north-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\"></td></tr><tr><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-WEST\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Maps/west-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/STOP\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Very_Basic/cancel_filled-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-EAST\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Maps/east-25.png\" width=\"26\" height=\"26\"></a></td></tr><tr><td width=\"50\" height=\"50\"></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-SOUTH\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Maps/south-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\"></td></tr></table>");
  } else if (motorStatus == 1) {
    client.print("<font color=\"red\">GOING FORWARD</font></br>");
    client.println("<table><tr><td width=\"50\" height=\"50\"></td><td width=\"50\" height=\"50\" align=\"middle\"><a href=\"/GO-FORWARD\"><img src=\"https://maxcdn.icons8.com/windows8/PNG/26/Arrows/up_arrow-26.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\"></td></tr><tr><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-LEFT\"><img src=\"https://maxcdn.icons8.com/windows8/PNG/26/Arrows/left_arrow-26.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/STOP\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Very_Basic/cancel_filled-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-RIGHT\"><img src=\"https://maxcdn.icons8.com/windows8/PNG/26/Arrows/right_arrow-26.png\" width=\"26\" height=\"26\"></a></td></tr><tr><td width=\"50\" height=\"50\"></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-BACKWARD\"><img src=\"https://maxcdn.icons8.com/windows8/PNG/26/Arrows/down_arrow-26.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\"></td></tr></table><table><tr><td width=\"50\" height=\"50\"></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-NORTH\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Maps/north-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\"></td></tr><tr><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-WEST\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Maps/west-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/STOP\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Very_Basic/cancel_filled-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-EAST\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Maps/east-25.png\" width=\"26\" height=\"26\"></a></td></tr><tr><td width=\"50\" height=\"50\"></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-SOUTH\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Maps/south-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\"></td></tr></table>");
  } else if (motorStatus == 2) {
    client.print("<font color=\"red\">GOING BACKWARD</font></br>");
    client.println("<table><tr><td width=\"50\" height=\"50\"></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-FORWARD\"><img src=\"https://maxcdn.icons8.com/windows8/PNG/26/Arrows/up_arrow-26.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\"></td></tr><tr><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-LEFT\"><img src=\"https://maxcdn.icons8.com/windows8/PNG/26/Arrows/left_arrow-26.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/STOP\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Very_Basic/cancel_filled-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-RIGHT\"><img src=\"https://maxcdn.icons8.com/windows8/PNG/26/Arrows/right_arrow-26.png\" width=\"26\" height=\"26\"></a></td></tr><tr><td width=\"50\" height=\"50\"></td><td width=\"50\" height=\"50\" align=\"middle\"><a href=\"/GO-BACKWARD\"><img src=\"https://maxcdn.icons8.com/windows8/PNG/26/Arrows/down_arrow-26.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\"></td></tr></table><table><tr><td width=\"50\" height=\"50\"></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-NORTH\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Maps/north-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\"></td></tr><tr><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-WEST\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Maps/west-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/STOP\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Very_Basic/cancel_filled-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-EAST\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Maps/east-25.png\" width=\"26\" height=\"26\"></a></td></tr><tr><td width=\"50\" height=\"50\"></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-SOUTH\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Maps/south-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\"></td></tr></table>");
  } else if (motorStatus == 3) {
    client.print("<font color=\"red\">GOING LEFT</font></br>");
    client.println("<table><tr><td width=\"50\" height=\"50\"></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-FORWARD\"><img src=\"https://maxcdn.icons8.com/windows8/PNG/26/Arrows/up_arrow-26.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\"></td></tr><tr><td width=\"50\" height=\"50\" align=\"middle\"><a href=\"/GO-LEFT\"><img src=\"https://maxcdn.icons8.com/windows8/PNG/26/Arrows/left_arrow-26.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/STOP\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Very_Basic/cancel_filled-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-RIGHT\"><img src=\"https://maxcdn.icons8.com/windows8/PNG/26/Arrows/right_arrow-26.png\" width=\"26\" height=\"26\"></a></td></tr><tr><td width=\"50\" height=\"50\"></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-BACKWARD\"><img src=\"https://maxcdn.icons8.com/windows8/PNG/26/Arrows/down_arrow-26.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\"></td></tr></table><table><tr><td width=\"50\" height=\"50\"></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-NORTH\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Maps/north-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\"></td></tr><tr><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-WEST\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Maps/west-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/STOP\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Very_Basic/cancel_filled-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-EAST\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Maps/east-25.png\" width=\"26\" height=\"26\"></a></td></tr><tr><td width=\"50\" height=\"50\"></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-SOUTH\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Maps/south-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\"></td></tr></table>");
  } else if (motorStatus == 4) {
    client.print("<font color=\"red\">GOING RIGHT</font></br>");
    client.println("<table><tr><td width=\"50\" height=\"50\"></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-FORWARD\"><img src=\"https://maxcdn.icons8.com/windows8/PNG/26/Arrows/up_arrow-26.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\"></td></tr><tr><td width=\"50\" height=\"50\" align=\"middle\"><a href=\"/GO-LEFT\"><img src=\"https://maxcdn.icons8.com/windows8/PNG/26/Arrows/left_arrow-26.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/STOP\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Very_Basic/cancel_filled-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-RIGHT\"><img src=\"https://maxcdn.icons8.com/windows8/PNG/26/Arrows/right_arrow-26.png\" width=\"26\" height=\"26\"></a></td></tr><tr><td width=\"50\" height=\"50\"></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-BACKWARD\"><img src=\"https://maxcdn.icons8.com/windows8/PNG/26/Arrows/down_arrow-26.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\"></td></tr></table><table><tr><td width=\"50\" height=\"50\"></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-NORTH\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Maps/north-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\"></td></tr><tr><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-WEST\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Maps/west-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/STOP\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Very_Basic/cancel_filled-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-EAST\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Maps/east-25.png\" width=\"26\" height=\"26\"></a></td></tr><tr><td width=\"50\" height=\"50\"></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-SOUTH\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Maps/south-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\"></td></tr></table>");
  } else if (motorStatus == 5) {
    client.print("<font color=\"red\">GOING NORTH</font></br>");
    client.println("<table><tr><td width=\"50\" height=\"50\"></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-FORWARD\"><img src=\"https://maxcdn.icons8.com/windows8/PNG/26/Arrows/up_arrow-26.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\"></td></tr><tr><td width=\"50\" height=\"50\" align=\"middle\"><a href=\"/GO-LEFT\"><img src=\"https://maxcdn.icons8.com/windows8/PNG/26/Arrows/left_arrow-26.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/STOP\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Very_Basic/cancel_filled-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-RIGHT\"><img src=\"https://maxcdn.icons8.com/windows8/PNG/26/Arrows/right_arrow-26.png\" width=\"26\" height=\"26\"></a></td></tr><tr><td width=\"50\" height=\"50\"></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-BACKWARD\"><img src=\"https://maxcdn.icons8.com/windows8/PNG/26/Arrows/down_arrow-26.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\"></td></tr></table><table><tr><td width=\"50\" height=\"50\"></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-NORTH\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Maps/north-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\"></td></tr><tr><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-WEST\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Maps/west-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/STOP\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Very_Basic/cancel_filled-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-EAST\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Maps/east-25.png\" width=\"26\" height=\"26\"></a></td></tr><tr><td width=\"50\" height=\"50\"></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-SOUTH\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Maps/south-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\"></td></tr></table>");
  } else if (motorStatus == 6) {
    client.print("<font color=\"red\">GOING SOUTH</font></br>");
    client.println("<table><tr><td width=\"50\" height=\"50\"></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-FORWARD\"><img src=\"https://maxcdn.icons8.com/windows8/PNG/26/Arrows/up_arrow-26.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\"></td></tr><tr><td width=\"50\" height=\"50\" align=\"middle\"><a href=\"/GO-LEFT\"><img src=\"https://maxcdn.icons8.com/windows8/PNG/26/Arrows/left_arrow-26.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/STOP\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Very_Basic/cancel_filled-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-RIGHT\"><img src=\"https://maxcdn.icons8.com/windows8/PNG/26/Arrows/right_arrow-26.png\" width=\"26\" height=\"26\"></a></td></tr><tr><td width=\"50\" height=\"50\"></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-BACKWARD\"><img src=\"https://maxcdn.icons8.com/windows8/PNG/26/Arrows/down_arrow-26.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\"></td></tr></table><table><tr><td width=\"50\" height=\"50\"></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-NORTH\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Maps/north-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\"></td></tr><tr><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-WEST\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Maps/west-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/STOP\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Very_Basic/cancel_filled-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-EAST\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Maps/east-25.png\" width=\"26\" height=\"26\"></a></td></tr><tr><td width=\"50\" height=\"50\"></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-SOUTH\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Maps/south-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\"></td></tr></table>");
  } else if (motorStatus == 7) {
    client.print("<font color=\"red\">GOING EAST</font></br>");
    client.println("<table><tr><td width=\"50\" height=\"50\"></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-FORWARD\"><img src=\"https://maxcdn.icons8.com/windows8/PNG/26/Arrows/up_arrow-26.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\"></td></tr><tr><td width=\"50\" height=\"50\" align=\"middle\"><a href=\"/GO-LEFT\"><img src=\"https://maxcdn.icons8.com/windows8/PNG/26/Arrows/left_arrow-26.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/STOP\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Very_Basic/cancel_filled-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-RIGHT\"><img src=\"https://maxcdn.icons8.com/windows8/PNG/26/Arrows/right_arrow-26.png\" width=\"26\" height=\"26\"></a></td></tr><tr><td width=\"50\" height=\"50\"></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-BACKWARD\"><img src=\"https://maxcdn.icons8.com/windows8/PNG/26/Arrows/down_arrow-26.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\"></td></tr></table><table><tr><td width=\"50\" height=\"50\"></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-NORTH\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Maps/north-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\"></td></tr><tr><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-WEST\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Maps/west-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/STOP\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Very_Basic/cancel_filled-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-EAST\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Maps/east-25.png\" width=\"26\" height=\"26\"></a></td></tr><tr><td width=\"50\" height=\"50\"></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-SOUTH\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Maps/south-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\"></td></tr></table>");
  } else if (motorStatus == 8) {
    client.print("<font color=\"red\">GOING WEST</font></br>");
    client.println("<table><tr><td width=\"50\" height=\"50\"></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-FORWARD\"><img src=\"https://maxcdn.icons8.com/windows8/PNG/26/Arrows/up_arrow-26.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\"></td></tr><tr><td width=\"50\" height=\"50\" align=\"middle\"><a href=\"/GO-LEFT\"><img src=\"https://maxcdn.icons8.com/windows8/PNG/26/Arrows/left_arrow-26.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/STOP\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Very_Basic/cancel_filled-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-RIGHT\"><img src=\"https://maxcdn.icons8.com/windows8/PNG/26/Arrows/right_arrow-26.png\" width=\"26\" height=\"26\"></a></td></tr><tr><td width=\"50\" height=\"50\"></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-BACKWARD\"><img src=\"https://maxcdn.icons8.com/windows8/PNG/26/Arrows/down_arrow-26.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\"></td></tr></table><table><tr><td width=\"50\" height=\"50\"></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-NORTH\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Maps/north-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\"></td></tr><tr><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-WEST\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Maps/west-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/STOP\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Very_Basic/cancel_filled-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-EAST\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Maps/east-25.png\" width=\"26\" height=\"26\"></a></td></tr><tr><td width=\"50\" height=\"50\"></td><td width=\"50\" height=\"50\" align=\"middle\" ><a href=\"/GO-SOUTH\"><img src=\"https://maxcdn.icons8.com/iOS7/PNG/25/Maps/south-25.png\" width=\"26\" height=\"26\"></a></td><td width=\"50\" height=\"50\"></td></tr></table>");
  }

  client.println("</br>");
  client.println("</body>");
  client.println("</html>");

  delay(1);
  Serial.println("User disconnected");
  Serial.println("");
}
