package com.academy.security.service;

import com.academy.security.dto.BookDto;
import java.util.List;

public interface BookService {
  List<BookDto> getAll();
}
