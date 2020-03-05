#include <SPI.h>
#include <Wire.h>
#include "Arduino.h"
#include <Adafruit_GFX.h>
#include <Adafruit_SSD1306.h>
#include "RTClib.h"
#include <SoftwareSerial.h>
//#include <Serial.h>
#include "HX711.h"
#include <time.h>


#define SCREEN_WIDTH 128
#define SCREEN_HEIGHT 64
#define OLED_RESET -1
#define OUTPUT 10 // change to be correct

SoftwareSerial btm(0,1); 
int index = 0;
// stores the data coming into arduino
char data[8];
char character;
int weight;
int hours;
int minutes;
int breakFreq;
boolean cancel; // true if cancelling, false else
boolean received = false; // if something has been received
// Store the input data in chars of positions 1-6, respecitvely
char a;
char b;
char c;
char d;
char e;
char f;




const int scalesckpin = 2;
const int scaleoutpin = 3;

Adafruit_SSD1306 display(SCREEN_WIDTH, SCREEN_HEIGHT, &Wire, OLED_RESET);
RTC_DS3231 rtc;
SoftwareSerial btmodule(0, 1);
HX711 scale;



void setup() {
  Serial.begin(9600);
  btm.begin(9600);
  display.begin(SSD1306_SWITCHCAPVCC, 0x3C);
  //display.display();
  delay(2000);
  display.clearDisplay();
  rtc.begin();
  rtc.adjust(DateTime(F(__DATE__), F(__TIME__)));
  display.setTextColor(WHITE);
  display.setTextSize(2);
  scale.set_scale(2280.f);
  scale.tare();
  Serial.print("Hello");
  


}

/*String formatTime(DateTime curTime) {
  return (curTime.hour() + ":" + curTime.minute()+ ":" + curTime.second());
}
*/
void displayCurTime() {
  DateTime now = rtc.now();
  display.clearDisplay();
  display.setCursor(0, 0);
  display.print("Time:");
  display.setCursor(0, 20);
  display.print(now.hour() + ':' + now.minute() + ':' + now.second() + ':');
  display.display();
  delay(1000);
}

void processInput(){
  char command = data[0];
  switch (command){
    case 'W':
      a = data[1];
      b = data[2];
      c = data[3];
      char nums[3] = {a, b, c};
      sscanf(nums, "%d", &weight);
      break;
    case 'T':
      a = data[1];
      b = data[2];
      char hrs[2] = {a, b};
      sscanf(hrs, "%d", &hours);
      c = data[3];
      d = data[4];
      char mins[2] = {c, d};
      sscanf(mins, "%d", &minutes);
      e = data[5];
      f = data[6];
      char breaks[2] = {e, f};
      sscanf(breaks, "%d", &breakFreq);
      break;
    case 'C':
      cancel = true; //timer was cancelled reset to current time (TODO - cancel method);
      break;
  }
}
void loop() {
  displayCurTime();
  DateTime now = rtc.now();
 /* if (btm.available()) {
     while (btm.available()){
        character = btm.read();
        delay(10);
        data[index] = character;
        index++;
     }
     data[index] = '\0';
     received = true;
  }
  */
  //data = {'T', '0', '3', '2', '0', '1', '5', '\0'};
  data[0] = 'T';
  data[1] = '0';
  data[2] = '3';
  data[3] = '2';
  data[4] = '0';
  data[5] = '1';
  data[6] = '5';
  data[7] = '\0';
  received = true;
  if (received){
     processInput();
     received = false;
     index = 0;
     data[0] = '\0';
  }
  else {
     data[0] = 'N';
  }
  



  if (data[0] == '\0') {
    long startTime = now.unixtime(); 
    display.clearDisplay();
    display.setTextSize(2);
    display.setCursor(0,0);
    display.print("Timer:");
    
    //int weight = (int) scale.get_units(10);
    int timelimit = (hours * 60) + minutes; 
    //int timelimit = data2;
    long endTime = startTime + (timelimit*60);
    int hours = timelimit / 60;
    int minutes = timelimit % 60;
    long currentTime = now.unixtime();
    minutes = 5;
    hours = 5;
    Serial.print("Hours: " +hours);
    Serial.print("Minutes: " +minutes);
    display.setCursor(0, 20);
    display.print(hours);
    display.print(":");
    display.print(minutes);
    display.display();
    long remainingTime;
    
    while (currentTime < endTime) {
      delay(1000);
      //if ((int) scale.get_units(10) > weight *.85 || (int) scale.get_units(10) < weight * 1.15) {
        
        DateTime now = rtc.now();
        currentTime = now.unixtime();
        Serial.println("starttime = ");
        Serial.println(startTime, DEC);
        Serial.println("current time = ");
        Serial.println(currentTime, DEC);
        remainingTime = (endTime - currentTime);
        int remainingHours = remainingTime / 3600;
        int remainingMinutes = (remainingTime / 60) - (remainingHours * 60);
        Serial.println("remaining minutes" + remainingMinutes);
        Serial.println("remaining hours" + remainingHours);
        display.clearDisplay();
        display.setTextSize(2);
        display.setCursor(0,0);
        display.print("Timer:");
        display.setCursor(0, 20);
        display.print(remainingHours);
        display.print(":");
        display.print(remainingMinutes);
        display.display(); 
      //}   
      /*else {
          remainingTime = (endTime - currentTime);
          DateTime now = rtc.now();
          currentTime = now.unixtime();
          endTime = currentTime+remainingTime;


       
        }
    }*/
    }

    


    
  }

  

}
