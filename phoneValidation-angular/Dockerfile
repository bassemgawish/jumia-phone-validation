FROM node:latest as build

WORKDIR /usr/local/app

COPY ./ /usr/local/app/

ENV PATH /usr/local/app/node_modules/.bin:$PATH

COPY package.json  /usr/local/app/package.json

RUN npm install

RUN npm install -g @angular/cli

RUN npm run build

EXPOSE 4200



