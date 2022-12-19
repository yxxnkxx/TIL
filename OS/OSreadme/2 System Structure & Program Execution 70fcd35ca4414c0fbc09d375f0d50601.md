# 2. System Structure & Program Execution

## 컴퓨터 시스템 구조

![Untitled](2%20System%20Structure%20&%20Program%20Execution%2070fcd35ca4414c0fbc09d375f0d50601/Untitled.png)

![Untitled](2%20System%20Structure%20&%20Program%20Execution%2070fcd35ca4414c0fbc09d375f0d50601/Untitled%201.png)

메모리: CPU의 작업 공간

IO 디바이스를 컨트롤 하는 작은 CPU같은 것 = device controller

→ device controller가 디스크의 내부를 통제함

device controller의 작업 공간 = 로컬 버퍼

### CPU

CPU: 메모리에서 하나씩 읽어서 실행

- **registers**: 메모리보다 빠른 작은 저장공간
- **mode bit**: CPU에서 실행되는 것이 운영체제인지, 사용자 프로그램인지 구분
- **interrupt line**: cpu는 메모리에 있는 instruction만 실행, 다음 instruction의 주소값이 증가 → cpu에 IO장치의 입출력이 끝났음을 알려주는 것
  - 키보드 입력, 화면 출력, 디스크 입출력 등 → CPU는 직접 접근X, 메모리를 접근하는 instruction만 실행
  - 프로그램 A가 디스크에서 뭔가 읽어와야 하면 → CPU가 device controller에 요청 → 디스크가 값을 읽어옴 → 로컬 버퍼에 저장 → CPU는 이걸 기다리는 동안 다른 작업 수행
- **timer**

CPU는 빠른 일꾼, 빠른 속도를 이용해서 메모리를 읽어가며 일을 함 → 아주 짧은 시간 간격으로 프로그램 여러 개를 왔다갔다하며 실행, 사용자 입장에서 interactive하게 보임

### Mode bit

사용자 프로그램의 잘못된 수행으로 다른 프로그램 및 운영체제에 피해가 가지 않도록 하기 위한 보호 장치 필요

**0 : 모니터 모드(커널 모드, 시스템 모드)** : OS 코드 수행

- 메모리 접근 + I/O device 접근 등 모든 작업 수행 가능
- 보안을 해칠 수 있는 중요한 명령어는 모니터 모드에서만 수행 가능한 ‘**특권 명령**’으로 규정

**1 : 사용자 모드 :** 사용자 프로그램 수행

- 제한된 instruction만 실행 가능

Interrupt나 Exception 발생 시 하드웨어가 mode bit을 0으로 바꿈

사용자 프로그램에게 CPU를 넘기기 전에 mode bit을 1로 세팅

### Timer

정해진 시간이 흐른 뒤 운영체제에게 제어권이 넘어가도록 인터럽트를 발생시킴

타이머는 매 클럭 틱 때마다 1씩 감소

타이머 값이 0이 되면 타이머 인터럽트 발생

CPU를 특정 프로그램이 독점하는 것으로부터 보호

- 무한 루프를 도는 프로그램: CPU가 time sharing을 할 수 없게 됨
- 특정 시간이 지나면 timer가 CPU에 interrupt를 건다
- CPU는 instruction 실행 → interrupt line 확인 → interrupt line이 있으면 하던 일 중단 → OS에 CPU의 제어권이 넘어옴→ OS: 다음 프로그램에 CPU를 넘겨줌
- cf) I/O에서 어떻게 제어권이 돌아오는가?
  - I/O를 해야하는 상황에서는 **프로그램이 스스로 OS에 CPU 제어권을 넘김**, OS가 I/O controller에 일을 시킴, I/O 작업이 오래 걸리니까 다른 프로그램에 CPU를 넘김
  - I/O를 해야하는 프로그램은 I/O 컨트롤러가 요청한 작업이 끝남 → 컨트롤러가 CPU에 interrupt → OS에 제어권이 넘어감 → OS가 입력된 값을 프로그램 메모리 공간에 copy, 원래 진행 중인 프로그램에 할당된 시간이 끝나지 않았다면 다시 CPU를 줌 → I/O 프로그램에게 순서가 돌아오면 작업 수행

### Device Controller

I/O device controller

- 해당 I/O 장치 유형을 관리하는 일종의 작은 CPU
- 제어 정보를 위해 control register, status register를 가짐
- data를 담는 local buffer를 가짐 (일종의 data register)

I/O는 실제 device와 local buffer 사이에서 일어남

Device controller는 I/O가 끝났을 경우 interrupt로 CPU에 그 사실을 알림

**용어 차이**

device driver(장치구동기)

- OS 코드 중 각 장치별 처리루틴 → software

device controller(장치제어기)

- 각 장치를 통제하는 일종의 작은 CPU → hardware

CPU: 로컬 버퍼, 메모리에 접근 가능

작은 CPU들(device controller) : 자신의 로컬 버퍼만 접근 가능

→ CPU가 interrupt를 너무 많이 당함, CPU가 효율적x

→ **DMA Controller**

### DMA Controller

Direct Memory Access Controller

원래는 메모리를 접근할 수 있는 장치가 CPU뿐,

DMA도 메모리 접근 가능

memory controller: DMA와 CPU가 동시에 접근하는 것을 처리

**CPU의 오버헤드 방지** → 로컬 버퍼에 들어오는 내용에 대해 DMA가 직접 메모리에 데이터를 복사해줌 → CPU에 interrupt를 한 번만 걸어준다

- 빠른 입출력 장치를 메모리에 가까운 속도로 처리하기 위해 사용
- CPU의 중재 없이 device controller가 device의 buffer storage의 내용을 메모리에 block 단위로 직접 전송
- 바이트 단위가 아니라 block 단위로 인터럽트를 발생시킴

### 입출력(I/O)의 수행

모든 입출력 명령은 **특권 명령 →** 사용자 프로그램이 직접 입출력x, 운영체제를 통해서만 가능

사용자 프로그램은 어떻게 I/O를 하는가?

- **시스템콜**(system call)
  - 사용자 프로그램이 운영체제에 커널 함수를 호출하는 것
  - 사용자 프로그램은 운영체제에게 I/O 요청
  - 프로그램이 직접 interrupt를 건다 → CPU 제어권이 운영체제로 넘어
- trap을 사용하여 인터럽트 벡터의 특정 위치로 이동
- 제어권이 인터럽트 벡터가 가리키는 인터럽트 서비스 루틴으로 이동
- 올바른 I/O 요청인지 확인 후 I/O 수행
- I/O 완료 시 제어권을 시스템콜 다음 명령으로 옮

### 인터럽트

인터럽트 당한 시점의 레지스터와 program counter를 save한 후 CPU의 제어를 인터럽트 처리 루틴에 넘긴다

- Interrupt(넓은 의미)
  - Interrupt(하드웨어 인터럽트): 하드웨어가 발생시킨 인터럽트
  - Trap(소프트웨어 인터럽트)
    - Exception: 프로그램이 오류를 범한 경우
    - System call: 프로그램이 커널 함수를 호출하는 경우

인터럽트 관련 용어

인터럽트 종류마다 해야할 일이 정의되어 있음

- 인터럽트 벡터
  - 해당 인터럽트의 처리 루틴 주소를 가지고 있음 (어디 있는 함수를 실행해야 하는지)
- 인터럽트 처리 루틴 (=Interrupt Service Routine, 인터럽트 핸들러)
  - 해당 인터럽트를 처리하는 커널 함수
  - 실제 인터럽트를 처리

I/O 요청 → 소프트웨어 인터럽트 → OS가 I/O 명령 → I/O 끝남 → 하드웨어 인터럽트

현대의 운영체제는 인터럽트에 의해 구동됨

### 시스템 콜(System call)

사용자 프로그램이 운영체제의 서비스를 받기 위해 커널 함수를 호출하는 곳

---

## 동기식 입출력과 비동기식 입출력

### 동기식 입출력 (synchronous I/O)

I/O 요청 후 입출력 **작업이 완료**된 후에야 제어가 사용자 프로그램에 넘어감

구현 방법 1

- I/O가 끝날 때까지 CPU를 낭비시킴
- 매시점 하나의 I/O만 일어날 수 있음

구현 방법 2

- I/O가 완료될 때까지 해당 프로그램에게서 CPU를 빼앗음
- I/O 처리를 기다리는 줄에 그 프로그램을 줄 세움

### 비동기식 입출력 (asynchronous I/O)

I/O가 시작된 후 입출력 작업이 끝나기를 기다리지 않고 제어가 사용자 프로그램에 즉시 넘어감

ex) write → write했는지 확인하지 않고 다른 프로그램 진행

⭐ 두 경우 모두 I/O의 완료는 인터럽트로 알려줌

![Untitled](2%20System%20Structure%20&%20Program%20Execution%2070fcd35ca4414c0fbc09d375f0d50601/Untitled%202.png)

## 서로 다른 입출력 명령

![Untitled](2%20System%20Structure%20&%20Program%20Execution%2070fcd35ca4414c0fbc09d375f0d50601/Untitled%203.png)

- I/O를 수행하는 special instruction에 의해
  - 메모리 접근하는 instruction과 I/O 수행 instruction이 별개 (왼쪽 그림)
- Memory Mapped I/O에 의해
  - I/O 장치도 메모리 주소의 연장 주소를 붙여서 함께 수행

## 저장장치 계층 구조

![Untitled](2%20System%20Structure%20&%20Program%20Execution%2070fcd35ca4414c0fbc09d375f0d50601/Untitled%204.png)

위로 갈수록 속도가 빠름, Cost ↑

Volatility = 휘발성, 위로 갈수록 휘발성이 높음

Primary: CPU가 직접 접근=byte단위로 접근 가능

↔ 하드디스크: sector 단위 접근

Cache memory: 빠른 매체로 정보를 읽어들여 쓰는 것 - 재사용 목

## 프로그램의 실행 (메모리 load)

![Untitled](2%20System%20Structure%20&%20Program%20Execution%2070fcd35ca4414c0fbc09d375f0d50601/Untitled%205.png)

파일 시스템의 실행 파일 → virtual memory → Physical memory: 프로세스

![Untitled](2%20System%20Structure%20&%20Program%20Execution%2070fcd35ca4414c0fbc09d375f0d50601/Untitled%206.png)

파일만의 독자적인 주소 공간이 만들어짐 → code, data, stack으로 구성

커널 영역: 컴퓨터 부팅 후 메모리에 상주

다른 주소 공간: 프로그램 실행 시에 메모리에 올림, 끝나면 사라짐

virtual memory 전부를 물리적 메모리에 올려놓지 않음 → 현재 실행되는 코드만 올려둠 (당장 필요한 부분)

나머지는 디스크의 Swap area에 내려와있다

주소 변환: virtual memory → 물리적 메모리

주소 변환 계층: 하드웨어의 지원을 받음

### 커널 주소 공간의 내용

![Untitled](2%20System%20Structure%20&%20Program%20Execution%2070fcd35ca4414c0fbc09d375f0d50601/Untitled%207.png)

### 사용자 프로그램이 사용하는 함수

**함수**

- 사용자 정의 함수
  - 자신의 프로그램에서 정의한 함수
- 라이브러리 함수
  - 자신의 프로그램에서 정의하지 않고 갖다 쓴 함수
  - 자신의 프로그램의 실행 파일에 포함되어 있다

![Untitled](2%20System%20Structure%20&%20Program%20Execution%2070fcd35ca4414c0fbc09d375f0d50601/Untitled%208.png)

- 커널 함수
  - 운영체제 프로그램의 함수
  - 커널 함수의 호출 = 시스템 콜

![Untitled](2%20System%20Structure%20&%20Program%20Execution%2070fcd35ca4414c0fbc09d375f0d50601/Untitled%209.png)

### 프로그램의 실행

![Untitled](2%20System%20Structure%20&%20Program%20Execution%2070fcd35ca4414c0fbc09d375f0d50601/Untitled%2010.png)
