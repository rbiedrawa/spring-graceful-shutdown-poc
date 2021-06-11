# Spring Boot graceful shutdown (PoC)

This repository demonstrates how to configure graceful shutdown of Network and default TaskExecutor threads in Spring
Boot application.

## Getting Started

### Prerequisite

* Java 11

### Usage

* Start application in IDE.

* Create async tasks via curl:
  ```shell
  curl -X POST --location "http://localhost:8080/tasks"
  ```

* Terminate application while tasks are still running in background threads.

* Check application logs to see if application shutdown gracefully. Sample output below:
  ```shell
  # 2021-06-11 06:39:16.977  INFO [nio-8080-exec-1] com.rbiedrawa.app.tasks.TaskController   : Creating task 1
  # 2021-06-11 06:39:16.990  INFO [    my-thread-1] com.rbiedrawa.app.tasks.TaskService      : Starting long running process id 1
  # 2021-06-11 06:39:17.609  INFO [nio-8080-exec-2] com.rbiedrawa.app.tasks.TaskController   : Creating task 2
  # 2021-06-11 06:39:17.609  INFO [    my-thread-2] com.rbiedrawa.app.tasks.TaskService      : Starting long running process id 2
  # 2021-06-11 06:39:20.103  INFO [extShutdownHook] o.s.b.w.e.tomcat.GracefulShutdown        : Commencing graceful shutdown. Waiting for active requests to complete
  # 2021-06-11 06:39:20.107  INFO [tomcat-shutdown] o.s.b.w.e.tomcat.GracefulShutdown        : Graceful shutdown complete
  # 2021-06-11 06:39:20.163  INFO [extShutdownHook] o.s.s.concurrent.ThreadPoolTaskExecutor  : Shutting down ExecutorService 'applicationTaskExecutor'
  # 2021-06-11 06:39:26.989  INFO [    my-thread-1] com.rbiedrawa.app.tasks.TaskService      : Completed long running process id 1
  # 2021-06-11 06:39:27.609  INFO [    my-thread-2] com.rbiedrawa.app.tasks.TaskService      : Completed long running process id 2
  # 
  # Process finished with exit code 130 (interrupted by signal 2: SIGINT)
  ```

  *** *Using graceful shutdown with your IDE may not work properly if it does not send a proper SIGTERM signal. Refer to
  the documentation of your IDE for more details.*

## Important Endpoints

| Name | Endpoint | 
| -------------:|:--------:|
| `Spring Boot Application` | http://localhost:8080 |
| `Create Async Task - Endpoint` | http://localhost:8080/tasks |

## References

* [Spring Boot Docs - Graceful shutdown](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.graceful-shutdown)

## License

Distributed under the MIT License. See `LICENSE` for more information.
