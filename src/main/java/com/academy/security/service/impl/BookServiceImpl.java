package com.academy.security.service.impl;

import com.academy.security.dto.BookDto;
import com.academy.security.model.entity.Book;
import com.academy.security.model.repository.BookRepository;
import com.academy.security.service.BookService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
  private final BookRepository bookRepository;

  @Override
  public List<BookDto> getAll() {
    var books = bookRepository.findAll();
    var bookDtos = new ArrayList<BookDto>();

    books.forEach(book -> {
      var bookDto = new BookDto();
      bookDto.setTitle(book.getTitle());
      bookDto.setYear(book.getYear());

      bookDtos.add(bookDto);
    });

    return bookDtos;
  }
}
