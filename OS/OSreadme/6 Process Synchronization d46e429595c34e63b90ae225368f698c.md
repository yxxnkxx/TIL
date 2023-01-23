# 6. Process Synchronization

### 데이터의 접근

![7FFCC432-2A33-4746-9A6F-CAF62C21111F.jpeg](6%20Process%20Synchronization%20d46e429595c34e63b90ae225368f698c/7FFCC432-2A33-4746-9A6F-CAF62C21111F.jpeg)

### Race Condition

![92570141-5B22-4F84-8C48-E2A4B602B402.jpeg](6%20Process%20Synchronization%20d46e429595c34e63b90ae225368f698c/92570141-5B22-4F84-8C48-E2A4B602B402.jpeg)

S-box(Memory, Address Space)를 공유하는 E-box(CPU, Process)가 여럿 있는 경우 Race Condition의 가능성이 있음

CPU → Multiprocessor system

Process → 공유 메모리를 사용하는 프로세스들 커널 내부 데이터를 접근하는 루틴들 간 (예: 커널모드 수행 중 인터럽트로 커널모드 다른 루틴 수행시)

### OS에서 race condition은 언제 발생하는가?

1. kernel 수행 중 인터럽트 발생 시
2. Process가 system call을 하여 kernel mode로 수행 중인데 context switch 발생
3. Multiprocessor shared memory 내의 kernel data

**1 kernel vs interrupt handler**
![Untitled](6%20Process%20Synchronization%20d46e429595c34e63b90ae225368f698c/Untitled.png)

interrupt가 들어오면 kernel 작업을 멈추고 interrupt handler 실행

→ interrupt handler도 kernel 작업

양쪽 다 커널 코드이므로 kernel address space 공유 → interrupt handler에서 1 감소한 건 저장되지 않고, kernel에 저장되었던 값에 +1만 처리됨

해결: 공유 데이터를 작업하는 동안에 interrupt를 막

**2 Preempt a process running in kernel?**

![Untitled](6%20Process%20Synchronization%20d46e429595c34e63b90ae225368f698c/Untitled%201.png)

두 프로세스의 address space 간에는 data sharing X

그러나 system call을 하는 동안에 kernel address space의 data를 access (share)

이 작업 중간에 CPU를 preempt 해가면 race condition 발생

![Untitled](6%20Process%20Synchronization%20d46e429595c34e63b90ae225368f698c/Untitled%202.png)

본인의 코드를 실행하다가 system call을 하던 중에 할당 시간이 넘어가서 CPU를 빼앗김 → 이때 race condition

**해결책**

- **커널 모드에서 수행 중**일 때는 **CPU를 preempt하지 않음**
- 커널 모드에서 사용자 모드로 돌아갈 때 preempt

**3 Multiprocessor**

![Untitled](6%20Process%20Synchronization%20d46e429595c34e63b90ae225368f698c/Untitled%203.png)

어떤 CPU가 마지막으로 count를 store했는가? → race condition

multiprocessor의 경우 interrupt enable/disable로 해결되지 않음

**해결책**

1. 한번에 하나의 CPU만 커널에 들어갈 수 있게 하는 방법
2. 커널 내부에 있는 각 공유 데이터에 접근할 때마다 그 데이터에 대한 lock/unlock을 하는 방법

## Process Synchronization 문제

- 공유 데이터(shared data)의 동시 접근(concurrent access)은 데이터의 불일치 문제(inconsistency)를 발생시킬 수 있다.
- 일관성 유지를 위해서는 협력 프로세스(cooperatin process) 간의 실행 순서(orderly execution)를 정해주는 메커니즘 필요

**Race condition**

- 여러 프로세스들이 동시에 공유 데이터를 접근하는 상황
- 데이터의 최종 연산 결과는 마지막에 그 데이터를 다룬 프로세스에 따라 달라짐

race condition을 막기 위해서는 concurrent process는 동기화(synchronize)되어야 한다

![Untitled](6%20Process%20Synchronization%20d46e429595c34e63b90ae225368f698c/Untitled%204.png)

### The Cirical-Section Problem

n개의 프로세스가 공유 데이터를 동시에 사용하기를 원하는 경우

각 프로세스의 code segment에는 공유 데이터를 접근하는 코드인 critical section이 존재

🟡 Problem

- 하나의 프로세스가 critical section에 있을 때 다른 모든 프로세스는 critical section에 들어갈 수 없어야 한다

![Untitled](6%20Process%20Synchronization%20d46e429595c34e63b90ae225368f698c/Untitled%205.png)

### Initial Attempts to Solve Problem

두 개의 프로세스가 있다고 가정 P0, P1

프로세스들의 일반적인 구조

![Untitled](6%20Process%20Synchronization%20d46e429595c34e63b90ae225368f698c/Untitled%206.png)

프로세스들은 수행의 동기화(synchronize)를 위해 몇몇 변수를 공유할 수 있다 → synchronization variable

entry section: critical section에 들어가기 전에 lock을 건다

exit section: critical section에서 나오면 lock을 푼다

### 프로그램적 해결법의 충족 조건

🟡 Mutual Exclusion (상호 배제)

- 프로세스 Pi가 ciritcal section 부분을 수행 중이면 다른 모든 프로세스들은 그들의 critical section에 들어가면 안 된다

🟡 Progress

- 아무도 critical sectionm에 있지 않은 상태에서 critical section에 들어가고자 하는 프로세스가 있으면 들어가게 해주어야 한다

🟡 Bounded Waiting (유한 대기)

- 프로세스가 critical section에 들어가려고 요청한 후부터 그 요청이 허용할 때까지 다른 프로세스들이 critical section에 들어가는 횟수에 한계가 있어야 한다

**가정**

- 모든 프로세스의 수행 속도는 0보다 크다
- 프로세스들 간의 상대적인 수행 속도는 가정하지 않는다

critical section에 들어갔다 나가는 걸 한번에 수행하면 문제X

but, 단일 instruction이 아니어서 수행 도중에 CPU를 빼앗길 수 있어서 문제

### Algorithm 1

![Untitled](6%20Process%20Synchronization%20d46e429595c34e63b90ae225368f698c/Untitled%207.png)

P0을 위한 코드 → critical section에 들어가기 전에 turn을 확인

- `while (turn ≠ 0)` : turn이 0이 아니라면 기다림
- turn이 0이 되면 critical section으로 들어간다
- critical section이 끝나면 turn을 1로 바꿈

🟡 Mutual Exclusion 만족

🟡 Progress 조건 만족x

- critical section을 교대로 들어가도록 설계되어 있음 → 한쪽이 critical section에 들어갔다 나와야 다음 turn이 돌아온다

### Algorithm 2

![Untitled](6%20Process%20Synchronization%20d46e429595c34e63b90ae225368f698c/Untitled%208.png)

flag 값 존재

- critical section에 들어가기 위해 flag를 true로 설정
- `while (flag[j])`상대방이 들어가 있는지 확인
- critical section이 끝나면 flag를 false로

🟡 Progress 조건 만족x

둘다 2행까지 수행 후 끊임 없이 양보하는 상황 발생 가능

### Algorithm 3 (Peterson’s Algorithm)

![Untitled](6%20Process%20Synchronization%20d46e429595c34e63b90ae225368f698c/Untitled%209.png)

Meets all three requirements; solves the critical section problem for two processes

`while (flag[j] && turn ==j)` j의 flag가 true이고, j의 turn인 상태에만 기다린다

→ 3가지 조건 모두 만족

Busy Waiting! (계속 CPU와 memory를 쓰면서 wait)

- 본인의 CPU 할당 시간에 while문의 조건이 만족한다면, CPU와 memory 낭비
- 쓸데없이 while문을 반복하면서 할당 시간을 쓰게 됨

### Synchronization Hardware

하드웨어적으로 Test & modify를 atomic하게 수행할 수 있도록 지원하는 경우 앞의 문제는 간단히 해결

![Untitled](6%20Process%20Synchronization%20d46e429595c34e63b90ae225368f698c/Untitled%2010.png)

원래 값을 읽어내고, 그 값을 1로 세팅

Mutual Exclusion with Test & Set

![Untitled](6%20Process%20Synchronization%20d46e429595c34e63b90ae225368f698c/Untitled%2011.png)

## Semaphores

앞의 방식들을 추상화시킴

Semaphore S

- integer variable (자원의 개수)
- 아래의 두 가지 atomic 연산에 의해서만 접근 가능

![Untitled](6%20Process%20Synchronization%20d46e429595c34e63b90ae225368f698c/Untitled%2012.png)

- P 연산: 자원 획득
- V 연산: 자원 반납

### Critical Section of n Processes

![Untitled](6%20Process%20Synchronization%20d46e429595c34e63b90ae225368f698c/Untitled%2013.png)

lock 걸기: P 연산, lock 풀기: V 연산

busy-wait는 효율적이지 못함 (spin lock)

Block & Wakeup 방식의 구현 (sleep lock)

![Untitled](6%20Process%20Synchronization%20d46e429595c34e63b90ae225368f698c/Untitled%2014.png)

### Block / Wakeup Implementation

Semaphore를 다음과 같이 정의

![Untitled](6%20Process%20Synchronization%20d46e429595c34e63b90ae225368f698c/Untitled%2015.png)

- block: 커널은 block을 호출한 프로세스를 suspend 시킴, 이 프로세스의 PCB를 semaphore에 대한 wait queue에 넣음
- wakeup(P): block된 프로세스 P를 wakeup 시킴, 이 프로세스의 PCB를 ready queue로 옮김

![Untitled](6%20Process%20Synchronization%20d46e429595c34e63b90ae225368f698c/Untitled%2016.png)

Semaphore 연산

![Untitled](6%20Process%20Synchronization%20d46e429595c34e63b90ae225368f698c/Untitled%2017.png)

- P 연산
    - 자원의 여분이 있다면 획득
    - 여분이 없으면 block 상태
- V 연산
    - 자원을 다 쓰고 반납
    - 자원을 기다리면서 잠들어 있는 프로세스가 있다면 wakeup

### Which is better?

- Busy-wait vs Block/wakeup
- Block/wakeup overhead vs Critical section 길이
    - Critical section의 길이가 긴 경우 - Block/Wakeup이 적당
    - Critical section의 길이가 짧은 경우 - Block/Wakeup의 오버헤드가 busy-wait 오버헤드보다 커질 수 있음
    - 일반적으로는 Block/wakeup 방식이 더 좋음

### Two Types of Semaphores

- Counting semaphore
    - 도메인이 0 이상인 임의의 정수값
    - 주로 resource counting에 사용
- Binary semaphore (=mutex)
    - 0 또는 1 값만 가질 수 있는 semaphore
    - 주로 mutual exclusion (lock/unlock)에 사용

### Deadlock and Starvation

**Deadlock**

- 둘 이상의 프로세스가 서로 상대방에 의해 충족될 수 있는 event를 무한히 기다리는 현상

eg. S와 Q가 1로 초기화된 semaphore

![Untitled](6%20Process%20Synchronization%20d46e429595c34e63b90ae225368f698c/Untitled%2018.png)

두 프로세스가 하나씩 쥐고 상대방이 가진 것을 기다리면서 영원히 기다림

해결: 자원이 획득하는 순서를 같게 조정 (S → Q 획득)

**Starvation**

- indefinite blocking: 프로세스가 suspend된 이유에 해당하는 세마포어 큐에서 빠져나갈 수 없는 현상

### Dining-Philosophers Problem

![Untitled](6%20Process%20Synchronization%20d46e429595c34e63b90ae225368f698c/Untitled%2019.png)

## Classical Problems of Synchronization

### Bounded-Buffer Problem (Producer-Consumer Problem)

![Untitled](6%20Process%20Synchronization%20d46e429595c34e63b90ae225368f698c/Untitled%2020.png)

Shared data

- buffer 자체 및 buffer 조작 변수 (empty/full buffer의 시작 위치)

생산자가 둘이 동시에 도착해서, 비어 있는 버퍼를 동시에 데이터를 만들어서 넣으면 문제 발생

소비자도 마찬가지 → 둘이 동시에 데이터를 사용하려고 할 때

→ 버퍼에 락을 걸어서 자원을 사용

**생산자**

버퍼가 유한: 버퍼가 다 찬 상태 → 생산자가 데이터를 만들고 싶은 상황, 생산자 입장에서는 사용할 수 있는 자원이 없음

버퍼의 개수가 N개 → 버퍼 N개를 가득 채우면 생산자가 도착하여도 데이터를 채워넣을 빈 버퍼가 없다

→ 소비자가 내용을 꺼내가야 빈 버퍼가 생긴다

생산자 입장: 빈 버퍼=자원

**소비자**

소비자 입장: 버퍼가 빈 상태 → 자원이 없음

내용이 들어 있는 버퍼 = 자원

생산자 프로세스가 내용을 만들어 넣을 때까지 기다려야 한다

**Synchronization variables**

- mutual exclusion → Need binary semaphore (shared data의 mutual exclusion을 위해)
- resource count → Need integer semaphore (남은 full/empty buffer의 수 표시)

![Untitled](6%20Process%20Synchronization%20d46e429595c34e63b90ae225368f698c/Untitled%2021.png)

mutex: 공유 버퍼를 1개만 접근할 수 있게 하는 semaphore (락을 건다)

### Readers-Writers Problem

한 process가 DB에 write 중일 때 다른 process가 접근하면 안됨

read는 동시에 여럿이 해도 됨

solution

- Writer가 DB에 접근 허가를 아직 얻지 못한 상태에서는 모든 대기중인 Reader들을 다 DB에 접근하게 해준다
- Writer는 대기중인 Reader가 하나도 없을 때 DB 접근이 허용된다
- 일단 Writer가 DB에 접근 중이면 Reader들은 접근이 금지된다
- Writer가 DB에서 빠져나가야만 Reader의 접근이 허용된다

**Shared Data**

- DB 자체
- readcount (현재 DB에 접근 중인 Reader의 수)

**Synchronization variables**

- mutex
    - 공유 변수 readcount를 접근하는 코드(critical section)의 mutual exclusion 보장을 위해 사용
- db
    - Reader와 writer가 공유 DB 자체를 올바르게 접근하는 역할

![Untitled](6%20Process%20Synchronization%20d46e429595c34e63b90ae225368f698c/Untitled%2022.png)

writer를 막기 위해 읽을 때도 lock을 걸어야 함

+reader가 함께 읽을 수 있게 해야 한다 → readcount

readcount: 공유 변수 → 여러 reader가 동시에 증가시키면 문제 발생

- readcount라는 공유 변수에 대한 lock = mutex 변수

readcount가 0일 때 lock을 풀어준다

read 중 → writer 도착 → reader 도착

reader는 read 중에 데이터에 접근 가능하기 때문에 기다리지 않음

writer는 모든 reader가 빠져나갈 때까지 기다려야 한다

→ starvation 발생 가능

### Dining-Philosophers Problem

![Untitled](6%20Process%20Synchronization%20d46e429595c34e63b90ae225368f698c/Untitled%2023.png)

**Synchronization variables**

- semaphore chopstick[5];
    - 초기에 모든 values = 1

![Untitled](6%20Process%20Synchronization%20d46e429595c34e63b90ae225368f698c/Untitled%2024.png)

왼쪽 젓가락을 잡고, 오른쪽 젓가락을 가짐

→ 젓가락이 공유 자원

문제점

- Deadlock 가능성
- 모든 철학자가 동시에 배가 고파져 왼쪽 젓가락을 집어버린 경우

해결방안

- 4명의 철학자만이 테이블에 동시에 앉을 수 있도록 한다
- 젓가락을 두 개 모드 집을 수 있을 때에만 젓가락을 집을 수 있게 한다
- 비대칭
    - 짝수(홀수) 철학자는 왼쪽(오른쪽) 젓가락부터 집도록

![Untitled](6%20Process%20Synchronization%20d46e429595c34e63b90ae225368f698c/Untitled%2025.png)

```jsx
semaphore self[i] // 다섯 명의 철학자가 젓가락을 잡을 수 있는지 없는지
self[i]=1 // 잡을 수 있음

state[5] // 다섯 명의 철학자의 상태 -> 다른 철학자의 상태도 변화시킬 수 있음
mutex -> state에 대한 lock 변수
```

왼쪽 철학자, 오른쪽 철학자가 모두 eating이 아니고 내가 hungry일 때 → state를 eating으로

→ V 연산 (self[i]= 0 → 1)

pickup 함수의 P연산 - 인접한 철학자가 밥을 먹고 있으면 self[i] = 0, P 연산을 하기 위해 self[i]가 1이 될 때까지 기다림 (인접한 철학자가 self 상태를 변화시킬 수 있음)

## Monitor

🟡 semaphore의 문제점

- 코딩하기 힘들다
- 정확성의 입증이 어렵다
- 자발적 협력(voluntary cooperation)이 필요하다
- 한번의 실수가 모든 시스템에 치명적 영향

Monitor: 동시 수행중인 프로세스 사이에서 abstract data type의 안전한 공유를 보장하기 위한 high-level synchronization construct

![Untitled](6%20Process%20Synchronization%20d46e429595c34e63b90ae225368f698c/Untitled%2026.png)

![Untitled](6%20Process%20Synchronization%20d46e429595c34e63b90ae225368f698c/Untitled%2027.png)

monitor 내부의 procedure를 통해서만 공유데이터를 접근 가능

기본적으로 모니터 내부의 procedure는 동시 접근을 허용하지 않음

→ lock을 걸 필요가 없다

**monitor**

모니터 내에서는 한번에 하나의 프로세스만이 활동 가능

프로그래머가 동기화 제약 조건을 명시적으로 코딩할 필요 없음

프로세스가 모니터 안에서 기다릴 수 있도록 하기 위해 condition variable 사용

```jsx
condition x,y;
```

Condition variable은 wait와 signal 연산에 의해서만 접근 가능

`x.wait();`

x.wait()를 invoke한 프로세스는 다른 프로세스가 x.signal()을 invoke하기 전까지 suspend된다

`x.signal();`

x.signal()은 정확하게 하나의 suspend된 프로세스를 resume

suspend된 프로세스가 없으면 아무 일도 일어나지 않는다

### Bounded-buffer

![Untitled](6%20Process%20Synchronization%20d46e429595c34e63b90ae225368f698c/Untitled%2028.png)

full: 내용이 들어있는 버퍼를 기다리는 process를 줄 세움

empty: 빈 버퍼를 기다리는 process를 줄 세움

생산자, 소비자 모두 하나의 프로세스만 모니터 안에서 활성화 → 락을 걸 필요가 없다 

생산자: empty buffer가 없다면 empty.wait() → 빌 때까지 기다린다

→ 작업이 끝나면 full.signal()로 깨워주기

Process Synchronization(프로세스 동기화)

Concurrency Control(병행 제어)

### Dining Philosophers Example

![Untitled](6%20Process%20Synchronization%20d46e429595c34e63b90ae225368f698c/Untitled%2029.png)

pickup, putdown: 모니터 내부 코드