import rpi_botanidrip
import psutil
import os

def check_water_plant():
    stats = rpi_botanidrip.check_status()
    msg = ""
    
    if stats == 1:
        msg = "Nivel de humedad bajo, favor de regar."
    
    else:
        msg = "Nivel de humedad normal, no se requiere tomar acción"
    

def manual_watering():
    rpi_botanidrip.start_water_pump()
    
def automatic(procced):
    execute = False
   
    if procced == True:
        for process in psutil.process_iter():
            try:
                if process.cmdline()[1] == 'automatic_water.py':
                    execute = True
            except:
                pass
            
        if not execute:
            os.system("python3 automatic_water.py")
    else:
        os.system("pkill -f rpi_botanidrip.py")
           
   