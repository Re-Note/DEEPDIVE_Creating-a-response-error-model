### [DEEPDIVE] 실습 과제 10.응답/에러모델 만들기

### [기본 구현 요구 사항]

- 공통 Api 응답모델과 에러 모델을 만들고 간단한 성적 저장/조회하는 api 를 구현.

- 아래의 응답(에러) 예시를 만족하는 응답을 가진 API 를 구현.

  1. Status.code: http status 가 아닌 서버에서 정의하는 code값이 담겨져 있어야 합니다.(정상응답에서는 항상 200)

  2. Status.message: 정상응답시에는 “OK”, 에러 응답에서는 에러에대한 상세한 이유를 담아줍니다.

  3. Metadata.resultCount: 정상 응답시에 나타나는 값입니다. results list 의 count 를 담아줍니다.

  4. Results: 정상응답시에 나타나는 값입니다. 항상 list 형태로, 실제 응답으로 내주고싶은 정보가 표시됩니다.

  5. Data: 에러 응답시에 나타나는 값으로, 에러 응답시에 frontend 에서 사용자에게 어떤 이유로 요청이 거부되었는지 메세지를 만들기 쉽게 필요한 데이터를 넣어줍니다.

- 아래의 API 요구사항을 만족하는 API 를 구현.

  1. 이름과 성적을 입력받아 저장하는 api
 
  2. 입력된 성적을 조회 하는 api

  3. 특정 성적을 입력받아, 해당 성적의 학생만 조회하는 api

- 아래의 구현 요구사항 만족하는 API 를 구현.

  1. Controller 에서 응답 모델로 만들어 주어야 합니다. 

  2. 에러응답을 만들기 위해서는 @ExceptionHander 를 사용하여 exception 의 데이터를 이용해야 합니다.

  3. exceptionHander 에서 응답모델을 만들때 필요한 데이터가 포함시킬 수 있는 customException 을 구현 해야 합니다.

- 에러 응답에서 다음의 경우에서도 정상적으로 data 가 응답의 결과로 나올 수 있도록 합니다.

- 구현한 api 이외에 필요하다고 생각하는 api를 추가 구현 하고, 위 api 요구사항에서 문제점이 발견될 경우 개선해보도록 합시다.

### [구현 결과 이미지]

![10결과1](https://github.com/user-attachments/assets/e26f6080-8d9d-4e08-ada7-b34951056413)

![10결과3](https://github.com/user-attachments/assets/df9e3798-6b7c-4c96-b41f-63638be73efb)

![10결과4](https://github.com/user-attachments/assets/eacd7fbe-4290-4bbf-8d3f-39556c3c7865)

![10결과2](https://github.com/user-attachments/assets/1ec0ad76-8d19-4ed4-9d20-0f614e72a902)

---
