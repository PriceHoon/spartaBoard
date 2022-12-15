package com.sparta.spartaboard.service;


import com.sparta.spartaboard.dto.BoardRequestDTO;
import com.sparta.spartaboard.dto.DeleteSuccessDto;
import com.sparta.spartaboard.entity.Board;
import com.sparta.spartaboard.entity.User;
import com.sparta.spartaboard.jwt.JwtUtil;
import com.sparta.spartaboard.repository.BoardRepository;
import com.sparta.spartaboard.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    @Transactional
    public List<Board> getBoardAll() {
        return boardRepository.findAllByOrderByModifiedAtDesc();
    }

    @Transactional
    public Board createBoard(BoardRequestDTO boardDto, HttpServletRequest request) {


        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        // 토큰이 있는 경우에만 게시글 생성 가능
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

            Board board = new Board(boardDto, user);
            boardRepository.save(board);
            return board;
        }
        return null;
    }

    @Transactional
    public Optional<Board> findBoardById(Long id) {
        return Optional.ofNullable(boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("특정 게시글이 없습니다!")
        ));
    }

    @Transactional
    public Board update(Long id, BoardRequestDTO boardDto, HttpServletRequest request) {

        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        //선택한 게시글 찾기
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("수정하고자 하는 게시글이 없습니다!")
        );

        // 토큰이 있는 경우에만 게시글 수정 가능
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

            if (!(board.getUser().getId() == user.getId())) {
                throw new IllegalArgumentException("해당 사용자가 아니면 게시글을 수정할 수 없습니다!");
            }
            board.update(boardDto);
            return board;
        }
        return null;
    }

    public ResponseEntity delete(Long id, BoardRequestDTO boardDto, HttpServletRequest request) {

        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("삭제하고자 하는 게시글이 없습니다!")
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

            if (!(board.getUser().getId() == user.getId())) {
                throw new IllegalArgumentException("해당 사용자가 아니면 게시글을 삭제할 수 없습니다!");
            }

            boardRepository.deleteById(id);

            DeleteSuccessDto deleteSuccessDto = new DeleteSuccessDto();
            deleteSuccessDto.setMsg("게시글 삭제 성공!");
            deleteSuccessDto.setStatusCode(200);
            return ResponseEntity.status(HttpStatus.OK).body(deleteSuccessDto);
        }
        return null;
    }
}
