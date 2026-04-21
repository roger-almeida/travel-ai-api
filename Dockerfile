# =============================================================================
# STAGE 1: BUILD
# Responsabilidade: compilar o código e gerar o .jar
# =============================================================================

# FROM: define a imagem base — eclipse-temurin é a distribuição oficial do OpenJDK
# 21-jdk-alpine: Java 21 + JDK completo + Alpine Linux (distro minimalista, ~5MB)
# "AS builder": nomeia esse stage para referenciar depois no stage 2
FROM eclipse-temurin:21-jdk-alpine AS builder

# WORKDIR: define o diretório de trabalho dentro do container
# Todos os comandos seguintes rodam a partir de /app
WORKDIR /app

# COPY: copia arquivos do computador host para dentro do container
# Copiamos pom.xml e o wrapper Maven ANTES do código fonte
# Por quê? Docker tem cache por camada — se o pom.xml não mudou,
# Docker reutiliza o cache do "mvn dependency:go-offline" sem baixar tudo de novo
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# RUN: executa um comando dentro do container durante o build
# dependency:go-offline: baixa todas as dependências do Maven antecipadamente
# -q: quiet, menos output
RUN chmod +x mvnw && ./mvnw dependency:go-offline -q

# Agora copiamos o código fonte — se só o código mudou (não o pom.xml),
# Docker reutiliza o cache das dependências e só recompila o Java
COPY src ./src

# package: compila e empacota tudo em um .jar em target/
# -DskipTests: pula os testes no build da imagem (testes rodam no CI, não aqui)
RUN ./mvnw package -DskipTests -q

# =============================================================================
# STAGE 2: RUNTIME
# Responsabilidade: rodar o .jar — imagem menor, sem Maven, sem JDK completo
# =============================================================================

# JRE (Java Runtime Environment) — só executa Java, não compila
# Muito menor que o JDK: ~180MB vs ~340MB
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# COPY --from=builder: copia o .jar gerado no stage 1 para este container
# O wildcard *.jar pega o arquivo independente da versão (0.0.1-SNAPSHOT, etc.)
COPY --from=builder /app/target/*.jar app.jar

# EXPOSE: documenta que o container usa a porta 8080
# Não abre a porta de verdade — isso é feito no "docker run -p" ou docker-compose
EXPOSE 8080

# ENTRYPOINT: comando executado quando o container inicia
# ["java", "-jar", "app.jar"] é equivalente a rodar "java -jar app.jar" no terminal
ENTRYPOINT ["java", "-jar", "app.jar"]
