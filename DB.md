## Domain Model
![Untitled](https://user-images.githubusercontent.com/54658745/187067317-7672acb3-d0c0-4b0c-ba4c-aab8d0946009.png)


## ERD
![Untitled (1)](https://user-images.githubusercontent.com/54658745/187067335-713c8326-fa56-427f-b4dc-1d3edfcbfffa.png)


## Git Branch 전략
- **main**
  - 배포 및 최종본, 출시 버전 브랜치 (배포 가능한 상태만 관리)

- **develop (from main)**
  - 다음 출시 버전을 개발/종합하는 브랜치 

- **front_dev/back_dev (from develop)**
  - 프론트엔드, 백엔드 각각 나눠진 폴더를 구분하기 위한 브런치

- **feature (from front_dev/back_dev)**
  - 기능을 개발하는 브랜치

- **hotfix**
  - 배포 시에 추가 설정이 필요할 대 사용한 브런치   
