package com.example.blog.Board;

import com.example.blog._core.util.Resp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin // cors error 해결 용
@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/api")
    public Resp<?> list() { // ? 는 와일드카드
        List<BoardResponse.DTO> boardList = boardService.게시글목록보기();
        return Resp.ok(boardList);
    }

    // post는 insert만 할거다
    @PostMapping("/api/board")
    public Resp<?> save(@Valid @RequestBody BorderRequest.SaveDTO saveDTO, Errors errors) {
        boardService.게시글쓰기(saveDTO);
        return Resp.ok(null);
    }

    // 주소는 where절에 들어감 -수정은 put
    @PutMapping("/api/board/{id}")
    public Resp<?> update(@PathVariable("id") Integer id, @Valid @RequestBody BorderRequest.UpdateDTO updateDTO, Errors errors) {
        boardService.게시글수정하기(id, updateDTO);
        return Resp.ok(null);
    }

    // 어노테이션에 붙이고 동사 주소 /delete를 지우기 -주소에 동사적지 않음
    // delete는 body를 못 받음 -받는건 post와 put만
    @DeleteMapping("/api/board/{id}")
    public Resp<?> delete(@PathVariable("id") Integer id) {
        boardService.게시글삭제(id);
        return Resp.ok(null);
    }

    @GetMapping("/api/board/{id}")
    public Resp<?> detail(@PathVariable("id") Integer id) {
        BoardResponse.DetailDTO boardDetail = boardService.게시글상세보기(id);
        return Resp.ok(boardDetail);
    }

}