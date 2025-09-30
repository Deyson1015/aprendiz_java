# Etapa de build
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copiamos solo el pom para cachear dependencias
COPY pom.xml ./
RUN mvn -q -e -DskipTests dependency:go-offline

# Copiamos el código fuente
COPY src ./src

# Compilamos
RUN mvn -q -DskipTests package

# Etapa de runtime (imagen ligera)
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copiamos el JAR construido
COPY --from=build /app/target/aprendiz-0.0.1-SNAPSHOT.jar app.jar

# Puerto interno expuesto por Spring Boot (configurable con PORT)
EXPOSE 8080

# Permitir pasar opciones a la JVM desde el entorno
ENV JAVA_OPTS=""

# Ejecutar la app (nota: PORT se resuelve dentro de Spring)
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar app.jar"]

