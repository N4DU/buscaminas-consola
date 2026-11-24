# Buscaminas en consola

Juego de Buscaminas desarrollado en Java y ejecutado desde la terminal. Permite elegir el tamaño del tablero y el nivel de dificultad, colocar banderas, descubrir casillas y mostrar automáticamente las zonas vacías conectadas.

## Características

- Tablero configurable mediante ancho y alto.
- Cinco niveles de dificultad.
- Generación aleatoria de bombas.
- Expansión automática en las zonas sin bombas cercanas.
- Expansión en diagonal dentro de las zonas vacías.
- Banderas marcadas con `X`.
- Conteo de bombas en las casillas vecinas.
- Mensajes de victoria y derrota.
- Visualización de la solución al finalizar la partida.

## Requisitos

- Java Development Kit (JDK) 17 o posterior recomendado.
- Una terminal compatible con texto.

Para comprobar si Java está instalado:

```bash
java -version
javac -version
```

## Ejecutar el juego

Desde la carpeta que contiene `Buscaminas.java`:

```bash
javac Buscaminas.java
java Buscaminas
```

También puedes ejecutar el archivo desde tu IDE favorito importando el repositorio como proyecto Java.

## Cómo jugar

Al iniciar el programa se solicitan tres datos:

1. **Ancho de la cuadrícula**.
2. **Alto de la cuadrícula**.
3. **Nivel de dificultad**, desde `1` hasta `5`.

Los niveles corresponden a los siguientes rangos aproximados de bombas:

| Nivel | Porcentaje aproximado |
|---:|---:|
| 1 | 5 % – 8 % |
| 2 | 10 % – 13 % |
| 3 | 15 % – 17 % |
| 4 | 20 % – 23 % |
| 5 | 27 % – 33 % |

Después, introduce una coordenada horizontal y vertical. Las coordenadas usan índices mostrados por el tablero y comienzan en `1`:

```text
3 4
```

Este ejemplo selecciona la columna `3` y la fila `4`.

Para colocar o quitar una bandera, añade `si` al introducir las coordenadas:

```text
3 4 si
```

## Símbolos del tablero

| Símbolo | Significado |
|---|---|
| `[]` | Casilla oculta |
| `1`–`8` | Cantidad de bombas alrededor |
| `X` | Bomba o bandera |
| `0` | Casilla vacía sin bombas cercanas |

## Objetivo

Descubre todas las casillas que no contienen bombas. Pierdes si descubres una bomba. Cuando terminas una partida, el programa muestra la solución completa antes de presentar el resultado.

## Estructura del proyecto

```text
buscaminas-consola/
└── Buscaminas.java
```

El archivo contiene todo el programa, incluidos la configuración del tablero, la colocación aleatoria de bombas, la interfaz de consola y las reglas del juego.

## Funcionamiento

- `llenarArraySolucion()` coloca las bombas aleatoriamente según el nivel elegido.
- `mostrarArrayVisible()` dibuja el tablero y sus coordenadas.
- `pedirCoordenadas()` procesa la entrada de la terminal.
- `CantBombasAlrededor()` cuenta las bombas de las ocho casillas vecinas.
- `limpiarAlrededor()` descubre recursivamente las zonas vacías.
- `comprobarGanaste()` verifica si quedan casillas seguras por descubrir.
- `mostrarSolucion()` revela las bombas y los números al finalizar.

## Autor

Proyecto creado por [N4DU](https://github.com/N4DU).
