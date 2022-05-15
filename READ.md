## How to make a clean pull request


### Fork

#### 1. 기여하려는 저장소 Fork 하기

![K-051](https://user-images.githubusercontent.com/54658745/168462581-501733ae-7da0-4667-bbe7-0d6fe2857283.png)
![K-052](https://user-images.githubusercontent.com/54658745/168462619-91013df4-8f06-4ab4-b98a-4b6e9d4e5a13.png)
![K-053](https://user-images.githubusercontent.com/54658745/168462621-3f5a3ab0-4a42-498c-bc68-99eacd134f0f.png)

- 기여하고자 하는 저장소를 나의 저장소로 fork 하기
- 나의 Repositories 에서 자신의 저장소로 이동하기
- 내 컴퓨터에 저장소를 clone 하기


#### 2. 원격 저장소 Remote 설정하기
```
$ git clone https://github.com/Seunghui98/BackEnd_Pj.git
$ cd 내려받은 폴더
$ git init
$ git remote add upstream https://github.com/WeedInGist/BackEnd_Pj.git
```


#### 3. PR용 branch 생성하기
1. Fetch the latest code from the main branch

```
$ git fetch upstream
```

2. Create a new branch or Using an existing branch
```
$ git checkout -b 자신의 브랜치 이름
```

or 

```
$ git checkout 기존 자신의 브랜치 이름
```

3. 코드 변경 진행
```
$ git add .
$ git commit -m "커밋 메시지"
```

4. Pull the latest code
```
$ git pull upstream
```

5. Pull the latest code
```
$ git push -u origin 현재 자신이 작업한 브랜치 이름
```


#### 4. fork한 github 사이트에 들어가서 PR 올리기
<img width="746" alt="스크린샷 2022-05-15 오후 5 18 58" src="https://user-images.githubusercontent.com/54658745/168463834-03a9dd15-ea86-4f99-b6f5-931bb260d62d.png">
<img width="1305" alt="스크린샷 2022-05-15 오후 5 19 29" src="https://user-images.githubusercontent.com/54658745/168463838-4387ada1-320f-43ba-83da-75cf9d9792f7.png">



