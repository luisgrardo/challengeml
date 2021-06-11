"# challengeml" 

Operación Fuego de Quasar

Para el desarrollo del Challenge utilice Spring Boot con solo 2 capas una de controlador y una de servicios.

el challenge de obtener la posicion la resolvi utilizando las siguientes librerias:
      
       apache.commons.math3 
       lemmingapex.trilateration
       
Para el calculo se elige el Algoritmo de Levenberg-Marquardt para resolver el problema de ajuste de minimos cuadrados no lineales en la trilateracion, esta opcion o este fragmento de codigo ya es muy cotidiano cuando hablamos de GPS posicionamiento global o incluso indoor positioning.


Comparto el Json que ocupe para las pruebas tal cual es el que esta en el ejercicio solo que modifique comillas y el nombre del root es satelite, es importante que se respete ese nombrado ya que asi lo serializa spring con el nombre y asi nombre a la propiedad.



{
    "satelite": [
        {
            "name": "kenobi",
            "distance": 100.0,
            "message": [
                "este",
                "",
                "",
                "mensaje",
                ""
            ]
        },
        {
            "name": "skywalker",
            "distance": 115.5,
"message": [
                "",
                "es",
                "",
                "",
                "secreto"
            ]
        },
        {
            "name": "sato",
            "distance": 142.7,
"message": [
                "este",
                "",
                "un",
                "",
                ""
            ]
        }
    ]
}

URL Repositorio
https://github.com/luisgrardo/challengeml

URL AWS
http://operacionfuego-env-1.eba-43aauwab.us-east-2.elasticbeanstalk.com/topsecret




Diagrama de paquete
![image](https://user-images.githubusercontent.com/12503508/121669020-84057f00-ca71-11eb-82b5-5c5f82399e90.png)
