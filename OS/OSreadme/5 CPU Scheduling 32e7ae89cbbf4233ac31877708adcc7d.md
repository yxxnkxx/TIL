# 5. CPU Scheduling

## CPU and I/O Bursts in Program Execution

![Untitled](5%20CPU%20Scheduling%2032e7ae89cbbf4233ac31877708adcc7d/Untitled.png)

프로그램 실행: CPU burst와 I/O burst가 번갈아가면서 실행

### CPU-burst Time의 분포

![Untitled](5%20CPU%20Scheduling%2032e7ae89cbbf4233ac31877708adcc7d/Untitled%201.png)

여러 종류의 job(process)이 섞여 있기 때문에 CPU 스케줄링이 필요하다 **(특히 I/O bound job)**

- Interactive job(I/O bound)에게 적절한 response 제공 요망
- CPU와 I/O 장치 등 시스템 자원을 골고루 효율적으로 사용

## 프로세스의 특성 분류

프로세스는 그 특성에 따라 다음 두 가지로 나눔

- I/O-bound process
    - CPU를 잡고 계산하는 시간보다 I/O에 많은 시간이 필요한 job
    - many short CPU bursts
- CPU-bound process
    - 계산 위주의 job
    - few very long CPU bursts

## CPU Scheduler & Dispatcher

🟡 **CPU Scheduler**

- Ready 상태의 프로세스 중에서 이번에 CPU를 줄 프로세스를 고른다

🟡 **Dispatcher**

- CPU의 제어권을 CPU scheduler에 의해 선택된 프로세스에게 넘긴다
- 이 과정을 context switch(문맥 교환)이라고 한다

CPU 스케줄링이 필요한 경우: 프로세스에게 다음과 같은 상태 변화가 있을 때

1. Running → Blocked (I/O 요청하는 시스템 콜)
2. Running → Ready (할당 시간 만료로 timer interrupt)
3. Blocked → Ready (I/O 완료 후 인터럽트)
4. Terminate

** 1,4: 비선점형 nonpreemptive(강제로 빼앗지 않고 강제 반납)

나머지: 선점형 scheduling: preemptive(강제로 빼앗음)

## Scheduling Algorithm

### Scheduling Criteria

(Performance Index = Performance Measure)

- **CPU utilization (이용률)**
    - keep the CPU as busy as possible
    - CPU가 일한 시간
- **Throughput (처리량)**
    - \# of processes that complete their execution per time unit
    - 주어진 시간 동안 몇 개의 작업을 완료했는지

*고객(프로세스) 입장의 척도 - ***시간이 중요***

- **Turnaround time (소요시간, 반환시간)**
    - amount of time to execute a particular process
    - CPU burst가 끝날 때까지 걸리는 총 시간
    - CPU를 쓰러 들어와서, I/O를 하러 나갈 때까지 걸리는 시간
    - ≠ 시작 → 종료 시간
- **Waiting time (대기 시간)**
    - amount of time a process has been waiting in the ready queue
    - CPU를 쓰기 위해 기다리는 시간
    - 한 번의 CPU burst 동안에도 CPU를 얻고 뺏기고를 반복 - 대기 시간이 여러 번 발생
    - 대기 시간을 모두 합친 시간
- **Response time (응답 시간)**
    - amount of time it takes from when a request was submitted until the first response is produced, not output
    - (for time-sharing environment)
    - 최초의 CPU를 얻기까지 기다리는 시간

중국집 예시

### FCFS(First-Come First-Served)

먼저 온 프로세스를 먼저 처리

비선점형 스케줄링 (일단 CPU를 얻어오면 빼앗기지 않음)

비효율적

- case 1)

![Untitled](5%20CPU%20Scheduling%2032e7ae89cbbf4233ac31877708adcc7d/Untitled%202.png)

![Untitled](5%20CPU%20Scheduling%2032e7ae89cbbf4233ac31877708adcc7d/Untitled%203.png)

Waiting time for P1=0; P2=24; P3=27

Avaerage waiting time: (0+24+27)/3 = 17

- case 2)

![Untitled](5%20CPU%20Scheduling%2032e7ae89cbbf4233ac31877708adcc7d/Untitled%204.png)

Waiting time for P1 = 6; P2=0; P3=3

Average waiting time = 3

**Convoy effect**: short process behind long process (긴 프로세스 뒤에 짧은 프로세스들이 기다림)

프로세스의 도착 순서에 영향을 많이 받음

### SJF (Shortest-Job-First)

각 프로세스의 다음 번 CPU burst time을 가지고 스케줄링에 활용

CPU burst time이 가장 짧은 프로세스를 제일 먼저 스케줄

Two schemes

- **Nonpreemtive**
    - 일단 CPU를 잡으면 이번 CPU burst가 완료될 때까지 CPU를 선점당하지 않음
- **Preemptive**
    - 현재 수행중인 프로세스의 남은 burst time보다 더 짧은 CPU burst time을 가지는 새로운 프로세스가 도착하면 CPU를 빼앗김
    - SRTF(Shortest-Remaining-Time-First)라고도 부른다

SJF is optimal

- 효율적
- 주어진 프로세스들에 대해 minimum average waiting time을 보장 (특히 Preemptive)

![Untitled](5%20CPU%20Scheduling%2032e7ae89cbbf4233ac31877708adcc7d/Untitled%205.png)

Average waiting time = (0+6+3+7)/4 = 4

![Untitled](5%20CPU%20Scheduling%2032e7ae89cbbf4233ac31877708adcc7d/Untitled%206.png)

Average waiting time = (9+1+0+2)/4 = 3 (이보다 짧은 알고리즘은 없음)

궁금한 점.. non-preemptive에 비해 preemptive는 CPU scheduling을 많이 수행할텐데 이 비용은 고려할 만큼 크지 않은 건가?

### 다음 CPU Burst Time의 예측

다음 CPU burst time을 어떻게 알 수 있는가? (input data, branch, user …)

추정(estimate)만 가능

- 과거의 CPU burst time을 이용해서 추정 (exponential averaging)

![Untitled](5%20CPU%20Scheduling%2032e7ae89cbbf4233ac31877708adcc7d/Untitled%207.png)

1. 실제 CPU 사용 시간
2. 예측된 CPU 사용 시간

![Untitled](5%20CPU%20Scheduling%2032e7ae89cbbf4233ac31877708adcc7d/Untitled%208.png)

a와 (1-a)가 둘다 1 이하이므로 후속 term은 선행 term보다 적은 가중치 값을 가진다

### Priority Scheduling

A priority number(integer) is associated with each process

highest priority를 가진 프로세스에게 CPU 할당

(smallest integer = highest priority)

- preemptive
- nonpreemptive

ex. SJF (priority = predicted next CPU burst time)

Problem

- **starvation**: low priority processes may never execute

Solution

- Aging: as time progresses increase the priority of the process

### Round Robin (RR)

현대 CPU 스케줄링은 RR에 기반한다

각 프로세스는 동일한 크기의 할당 시간(time quantum)을 가짐 (일반적으로 10-100 milliseconds)

할당 시간이 지나면 프로세스는 선점당하고 ready queue의 제일 뒤에 가서 다시 줄을 선다

n개의 프로세스가 ready queue에 있고 할당 시간이 q time unit인 경우, 각 프로세스는 최대 q time unit 단위로 CPU 시간의 1/n을 얻는다.

→ **어떤 프로세스도 (n-1)\*q time unit 이상 기다리지 않는다**

장점: 응답 시간이 빨라진다

대기 시간은 CPU의 사용 시간에 비례한다

Performance

- q large → FCFS
- q small → context switch. 오버헤드가 커진다

ex. time quantum = 20

![Untitled](5%20CPU%20Scheduling%2032e7ae89cbbf4233ac31877708adcc7d/Untitled%209.png)

![Untitled](5%20CPU%20Scheduling%2032e7ae89cbbf4233ac31877708adcc7d/Untitled%2010.png)

일반적으로 SJF보다 average turnaround time이 길지만 response time은 더 짧다

**CPU 사용시간이 모두 동일한 프로세스가 여러 개 있다면, 모든 프로세스가 기다렸다가 동시에 빠져나가게 됨 → waiting time이 길어질 수 있다

일반적으로는 짧은 프로세스와 긴 프로세스가 섞여 있어서 RR이 효과적

### Multilevel Queue

![D1C41E7E-A42E-4001-A88A-D860A23D51DE.jpeg](5%20CPU%20Scheduling%2032e7ae89cbbf4233ac31877708adcc7d/D1C41E7E-A42E-4001-A88A-D860A23D51DE.jpeg)

위로 갈수록 높은 우선순위

CPU는 높은 우선순위부터 스케줄링

Ready Queue를 여러 개로 분할

- foreground (interactive)
- background (batch - no human interaction)

각 큐는 독립적인 스케줄링을 가짐

- fore - RR
- back - FCFS

큐에 대한 스케줄링이 필요

- Fixed priority scheduling
    - serve all from foreground then from background
- Time slice
    - 각 큐에 CPU time을 적절한 비율로 할당
    - eg. 80 - fore, 20 - back

### Multilevel Feedback Queue

![4F2A1DE3-55EB-48E4-ABC0-A8FDD5D245E1.jpeg](5%20CPU%20Scheduling%2032e7ae89cbbf4233ac31877708adcc7d/%ED%99%94%EB%A9%B4%20%EC%BA%A1%EC%B2%98%202023-01-06%20171749.png)

프로세스가 다른 큐로 이동 가능

에이징(aging)을 이와 같은 방식으로 구현 가능

Multilevel-feedback-queue scheduler를 정의하는 파라미터

- Queue의 수
- 각 큐의 scheduling algorithm
- Process를 상위 큐로 보내는 기준
- Process를 하위 큐로 내쫓는 기준
- 프로세스가 CPU 서비스를 받으려 할 때 들어갈 큐를 결정하는 기준

CPU burst가 짧은 프로세스에게 유리한 구조

CPU 이용 시간 예측 필요x

![791F8A14-3A5C-4907-9264-3B7A8B79641B.jpeg](5%20CPU%20Scheduling%2032e7ae89cbbf4233ac31877708adcc7d/791F8A14-3A5C-4907-9264-3B7A8B79641B.jpeg)

### Multi-Processor Scheduling

CPU가 여러 개인 경우 스케줄링은 더욱 복잡

- Homogeneous processor인 경우
    - Queue를 한 줄로 세워서 각 프로세스가 알아서 꺼내가게 할 수 있다
    - 반드시 특정 프로세서에서 수행되어야 하는 프로세스가 있은 경우에는 문제가 더 복잡해짐
- Load sharing
    - 일부 프로세서에 job이 몰리지 않도록 부하를 적절히 공유하는 메커니즘 필요
    - 별개의 큐를 두는 방법 vs 공동 큐를 사용하는 방법
- Symmetric Multiprocessing(SMP)
    - 각 프로세서가 각자 알아서 스케줄링 결정
- Asymmetric multiprocessing
    - 하나의 프로세서가 시스템 데이터의 접근과 공유를 책임지고 나머지 프로세서는 거기에 따름

 

### Real-Time Scheduling

real-time-job: 정해진 시간 안에 반드시 실행되어야 하는 job

**Hard real-time systems**

- Hard real-time-task는 정해진 시간 안에 반드시 끝내도록 스케줄링

**Soft real-time computing**

- Soft real-time-task는 일반 프로세스에 비해 높은 priority를 갖도록 해야 함
- 일반적인 time sharing에서 다른 프로세스와 섞여서 실행

### Thread Scheduling

Local Scheduling

- User level thread의 경우 사용자 수준의 thread library에 의해 어떤 thread를 스케줄할지 결정

Global Scheduling

- Kernel level thread의 경우 일반 프로세스와 마찬가지로 커널의 단기 스케줄러가 어떤 thread를 스케줄할지 결정

## Algorithm Evaluation

![68DA076D-4D5B-4551-8642-8276BE744929.jpeg](5%20CPU%20Scheduling%2032e7ae89cbbf4233ac31877708adcc7d/68DA076D-4D5B-4551-8642-8276BE744929.jpeg)

Queueing models

- 확률 분포로 주어지는 arrival rate와 service rate 등을 통해 각종 performance index 값을 계산

Implementation(구현) & Measurement(성능 측정)

- 실제 시스템에 알고리즘을 구현하여 실제 작업(workload)에 대해서 성능을 측정 비교

Simulation(모의 실험)

- 알고리즘을 모의 프로그램으로 작성 후 trace를 입력으로 하여 결과 비교