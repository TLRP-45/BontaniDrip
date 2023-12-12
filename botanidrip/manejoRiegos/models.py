from django.db import models



# Create your models here.
class RiegoPrueba(models.Model):
    riegoid = models.IntegerField(primary_key=True)
    fecha = models.DateField()
    hora = models.CharField(max_length=10)
    automatico = models.BooleanField()
    
    def __str__(self):
        return str(self.riegoid)
    