package org.project.controller;

import org.project.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

	@Autowired
	private BookRepo bookRepository;

	@GetMapping("/get-book-list")
	public String getBookList(Model model){
		model.addAttribute("bookList", bookRepository.findAll());
		return "book-list";
	}
}
