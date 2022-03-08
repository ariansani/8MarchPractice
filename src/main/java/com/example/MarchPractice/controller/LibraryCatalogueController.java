package com.example.MarchPractice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import com.example.MarchPractice.model.Book;
import com.example.MarchPractice.service.LibraryService;

@Controller
public class LibraryCatalogueController {
    private Logger logger = Logger.getLogger(LibraryCatalogueController.class.getName());

    @Autowired
    LibraryService service;

    @GetMapping("/")
    public String bookForm(Model model){
        logger.log(Level.INFO,"Show the book form");
        model.addAttribute("book", new Book());
        return "book";
    }

    @GetMapping("/getBook/{bookId}")
    public String getBook(Model model, @PathVariable(value="bookId")String bookId){
        logger.log(Level.INFO, "bookId "+ bookId);
        Book book = service.findById(bookId);
        logger.log(Level.INFO,"Title "+ book.getTitle());
        logger.log(Level.INFO,"Title "+ book.getAuthor());
        logger.log(Level.INFO,"Title "+ book.getImgFileName());
        model.addAttribute("book",book);
        return "bookAdded";
    }


    @GetMapping("/book")
    public String getAllBook(Model model, @RequestParam(name="startIndex")String startIndex){
        List<Book> resultFromSvc = service.findAll(Integer.parseInt(startIndex));
        model.addAttribute("books", resultFromSvc);
        return "listBook";
    }

    @PostMapping("/book")
    public String bookSubmit(@ModelAttribute Book book, Model model,HttpServletResponse httpResponse){
        logger.log(Level.INFO,"Id: "+ book.getId());
        logger.log(Level.INFO,"Title: "+ book.getTitle());
        logger.log(Level.INFO,"Author: "+ book.getAuthor());
        logger.log(Level.INFO,"ImgFile: "+ book.getImgFileName());
        service.save(book);
        
        httpResponse.setStatus(HttpStatus.CREATED.value());
        model.addAttribute("book",book);
        return "bookAdded";
    }

}
