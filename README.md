![](diagrama.png?raw=true "Diagrama UML")

mapeamento entre abismos e ferramentas:

0- Herança:
- Duplicated Code

1- Programação funcional:
- ciclo Infinito
- Efeitos Secundarios

2- Testes unitários:
- Erros de Logica

3- Tratamento de Excepções:
- Segmentation Fault
- Crash
- Exception
- File Not Found Exception

4- IDE:
- Erro de Sintaxe

5- Ajuda Do Professor:
- Erro de Sintaxe
- Erro de Lógica
- Exception
- File Not Found Exception

Modelação de classes:

Criamos uma classe chamada "Casa", a qual é abstrata, e nela temos uma função chamada "reactToSquare". Além disso, criamos subclasses que herdam da classe casa ("Abyss" e "Tool").
A classe Abyss é abstrata e tem subclasses de cada abismo do jogo, cada um com a sua função propria "reactToSquare" (@override). Nesta função é validado o abismo que o jogador caiu.
A classe Tool não é abstrata e não tem nenhum "filho". Nessa classe temos a implementação da função "reactToSquare" (@override) para as ferramentas. Nesta função apenas é adicionada a ferramenta ao inventário do jogador. 


Link para o nosso vídeo no youtube:
https://youtu.be/ytU1SXH2Fhs