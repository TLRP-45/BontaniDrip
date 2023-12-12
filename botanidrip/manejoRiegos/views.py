from django.shortcuts import render
from manejoRiegos.models import RiegoPrueba
from django.http import HttpResponse

# Create your views here.
def home(request):
    salida = "["
    for r in RiegoPrueba.objects.all():
        salida+="{"
        salida+=f"\"riegoid\":{r.riegoid}, \"fecha\": \"{r.fecha}\", \"hora\": \"{r.hora}\", \"automatico\": {int(r.automatico)}"
        salida+="},"
    salida = salida[:-1] + "]"
    
    
    return HttpResponse(salida)

