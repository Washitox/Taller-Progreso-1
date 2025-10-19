# 🐉 Ejercicios 4, 5 y 6 - Programación III (Java)

Este repositorio contiene las implementaciones en **Java** de los **Ejercicios 4, 5 y 6** usando estructuras de datos: **Pila** (Stack) y **Cola** (Queue).  
Cada ejercicio está en su propia carpeta con clases separadas por responsabilidad.

---

## Estructura del proyecto

```
ProyectoDragonBall/
│
├── Ejercicio4_DominioKi/
│   ├── DominioKi.java
│   ├── Pila.java
│   └── ValidadorExpresion.java
│
├── Ejercicio5_MensajeYoda/
│   ├── MensajeYoda.java
│   ├── Pila.java           (puedes reutilizar la misma Pila.java del ejercicio 4)
│   └── RPN.java
│
└── Ejercicio6_BusquedaEsferas/
    ├── BusquedaEsferas.java
    ├── Cola.java
    └── EsferaDragon.java
```

> Nota: `Pila.java` implementa una pila genérica simple (push/pop/peek/isEmpty). Puedes copiar la misma clase `Pila.java` en las carpetas de los ejercicios 4 y 5 o mantenerla en una carpeta `util/` si prefieres un único archivo compartido.

---

## Ejercicio 4 — Dominio del Ki (Validación de expresiones balanceadas)

### Descripción
Verifica si una expresión formada por `()`, `{}` y `[]` está balanceada. Se utiliza una **Pila** para empujar los símbolos de apertura y comprobar que cada cierre corresponda al último abierto.

### Archivos clave
- `DominioKi.java` — clase con `main()`. Lee la expresión desde consola y usa `ValidadorExpresion`.
- `ValidadorExpresion.java` — método `esBalanceada(String expresion)` que implementa la lógica con la pila.
- `Pila.java` — implementación genérica de pila.

### Ejemplo
Entrada:
```
( { [ ] } )
```
Salida (por consola):
```
Balanceada
```

Entrada:
```
( { [ ] )
```
Salida:
```
No balanceada
```

### Uso
Compilar y ejecutar (desde la carpeta `Ejercicio4_DominioKi`):
```bash
javac Pila.java ValidadorExpresion.java DominioKi.java
java DominioKi
```
Ingresar la expresión y presionar Enter.

---

## Ejercicio 5 — Reconstrucción del Mensaje de Yoda (RPN simbólica)

### Descripción
Interpreta una secuencia en **Forma Polaca Inversa (RPN)** donde los operandos son fragmentos de texto y el operador `+` concatena los dos fragmentos superiores de la pila. Se usa una **Pila de Strings** para reconstruir la frase final.

### Archivos clave
- `MensajeYoda.java` — `main()` que recibe la línea RPN desde consola y llama a `RPN.reconstruirMensaje`.
- `RPN.java` — contiene `reconstruirMensaje(String[] tokens)`.
- `Pila.java` — (misma implementación de pila) para manejar `String`.

### Ejemplo
Entrada (una línea):
```
"entrenar" "debes" + "camino" "el" + +
```
Salida:
```
Mensaje de Yoda: entrenar debes el camino
```

### Uso
Compilar y ejecutar (desde `Ejercicio5_MensajeYoda`):
```bash
javac Pila.java RPN.java MensajeYoda.java
java MensajeYoda
```
Escribe la secuencia RPN en una sola línea y presiona Enter.

---

## Ejercicio 6 — La Gran Búsqueda (Cola de Esferas del Dragón)

### Descripción
Registro de esferas encontradas mediante una **Cola** (orden de llegada). Cada esfera tiene un número (1..7) y una ubicación. El sistema permite:
- `agregar <número> "<ubicación>"` — añade una esfera (validando rango y duplicados).
- `mostrar` — lista el estado actual de esferas en orden de llegada.
- `invocar` — comprueba si están las 7 esferas diferentes (1..7) y, si es así, invoca a Shenlong.
- `salir` — termina el programa.

### Archivos clave
- `BusquedaEsferas.java` — `main()` interactivo que procesa comandos.
- `Cola.java` — implementación que usa `Queue<EsferaDragon>` y contiene validaciones:
  - No acepta números fuera del rango 1..7.
  - No permite agregar dos veces la misma esfera (mismo número).
  - `invocar()` verifica que existan todas las esferas 1..7 (sin repeticiones).
- `EsferaDragon.java` — POJO con `numero`, `ubicacion` y `toString()`.

### Comportamiento importante (según el código)
- Si intentas `agregar 1 "X"` y luego `agregar 1 "Y"`, el segundo intento será rechazado mostrando en qué ubicación fue encontrada la primera vez.
- `invocar` solo tendrá éxito si la cola contiene exactamente (o al menos) las esferas 1..7. La implementación comprueba que estén presentes los números 1..7; si faltan, mostrará que aún no se han reunido.
- Si se intentan agregar números fuera de 1..7 (ej. 8), se mostrará un mensaje de error y no se insertará.

### Ejemplo de sesión
```
Sistema de búsqueda de las 7 Esferas del Dragón
Ingrese un comando (agregar <número> "<ubicación>", mostrar, invocar, salir):
agregar 1 "Montañas Paoz"
✅ Esfera 1 encontrada en Montañas Paoz.
agregar 1 "Torre Karin"
⚠️ La Esfera 1 ya ha sido encontrada en Montañas Paoz.
agregar 2 "Torre Karin"
✅ Esfera 2 encontrada en Torre Karin.
...
mostrar
Estado actual de las esferas encontradas:
- Esfera 1 en Montañas Paoz
- Esfera 2 en Torre Karin
...
invocar
🐉 Invocando a Shenlong...
✨ Las 7 Esferas han sido reunidas.
🌠 ¡Pide tu deseo!
```

### Uso
Compilar y ejecutar (desde `Ejercicio6_BusquedaEsferas`):
```bash
javac EsferaDragon.java Cola.java BusquedaEsferas.java
java BusquedaEsferas
```
Luego escribe comandos en la consola siguiendo el formato indicado.

---

## Notas y recomendaciones

- Todas las clases están separadas por responsabilidad (una clase por archivo). Esto facilita pruebas unitarias y mantenimiento.
- `Pila.java` es reutilizable entre los ejercicios 4 y 5. Si prefieres, deja una copia en cada carpeta para entregas separadas, o colócala en `util/Pila.java` e importa/usa desde ahí.
- El parsing del comando `agregar` en `BusquedaEsferas` usa `split(" ", 3)` para aceptar ubicaciones con espacios si se pasan entre comillas. Asegúrate de ingresar la ubicación con comillas si contiene espacios:
  ```
  agregar 4 "Cueva del Norte"
  ```
- Manejo de errores:
  - Si escribes `agregar` sin parámetros, el programa te indica el formato correcto.
  - Si escribes un número no entero, verás un mensaje de error específico.

---

## Compilación y ejecución general (desde la raíz)
Para compilar y ejecutar individualmente cada ejercicio, entra a la carpeta correspondiente y ejecuta los comandos `javac` y `java` indicados en cada sección.  
Ejemplo para el ejercicio 6:
```bash
cd Ejercicio6_BusquedaEsferas
javac EsferaDragon.java Cola.java BusquedaEsferas.java
java BusquedaEsferas
```

---

## Contribuciones / cambios sugeridos
- Agregar tests unitarios (JUnit) para `ValidadorExpresion` y `RPN`.
- Mejorar el parsing de comandos (por ejemplo usando una librería de argumentos o un parser más robusto).
- Añadir una opción en `BusquedaEsferas` para limpiar la cola después de la invocación (comportamiento opcional).
