# 은행 관리 프로그램
## 필수 요구 사항
1. 은행은 계좌를 등록한다.
    1. 단, 계좌번호는 정규 표현식으로 제한 
2. 은행은 계좌를 관리(수정/삭제)한다.
3. 은행은 계좌번호로 계좌를 찾을 수 있다.
4. 은행은 계좌의 소유자 명으로 계좌를 찾을 수 있다.
5. 은행은 모든 계좌의 목록을 조회 할 수 있다.
6. 계좌는 소유자 명, 계좌번호, 잔고로 구성된다.
7. 계좌는 입금, 출금 기능이 있다.
8. 계좌는 잔고 확인 기능이 있다.
    1. 계좌 소유주만 잔고 확인이 가능하게
9. 계좌에서 잔고의 변화가 있을 때마다 거래 내역에 기록된다.
10. 계좌의 거래 내역은 거래 일자, 거래 시간, 계좌번호, 입금/출금 여부, 거래 금액, 은행 명으로 구성된다.
11. 계좌는 모든 거래 내역을 조회 할 수 있다.

## 추가 요구 사항 (선택)
1. 입금 금액에 이율을 적용 시킨 잔고가 계산되도록 (부동 소수점 연산 활용) 
    1. 계좌 입금 시 적용 이율 이 표시됨. (ex. 적용 이율 0.01%)
    2. 계좌 잔고 확인 시 잔고(기존 잔고 + 입금 금액 x 적용 이율)가 표시됨.
2. 출금 수수료 계산
3. 계좌 송금 기능

## 프로젝트 구조
![UML](https://user-images.githubusercontent.com/117354616/204943740-37042066-2aad-44e5-9b04-3f822c004c0e.png)
