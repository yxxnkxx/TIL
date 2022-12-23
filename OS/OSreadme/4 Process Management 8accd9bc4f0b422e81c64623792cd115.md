# 4. Process Management

## 프로세스 생성

부모 프로세스가 자식 프로세스 생성

프로세스의 트리(계층 구조) 형성

프로세스는 자원을 필요로 함

- 운영체제로부터 받는다
- 부모와 공유한다

자원의 공유

- 부모와 자식이 모든 자원을 공유하는 모델
- 일부를 공유하는 모델
- 전혀 공유하지 않는 모델 (원칙)

수행(Execution)

- 부모와 자식은 공존하며 수행되는 모델
- 자식이 종료될 때까지 부모가 기다리는 모델

🟡 주소 공간

- 자식은 부모의 공간을 복사함 (binary and OS data)
- 자식은 그 공간에 새로운 프로그램을 올림

유닉스의 예

- **fork()** 시스템 콜이 새로운 프로세스를 생성
  - 부모를 그대로 복사 (OS data execpt PID + binary)
  - 주소 공간 할당
- fork 다음에 이어지는 exec() 시스템 콜을 통해 새로운 프로그램을 메모리에 올림

Copy-on-write(COW) : write가 발생했을 때(내용이 바뀔 때) copy한다

## 프로세스 종료

프로세스가 마지막 명령을 수행한 후 운영체제에 이를 알려줌(**exit**)

- 자식이 부모에게 output data를 보냄 (via wait)
- 프로세스의 각종 자원들이 운영체제에게 반납됨

부모 프로세스가 자식의 수행을 종료시킴 (**abort**)

- 자식이 할당 자원의 한계치를 넘어섬
- 자식에게 할당된 태스크가 더이상 필요하지 않음
- 부모가 종료(exit)하는 경우
  - 운영체제는 부모 프로세스가 종료하는 경우 자식이 더 이상 수행되도록 두지 않는다
  - 단계적인 종료

### fork() 시스템 콜

A process is created by the fork() system call

- creates a new address space that is a duplicate of the caller

![Untitled](4%20Process%20Management%208accd9bc4f0b422e81c64623792cd115/Untitled.png)

```c
int main()
{ int pid;
	pid = fork();
	if (pid == 0) /* this is child */
		printf("\n Hello, I am child!\n");
	else if (pid > 0) /* this is parent */
		printf("\n Hello, I am parent!\n");
}
```

fork() 실행 → 자식은 부모 프로세스를 복사하고 fork() 이후부터 코드를 수행

pid로 부모와 자식 프로세스 구분

### exec() 시스템 콜

어떤 프로그램을 완전히 새로운 프로세스로 태어나게 함

A process can execute a different program by the exec() system call

- replaces the memory image of the caller with a new program

```c
int main()
{ int pid;
	pid = fork();
	if (pid == 0) /* this is child */
	{
		printf("\n Hello, I am child!\n");
		execlp("/bin/date", "/bin/date", (char*)0);
	}
	else if (pid > 0) /* this is parent */
		printf("\n Hello, I am parent!\n");
}
```

한번 exec하면 다시 돌아올 수 없음

execlp - 프로그램이름 2번, args

### wait() 시스템 콜

프로세스 A가 wait() 시스템 콜을 호출

- 커널은 child가 종료될 때까지 프로세스 A를 sleep 시킨다 (block 상태)
- Child process가 종료되면 커널은 프로세스 A를 깨운다 (ready 상태)

![Untitled](4%20Process%20Management%208accd9bc4f0b422e81c64623792cd115/Untitled%201.png)

### exit() 시스템 콜

프로세스의 종료

- 자발적 종료
  - 마지막 statement 수행 후 exit() 시스템 콜을 통해
  - 프로그램에 명시적으로 적어주지 않아도 main함수가 리턴되는 위치에 컴파일러가 넣어줌
- 비자발적 종료
  - 부모 프로세스가 자식 프로세스를 강제 종료시킴
    - 자식 프로세스가 한계치를 넘어서는 자원 요청
    - 자식에게 할당된 태스크가 더 이상 필요하지 않음
  - 키보드로 kill, break 등을 친 경우
  - 부모가 종료하는 경우
    - 부모 프로세스가 종료하기 전에 자식들이 먼저 종료함

## 프로세스 간 협력

독립적 프로세스 (independent process)

- 프로세스는 각자의 주소 공간을 가지고 수행되므로 원칙적으로 하나의 프로세스는 다른 프로세스의 수행에 영향을 미치지 못함

협력 프로세스 (Cooperating process)

- 프로세스 간 협력 메커니즘을 통해 하나의 프로세스가 다른 프로세스의 수행에 영향을 미칠 수 있음

프로세스 간 협력 메커니즘 (IPC: Interprocess Communication)

- 메시지를 전달하는 방법
  - message passing: 커널을 통해 메시지 전달
- 주소 공간을 공유하는 방법
  - shared memory: 서로 다른 프로세스 간에도 일부 주소 공간을 공유하게 하는 shared memory 메커니즘이 있음
  - thread: thread는 사실상 하나의 프로세스 → 프로세스 간의 협력으로 보기는 어렵지만 동일한 process를 구성하는 thread들 간에는 주소 공간을 공유하므로 협력이 가능

### Message Passing

🟡 Message system

프로세스 사이에 공유 변수(shared variable)를 일체 사용하지 않고 통신하는 시스템

Direct Communication

- 통신하려는 프로세스의 이름을 명시적으로 표시

![Untitled](4%20Process%20Management%208accd9bc4f0b422e81c64623792cd115/Untitled%202.png)

Indiret Communication

- mailbox (또는 port)를 통해 메시지를 간접 전달

![Untitled](4%20Process%20Management%208accd9bc4f0b422e81c64623792cd115/Untitled%203.png)

공통: 운영체제 커널을 통해 전달한다

![Untitled](4%20Process%20Management%208accd9bc4f0b422e81c64623792cd115/Untitled%204.png)
