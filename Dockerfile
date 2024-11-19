# Usa una imagen de Node.js
FROM node:20

# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia el package.json e instala JSON Server
COPY package*.json ./
RUN npm install json-server

# Copia el archivo db.json y el resto del código fuente
COPY db.json .

# Expone el puerto 3000
EXPOSE 3000

# Comando para iniciar JSON Server
CMD ["npx", "json-server", "--watch", "db.json", "--host", "0.0.0.0", "--port", "3000"]
