package com.academy.security.controller;

import com.academy.security.dto.BookDto;
import com.academy.security.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookController {
  private final BookService bookService;

  @GetMapping("/books")
  public List<BookDto> getAll() {

    return bookService.getAll();
  }
}
