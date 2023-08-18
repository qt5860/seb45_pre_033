package preproject.spring.comment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import preproject.spring.comment.dto.CommentReqDto;
import preproject.spring.comment.dto.CommentResDto;
import preproject.spring.comment.service.CommentService;

@RestController
@RequestMapping("/answer/comment")public class CommentController {
    final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 등록
    @PostMapping("/{answer_id}")
    public ResponseEntity<CommentResDto> createComment(@PathVariable(value = "answer_id") Long answer_id, @RequestBody CommentReqDto commentReqDto) throws Exception {
        try {
            commentReqDto.setAnswerId(answer_id);
            commentReqDto.setUserId(1L); // 작성자 아이디 (나중에 수정)


            CommentResDto comment = commentService.createComment(commentReqDto);

            return new ResponseEntity<>(comment, HttpStatus.CREATED); // 204 없으면 500
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 수정
    @PatchMapping("/{comment_id}")
    public ResponseEntity<CommentResDto> updateComment(@PathVariable(value = "comment_id") Long answer_id, @RequestBody CommentReqDto commentReqDto) throws Exception {
        try {
            commentReqDto.setAnswerId(answer_id);

            CommentResDto commentResDto = commentService.updateComment(commentReqDto);

            return new ResponseEntity<>(commentResDto, HttpStatus.OK); // 200
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }
    }

    // 삭제
    @DeleteMapping("{comment_id}")
    public ResponseEntity<Void> deleteComment(@PathVariable(value = "comment_id") Long commentId) throws Exception {
        try {
            commentService.deleteComment(commentId);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        }
    }
}