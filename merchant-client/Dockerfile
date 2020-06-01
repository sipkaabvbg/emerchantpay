####  Build the react application
FROM node:12.4.0-alpine as build

# Configure the main working directory inside the docker image. 
WORKDIR /app

# Copy the package.json as well as the package-lock.json and install 
COPY package.json package-lock.json ./
RUN npm install

# Copy the main application
COPY . ./

# Arguments
ARG REACT_APP_API_BASE_URL
ENV REACT_APP_API_BASE_URL=${REACT_APP_API_BASE_URL}

# Build the application
RUN npm run build

####Serve the React application from Nginx 
FROM nginx:1.17.0-alpine

# Copy the react build 
COPY --from=build /app/build /var/www

# Copy nginx config
COPY nginx.conf /etc/nginx/nginx.conf

# Expose port 3000 to the Docker host, port can access it 
# from the outside Docker.
EXPOSE 80

ENTRYPOINT ["nginx","-g","daemon off;"]
