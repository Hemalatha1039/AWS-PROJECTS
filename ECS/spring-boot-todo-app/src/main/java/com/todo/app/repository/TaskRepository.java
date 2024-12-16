package com.todo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.app.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}