# Tiro Ao Alvo

O TiroAoAlvo é um jogo de tiro ao alvo em ambiente bidimensional, onde os jogadores devem ajustar a força e o ângulo do tiro para acertar um alvo. O jogo permite que os jogadores acumulem pontuações e moedas, comprem itens na loja e continuem jogando.

## Funcionalidades

- Ajustar a força e o ângulo do tiro para acertar o alvo.
- Acumular pontuações para cada tiro realizado com sucesso.
- Ganhar moedas ao atingir o alvo.
- Comprar itens na loja usando as moedas acumuladas.
- Visualizar os itens disponíveis na loja.
- Continuar jogando ou visitar a loja de itens.

## Pré-requisitos

- Java (versão 11)
- Alguma IDE ou ambiente de desenvolvimento Java

## Instalação

1. Clone o repositório do TiroAoAlvo.
2. Abra o projeto em sua IDE ou ambiente de desenvolvimento Java.
3. Execute o arquivo TiroAoAlvo.java para iniciar o jogo.

## Como jogar

1. Ajuste a força do tiro em metros por segundo (float).
2. Ajuste o ângulo do tiro em graus.
3. Ajuste o raio do alvo em metros (quanto maior, mais fácil acertar, sugiro colocar 2 metros).
4. Tente acertar o alvo para acumular pontuações e ganhar moedas.
5. Use as moedas para comprar itens na loja e melhorar seu desempenho no jogo.

## Estrutura do projeto

O projeto está estruturado da seguinte forma:

- TiroAoAlvo.java: Classe principal que inicia o jogo.
- Player.java: Classe que representa um jogador no jogo, com nome, pontuação e moedas.
- Item.java: Classe que representa um item disponível na loja, com nome e preço.
- LojaDeItens.java: Classe que representa a loja de itens no jogo, onde os jogadores podem comprar itens.
- Arco.java: Classe que representa o arco que está à venda na loja e pode ser usado para deixar o jogo mais interessante.
- Armadura.java: Classe que representa a armadura que está à venda na loja e pode ser usada para aumentar as tentativas de acertar o alvo.
- LimpaTela.java: Classe que fornece um método para limpar a tela do console.

## Contribuição

Contribuições são bem-vindas! Se você quiser contribuir com o projeto TiroAoAlvo, sinta-se à vontade para abrir uma issue ou enviar um pull request.

## Autor

Vitor Carlet
