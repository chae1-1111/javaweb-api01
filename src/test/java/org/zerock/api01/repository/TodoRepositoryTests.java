package org.zerock.api01.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.zerock.api01.domain.Todo;
import org.zerock.api01.dto.PageRequestDTO;
import org.zerock.api01.dto.TodoDTO;

import java.time.LocalDate;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class TodoRepositoryTests {

    @Autowired
    TodoRepository todoRepository;

    @Test
    public void testInsert() {

        IntStream.rangeClosed(1,100).forEach(i -> {

            Todo todo = Todo.builder()
                    .title("Todo_"+i)
                    .dueDate(LocalDate.of(2023, (i%12) + 1, 6))
                    .writer("user_"+i)
                    .complete(false)
                    .build();

            todoRepository.save(todo);
        });
    }

    @Test
    public void testSearch() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(2)
                .from(LocalDate.of(2023,01,01))
                .to(LocalDate.of(2023,02,28))
                .build();

        Page<TodoDTO> result = todoRepository.list(pageRequestDTO);

        result.forEach(item -> log.info(item));
    }

}
