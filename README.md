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
