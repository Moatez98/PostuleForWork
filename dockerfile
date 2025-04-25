# Étape 1 : construire l'application
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Étape 2 : créer l'image d'exécution
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copier le jar construit dans l'image finale
COPY --from=builder /app/target/postuleforwork.jar app.jar

# Exposer le port de l'application (ajuste si différent)
EXPOSE 9999

# Lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]