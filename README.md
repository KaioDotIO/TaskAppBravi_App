# TaskApp
## _The Last Markdown Editor, Ever_

TaskApp é um aplicativo que gera tarefas aleatórias e permite o usuário executá-las. Com um contabilizador de tempo, o usuário pode monitorar seu progresso em suas tarefas escolhidas, também podendo definir o "Status" delas.

- Gere uma tarefa
- Escolha
- ✨E  Just Do It

## Features

- Botão gerador de novas tarefas.
- Possibilidade de adicionar tarefas para meu Dashbord.
- Filtro que possibilita o usuário escolher apenas 1 tipo de tarefa a ser gerada aleatóriamente.
- Timer e Spinner que possibilitam o usuário a monitorar o progresso em cada tarefa.

## Tech

- [Retrofit] - A lib foi utilizada para fazer a requisição para a "boredpi". 
- [Room] - De extrema importancia, o Room foi usado para criar um banco de dados local para armazenar as tarefas escolhidas para usuário, assim criando o Dashbord.
- [GSON] - Foi usado para tratar dados da API que vinham em JSON e converteu-os para Objetos.

## Possíveis melhorias

- Implementação de uma arquitetura MVVM ou MVP.
- Clean Code nunca é demais.
