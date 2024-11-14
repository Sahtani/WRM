# Utiliser l'image OpenJDK Slim
FROM openjdk:22-jdk-slim

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier JAR généré dans l'image
COPY target/WRM-0.0.1-SNAPSHOT.jar WRM-0.0.1-SNAPSHOT.jar

# Exposer le port utilisé par l'application
EXPOSE 8888

# Commande d'entrée pour démarrer l'application
ENTRYPOINT ["java", "-jar", "WRM-0.0.1-SNAPSHOT.jar"]
