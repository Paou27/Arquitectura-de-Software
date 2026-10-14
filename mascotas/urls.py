from django.urls import path
from . import views

app_name = 'mascotas'

urlpatterns = [
    path('', views.listar_mascotas, name='index'),
    path('registro/', views.registrar_usuario, name='registro'),
    path('login/', views.iniciar_sesion, name='login'),
    path('logout/', views.cerrar_sesion, name='logout'),
    path('rescatar/', views.registrar_rescate, name='rescatar'),
    path('adoptar/<int:mascota_id>/', views.solicitar_adopcion, name='adoptar'),
    path('perfil/', views.ver_perfil, name='perfil'),
]