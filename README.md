# Tech Chalenge Pos-Tech - Fiap

## Problema
Há uma lanchonete de bairro que está expandindo devido seu grande sucesso. Porém, com a expansão e sem um sistema de controle de pedidos, o atendimento aos clientes pode ser caótico e confuso. Por exemplo, imagine que um cliente faça um pedido complexo, como um hambúrguer personalizado com ingredientes específicos, acompanhado de batatas fritas e uma bebida. O atendente pode anotar o pedido em um papel e entregá-lo à cozinha, mas não há garantia de que o pedido será preparado corretamente. Sem um sistema de controle de pedidos, pode haver confusão entre os atendentes e a cozinha, resultando em atrasos na preparação e entrega dos pedidos. Os pedidos podem ser perdidos, mal interpretados ou esquecidos, levando à insatisfação dos clientes e a perda de negócios. Em resumo, um sistema de controle de pedidos é essencial para garantir que a lanchonete possa atender os clientes de maneira eficiente, gerenciando seus pedidos e estoques de forma adequada. Sem ele, expandir a lanchonete pode acabar não dando certo, resultando em clientes insatisfeitos e impactando os negócios de forma negativa. Para solucionar o problema, a lanchonete irá investir em um sistema de autoatendimento de fast food, que é composto por uma série de dispositivos e interfaces que permitem aos clientes selecionar e fazer pedidos sem precisar interagir com um atendente.

## Descrição do Sistema
Sistema de gerenciamento de pedidos de fast food, este sistema tem como objetivo permitir que a rede de fast food possa atender os clientes de maneira eficiente, gerenciando seus pedidos e estoques de forma adequada assim viabilizando a expansão da rede. 

## Event Storming

O projeto foi elaborado com base na Arquitetura Hexagonal seguindo os conceitos de DDD, cujo Event Storming pode ser acessado [Aqui](https://miro.com/app/board/uXjVM5IDnUo=/?share_link_id=798761038531).


## Tópicos

- [Pré-Requisitos](#pré-requisitos-)
- [Como executar o projeto](#como-executar-o-projeto-)
- [Funcionalidades](#funcionalidades-)

## Pré-Requisitos ⚙️

<br>![Static Badge](https://img.shields.io/badge/java-v17.0.0-blue)
<br>![Static Badge](https://img.shields.io/badge/docker-latest-blue)

## Como executar o projeto? 💻

Esse projeto está configurado de forma a ser executado de 3 formas:

- **[Aprovisionamento apenas das dependências externas do projeto](RUN-OWN-CODE.md)**: indicado para os desenvolvedores 
que estiverem atuando no projeto, pois constrói as dependências externas, baseadas em container, e deixa a carga da IDE 
a execução do serviço em si.
<br><br>
- **[Aprovisionamento do ambiente completo incluindo build da imagem do serviço](RUN-BUILDING-IMAGE.md)**: indicado quem
 quer construir a imagem Docker do serviço localmente e usá-la para executar a aplicação como um todo (incluindo as dependências externas).
<br><br>
- **[Aprovisionamento do ambinete completo fazendo uso da imagem do DockerHub](RUN-DOCKER-HUB-IMAGE.md)**: indicado para
 quem apenas quer executar o projeto sem precisar construir nada na máquina. Nesse caso, todos os containers serão 
baixados e executados. Esse projeto conta com um job, via Github Action, que atualiza a imagem no Docker Hub sempre que 
ocorre uma alteração na branch master.

  
## Funcionalidades ✅

Para ter acesso à documentação das API's expostas nesse serviço acesse:

> **localhost:8080/doc** e terá acesso ao swagger da aplicação.
> 
> Observação: caso opte por rodar o código direto, a porta deverá ser: 8688

Caso prefira executar as API's via a ferramenta Postman, a collection está disponível em:

> [Tech Challenge API](./documentation/Tech%20Challenge%20API.postman_collection.json)