# Library
## Laboratorio 3 - TDD
### Juan David Martínez Mendez
### Santiago Gualdron Rincon
#### CREA UN PROYECTO CON MAVEN
Como en el laboratorio anterior, utilizamos el comando "mvn archetype:generate" y configuramos el proyecto con los datos suministrados.
Grupo: edu.eci.cvds 
Artefacto: Library 
Paquete: edu.eci.cvds.tdd 
archetypeArtifactId: maven-archetype-quickstart 
#### AGREGAR DEPENDENCIAS JUNIT5
Para esto buscamos en el repositorio de maven la penultima version mas reciente de JUnit(5.11.4), por medio del siguiente link "https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api/5.11.4"
y agregamos al pom.xml

![2](https://github.com/user-attachments/assets/93b9feae-7375-4d12-b65e-a4d279b446a9)

#### AGREGAR ESQUELETO DEL PROYECTO
![1](https://github.com/user-attachments/assets/3dd665b1-2799-4300-a0ad-0567b056fded)
#### AGREGAR CLASES
se crearon y posteriormente agregaron todos los codigos suministrados de cada clase, cada una en la respectiva ruta dada

![image](https://github.com/user-attachments/assets/03ba14b4-218c-4776-a055-f9360aa2a74a)

![image](https://github.com/user-attachments/assets/09ae2e5e-ba87-477a-865c-994fd4142f7b)


#### PRUEBAS UNITARIAS Y TDD
para el método addBook se crearon las siguientes pruebas:

1.testAddBookWhenNotExist

2.testAddIncreaseBookWhenNotExist

3.testAddBookWhenAlreadyExists

4.testAddIncreaseBook

5.testNotAddBookWhenItIsNull

6.testNotAddIncreaseBook

7.testNotAddDifferentIsbnBook

8.testNotAddNullIsbnBook

9.testNotAddBookWhenRepeatIsbn

para el método loanABook se crearon las siguientes pruebas:

1.testLoanABook

2.testNotLoanABookWhenUserNotExist

3.testNotLoanABookWhenBookNotExist

4.testNotLoanABookWhenUserHasIt

5.testNotLoanABookWhenBookIsNotAvailable

6.testNotLoanABookWhenUserOrBookIsNull

finalmente para el método returnLoan se crearon las siguientes pruebas:

1.testNotReturnABookWhenLoanIsNull

2.testReturnALoan

3.testNotReturnALoanAlreadyReturned

4.testAddIncreaseBooksWhenLoan

Al realizar el diseño de las pruebas y su respectivo método, se crearon ramas feature para evitar trabajar sobre la rama principal, a medida de completar una funcionalidad se realizó PR sobre la rama develop, esto para cuando trabajemos en una nueva funcionalidad podamos tener los archivos actualizados, ya que cada rama feature se "desprende" de la rama develop. Aqui hemos realizado los PR por cada funcionalidad

![image](https://github.com/user-attachments/assets/c3a46819-a0b0-4ae7-b5b4-a8166dd1f759)

Al realizar terminar cada feature se verifico que las pruebas siguieran funcionando, tanto las ya realizadas como las recién hechas, para cada feature no tuvimos problemas con interrumpir otras pruebas, al finalizar todas las implementaciones realizamos las pruebas para verificar que todas pasen.

![image](https://github.com/user-attachments/assets/a2215a33-f767-4200-b988-5b038d74d750)




#### CREAR CLASE DE PRUEBA
La clase LibraryTest. java fue creada dentro de la ruta edu.eci.cvds.tdd.Library, 

#### COBERTURA

![image](https://github.com/user-attachments/assets/72818310-92b1-4e5d-9fe4-4ed9e632f44c)

#### SONARQUBE

Instalamos sonarqube por medio del siguiente link 

https://docs.docker.com/desktop/setup/install/windows-install/

![image](https://github.com/user-attachments/assets/16c71b1d-ee9e-424a-a95e-6bbbabc223d8)

teniendo docker ya instalado, procedemos a realizar el comando "docker pull sonarqube"


![image](https://github.com/user-attachments/assets/15ce8733-96e1-44a0-812e-08bb28291af6)

utilizamos el comando docker run -d --name sonarqube -e SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true -p 9000:9000 sonarqube:latest

![image](https://github.com/user-attachments/assets/84e53b82-a904-4396-bf69-43a8b8baf407)

la validación de que docker funciona se puede revisar tanto por consola "docker ps -a" como por la aplicación

![image](https://github.com/user-attachments/assets/8c0ac2fd-8ed1-47fb-8476-813d682acb00)


![image](https://github.com/user-attachments/assets/bb57fbba-1d86-4e56-a63f-ff76edf921e6)

![image](https://github.com/user-attachments/assets/8a5b0245-13f1-447f-a0e7-718901397cc3)

![image](https://github.com/user-attachments/assets/f5aa75a4-b366-4213-ab4b-077e596bb5cd)

iniciamos sesion con las credenciales dadas, nueva contraseña: cvdsMG20251%

![image](https://github.com/user-attachments/assets/9547d45e-3090-4980-beb6-0499687c183a)

ahora vamos a modificar los tokens

* Account -> security -> generate token.

![image](https://github.com/user-attachments/assets/6146b7a6-cf4d-4443-8e9b-190a64e020bf)

vamos a generar un token de prueba

![image](https://github.com/user-attachments/assets/1e207f3d-cda1-4e86-9535-74f9da64f220)

![image](https://github.com/user-attachments/assets/ec957853-e094-4a90-8d2c-dbe49f90400b)

Ahora, estamos manejando el entorno intellij por medio de los pluggins

![image](https://github.com/user-attachments/assets/43dd023d-a5c1-4b1a-9ca8-dd06a122e4d4)

![image](https://github.com/user-attachments/assets/13541d1a-a90a-4070-a1f8-7c89c6ad42dd)

![image](https://github.com/user-attachments/assets/08450b2e-7b7d-4e2f-91ed-2c31aaf5bfbd)

