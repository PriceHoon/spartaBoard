package com.sparta.spartaboard.service;


import com.sparta.spartaboard.dto.BoardRequestDTO;
import com.sparta.spartaboard.entity.Board;
import com.sparta.spartaboard.entity.User;
import com.sparta.spartaboard.jwt.JwtUtil;
import com.sparta.spartaboard.repository.BoardRepository;
import com.sparta.spartaboard.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
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

        // 토큰이 있는 경우에만 관심상품 조회 가능
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
    public Long update(Long id,BoardRequestDTO boardDto) {
       Board board = boardRepository.findById(id).orElseThrow(
               () -> new IllegalArgumentException("수정하고자 하는 게시글이 없습니다!")
       );

       board.update(boardDto);
       return board.getId();
    }

//    public Long delete(Long id,BoardRequestDTO boardDto) {
//
//       Board board = boardRepository.findById(id).orElseThrow(
//                () -> new IllegalArgumentException("수정하고자 하는 게시글이 없습니다!")
//        );
//       if(!board.getPwd().equals(boardDto.getPwd())){
//           throw new IllegalArgumentException("비밀번호가 틀립니다!");
//       }
//       boardRepository.deleteById(id);
//        return id;
//    }
}
