# 💾 ELIZA (Java Edition)

> "Tell me more about being empty and cold."

Uma implementação moderna e estilizada do clássico chatbot **ELIZA**, recriada em Java. Este projeto une o processamento de linguagem natural pioneiro de Joseph Weizenbaum (1966) com a abordagem de um programador em linguagem moderna

---

## 🕶️ A Estética
Diferente da ELIZA original que operava em teletipos brancos e cinzas, esta versão foi projetada para rodar em terminais de alta fidelidade:
- **Lógica Socrática:** O bot atua como um espelho psicológico frio, devolvendo questões para o usuário.
- **Visual Terminal:** Foco em diálogos existenciais e processamento de sentimentos em ambientes minimalistas.

## 🛠️ Engenharia do Projeto

O sistema utiliza três camadas principais de processamento para criar a "ilusão de consciência":

### 1. O Motor de Busca (Regex)
Utiliza o pacote `java.util.regex` para identificar padrões de fala. O uso de um `LinkedHashMap` garante que frases mais específicas sejam testadas antes de respostas genéricas.

### 2. Transposição de Pronomes (A Mágica)
Para que o bot não pareça um simples eco mecânico, foi implementada uma função de transformação. Ela mapeia os pronomes da entrada do usuário para o ponto de vista do bot:
- **"I"** torna-se **"you"**
- **"my"** torna-se **"your"**
- **"me"** torna-se **"you"**

*Exemplo:* Se o usuário diz "My boss hates me", a ELIZA processa o termo para responder "Why does **your** boss hate **you**?".

### 3. Método Socrático (Default Mode)
Quando nenhum padrão de regex é reconhecido, o sistema aciona o **Dicionário Socrático**. São respostas de conteúdo mínimo projetadas para serem abertas e instigativas, forçando o usuário a continuar o fluxo de consciência.

## 📜 História e Contexto
A **ELIZA** foi criada entre 1964 e 1966 no MIT por Joseph Weizenbaum. O script original, conhecido como **DOCTOR**, simulava um terapeuta da escola Rogeriana. 

O **"Efeito ELIZA"** descreve a tendência humana de atribuir inconscientemente intenções e emoções complexas a sistemas computacionais simples, uma prova de que a percepção de inteligência muitas vezes reside na interpretação de quem interage com a máquina.

## 🚀 Como Executar

1. Certifique-se de ter o **JDK 17+** instalado.
2. Compile o arquivo:
   ```bash
   javac code.java
   java code
   ```
