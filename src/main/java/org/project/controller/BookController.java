package org.project.controller;


import org.project.model.Book;
import org.project.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {

    @Autowired
    private BookRepo bookRepo;

    @GetMapping("/get-book-list")
    public String getBookList(@RequestParam(required=false, defaultValue="") String function, @RequestParam(required=false) String variable, Model model){
        //Decide the contents of the book list to display
        Iterable<Book> bookList = null;
        switch (function){
            case "search":
                bookList = bookRepo.findByAllColumns(variable.toLowerCase());
                break;
            default:
                bookList = bookRepo.findAll();
                break;
        }
        model.addAttribute("bookList", bookList);
        model.addAttribute("book", new Book());
        return "book-list";
    }

    @PostMapping("/add-book")
    public String createBook(@ModelAttribute Book book){
        bookRepo.save(book);
        return "redirect:/get-book-list";
    }

    @PostMapping("/delete-book/{id}")
    public String deleteBook(@PathVariable int id){
        bookRepo.deleteById(id);
        return "redirect:/get-book-list";
    }

    @GetMapping("/edit-book/{id}")
    public String editBook(@PathVariable int id, Model model){
        Book book = bookRepo.findById(id);
        model.addAttribute("book", book);
        return "edit-book";
    }

    @PostMapping("/update-book")
    public String updateBook(@ModelAttribute Book book){
        bookRepo.save(book);
        return "redirect:/get-book-list";
    }

}
