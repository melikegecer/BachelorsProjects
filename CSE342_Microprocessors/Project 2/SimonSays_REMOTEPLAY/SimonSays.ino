#include <EEPROM.h>
#include <Event.h>
#include <Timer.h>
#include <LiquidCrystal.h>
#include <SPI.h>
#include <SoftwareSerial.h>
SoftwareSerial mySerial(0, 1);

// ################
// # PIN MAPPINGS #
// ################
#define button_blue 8
#define button_green 4
#define button_red 2
#define button_yellow 7
#define buzzer 10
#define led_red 6
#define led_green 3
#define led_blue 12
#define led_yellow 5


// ##################
// # initialize LCD #
// ##################
LiquidCrystal lcd(9);


// ##############
// # PIN states #
// ##############
int blue_button_state;
int green_button_state;
int red_button_state;
int yellow_button_state;


// #####################
// # MENU navigation    #
// #                    #
// # 0 - Main Menu      #
// # 1 - Gameplay       #
// # 2 - Winning screen #
// # 3 - Losing screen  #
// ######################
int menuNumber = 0;


// ##############
// # Difficulty #
// ##############
int level = 1;


// ##########################
// # Number of combinations #
// # in a round             #
// ##########################
int num_combinations = 3;

// ######################
// # Levels 1, 2, and 3 #
// ######################
int combinations_level_1 [3];
int combinations_level_2 [5];
int combinations_level_3 [7];


// #################
// # Winner sounds #
// #################
int melody[] = {262, 196, 196, 220, 196, 0, 247, 262};


// ###################
// # Note durations  #
// #                 #
// # 4: quarter note #
// # 8: eigth note   #
// ###################
int noteDurations[] = {4, 8, 8, 4, 4, 4, 4, 4 };


// ######################
// # HI-SCORE           #
// # (read from EEPROM) #
// ######################
int highScore;


// #################
// # Current score #
// #################
int score = 0;


// ##########################
// # Pointer to read inputs #
// # from push buttons      #
// ##########################
int ledCursor = 0;


// #################################
// # Declare Timer object          #
// # and its events for interrupts #
// #################################
Timer timer;
int countdown_event;
int time_out_event;


// ######################
// # Countdown starting #
// # from 10            #
// ######################
int countdown_time = 10;


// ###########################
// # Remerber whether or not #
// # the time has run out    #
// ###########################
boolean time_is_up = false;


// ##########################
// # EEPROM address to read #
// # from, and write on     #
// ##########################
int addr = 0;

void setup() {

  // randomSeed is needed to generate random numbers
  randomSeed(analogRead(A0));

  // led pin settings
  pinMode(led_blue, OUTPUT);
  pinMode(led_green, OUTPUT);
  pinMode(led_red, OUTPUT);
  pinMode(led_yellow, OUTPUT);

  // button pin settings
  pinMode(button_blue, INPUT);
  pinMode(button_green, INPUT);
  pinMode(button_red, INPUT);
  pinMode(button_yellow, INPUT);

  // buzzer pin mode setting
  pinMode(buzzer, OUTPUT);

  // initialize LCD
  lcd.begin(16, 2);

  // initialize Serial
  Serial.begin(9600);

  // get high score from EEPROM
  highScore = EEPROM.read(addr) * 10;

  mySerial.begin(9600);
}


void loop() {

  if (menuNumber == 1 ) {
    startGame();
  } else if (menuNumber == 0) {
    showMainMenu();
  } else if (menuNumber == 2) {
    gameOverWinner();
  } else if (menuNumber == 3) {
    gameOverLoser();
  }

}


void showMainMenu() {

  // reset everything
  time_is_up = false;
  ledCursor = 0;
  level = 1; // do not reset level to be 1
  // if you want to allow players
  // to continue from where they
  // left off.
  num_combinations = 3;
  countdown_time = 10;

  // reset lcd
  lcd.clear();
  lcd.setCursor(0, 0);
  lcd.print("Press to start!");
  lcd.setCursor(0, 1);
  lcd.print("Hi-score: ");
  lcd.print(highScore);

  blue_button_state = digitalRead(button_blue);
  green_button_state = digitalRead(button_green);
  red_button_state = digitalRead(button_red);
  yellow_button_state = digitalRead(button_yellow);

  // if player presses any button, game will start
  if (blue_button_state == 0 || green_button_state == 0
      || red_button_state == 0 || yellow_button_state == 0 ) {
    lcd.clear();
    resetButtons();
    lcd.print("Loading...");
    delay(500);
    lcd.clear();
    menuNumber = 1;
  }

}

void startGame() {

  boolean terminate = false;

  if (time_is_up) {
    menuNumber = 3;
  }
  else {
    // initialize and generate combinations randomly
    generateCombinations();

    // clear everything
    resetButtons();
    clearLeds();
    lcd.clear();

    // attach required interrupts to timers
    time_out_event = timer.after(11000, time_ran_out);
    countdown_event = timer.every(1000, countDown);


    // wait for any of the buttons to be pressed, before time runs out
    while (!terminate && !time_is_up) {

      
    int color_code = -1;

      if (mySerial.available() > 0) {
        color_code = mySerial.parseInt();
        if (mySerial.read() == NULL ) {
          Serial.println("NULL");
          continue;
        } else {
          if (mySerial.read() == '\n') {
            //          Serial.print("Choice: ");
            Serial.println(color_code);
          }
        }
      }


      lcd.clear();
      lcd.print("Score: ");
      lcd.print(score);
      lcd.setCursor(0, 1);
      lcd.print("Time Left: ");
      lcd.print(countdown_time);

      timer.update();

      // read buttons' state
      blue_button_state = digitalRead(button_blue);
      green_button_state = digitalRead(button_green);
      red_button_state = digitalRead(button_red);
      yellow_button_state = digitalRead(button_yellow);

      // check which button is pressed
      if (color_code == 2) {
        tone(buzzer, 1023, 500);
        if (level == 1) {
          //          Serial.println("blue button is pressed");
          if (combinations_level_1[ledCursor] == 2) { // if the correct color is blue (blue == 2)
            ledCursor = ledCursor + 1; // set cursor to the next color in the combination
            // to be read
            score = score + 10;
          } else {
            menuNumber = 3;
            terminate = true;
          }
        }
        else if (level == 2) {
          //          Serial.println("blue button is pressed");
          if (combinations_level_2[ledCursor]  == 2) { // if the correct color is blue (blue == 2)
            ledCursor = ledCursor + 1; // set cursor to the next color in the combination
            score = score + 10;
          } else {
            menuNumber = 3;
            terminate = true;
          }
        }
        else if (level == 3) {
          //          Serial.println("blue button is pressed");
          if (combinations_level_3[ledCursor] == 2) { // if the correct color is blue (blue == 2)
            ledCursor = ledCursor + 1; // set cursor to the next color in the combination
            score = score + 10;
          } else {
            menuNumber = 3;
            terminate = true;
          }
        }
      }
      else if (color_code == 1) {
        tone(buzzer, 1023, 500);
        if (level == 1) {
          //          Serial.println("green button is pressed");
          if (combinations_level_1[ledCursor] == 1) { // if the correct color is green (green == 1)
            ledCursor = ledCursor + 1; // set cursor to the next color in the combination
            score = score + 10;
          } else {
            menuNumber = 3;
            terminate = true;
          }
        }
        else if (level == 2) {
          //          Serial.println("green button is pressed");
          if (combinations_level_2[ledCursor] == 1) { // if the correct color is green (green == 1)
            ledCursor = ledCursor + 1; // set cursor to the next color in the combination
            score = score + 10;
          } else {
            menuNumber = 3;
            terminate = true;
          }
        }
        else if (level == 3) {
          //          Serial.println("green button is pressed");
          if (combinations_level_3[ledCursor] == 1) { // if the correct color is green (green == 1)
            ledCursor = ledCursor + 1; // set cursor to the next color in the combination
            score = score + 10;
          } else {
            menuNumber = 3;
            terminate = true;
          }
        }
      }
      else if (color_code == 0) {
        tone(buzzer, 1023, 500);
        if (level == 1) {
          //          Serial.println("red button is pressed");
          if (combinations_level_1[ledCursor] == 0) { // if the correct color is red (red == 0)
            ledCursor = ledCursor + 1; // set cursor to the next color in the combination
            score = score + 10;
          } else {
            menuNumber = 3;
            terminate = true;
          }
        }
        else if (level == 2) {
          //          Serial.println("red button is pressed");
          if (combinations_level_2[ledCursor] == 0) { // if the correct color is red (red == 0)
            ledCursor = ledCursor + 1; // set cursor to the next color in the combination
            score = score + 10;
          } else {
            menuNumber = 3;
            terminate = true;
          }
        }
        else if (level == 3) {
          //          Serial.println("red button is pressed");
          if (combinations_level_3[ledCursor] == 0) { // if the correct color is red (red == 0)
            ledCursor = ledCursor + 1; // set cursor to the next color in the combination
            score = score + 10;
          } else {
            menuNumber = 3;
            terminate = true;
          }
        }
      }
      else if (color_code == 3) {
        tone(buzzer, 1023, 500);
        if (level == 1) {
          //          Serial.println("yellow button is pressed");
          if (combinations_level_1[ledCursor] == 3) { // if the correct color is yellow (yellow == 3)
            ledCursor = ledCursor + 1; // set cursor to the next color in the combination
            score = score + 10;
          } else {
            menuNumber = 3;
            terminate = true;
          }
        }
        else if (level == 2) {
          //          Serial.println("yellow button is pressed");
          if (combinations_level_2[ledCursor] == 3) { // if the correct color is yellow (yellow == 3)
            ledCursor = ledCursor + 1; // set cursor to the next color in the combination
            score = score + 10;
          } else {
            menuNumber = 3;
            terminate = true;
          }
        }
        else if (level == 3) {
          //          Serial.println("yellow button is pressed");
          if (combinations_level_3[ledCursor] == 3) { // if the correct color is yellow (yellow == 3)
            ledCursor = ledCursor + 1; // set cursor to the next color in the combination
            score = score + 10;
          } else {
            menuNumber = 3;
            terminate = true;
          }
        }
      }

      // if cursor has reached the end
      if (ledCursor == num_combinations) {
        menuNumber = 2;
        terminate = true;
      }
      // wait a little bit before reading
      // another input
      delay(150);
    }
    // stop timer events
    timer.stop(countdown_event);
    timer.stop(time_out_event);
  }

}


void countDown() {

  countdown_time--;
  if (countdown_time == 3 || countdown_time == 2 || countdown_time == 1) {
    tone(buzzer, 500, 500);
  }

}


void generateCombinations() {

  lcd.clear();
  lcd.print("Level");
  lcd.setCursor(6, 0);
  lcd.print(level);
  lcd.setCursor(7, 0);
  lcd.print(": ");
  lcd.setCursor(0, 1);
  lcd.print("Get ready...");
  for (int i = 0; i < num_combinations; i++) {
    // generate random number [0, 3]
    // to represent which led should illuminate
    if (level == 1) {
      combinations_level_1[i] = (int) random(0, 4);
      if (combinations_level_1[i] == 0) { // if red
        blinkRed();
        clearLeds();
      } else if (combinations_level_1[i] == 1) { // if green
        blinkGreen();
        clearLeds();
      } else if (combinations_level_1[i] == 2) { // if blue
        blinkBlue();
        clearLeds();
      } else if (combinations_level_1[i] == 3) { // if yellow
        blinkYellow();
        clearLeds();
      }
    }
    else if (level == 2) {
      combinations_level_2[i] = (int) random(0, 4);
      if (combinations_level_2[i] == 0) { // if red
        blinkRed();
        clearLeds();
      } else if (combinations_level_2[i] == 1) { // if green
        blinkGreen();
        clearLeds();
      } else if (combinations_level_2[i] == 2) { // if blue
        blinkBlue();
        clearLeds();
      } else if (combinations_level_2[i] == 3) { // if yellow
        blinkYellow();
        clearLeds();
      }
    }
    else if (level == 3) {
      combinations_level_3[i] = (int) random(0, 4);
      if (combinations_level_3[i] == 0) { // if red
        blinkRed();
        clearLeds();
      } else if (combinations_level_3[i] == 1) { // if green
        blinkGreen();
        clearLeds();
      } else if (combinations_level_3[i] == 2) { // if blue
        blinkBlue();
        clearLeds();
      } else if (combinations_level_3[i] == 3) { // if yellow
        blinkYellow();
        clearLeds();
      }
    }
  }

}


void increase_difficulty() {

  level++;
  if (level == 2) {
    num_combinations = 5;
  }
  else if (level == 3) {
    num_combinations = 7;
  }

}


void gameOverWinner() {

  timer.stop(countdown_event);
  timer.stop(time_out_event);

  if (level == 3) {

    // show game over on LCD
    lcd.clear();
    lcd.print("YOU WON!");
    lcd.setCursor(0, 1);
    lcd.print("Score: ");
    lcd.print(score);
    play_winner_melody();

    int scoreVal = score / 10;
    if (score > highScore) {
      highScore = score;
      EEPROM.write(addr, scoreVal);
    }

    menuNumber = 0;
    score = 0;
    delay(1000);
  }

  else {

    // show game over on LCD
    lcd.clear();
    lcd.print("Stage clear!");
    lcd.setCursor(0, 1);
    lcd.print("Score: ");
    lcd.print(score);
    play_winner_melody();

    int scoreVal = score / 10;
    if (score > highScore) {
      highScore = score;
      EEPROM.write(addr, scoreVal);
    }

    increase_difficulty();
    countdown_time = 10;
    ledCursor = 0;
    menuNumber = 1;
    delay(1000);
  }

}


void gameOverLoser() {

  timer.stop(countdown_event);
  timer.stop(time_out_event);

  // show game over on LCD
  lcd.clear();
  lcd.print("GAME OVER/S: ");
  lcd.print(score);
  lcd.setCursor(0, 1);
  lcd.print("Press to reset");
  play_losing_melody();

  int scoreVal = score / 10;
  if (score > highScore) {
    highScore = score;
    EEPROM.write(addr, scoreVal);
  }

  blue_button_state = digitalRead(button_blue);
  green_button_state = digitalRead(button_green);
  red_button_state = digitalRead(button_red);
  yellow_button_state = digitalRead(button_yellow);

  // if player presses any button, game will restart
  if (blue_button_state == 0 || green_button_state == 0
      || red_button_state == 0 || yellow_button_state == 0 ) {
    lcd.clear();
    resetButtons();
    lcd.print("Loading...");
    delay(500);
    lcd.clear();
    menuNumber = 0;
    score = 0;
    countdown_time = 10;
  }

}


void play_winner_melody() {

  for (int thisNote = 0; thisNote < 8; thisNote++) {
    int noteDuration = 1000 / noteDurations[thisNote];
    tone(buzzer, melody[thisNote], noteDuration);
    int pauseBetweenNotes = noteDuration * 1.30;
    delay(pauseBetweenNotes);
    noTone(buzzer);
  }

}


void play_losing_melody() {

  tone(buzzer, 0, 400);
  delay(4 * 1.30);
  tone(buzzer, 200, 600);
  noTone(buzzer);

}


void time_ran_out() {

  timer.stop(countdown_event);
  timer.stop(time_out_event);
  time_is_up = true;

}


void clearLeds() {

  // turn off the leds
  digitalWrite(led_red, LOW);
  digitalWrite(led_green, LOW);
  digitalWrite(led_blue, LOW);
  digitalWrite(led_yellow, LOW);
  delay(500);

}

void resetButtons() {

  // clear button states
  blue_button_state = 1;
  green_button_state = 1;
  red_button_state = 1;
  yellow_button_state = 1;

}


void blinkRed() {

  // illuminate red led
  digitalWrite(led_red, HIGH);
  delay(500);

}


void blinkBlue() {

  // illuminate blue led
  digitalWrite(led_blue, HIGH);
  delay(500);

}


void blinkGreen() {

  // illuminate green led
  digitalWrite(led_green, HIGH);
  delay(500);

}


void blinkYellow() {

  // illuminate yellow led
  digitalWrite(led_yellow, HIGH);
  delay(500);

}
