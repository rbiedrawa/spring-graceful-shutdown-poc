package com.rbiedrawa.app.tasks;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("tasks")
@RequiredArgsConstructor
public class TaskController {
	private final AtomicInteger idGenerator = new AtomicInteger(0);

	private final TaskService taskService;

	@PostMapping
	public ResponseEntity create() {
		var taskId = idGenerator.incrementAndGet();
		log.info("Creating task {}", taskId);

		taskService.simulateLongRunningProcess(taskId);

		return ResponseEntity.accepted().build();
	}

}
