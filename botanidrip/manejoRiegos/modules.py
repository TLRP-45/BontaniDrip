#import RPi.GPIO as GPIO
import time



SENSOR_AGUA=26
RELAY=21
CONDICION = False
SEGUNDOS = 3


def init_components():
    GPIO.setmode(GPIO.BCM)
    GPIO.setup(SENSOR_AGUA, GPIO.IN)
    GPIO.setup(RELAY, GPIO.OUT)

def regadoHastaHumedo(): 
    while GPIO.input(SENSOR_AGUA)==GPIO.HIGH: 
        GPIO.output(RELAY, GPIO.LOW) 
        print("Regando") 
    print("Regado!")
    GPIO.cleanup()

def regadoConTiempo(segundos): 
    print("Regando...") 
    GPIO.output(RELAY,GPIO.LOW)
    time.sleep(segundos)
    GPIO.cleanup()



