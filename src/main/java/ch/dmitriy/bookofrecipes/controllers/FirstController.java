package ch.dmitriy.bookofrecipes.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

    @GetMapping
    public String programStart(){
        return "Запуск программы";
    }

    @GetMapping("/info/student")
    public String infoStudent(@RequestParam String student) {
        return "Имя ученика " + student;
    }
    @GetMapping("/info/projectName")
    public String projectName(@RequestParam String project) {
        return "Название проекта " + project;
    }
    @GetMapping("/info/project")
    public String infoProject(@RequestParam String project) {
        return "Описание проекта " + project;
    }
    @GetMapping("/info/projectDate")
    public String infoDate(@RequestParam String project) {
        return "Дата создания проекта" + project;
    }
}
