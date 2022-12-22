package com.sparta.spartaboard.service;

import com.sparta.spartaboard.dto.CommentRequestDto;
import com.sparta.spartaboard.dto.CommentResponseDto;
import com.sparta.spartaboard.dto.ResponseMsgStatusCodeDto;
import com.sparta.spartaboard.entity.Board;
import com.sparta.spartaboard.entity.Comment;
import com.sparta.spartaboard.entity.User;
import com.sparta.spartaboard.entity.UserRoleEnum;
import com.sparta.spartaboard.jwt.JwtUtil;
import com.sparta.spartaboard.repository.BoardRepository;
import com.sparta.spartaboard.repository.CommentsRepository;
import com.sparta.spartaboard.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentsRepository commentsRepository;
    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    private final BoardRepository boardRepository;

    @Transactional
    public CommentResponseDto addComments(Long id, CommentRequestDto requestDto, HttpServletRequest request) {

        String token = jwtUtil.resolveToken(request);
        Claims claims;

        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글을 작성하고자 하는 게시글이 없습니다!")
        );

        if (token != null) {

            // Token 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Comment comment = new Comment(requestDto, user, board);
            commentsRepository.save(comment);
            CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
            return commentResponseDto;
        }
        return null;
    }

    @Transactional
    public List<CommentResponseDto> getComments(Long id, HttpServletRequest request) {

        String token = jwtUtil.resolveToken(request);
        Claims claims;

        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글을 조회하고자 하는 게시글이 없습니다!")
        );

        if (token != null) {

            // Token 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            List<Comment> comments = board.getComments();
            List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();

            for (Comment comment : comments) {
                CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
                commentResponseDtoList.add(commentResponseDto);
            }

            return commentResponseDtoList.stream().sorted(Comparator.comparing(CommentResponseDto::getModifiedAt).reversed()).collect(Collectors.toList());
        }
        return null;
    }

    @Transactional
    public CommentResponseDto update(Long id,CommentRequestDto commentRequestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;


        if (token != null) {

            // Token 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Comment comment = commentsRepository.findById(id).orElseThrow(() ->
                    new IllegalArgumentException("수정하고자 하는 댓글이 없습니다!"));

            if (comment.getUser().getId() == user.getId() || user.getUserRoleEnum().equals(UserRoleEnum.ADMIN)) {

                comment.update(commentRequestDto);
                return new CommentResponseDto(comment);
            } else {
                throw new IllegalArgumentException("해당 사용자가 혹은 관리자  아니면 댓글을 수정할 수 없습니다!");
            }

        }
        return null;
    }

    public ResponseMsgStatusCodeDto delete(Long id, HttpServletRequest request) {

        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {

            // Token 검증
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );

            Comment comment = commentsRepository.findById(id).orElseThrow(() ->
                    new IllegalArgumentException("삭제하고자 하는 댓글이 없습니다!"));

            if (comment.getUser().getId() == user.getId() || user.getUserRoleEnum().equals(UserRoleEnum.ADMIN)) {

                commentsRepository.deleteById(id);
                ResponseMsgStatusCodeDto responseMsgStatusCodeDto = new ResponseMsgStatusCodeDto("댓글삭제 완료!", HttpStatus.OK.value());
                return  responseMsgStatusCodeDto;
            } else {
                throw new IllegalArgumentException("해당 사용자가 혹은 관리자가 아니면 댓글을 삭제할 수 없습니다!");
            }


        }
        return null;
    }
}
