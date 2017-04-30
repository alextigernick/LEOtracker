
/*
  DigitalReadSerial
 Reads a digital input on pin 2, prints the result to the serial monitor

 This example code is in the public domain.
 */

#define Y_STEP_PIN         60
#define Y_DIR_PIN          61
#define Y_ENABLE_PIN       56

#define X_STEP_PIN         54
#define X_DIR_PIN          55
#define X_ENABLE_PIN       38
// the setup routine runs once when you press reset:


void enableA(){
  digitalWrite(Y_ENABLE_PIN, LOW); 
}
void disableA(){
  digitalWrite(Y_ENABLE_PIN, HIGH); 
}

void pulseA(){
  digitalWrite(Y_STEP_PIN, HIGH);
  delayMicroseconds(1000);
  digitalWrite(Y_STEP_PIN, LOW);
  delayMicroseconds(900);
  
}

void enableB(){
  digitalWrite(X_ENABLE_PIN, LOW); 
}
void disableB(){
  digitalWrite(X_ENABLE_PIN, HIGH); 
}

void pulseB(){
  digitalWrite(X_STEP_PIN, HIGH);
  delayMicroseconds(800);
  digitalWrite(X_STEP_PIN, LOW);
  delayMicroseconds(600 );
  
}




void setup() {
  // initialize serial communication at 9600 bits per second:
  //Serial.begin(9600);
  // make the pushbutton's pin an input
  Serial.begin(9600);
  pinMode(Y_ENABLE_PIN, OUTPUT);
  pinMode(Y_DIR_PIN, OUTPUT);
  pinMode(Y_STEP_PIN, OUTPUT);

  digitalWrite(Y_DIR_PIN, HIGH); 
  enableA();
  
  pinMode(X_ENABLE_PIN, OUTPUT);
  pinMode(X_DIR_PIN, OUTPUT);
  pinMode(X_STEP_PIN, OUTPUT);

  digitalWrite(X_DIR_PIN, HIGH); 
  enableB();
   }


// the loop routine runs over and over again forever:
void loop() {
  long s = Serial.parseInt();
  Serial.println(s);
  int a = s%10;
  s = s/10;
  int b = s%10;
 
  s = s/10;
  int c = s%10;
  
  s = s/10;
  int d = s%10;
  
  s = s/10;
  int e = s%10;
  // read the input pin:
  //int buttonState = digitalRead(pushButton);
  // print out the state of the button:
  //Serial.println(buttonState);
  //delay(1);
  
  if(e==1){
    digitalWrite(X_DIR_PIN, HIGH);
    }   
  else{
    digitalWrite(X_DIR_PIN, LOW);
    }
    
  if(c==1){
    digitalWrite(Y_DIR_PIN, HIGH);
    }   
  else{
    digitalWrite(Y_DIR_PIN, LOW);
    }
  int y = pow(2.2,b); 
  int x = pow(2.2,d);
  Serial.println(x);
  
  Serial.println(y);
  int dex = 0;
  while(dex<x||dex<y){
    dex++;
    if(dex<x){
      pulseB();}
    if(dex<y){
      pulseA();}
  }
  Serial.println("done");
}





