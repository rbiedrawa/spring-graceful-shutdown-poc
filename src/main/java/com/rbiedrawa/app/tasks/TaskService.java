package com.rbiedrawa.app.tasks;

import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TaskService {

	@Async
	@SneakyThrows
	public void simulateLongRunningProcess(int taskId) {
		log.info("Starting long running process id {}", taskId);
		TimeUnit.SECONDS.sleep(10);
		log.info("Completed long running process id {}", taskId);
	}

}
