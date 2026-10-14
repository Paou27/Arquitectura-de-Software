from django.db import models

class MascotaRescate(models.Model):
    id = models.AutoField(primary_key=True, db_column='id_mascota')
    nombre = models.CharField(max_length=80)
    especie = models.CharField(max_length=40)
    raza = models.CharField(max_length=60, blank=True, null=True)
    edad_estimada = models.IntegerField(blank=True, null=True)
    descripcion = models.TextField(blank=True, null=True)
    imagen_url = models.CharField(max_length=255, blank=True, null=True)
    estado = models.CharField(max_length=20, default='en_adopcion')

    class Meta:
        managed = False
        db_table = 'mascotas'

class SolicitudAdopcion(models.Model):
    id = models.AutoField(primary_key=True, db_column='id_solicitud')
    id_mascota = models.IntegerField(db_column='id_mascota')
    id_usuario_adoptante = models.IntegerField(db_column='id_usuario_adoptante')
    mensaje = models.TextField(blank=True, null=True)
    estado_solicitud = models.CharField(max_length=30, default='pendiente')

    class Meta:
        managed = False
        db_table = 'solicitudes_adopcion'