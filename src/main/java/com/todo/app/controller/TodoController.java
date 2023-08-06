package com.todo.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.todo.app.entity.Todo;
import com.todo.app.mapper.TodoMapper;

@Controller
public class TodoController {

    @Autowired
    TodoMapper todoMapper;

    @RequestMapping(value="/")
    public String index(Model model) {
        List<Todo> list = todoMapper.selectIncomplete();
        List<Todo> doneList = todoMapper.selectComplete();
        List<Todo> sortList = todoMapper.selectSort();
        model.addAttribute("todos", list);
        model.addAttribute("doneTodos", doneList);
        model.addAttribute("iterationSource", "todos");
        model.addAttribute("sort", sortList);

        return "index";
    }

    @RequestMapping(value="/add")
    public String add(Todo todo) {
        todoMapper.add(todo);
        return "redirect:/";
    }

    @RequestMapping(value="/update")
    public String update(Todo todo) {
        todoMapper.update(todo);
        return "redirect:/";
    }

    @RequestMapping(value="/delete")
    public String delete() {
        todoMapper.delete();
        return "redirect:/";
    }
    
    @RequestMapping(value="/sort")
    public String sort(Model model) {
        List<Todo> sortList = todoMapper.selectSort(); // Get sorted todos
        model.addAttribute("sort", sortList); // Add sorted todos to the model
        model.addAttribute("iterationSource", "sort"); // Set the iterationSource to 'sort' to display sorted todos
        return "index"; // Return the view to display the sorted todos on the same page
    }

}
