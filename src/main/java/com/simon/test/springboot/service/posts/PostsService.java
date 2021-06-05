package com.simon.test.springboot.service.posts;

import com.simon.test.springboot.domain.posts.Posts;
import com.simon.test.springboot.domain.posts.PostsRepository;
import com.simon.test.springboot.web.dto.PostsListResponseDto;
import com.simon.test.springboot.web.dto.PostsResponseDto;
import com.simon.test.springboot.web.dto.PostsSaveRequestDto;
import com.simon.test.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post is not exists. Post id: " + id));
        posts.update(requestDto.getTitle(), requestDto.getContent()); // dirty checking
        return id;
    }

    @Transactional
    public PostsResponseDto findById(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post is not exists. Post id: " + id));
        return new PostsResponseDto(posts);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc()
                .stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Post is not exists. Post id: " + id));
        postsRepository.delete(posts);
    }
}
