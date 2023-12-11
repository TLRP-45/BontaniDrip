from django.shortcuts import render
from manejoRiegos.models import RiegoPrueba
from django.http import HttpResponse

# Create your views here.
def home(request):

    riego1 = RiegoPrueba(riegoid=1,fecha="2023-10-12",hora="",automatico=True)
    riego1.save()


    riego2 = RiegoPrueba(riegoid=2,fecha="2023-10-12",hora="",automatico=True)
    riego2.save()

    riego3 = RiegoPrueba(riegoid=3,fecha="2023-10-12",hora="",automatico=True)
    riego3.save()

    riego4 = RiegoPrueba(riegoid=4,fecha="2023-10-12",hora="",automatico=True)
    riego4.save()

    riego5 = RiegoPrueba(riegoid=5,fecha="2023-10-12",hora="",automatico=True)
    riego5.save()

    riego6 = RiegoPrueba(riegoid=6,fecha="2023-10-12",hora="",automatico=True)
    riego6.save()


    return HttpResponse("agregado")