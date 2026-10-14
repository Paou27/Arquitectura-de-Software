from django.contrib import admin
from django.urls import path, include

urlpatterns = [
    path('admin/', admin.site.urls),
    path('', include('mascotas.urls')),
]

# Importamos el módulo admin de Django para habilitar el panel de administración por defecto
from django.contrib import admin

# Importamos 'path' para definir las rutas web y 'include' para conectar las URLs de otras apps
from django.urls import path, include

# Lista principal que almacena todas las rutas (URLs) disponibles en el proyecto web
urlpatterns = [
    # Ruta para acceder al panel de administración nativo de Django (/admin/)
    path('admin/', admin.site.urls),
    
    # Ruta raíz (''). Conecta todas las peticiones que entren a la página principal 
    # y las redirige al archivo 'urls.py' dentro de la aplicación 'mascotas'.
    # Usamos 'namespace="mascotas"' para organizar y llamar las rutas limpiamente en los templates.
    path('', include(('mascotas.urls', 'mascotas'), namespace='mascotas')),
]