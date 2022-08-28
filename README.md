# 인프라

## 배포환경 CI/CD
--> 배포 환경 이미지..ㅎㅎ
- #오운완 서비스의 배포 전체 흐름을 볼 수 있는 이미지입니다.
- Jenkins의 Pipeline 구조를 통해 GitLab에 Webhook을 걸어 main Branch에 push하면 FrontEnd와 BackEnd가 자동배포 되도록 구현했습니다.
1) Jenkins file

```
pipeline {
    agent any
    stages {
        stage('FrontEnd'){
            steps {

                dir('frontend/owo'){
                script{
                    docker.build('front')
                 }
                }

                dir('frontend/owo'){
                                sh 'docker ps -f name=front -q | xargs --no-run-if-empty docker container stop'
                                sh 'docker container ls -a -f name=front -q | xargs -r docker container rm'
                                sh 'docker images --no-trunc --all --quiet --filter="dangling=true" | xargs --no-run-if-empty docker rmi'
                                sh 'docker run -d --privileged --name front -p 8080:8080 front:latest'
                 }

            }
        }

        

        stage('BackEnd'){

            steps {
             dir('backend'){
                      script{
                         docker.build('backend')
                      }
             }

              dir('backend'){
              sh 'docker ps -f name=backend -q | xargs --no-run-if-empty docker container stop'
              sh 'docker container ls -a -f name=backend -q | xargs -r docker container rm'
              sh 'docker images --no-trunc --all --quiet --filter="dangling=true" | xargs --no-run-if-empty docker rmi'
              sh 'docker run -d --privileged --name backend -p 8282:8282 backend:latest'
              }

             }

            }

        }

}

```
2) FrontEnd
- Vue.js는 Nginx와 같이 docker container로 빌드하여 배포했습니다.
- Docker file
```
FROM node:16 as build-stage
WORKDIR /app
COPY package*.json ./
RUN npm install --force
COPY . .
RUN npm run build

EXPOSE 8080

FROM nginx:stable-alpine as production-stage
COPY  ./nginx/nginx.conf /etc/nginx/conf.d/default.conf 

COPY --from=build-stage /app/dist /usr/share/nginx/html

CMD ["nginx", "-g", "daemon off;"]


```

- nginx conf file
```
server {
  listen 8080;
  location / {
        root    /usr/share/nginx/html;
        index   index.html index.htm;
        try_files $uri $uri/ /index.html;
  }
}


```

3) BackEnd
- spring boot에서 생성된 jar파일을 통해 docker container를 build 할 수 있도록 구현했습니다.
```
FROM openjdk:11-jdk
EXPOSE 8282
CMD ["./mvnw", "clean", "package"]
VOLUME /tmp
ARG JAR_FILE=build/libs/demo-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
ENV TZ=Asia/Seoul
RUN apt-get install -y tzdata
```

4) Redis
- redis를 docker container를 활용해서 build할 수 있도록 구현했습니다.
```
# Redis 최신 이미지 가져오기
$ docker pull redis
# Redis Server 실행
$ docker run --name myredis -d -p 6379:6379 redis
```

5) Openvidu 
- Openvidu CE On premises version으로 server build할 수 있도록 구현했습니다. 
```
$ curl https://s3-eu-west-1.amazonaws.com/aws.openvidu.io/install_openvidu_latest.sh | bash
```
- openvidu 관련 setting Configuration
```
$ sudo su
$ cd /opt/openvidu
$ vi .env
```


```
# OpenVidu configuration


# Domain name. If you do not have one, the public IP of the machine.
# For example: 198.51.100.1, or openvidu.example.com
DOMAIN_OR_PUBLIC_IP=i7c202.p.ssafy.io

# OpenVidu SECRET used for apps to connect to OpenVidu server and users to access to OpenVidu Dashboard
OPENVIDU_SECRET=owo202

# Certificate type:
CERTIFICATE_TYPE=letsencrypt

# If CERTIFICATE_TYPE=letsencrypt, you need to configure a valid email for notifications
LETSENCRYPT_EMAIL=hithere1012@naver.com

# Proxy configuration
HTTP_PORT=80

# Changes the port of all services exposed by OpenVidu.
# SDKs, REST clients and browsers will have to connect to this port
 HTTPS_PORT=8443

```

- openvidu server 실행
```
$ ./openvidu start
```



## Nginx Setting

- letsencrypt로 SSL 인증서를 발급 받은 후 Nginx에 적용했습니다.

1) EC2에 nginx 설치
```
$ sudo apt-get update
$ sudo apt-get install nginx
$ nginx -v

// 시작
$ sudo service nginx start
$ sudo systemctl start nginx
$ sudo /etc/init.d/nginx start

// 재시작
$ sudo service nginx restart
$ sudo systemctl restart nginx
$ sudo /etc/init.d/nginx restart

// 중지
$ sudo service nginx stop
$ sudo systemctl stop nginx
$ sudo /etc/init.d/nginx stop
```

2) 발급받은 도메인에 HTTPS 설정
- Cerbot을 통해 Let's Encrypt 인증서 발급
```
$ sudo apt-get update #apt-get 업데이트
$ sudo apt-get install software-properties-common #선행 소프트웨어 설치
$ sudo add-apt-repository ppa:certbot/certbot #저장소 추가
$ sudo apt-get update #apt-get 업데이트
$ sudo apt-get install certbot #certbot 설치
```
- Cerbot에서 Standalone 방식을 통해 도메인 인증서 요청
  - Certbot이 위한 간이 웹 서버를 돌려서 Let's Encrypt 서버로부터 오는 도메인 인증 요청을 직접 받아서 처리하는 방식입니다.
  - 별도의 설정이 필요없기 때문에 간단한 Standalone 방식을 채택했습니다.
```
$ sudo certbot certonly –-standalone -d i7c202.p.ssafy.io
```

3) Nginx https 적용 및 분기 처리
```
$ sudo vi /etc/nginx/sites-available/default
```
- https를 적용하며 80(http)포트로 오는 요청은 https(443포트)로 갈 수 있도록 프록시 패턴을 적용했습니다.
- https(443포트)로 오는 모든 요청 중 /는 frontend로, /api와 /oauth2는 backend 요청으로 처리될 수 있도록 프록시 패턴을 적용했습니다.
- /jenkins 오는 요청은 jenkins 관리 홈페이지로 연결될 수 있도록 분기처리하였습니다.

```
# nginx setting file
server {
        listen 82 default_server;
        listen [::]:82 default_server;

        root /var/www/html;

        index index.html index.htm index.nginx-debian.html;

        server_name _;

        location / {
                root /var/www/html;
                index index.nginx-debian.html;
        }

        return 301 https://i7c202.p.ssafy.io:443$request_uri;
}



server {
        listen 443 ssl;
        listen [::]:443;
        server_name i7c202.p.ssafy.io;

        ssl_certificate /etc/letsencrypt/live/i7c202.p.ssafy.io/fullchain.pem;
        ssl_certificate_key /etc/letsencrypt/live/i7c202.p.ssafy.io/privkey.pem;

        location / {
                proxy_pass http://localhost:8080;
                #root /home/ubuntu/dist;
                #index index.html;
                #root /var/www/html;
                #index index.nginx-debian.html;
        }

        location /api {
                proxy_pass https://i7c202.p.ssafy.io:8282/api;
        }

        location /oauth2 {
                proxy_pass https://i7c202.p.ssafy.io:8282/oauth2;
        }

        location /jenkins {
                proxy_pass http://i7c202.p.ssafy.io:9090;
        }

        location /demo {
                proxy_pass http://localhost:8081;
                proxy_redirect off;
                charset utf-8;
                proxy_set_header X-Readl-IP $remote_addr;
                proxy_set_header X-Forwarded-For @proxy_add_x_forwarded_for;
                proxy_set_header X-Forwarded-Proto $scheme;
                proxy_set_header X-NginX-Proxy true;
        }
}



```
