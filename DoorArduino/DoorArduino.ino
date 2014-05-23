
#define PIN 13

void setup() {
  // initialize serial communication:
  Serial.begin(9600);
   // initialize the LED pins:
      
        pinMode(PIN, OUTPUT);
      
}

void loop() {
  // read the sensor:
  if (Serial.available() > 0) {
    int inByte = Serial.read();
    // do something different depending on the character received.  
    // The switch statement expects single number values for each case;
    // in this exmaple, though, you're using single quotes to tell
    // the controller to get the ASCII value for the character.  For
    // example 'a' = 97, 'b' = 98, and so forth:

     if(inByte == 'a'){
        digitalWrite(PIN, HIGH);
        delay(100);
        digitalWrite(PIN, LOW);
    }
  }
}
