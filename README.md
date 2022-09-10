# Taygeta CLI
<p align="center">
<img src="https://github.com/andersonhsporto/Taygeta-planet-probe/blob/master/img/star.png" width="200px" alt="Taygeta Star"/><br>
</p>

Taygeta é uma [interface de linha de comando](https://en.wikipedia.org/wiki/Command-line_interface) 
capaz de criar planetas, sondas e simular a movimentação / colisão destas por meio de comandos.




## Ferramentas Utilizadas

* [JAVA 18](https://www.java.com/pt-BR/) - Linguagem de programação.
* [Apache Maven 3.8.6](https://maven.apache.org/) - Gerenciador de dependências.
* [IntelliJ](https://www.jetbrains.com/idea/) - IDE para desenvolvimento.
* [Docker](https://www.docker.com/) - Serviço de virtualização.


## Inicialização

Para executar o projeto é necessario gerar o artefato *.jar*, uma forma de executar essa ação no linux é utilizando o comando:

```sh
  mvn package
```

Este comando irá gerar um *.jar* de nome `Taygeta-cli-1.0-SNAPSHOT.jar`, para executar este artefato basta utilizar o comando:

```sh
  java -jar Taygeta-cli-1.0-SNAPSHOT.jar
```

### Docker

Para utilizar este projeto no docker, basta executar o seguinte comando para gerar uma imagem deste projeto:

```sh
  docker build -t taygeta --build-arg JAR_FILE=Taygeta-cli-1.0-SNAPSHOT .
```

Este comando irá gerar uma imagem com o nome `taygeta`, para visualizar o projeto no container basta utilizar o comando:

```sh
  docker run -it --rm taygeta
```

### Lista de Comandos
Comando | Definição
---|---------
`add-planet` | Adicionar novo planeta
`add-probe` | Adicionar sonda a um planeta existente
`move-probe` | Mover sonda existente
`list all` ou `list total` | Listar todos os planetas e suas respectivas sondas
`list planets` ou `list planets` | Listar todos os planetas
`list probes` ou `list probe` | Listar todos as sondas
`undo` | Desfazer comando atual
`help` ou `?` | Exibir lista de comandos
`exit`|  Encerrar o programa

#### Exemplos

Para adicionar um planeta é necessario utilizar o comando `add-planet` e inserir a altura e largura 
do retangulo que representa a area deste planeta utilizando o caractere x como delimitador. Por exemplo: `10x10`

