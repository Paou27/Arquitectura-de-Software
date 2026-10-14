from django.shortcuts import render, redirect, get_object_or_404
from django.contrib.auth.models import User
from django.contrib.auth import authenticate, login, logout
from .models import MascotaRescate, SolicitudAdopcion

def listar_mascotas(request):
    buscar = request.GET.get('buscar', '')
    mascotas = MascotaRescate.objects.filter(estado='en_adopcion')
    if buscar:
        mascotas = mascotas.filter(nombre__icontains=buscar)
    return render(request, 'mascotas/index.html', {'mascotas': mascotas, 'buscar': buscar})

def registrar_usuario(request):
    if request.method == 'POST':
        nombre = request.POST.get('username')
        email = request.POST.get('email')
        password = request.POST.get('password')
        if nombre and email and password:
            if not User.objects.filter(username=nombre).exists():
                User.objects.create_user(username=nombre, email=email, password=password)
                return redirect('mascotas:login')
    return render(request, 'mascotas/registro.html')

def iniciar_sesion(request):
    error = None
    if request.method == 'POST':
        nombre = request.POST.get('username')
        password = request.POST.get('password')
        user = authenticate(request, username=nombre, password=password)
        if user is not None:
            login(request, user)
            return redirect('mascotas:index')
        else:
            error = 'usuario o contraseña incorrectos'
    return render(request, 'mascotas/login.html', {'error': error})

def cerrar_sesion(request):
    logout(request)
    return redirect('mascotas:index')

def registrar_rescate(request):
    if not request.user.is_authenticated:
        return redirect('mascotas:login')
    if request.method == 'POST':
        MascotaRescate.objects.create(
            nombre=request.POST.get('nombre'),
            especie=request.POST.get('especie'),
            raza=request.POST.get('raza'),
            edad_estimada=request.POST.get('edad_estimada') or None,
            descripcion=request.POST.get('descripcion'),
            imagen_url=request.POST.get('imagen_url'),
            estado='en_adopcion'
        )
        return redirect('mascotas:index')
    return render(request, 'mascotas/rescate.html')

def solicitar_adopcion(request, mascota_id):
    if not request.user.is_authenticated:
        return redirect('mascotas:login')
    
    mascota = get_object_or_404(MascotaRescate, id=mascota_id)
    
    # cambiamos el estado de la mascota a pendiente
    mascota.estado = 'pendiente'
    mascota.save()
    
    # registramos la solicitud en la tabla solicitudes_adopcion de xampp
    SolicitudAdopcion.objects.create(
        id_mascota=mascota.id,
        id_usuario_adoptante=request.user.id,
        mensaje='quiero adoptar a esta mascota',
        estado_solicitud='pendiente'
    )
    
    return redirect('mascotas:perfil')

def ver_perfil(request):
    if not request.user.is_authenticated:
        return redirect('mascotas:login')
    
    # buscamos las solicitudes de este usuario específico
    solicitudes = SolicitudAdopcion.objects.filter(id_usuario_adoptante=request.user.id)
    ids_mascotas = [s.id_mascota for s in solicitudes]
    mascotas_adoptadas = MascotaRescate.objects.filter(id__in=ids_mascotas)
    
    return render(request, 'mascotas/perfil.html', {'mascotas_adoptadas': mascotas_adoptadas})