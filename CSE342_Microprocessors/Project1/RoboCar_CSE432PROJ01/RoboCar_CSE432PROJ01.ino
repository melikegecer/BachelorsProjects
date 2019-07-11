#include <LSM303.h>
#include <NewPing.h>
#include <Wire.h>
#include <SoftwareSerial.h>

//----------
//Motor pins
//----------
#define DIR_MOTOR_A 12
#define DIR_MOTOR_B 13
#define SPEED_MOTOR_A 3
#define SPEED_MOTOR_B 11
#define BRAKE_A 9
#define BRAKE_B 8

//------------
//Encoder pins
//------------
#define OUT_A_PIN 2
#define OUT_B_PIN 4

//----------------------
//Ultrasonic sensor pins
//----------------------
#define US_TRIGGER_PIN 7
#define US_ECHO_PIN 6

#define TX_PIN 10
#define RX_PIN 5

SoftwareSerial ArduinoSerial(TX_PIN, RX_PIN);
NewPing sonar = NewPing(US_TRIGGER_PIN, US_ECHO_PIN, 200);
LSM303 compass;

int command;

//----------------------------------
// setup()
//----------------------------------
void setup() {
  Serial.begin(9600);
  //Setup Channel A
  pinMode(DIR_MOTOR_A, OUTPUT); //Initiates Motor Channel A pin
  pinMode(BRAKE_A, OUTPUT); //Initiates Brake Channel A pin

  //Setup Channel B
  pinMode(DIR_MOTOR_B, OUTPUT); //Initiates Motor Channel B pin
  pinMode(BRAKE_B, OUTPUT);  //Initiates Brake Channel B pin

  //Setup the LSM303D compass
  Wire.begin();
  compass.init();
  compass.enableDefault();
  compass.m_min = (LSM303::vector<int16_t>) {
    -2156,  -4016,  -5000
  };
  compass.m_max = (LSM303::vector<int16_t>) {
    +659,   +114,   -977
  };

  //Setup the connection with NodeMCU
  ArduinoSerial.begin(4800);
}

//----------------------------------
// loop()
//----------------------------------
void loop() {

  //--Taking direction commands from NodeMCU--
  if (ArduinoSerial.available() > 0) {
    command = ArduinoSerial.parseInt();
    command = command % 10;
    Serial.print("Command = ");
    Serial.println(command);
  }
  //------------------------------------------
  
  //--Ultrasonic Sensor--
  float sensorDelay = sonar.ping();
  float distance = ((float) (sensorDelay / 29 / 2));
  //---------------------

  //--Giving the appropriate directions to RoboCar--
  if (command == 0) { //STOP
    digitalWrite(BRAKE_A, HIGH);
    analogWrite(SPEED_MOTOR_A, 0);
    digitalWrite(BRAKE_B, HIGH);
    analogWrite(SPEED_MOTOR_B, 0);
  }
  else if (command == 1 && (distance < 2 || distance > 20)) { //FORWARD
    Serial.println("RedBot is going forward.");
    digitalWrite(DIR_MOTOR_A, HIGH);
    digitalWrite(BRAKE_A, LOW);
    analogWrite(SPEED_MOTOR_A, 220);
    digitalWrite(DIR_MOTOR_B, HIGH);
    digitalWrite(BRAKE_B, LOW);
    analogWrite(SPEED_MOTOR_B, 255);
  }
  else if (command == 2 && (distance < 2 || distance > 20)) { //BACKWARDS
    Serial.println("RedBot is going backwards.");
    digitalWrite(DIR_MOTOR_A, LOW);
    digitalWrite(BRAKE_A, LOW);
    analogWrite(SPEED_MOTOR_A, 216);
    digitalWrite(DIR_MOTOR_B,  LOW);
    digitalWrite(BRAKE_B, LOW);
    analogWrite(SPEED_MOTOR_B, 255);
  }
  else if (command == 3 && (distance < 2 || distance > 20)) { //LEFTWARDS
   Serial.println("RedBot is going forward.");
    digitalWrite(DIR_MOTOR_A, LOW);
    digitalWrite(BRAKE_A, LOW);
    analogWrite(SPEED_MOTOR_A, 150);
    digitalWrite(DIR_MOTOR_B,  HIGH);
    digitalWrite(BRAKE_B, LOW);
    analogWrite(SPEED_MOTOR_B, 255);
  }
  else if (command == 4 && (distance < 2 || distance > 20)) { //RIGHTWARDS
    digitalWrite(DIR_MOTOR_A, HIGH);
    digitalWrite(BRAKE_A, LOW);
    analogWrite(SPEED_MOTOR_A, 150);
    digitalWrite(DIR_MOTOR_B,  LOW);
    digitalWrite(BRAKE_B, LOW);
    analogWrite(SPEED_MOTOR_B, 255);
  }
  
  else if (command == 5) { //GO NORTH
    compass.read();
    float heading = compass.heading();
    while (heading > 45 && heading < 315) {
      digitalWrite(DIR_MOTOR_A, LOW);
      digitalWrite(BRAKE_A, LOW);
      analogWrite(SPEED_MOTOR_A, 150);
      digitalWrite(DIR_MOTOR_B,  HIGH);
      digitalWrite(BRAKE_B, LOW);
      analogWrite(SPEED_MOTOR_B, 255);
      compass.read();
      heading = compass.heading();
      Serial.println(heading);
    }
    command = 1; //to go straight after rotating
  }
  else if (command == 6) { //GO SOUTH

  }
  else if (command == 7) { //GO EAST

  }
  else if (command == 8) { // GO WEST

  }
  else{
    digitalWrite(BRAKE_A, HIGH);
    analogWrite(SPEED_MOTOR_A, 0);
    digitalWrite(BRAKE_B, HIGH);
    analogWrite(SPEED_MOTOR_B, 0);
  }
  
  //------------------------------------------------
  
  //--Encoder--
  //long l  = encoder.getTicks(LEFT);
  //Serial.println(l);
  //long r = encoder.getTicks(RIGHT);
  //Serial.println(r);
  //-----------


}
