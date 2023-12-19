import RPi.GPIO as GPIO
import time
PIN_WATER_PUMP = 21
PIN_SENSOR = 21

# Set mode to BOARD | Poner el modo en BOARD
GPIO.setmode(GPIO.BOARD)

# I'll modify defaults | Modificare los predeterminados
GPIO.setwarnings(False)

# Initializer for components using pins | Inicializador 
def init_pin_component(pin):
    GPIO.setup(pin, GPIO.OUT)
    GPIO.output(pin, LOW)
    GPIO.output(pin, HIGH)
    
# Check water status | Revisar el estado del agua 
def check_status(pin = PIN_SENSOR):
    GPIO.setup(pin, GPIO.IN)
    return(GPIO.input(pin))
    
# Start water pump | Iniciar la bomba de agua
def start_water_pump(PIN_WATER_PUMP):
    init_pin_component(PIN_WATER_PUMP)
    # Aqui va el la insercion SQL a la Base de Datos
    GPIO.output(PIN_WATER_PUMP, LOW)
    time.sleep(1)
    GPIO.output(PIN_WATER_PUMP, HIGH)

# Automatization of the process | Automatizacion del proceso
def automatization(PIN_WATER_PUMP, PIN_SENSOR):
    water_count = 0
    init_pin_component(PIN_WATER_PUMP)
    while True and water_count < 10:
        time.sleep(5)
        ok = check_status(PIN_SENSOR) == 0
        if not ok:
            if water_count < 5:
                start_water_pump(PIN_WATER_PUMP)
            water_count = water_count + 1
        else:
            water_count = 0
            
            
