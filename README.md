## Trabajo Practico Integrador 1 - Grupo 11

<h3>Integrantes:</h1>
<ul>
  <li>Todesco Ana Olivia</li>
  <li>Sosa Julieta</li>
  <li>Quinteros Tomas</li>
  <li>Gomez Tomas</li>
</ul>

### Instalacion
<p>Para ejecutar el proyecto, utiliza el siguiente comando en la carpeta raíz del proyecto:</p>
<b>docker compose up --build</b> en la carpeta raiz

### Consideraciones del csv
Para no tener problemas a la hora de cargar los valores del csv eliminamos un valor repetido del csv estudianteCarrera(id 82) y agregamos un 0 al dni de los valores 103 y 104 porque no encontraba al estudiante en la base de dato (no existia).

Ademas, no usamos el id de estudianteCarrera, ya que utilizamos un id compuesto (dni, id_carrera).

### Para Linux
<p>Si estás utilizando Linux, realiza los siguientes cambios en el archivo /utils/HelperMySQL.java 
<br>
Descomenta la línea 111.
Comenta la línea 110.</p>

### Endpoints

#### POST

```
/estudiantes

{
  "dni": 87654321,
  "nombre": "María",
  "apellido": "González",
  "edad": 22,
  "genero": "Femenino",
  "nroLU": 56789,
  "ciudadResidencia": "Rosario"
}
```

```
/carreras

{
    "id_carrera": 99,
    "nombre": "Carrera de prueba",
    "duracion": 99
}
```
```
/matriculas

{
    "id": 150,
    "id_estudiante":10719241,
    "id_carrera":1,
    "anio_inicio":2020,
    "anio_fin":0,
    "antiguedad": 5
}
```
--> a) dar de alta un estudiante (POST /estudiantes)

--> b) matricular un estudiante en una carrera (POST /matriculas)

--> c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.(GET estudiantes/list)

--> d) recuperar un estudiante, en base a su número de libreta universitaria.(GET estudiantes/lu/{lu})

--> e) recuperar todos los estudiantes, en base a su género.(GET estudiantes/{genero})

--> f) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos. (GET carreras/incriptos)

--> g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia. (GET estudiantes/{carrera}/{ciudad}/


h) generar un reporte de las carreras, que para cada carrera incluya información de los
inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y
presentar los años de manera cronológica. (GET carreras/informe)

