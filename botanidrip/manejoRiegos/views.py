from django.shortcuts import render
from manejoRiegos.models import RiegoPrueba
from django.http import HttpResponse
from manejoRiegos.modules import *
import threading as th

# Create your views here.
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
        hilo = th.Thread(regadoManual)
        hilo.start()
    except:
        return HttpResponse(-1)
    
    return HttpResponse(1)


def regadoManual():
    init_components()
    regadoHastaHumedo()
    
    

