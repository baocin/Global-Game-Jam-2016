/*
TrinketKeyboard example
For Trinket by Adafruit Industries
*/

#include <TrinketKeyboard.h>

#define PIN_BUTTON_LEFT 0
#define PIN_BUTTON_RIGHT 2

#define DEBOUNCE 3AA00

void setup()
{
  // button pins as inputs
  pinMode(PIN_BUTTON_LEFT, INPUT);
  pinMode(PIN_BUTTON_RIGHT, INPUT);

  // setting input pins to high means turning on internal pull-up resistors
  //digitalWrite(PIN_BUTTON_LEFT, HIGH);
  //digitalWrite(PIN_BUTTON_RIGHT, HIGH);
  // remember, the buttons are active-low, they read LOW when they are not pressed

  // start USB stuff
  TrinketKeyboard.begin();
}

boolean leftPressed = true;
boolean lastLeft = false;
boolean leftDebounceTime = 0;

boolean rightPressed = true;
boolean lastRight = false;
boolean rightDebounceTime = 0;
void loop()
{
  // the poll function must be called at least once every 10 ms
  // or cause a keystroke
  // if it is not, then the computer may think that the device
  // has stopped working, and give errors
  TrinketKeyboard.poll();
  
  boolean currLeft = (digitalRead(PIN_BUTTON_LEFT) == HIGH);
  if (currLeft != lastLeft) {
    leftDebounceTime = millis();
  }
  
  if ((millis() - leftDebounceTime) > DEBOUNCE) {
    if (currLeft != leftPressed) {
      leftPressed = currLeft;
      
      if (leftPressed == true) {
        // this should type a capital A
        TrinketKeyboard.pressKey(KEYCODE_MOD_LEFT_SHIFT, KEYCODE_A);
        // this releases the key (otherwise it is held down!)
        TrinketKeyboard.pressKey(0, 0);
      }
    }
  }
  
  lastLeft = currLeft;
  
  // RIGHT BUTTON
  
  boolean currRight = (digitalRead(PIN_BUTTON_RIGHT) == HIGH);
  if (currRight != lastRight) {
    rightDebounceTime = millis();
  }
  
  if ((millis() - rightDebounceTime) > DEBOUNCE) {
    if (currRight != rightPressed) {
      rightPressed = currRight;
      
      if (rightPressed == true) {
        // this should type a capital A
        TrinketKeyboard.pressKey(KEYCODE_MOD_RIGHT_SHIFT, KEYCODE_D);
        // this releases the key (otherwise it is held down!)
        TrinketKeyboard.pressKey(0, 0);
      }
    }
  }
  
  lastRight = currRight;
}
