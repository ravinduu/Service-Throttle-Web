FROM node:latest

WORKDIR /app

# add `/app/node_modules/.bin` to $PATH
ENV PATH /app/node_modules/.bin:$PATH

# install app dependencies
COPY ./Service-Throttle-Frontend/service-throttle-admin-dashboard/package.json .
COPY ./Service-Throttle-Frontend/service-throttle-admin-dashboard/package-lock.json .
RUN npm install --silent
RUN npm install react-scripts@3.4.1 -g --silent

# add app
COPY ./Service-Throttle-Frontend/service-throttle-admin-dashboard .

# start app
CMD ["npm", "start"]
