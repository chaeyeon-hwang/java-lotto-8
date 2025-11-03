# java-lotto-precourse


---
# 프리코스 3주차 미션 - 로또
---
우아한테크코스 8기 프리코스 3주차 미션, 로또를 구현한 저장소입니다.

---
## 개요
### 프로젝트 컨셉
입력받은 구입 금액, 당첨 번호, 보너스 번호를 기반으로 자동으로 로또를 발행하고 당첨 내역 및 수익률을 계산하는 로또 발매기 프로그램입니다.

로또 한 장은 1,000원이며 1~45 사이의 중복되지 않은 6개 숫자로 구성됩니다.
당첨 번호와 보너스 번호를 비교하여 일치 개수에 따라 등수를 판정하고 총 수익률을 계산하여 소수점 둘째 자리까지 반올림하여 출력합니다.

잘못된 입력(금액 단위 오류, 번호 개수 불일치, 숫자 범위 초과 등)이 들어올 경우 IllegalArgumentException을 발생시키고 [ERROR]로 시작하는 에러 메시지를 출력한 뒤 재입력받습니다.

### 프로그래밍 요구사항
* 들여쓰기는 2단계까지만 허용합니다.
  - 예를 들어 while문 안에 if문이 있으면 들여쓰기는 2입니다.
  - depth를 줄이는 좋은 방법은 함수(또는 메서드)를 분리하면 됩니다.
* 프로그램 종료 시 System.exit()를 호출하지 않습니다.
* 3항 연산자를 쓰지 않습니다.
  - 조건문을 if문으로 작성합니다.
* else 예약어를 사용하지 않습니다.
  - switch/case도 허용하지 않습니다.
* 3개 이상의 인스턴스 변수를 가진 클래스는 구현하지 않습니다.
* 이름을 통해 의도를 드러냅니다. 축약하지 않습니다.
* 코드 포매팅을 사용합니다.
* Java에서 제공하는 API를 적극 활용합니다.
* 배열 대신 컬렉션을 사용합니다.

### 학습 목표
* 관련 함수를 묶어 클래스를 만들고, 객체들이 협력하여 하나의 큰 기능을 수행하도록 합니다.
* 값을 하드 코딩하지 않습니다. 대신 상수(static final)를 정의하고 의미 있는 이름을 부여합니다.
* 변수 이름에 자료형은 사용하지 않습니다.
* 한 메서드가 한 가지 기능만 담당하게 합니다. 함수(또는 메서드)의 길이가 15라인을 넘어가지 않도록 구현합니다.
* 클래스는 상수, 멤버 변수, 생성자, 메서드 순으로 작성합니다.
* Java Enum을 적용하여 프로그램을 구현합니다.
---
&nbsp;&nbsp;&nbsp;<**단위테스트 관련하여**>
* 테스트 작성을 학습 도구로 활용합니다. 
* 클래스와 함수에 대한 단위 테스트를 통해 의도한 대로 정확하게 작동하는 영역을 확보합니다.
* 처음부터 큰 단위의 테스트를 만들지 않습니다.
* JUnit 5와 AssertJ를 이용하여 정리한 기능 목록이 정상적으로 작동하는지 테스트 코드로 확인합니다.
* 구현한 기능에 대한 단위 테스트를 작성합니다. (단, UI(System.out, System.in, Scanner) 로직은 제외합니다.)



### 사용 라이브러리

* camp.nextstep.edu.missionutils에서 제공하는 Randoms 및 Console API를 사용하여 구현합니다.
* Random 값 추출은 camp.nextstep.edu.missionutils.Randoms의 pickUniqueNumbersInRange()를 활용합니다.
* 사용자가 입력하는 값은 camp.nextstep.edu.missionutils.Console의 readLine()을 활용합니다.


### 클래스 구조도 (클래스 간 흐름)
lotto/
 Application<br>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▶InputView 사용자 입력 받음<br>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▶ LottoMachine 구입금액 검증과 로또 생성<br>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▶ Lotto 번호 유효성 검증<br>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▶ WinningLotto  당첨 번호 및 보너스 번호 관리<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▶ Rank 등수/상금 판별   <br>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▶ ResultCalculator  결과 집계와 수익률 계산   <br>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▶ OutputView  결과 출력<br>
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;▶ ExceptionHandler 모든 클래스에서 공통 예외 처리



---

## 프로그램 흐름
**1️⃣ 구입 금액 입력 기능**
* 사용자는 구입 금액을 입력합니다.
* 입력값은 Console.readLine()을 통해 문자열로 받아 int로 변환합니다.
* 금액은 1,000원 단위로만 입력 가능하며 1,000으로 나누어떨어지지 않거나 0 이하일 경우 예외를 발생시킵니다.


**2️⃣ 로또 발행 기능**
* 입력받은 구입 금액을 기준으로 로또 개수 = 금액 / 1000 계산 후 발행합니다.
* 각 로또는 camp.nextstep.edu.missionutils.Randoms.pickUniqueNumbersInRange(1, 45, 6)
메서드를 통해 1~45 범위의 중복 없는 숫자 6개를 생성합니다.
* 생성된 번호는 오름차순으로 정렬하여 출력합니다.



**3️⃣ 당첨 번호 입력 기능**
* 쉼표(,)로 구분된 문자열을 입력받아 List<Integer>로 변환합니다.
* 숫자는 1~45 범위 내 정수여야 하며, 중복된 값이 있으면 예외가 발생합니다.
* 입력값이 비어 있거나 숫자가 아닌 경우 역시 예외가 발생합니다.


**4️⃣ 보너스 번호 입력 기능**
* 보너스 번호는 단일 정수로 입력받습니다. 1~45 범위 내 숫자여야 하고 당첨 번호 리스트에 포함되어 있으면 예외가 발생합니다.

  
**5️⃣ 당첨 결과 판별 기능**
* 사용자가 구매한 각 로또와 당첨 번호를 비교하여 일치 개수를 계산합니다.
* 일치 개수와 보너스 번호 여부에 따라 등수를 결정합니다.


**6️⃣ 수익률 계산 기능**
* 모든 당첨 결과의 총 상금을 합산합니다. 수익률 = (총 상금 ÷ 구입 금액) × 100로 계산합니다.
* 소수점 둘째 자리에서 반올림하여 출력합니다.
 
 
**7️⃣ 예외 처리 기능** 
* 모든 예외 상황은 ExceptionHandler 클래스를 통해 관리됩니다.
* 각 도메인 클래스에서는 예외를 직접 생성하지 않도록 합니다.
* 예외 메시지는 항상 [ERROR]로 시작하며, 콘솔에 출력 후 재입력을 유도합니다.

**8️⃣ 출력 기능**
* 로또 구매 결과, 당첨 내역, 수익률을 순서대로 출력합니다.
* 모든 숫자 리스트는 오름차순으로 정렬되어 표시됩니다
 
---
## 단위테스트 케이스
  
| 구분                            | 테스트 대상             | 검증 내용                       |
| ----------------------------- | ------------------ | --------------------------- |
| **1. 구입 금액 입력 검증**            | `LottoMachine`     | 1,000원 단위 여부, 0 이하 입력 금지    |
| **2. 로또 번호 생성 검증**            | `Lotto`            | 1~45 범위, 중복 없음, 개수 6개       |
| **3. 당첨 번호 입력 검증**            | `WinningLotto`     | 숫자 포맷, 범위, 중복 확인            |
| **4. 보너스 번호 검증**              | `WinningLotto`     | 보너스 번호 중복, 범위 확인            |
| **5. 등수 판별 로직**               | `Rank (Enum)`      | 일치 개수, 보너스 여부 매핑 확인        |
| **6. 당첨 결과 집계**               | `ResultCalculator` | 등급별 개수, 총 상금 계산             |
| **7. 수익률 계산**                 | `ResultCalculator` | 총 수익률 계산, 반올림 검증            |
| **8. ExceptionHandler 자체 검증** | `ExceptionHandler` | `[ERROR]` prefix, 메시지 포함 여부 |



---
## 단위 테스트 학습내용
  


1. JUnit 5 기본 구조
  
| 어노테이션                        | 역할               | 설명                |
| ---------------------------- | ---------------- | ----------------- |
| `@Test`                      | 테스트 메서드          | 하나의 독립된 테스트 단위    |
| `@BeforeEach` / `@AfterEach` | 테스트 전/후 실행       | 테스트마다 객체 초기화 및 정리 |
| `@BeforeAll` / `@AfterAll`   | 전체 테스트 전/후 1회 실행 | DB 연결 등 공통 자원 준비용 |
| `@DisplayName`               | 테스트 이름 지정        | 테스트 의도를 명확하게 표현   |
| `@ParameterizedTest`         | 반복 테스트           | 여러 입력값으로 같은 검증 수행 |

2. JUnit 기본 Assertions
  
| 메서드                                                | 기능            |
| -------------------------------------------------- | ------------- |
| `assertEquals(expected, actual)`                   | 두 값이 같은지 비교   |
| `assertTrue(condition)` / `assertFalse(condition)` | 조건 검증         |
| `assertThrows(Exception.class, () -> {...})`       | 예외 발생 여부 확인   |
| `assertAll(...)`                                   | 여러 검증을 동시에 수행 |

3. AssertJ
  
| 구조                                           | 설명           |
| -------------------------------------------- | ------------ |
| `assertThat(actual)`                         | 검증 시작점       |
| `.isEqualTo(expected)`                       | 값 비교         |
| `.isNotNull()` / `.isEmpty()`                | null, 빈 값 검증 |
| `.contains(...)`, `.containsExactly(...)`    | 컬렉션 검증       |
| `.startsWith()`, `.endsWith()`, `.matches()` | 문자열 검증       |
| `.isGreaterThan()`, `.isBetween()`           | 숫자 검증        |

4. 예외 테스트 
  
| 메서드                                                   | 설명              |
| ----------------------------------------------------- | --------------- |
| `assertThatThrownBy(() -> {...})`                     | 예외 발생 검증 기본형    |
| `assertThatExceptionOfType(Exception.class)`          | 예외 타입 명시형       |
| `assertThatIllegalArgumentException()`                | 자주 쓰는 예외 단축 메서드 |
| `.hasMessage("...")` / `.hasMessageContaining("...")` | 예외 메시지 검증       |
| `.withMessageMatching("정규식")`                         | 메시지 패턴 검증       |
| `.hasNoCause()`                                       | 예외 원인 없는지 확인    |

5. 파라미터화 테스트 
  
| 어노테이션                                                | 역할             | 예시                    |
| ---------------------------------------------------- | -------------- | --------------------- |
| `@ValueSource`                                       | 단일 값 반복 테스트    | `{1, 2, 3}`           |
| `@EnumSource`                                        | enum 모든 값 테스트  | `Direction.class`     |
| `@CsvSource`                                         | 다중 인자 테스트      | `"1,true", "2,false"` |
| `@MethodSource`                                      | 메서드로부터 인자 제공   | `Stream.of(...)`      |
| `@NullSource`, `@EmptySource`, `@NullAndEmptySource` | null/빈 문자열 테스트 | 입력 검증용                |

