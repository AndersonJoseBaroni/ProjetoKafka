Apache Kafka - Começando no Windows 10


Aprenda a instalar o Apache Kafka no Windows 10 e executando o servidor iniciar e parar scripts de servidor relacionados ao Kafka e ao Zookeeper. 
Também verificaremos a instalação da Kafka criando um tópico, produzindo poucas mensagens para ele e, em seguida, usaremos um consumidor para ler as mensagens escritas em Kafka.
1. Pré-requisitos
Java 8 é necessário para executar downloads mais recentes do site Kafka.
Zookeeper (para armazenar metadados sobre o cluster Kafka) também é obrigatório. Kafka vem com zookeeper embutido para começar. 
Mas recomenda-se instalar o zookeeper separadamente no ambiente de produção. Baixe-o em seu site oficial.
Kafka pode ser executado em qualquer sistema operacional. Recomenda-se o sistema operacional Linux. Com o Windows, Kafka tem alguns bugs conhecidos.
Este tutorial é para iniciantes e não usa instâncias de zookeeper separadas – para manter as coisas simples e focadas apenas em Kafka.

2. Baixe e instale Kafka
Baixe Kafka no site oficial. Eu baixei a versão mais recente de hoje que é 2.5.0 e o nome do arquivo é "kafka_2.12-2.5.0.tgz".
Copie o arquivo baixado para alguma pasta e extraia-o usando o comando tar
> tar -xzf kafka_2. 12-2.5. 0.tgz
Copie a pasta extraída no local desejado. Eu coloquei no local "C:\ProjetoKafka\kafka_2.12-2.5.0".
A instalação está praticamente concluída !!

3. Startup and Shutdown
Para começar Kafka, precisamos primeiro começar Zookeeper e depois Kafka. Estou escrevendo pequenos arquivos em lote que se movem para o diretório de instalação kafka primeiro e,
 em seguida, executar o comando na nova janela de comando prompt.
3.1. Comece Zookeeper
Para iniciar o zookeeper, precisamos executar o script zookeeper-server-start.bat e passar o caminho do arquivo de configuração zookeeper.
start-zookeeper.bat
cd C:\ProjetoKafka\kafka_2.12-2.5.0
start cmd /k bin\windows\zookeeper-server-start.bat config\zookeeper.properties
3.2. Iniciar Kafka
Para iniciar o Kafka, precisamos executar kafka-server-start.bat script e passar o caminho do arquivo de configuração do corretor.
start-kafka.bat
cd C:\ProjetoKafka\kafka_2.12-2.5.0
start cmd /k bin\windows\kafka-server-start.bat config\server.properties
3.3. Desligamento Kafka
Para parar Kafka, precisamos executar o script kafka-server-stop.bat
stop-kafka.bat
cd C:\ProjetoKafka\kafka_2.12-2.5.0
start cmd /k bin\windows\kafka-server-stop.bat
3.4. Zookeeper de desligamento
Para parar o Zookeeper, precisamos executar o script zookeeper-server-stop.bat
stop-zookeeper.bat
cd C:\ProjetoKafka\kafka_2.12-2.5.0
start cmd /k bin\windows\zookeeper-server-stop.bat
Não pare o zookeeper e o kafka usando comandos CTRL+C Use sempre arquivos ou comandos .bat Caso contrário, a corrupção de dados pode ocorrer.

4. Verifique a instalação da Kafka
Primeiro, comece Zookeeper e Kafka usando scripts acima.
Abra um novo prompt de comando e crie um novo tópico Kafka.
Criar tópico
> bin\windows\kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor  1 --partitions  1 --topic test
 
//Output:
 
Created topic test.
Agora liste todos os tópicos para verificar se o tópico criado está presente nesta lista. Nesta etapa, temos apenas um tópico.
Verificar lista de tópicos
> bin\windows\kafka-topics.bat --list --bootstrap-server localhost:9092
 
//Output:
 
test
Agora liste todos os tópicos para verificar se o tópico criado está presente nesta lista. Nesta etapa, temos apenas um tópico.
Verificar lista de tópicos
> bin\windows\kafka-topics.bat --list --bootstrap-server localhost:9092
 
//Output:
 
test
Produzir algumas mensagens e submeter-se ao tópico test Eu adicionei duas mensagens, ou seja, "Olá" e "Kafka".
Produzir mensagens
> bin\windows\kafka-console-producer.bat --bootstrap-server localhost:9092 --topic test
Consuma as mensagens e submeta-se ao tópico test
Produzir mensagens
> bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test --from-beginning
 
//Output:
 
Hello
Kafka

======================================== Para realizar o teste no postman =======================================
COm unico consumidor
http://localhost:9000/kafka/publish?message=Teste

Com multiplos consumidores
http://localhost:9000/kafka/createUser?userId=1&firstName=teste1&lastName=teste2