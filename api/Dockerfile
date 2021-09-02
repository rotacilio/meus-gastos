FROM node:14.17-alpine

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

COPY ./package-lock.json /usr/src/app
COPY ./package.json /usr/src/app

RUN npm install

COPY . /usr/src/app/

EXPOSE 5001

CMD [ "npm", "run", "start:debug" ]