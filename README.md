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


