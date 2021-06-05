package com.simon.test.springboot.web;

import com.simon.test.springboot.service.posts.PostsService;
import com.simon.test.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")    // 일종의 router
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc()); // template 변수 매핑
        return "index"; // 해당하는 이름의 mustache file을 반환해줌.
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "post-saves";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "post-update";
    }
}
