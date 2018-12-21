# tp2algo3

[![Build Status](https://travis-ci.com/JuanGaray93/tp2algo3.svg?branch=master)](https://travis-ci.com/JuanGaray93/tp2algo3)

Trabajo práctico 2 de Algoritmos 3, cátedra Fontela, Universidad de Buenos Aires.

Supuestos entrega 1:

  
Unidades:
  ·Las unidades creadas se crean instantáneamente en el casillero vacío más cercano al edificio que las crea y no se pueden mover hasta el siguiente turno.
  · La búsqueda del casillero vacío cercano es responsabilidad del Mapa.
  · 


Mapa:
  · Es un singleton. Para conseguir el mapa y pedirle que haga esto o aquello le decimos Mapa mapa = Mapa.getMapa();.


Posición:
  · Se encarga de interaccionar con Mapa para operaciones de movimiento y ubicación.
  · 

Posicionable: 
  · Son todos los edificios y unidades. El mapa sólo sabe que está colocando Posicionables.

Edificios:
  · Edificio: 
    · Tiene almacenadas sus coordenadas iniciales.
    · Tiene una colección de posiciones. Cuando se coloca en el mapa, le asigna a esas posiciones sus coordenadas una por una, de acuerdo a su coordenada "central" (la que se le pasa por parámetro al armarlo) y su tamaño. Cuando se muere, sea por falta de vida o por eliminación del Jugador, le indica a todas sus posiciones que se destruyan. Las posiciones entonces eliminan la referencia al edificio de todos los casilleros donde está ubicado.
    · Tiene un state que indica si está siendo construido o si ya fue construido. El state "EnConstruccion" además podra tener un state que indique en qué nivel de construcción está, o alternativamente (y hay que ver si es suficientemente OO) un porcentaje de completado.
    · De su coordenada eje va a calcularse dónde surgen las unidades que produce y qué lugar ocupa.
    · Cuando está en construcción, almacena una referencia al aldeano que lo está construyendo en su state enConstrucción.
    
  · Cuartel:
  · PlazaCentral:
  · Castillo:

  
Unidades: 
  · Unidad:
  · Aldeano: 
  · Espadachin: 
  · Arquero: 
  · ArmaDeAsedio:
	

# Entrega 2

### TODO

## JUGADOR
Distribución de los jugadores en el mapa
	· Dónde empiezan las unidades iniciales de cada jugador (3 aldeanos y 100 de oro)
	· Qué jugador empieza primero (azar)
	·  


Pruebas de inicio del juego, posición, edificios, aldeanos y oro necesarios.
Reglas de población
Crear unidades => sube la población
Matar unidades => baja población
Matar aldeanos => baja población y baja producción de oro
Verificar tope poblacional
Pruebas de distancia y ataques (tanto para las unidades como para el castillo)


















