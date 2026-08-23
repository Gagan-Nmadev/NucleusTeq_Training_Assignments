package com.training.todoapp.controller.TodaController;

import com.training.todoapp.dto.TodoDTO;
import com.training.todoapp.service.TodoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private static final Logger log = LoggerFactory.getLogger(TodoController.class);

    private final TodoService todoService;

    // Constructor Injection (mandatory)
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    //  CREATE
    @PostMapping
    public ResponseEntity<TodoDTO> createTodo(@Valid @RequestBody TodoDTO dto) {
        log.info("POST /todos — title='{}'", dto.getTitle());

        TodoDTO created = todoService.createTodo(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    //  GET ALL
    @GetMapping
    public ResponseEntity<List<TodoDTO>> getAllTodos() {
        log.info("GET /todos");

        return ResponseEntity.ok(todoService.getAllTodos());
    }

    //  GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<TodoDTO> getTodoById(@PathVariable Long id) {
        log.info("GET /todos/{}", id);

        return ResponseEntity.ok(todoService.getTodoById(id));
    }

    //  UPDATE (FULL)
    @PutMapping("/{id}")
    public ResponseEntity<TodoDTO> updateTodo(@PathVariable Long id,
                                              @Valid @RequestBody TodoDTO dto) {
        log.info("PUT /todos/{}", id);

        return ResponseEntity.ok(todoService.updateTodo(id, dto));
    }

    //  UPDATE (PARTIAL)
    @PatchMapping("/{id}")
    public ResponseEntity<TodoDTO> patchTodo(@PathVariable Long id,
                                             @RequestBody TodoDTO dto) {
        log.info("PATCH /todos/{}", id);

        return ResponseEntity.ok(todoService.updateTodo(id, dto));
    }

    //  DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        log.info("DELETE /todos/{}", id);

        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
}