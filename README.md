# Inventario
_________________

## Notas del repositorio:
_________
Se tiene disponible el archivo de configuracion
en el directorio **'./Data/config.properties'**.

Para comentar una propiedad se debe de poner '#'
antes de una propiedad, ejemplo:

```
#mysql.host=127.0.0.1
```  

Por favor seguir el formato de tal manera que primero
se ponga el concepto principal en este caso ``mysql``, 
despues del punto, poner el nombre de la propiedad
en este caso ``host`` seguido de un " = " y su valor.

El nombre de la variable debe de ir en minusculas y 
pegado, si se usan dos palabras usar el formato 
lower camel case, ejemplo:

```
algo.propiedadConDosLetras=valor de algo
```

Como se aprecía el valor puede tener espacios y 
caracteres siempre y cuando se mantenga en la misma 
linea.

Por ultimo recordar que para hacer tags de versionado 
tener en cuenta que el formato `v1.0.2`, el aumentar
el 1 es para cuando se hizo cambios mayores, el 0 para
ligeros pero notables, el 2 para parches o pequeños 
detalles, esto unicamente cuando ya se considere una
version estable en ciertos puntos del proceso de desarrollo.
