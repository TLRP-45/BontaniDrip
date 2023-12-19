from manejoRiegos.models import RiegoPrueba
from django.http import HttpResponse
from manejoRiegos.modules import *
import threading as th
import datetime

# Threads
def regadoManual():
    init_components()
    regadoHastaHumedo()

def comprobarHumedad():
    init_sensor()
    

def getNewID():
    try:
        id = RiegoPrueba.objects.last().riegoid
        return (id * 2) + id % 3
    except:
        return 1

def getHour():
    return datetime.datetime.now().strftime('%H:%M')

def getDate():
    return datetime.datetime.now().strftime('%Y-%m-%d')




# Create your views here.
def Pruebas(request):
    return HttpResponse('AGREGADO')

def getRiegos(request):
    salida = "["
    for r in RiegoPrueba.objects.all():
        salida+="{"
        salida+=f"\"riegoid\":{r.riegoid}, \"fecha\": \"{r.fecha}\", \"hora\": \"{r.hora}\", \"automatico\": {int(r.automatico)}"
        salida+="},"
    salida = salida[:-1] + "]"
    return HttpResponse(salida)


def accionarRegado(request):
    try:
        hilo = th.Thread(name="riiego",target=regadoManual)
        
        hilo.start()
        r = RiegoPrueba(getNewID(),getDate(),getHour(),False)
        r.save()

    except:
        return HttpResponse(-1)
    
    return HttpResponse(1)


def obtEstadoAgua(request):
    try:
        hilo = th.Thread(name="compruena",target=comprobarHumedad)
        hilo.start()
    except:
        return HttpResponse(-1)
    return HttpResponse(GPIO.input(SENSOR_AGUA))


    
    

